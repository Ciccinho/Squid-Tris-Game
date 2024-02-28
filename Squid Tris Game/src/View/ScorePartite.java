package View;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Control.Tris;
import Model.Giocatore;

public class ScorePartite extends JComponent {

	private static final long serialVersionUID = 1L;
	public static String titolo;
	public static String scorePartite = " ";
	static JTextArea pScore;
	static JTextArea textTitolo;
	public JButton back;
	public ImageIcon sfodo; 

	public ScorePartite() {

		titolo = "SCORE PARTITE";
		textTitolo = new JTextArea(titolo);
		textTitolo.setEditable(false);
		JPanel pSopra = new JPanel();
		pSopra.add(textTitolo);

		pScore = new JTextArea(scorePartite);
		pScore.setEditable(false);
		pScore.setSize(500, 400);
		JScrollPane sp = new JScrollPane(pScore);

		JPanel pSotto = new JPanel();
		setLayout(new BorderLayout());
		back = new JButton("Back");	
		back.addActionListener(new ritorno());
		pSotto.add(back);		

		add(pSopra, BorderLayout.PAGE_START);
		add(sp, BorderLayout.CENTER);
		add(pSotto, BorderLayout.PAGE_END);
		setSize(800, 600);
		setVisible(true);

		repaint();

		Tris.mFrame.pack();

	}

	public void paintComponent(Graphics g) {

		super.paintComponents(g);

		sfodo = new ImageIcon("resources"+ File.separator+"X.png");
		Image imgSf = sfodo.getImage();
		Image nSf = imgSf.getScaledInstance(1080, 550, Image.SCALE_SMOOTH);
		sfodo = new ImageIcon(nSf);
	}

	public static void addRecord(Giocatore g) {
		scorePartite = g.toString()+"\n"+ scorePartite;
	}

	private class ritorno implements ActionListener{
		public void actionPerformed(ActionEvent e){  

			setVisible(false);
			Tris.mFrame.remove(Tris.pScore);
			Tris.menu= new MainMenu();
			Tris.mFrame.getContentPane().add(Tris.menu);
			Tris.menu.getParent().revalidate();
		}
	}  
}
