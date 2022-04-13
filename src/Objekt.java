import java.awt.Graphics;

public abstract class Objekt {

	protected boolean aktiv = true;
	protected float x, y;
	protected int rx, ry;
	protected boolean istLoch = false, istMuenze = false, istStacheln = false;
	protected int powerupid = -1;
	protected boolean schaden = true;
	protected boolean wertvoll = false;

	public boolean istTeuer() {
		return wertvoll;
	}

	public int getPowerUp() {
		return powerupid;
	}

	public boolean schadet() {
		return schaden;
	}

	public boolean istStacheln() {
		return istStacheln;
	}

	public boolean istLoch() {
		return istLoch;
	}

	public boolean istMuenze() {
		return istMuenze;
	}

	public int getX() {
		return rx;
	}

	public int getY() {
		return ry;
	}

	public boolean destroy() {
		return !aktiv;
	}

	public abstract void paint(Graphics g, boolean b);

}
