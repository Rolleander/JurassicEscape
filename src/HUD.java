import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public Font font = new Font("Arial", 25, 25);

	private int meter, muenzen, geld;
	private boolean[] powerup;
	private int[] pwrtime;
	private int[] high;
	private int highnr = 9, dt = 0;

	public HUD() {

	}

	public void setMeter(int m) {
		meter = m;
	}

	public void setPowerUp(boolean[] pw, int[] pwt) {
		powerup = pw;
		pwrtime = pwt;
	}

	public void paint(Graphics g) {
		int x = 10;
		int y = 10;

		String s = "" + (meter / 50);
		for (int i = 0; i < s.length(); i++) {
			int z = Integer.parseInt("" + s.charAt(i));
			g.drawImage(Bilder.zahlen[z], x + i * 32, y, null);
		}
		if (highnr > -1) {
			if (meter / 50 > high[highnr]) {

				highnr--;
				dt = 200;

			}
		}

		if (dt > 0) {
			dt--;
			int hx = 200, hy = 50;
			g.drawImage(Bilder.button[0], hx, hy, 320, 40, null);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString((highnr + 2) + ". Highscore gebrochen!", hx + 20, hy + 30);
		}

		x = 750;
		y = 10;
		g.setColor(Color.BLACK);
		for (int i = 0; i < powerup.length; i++) {
			if (powerup[i]) {
				g.drawImage(Bilder.powerup[i], x, y, null);
				int t = (int) (((float) pwrtime[i] / 2000.0f) * 100);
				// g.drawString(t+"%",x+2,y+45);

				if (t > 100) {
					t = 100;
				}

				g.setColor(new Color(100, 100, 100));
				g.fillRect(x - 2, y + 35, 36, 10);

				g.setColor(new Color(50, 50, 50));
				g.fillRect(x - 1, y + 36, 34, 8);

				g.setColor(new Color(100, 200, 100));
				g.fillRect(x - 1, y + 36, (int) ((float) (t * 3.4f) / 10.0f), 8);

				x -= 40;
			}
		}

		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("" + (muenzen + geld), 400, 30);
		g.drawImage(Bilder.geld, 375, 12, null);

	}

	public void setHighscores(int[] highscores) {
		// TODO Auto-generated method stub
		high = highscores;
		highnr = 9;
	}

	public void setMuenzen(int muenzen) {
		// TODO Auto-generated method stub
		this.muenzen = muenzen;
	}

	public void setGeld(int geld) {
		// TODO Auto-generated method stub
		this.geld = geld;
	}

}
