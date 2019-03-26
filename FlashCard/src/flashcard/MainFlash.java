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
    JMenu topRow[] = {file, random, about};
    //JMenuItem menuItems[] = {newDeck, editDeck, exit};
    JMenuItem menuItems[] = {newDeck, exit};

    public MainFlash() {
        super("Flash ahhah savior of the universe");
        getCards("flash");
        add(buttonContainer);

        setJMenuBar(menubar);
        menubar.add(file);
        file.add(newDeck);
        //file.add(editDeck);
        file.add(exit);
        menubar.add(random);
        menubar.add(about);

        for (int i = 0; i < topRow.length; i++) {
            topRow[i].addActionListener((ActionListener) this);
        }

        // adds action listeners to all menu items
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].addActionListener((ActionListener) this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
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

    public void actionPerformed(ActionEvent ae) {
        Object choice = ae.getSource();

        // loops through all menu items to find match with choice
        for (int i = 0; i < menuItems.length; i++) {
            // if match is found
            if (choice == menuItems[i]) {

            }
            if (choice == exit) {
                System.exit(0);
            }
        }
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
        Path inf = Paths.get("flashcardDecks\\" + fileName + ".txt");
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
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
}
