/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.*;
import org.jdesktop.swingx.VerticalLayout;

/**
 *
 * @author Owner
 */
public class NewDeck extends JFrame implements ActionListener{
    JPanel buttonPanelN = new JPanel(new FlowLayout());
    JLabel direction = new JLabel("Title: ");
    JTextField title = new JTextField(20);
    JPanel buttonPanelW = new JPanel(new FlowLayout());
    JButton newCard = new JButton("New Card");
    JPanel buttonPanelE = new JPanel();
    JTextField[] subjectCardArr = new JTextField[1000];
    JTextField[] definitionCardArr = new JTextField[1000];
    JButton[] delCardArr = new JButton[1000];
    Component[] padding1 = new Component[1000];
    Component[] padding2 = new Component[1000];
    int cardArrCounter = 0;
    JPanel scrollContaner = new JPanel(new BorderLayout());
    JPanel writerPanelC = new JPanel();
    JScrollPane pane = new JScrollPane(scrollContaner);
    JButton finishedButton = new JButton("finish");
    int listAmount = new File("flashcardDecks").listFiles().length;//use this for file name gen
    public NewDeck() {
        super("New Deck");
        setLayout(new BorderLayout());
        add(buttonPanelN, BorderLayout.NORTH);
        buttonPanelN.add(direction);
        buttonPanelN.add(title);
        writerPanelC.setLayout(new VerticalLayout());
        buttonPanelE.setLayout(new BoxLayout(buttonPanelE, BoxLayout.PAGE_AXIS));
        add(finishedButton, BorderLayout.SOUTH);
        finishedButton.addActionListener(this);
        
        add(buttonPanelW, BorderLayout.WEST);
        buttonPanelW.add(newCard);
        newCard.addActionListener(this);
        scrollContaner.add(writerPanelC, BorderLayout.CENTER);
        scrollContaner.add(buttonPanelE, BorderLayout.EAST);
        add(pane, BorderLayout.CENTER);
    }
    public void writeFile(){
        String filePath = "flashcardDecks\\deck" + listAmount + ".csv";
        File f = new File(filePath);
        StringBuilder subjectSet = new StringBuilder();
        StringBuilder descraptionSet = new StringBuilder();
        for(int i = 0; i < cardArrCounter; i++){
            try{
                subjectSet.append(subjectCardArr[i].getText()).append(",");
                descraptionSet.append(definitionCardArr[i].getText()).append(",");
            }catch(Exception ex){

            }
        }
        try {
            boolean bool = f.createNewFile();
            Path outFile = Paths.get(filePath);
            FileChannel out = (FileChannel)Files.newByteChannel(outFile, CREATE, WRITE);
            String lineSep = System.getProperty("line.separator");
            String s = title.getText() + lineSep + subjectSet.toString()  + lineSep + descraptionSet.toString() + lineSep;
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            out.write(buffer);
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == newCard){
            addNewCard();
        }
        else if(source == finishedButton){
            writeFile();
        }
        else{
            for(int i = 0; i < cardArrCounter; i++){
                if(source == delCardArr[i]){
                    deleteCard(i);
                }
            }
        }
    }
    private void addNewCard() {
        subjectCardArr[cardArrCounter] = new JTextField();
        writerPanelC.add(subjectCardArr[cardArrCounter]);
        
        definitionCardArr[cardArrCounter] = new JTextField();
        writerPanelC.add(definitionCardArr[cardArrCounter]);
        
        padding1[cardArrCounter] = Box.createRigidArea(new Dimension(0, 7));
        buttonPanelE.add(padding1[cardArrCounter]);
        
        delCardArr[cardArrCounter] = new JButton("Delete Card");
        buttonPanelE.add(delCardArr[cardArrCounter]).setBackground(Color.red);
        delCardArr[cardArrCounter].addActionListener(this);
        
        padding2[cardArrCounter] = Box.createRigidArea(new Dimension(0, 7));
        buttonPanelE.add(padding2[cardArrCounter]);
        cardArrCounter+=1;
        rePaint();
    }
    private void deleteCard(int i) {
        writerPanelC.remove(subjectCardArr[i]);
        writerPanelC.remove(definitionCardArr[i]);
        buttonPanelE.remove(delCardArr[i]);
        buttonPanelE.remove(padding1[i]);
        buttonPanelE.remove(padding2[i]);
        subjectCardArr[i] = null;
        definitionCardArr[i] = null;
        delCardArr[i] = null;
        padding1[i] = null;
        padding2[i] = null;
        rePaint();
    }
    public void rePaint(){
        revalidate();
        repaint();
    }
}
