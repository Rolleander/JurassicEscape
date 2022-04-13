import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Spieler extends Objekt {

	private int ani = 1, m = 0;

	private boolean sprung = false, lebt = true;
	private float sprungw = 0;
	public Font font = new Font("Arial", 20, 20);
	private boolean[] powerup = new boolean[3];
	private int[] powertimer = new int[3];
	private int sprunghoehe = 100, sprungt = 0;
	public final int PWR_STEIN = 0, PWR_LOCH = 1, PWR_SPRUNG = 2;

	private boolean aura = false, schutz = false, inmaulfliegen = false, ende = false;
	private int auz = 0;
	private int schildz = 0;
	private int dinoy;
	private int muenzen = 0;

	private int muenzz = 0;
	private boolean schild = false;
	private int design = 0;
	private int[] items;

	private int plusz = 0;
	private int plusgeld = 0;

	private int plusdrehung = 0;

	private int wurfpause = 0;

	public Spieler(int[] it, int d) {

		items = it;
		design = d;
		for (int i = 0; i < powerup.length; i++) {
			powerup[i] = false;

		}

		if (items[5] > 0) {
			int r = (int) (Math.random() * 3 + 1) - 1;
			powerup[r] = true;
			powertimer[r] = 1500;
		}

		if (items[7] > 0) {
			plusdrehung = 2;
		}

		if (items[11] > 0) {
			schild = true;
		}
	}

	public void werfen() {
		wurfpause = 1500;
	}

	public void stopWurfPause() {

		wurfpause = 0;
	}

	public boolean kannWerfen() {

		if (items[13] > 0 && wurfpause <= 0) {
			return true;
		} else {
			return false;
		}

	}

	public void sammleMuenze() {
		muenzen++;
		if (items[6] > 0) {
			muenzz++;
		}
	}

	public int getMuenzen() {
		return muenzen;
	}

	public void steinGeblockt() {
		schildz = 99;
	}

	public boolean lebendig() {
		return lebt;
	}

	public void tot() {

		lebt = false;
		auz = 0;

	}

	public boolean[] getPowerUps() {
		return powerup;
	}

	public int[] getPowerTime() {
		return powertimer;
	}

	public boolean zerstoertSteine() {
		if (powerup[PWR_STEIN]) {

			return true;
		} else if (schild) {

			schild = false;
			return true;
		} else {
			return false;
		}

	}

	public boolean kannLoch() {
		return powerup[PWR_LOCH];
	}

	public void powerup(int id) {

		powerup[id] = true;
		powertimer[id] += 2000;
		if (id == 1) {
			if (items[1] > 0) {
				powertimer[id] += 500;
			}
		}

	}

	public void paint(Graphics g, boolean bewegen) {
		if (muenzz >= 10) {
			muenzz = 0;
			muenzen++;

			plusz = 100;
			plusgeld = 1;
		}

		for (int i = 0; i < powerup.length; i++) {
			if (powerup[i]) {
				powertimer[i]--;
				if (powertimer[i] <= 0) {

					powerup[i] = false;
				}
			}
		}

		g.setColor(new Color(0, 0, 0, 100));
		if (sprung) {
			g.fillOval((int) (x + 500), 235, 32, 32);
		}
		if (schutz) {
			auz++;
			if (auz > 49) {
				auz = 0;
			}
			schutz = false;
			g.drawImage(Bilder.schutz[auz / 10], (int) (470 + x), (int) (200 + y), null);
		}
		if (ende == false) {
			if (sprung) {
				Graphics2D g2d = (Graphics2D) g;
				AffineTransform affineTransform = new AffineTransform();

				affineTransform.setToTranslation((int) (500 + x), (int) (220 + y));

				if (powerup[PWR_SPRUNG]) {

					affineTransform.rotate(Math.toRadians(sprungw * -4 - plusdrehung), 16, 16);
				} else {
					affineTransform.rotate(Math.toRadians(sprungw * -2 * plusdrehung), 16, 16);
				}

				g2d.drawImage(Bilder.spieler[design][ani], affineTransform, null);

				if (items[10] > 0) {
					g.drawImage(Bilder.hut, (int) (500 + x + 5), (int) (220 + y - 20), null);
				}
				if (items[17] > 0) {
					g.drawImage(Bilder.brille, (int) (500 + x), (int) (220 + y - 15), null);
				}
			} else {
				g.drawImage(Bilder.spieler[design][ani], (int) (500 + x), (int) (220 + y), null);
				if (items[10] > 0) {
					g.drawImage(Bilder.hut, (int) (500 + x + 5), (int) (220 + y - 10 - (1 - ani)), null);
				}
				if (items[17] > 0) {
					g.drawImage(Bilder.brille, (int) (500 + x), (int) (220 + y - 5 - (1 - ani)), null);
				}

			}

			if (wurfpause > 0) {
				wurfpause--;
			}

			if (items[13] > 0 && wurfpause <= 0) {
				g.drawImage(Bilder.axt, (int) (500 + x - 10), (int) (220 + y + 5), null);

			}

			if (schild) {
				g.drawImage(Bilder.steinschild, (int) (500 + x - 5), (int) (220 + y + 10), null);

			}

		}
		if (schildz > 0) {
			schildz--;
			g.drawImage(Bilder.schild[schildz / 20], (int) (470 + x), (int) (200 + y), null);

		}
		if (aura) {
			auz++;
			if (auz > 99) {
				auz = 0;
			}
			g.drawImage(Bilder.aura[auz / 20], (int) (470 + x), (int) (210 + y), null);
			aura = false;
		}

		if (items[3] > 0 && sprung == false) {
			int sx = (int) (120 * Map.SPEED);

			g.setColor(new Color(255, 255, 255, 50));
			g.fillOval(500 - sx, 230, 24, 24);
		}

		if (lebt) {
			m++;
		} else {
			if (auz > 20) {
				inmaulfliegen = true;
			}
			// tot
			auz++;
			if (auz > 99) {
				auz = 0;

			}

			if (inmaulfliegen == true) {
				if (x < 200) {
					g.drawImage(Bilder.treffer[auz / 20], (int) (470 + x), (int) (190 + y), null);
					x += 2.5;
					sprungw += 5;
					sprung = true;

					int di = (dinoy + 70) - 200;
					if (y < di) {
						y++;
					} else {
						y--;
					}

				} else {

					ende = true;
				}
			}
		}

		int mmax = (int) (20 - Map.SPEED * 2);
		if (mmax < 3) {

			mmax = 3;
		}

		if (m > mmax) {
			m = 0;
			ani++;
			if (ani > 2) {
				ani = 0;
			}
		}

		if (sprung && lebt) {

			sprungw += 1.5f;

			y = (float) (Math.sin(Math.toRadians(sprungw)) * -sprunghoehe);
			if (sprungw > 180) {
				sprungw = 0;
				sprung = false;
				Main.sound.playSound(1, true);

			}
		}

		rx = (int) (x + 516);
		ry = (int) (y + 236);

		if (plusz > 0) {
			plusz--;
			g.setColor(Color.GREEN);
			g.setFont(font);
			g.drawString("+" + plusgeld, rx - 22, ry - 30);
		}

	}

	public void bewegen(boolean[] tasten) {

		if (tasten[4]) {
			springen();

		}

	}

	public boolean fertig() {
		return ende;
	}

	private void springen() {
		// TODO Auto-generated method stub
		if (sprung == false) {

			sprung = true;
			sprungw = 0;
			sprunghoehe = 100;
			Main.sound.playSound(4, true);

			sprungt = 100;
			if (items[2] > 0) {
				sprungt += 35;
			}
			auz = 0;

		} else {
			if (powerup[PWR_SPRUNG] && sprungw > 15) {

				aura = true;
				if (sprungt > 0) {
					sprungt--;
					sprungw -= 1.5f;

				}
			}
		}

	}

	public void schutzAnimation() {
		// TODO Auto-generated method stub
		schutz = true;
	}

	public void setDinoY(int i) {
		// TODO Auto-generated method stub
		dinoy = i;
	}

	public void sammleMuenze(int i) {
		// TODO Auto-generated method stub
		muenzen += i;
		plusz = 100;
		plusgeld = i;

	}

}
