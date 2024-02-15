/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import survivalgame.Construction;
import survivalgame.Historia;
import survivalgame.model.items.Craftable;
import survivalgame.model.items.Item;
import survivalgame.view.Canvas;
import survivalgame.view.Main;

public class ItemGUI extends JPanel{
    private ItemGUI i;
    private JPanel view;
    private BufferedImage imagemFundo;
    private ArrayList<Item> item;
    private void createBasic(){

        this.setOpaque(false);
        
        Border empty = BorderFactory.createEmptyBorder();
        TitledBorder border = BorderFactory.createTitledBorder(empty, Integer.toString(item.size()));
        border.setTitleFont(new Font("Joystix Monospace", Font.TRUETYPE_FONT, 11));
        border.setTitleColor(new Color(238, 200, 4));
        this.setBorder(border);
        
        this.setLayout(new BorderLayout());
        
        JLabel itemImage = new JLabel();
        this.setToolTipText(item.get(0).getName());
        ToolTipManager.sharedInstance().setInitialDelay(0);  
        itemImage.setIcon(new ImageIcon("src/survivalgame/view/images/"+item.get(0).getName()+".png"));
        itemImage.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackground.png"));

        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPanel delimiter = new JPanel();
        delimiter.setPreferredSize(new Dimension(400,270));
        delimiter.setMinimumSize(new Dimension(400,270));
        this.add(delimiter);
        delimiter.setOpaque(false);
        delimiter.setLayout(new BorderLayout());
        this.add(delimiter);
        
        delimiter.add(itemImage, BorderLayout.CENTER);
        this.view = delimiter;
        this.setPreferredSize(new Dimension(70, 70));
    };
    public ItemGUI(ArrayList<Item> item, JFrame parent, Historia historia){
        this.item = item;
        createBasic();
        i = this;
        this.addMouseListener(new MouseAdapter() {
            private int clicks = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 1){
                    ((Main)parent).deselectAllItens();
                    try {
                        imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackgroundSelected.png"));
                        i.repaint();
                    } catch (IOException ex) {
                        Logger.getLogger(ItemGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                if(e.getClickCount() == 2){
              
                    Object[] options = { "Sim", "Nao" };
                    int n = JOptionPane.showOptionDialog(null, "Voce deseja usar o item?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    if(n==0){
                        ((Main)parent).getHistoryGUI().cleanText();
                        try {
                        	((Main)parent).showDialog(historia.useItemFromInventory(item), "Tentou usar");
                        }catch(Exception e1) {
                        	((Main)parent).showDialog(e1.getMessage(), "Tentou usar");
                        }
                        
                        ((Main)parent).loadAtributtes();
                    }
                    
                }
                
            }
        });
    }
    public ItemGUI(Craftable item, JFrame parent, Historia historia){
        ArrayList<Item> ta = new ArrayList<Item>();
        ta.add(item);
        this.item = ta;
        createBasic();
        i = this;
        this.addMouseListener(new MouseAdapter() {
            private int clicks = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                Object[] options = { "Sim", "Nao" };
                String text = "Voce deseja craftar o item?\nRequisitos:";
                for(ArrayList<Item> i: item.getRecursos()){
                    text +="\n"+i.size()+" "+i.get(0).getName();
                }
                
                int n = JOptionPane.showOptionDialog(null, text, null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(n==0){
                	try {
                	((Main)parent).showDialog(historia.craft(item), "Tentou craftar");
                	}catch(Exception e1) {
                		((Main)parent).showDialog(e1.getMessage(), "Tentou craftar");
                	}
                    ((Main)parent).loadCraftables();
                    ((Main)parent).loadAtributtes();
                 }
                 i.setBackground(Color.white);

                
                
            }
        });
    }
    public ItemGUI(Construction item, JFrame parent, Historia historia){
        ArrayList<Item> ta = new ArrayList<Item>();
        ta.add(item);
        this.item = ta;
        createBasic();
        i = this;
        this.addMouseListener(new MouseAdapter() {
            private int clicks = 0;
            @Override
            public void mousePressed(MouseEvent e) {
                Object[] options = { "Sim", "Nao" };
                String text = "voce deseja usar?";
                int n = JOptionPane.showOptionDialog(null, text, null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if(n==0){
                    try {
                        JOptionPane.showMessageDialog(parent, item.usar(historia), "Construcao usada",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(ItemGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ((Main)parent).loadAtributtes();
                 }
                 i.setBackground(Color.white);

                
                
            }
        });
    }
    public void setDeselected(){
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/itemBackground.png"));
            i.repaint();
        } catch (IOException ex) {
            Logger.getLogger(ItemGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, null);
    }
}
