package Controller;

import Graph.OptionsView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionsActions extends MouseAdapter {

    //Attributs
    private final int TAILLE;

    //Constructeur
    public OptionsActions(){
        TAILLE = 70;
    }

    public OptionsActions(int taille){
        TAILLE = taille;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if ((mouseX >= 0 && mouseX <= TAILLE) && (mouseY >= 0 && mouseY <= TAILLE)){
            OptionsView.switchShow();
            return;
        }
        if ((mouseX >= 0 && mouseX <= TAILLE) && (mouseY >= 2 * TAILLE && mouseY <= 3 * TAILLE)){
            OptionsView.switchArte();
            return;
        }

    }
}