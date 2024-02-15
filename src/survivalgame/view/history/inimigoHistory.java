/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.history;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import survivalgame.view.history.modifiquedcomponents.JButtonEnemy;
import survivalgame.view.history.modifiquedcomponents.JLabelFontPixel;

/**
 *
 * @author sheie
 */
public class inimigoHistory extends JPanel{
    private JLabelFontPixel nome;
    private JLabelFontPixel vida;
    private JLabelFontPixel ataque;
    private JButton btnAtacar;
    private JButton btnFugir;
    private int action;
    public inimigoHistory(String nome, int vida, int ataque){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.nome = new JLabelFontPixel();
       
        this.vida = new JLabelFontPixel();
     
        this.ataque = new JLabelFontPixel();
    
        this.btnAtacar = new JButtonEnemy("Atacar");
        this.btnFugir = new JButtonEnemy("Fugir");
        btnAtacar.setPreferredSize(new Dimension(80, 30));
        btnFugir.setPreferredSize(new Dimension(80, 30));
        this.nome.setText("Inimigo encontrado: "+ nome);
        this.vida.setText("Vida: "+Integer.toString(vida));
        this.ataque.setText("Ataque: "+Integer.toString(ataque));
        this.nome.setAlignmentX(0);
        this.vida.setAlignmentX(0);
        this.ataque.setAlignmentX(0);
        this.add(this.nome);
        this.add(this.vida);
        this.add(this.ataque);
        JPanel jpButtons = new JPanel();
        jpButtons.setOpaque(false);
        jpButtons.setAlignmentX(0);
        this.add(jpButtons);
        jpButtons.add(btnAtacar);
        jpButtons.add(btnFugir);
        this.setBackground(new Color(222, 205, 144));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setMaximumSize(new Dimension(500,150));
        this.setMinimumSize(new Dimension(300,150));
    }
    public void refresh(int vida){
        this.vida.setText("Vida: "+Integer.toString(vida));
        this.revalidate();
    }
     public int waitAction() throws InterruptedException{
            action = 0;
            final CountDownLatch latch = new CountDownLatch(1);
            btnAtacar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){   
                    action = 1;
                    latch.countDown();
                }
            });
            btnFugir.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){    
                    action = 2;
                    latch.countDown();
                }
            });
            latch.await();  // current thread waits here until countDown() is called
           
            return action;
        }
}
