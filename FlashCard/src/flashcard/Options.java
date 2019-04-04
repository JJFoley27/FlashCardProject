/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Owner
 */
public class Options extends JFrame implements ItemListener, ActionListener{
    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String[] sizes = {"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
    String[] colorF = {"black", "red", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "white", "yellow"};
    String[] colorC = {"white", "red", "black", "blue", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "yellow"};
    String fontString = "";
    int fontSizeInt;
    int red;
    int green;
    int blue;
    String fSColor, bSColor;
    Color fColor, bColor;
    public Options() {
        initComponents();
        makeList();
        getApp();
    }
    public void getApp(){
        Path inf = Paths.get("options.txt");
        try {
            InputStream input = Files.newInputStream(inf);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String[] s = reader.readLine().split(",");
            reader.close();
            input.close();
            fontString = s[0];
            String fontSizeString = s[1];
            fontSizeInt = Integer.parseInt(fontSizeString);
            fSColor = s[2];
            bSColor = s[3];
            getColors();
            Font newFont = new Font(fontString, Font.PLAIN, fontSizeInt);
            exampleButton.setForeground(fColor);
            exampleButton.setBackground(bColor);
            exampleButton.setFont(newFont);
            for(int i = 0; i < sizes.length; i++){
                if(fontSizeString.equals(sizes[i])){
                    fontSize.select(i);
                }
            }
            for(int i = 0; i < fonts.length; i++){
                if(fontString.equals(fonts[i])){
                    font.select(i);
                }
            }
            for(int i = 0; i < colorF.length; i++){
                if(fSColor.equals(colorF[i])){
                    fontColor.select(i);
                }
                if(bSColor.equals(colorC[i])){
                    cardColor.select(i);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    public void makeList(){
        
        for (String fontS : fonts) {
            font.add(fontS);
        }
        for (String size : sizes) {
            fontSize.add(size);
        }
        Arrays.sort(colorF);//,1,colorF.length
        Arrays.sort(colorC);//,1,colorC.length
        for (int i = 0; i < colorF.length; i++) {
            fontColor.add(colorF[i]);
            cardColor.add(colorC[i]);
        }
    }
    public void buildAWall(){
        fontString = font.getSelectedItem();
        fSColor = fontColor.getSelectedItem();
        bSColor = cardColor.getSelectedItem();
        getColors();
        fontSizeInt = Integer.parseInt(fontSize.getSelectedItem());
        Font newFont = new Font(fontString, Font.PLAIN, fontSizeInt);
        exampleButton.setForeground(fColor);
        exampleButton.setBackground(bColor);
        exampleButton.setFont(newFont);
    }
    public void getColors(){
        switch(fSColor){
            case "red":
                fColor = Color.RED;
                break;
            case "black":
                fColor = Color.black;
                break;
            case "blue":
                fColor = Color.blue;
                break;
            case "cyan":
                fColor = Color.cyan;
                break;
            case "darkGray":
                fColor = Color.darkGray;
                break;
            case "gray":
                fColor = Color.gray;
                break;
            case "green":
                fColor = Color.green;
                break;
            case "lightGray":
                fColor = Color.lightGray;
                break;
            case "magenta":
                fColor = Color.magenta;
                break;
            case "orange":
                fColor = Color.orange;
                break;
            case "pink":
                fColor = Color.pink;
                break;
            case "white":
                fColor = Color.white;
                break;
            case "yellow":
                fColor = Color.yellow;
                break;
            default:
                break;
        }
        switch(bSColor){
            case "red":
                bColor = Color.RED;
                break;
            case "black":
                bColor = Color.black;
                break;
            case "blue":
                bColor = Color.blue;
                break;
            case "cyan":
                bColor = Color.cyan;
                break;
            case "darkGray":
                bColor = Color.darkGray;
                break;
            case "gray":
                bColor = Color.gray;
                break;
            case "green":
                bColor = Color.green;
                break;
            case "lightGray":
                bColor = Color.lightGray;
                break;
            case "magenta":
                bColor = Color.magenta;
                break;
            case "orange":
                bColor = Color.orange;
                break;
            case "pink":
                bColor = Color.pink;
                break;
            case "white":
                bColor = Color.white;
                break;
            case "yellow":
                bColor = Color.yellow;
                break;
            default:
                break;
        }
    }
    
    
    public void updateApp(){
        String filePath = "options.txt";
        File f = new File(filePath);
        FileWriter fwOb; 
        try {
            fwOb = new FileWriter(filePath, false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder subjectSet = new StringBuilder();
        StringBuilder descraptionSet = new StringBuilder();
        try {
            Path outFile = Paths.get(filePath);
            FileChannel out = (FileChannel)Files.newByteChannel(outFile, CREATE, WRITE);
            String lineSep = ",";
            
            String s = fontString + lineSep + fontSize.getSelectedItem() + lineSep + 
                    fontColor.getSelectedItem() + lineSep + cardColor.getSelectedItem();
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
            out.write(buffer);
            out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        buildAWall();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(applyButton == source){
            updateApp();
        }
        else if(okButton == source || cancelButton == source){
            dispose();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        applyButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        font = new java.awt.List();
        fontSize = new java.awt.Choice();
        fontColor = new java.awt.Choice();
        cardColor = new java.awt.Choice();
        exampleButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Font:");

        jLabel2.setText("Font Size:");

        jLabel3.setText("Font Color:");

        jLabel4.setText("Card Color:");

        applyButton.setText("Apply");
        applyButton.addActionListener(this);

        okButton.setText("Ok");
        okButton.addActionListener(this);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this);

        font.addItemListener(this);

        fontSize.addItemListener(this);

        fontColor.addItemListener(this);

        cardColor.addItemListener(this);

        exampleButton.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(applyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(font, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cardColor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fontColor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(exampleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(fontColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(cardColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(font, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(exampleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(okButton)
                    .addComponent(cancelButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Options().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private java.awt.Choice cardColor;
    private javax.swing.JButton exampleButton;
    private java.awt.List font;
    private java.awt.Choice fontColor;
    private java.awt.Choice fontSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    

    
    
}
