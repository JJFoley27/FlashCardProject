package flashcard;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

/**
 *
 * @author Michael Flett, (add your guys's names here)
 */
public class MainFlash extends JFrame implements MouseListener, ActionListener{

    String[] names;
    String[] answers;
    JButton[] buttonArr;
    CardLayout cardLayout = new CardLayout();
    JPanel buttonContainer = new JPanel(cardLayout);
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem newDeck = new JMenuItem("New Deck");  // send to new JFrame
    //   part of different jframe   JMenuItem editDeck = new JMenuItem("Add Card to current Deck");
    JMenu random = new JMenu("Random");
    JMenu about = new JMenu("About");
    JMenu openDeck = new JMenu("Open Decks");
    JMenuItem[] files;
    //JMenuItem menuItems[] = {newDeck, editDeck, exit};
    JMenuItem fileMenu[] = {newDeck, exit};

    public MainFlash() {
        super("Flash ahhah savior of the universe");
        getCards("deck0");
        add(buttonContainer);
        setMenuBar();
    }
    private void setMenuBar() {
        setJMenuBar(menubar);
        menubar.add(file);
        file.add(newDeck);
        //file.add(editDeck);
        menubar.add(random);
        menubar.add(about);
        // adds action listeners to all menu items
        deckFileBarGen();
        for (int i = 0; i < fileMenu.length; i++) {
            file.add(fileMenu[i]);
            fileMenu[i].addActionListener((ActionListener) this);
        }
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        Object choice = ae.getSource();
        // loops through all menu items to find match with choice
        for (int i = 0; i < fileMenu.length; i++) {
            // if match is found
            if (choice == fileMenu[i]) {

            }
            if (choice == exit) {
                System.exit(0);
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) {
            cardLayout.next(buttonContainer);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            cardLayout.previous(buttonContainer);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
    }
    
    public static void main(String[] args) {
        MainFlash frame = new MainFlash();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private void getCards(String fileName) {
        names = null;
        answers = null;
        buttonArr = null;
        int counter = 0;
        Path inf = Paths.get("flashcardDecks\\" + fileName + ".csv");
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            reader.readLine();
            String s = reader.readLine();
            names = s.split(",");
            s = reader.readLine();
            answers = s.split(",");
            buttonArr = new JButton[names.length + answers.length];
            for (int i = 0; i < names.length; i++) {
                buttonArr[counter] = new JButton(names[i]);
                buttonContainer.add(buttonArr[counter]);
                buttonArr[counter].addMouseListener(this);
                counter += 1;
                buttonArr[counter] = new JButton(answers[i]);
                buttonContainer.add(buttonArr[counter]);
                buttonArr[counter].addMouseListener(this);
                counter += 1;

            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
    
    private void deckFileBarGen() {
        file.add(openDeck);
        int listAmount = new File("flashcardDecks").listFiles().length;
        files = new JMenuItem[listAmount];
        for(int i = 0; i < listAmount; i++){
            System.out.println(i);
        Path inf = Paths.get("flashcardDecks\\deck" + i + ".csv");
        
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String[] s = reader.readLine().split(",");
            System.out.println(s[0]);
            files[i] = new JMenuItem(s[0]);
            openDeck.add(files[i]);
            files[i].addActionListener(this);
            
        } catch (Exception ex) {
            System.out.println(ex);

        }
        }
        
    }
    

    
}
