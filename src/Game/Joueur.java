package Game;

import java.util.ArrayList;

import java.util.Random;
import Game.Role.*;
import static Game.Cle.*;

public class Joueur {
    private String pseudo;
    private Case caseJoueur;
    private Role role;
    private boolean tour ;
    private CoffreFort coffreFort = new CoffreFort();
    private int actionsRestantes = 3;

    // Constructeurs
    public Joueur(String pseudo, Role role, Case caseJoueur, boolean tour){
        this.pseudo = pseudo;
        this.caseJoueur = caseJoueur;
        this.role = role;
        this.tour = tour;
        this.coffreFort = new CoffreFort();
    }

    
    public Joueur(String pseudo, Role role){
        this.pseudo = pseudo;
        this.role = role;
    }


    //Getters 

    public int getPosx(){
        return this.caseJoueur.getPosx();
    }

    public int getPosy(){
        return this.caseJoueur.getPosy();
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public Case getCaseJoueur(){return this.caseJoueur;}

    public Role getRole() { return this.role; }

    public boolean isTour() {
        return tour;
    }

    public CoffreFort getCoffreFort(){
        return this.coffreFort;
    }

    public int getActionsRestantes() {
        return actionsRestantes;
    }


    //Setters 

    public void actionsRestantesReInit(){
        actionsRestantes = 3;
    }
    public void decreaseAction() {
        this.actionsRestantes--;
    }
    public void setRole(Role role){
        if (role!=Role.NAVIGATEUR || role!= Role.EXPLORER || role!=Role.INGENIEUR || role!=Role.MESSAGER || role!=Role.PILOTE || role!=Role.PLONGEUR){ return;}
        this.role = role;
    }

    public void setTour(boolean tour){
        this.tour=tour;
    }

    public void setCase(Case newCase){ this.caseJoueur = newCase; }





    public void InnondeCase(Case c){   c.upLevel(); }

    
    public void AssecherCase(Case c) throws Exception {
        if(c.getLevel()!=EtatZone.SUBMERGEE){
            c.downLevel();
            this.decreaseAction();
        }
        else{
            return;
        }
    }



    public void ajouterCle(Cle nouvelleCle) {
        this.coffreFort.ajouterCle(nouvelleCle);
    }

    
    public void retirerCle(Element elementArtefact) {
        this.coffreFort.retirerCle(elementArtefact);
    }


    public int avoirToutesCles(Element element) {
        int cpt=0;
        for(Cle cle:coffreFort.getCles()   ){
            if(cle.getElementCle().equals(element))
            { cpt++;}
        }
        return cpt;
    }



    public void deplacer(Case c) {
        if(c.getLevel()!=EtatZone.SUBMERGEE){this.setCase(c); this.decreaseAction();}
        else{ return;}
    }


    public  void recevoirCle(){
        Random r = new Random();
        int cle = r.nextInt(5);
        switch(cle){
            case 1 : ajouterCle(CLEAIR); break;
            case 2 : ajouterCle(CLEEAU); break;
            case 3 : ajouterCle(CLEFEU); break;
            case 4 : ajouterCle(CLETERRE); break;
            default:
                System.out.println("No key to add");
        }
    }

    public void ramasseArtefact() {
        Case c = this.caseJoueur;
        ArrayList<Artefact> listArte = c.getArtefact();
        CoffreFort coffre = this.coffreFort;
        ArrayList<Artefact>arterecup = new ArrayList<>(); 
        for(Artefact a : listArte) {
            if(coffre.retirerCle(a.getElement())) {
            	CoffreFort.ajouterArte(a.getElement());
                arterecup.add(a);
            }
        }
        for(Artefact a : arterecup) {
            c.removeArtefact(a);
        }
    }



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
        return false;
    }


    
    public void deplacerJoueur(Case newCase,Joueur j){
        System.out.println("nothing to do");
    }

    public void addActionRestante(){
        this.actionsRestantes++;
    }

    public void methIngenieur(Ingenieur i){
        i.setAsseche();
    }

}