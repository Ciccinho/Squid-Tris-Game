package Control;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import View.HelpGame;
import View.MainMenu;
import View.ScorePartite;


public class Tris {

	public static JFrame mFrame;
	public static MainMenu menu;
	public static PannelloGioco pGioco;
	public static ScorePartite pScore;
	public static HelpGame pHelp;

	public Tris() {

		mFrame= new JFrame("TRIS");
		mFrame.setPreferredSize(new Dimension(1080,720));
		menu = new MainMenu();
		JPanel p = (JPanel) mFrame.getContentPane();
		p.add(menu);
		mFrame.pack();
		mFrame.setLocationRelativeTo(null);
		mFrame.setVisible(true);
		mFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	public static void main(String arg[]) {			
		new Tris();	
	}

}
