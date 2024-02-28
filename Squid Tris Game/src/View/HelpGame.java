package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Control.Tris;

public class HelpGame extends JComponent {

	private static final long serialVersionUID = 1L;
	public String titolo;
	public String help;
	public JTextArea textTitolo;
	public JTextArea textHelp;
	public JButton back;

	public String getHelp() {

		help = (
				"Tasto START:"
						+"\n"+"Per giocare in modalità 1vs1 clicca START."
						+ "\n" + "Puoi selezionare il range di set da vincere per poter aggiudicarsi il game, puoi scegliare tra diversi set/game per giocare,"
						+"\n"+"se non selezioni alcun range verrà avviata una partita di default."
						+"\n"+"\n"+
				"Tasto SCORE:"
						+"\n"+
						"Puoi visualizzare i risultati delle partite precedenti relative al giocatore vincente (X, O)"
						+"\n"+"con il numero di set vinti e numero totale di game."
						+"\n"+"Tutti gli score delle partite rimarranno salvati fino a quando la sessione rimarrà aperte."
						+"\n"+"\n"+
				"Tasto HELP:"
						+"\n"+
						"Visualizzi la funzionalita e le specifiche dei pulsanti presenti nel menù,"
						+"\n"+"una spiegazione più approfondita di ciò che verrà visualizzato."
						+"\n"+"\n"+
				"Informazioni utili:"
						+"\n"+"Puoi visitare il sito: https://it.wikipedia.org/wiki/Tris_(gioco) "
						+"\n"+"\n"+
				"Per tornare alla schermata del menù clicca BACK.");

		return help;
	}


	public HelpGame() {

		titolo = "HELP";
		textTitolo = new JTextArea(titolo);
		textTitolo.setEditable(false);
		JPanel front = new JPanel();
		front.add(textTitolo);

		textHelp = new JTextArea(getHelp());
		textHelp.setEditable(false);
		JScrollPane pCentro = new JScrollPane(textHelp);

		JPanel pSotto = new JPanel();
		back = new JButton("Back");	
		back.addActionListener(new ritorno());
		pSotto.add(back);	

		setLayout(new BorderLayout());
		add(front, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSotto, BorderLayout.PAGE_END);
		setSize(800, 600);
		setVisible(true);
	}

	private class ritorno implements ActionListener{
		public void actionPerformed(ActionEvent e){  
			setVisible(false);
			Tris.mFrame.remove(Tris.pHelp);
			Tris.menu = new MainMenu();
			Tris.mFrame.getContentPane().add(Tris.menu);
			Tris.menu.getParent().revalidate();
		}
	}


}
