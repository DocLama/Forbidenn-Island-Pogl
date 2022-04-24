package Controller;

import Game.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
  
	//Attributs
    private static Grille grille;
    private final int TAILLE = 70 ; 
    private static int actionsRestantes = 3;
    private static int tour=0;

    //Constructeur
    public Mouse(Grille g){
        super();
        this.grille = g;
    }
    
    
    //Getters
    public static int getActionsRestantes() {
        return grille.getJoueur("P"+tour).getActionsRestantes();
    }

    public static int getTour(){
        return tour;
    }

    
    
    public void mousePressed(MouseEvent e){
        int mouseX = e.getX()/this.TAILLE;
        int mouseY = e.getY()/this.TAILLE;
        if(e.getClickCount() >=2) {
        	if( SwingUtilities.isLeftMouseButton(e)) {
        		this.grille.mouseDoublePressed(mouseX, mouseY);
        	}
        	if( SwingUtilities.isRightMouseButton(e)) {
        		this.grille.removeJoueurDeplace();
        	}
        }else {
        	this.grille.mousePressed(mouseX, mouseY,SwingUtilities.isLeftMouseButton(e), SwingUtilities.isRightMouseButton(e));
        }
    }

    public static void nextTour() {
        tour = grille.getTour();
    }
  
    public static void boutonEffect() {
    	grille.boutonEffect();
    	nextTour();
    }
    public static void actionsRestantesReInit(){
        actionsRestantes = 3;
    }

}