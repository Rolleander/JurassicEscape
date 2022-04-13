import java.awt.Graphics;

public class PowerUp extends Objekt {

	public PowerUp() {
		x = -32;
		y = 150;
		powerupid = (int) (Math.random() * 3 + 1) - 1;
	}

	@Override
	public void paint(Graphics g, boolean bewegen) {
		// TODO Auto-generated method stub

		g.drawImage(Bilder.powerup[powerupid], (int) (x - 16), (int) (y - 16), null);

		if (bewegen) {
			x += Map.SPEED;
		}
		rx = (int) x;
		ry = (int) y;

	}

}
