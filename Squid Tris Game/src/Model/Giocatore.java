package Model;

public class Giocatore {
	private int set;
	private int game;
	private int turno;

	public Giocatore () {
		this.set = 0;
		this.game = 0;
		this.turno = 0;
	}

	public Giocatore(int set, int game, int t) {
		super();
		this.set = set;
		this.game = game;
		this.turno = 0;
	}

	public Integer getSet() {
		return set;
	}
	public void setSet(int set) {
		this.set = set;
	}
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public void setSimbol(int s) {
		this.turno = s;
	}
	public int getSimbol() {
		return turno;
	}

	@Override
	public String toString() {
		if(this.getSimbol() == 1) 
			return "Giocatore "+" X  "+" [Set: " + this.set + " Game: "+ this.game +"]";

		if(this.getSimbol() == 2) 
			return "Giocatore "+" O  "+" [Set: " + this.set + " Game: "+ this.game +"]";

		else {
			return " ";
		}
	}

}
