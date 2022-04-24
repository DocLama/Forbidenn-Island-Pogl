package Game.Role;
import Game.Joueur;
import Game.Case;

public class Explorateur extends Joueur {

    // Constructeur 
    public Explorateur(String pseudo, Case newCase) {
        super(pseudo, Role.EXPLORER);
        this.setCase(newCase);
    }


    /** Methods 
     * Surchage de la fonction qui gere les deplacements d'un joueur dans la classe joueur
    @param mouseX coordonnée en x du click
    @param mouseY coordonnée en y du click
    @param JX coordonnée en x du joueur
    @param JY coordonnée en y du joueur
    @return
     */
    public boolean clicUtile(int mouseX, int mouseY, int JX, int JY) {
        if((mouseX == JX + 1|mouseX == JX - 1)&(mouseY == JY)){
            return true;
        }
        if((mouseY == JY + 1|mouseY == JY - 1)&(mouseX == JX)){
            return true;
        }
        if((mouseX==JX) && (mouseY==JY)){
            return true;
        }
        //diag HAUT-DROITE
        if((mouseX==JX+1 ) && mouseY==JY+1){
            return true;
        }
        //diag HAUT-GAUCHE
        if((mouseX==JX-1 ) && mouseY==JY+1){
            return true;
        }
        //diag BAS-DROITE
        if((mouseX==JX+1 ) && mouseY==JY-1){
            return true;
        }
        //diag BAS-GAUCHE
        if((mouseX==JX-1 ) && mouseY==JY-1){
            return true;
        }
        return false;
    }

}