
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

import java.net.URL;
import java.util.Timer;

public class Main extends Frame {

	/**
	 * 
	 */

	// Display
	public static double xf, yf;
	public static int resolution[] = new int[2];

	static Main main;

	private Font errorfont = new Font("Arial", 1, 15);
	private Bilder bilder;
	private Spiel spiel;
	private static BufferedImage cursor = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	public static Sound sound = new Sound();

	public static void main(String[] args) {

		main = new Main();
		//main.setUndecorated(true);

		main.setVisible(true);
		main.setEnabled(true);
		main.setResizable(false);

		main.setTitle("Jurassic Hunt");
		main.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		resolution[0] = 800;
		resolution[1] = 500;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		 main.setSize(dimension);
		//main.setSize(resolution[0], resolution[1]);
		main.setLocation((dimension.width / 2) - (resolution[0] / 2),
				(dimension.height / 2) - (resolution[1] / 2) - 50);
		main.setLocation(0, 0);

		xf = dimension.width / (double) resolution[0];
		yf = dimension.height / (double) resolution[1];

		screen = new BufferedImage(resolution[0], resolution[1], BufferedImage.TYPE_INT_RGB);
		main.setMaximumSize(dimension);
		main.setMinimumSize(new Dimension(resolution[0], resolution[1]));
		main.init();

	}

	public static void setCursor(boolean sichtbar) {
		if (sichtbar) {
			main.setCursor(DEFAULT_CURSOR);
		} else {
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(16, 16), "Custom Cursor");
			main.setCursor(c);
		}
	}

	public void init() {
		spiel = new Spiel(main);
		bilder = new Bilder(main);

		main.setIconImage(Bilder.dino[1][0]);

		start();

		run();
	}

	private static BufferedImage screen;

	public void paint(Graphics p) {

		if (screen != null) {
			Graphics g = screen.getGraphics();
			if (spiel != null) {
				spiel.paint(g);

			}

			// p.dispose();
			p.setColor(Color.BLACK);
			p.fillRect(0, 0, main.getWidth(), 32);
			p.drawImage(screen, 0, 32, main.getWidth(), main.getHeight(), null);

		}
	}

	private Image dbImage;
	private Graphics dbg;

	public void update(Graphics g) {
		// DOUBLE BUFFER gegen Bildschirmflackern
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width, this.getSize().height);
			dbg = dbImage.getGraphics();
		}
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		dbg.setColor(getForeground());
		paint(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void start() {
		// Thread starten
		Thread th = new Thread();
		th.start();
	}

	public void stop() {
		// Wird ausgeführt wenn Thread gestoppt wird
	}

	public void destroy() {
		// Wird ausgeführt wenn Thread geschlossen wird (Programmende)
		// Hier müsst ihr alle Lieder stoppen, falls ihr welche verwendet
		// damit sie im Browser nicht weiterlaufen
	}

	public void run() {
		java.util.Timer timer = new Timer();
		timer.schedule(new Loop(), 0, 10);

	}

	private class Loop extends java.util.TimerTask {

		public void run()
		// this becomes the loop
		{
			repaint();
		}
	}

}
