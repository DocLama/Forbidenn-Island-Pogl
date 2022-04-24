package Graph;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import Game.Grille;
import javax.swing.*;


public class Menu extends JPanel{
	    private static int TAILLE;
	    private ImageIcon bg = new ImageIcon("./images/Menu.PNG");

	    public Menu(int taille){
	        TAILLE = taille;
	        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3));
	        this.setPreferredSize(dim);
	    };

	    @Override
	    public void paintComponent(Graphics g){	
	        super.repaint();
	        g.drawImage(bg.getImage(), 0, 0, TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3), 0, 0,bg.getImage().getWidth(null), bg.getImage().getHeight(null), null);
	    }
}