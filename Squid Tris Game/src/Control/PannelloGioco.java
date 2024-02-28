package Control;

///////////////////////////////////////////////////////   IMPORT GENERALI   /////////////////////////////////////////////////////////////
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import Model.Block;
import Model.Giocatore;
import View.MainMenu;
import View.ScorePartite;

///////////////////////////////////////////////////////   PANNELLO GIOCO   /////////////////////////////////////////////////////////////////

public class PannelloGioco extends JComponent{

	private static final long serialVersionUID = 1L;

	public static final int rig = 3;
	public static final int col = 3;
	public int jndex=0;
	public int set, game, turno;
	public Giocatore gx, go;
	public TavoloGioco table;
	public ScoreGioco score;
	public ImageIcon optionIm;
	public JSplitPane split;
	private JButton backButton;

	public int getSet() {return this.set;}
	public void setSet(int set) {this.set = set;}
	public int getGame() {return this.game;}
	public void setGame(int game) {this.game = game;}
	public int getTurno() {return this.turno;}
	public void setTurno(int turno) {this.turno = turno;}


	public PannelloGioco() {

		inizzializza();
		repaint();
	}

	private void inizzializza() {

		gx = new Giocatore();
		go = new Giocatore();

		gx.setSet(0);
		gx.setSimbol(1);
		go.setSet(0);
		go.setSimbol(2);

		optionIm = new ImageIcon("resources"+ File.separator+"PHOTOmin.jpeg");
		Image imgOp = optionIm.getImage();
		Image nOp = imgOp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		optionIm = new ImageIcon(nOp);

		setLayout(new BorderLayout());

		String range []= {"2/3", "3/5", "4/7" };
		JComboBox<String> comboB = new JComboBox<String>(range);
		JOptionPane.showMessageDialog(this, comboB, "Scegli il Range Set/Game",  JOptionPane.OK_OPTION, optionIm);

		switch(comboB.getSelectedIndex()) {
		case 0:
			setSet(2);
			setGame(3);
			gx.setGame(3);
			go.setGame(3);
			break;
		case 1:
			setSet(3);
			setGame(5);
			gx.setGame(5);
			go.setGame(5);
			break;
		case 2:
			setSet(4);
			setGame(7);
			gx.setGame(7);
			go.setGame(7);
			break;
		default:
			setSet(1);
			setGame(2);
			gx.setGame(1);
			go.setGame(1);
			break;
		}

		repaint();

		table  = new TavoloGioco(set, game, turno, gx, go);
		score = new ScoreGioco(set, game, turno, gx, go);
		backButton = new JButton("Back to Menu");
		backButton.setBounds(430, 115, 220, 30);
		backButton.addActionListener(new BackButton());
		score.add(backButton);

		setLayout(new BorderLayout());
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(150);
		split.setDividerSize(0);     
		split.setTopComponent(score);
		split.setBottomComponent(table);

		add(split, BorderLayout.CENTER);

		Tris.mFrame.pack();
	}


	private class BackButton implements ActionListener{
		public void actionPerformed(ActionEvent e){  
			setVisible(false);
			Tris.mFrame.remove(Tris.pGioco);
			Tris.menu = new MainMenu();
			Tris.mFrame.getContentPane().add(Tris.menu);
			Tris.menu.getParent().revalidate();
		}
	}

	public int turnoGiocatore(int turno) {
		if(turno == 1) { 
			this.turno = 2;
			return turno;
		}else {
			this.turno = 1;
			return turno;
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////   SCORE GIOCO   //////////////////////////////////////////////////////////////

	public class ScoreGioco extends JComponent {

		private static final long serialVersionUID = 1L;

		JLabel giocatoreX;
		JLabel giocatoreXset;
		JLabel giocatoreO;
		JLabel giocatoreOset;
		JLabel setGame;
		JLabel turnoG;
		JLabel turnoGCor;


		public ScoreGioco(int set, int game, int turno, Giocatore gx, Giocatore go) {

			repaint();
		}

		public void paintComponent(Graphics g) {

			super.paintComponents(g);
			//SFONDO
			ImageIcon backGround = new ImageIcon("resources"+ File.separator+"scoreIm.JPG");
			Image img = backGround.getImage();
			Image n = img.getScaledInstance(1080, 150, Image.SCALE_SMOOTH);
			backGround = new ImageIcon(n);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(backGround.getImage(),0,0,this);

			//TESTI
			giocatoreX = new JLabel();
			giocatoreXset = new JLabel();
			Font fontGx = new Font("GIOCATORE X", Font.BOLD, 30);
			g.setFont(fontGx);
			giocatoreX.setText("GIOCATORE X");
			giocatoreXset.setText(statoGx());
			g.setColor(Color.RED);
			g.drawString(giocatoreX.getText(), 40, 30);
			g.drawString(giocatoreXset.getText(), 40, 80);


			setGame = new JLabel();
			turnoG = new JLabel();
			turnoGCor = new JLabel();
			Font font1 =new Font("SetGame", Font.BOLD, 20);
			g.setFont(font1);
			setGame.setText("set : "+getSet()+" Game: "+getGame());
			g.setColor(Color.BLACK);
			g.drawString(setGame.getText(), 455, 20);

			Font font2 = new Font("Turno", Font.BOLD, 30);
			g.setFont(font2);
			turnoG.setText("TURNO:");
			turnoGCor.setText(statoTurno(getTurno()));
			g.setColor(Color.GREEN);
			g.drawString(turnoG.getText(), 475, 70);
			g.drawString(turnoGCor.getText(), 430, 105);


			giocatoreO = new JLabel();
			giocatoreOset = new JLabel();
			Font fontGo = new Font("GIOCATORE O", Font.BOLD, 30);
			g.setFont(fontGo);
			giocatoreO.setText("GIOCATORE O");
			giocatoreOset.setText(statoGo());
			g.setColor(Color.BLUE);
			g.drawString(giocatoreO.getText(), 750, 30);
			g.drawString(giocatoreOset.getText(), 750, 80);

		}

		public String statoTurno(int turno) {
			if( turno == 1)
				return "GIOCATORE X";
			else
				return "GIOCATORE O";

		}

		public String statoGx() {return " set: "+ gx.getSet();}

		public String statoGo() {return " set: "+ go.getSet();}

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////   TAVOLO GIOCO   /////////////////////////////////////////////////////////////


	public class TavoloGioco extends JComponent {

		private static final long serialVersionUID = 1L;
		public static final int rig = 3;
		public static final int col = 3;
		public Block blocchi[][];
		private JButton buttons [][];
		public ImageIcon x;
		public ImageIcon o;
		public ImageIcon resetIm;
		public ImageIcon winnerGx;
		public ImageIcon winnerGo;

		public TavoloGioco(int set, int game, int turno, Giocatore gx, Giocatore go) {		
			inizzializza(set, game, turno, gx, go);
			repaint();
		}
		public void inizzializza(int set, int game, int turno, Giocatore gx, Giocatore go) {

			// IMMAGINI
			x = new ImageIcon("resources"+ File.separator+"X.png");
			Image img = x.getImage();
			Image nx = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			x = new ImageIcon(nx);

			o = new ImageIcon("resources"+ File.separator+"O.png");
			Image imgo = o.getImage();
			Image no = imgo.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			o = new ImageIcon(no);

			resetIm = new ImageIcon("resources"+ File.separator+"white.jpg");
			Image imgRes = resetIm.getImage();
			Image nR = imgRes.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			resetIm = new ImageIcon(nR);
			
			winnerGx = new ImageIcon("resources"+ File.separator+"giocatoreX.jpg");
			Image imgWGx = winnerGx.getImage();
			Image nWGx = imgWGx.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			winnerGx = new ImageIcon(nWGx);
			
			winnerGo = new ImageIcon("resources"+ File.separator+"giocatoreO.jpg");
			Image imgWGo = winnerGo.getImage();
			Image nWGo = imgWGo.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			winnerGo = new ImageIcon(nWGo);


			// CREAZIONE BLOCCHI E CELLE CHE COMPORRANNO LA GRIGLIA DI GIOCO
			blocchi = new Block[rig][col];
			for(int i=0; i<rig; i++) {
				for(int j=0; j<col; j++) {
					blocchi[i][j] = new Block(0);
				}
			}
			buttons = new JButton[rig][col];
			setLayout(new GridLayout(rig, col, 3, 3));
			for(int i=0; i<rig; i++) {
				for(int j=0; j<col; j++) {
					buttons[i][j] = new JButton();
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setOpaque(true);
					buttons[i][j].addActionListener(new ButtonListener());
					add(buttons[i][j]);
				}
			}
			setTurno(firstTurn(1));

		}

		// SETTAGGIO GIOCATA E RISPETTIVI CONTROLLI 
		public class ButtonListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<rig; i++) {
					for(int j=0; j<col; j++) {
						if(buttons[i][j] == e.getSource()) {
							if(blocchi[i][j].getState() == 0) {
								if(getTurno() == gx.getSimbol()) {
									buttons[i][j].setBackground(Color.RED);
									buttons[i][j].setIcon(x);
									blocchi[i][j].setState(gx.getSimbol());
									score.repaint();
									if(controlloTris(gx.getSimbol())) {
										if( gx.getSet() != getSet()) {
											winnerSet(gx.getSimbol());
											return;
										}
										else {
											winnerGame(gx.getSimbol());
											return;
										}

									}
									if(! isEmpty()) {
										turnoGiocatore(getTurno());
										return;
									}
									else {
										errorPatta();
										return;
									}
								}
								if(getTurno() == go.getSimbol()) {
									buttons[i][j].setBackground(Color.BLUE);
									buttons[i][j].setIcon(o);;
									blocchi[i][j].setState(go.getSimbol());
									score.repaint();
									if(controlloTris(go.getSimbol())) {
										if( go.getSet() != getSet()) {
											winnerSet(go.getSimbol());
											return;
										}
										else {
											winnerGame(go.getSimbol());
											return;
										}

									}
									if(! isEmpty()) {
										turnoGiocatore(getTurno());
										return;
									}
									else {
										errorPatta();
										return;
									}
								}						
							}
							else {
								errorOccupato();
								return;
							}	
						}
					}
				}
			}	
		}

		// CONTROLLI 
		public void errorPatta() {			
			JOptionPane.showMessageDialog(this,"Nessuno dei due giocatori ha vinto","Partita Patta",JOptionPane.WARNING_MESSAGE, optionIm);
			reset();
		}

		public void errorOccupato() {		
			JOptionPane.showMessageDialog(this,"Cella gia occupata","ERROR",JOptionPane.WARNING_MESSAGE, optionIm);
		}

		public void winnerSet(int index) {
			if(index == gx.getSimbol()) 
				JOptionPane.showMessageDialog(this,"Giocatore X ha vinto il set!","WINNER SET",JOptionPane.INFORMATION_MESSAGE, winnerGx);
			if(index == go.getSimbol())  
				JOptionPane.showMessageDialog(this,"Giocatore O ha vinto il set!","WINNER SET",JOptionPane.INFORMATION_MESSAGE, winnerGo);

			reset();
		}

		public void winnerGame(int index) {	
			if(index == gx.getSimbol()) {
				JOptionPane.showMessageDialog(this,"Giocatore X ha vinto il Game!","WINNER GAME",JOptionPane.INFORMATION_MESSAGE, winnerGx);
				ScorePartite.addRecord(gx);
			}
			if(index == go.getSimbol()) {    
				JOptionPane.showMessageDialog(this,"Giocatore O ha vinto il Game!","WINNER GAME",JOptionPane.INFORMATION_MESSAGE, winnerGo);
				ScorePartite.addRecord(go);

			}
			restart();

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

		}

		public int firstTurn(int index) {
			return ((int)((Math.random() * (2 - 1)) +index));
		}

		public boolean isEmpty() {
			for( int i=0; i<rig; i++ ) {
				for( int j=0; j<col; j++ )
					if(blocchi[i][j].getState() == 0)
						return false;
			}
			return true;
		}

		public boolean controlloTris(int index) {
			if( controlloRig(index) || controlloCol(index) || controlloDiag(index) ) {
				if(index == gx.getSimbol())
					gx.setSet(gx.getSet()+1);
				else
					go.setSet(go.getSet()+1);
				return true;
			}
			return false;

		}

		public boolean controlloRig(int index) {
			int k=0;
			for(int i=0; i<rig; i++) {
				for(int j=0; j<col; j++) {
					if(blocchi[i][j].getState() == index) {
						k = k+1;
						if(k == 3) {
							return true;
						}
					}

				}
				k=0;
			}
			return false;
		}

		public boolean controlloCol(int index) {
			int k=0;
			for(int i=0; i<col; i++) {
				for(int j=0; j<rig; j++) {
					if(blocchi[j][i].getState() == index) {
						k = k+1;
						if(k == 3) {
							return true;
						}
					}
				}
				k=0;
			}
			return false;
		}

		public boolean controlloDiag(int index) {

			int k=0;
			for(int i=0; i<rig; i++) {
				if(blocchi[i][i].getState() == index) {
					k= k+1;
					if(k == 3) {
						return true;
					}
				}
			}
			k=0;
			for(int i=rig-1, j=0; i>=0 && j<col; i--, j++) {
				if(blocchi[i][j].getState() == index ) {
					k = k+1;
					if(k == 3) {
						return true;
					}
				}
			}
			k=0;
			return false;
		}

		public void reset() {

			for(int i=0; i<rig; i++) {
				for(int j=0; j<col; j++) {
					blocchi[i][j].setState(0);
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setIcon(resetIm);
				}
			}
			turnoGiocatore(getTurno());
			score.repaint();
		}

		public void restart() {
			Tris.mFrame.remove(Tris.pGioco);
			Tris.menu = new MainMenu();
			Tris.mFrame.getContentPane().add(Tris.menu);
			Tris.menu.getParent().revalidate();
		}


	}


}
