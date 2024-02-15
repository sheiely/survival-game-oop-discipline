/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.history.modifiquedcomponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author sheie
 */
public class JLabelFontPixel extends JLabel{
    public JLabelFontPixel(String text){
        super(text);
        this.setFont(new Font("Perfect DOS VGA 437 Win", Font.TRUETYPE_FONT, 15));
        this.setForeground(Color.BLACK);
    }
    public JLabelFontPixel(){
        this.setFont(new Font("Perfect DOS VGA 437 Win", Font.TRUETYPE_FONT, 15));
        this.setForeground(Color.BLACK);
    }
}
