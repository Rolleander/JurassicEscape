import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Spiel {

	private Maus maus = new Maus();
	private Menu menu = new Menu();
	private Steuerung tasten = new Steuerung();
	private Spieler spieler;
	private Map map;
	private boolean aktiv = false;
	private HUD hud = new HUD();

	public Spiel(Frame main) {
		main.addMouseMotionListener(maus);
		main.addMouseListener(maus);
		main.addKeyListener(tasten);
		main.addMouseWheelListener(maus);

	}

	public void neuesSpiel() {

		aktiv = true;

		spieler = new Spieler(menu.getItemsAktiv(), menu.getSelectedSkin());
		map = new Map(menu.getItemsAktiv());
		Main.setCursor(false);
		Main.sound.playSound(8, true);
		hud.setHighscores(menu.getProfil().getHighscores());
		hud.setGeld(menu.getProfil().getGeld());
	}

	public void paint(Graphics g) {
		if (menu.isOpen()) {
			menu.paint(g);
			menu.setTasten(tasten);
		} else {
			if (aktiv == false) {
				neuesSpiel();

			}

			spieler = map.paint(g, spieler);
			if (map.verbrauchtSchutzengel()) {
				menu.verbrauchtSchutzengel();
			}
			hud.setMeter(map.getMeter());
			hud.setPowerUp(spieler.getPowerUps(), spieler.getPowerTime());
			hud.setMuenzen(spieler.getMuenzen());
			hud.paint(g);

			spieler.bewegen(tasten.getTasten());

			if (map.zurueck()) {
				menu.oeffnen();
				Main.setCursor(true);
				menu.beendeSpiel(map.getMeter() / 50, spieler.getMuenzen());
				aktiv = false;
			}

		}

		maus.reset();
	}

}
