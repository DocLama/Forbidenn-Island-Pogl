package Game.Role;
import Game.Joueur;
import Game.Case;

public class Plongeur extends Joueur {
    // Constructeur 
    public Plongeur(String pseudo, Case c) {
        super(pseudo, Role.PLONGEUR);this.setCase(c);
    }

    
    public void deplacer(Case c) {
        this.setCase(c); this.decreaseAction();
    }
}
