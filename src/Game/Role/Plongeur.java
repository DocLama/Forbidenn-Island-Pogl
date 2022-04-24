package Game.Role;
import Game.Joueur;
import Game.Case;

public class Plongeur extends Joueur {
    // Constructeur 
    public Plongeur(String pseudo, Case c) {
        super(pseudo, Role.PLONGEUR);this.setCase(c);
    }

    /**
      Deplacer le pilote vers une case donnée sans tenir en compte son niveau d'eau
      @param c la nouvelle case du joueur
     */
    public void deplacer(Case c) {
        this.setCase(c); this.decreaseAction();
    }
}