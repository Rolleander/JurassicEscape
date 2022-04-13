
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadAndSave {

	private SaveCoder coder = new SaveCoder();

	private String ordnerpfad = System.getProperty("user.home") + "/BRollGames/JurassicHunt";

	public LoadAndSave() {

	}

	public void save(Profil save) {

		File f = new File(ordnerpfad);
		if (f.exists() == false) {
			f.mkdirs();
		}

		String datei = "profil.txt";
		try {

			FileWriter out = new FileWriter(ordnerpfad + "/" + datei, false);
			out.write(coder.getCode(save));
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Profil load() {
		Profil p = new Profil();
		BufferedReader br;
		try {

			File file = new File(ordnerpfad);
			if (!file.exists()) {
				file.mkdirs();
			}

			br = new BufferedReader(new FileReader(ordnerpfad + "/profil.txt"));
			String s = br.readLine();
			if (s != null) {
				p = coder.getProfil(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			save(new Profil());
			e.printStackTrace();
		}
		return p;
	}

}
