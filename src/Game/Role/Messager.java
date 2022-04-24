package Game.Role;
import Game.Joueur;
import Game.Case;
import Game.Cle;


public class Messager extends Joueur {
	private Joueur j = null;

	//Constructeur
    public Messager(String pseudo, Case caseJoueur) {
        super(pseudo, Role.MESSAGER);
        this.setCase(caseJoueur);
    }

    //Getters
    public Joueur getJdeplace() {
        return this.j;
    }

    //Setters
	public void setJdeplace(Joueur j) {
    	this.j =j;
    }


    public void giveKey( Cle c) {
        if (this.getCoffreFort().hadCle(c)) {
            this.getCoffreFort().retirerCle(c.getElementCle());
            j.getCoffreFort().ajouterCle(c);
            this.decreaseAction();
        }
    }


}
