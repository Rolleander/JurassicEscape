import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.net.URL;

public class Bilder {

	public static Image hintergrund, geld, title, hut, steinschild, ruby, axt, brille;
	public static Image[] pfad = new Image[8];

	public static Image[][] spieler = new Image[16][3];
	public static Image[][] dino = new Image[2][2];
	public static Image[] feuer = new Image[3];
	public static Image[] loch = new Image[4];
	public static Image[] stacheln = new Image[4];
	public static Image[][] stein = new Image[4][3];
	public static Image[] glanz = new Image[3];
	public static Image[] powerup = new Image[3];
	public static Image[] zahlen = new Image[10];

	public static Image[] aura = new Image[5];
	public static Image[] schutz = new Image[5];
	public static Image[] schild = new Image[5];
	public static Image[] treffer = new Image[5];
	public static Image[] button = new Image[3];
	public static Image[] items = new Image[50];
	public static Image[] blase = new Image[4];

	public Bilder(Frame main) {

		URL filename = getClass().getResource("back3.png");
		hintergrund = Toolkit.getDefaultToolkit().getImage(filename);

		filename = getClass().getResource("muenze.png");
		geld = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("titel.png");
		title = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("hut.png");
		hut = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("schild.png");
		steinschild = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("ruby.png");
		ruby = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("axt.png");
		axt = Toolkit.getDefaultToolkit().getImage(filename);
		filename = getClass().getResource("brille.png");
		brille = Toolkit.getDefaultToolkit().getImage(filename);

		for (int i = 0; i < pfad.length; i++) {
			filename = getClass().getResource("pfad" + i + ".png");
			pfad[i] = Toolkit.getDefaultToolkit().getImage(filename);
		}

		for (int h = 0; h < 2; h++) {
			filename = getClass().getResource("dinokopf" + (h + 1) + ".png");
			Image bild = Toolkit.getDefaultToolkit().getImage(filename);
			for (int i = 0; i < dino.length; i++) {
				dino[h][i] = main.createImage(
						new FilteredImageSource(bild.getSource(), new CropImageFilter(0, i * 221, 263, 221)));
			}
		}
		filename = getClass().getResource("zahlen.png");
		Image bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 10; i++) {
			zahlen[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32, 0, 32, 50)));
		}
		filename = getClass().getResource("loch.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 4; i++) {
			loch[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32, 0, 32, 64)));
		}
		filename = getClass().getResource("button.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 3; i++) {
			button[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 500, 0, 500, 100)));
		}

		filename = getClass().getResource("blase.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 4; i++) {
			blase[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32, 0, 32, 32)));
		}

		filename = getClass().getResource("Actor1.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int j = 0; j < 16; j++) {
			for (int h = 0; h < 3; h++) {

				spieler[j][h] = main.createImage(
						new FilteredImageSource(bild.getSource(), new CropImageFilter(h * 32 + 96 * j, 0, 32, 32)));

			}
		}

		filename = getClass().getResource("Heal6.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 5; i++) {
			aura[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 96, 0, 96, 96)));
		}

		filename = getClass().getResource("State6.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 5; i++) {
			schutz[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 96, 0, 96, 96)));
		}

		filename = getClass().getResource("Heal4.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 5; i++) {
			schild[i] = main.createImage(
					new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 96, 96 * 3, 96, 96)));
		}

		filename = getClass().getResource("Fire2.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 5; i++) {
			treffer[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 96, 0, 96, 96)));
		}

		filename = getClass().getResource("!Flame.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 3; i++) {
			feuer[i] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32, 0, 32, 32)));
		}
		for (int i = 0; i < 3; i++) {
			glanz[i] = main.createImage(
					new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32 + 288, 128, 32, 32)));
		}

		filename = getClass().getResource("!Other1.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int h = 0; h < 4; h++) {
			for (int i = 0; i < 3; i++) {
				stein[h][i] = main.createImage(
						new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32 + h * 96, 0, 32, 32)));
			}
		}
		for (int i = 0; i < 3; i++) {
			powerup[i] = main.createImage(
					new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 32 + 288, 128, 32, 32)));
		}
		for (int i = 0; i < 4; i++) {
			stacheln[i] = main.createImage(
					new FilteredImageSource(bild.getSource(), new CropImageFilter(0, 128 + i * 32, 32, 32)));
		}

		items[0] = powerup[0];
		items[1] = powerup[1];
		items[2] = powerup[2];
		filename = getClass().getResource("itembilder.png");
		bild = Toolkit.getDefaultToolkit().getImage(filename);
		for (int i = 0; i < 16; i++) {
			items[i + 3] = main
					.createImage(new FilteredImageSource(bild.getSource(), new CropImageFilter(i * 40, 0, 40, 40)));
		}
	}
}
