import java.awt.Graphics;

public class Loch extends Objekt {

	private int ani = 0;

	public Loch(int x, boolean zugefroren) {
		this.x = x;
		istLoch = true;
		wertvoll = zugefroren;
	}

	@Override
	public void paint(Graphics g, boolean bewegen) {
		// TODO Auto-generated method stub
		if (wertvoll) {
			g.drawImage(Bilder.loch[3], (int) x, 222, null);
		} else {
			g.drawImage(Bilder.loch[ani / 50], (int) x, 222, null);
		}
		if (bewegen) {
			x += Map.SPEED;
			if (x > 800) {
				aktiv = false;
			}
			ani++;
			if (ani > 149) {
				ani = 0;
			}
		}
		rx = (int) x;
		ry = 222;
	}

}
