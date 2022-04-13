
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.easyogg.OggClip;

public class Sound {

	Clip sounds[] = new Clip[10]; // Anzahl Sounds

	OggClip[] music = new OggClip[6];

	private boolean playMusic = true, playSounds = true;

	public Sound() {

		for (int i = 0; i < sounds.length; i++) {
			sounds[i] = loadSound("s" + i + ".wav");
		}

		/*
		 * try { music[0] = new OggClip("Music/menu.ogg"); music[1] = new
		 * OggClip("Music/battle0.ogg"); music[2] = new OggClip("Music/battle1.ogg");
		 * music[3] = new OggClip("Music/battle2.ogg"); music[4] = new
		 * OggClip("Music/battle3.ogg"); music[5] = new OggClip("Music/Credits.ogg");
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // playMusic=false;
		 */
	}

	public boolean canPlayMusic() {
		return playMusic;
	}

	public boolean canPlaySounds() {
		return playSounds;
	}

	public void switchMusic() {
		playMusic = !playMusic;
		if (playMusic == false) {
			stopMusic();
		}
	}

	public void switchSounds() {
		playSounds = !playSounds;
		if (playSounds == false) {
			killSounds();
		}
	}

	public void stopMusic() {
		for (int i = 0; i < music.length; i++) {
			music[i].stop();
		}
	}

	public void playMusic(int nr) {
		for (int i = 0; i < music.length; i++) {
			if (i != nr) {
				if (music[i] != null) {

					music[i].stop();

				}
			}
		}
		if (playMusic) {

			music[nr].loop();
		}
	}

	public void playSound(int nr, boolean clear) // nr: soundnummer clear: wenn false nur abspielen wenn sound gerade
													// nicht abgespielt wird
	{
		if (playSounds) {
			if (clear) {
				sounds[nr].setMicrosecondPosition(0);
			} else {
				if (sounds[nr].getMicrosecondPosition() >= sounds[nr].getMicrosecondLength()) {
					sounds[nr].setMicrosecondPosition(0);
				}
			}

			sounds[nr].start();
		}
	}

	public void loopSound(int nr) {
		if (playSounds) {
			// sounds[nr].setMicrosecondPosition(0);
			sounds[nr].loop(999);
		}
	}

	public void stopSound(int nr) {

		sounds[nr].stop();

	}

	public void killSounds() {
		for (int i = 0; i < sounds.length; i++) {
			if (sounds[i].isRunning()) {
				sounds[i].stop();
			}
		}

	}

	private Clip loadSound(String name) {
		Clip clip = null;
		try {
			try {
				// Open an audio input stream.
				java.net.URL url = this.getClass().getClassLoader().getResource(name);

				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioIn);
				clip.setMicrosecondPosition(0);

			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clip;
	}

}
