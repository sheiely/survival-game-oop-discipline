
package survivalgame.view;

import survivalgame.view.history.HistoryGUI;
import survivalgame.view.history.modifiquedcomponents.JbuttonHistory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import survivalgame.Historia;
import survivalgame.model.items.Item;
import survivalgame.view.player.InventarioGUI;
import survivalgame.view.player.ItemGUI;
import survivalgame.view.player.StatusGUI;









public class Main extends JFrame{
        private JPanel jogador;
        private StatusGUI status;
        private HistoryGUI historyGUI;
        private InventarioGUI inventario;
        private Historia historia;
        public void deselectAllItens(){
            for(int i =0; i < inventario.getView().getComponentCount(); i++){
                ((ItemGUI)inventario.getView().getComponent(i)).setDeselected();
            }
          
        }
        public HistoryGUI getHistoryGUI(){
            return this.historyGUI;
        }
        public void loadAtributtes(){
            
            status.remake();
            inventario.getView().removeAll();
          
            for(ArrayList<Item> i: historia.getJogador().getInventario().values()){
                inventario.getView().add(new ItemGUI(i, this, historia));
            }
            
            this.revalidate();
            this.repaint();
        }
        public void loadCraftables(){
            historyGUI.loadCraftablesGUI(historia.getCraftables().values(), historia);
        }
        public Main(Historia historia) throws IOException{
            super("Survival Game");
            
            this.historia = historia;
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new GridBagLayout());
            this.setSize(700, 700);
            this.setVisible(true);
            
            jogador = new JPanel();
            
            status = new StatusGUI(historia, this);
            
            inventario = new InventarioGUI();
           
            JPanel actionSection = new JPanel();
            JButton btnExplorar = new JbuttonHistory("Explorar");
            JButton btnConstruir = new JbuttonHistory("Construir");
            JButton btnAcampamento = new JbuttonHistory("Acampamento");
            historyGUI = new HistoryGUI(this, historia);
            JPanel historyButtons = new JPanel();
            
            
            jogador.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            actionSection.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            historyButtons.setLayout(new FlowLayout());
            GridBagConstraints gbcM = new GridBagConstraints();
    
            
            
            c.insets = new Insets(5,10,5,10);
          
            
            jogador.setBackground(new Color(70, 87, 92));
            actionSection.setBackground(new Color(70, 87, 92));
            historyButtons.setOpaque(false);
              
            btnExplorar.setPreferredSize(new Dimension(150, 30));
            btnConstruir.setPreferredSize(new Dimension(150, 30));
            btnAcampamento.setPreferredSize(new Dimension(150, 30));
    
            
            
            jogador.setPreferredSize(new Dimension(500,350));
            
                
            actionSection.setPreferredSize(new Dimension(500,350));
  
                
            gbcM.fill = GridBagConstraints.BOTH;
            gbcM.weightx = 1;
            gbcM.weighty = 1;
            gbcM.gridx = 0;  
            gbcM.gridy = 0;
            this.add(jogador, gbcM);
                jogador.add(status,c);
                jogador.add(inventario, c);
            gbcM.fill = GridBagConstraints.BOTH;
            gbcM.weightx = 1;
            gbcM.weighty = 1;
            gbcM.gridx = 0;  
            gbcM.gridy = 1;        
                    
            this.add(actionSection, gbcM);
                gbc.fill = GridBagConstraints.HORIZONTAL;   
                gbc.insets = new Insets(5, 50, 5, 50);
                gbc.gridwidth = 1; 
                gbc.weightx = 1;
                gbc.gridx = 0;  
                gbc.gridy = 0;  
                actionSection.add(historyButtons, gbc);
                    
                   
                    historyButtons.add(btnExplorar);
                    historyButtons.add(btnConstruir);
                    historyButtons.add(btnAcampamento);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipady = 250;
                gbc.gridwidth = 1;
                gbc.weightx = 1;
                gbc.gridx = 0;  
                gbc.gridy = 1; 
                actionSection.add(historyGUI, gbc);
                

                
                
                
            btnExplorar.addActionListener(new ActionListener(){ 
                @Override
                public void actionPerformed(ActionEvent e){
                    SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                        @Override
                        protected Void doInBackground() throws Exception {

                            btnExplorar.setEnabled(false);
                            btnConstruir.setEnabled(false);
                            btnAcampamento.setEnabled(false);
                            try{
                            	historyGUI.cleanText();
                                historyGUI.loadExploration(historia.explore());
                                loadAtributtes();
                                if(historia.getDeadCreatures()>5) {
                                	showDialog("Voce matou o resto de criaturas que sobraram, e agora pode finalmente descansar", "Voce ganhou");
                                }
                            }catch(Exception error){
                                System.out.println(error);
                            }finally{
                                btnExplorar.setEnabled(true);
                            btnConstruir.setEnabled(true);
                            btnAcampamento.setEnabled(true);
                            }       
                            return null;
                        }          
                    };
                    worker.execute();
                } 
            });
            btnConstruir.addActionListener(new ActionListener(){ 
                @Override
                public void actionPerformed(ActionEvent e){
                    SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                           
                            btnExplorar.setEnabled(false);
                            btnConstruir.setEnabled(false);
                            btnAcampamento.setEnabled(false);
                            try{
                                loadCraftables();
                            }catch(Exception error){
                                System.out.println(error);
                            }finally{
                                btnExplorar.setEnabled(true);
                            btnConstruir.setEnabled(true);
                            btnAcampamento.setEnabled(true);
                            }       
                            return null;
                        }          
                    };
                    worker.execute();
                } 
            });
            btnAcampamento.addActionListener(new ActionListener(){ 
                @Override
                public void actionPerformed(ActionEvent e){
                    SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                          
                            btnExplorar.setEnabled(false);
                            btnConstruir.setEnabled(false);
                            btnAcampamento.setEnabled(false);
                            try{
                                historyGUI.loadCampingGUI(historia.getConstrucoes().values(), historia);
                            }catch(Exception error){
                                System.out.println(error);
                            }finally{
                                btnExplorar.setEnabled(true);
                            btnConstruir.setEnabled(true);
                            btnAcampamento.setEnabled(true);
                            }       
                            return null;
                        }          
                    };
                    worker.execute();
                } 
            });
            
            this.loadAtributtes();
       
            
            
        }
        public int waitKey() throws InterruptedException{
            final CountDownLatch latch = new CountDownLatch(1);
            KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
                // Anonymous class invoked from EDT
                public boolean dispatchKeyEvent(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        latch.countDown();
                    return false;
                }
            };
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
            latch.await();  // current thread waits here until countDown() is called
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
            return 0;
        }
        public void showDialog(String text, String tittle) {
        	JOptionPane.showMessageDialog(this, text, tittle,JOptionPane.WARNING_MESSAGE);
        }
    
}
