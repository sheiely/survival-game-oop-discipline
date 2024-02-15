package survivalgame.view.history.modifiquedcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JbuttonHistory extends JButton{
	private BufferedImage imagemFundo;
	public JbuttonHistory(String text){
		JLabel label = new JLabel(text);
		label.setFont(new Font("ArcadeClassic", Font.TRUETYPE_FONT, 15));
		label.setForeground(Color.white);
		this.setLayout(new BorderLayout());
		label.setVerticalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		this.add(label, BorderLayout.CENTER);
		this.setOpaque(false);
		setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
		try {
			this.imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/backGroundBtn.png"));
		} catch (IOException e) {
			System.out.println(e);
		}
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent  e){
				try {
					imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/backGroundBtnClicked.png"));
				} catch (IOException e1) {
					System.out.println(e1);
				}
			}
			public void mouseEntered(MouseEvent  e){
				try {
					imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/backGroundBtnOver.png"));
				} catch (IOException e1) {
					System.out.println(e1);
				}
            }
            public void mouseExited(MouseEvent e) {
            	try {
					imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/backGroundBtn.png"));
				} catch (IOException e1) {
					System.out.println(e1);
				}
             }
            public void mouseReleased(MouseEvent e) {
            	try {
					imagemFundo = ImageIO.read(new File("src/survivalgame/view/images/backGroundBtn.png"));
				} catch (IOException e1) {
					System.out.println(e1);
				}
             }
		});
	}

	@Override
	protected void paintComponent(Graphics g){
	    super.paintComponent(g);
	  
	    g.drawImage(imagemFundo, 0, 0, null);
	   
	}
}