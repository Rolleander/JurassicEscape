import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Steuerung implements KeyListener {

	public boolean[] tasten = new boolean[5];

	public Steuerung() {

	}

	public boolean[] getTasten() {
		return tasten;
	}

	private void tastenDruck(int keyCode, boolean b) {

		switch (keyCode) {
		case KeyEvent.VK_UP:
			tasten[0] = b;
			break;
		case KeyEvent.VK_LEFT:
			tasten[1] = b;
			break;
		case KeyEvent.VK_DOWN:
			tasten[2] = b;
			break;
		case KeyEvent.VK_RIGHT:
			tasten[3] = b;
			break;
		case KeyEvent.VK_SPACE:
			tasten[4] = b;
			break;

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		tastenDruck(e.getKeyCode(), true);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		tastenDruck(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
