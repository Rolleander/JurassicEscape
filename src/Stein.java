import java.awt.Color;
import java.awt.Graphics;

public class Stein extends Objekt {

	private float sprungh;
	private float winkel;

	private int ani = 0, design;

	public Stein(int h, int d) {
		y = 220;
		x = 0;
		sprungh = h;
		winkel = 90;
		y += (int) (Math.random() * 50 + 1) - 5;
		x = -32;
		design = d;
	}

	public void paint(Graphics g, boolean bewegen) {

		int s = 0;

		if (bewegen) {

			winkel += 1.5;
			if (winkel > 180) {
				winkel = 0;
				sprungh *= 0.6;
				Main.sound.playSound(1, true);
			}

			x += Map.SPEED + 1;
			ani++;
			if (ani > 59) {
				ani = 0;
			}
		}
		s = (int) (Math.sin(Math.toRadians(winkel)) * sprungh * -1);

		g.setColor(new Color(0, 0, 0, 100));
		g.fillOval((int) x, (int) y, 32, 32);

		g.drawImage(Bilder.stein[design][ani / 20], (int) x, (int) y + s, null);

		rx = (int) x + 16;
		ry = (int) (y + s + 16);

		if (x > 800) {
			aktiv = false;
		}
	}

}
