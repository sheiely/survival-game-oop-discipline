/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.view.history;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import survivalgame.Construction;
import survivalgame.Historia;
import survivalgame.exceptions.MsgException;
import survivalgame.model.entities.Inimigo;
import survivalgame.model.items.Craftable;
import survivalgame.model.items.Item;
import survivalgame.view.Main;
import survivalgame.view.player.ItemGUI;

/**
 *
 * @author sheie
 */
public class HistoryGUI extends JScrollPane{
    private JPanel pane;
    private JPanel wrapCraft;
    private JFrame parent;
    private Historia historia;
    public HistoryGUI(JFrame parent, Historia historia){
        this.parent = parent;
        pane = new JPanel();
        this.historia = historia;
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        this.getViewport().add(pane, null);
        this.setPreferredSize(new Dimension(600, 400));
        wrapCraft = new JPanel();
        wrapCraft.setLayout(new FlowLayout(FlowLayout.LEFT));
        wrapCraft.setPreferredSize(new Dimension(400, 200));
        wrapCraft.setMaximumSize(new Dimension(600, 200));
        pane.setBackground(new Color(133,133,154));
        wrapCraft.setVisible(true);
        wrapCraft.setOpaque(false);
        
        
    }
    public void addText(String text){
        textHistory txtH = new textHistory(text);
        pane.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel msgWrap = new JPanel();
        msgWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        msgWrap.setMaximumSize(new Dimension(600, 50));
        msgWrap.add(Box.createRigidArea(new Dimension(20,0)));
        msgWrap.add(txtH);
        msgWrap.setOpaque(false);
        pane.add(msgWrap, null);
        
        int maxValue  = this.getVerticalScrollBar().getMaximum();
        this.getViewport().setViewPosition(new Point(0,maxValue));
        this.getViewport().validate();
    }
    public void loadCraftablesGUI(Collection<Craftable> craftaveis, Historia historia){
        cleanText();
        wrapCraft.removeAll();
        pane.add(wrapCraft, null);
        
        for(Craftable c: craftaveis){
            wrapCraft.add(new ItemGUI(c, parent, historia));
        }
        int maxValue  = this.getVerticalScrollBar().getMaximum();
        this.getViewport().setViewPosition(new Point(0,maxValue));
        this.getViewport().validate();
    }
    public void loadCampingGUI(Collection<Construction> construcoes, Historia historia){
        cleanText();
        wrapCraft.removeAll();
        pane.add(wrapCraft, null);
        for(Construction c: construcoes){
            wrapCraft.add(Box.createRigidArea(new Dimension(10,0)));
            wrapCraft.add(new ItemGUI(c, parent, historia));
        }
        int maxValue  = this.getVerticalScrollBar().getMaximum();
        this.getViewport().setViewPosition(new Point(0,maxValue));
        this.getViewport().validate();
    }
    
    
    public inimigoHistory addInimigo(Inimigo inimigo){
        inimigoHistory inimigoH = new inimigoHistory(inimigo.getName(), inimigo.getLife(), inimigo.getAtaque());
        pane.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel msgWrap = new JPanel();
        msgWrap.setOpaque(false);
        msgWrap.setLayout(new FlowLayout(FlowLayout.LEFT));
        msgWrap.setMaximumSize(new Dimension(600, 150));
        msgWrap.add(Box.createRigidArea(new Dimension(20,0)));
        msgWrap.add(inimigoH);
        pane.add(msgWrap, null);
        int maxValue  = this.getVerticalScrollBar().getMaximum();
        this.getViewport().setViewPosition(new Point(0,maxValue));
        this.getViewport().validate();
        
        return inimigoH;
    }
    public void cleanText(){
        pane.removeAll();
    
    }
    public void loadExploration(ArrayList<String> events) throws InterruptedException {
    	try {
	    	for(String s: events) {
		    		if(!historia.getJogador().isAlive()) {
		    			throw new MsgException("Voce morreu");
		    		}
		    		switch(s){
		    			case "gerateFightCode":
		    				gerarBatalha();
		    				break;
		    			default: 
		    				addText(s);
		    				break;
		    		}
		    		Thread.sleep(500);
	    		
	    	}
	    	
    	}catch(Exception e) {
			((Main)parent).showDialog(e.getMessage(), "Ops!");
		}finally {
			historia.passarDias();
		}
    	
    }
    private void gerarBatalha() throws CloneNotSupportedException, InterruptedException{
    	Random rand = new Random();
        Inimigo inimigo = historia.generateEnemy();
        inimigoHistory inimigoGUI = addInimigo(inimigo);
        while(inimigo.isAlive() && historia.getJogador().isAlive()){
        	
        	((Main)parent).loadAtributtes();
            inimigoGUI.refresh(inimigo.getLife());
            switch(inimigoGUI.waitAction()){
                case 1:
                    historia.lutar(inimigo);
                    break;
                case 2:
                    if(rand.nextInt(100) <= 60){
                    	((Main)parent).showDialog("Fugiu!", "Voce tentou fugir");
                        return;
                    }else{
                    	((Main)parent).showDialog("Voce nao consegui fugir!", "Voce tentou fugir");
                        historia.getJogador().setLife(historia.getJogador().getLife() - historia.gerarDano(inimigo));
                    }
                    break;
            }
            if(!historia.getJogador().isAlive()){
        		throw new MsgException("Voce morreu");
        	}
            if(!inimigo.isAlive()){
                Item drop = inimigo.gerarDrop();
                inimigoGUI.refresh(inimigo.getLife());
                if(inimigo.getName().equals("criatura")){
                    historia.setDeadCreatures(historia.getDeadCreatures()+1);
                }
                if(drop!=null){
                    historia.getJogador().addItem(drop);
                    ((Main)parent).showDialog("Voce conseguiu "+drop.getName(), "Voce Matou o inimigo");
                    return;
                }else{
                	((Main)parent).showDialog("O inimigo nao dropou nada!", "Voce Matou o inimigo");
                    return;
                }
            }     
        }
        
    }

}
