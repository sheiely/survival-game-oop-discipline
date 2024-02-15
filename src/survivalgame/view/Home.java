package survivalgame.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import survivalgame.Construction;
import survivalgame.Historia;
import survivalgame.Savealize;
import survivalgame.model.equipments.Arma;
import survivalgame.model.equipments.Bota;
import survivalgame.model.equipments.Chapeu;
import survivalgame.model.equipments.Roupa;
import survivalgame.model.items.Item;


public class Home extends JFrame{
	

	private static final long serialVersionUID = 5382456176364609467L;
	private Home home;
    public Home(){
        
        super("Survival Game");
        try{
            this.home = this;

            Historia historia = new Historia();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new GridBagLayout());
            this.setSize(700, 700);
            this.getContentPane().setBackground(new Color(70, 87, 92));
            GridBagConstraints c = new GridBagConstraints();
            JLabel btnContinuar = new JLabel();
            JLabel btnNovoJogo = new JLabel();
            ImageIcon imgContinuar = new ImageIcon("src/survivalgame/view/images/btnContinuar.png");
            ImageIcon imgNovoJogo = new ImageIcon("src/survivalgame/view/images/btnNovojogo.png");
            btnContinuar.setIcon(imgContinuar);
            btnNovoJogo.setIcon(imgNovoJogo);
            JPanel wrap = new JPanel();
            wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
            c.fill = GridBagConstraints.BOTH;
            wrap.add(btnContinuar);
            wrap.add(Box.createRigidArea(new Dimension(0,30)));
            wrap.add(btnNovoJogo);
            btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnNovoJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            wrap.setOpaque(false);
            this.add(wrap, c);
            File arq = new File("save.dat");
            if(arq.exists()==false){
                btnContinuar.setIcon(new ImageIcon("src/survivalgame/view/images/btnContinuarDisabled.png"));  
            }else{
                btnContinuar.addMouseListener(new MouseAdapter(){ 
                    public void mouseClicked(MouseEvent  e){
                        btnContinuar.setIcon(new ImageIcon("src/survivalgame/view/images/btnContinuarClicked.png"));   
                        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                            @Override
                            protected Void doInBackground() throws Exception {     
                                ArrayList<Object> resgate = Savealize.lerArquivoBinario("save.dat");
                                historia.getJogador().setInventario((Map<String, ArrayList<Item>>) resgate.get(0));
                                historia.getJogador().setArma((Arma) resgate.get(1));
                                historia.getJogador().setBota((Bota) resgate.get(2));
                                historia.getJogador().setChapeu((Chapeu) resgate.get(3));
                                historia.getJogador().setRoupa((Roupa) resgate.get(4));
                                historia.getJogador().setAtaque((int) resgate.get(5));
                                historia.getJogador().setFome((int) resgate.get(6));
                                historia.getJogador().setSede((int) resgate.get(7));
                                historia.getJogador().setLife((int) resgate.get(8));
                                historia.getJogador().setCriticalChance((int) resgate.get(9));
                                historia.setConstrucoes((Map<String, Construction>) resgate.get(10));
                                for(Construction m:((Map<String, Construction>)resgate.get(10)).values()) {
                                	historia.getCraftables().remove(m.getName());
                                }
                                
                                historia.setDias((int) resgate.get(11));
                                historia.setDeadCreatures((int) resgate.get(12));
                                Main janela = new Main(historia);
                                setVisible(false);
                                return null;
                            };
                        };
                        worker.execute();



                    } 

                    public void mouseEntered(MouseEvent  e){
                        btnContinuar.setIcon(new ImageIcon("src/survivalgame/view/images/btnContinuarOver.png"));
                        home.revalidate();
                    }
                    public void mouseExited(MouseEvent e) {
                        btnContinuar.setIcon(new ImageIcon("src/survivalgame/view/images/btnContinuar.png"));
                     }
                });
            }


            btnNovoJogo.addMouseListener(new MouseAdapter(){ 
                public void mouseClicked(MouseEvent  e){
                    btnNovoJogo.setIcon(new ImageIcon("src/survivalgame/view/images/btnNovoJogoClicked.png"));   
                    SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                        	
                        	
                            Main main = new Main(historia);
                            setVisible(false);
                           
                            JOptionPane.showMessageDialog(main, "O mundo entrou em guerra e batalhas radioativas criaram criaturas deformadas e agressivas\n que praticamente extinguiram a população e elas próprias juntas. Voce se encontra sobrevivendo a beira da morte\n e o seu unico objetivo é matar o resto das criaturas que sobraram, em vingança pelo seu antigo mundo\n explore bem para achar as criaturas, elas agora se escondem", "Boa sorte!",
                    JOptionPane.WARNING_MESSAGE); 
                   
                            return null;
                            };
                    };
                    worker.execute();


                } 
                public void mouseEntered(MouseEvent  e){
                    btnNovoJogo.setIcon(new ImageIcon("src/survivalgame/view/images/btnNovoJogoOver.png"));

                }
                public void mouseExited(MouseEvent e) {
                    btnNovoJogo.setIcon(new ImageIcon("src/survivalgame/view/images/btnNovoJogo.png"));

                 }
            }); 
            this.revalidate();
        }catch(Exception e){
        this.add(new JLabel("erro: "+e.getMessage()));
    }
    }
                

                
                
            
}
