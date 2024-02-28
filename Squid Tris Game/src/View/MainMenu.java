package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Control.PannelloGioco;
import Control.Tris;

public class MainMenu extends JComponent {

	private static final long serialVersionUID = 1L;
	public JButton start, score, help;

	public MainMenu() {

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());

		GridBagConstraints box = new GridBagConstraints();
		box.gridwidth = GridBagConstraints.REMAINDER;
		//  box.anchor = GridBagConstraints.NORTH;
		
		add(new JLabel("<html><h1 style='color: Fuchsia';><strong><i>TRIS GAME</i></strong></h1><hr></html>"), box);

		box.anchor = GridBagConstraints.CENTER;
		box.fill = GridBagConstraints.HORIZONTAL;
		

		start = new JButton("Start");
		score = new JButton("Score");
		help = new JButton("Help");
//		start.setBackground(Color.WHITE);
		start.addActionListener(new startButton());
		score.addActionListener(new scoreButton());
		help.addActionListener(new helpButton());
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.BLACK);
		panel.add(start);
		panel.add(score);
		panel.add(help);

		add(panel);

		box.weighty = 1;

		repaint();
	}

	//GRAFICA 
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageIcon backGround = new ImageIcon("resources"+ File.separator+"IMG_sf.JPG");
		Image img = backGround.getImage();
		Image n = img.getScaledInstance(1080, 720, Image.SCALE_SMOOTH);
		backGround = new ImageIcon(n);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(backGround.getImage(),0,0,this);
		setOpaque(false);
		setVisible(true);

	}

	//SWITCH DAL MENU AL TAVOLOGIOCO PER INIZIARE LA PARTITA

	private class startButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Tris.mFrame.remove(Tris.menu);
			Tris.pGioco = new PannelloGioco();
			Tris.mFrame.getContentPane().add(Tris.pGioco);
			Tris.pGioco.getParent().revalidate();
		}

	}
	//SWITCH DAL MENU AL PANNELLO SCORE

	private class scoreButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Tris.mFrame.remove(Tris.menu);
			Tris.pScore = new ScorePartite();
			Tris.mFrame.getContentPane().add(Tris.pScore);
			Tris.pScore.getParent().revalidate();
		}

	}

	//SWITCH DAL MENU AL PANNELLO SCORE

	private class helpButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Tris.mFrame.remove(Tris.menu);
			Tris.pHelp = new HelpGame();
			Tris.mFrame.getContentPane().add(Tris.pHelp);
			Tris.pHelp.getParent().revalidate();
		}

	}
}
