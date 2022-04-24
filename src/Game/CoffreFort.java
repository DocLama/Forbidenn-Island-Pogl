package Game;

import java.util.HashSet;
import java.util.Set;

public class CoffreFort  {

    //Attributs
    private Set<Cle> cles;
    private static Set<Artefact> artefacts;


    //Constructeur
    public CoffreFort() {
        cles = new HashSet<>();
        artefacts = new HashSet<>();
    }

    //Getters

    public Set<Cle> getCles() {
        return cles;
    }
    public static Set<Artefact> getArtefacts() {
        return artefacts;
    }


    //Test coffre rempli
    public static boolean endGame() {
    	boolean air = false;
    	boolean eau = false;
    	boolean feu = false;
    	boolean terre = false;
    	Element e = null;
    	for(Artefact a: artefacts) {
    		e = a.getElement();
    		switch(e) {
    		case FEU: feu = true;break;
    		case EAU: eau = true;break;
    		case TERRE: terre = true;break;
    		case AIR: air= true;break;
    		}
    	}
    	return air && feu && eau && terre;
    }

    public void ajouterCle(Cle cle) {
        this.cles.add(cle);
    }

    public static void ajouterArte(Element e) {
        CoffreFort.artefacts.add(new Artefact(e));
    }

    public boolean retirerCle(Element elementArtefact) {
        for(Cle c : cles){
            if(c.getElementCle().equals(elementArtefact)){
                cles.remove(c);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String s = "\n";
        for(Artefact a : CoffreFort.artefacts) {
            s+= " "+a.toString();
        }
        s+="\n";
        for(Cle c: this.cles) {
            s+= " "+c.toString();
        }
        return s;
    }
    
    public boolean hadCle(Cle c) {
    	for(Cle cle: this.cles) {
    		if(cle.equals(c)) {
    			return true;
    		}
    	}
    	return false;
    }

}