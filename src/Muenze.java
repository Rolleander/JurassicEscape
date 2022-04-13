import java.awt.Graphics;

public class Muenze extends Objekt {

	private int glanz;

	public Muenze(int x, int y, boolean wert) {
		this.x = x;
		this.y = y;
		istMuenze = true;

		wertvoll = wert;
	}

	@Override
	public void paint(Graphics g, boolean bewegen) {

		if (wertvoll) {
			g.drawImage(Bilder.ruby, (int) (x - 8), (int) (y - 12), null);
		} else {
			g.drawImage(Bilder.geld, (int) (x - 8), (int) (y - 8), null);
		}
		if (glanz / 20 < 3) {
			g.drawImage(Bilder.glanz[glanz / 20], (int) (x - 14), (int) (y - 14), null);
		}
		if (bewegen) {
			x += Map.SPEED;
			if (x > 800) {
				aktiv = false;
			}

			glanz++;
			if (glanz > 150) {
				glanz = 0;
			}

		}

		rx = (int) x;
		ry = (int) y;

	}

}