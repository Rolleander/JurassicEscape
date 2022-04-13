
public class Profil {

	private int[] highscores = new int[10];
	private int[] shop = new int[50];
	private int geld = 0;
	private int spielzeit = 0;

	public Profil() {
		for (int i = 0; i < highscores.length; i++) {
			highscores[i] = 0;
		}
		geld = 0;
		spielzeit = 0;

	}

	public int[] getItems() {
		return shop;
	}

	public void setItems(int[] i) {
		shop = i;
	}

	public void setItem(int nr, int w) {
		shop[nr] += w;
	}

	public void einsetzenItem(int nr) {
		shop[nr]--;
	}

	public int getSpielzeit() {
		return spielzeit;
	}

	public void setSpielzeit(int s) {
		spielzeit = s;
	}

	public void spieleMeterZeit(int m) {
		spielzeit += m;
	}

	public int getGeld() {
		return geld;
	}

	public void setGeld(int g) {
		geld = g;

	}

	public void addGeld(int g) {
		geld += g;
	}

	public int[] getHighscores() {
		return highscores;
	}

	public void setHighscores(int[] h) {
		highscores = h;
	}

	public void setHighscore(int nr, int h) {
		highscores[nr] = h;
	}
}
