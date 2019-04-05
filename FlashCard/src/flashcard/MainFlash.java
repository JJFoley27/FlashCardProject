package flashcard;

import de.vogella.algorithms.shuffle.ShuffleArray;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author Michael Flett, Christopher Coen, Alix Kramer, Jesse Foley
 */
class MainFlash extends JFrame implements MouseListener, ActionListener {

    String[] names;
    String[] answers;
    JButton[] buttonArr = null;
    CardLayout cardLayout = new CardLayout();
    JPanel buttonContainer = new JPanel(cardLayout);
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem newDeck = new JMenuItem("New Deck");
    JMenu tools = new JMenu("Tools");
    JMenuItem random = new JMenuItem("Random");
    JMenuItem alphabetical = new JMenuItem("Alphabetical");
    JMenuItem options = new JMenuItem("Options");
    JMenu about = new JMenu("About");
    JMenu openDeck = new JMenu("Open Decks");
    JMenuItem editDeck = new JMenuItem("Edit Deck");
    JMenuItem[] files;
    JMenuItem fileMenu[] = {openDeck, newDeck, exit};
    String currentDeck = "deck0";
    String[] optionArr;

    public MainFlash() {
        super("Flash!!!!! Ahhah Savior of the Universe!!!!!!");
        getCards(currentDeck, false, false);
        getApp();
        add(buttonContainer);
        setMenuBar();
    }

    private void setMenuBar() {
        setJMenuBar(menubar);
        menubar.add(file);
        //file.add(editDeck);
        menubar.add(tools);
        tools.add(random);
        tools.add(alphabetical);
        tools.add(options);
        tools.add(editDeck);
        random.addActionListener(this);
        alphabetical.addActionListener(this);
        options.addActionListener(this);
        menubar.add(about);
        editDeck.addActionListener(this);
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
            frame.setSize(800, 600);
            frame.setVisible(true);
        } else if (source == random) {
            getCards(currentDeck, true, false);
        } else if (source == alphabetical) {
            getCards(currentDeck, false, true);
        } else if (source == options) {
            Options frame = new Options();
            frame.setVisible(true);
        } else if(source == editDeck){
            editDeck(currentDeck);
        }
        else {
            for (int i = 0; i < files.length; i++) {
                if (source == files[i]) {
                    currentDeck = "deck" + i;
                }
            }
            getCards(currentDeck, false, false);
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
        getApp();
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
    //add methods here

    public void getCards(String fileName, boolean random, boolean alphabetical) {
        if (buttonArr != null) {
            try {
                for (int i = 0; i < buttonArr.length; i++) {
                    buttonContainer.remove(buttonArr[i]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
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
            reader.close();
            input.close();
            if (random == true) {
                randomizeButtons();
            }
            if (alphabetical == true) {
                alphabetizeButtons();
            }
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
        rePaint();
    }

    public void alphabetizeButtons() {
        int[] numArr = new int[names.length];
        String[] tempName = names.clone();
        Arrays.sort(tempName);
        for (int i = 0; i < names.length; i++) {
            for (int a = 0; a < names.length; a++) {
                if (tempName[i].equals(names[a])) {
                    numArr[i] = a;
                }
            }
        }
        tempName = null;
        tempName = new String[names.length];
        String[] tempAnswers = new String[answers.length];
        for (int i = 0; i < names.length; i++) {
            tempName[i] = names[numArr[i]];
            tempAnswers[i] = answers[numArr[i]];
        }
        names = tempName;
        answers = tempAnswers;
    }

    public void randomizeButtons() {
        int[] numArr = new int[names.length];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = i;
        }
        numArr = ShuffleArray.shuffleArray(numArr);
        String[] tempName = new String[names.length];
        String[] tempAnswers = new String[answers.length];
        for (int i = 0; i < numArr.length; i++) {
            tempName[i] = names[numArr[i]];
            tempAnswers[i] = answers[numArr[i]];
        }
        names = tempName;
        answers = tempAnswers;
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
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public void editDeck(String deck) {
        System.out.println(deck);
        try {
            Desktop.getDesktop().open(new File("flashcardDecks//" + deck + ".csv"));
        } catch (IOException ex) {

        }
    }

    public void rePaint() {
        revalidate();
        repaint();
    }

    public void getApp() {
        Path inf = Paths.get("options.txt");
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            optionArr = reader.readLine().split(",");
            Font newFont = new Font(optionArr[0], Font.PLAIN, Integer.parseInt(optionArr[1]));
            for (int i = 0; i < buttonArr.length; i++) {
                buttonArr[i].setForeground(getFontColor());
                buttonArr[i].setBackground(getCardColor());
                buttonArr[i].setFont(newFont);
            }
            reader.close();
            input.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public Color getCardColor() {
        switch (optionArr[3]) {
            case "red":
                return Color.RED;
            case "black":
                return Color.black;
            case "blue":
                return Color.blue;
            case "cyan":
                return Color.cyan;
            case "darkGray":
                return Color.darkGray;
            case "gray":
                return Color.gray;
            case "green":
                return Color.green;
            case "lightGray":
                return Color.lightGray;
            case "magenta":
                return Color.magenta;
            case "orange":
                return Color.orange;
            case "pink":
                return Color.pink;
            case "white":
                return Color.white;
            case "yellow":
                return Color.yellow;
            default:
                return Color.white;
        }
    }

    public Color getFontColor() {
        switch (optionArr[2]) {
            case "red":
                return Color.RED;

            case "black":
                return Color.black;

            case "blue":
                return Color.blue;

            case "cyan":
                return Color.cyan;

            case "darkGray":
                return Color.darkGray;

            case "gray":
                return Color.gray;

            case "green":
                return Color.green;

            case "lightGray":
                return Color.lightGray;

            case "magenta":
                return Color.magenta;

            case "orange":
                return Color.orange;

            case "pink":
                return Color.pink;

            case "white":
                return Color.white;

            case "yellow":
                return Color.yellow;

            default:
                return Color.black;
        }

    }

    public static void main(String[] args) {
        MainFlash frame = new MainFlash();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);
    }
}
