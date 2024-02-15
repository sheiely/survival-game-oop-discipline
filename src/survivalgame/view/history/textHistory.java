/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.history;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import survivalgame.view.history.modifiquedcomponents.JLabelFontPixel;

/**
 *
 * @author sheie
 */
public class textHistory extends JPanel{
    private JLabelFontPixel text;
    public textHistory(String text){
        this.setMaximumSize(new Dimension(500,50));
        this.setMinimumSize(new Dimension(100,50));
        this.setAlignmentX(0);
        this.text = new JLabelFontPixel();
        this.text.setText(text);
        this.add(this.text);
        this.setBackground(new Color(222, 205, 144));
     
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    
    }
}
