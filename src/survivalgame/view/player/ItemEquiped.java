/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import survivalgame.Construction;
import survivalgame.model.entities.Jogador;
import survivalgame.Historia;
import survivalgame.model.equipments.Arma;
import survivalgame.model.equipments.Bota;
import survivalgame.model.equipments.Chapeu;
import survivalgame.model.equipments.Roupa;
import survivalgame.model.items.Craftable;
import survivalgame.model.items.Item;
import survivalgame.model.items.ItemEquipavel;
import survivalgame.view.Canvas;
import survivalgame.view.Main;

/**
 *
 * @author sheie
 */
public class ItemEquiped extends JPanel{
    private ItemEquiped i;
    private JPanel view;
    private BufferedImage imagemFundo;
    private ItemEquipavel item;
    private Jogador jogador;
    private Main parent;
    private void createBasic(){
    	
        this.setOpaque(false);
        
        Border empty = BorderFactory.createEmptyBorder();
        TitledBorder border = BorderFactory.createTitledBorder(empty, null);
        border.setTitleColor(Color.WHITE);
        this.setBorder(border);
        
        this.setLayout(new BorderLayout());
        
        JLabel itemImage = new JLabel();
        
        ToolTipManager.sharedInstance().setInitialDelay(0);  
        if(item!=null){
            this.setToolTipText(item.getName());
            itemImage.setIcon(new ImageIcon("src/survivalgame/view/images/"+item.getName()+".png"));
            itemImage.setHorizontalAlignment(SwingConstants.CENTER);
        }
        
        
        
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackground.png"));

        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPanel delimiter = new JPanel();
        delimiter.setPreferredSize(new Dimension(70,70));
        delimiter.setMinimumSize(new Dimension(70,70));
        this.add(delimiter);
        delimiter.setOpaque(false);
        delimiter.setLayout(new BorderLayout());
        this.add(delimiter);
        
        delimiter.add(itemImage, BorderLayout.CENTER);
        this.view = delimiter;
        this.setPreferredSize(new Dimension(70, 70));
    };
    public ItemEquiped(ItemEquipavel item, Jogador jogador, Main parent){
        this.item = item;
        createBasic();
        i = this;
        this.jogador = jogador;
        this.parent = parent;
        if(item!=null){
            this.addMouseListener(new MouseAdapter() {
                private int clicks = 0;
                @Override
                public void mousePressed(MouseEvent e) {
                    if(e.getClickCount() == 1){
                        try {
                            imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackgroundSelected.png"));
                            i.repaint();
                        } catch (IOException ex) {
                            Logger.getLogger(ItemEquiped.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if(e.getClickCount() == 2){

                        Object[] options = { "Sim", "Nao" };
                        int n = JOptionPane.showOptionDialog(null, "Voce deseja desequipar o item?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                        if(n==0){
                            if(item instanceof Chapeu){jogador.addItem(item);
                                jogador.setChapeu(null);}
                            else if(item instanceof Roupa){jogador.addItem(item);
                                jogador.setRoupa(null);System.out.println("era uma roupa");}
                            else if(item instanceof Bota){jogador.addItem(item);
                                jogador.setBota(null);System.out.println("era uma bota");}
                            else if(item instanceof Arma){jogador.addItem(item);
                                jogador.setArma(null);System.out.println("era uma arma");}
                            parent.loadAtributtes();


                        }

                    }

                }
            });
        }
        
       
    }
    
    public void setDeselected(){
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackground.png"));
            i.repaint();
        } catch (IOException ex) {
            Logger.getLogger(ItemEquiped.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, null);
    }
	
}
