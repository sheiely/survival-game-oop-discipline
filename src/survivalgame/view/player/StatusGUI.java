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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import survivalgame.Historia;
import survivalgame.model.entities.Jogador;
import survivalgame.view.Canvas;
import survivalgame.view.Main;
import survivalgame.view.history.modifiquedcomponents.JLabelFontPixel;

/**
 *
 * @author sheie
 */
public class StatusGUI extends JPanel{
    private JPanel paneChapeu;
    private JPanel paneRoupa;
    private JPanel paneBota;
    private JPanel paneArma;
    private JPanel equipWrap;
    private Jogador jogador;
    private BufferedImage imagemFundo;
    private Historia historia;
    private Main parent;
    private JPanel view;

    public StatusGUI(Historia historia, Main parent){
        this.historia = historia;
        this.parent = parent;
        setPreferredSize(new Dimension(200,270));
        setMinimumSize(new Dimension(200,270));
        this.jogador = historia.getJogador();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       
        paneChapeu = new ItemEquiped(jogador.getChapeu(), jogador, parent);
        paneRoupa = new ItemEquiped(jogador.getRoupa(), jogador, parent);
        paneBota = new ItemEquiped(jogador.getBota(), jogador, parent);
        paneArma = new ItemEquiped(jogador.getArma(), jogador, parent);
        equipWrap = new JPanel();
        equipWrap.setOpaque(false);
        equipWrap.setMaximumSize(new Dimension(160, 150));
        equipWrap.setAlignmentX(0);
        paneChapeu.setPreferredSize(new Dimension(40, 40));
        paneRoupa.setPreferredSize(new Dimension(40, 40));
        paneBota.setPreferredSize(new Dimension(40, 40));
        paneArma.setPreferredSize(new Dimension(40, 40));
       
        try {
            this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/statusBackGround.png"));
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        JPanel delimiter = new JPanel();
        delimiter.setPreferredSize(new Dimension(200,270));
        delimiter.setMinimumSize(new Dimension(200,270));
        this.add(delimiter);
        delimiter.setOpaque(false);
        delimiter.setLayout(new BoxLayout(delimiter, BoxLayout.Y_AXIS));
        delimiter.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.view = delimiter;
        this.setOpaque(false);
        remake();
        
    }
    public void remake(){
    	
        this.view.removeAll();
        JLabel vida = new JLabelFontPixel("vida: " + Integer.toString(jogador.getLife())+"/"+Integer.toString(jogador.getMaxLife()));
        vida.setAlignmentX(0);
        this.view.add(vida);
        JLabel ataque = new JLabelFontPixel("ataque: " + Integer.toString(jogador.getAtaque()));
        ataque.setAlignmentX(0);
        this.view.add(ataque);
        JLabel fome =new JLabelFontPixel("fome: " + Integer.toString(jogador.getFome())+"/"+Integer.toString(jogador.getMaxFome()));
        if(jogador.getFome()<=2){
            fome.setForeground(Color.red);
        }
        fome.setAlignmentX(0);
        this.view.add(fome);
        JLabel sede = new JLabelFontPixel("sede: " + Integer.toString(jogador.getSede())+"/"+Integer.toString(jogador.getMaxSede()));
        sede.setAlignmentX(0);
        if(jogador.getSede()<=1){
            sede.setForeground(Color.red);
        }
        this.view.add(sede);
        JLabel dia = new JLabelFontPixel("dias: " + Integer.toString(historia.getDias()));
        dia.setAlignmentX(0);
        this.view.add(dia);   
        JLabel deadCreatures = new JLabelFontPixel("criaturas: " + Integer.toString(historia.getDeadCreatures())+"/5");
        deadCreatures.setAlignmentX(0);
        this.view.add(deadCreatures);  
       
        paneChapeu = new ItemEquiped(jogador.getChapeu(), jogador, parent);
        paneRoupa = new ItemEquiped(jogador.getRoupa(), jogador, parent);
        paneBota = new ItemEquiped(jogador.getBota(), jogador, parent); 
        paneArma = new ItemEquiped(jogador.getArma(), jogador, parent);
        
        equipWrap.removeAll();
        equipWrap.add(paneChapeu);
        equipWrap.add(paneRoupa);
        equipWrap.add(paneBota);
        equipWrap.add(paneArma);
        this.view.add(equipWrap);
        this.revalidate();
        this.repaint();
       
    }
   
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, null);
    }
   
}
