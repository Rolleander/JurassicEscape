import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Shop {

	public Font font = new Font("Arial", 25, 25);
	public Font font2 = new Font("Arial", 15, 15);
	private Item[] items = new Item[50];

	private int scrolly = 0;

	private Profil profil;

	public Item[] getItems() {
		return items;
	}

	public Shop() {
		items[0] = new Item("Steinschild", "Spieler erhält bei Zerstörung eines Steins 5 Münzen", 350);
		items[1] = new Item("Lavatreter", "PowerUp dauert länger an", 775);
		items[2] = new Item("Zeitsprung", "Spieler kann länger in der Luft stehen", 600);
		items[3] = new Item("Zielwasser", "Zeigt das Landefeld vor einem Sprung an", 1000);
		items[4] = new Item("Schutzengel", "Wiederbelebt den Spieler bei Aktivierung", 500);
		items[4].mehrmals = true;
		items[5] = new Item("Startgeschenk", "Spieler startet mit zufälligem PowerUp", 450);
		items[6] = new Item("Goldrausch", "Jede 10te Münze erhält man doppelt", 300);
		items[7] = new Item("Stuntman", "Spieler dreht sich im Sprung", 200);
		items[8] = new Item("Kartenpuzzle", "Ein neuer Streckenabschnitt", 700);
		items[9] = new Item("Stachelvorsorge", "Stachelfallen bleiben länger untätig", 975);
		items[10] = new Item("Koboldhut", "Erhöht die Wahrscheinlichkeit auf Münzen", 475);
		items[11] = new Item("Kriegerrüstung", "Schützt außerhalb vom PowerUp vor einem Fels", 650);
		items[12] = new Item("Lampe des Hellsehers", "Erkennt frühzeitig Fallen", 550);
		items[13] = new Item("Wurfaxt", "Wirft nahe Felsen ab", 1375);
		items[14] = new Item("Schatz des Entdeckers", "Es erscheinen seltene Rubine, die 10 Münzen wert sind", 400);
		items[15] = new Item("Eisregen", "Manche Lavafelder sind von Eis bedeckt", 1100);
		items[16] = new Item("Frischfleisch", "Ein neuer Dino wird angelockt", 900);
		items[17] = new Item("Checkerbrille", "Pass auf vor dem Stlye", 425);
		items[18] = new Item("Gestaltwandler", "Spieler startet mit zufälligem Skin", 325);

	}

	public void paint(Graphics g, Profil p) {
		profil = p;
		for (int i = 0; i < 19; i++) {
			paintItem(g, new Point(10, 20 + i * 70 - scrolly), i);
		}

		scrolly += Maus.dreh * 25;

	}

	public void paintItem(Graphics g, Point punkt, int item) {
		Rectangle rect = new Rectangle(punkt.x, punkt.y, 450, 60);
		int id = 0;
		int mx = Maus.getX();
		int my = Maus.getY();
		g.setColor(Color.BLACK);
		if (rect.inside(mx, my)) {
			id = 1;
			if (Maus.gedruecktMaus()) {
				kaufeItem(item);
			}
		}

		int[] spitems = profil.getItems();
		Item it = items[item];

		if (spitems[item] > 0 && it.mehrmals == false) {
			g.drawImage(Bilder.button[2], rect.x, rect.y, rect.width, rect.height, null);
		} else {
			g.drawImage(Bilder.button[id], rect.x, rect.y, rect.width, rect.height, null);
		}
		int x = rect.x;
		int y = rect.y;

		g.drawImage(Bilder.items[item], x + 10, y + 10, 40, 40, null);
		g.setFont(font);
		g.drawString(it.name, x + 60, y + 30);

		if (it.mehrmals) {
			g.drawString("x" + profil.getItems()[item], x + 300, y + 30);
		}

		g.setFont(font2);
		g.drawString(it.beschreibung, x + 60, y + 45);

		x = x + rect.width - 50;
		g.drawImage(Bilder.geld, x, y + 7, null);

		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(it.preis + "", x - 5 - fm.stringWidth(it.preis + ""), y + 25);
	}

	private int kaufen = -1;

	private void kaufeItem(int item) {
		// TODO Auto-generated method stub

		if (profil.getItems()[item] == 0 || items[item].mehrmals) {

			if (profil.getGeld() >= items[item].preis) {

				kaufen = item;
			}
		}
	}

	public int moechteKaufen() {
		int k = kaufen;
		kaufen = -1;
		return k;

	}

	public void setTasten(Steuerung tasten) {
		// TODO Auto-generated method stub
		if (tasten.getTasten()[0]) {
			// hoch
			scrolly -= 10;
		} else if (tasten.getTasten()[2]) {
			// runter
			scrolly += 10;
		}
	}

}
