package Game;
import java.util.ArrayList;


import static Game.EtatZone.*;

public class Case  {

    //Attributs 
    private boolean heliport;
    private ArrayList<Artefact> artefact;
    private EtatZone level = NORMALE;
    private int posx, posy;
    private Grille grille;
    private ArrayList<Joueur> joueurs = new ArrayList<>();


    //Constructeur 
    public Case(int posx, int posy, Grille grille){
        this.artefact = new ArrayList<>();
        this.posx = posx;
        this.posy = posy;
        this.grille = grille;
        this.heliport = false;
    }

    public Case(int x,int y){
        this.posx = x;
        this.posy = y;
    }

    //Getters
    public int getPosx(){
        return posx;
    }

    public int getPosy(){
        return posy;
    }

    public ArrayList<Artefact> getArtefact(){
        return artefact;
    }

    public EtatZone getLevel(){
        return level;
    }

    public Joueur getJoueur() throws java.lang.NullPointerException{
    	if(joueurs.size() == 0) {
    		return null;
    	}else {
    	return this.joueurs.get(0);
    	}
    }

    public Grille getGrille (){
        return this.grille;
    }

    public Boolean isHeliport(){ 
        return this.heliport;
    }

    //Setters 
    public void setHeliport() {
        this.heliport = true;
    }

    public void setArtefact(Element e) {
        this.artefact.add(new Artefact(e));
    }

    public void setSituationZone(EtatZone etat){
        this.level = etat;
    }

    public void setJoueurs(Joueur j){
        this.joueurs.add(j);
    }




    
    //Sac de sable
     
    public void downLevel() throws Exception {
        if(this.level == SUBMERGEE){
            System.out.println("Non asséchable");
        }
        else if(this.level == INONDEE) {
            this.level = NORMALE;
        }
        else {
            throw new Exception("Case sèche");
        }
    }

    //Montée des eaux
    public void upLevel(){
        switch (this.level) {
            case NORMALE : this.level = INONDEE; break;
            default: this.level = SUBMERGEE;
                break;
        }
    }

    public void removeArtefact(Artefact o){
        this.artefact.remove(o);
    }

    public void removeJoueur(Joueur j){
    	joueurs.remove(j);
    }


    public void removeAllJoueur(){

    }




}
