package Game.Role;
import Game.Joueur;
import Game.Case;
import Game.EtatZone;

public class Ingenieur extends Joueur {
    //Attributs
    private boolean asseche = false;

    // Constructeurs
    public Ingenieur(String pseudo,Case c) {
        super(pseudo, Role.INGENIEUR);this.setCase(c);
    }

    //Getters
    public boolean getAssseche() {
    	return this.asseche;
    }

    //Setters

    public void setAsseche() {
    	this.asseche = false;
    }

     //Permet d'inverser le boolean d'asseche
    public void switchAsseche(){
    	this.asseche = !this.asseche;
    }

   
    public void AssecherCase(Case c) throws Exception {
        if(c.getLevel()!=EtatZone.SUBMERGEE ) {
           c.downLevel();         
           if(!this.asseche) {
    		   this.decreaseAction();
           }
           switchAsseche();
        }
    }

    
}
