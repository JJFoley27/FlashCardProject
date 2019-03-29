package flashcard;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

/**
 *
 * @author Michael Flett, Christopher Coen, Alix Kramer, Jesse Foley
 */
class MainJFrame extends JFrame implements MouseListener, ActionListener {

    String[] names;
    String[] answers;
    JButton[] buttonArr;
    CardLayout cardLayout = new CardLayout();
    JPanel buttonContainer = new JPanel(cardLayout);
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem newDeck = new JMenuItem("New Deck");
    JMenu random = new JMenu("Random");
    JMenu about = new JMenu("About");
    JMenu openDeck = new JMenu("Open Decks");
    JMenuItem[] files;
    JMenuItem fileMenu[] = {openDeck, newDeck, exit};

    public MainJFrame() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exit) {
            System.exit(0);
        } else if (source == newDeck) {
            NewDeck frame = new NewDeck();
            frame.setSize(600, 300);
            frame.setVisible(true);
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
        for (int i = 0; i < files.length; i++) {
            openDeck.remove(files[i]);
        }
        rePaint();
        deckFileBarGen();
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

    public void getCards(String fileName) {
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

    public void deckFileBarGen() {
        int listAmount = new File("flashcardDecks").listFiles().length;
        files = null;
        files = new JMenuItem[listAmount];
        for (int i = 0; i < listAmount; i++) {
            Path inf = Paths.get("flashcardDecks\\deck" + i + ".csv");
            try {
                InputStream input = Files.newInputStream(inf);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String[] s = reader.readLine().split(",");
                files[i] = new JMenuItem(s[0]);
                openDeck.add(files[i]);
                files[i].addActionListener(this);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void rePaint() {
        revalidate();
        repaint();
    }
}

public class MainFlash {

    public static void main(String[] args) {
        MainJFrame frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
