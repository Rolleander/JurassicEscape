import java.awt.Graphics;

public class Stacheln extends Objekt {

	private int z = 0;
	private int ani = 0;
	private boolean b;

	public Stacheln(int x, int z, boolean b) {
		this.x = x;
		y = 230;
		istStacheln = true;
		this.z = z;
		this.b = b;
	}

	@Override
	public void paint(Graphics g, boolean bewegen) {

		if (bewegen) {
			x += Map.SPEED;
			if (x > 800) {
				aktiv = false;
			}
			z++;
			if (ani == 0) {
				int a = 200;
				if (b) {
					a += 100;
				}
				if (z == a) {
					z = 0;
					ani++;
					Main.sound.playSound(6, true);
				}
			} else {
				if (ani == 3) {
					if (z == 70) {
						ani = 0;
					}
				} else {

					if (z == 20) {
						z = 0;
						ani++;

					}
				}
			}

		}

		g.drawImage(Bilder.stacheln[ani], (int) x, (int) y, null);

		rx = (int) x + 16;
		ry = (int) y;

		if (ani < 2) {
			schaden = false;
		} else {
			schaden = true;
		}

	}

}
