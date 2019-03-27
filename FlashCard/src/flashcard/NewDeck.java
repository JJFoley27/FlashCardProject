/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Owner
 */
public class NewDeck extends JFrame implements ActionListener{
    JPanel buttonPanelW = new JPanel(new FlowLayout());
    JButton newCard = new JButton("New Card");
    JPanel buttonPanelE = new JPanel();
    JTextField[] subjectCardArr = new JTextField[1000];
    JTextField[] definitionCardArr = new JTextField[1000];
    JButton[] delCardArr = new JButton[1000];
    Component[] padding1 = new Component[1000];
    Component[] padding2 = new Component[1000];
    int cardArrCounter = 0;
    JPanel writerPanelC = new JPanel(new FlowLayout());
    public NewDeck() {
        super("New Deck");
        setLayout(new BorderLayout());
        buttonPanelE.setLayout(new BoxLayout(buttonPanelE, BoxLayout.PAGE_AXIS));
        add(buttonPanelE, BorderLayout.EAST);
        add(buttonPanelW, BorderLayout.WEST);
        buttonPanelW.add(newCard);
        newCard.addActionListener(this);
        add(writerPanelC, BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == newCard){
            addNewCard();
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
        subjectCardArr[cardArrCounter].setColumns(30);
        writerPanelC.add(subjectCardArr[cardArrCounter]);
        
        definitionCardArr[cardArrCounter] = new JTextField();
        definitionCardArr[cardArrCounter].setColumns(30);
        writerPanelC.add(definitionCardArr[cardArrCounter]);
        
        padding1[cardArrCounter] = Box.createRigidArea(new Dimension(0, 12));
        buttonPanelE.add(padding1[cardArrCounter]);
        
        delCardArr[cardArrCounter] = new JButton("Delete Card");
        buttonPanelE.add(delCardArr[cardArrCounter]).setBackground(Color.red);
        delCardArr[cardArrCounter].addActionListener(this);
        
        padding2[cardArrCounter] = Box.createRigidArea(new Dimension(0, 12));
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
