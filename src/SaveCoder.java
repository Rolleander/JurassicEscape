
import java.awt.Dimension;
import java.awt.Image;

public class SaveCoder {

	private SaveDecoder decoder = new SaveDecoder();

	public SaveCoder() {

	}

	public String getCode(Profil profil) {
		String code = "";
		code += profil.getSpielzeit() + "$";
		code += profil.getGeld() + "$";

		for (int i = 0; i < 10; i++) {

			code += profil.getHighscores()[i] + "$";

		}
		for (int i = 0; i < 50; i++) {

			code += profil.getItems()[i] + "$";

		}

		code = decoder.encode(code);

		return code;
	}

	public Profil getProfil(String code) {
		Profil profil = new Profil();

		code = decoder.decode(code);
		String[] data = code.split("\\$");

		profil.setSpielzeit(Integer.parseInt(data[0]));
		profil.setGeld(Integer.parseInt(data[1]));

		for (int i = 0; i < 10; i++) {
			profil.setHighscore(i, Integer.parseInt(data[2 + i]));

		}

		for (int i = 0; i < 50; i++) {
			profil.setItem(i, Integer.parseInt(data[12 + i]));

		}

		return profil;
	}

}
