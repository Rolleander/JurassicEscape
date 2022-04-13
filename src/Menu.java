import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Menu {

	private boolean open = true;
	public Font font = new Font("Arial", 25, 25);
	private int mainmenu = -1, submenu = 0;
	private float dx, dy, dinowinkel, bx;

	private int muenzen, meter, highscoreplatz;
	private boolean endespiel = false;
	private Shop shop = new Shop();
	private Item[] items = shop.getItems();

	private boolean[] itemsaktiv = new boolean[50];

	private LoadAndSave save = new LoadAndSave();
	private Profil profil;

	private int skin = 0;
	private int skinauswahl = 0;

	private int[] skinpreis = { 150, 320, 275, 800, 460, 555, 675, 700, 400, 695, 480, 590, 725, 375, 625 };

	public Menu() {

		profil = save.load();
		for (int i = 0; i < 50; i++) {
			itemsaktiv[i] = true;
		}

	}

	public int getSelectedSkin() {

		int s = skin;

		if (profil.getItems()[18] > 0 && itemsaktiv[18]) {
			boolean b = false;
			do {
				s = (int) (Math.random() * 16 + 1) - 1;
				if (s == 0) {
					b = true;
				} else if (profil.getItems()[34 + s - 1] > 0) {
					b = true;
				}
			} while (b == false);
		}

		return s;
	}

	public void speichern() {
		save.save(profil);
	}

	public Profil getProfil() {
		return profil;
	}

	public int[] getItemsAktiv() {
		int[] it = new int[50];
		int[] items = profil.getItems();

		for (int i = 0; i < 50; i++) {
			if (items[i] > 0 && itemsaktiv[i]) {
				int z = items[i];
				it[i] = z;
			}

		}

		return it;
	}

	private int scrolly = 0;

	public void paint(Graphics g) {
		g.setFont(font);

		if (endespiel) {
			int x = 200, y = 100;

			g.drawImage(Bilder.button[0], x, y, 400, 300, null);
			g.setColor(Color.WHITE);
			g.drawString("Du wurdest gefressen!", x + 50, y + 50);
			g.setColor(new Color(50, 50, 50));
			g.drawString("Strecke: ", x + 40, y + 120);

			g.drawString("Münzen: ", x + 40, y + 160);

			if (highscoreplatz > -1) {
				g.drawString((highscoreplatz + 1) + ". Highscore erreicht!", x + 40, y + 200);
			}

			g.setColor(new Color(100, 250, 100));
			g.drawString(meter + " m", x + 140, y + 120);
			g.drawString("" + muenzen, x + 140, y + 160);
			if (paintButton(g, new Rectangle(x + 40, y + 240, 150, 40), "Hauptmenü")) {
				endespiel = false;
			}
			if (paintButton(g, new Rectangle(x + 210, y + 240, 150, 40), "Nochmal")) {
				endespiel = false;
				starteSpiel();
			}
		} else {

			if (mainmenu < 0) {

				g.drawImage(Bilder.title, 0, 0, null);

				if (paintButton(g, new Rectangle(450, 350, 300, 50), "Spiel starten!")) {
					mainmenu = 0;
				}
			} else {

				bx += 0.2;

				if (bx > 800) {
					bx = 0;
				}
				for (int i = -1; i < 1; i++) {
					g.drawImage(Bilder.hintergrund, (int) (bx + i * 800), 0, null);
				}

				int dk = 0;
				if (profil.getItems()[16] > 0 && itemsaktiv[16]) {
					dk = 1;
				}
				g.drawImage(Bilder.dino[dk][0], (int) (547 + dx + Math.cos(Math.toRadians(dinowinkel)) * 10),
						(int) (100 + dy + Math.sin(Math.toRadians(dinowinkel)) * 30), null);

				dinowinkel += 0.2;

				paintGeld(g, 750, 20);

				if (submenu == 0) {
					// Hauptmenü
					g.setColor(Color.WHITE);
					g.drawString("Gesamte Strecke: " + profil.getSpielzeit() + " m", 10, 465);
					String[] ausw = { "Starten!", "Spieler", "Shop", "Highscores", "Beenden" };
					int wahl = paintSelection(g, new Rectangle(70, 50, 300, 50), ausw);
					switch (wahl) {
					case 1:
						starteSpiel();
						break;
					case 2:
						submenu = 1;
						break;
					case 3:
						submenu = 2;
						break;
					case 4:
						submenu = 3;
						break;
					case 5:
						System.exit(0);
						break;
					}
				} else if (submenu == 1) {
					int skx = 450, sky = 50;
					g.setColor(Color.BLACK);
					g.drawString("Skinauswahl", skx - 50, sky - 5);
					int bid = 0;

					if (skinauswahl > 0 && profil.getItems()[34 + skinauswahl - 1] == 0) {
						bid = 2;
						int x = skx + 50, y = sky + 55;
						String geld = "" + skinpreis[skinauswahl - 1];
						FontMetrics fm = g.getFontMetrics(font);

						g.drawImage(Bilder.geld, x, y, null);

						x -= 10;

						x -= fm.stringWidth(geld);
						g.setColor(Color.BLACK);
						g.drawString(geld, x, y + 18);

						if (paintButton(g, new Rectangle(skx - 50, sky + 75, 150, 30), "Skin kaufen")) {
							int gg = skinpreis[skinauswahl - 1];
							if (profil.getGeld() >= gg) {
								profil.addGeld(gg * -1);
								profil.setItem(34 + skinauswahl - 1, 1);
								speichern();
							}

						}
					} else {
						skin = skinauswahl;
					}

					g.drawImage(Bilder.button[bid], skx, sky, 50, 50, null);
					g.drawImage(Bilder.spieler[skinauswahl][0], skx + 8, sky + 5, null);
					if (paintButton(g, new Rectangle(skx - 50, sky, 40, 50), "<")) {
						skinauswahl--;
						if (skinauswahl < 0) {
							skinauswahl = 15;
						}
					}
					if (paintButton(g, new Rectangle(skx + 60, sky, 40, 50), ">")) {
						skinauswahl++;
						if (skinauswahl > 15) {
							skinauswahl = 0;
						}
					}

					int[] items = profil.getItems();

					scrolly += Maus.dreh * -25;
					int iy = scrolly;
					for (int i = 0; i < 34; i++) {
						if (items[i] > 0) {

							paintItem(g, new Rectangle(10, 10 + iy, 350, 30), i);
							iy += 35;
						}
					}

					if (paintButton(g, new Rectangle(500, 400, 250, 50), "Zurück")) {
						submenu = 0;
					}
				} else if (submenu == 2) {
					// Shop
					shop.paint(g, profil);
					int kauf = shop.moechteKaufen();
					if (kauf > -1) {
						System.out.println("okk");
						profil.addGeld(items[kauf].preis * -1);
						profil.setItem(kauf, 1);
						speichern();
					}

					if (paintButton(g, new Rectangle(500, 400, 250, 50), "Zurück")) {
						submenu = 0;
					}
				} else if (submenu == 3) {// Highscores

					int[] highscores = profil.getHighscores();

					int x = 50, y = 10;

					for (int i = 0; i < highscores.length; i++) {

						g.drawImage(Bilder.button[0], x, y + i * 45, 200, 40, null);
						g.setColor(Color.WHITE);
						g.drawString((i + 1) + ": ", x + 30, y + i * 45 + 30);
						g.setColor(Color.BLACK);
						g.drawString("" + highscores[i] + " m", x + 70, y + i * 45 + 30);
						x += 10;
					}
					if (paintButton(g, new Rectangle(450, 400, 300, 50), "Zurück")) {
						submenu = 0;
					}

				}
			}
		}

	}

	private int glanz;

	private void paintGeld(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		String geld = "" + profil.getGeld();
		FontMetrics fm = g.getFontMetrics(font);

		g.drawImage(Bilder.button[0], x - fm.stringWidth(geld) - 50, y - 8, 200, 35, null);
		g.drawImage(Bilder.geld, x, y, null);
		if (glanz / 20 < 3) {
			g.drawImage(Bilder.glanz[glanz / 20], x - 10, y - 6, null);
		}
		glanz++;
		if (glanz > 150) {
			glanz = 0;
		}
		x -= 10;

		x -= fm.stringWidth(geld);
		g.setColor(Color.BLACK);
		g.drawString(geld, x, y + 18);

	}

	public int paintSelection(Graphics g, Rectangle rect, String[] name) {
		int s = 0;

		for (int i = 0; i < name.length; i++) {
			Rectangle r = rect;
			if (i > 0) {
				r.y += (r.height) + 10;
				r.x += 20;
			}
			if (paintButton(g, r, name[i])) {
				s = i + 1;
			}

		}

		return s;
	}

	public void paintItem(Graphics g, Rectangle rect, int i) {

		int mx = Maus.getX();
		int my = Maus.getY();

		g.setColor(new Color(100, 100, 100));
		if (rect.inside(mx, my)) {
			g.setColor(new Color(150, 150, 150));
			if (Maus.gedruecktMaus()) {
				itemsaktiv[i] = !itemsaktiv[i];
			}
		}

		g.fillRect(rect.x, rect.y, 350, 30);
		g.setColor(Color.BLACK);
		g.drawRect(rect.x, rect.y, 350, 30);

		g.drawString(items[i].name, 45, rect.y + 25);
		g.drawImage(Bilder.items[i], rect.x + 2, rect.y + 2, 25, 25, null);

		if (itemsaktiv[i]) {
			g.setColor(new Color(50, 250, 50));
			g.drawString("AN", rect.x + 310, rect.y + 25);
		} else {
			g.setColor(new Color(250, 50, 50));
			g.drawString("AUS", rect.x + 295, rect.y + 25);
		}

	}

	public boolean paintButton(Graphics g, Rectangle rect, String name) {
		boolean auf = false;

		int mx = Maus.getX();
		int my = Maus.getY();
		g.setColor(Color.BLACK);
		int id = 0;
		if (rect.inside(mx, my)) {
			id = 1;
			if (Maus.gedruecktMaus()) {
				auf = true;
			}
		}

		g.drawImage(Bilder.button[id], rect.x, rect.y, rect.width, rect.height, null);
		FontMetrics fm = g.getFontMetrics(font);
		int x = (rect.x + rect.width / 2) - (fm.stringWidth(name) / 2);
		int y = (int) ((rect.y + rect.height / 2) + (font.getSize2D() / 2));
		g.drawString(name, x, y - 5);

		return auf;
	}

	public void oeffnen() {
		open = true;
	}

	public boolean isOpen() {
		return open;
	}

	public void starteSpiel() {

		open = false;
	}

	public void beendeSpiel(int meter, int muenzen) {
		// TODO Auto-generated method stub
		endespiel = true;
		this.meter = meter;
		this.muenzen = muenzen;

		getHighscorePlatz();
		profil.addGeld(muenzen);
		profil.spieleMeterZeit(meter);
		speichern();
	}

	public void getHighscorePlatz() {
		highscoreplatz = -1;
		int[] highscores = profil.getHighscores();
		for (int i = 0; i < 10; i++) {
			if (meter > highscores[i]) {
				highscoreplatz = i;

				break;
			}
		}
		if (highscoreplatz > -1) {
			for (int i = 9; i > highscoreplatz; i--) {
				highscores[i] = highscores[i - 1];
			}
			highscores[highscoreplatz] = meter;
			profil.setHighscores(highscores);
		}
	}

	public void verbrauchtSchutzengel() {
		profil.einsetzenItem(4);
	}

	public void setTasten(Steuerung tasten) {
		// TODO Auto-generated method stub
		shop.setTasten(tasten);
	}
}
