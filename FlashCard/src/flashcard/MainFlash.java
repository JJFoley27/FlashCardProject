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

public class MainFlash extends JFrame implements MouseListener{
    String[] names;
    String[] answers;
    JButton[] buttonArr;
    CardLayout cardLayout = new CardLayout();
    JPanel buttonContainer = new JPanel(cardLayout);
    public MainFlash() {
        super("Flash ahhah savior of the universe");
        getCards("flash");
        add(buttonContainer);
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
        if(SwingUtilities.isLeftMouseButton(e)){
            cardLayout.next(buttonContainer);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
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
        int counter =0;
        Path inf = Paths.get("flashcardDecks\\" + fileName+ ".csv");
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = reader.readLine();
            names = s.split(",");
            s = reader.readLine();
            answers = s.split(",");
            buttonArr = new JButton[names.length + answers.length];
            for(int i = 0; i < names.length; i++){
                buttonArr[counter] = new JButton(names[i]);
                buttonContainer.add(buttonArr[counter]);
                buttonArr[counter].addMouseListener(this);
                counter+=1;
                buttonArr[counter] = new JButton(answers[i]);
                buttonContainer.add(buttonArr[counter]);
                buttonArr[counter].addMouseListener(this);
                counter+=1;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            
        }
    }
}
