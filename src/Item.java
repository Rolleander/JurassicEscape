import java.awt.Image;

public class Item {

	public int preis;
	public String name, beschreibung;
	public boolean mehrmals = false;

	public Item(String name, String beschreibung, int preis) {
		this.preis = preis;
		this.name = name;
		this.beschreibung = beschreibung;

	}

}
