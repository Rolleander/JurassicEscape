import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Maus implements MouseMotionListener, MouseListener, MouseWheelListener {

	private static boolean pressed;
	public static int mx, my;
	public static int dreh;

	public Maus() {

	}

	public static int getX() {
		return mx;
	}

	public static int getY() {
		return my - 26;
	}

	public static boolean gedruecktMaus() {
		boolean b = pressed;
		pressed = false;
		return b;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = (int) (e.getX() / Main.xf);
		my = (int) (e.getY() / Main.yf);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = (int) (e.getX() / Main.xf);
		my = (int) (e.getY() / Main.yf);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		// TODO Auto-generated method stub
		pressed = false;
		dreh = 0;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		dreh = e.getWheelRotation();
	}

}
