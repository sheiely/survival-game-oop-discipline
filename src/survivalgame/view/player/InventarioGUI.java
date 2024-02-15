/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import survivalgame.view.Canvas;

/**
 *
 * @author sheie
 */
public class InventarioGUI extends JPanel{
    private BufferedImage imagemFundo;
    private JPanel view;
    public InventarioGUI() throws IOException{
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(17, 17, 17, 17));
        setPreferredSize(new Dimension(400,270));
        setMinimumSize(new Dimension(400,270));
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/InventoryBackGround.png"));
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPanel delimiter = new JPanel();
        delimiter.setPreferredSize(new Dimension(400,270));
        delimiter.setMinimumSize(new Dimension(400,270));
        this.add(delimiter);
        delimiter.setOpaque(false);
        delimiter.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 3));
        this.view = delimiter;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, null);
    }
    public JPanel getView(){
        return this.view;
    }
    

   
}
