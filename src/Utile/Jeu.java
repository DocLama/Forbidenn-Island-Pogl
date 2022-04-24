package Utile;
import Game.*;
import Graph.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;


public class Jeu {
	public static int nbJoueur = 0;

	public static void main(String[] args){
	 	ClassFrame cl = new ClassFrame("Entrer");
	 }
	public static void lancerJeu() {
		   Grille grille = new Grille(nbJoueur);
		   Graph Graph = new Graph(grille);
		   music();
	}
	public static void music(){

		try{
			File mymusic = new File("THEME.wav");
			if(mymusic.exists()){
				AudioInputStream ai = AudioSystem.getAudioInputStream(mymusic);
				Clip clip = AudioSystem.getClip();
				clip.open(ai);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else{
				System.out.println("can't find file");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}