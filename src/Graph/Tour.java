package Graph;

import Controller.Mouse;
import Game.Grille;

import javax.swing.*;
import java.awt.*;

public class Tour extends JPanel {
    //Attributs 
    private ImageIcon bandrol = new ImageIcon("./images/toi.png");
    public static int TAILLE = 70;

    //Constructeur
    public Tour(){
        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), 2 * TAILLE);
        this.setPreferredSize(dim);
    }

   
    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        int bordure = 10;
        g.drawImage(bandrol.getImage(), 2 * TAILLE, bordure, TAILLE * 8, 2 * TAILLE - bordure, 0, 0,bandrol.getImage().getWidth(null), bandrol.getImage().getHeight(null), null);
    }
}