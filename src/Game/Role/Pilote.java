package Game.Role;
import Game.Joueur;
import Game.Case;
import Game.EtatZone;


public class Pilote extends Joueur {

    // Constructeur
    public Pilote(String pseudo,Case c) {
        super(pseudo, Role.PILOTE);
        this.setCase(c);
    }

   
    public boolean clicUtile(int mouseX, int mouseY,int JX,int JY) {
        return true;
    }


  
    public void AssecherCase(Case c) throws Exception {
    	if(AssecheUtile(c.getPosx(),c.getPosy(),this.getPosx(),this.getPosy())) {
	        if(c.getLevel()!=EtatZone.SUBMERGEE){
	            c.downLevel();
	            this.decreaseAction();
	        }
    	}
      
    }

    public boolean AssecheUtile(int mouseX, int mouseY, int JX, int JY) {
        // Un pas sur X
        if((mouseX == JX + 1|mouseX == JX - 1)&(mouseY == JY)){
            return true;
        }
        // Un pas sur Y
        if((mouseY == JY + 1|mouseY == JY - 1)&(mouseX == JX)){
            return true;
        }
        if((mouseX==JX) && (mouseY==JY)){
            return true;
        }
        return false;
    }


}
