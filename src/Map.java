import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Map {

	public static float SPEED = 2f;

	private Font font = new Font("Arial", 25, 25);
	private float bodenx = 0;
	private int db = 0;
	private float dx, dy;
	private float w;
	private int fl;
	private int[] pfad = new int[5];
	private int meter = 0, sz = 0;
	private final int ST_BRAUN = 0, ST_GRAU = 1, ST_WEISS = 2, ST_SCHWARZ = 3;
	private int[] steindesign = { ST_BRAUN, ST_BRAUN, ST_SCHWARZ, ST_WEISS, ST_GRAU, ST_BRAUN, ST_GRAU, ST_SCHWARZ };
	private int design = 0;
	private int endc = 0;
	private ArrayList<Objekt> objekte = new ArrayList<Objekt>();
	private boolean zurueck = false, schutzengel = false, schutzeingesetz = false;

	private int unsterblich = 0;
	private int[] items;
	private float bx = 0;

	private int axtid = -1;
	private float axtx, axty;

	private int blasez, blaseid;

	private boolean schutzengelverbrauch = false;

	public Map(int[] it) {

		pfad[0] = design;
		SPEED = 2f;

		items = it;

		axtx = 500;
		axty = 220;
	}

	public int getMeter() {
		return meter;
	}

	public Spieler paint(Graphics g, Spieler spieler) {

		if (schutzengel) {
			// g.setColor(Color.BLACK);
			// g.fillRect(0,0,800,500);
			int fx = 200, fy = 300;
			g.drawImage(Bilder.button[0], fx, fy, 400, 150, null);

			g.setFont(font);
			g.drawString("Schutzengel x" + items[4], fx + 50, fy + 50);
			g.drawString("Schutzengel einsetzen?", fx + 50, fy + 90);
			Rectangle rect = new Rectangle(fx + 50, fy + 100, 100, 40);
			int mx = Maus.getX();
			int my = Maus.getY();
			g.setColor(Color.BLACK);
			int id = 0;
			if (rect.inside(mx, my)) {
				id = 1;
				if (Maus.gedruecktMaus()) {
					schutzengelverbrauch = true;
					schutzengel = false;
					Main.setCursor(false);
					unsterblich = 100;
					schutzeingesetz = true;
				}
			}

			g.drawImage(Bilder.button[id], rect.x, rect.y, rect.width, rect.height, null);
			g.drawString("Ja", rect.x + 20, rect.y + 30);
			rect = new Rectangle(fx + 250, fy + 100, 100, 40);
			mx = Maus.getX();
			my = Maus.getY();
			g.setColor(Color.BLACK);
			id = 0;
			if (rect.inside(mx, my)) {
				id = 1;
				if (Maus.gedruecktMaus()) {
					schutzengel = false;
					spieler.tot();
				}
			}

			g.drawImage(Bilder.button[id], rect.x, rect.y, rect.width, rect.height, null);
			g.drawString("Nein", rect.x + 20, rect.y + 30);

		} else {

			if (spieler.lebendig()) {
				bx += Map.SPEED / 2;
			}
			if (bx > 800) {
				bx = 0;
			}
			for (int i = -1; i < 1; i++) {
				g.drawImage(Bilder.hintergrund, (int) (bx + i * 800), 0, null);
			}
			unsterblich--;
			if (spieler.lebendig()) {

				meter += Map.SPEED;

				bodenx += Map.SPEED;
				if (bodenx > 224) {
					bodenx = 0;
					for (int i = 3; i > -1; i--) {
						pfad[i + 1] = pfad[i];
					}
					pfad[0] = design;
					if ((int) (Math.random() * 25 + 1) == 1) {
						if (items[8] > 0) {
							design = (int) (Math.random() * 8 + 1) - 1;
						} else {
							design = (int) (Math.random() * 7 + 1) - 1;
						}

					}

					if ((int) (Math.random() * 5 + 1) == 1) {
						int sh = 100 + (int) (Math.random() * 200 + 1);
						Objekt st = new Stein(sh, steindesign[design]);
						objekte.add(st);
						blasez = 100;
						blaseid = 0;

					} else {
						if ((int) (Math.random() * 3 + 1) == 1) {// Loch
							blasez = 100;
							blaseid = 1;
							boolean wertvoll = false;
							if (items[15] > 0) {
								if ((int) (Math.random() * 5 + 1) == 1) {
									wertvoll = true;
									blaseid = 3;
								}
							}
							for (int i = 0; i < (int) (Math.random() * 7 + 1); i++) {
								objekte.add(new Loch(i * -32 - 32, wertvoll));
							}

						} else {
							// Stacheln
							if ((int) (Math.random() * 5 + 1) == 1) {
								int z = (int) (Math.random() * 200 + 1);
								boolean b = false;
								if (items[9] > 0) {
									b = true;
								}
								for (int i = 0; i < (int) (Math.random() * 8 + 1); i++) {
									objekte.add(new Stacheln(i * -32 - 32, z, b));
								}
								blasez = 100;
								blaseid = 2;
							}

						}

					}
					int chanc = 5;
					if (items[10] > 0) {
						chanc = 4;
					}
					if ((int) (Math.random() * chanc + 1) == 1) {
						// Geld
						int my = 0;
						if ((int) (Math.random() * 3 + 1) == 1) {
							my = (int) (Math.random() * 50 + 1) + 50;
						}

						for (int i = 0; i < (int) (Math.random() * 10 + 1) + 3; i++) {
							boolean wertvoll = false;
							if (items[14] > 0) {
								if ((int) (Math.random() * 50 + 1) == 1) {
									wertvoll = true;
								}
							}
							objekte.add(new Muenze(i * -25, 240 - my, wertvoll));
						}
					}

					if ((int) (Math.random() * 20 + 1) == 1) {
						// Powerup
						objekte.add(new PowerUp());
					}

					sz++;
					if (sz > 10) {
						sz = 0;
						SPEED += 0.1f;
					}
				}
			}
			int x = 0;
			for (int i = -1; i < 4; i++) {
				x = (int) (224 * i + bodenx);
				int id = pfad[i + 1];

				g.drawImage(Bilder.pfad[id], x, 190, null);
				g.drawImage(Bilder.feuer[fl / 20], x + 160, 300, null);
			}

			int spx = spieler.getX();
			int spy = spieler.getY();

			if (items[12] > 0) {
				if (blasez > 0) {
					g.drawImage(Bilder.blase[blaseid], spx, spy - 50, null);
					blasez--;
				}
			}

			for (int i = 0; i < objekte.size(); i++) {
				Objekt ob = objekte.get(i);
				if (ob.istLoch()) {
					int lx = ob.getX();
					if (spx - 5 >= lx && spx + 5 <= lx + 32) {
						if (spy >= 230) {
							if (spieler.kannLoch() == false && ob.istTeuer() == false) {
								if (spieler.lebendig()) {

									spieler = spielerStirbt(spieler);
									Main.sound.playSound(3, true);
								}
								break;
							} else {
								spieler.schutzAnimation();
							}
						}
					}
				} else {
					int ox = ob.getX();
					int oy = ob.getY();

					if (spieler.kannWerfen() && axtid == -1) {
						if (ob.istLoch == false && ob.istMuenze == false && ob.istStacheln == false
								&& ob.getPowerUp() == -1) {
							if (Point.distance(ox, oy, spx, spy) <= 300) {
								if (ox < spx) {
									spieler.werfen();
									axtid = i;

								}
							}
						}
					}

					if (Point.distance(ox, oy, spx, spy) <= 16) {
						if (ob.istMuenze) {
							if (ob.istTeuer()) {
								spieler.sammleMuenze(10);

							} else {
								spieler.sammleMuenze();
							}
							objekte.remove(i);
							Main.sound.playSound(5, true);

						} else if (ob.getPowerUp() > -1) {
							spieler.powerup(ob.getPowerUp());
							Main.sound.playSound(0, true);
							objekte.remove(i);

						} else {
							if (ob.istStacheln()) {
								if (ob.schadet()) {
									if (spieler.lebendig()) {
										spieler = spielerStirbt(spieler);
										Main.sound.playSound(7, true);
										Main.sound.playSound(3, true);
									}
								}
							} else {
								if (spieler.zerstoertSteine() == false) {
									if (spieler.lebendig()) {
										spieler = spielerStirbt(spieler);
										Main.sound.playSound(2, true);
										Main.sound.playSound(3, true);
									}
								} else {
									if (items[0] > 0) {
										spieler.sammleMuenze(5);
										Main.sound.playSound(5, true);
									}
									spieler.steinGeblockt();
									objekte.remove(i);
									Main.sound.playSound(2, true);
								}
							}
						}

					}

				}
			}

			if (axtid > -1) {
				if (axtid < objekte.size()) {

					Objekt ob = objekte.get(axtid);
					if (ob.istLoch == false && ob.istMuenze == false && ob.istStacheln == false
							&& ob.getPowerUp() == -1) {
						int ox = objekte.get(axtid).getX();
						int oy = objekte.get(axtid).getY();

						Graphics2D g2d = (Graphics2D) g;
						AffineTransform affineTransform = new AffineTransform();

						affineTransform.setToTranslation(axtx - 12, axty - 12);

						affineTransform.rotate(Math.toRadians(w * 5), 16, 16);

						g2d.drawImage(Bilder.axt, affineTransform, null);

						double wink = Math.atan2(axty - oy, axtx - ox);
						axtx += Math.cos(wink + Math.PI) * 5;
						axty += Math.sin(wink + Math.PI) * 5;

						if (Point.distance(ox, oy, axtx, axty) <= 6) {

							objekte.remove(axtid);
							axtid = -1;
							Main.sound.playSound(2, true);

							if (items[0] > 0) {
								spieler.sammleMuenze(5);
							}
							Main.sound.playSound(5, true);
							axtx = spx;
							axty = spy;
						}
					} else {

						axtid = -1;
						spieler.stopWurfPause();
					}
				} else {

					axtid = -1;
					spieler.stopWurfPause();
				}

			}

			ArrayList<Integer> lochx = new ArrayList<Integer>();
			for (int i = 0; i < objekte.size(); i++) {
				Objekt ob = objekte.get(i);
				if (ob.istLoch() && ob.istTeuer() == false) {
					lochx.add(ob.getX());
				}
			}

			for (int i = 0; i < objekte.size(); i++) {
				Objekt ob = objekte.get(i);
				ob.paint(g, spieler.lebendig());

				if (ob.istLoch() == false && ob.getPowerUp() == -1) {
					for (int h = 0; h < lochx.size(); h++) {
						int ox = ob.getX();
						int oy = ob.getY();
						int lx = lochx.get(h);

						if (ox + 10 >= lx && ox - 10 <= lx + 32) {
							if (oy >= 235) {
								objekte.remove(i);

								break;
							}
						}

					}

				}
				if (ob.destroy()) {
					objekte.remove(i);
				}
			}
			spieler.paint(g, true);
			int dk = 0;
			if (items[16] > 0) {
				dk = 1;
			}
			g.drawImage(Bilder.dino[dk][db], (int) (547 + dx + Math.cos(Math.toRadians(w)) * 10),
					(int) (100 + dy + Math.sin(Math.toRadians(w)) * 30), null);

			spieler.setDinoY((int) (100 + dy + Math.sin(Math.toRadians(w)) * 30));
			if (spieler.lebendig()) {
				w += Math.random() * (5 + SPEED);
			} else {
				if (spieler.fertig()) {
					if (db == 0) {
						Main.sound.playSound(8, true);
					}
					db = 1;

					endc++;
					if (endc > 100) {
						zurueck = true;
					}
				}
			}

			fl++;
			if (fl > 59) {
				fl = 0;

			}

		}

		return spieler;

	}

	private Spieler spielerStirbt(Spieler spieler) {
		// TODO Auto-generated method stub
		if (unsterblich > 0) {

		} else {
			if (items[4] > 0 && schutzeingesetz == false) {

				schutzengel = true;
				Main.setCursor(true);
			} else {
				spieler.tot();
			}
		}
		return spieler;
	}

	public boolean zurueck() {

		return zurueck;
	}

	public boolean verbrauchtSchutzengel() {
		boolean s = schutzengelverbrauch;
		schutzengelverbrauch = false;
		return s;
	}

}
