package Game;
import Graph.RootPane;

import java.util.*;
import java.util.List;
import Utile.Observable;
import static Game.EtatZone.*;
import static Game.Role.Role.*;
import Game.Role.*;


public class Grille extends Observable {
    // Attributs
    public static final int HAUTEUR = 6, LARGEUR = 6, nbArte = 3;
    private Case[][] tabCase;
    private HashMap<String,Joueur> listJoueur;
    private Random random = new Random();
    private int tour;
    private int nbJoueur;
    public ArrayList<Joueur>  personnages = new ArrayList<>(); 


    //Constructeur.
    public Grille(int nbJoueur) {
        tabCase = new Case[LARGEUR][HAUTEUR];
        this.nbJoueur = nbJoueur;

        this.listJoueur = new HashMap<>();

        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                tabCase[i][j] = new Case(i, j, this);
            }
        }
     
        //ajout aléatoire des personnages
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);l.add(2); l.add(3); l.add(4);l.add(5);
        for(int i=0; i<nbJoueur; i++) {
            Case caseJoueur = tabCase[random.nextInt(LARGEUR-1)][random.nextInt(HAUTEUR-1)];
            int per = l.get(random.nextInt(l.size()));
            setPersonnage("P"+i,per,caseJoueur);
            
            l.remove(l.indexOf(per));
        }
        addPersonnage();
        
        tabCase[3][5].setHeliport();
        int x,y;
        Element e = null;

        for(int i=0; i<4; i++) {
            switch(i) {
                case 0: e=Element.FEU;break;
                case 1: e=Element.AIR;break;
                case 2: e=Element.EAU;break;
                case 3: e=Element.TERRE;break;
            }
            for(int j=0; j<Grille.nbArte; j++) {
                x= this.random.nextInt(LARGEUR);
                y= this.random.nextInt(HAUTEUR);
                tabCase[x][y].setArtefact(e);

            }
        }
        return;
    }


    //Getters

    public HashMap<String,Joueur> getListJoueur(){
        return this.listJoueur;
    }

    public Joueur getJoueur(String p){
        return this.listJoueur.get(p);
    }
    public Case getHeliport(){ return tabCase[3][5];}
    public Case getCase(int x,int y) {
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                if (i==x && j==y) {
                    return tabCase[i][j];
                }
            }
        }
        System.out.println("Zone introuvable...");
        return null;
    }
    public int getTour(){
        return this.tour;
    }




    public void reset() {
    	if(!RootPane.win) {
    		 tabCase = new Case[LARGEUR][HAUTEUR];
    		 this.listJoueur = new HashMap<>();

	        for (int i = 0; i < LARGEUR ; i++) {
	            for (int j = 0; j < HAUTEUR ; j++) {
	                tabCase[i][j] = new Case(i, j, this);
	            }
	        }
    	     
    	        
    	        List<Integer> l = new ArrayList<Integer>();
                 l.add(0); l.add(1);l.add(2); l.add(3); l.add(4);l.add(5);
    	        for(int i=0; i<nbJoueur; i++) {
    	            Case caseJoueur = tabCase[random.nextInt(LARGEUR-1)][random.nextInt(HAUTEUR-1)];
    	            int per = l.get(random.nextInt(l.size()));
    	            setPersonnage("P"+i,per,caseJoueur);
    	            l.remove(l.indexOf(per));
    	        }
    	        addPersonnage();
    	        
    	        tabCase[3][5].setHeliport();
    	        int x,y;
    	        Element e = null;

    	        for(int i=0; i<4; i++) {
    	            switch(i) {
    	                case 0: e=Element.FEU;break;
    	                case 1: e=Element.AIR;break;
    	                case 2: e=Element.EAU;break;
    	                case 3: e=Element.TERRE;break;
    	            }
    	            for(int j=0; j<Grille.nbArte; j++) {
    	                x= this.random.nextInt(LARGEUR);
    	                y= this.random.nextInt(HAUTEUR);
    	                tabCase[x][y].setArtefact(e);

    	            }
    	        }
	        this.notifyObservers();
    	}
    }
    
    public List<Case> zoneNonSubmergees() {
        List<Case> result = new ArrayList<>();
        Case c;
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                c = this.tabCase[i][j];
                if (c.getLevel() != SUBMERGEE) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    private boolean allAtHeliport() {
    	for(Joueur j : this.listJoueur.values()) {
    		if(j.getCaseJoueur() != this.getHeliport()) {
    			return false;
    		}
    	}
    	return true;
    }

    public void endGame() {
    	if(CoffreFort.endGame() && this.allAtHeliport()) { 
            RootPane.win = true;
    	}
    }
    


    public void tourCaseI() throws Exception {
        List<Case> zoneNonSub = zoneNonSubmergees();
        if(!zoneNonSub.isEmpty()) {
            Case c;
            int x, y;
            for (int i = 0; i < 3; i++) {
                c = zoneNonSub.get(random.nextInt(zoneNonSub.size()));
                c.upLevel();
                x = c.getPosx();
                y = c.getPosy();
                this.tabCase[x][y] = c;
                if(c.getLevel() == EtatZone.SUBMERGEE) {
                    c.removeAllJoueur();
                }
                zoneNonSub.remove(c);

            }
        }
        Joueur joueur = this.getJoueur("P"+this.tour);
        joueur.recevoirCle();
        for(Joueur j : this.listJoueur.values()){
            if(j.getRole()==INGENIEUR){
                  j.methIngenieur((Ingenieur) j);
            }
        }
        nextTour();
        this.notifyObservers();
    }

    private void actionsRestantesReInit(){
        Joueur j=this.getJoueur("P"+this.tour);
        j.actionsRestantesReInit();
    }


    public void boutonEffect() {
        this.actionsRestantesReInit();
        this.getTour();
    }

  
    public void mousePressed(int mouseX, int mouseY,boolean leftClick, boolean rightClick) {
        Joueur j =  this.getJoueur("P"+this.tour);
		int joueurX = j.getPosx();
		int joueurY = j.getPosy();
		if(j.getActionsRestantes() > 0) {
		     if(j.clicUtile(mouseX,mouseY,joueurX,joueurY)) {
		        if(rightClick && !(mouseX==joueurX && mouseY == joueurY)) {
		            this.movePlayer(mouseX, mouseY, j);
		            this.endGame();
		
		        }else {
                    if(leftClick) {
                        try {
                        	
                                 j.AssecherCase(this.getCase(mouseX, mouseY));
                            }
                            	
                            catch(Exception ex) {
                            	System.out.println("case sèche");
                            }
		            }
		        }
		    }
		}
		if(j.getActionsRestantes() == 0 && j.getRole()==INGENIEUR && leftClick) {
			if(((Ingenieur)j).getAssseche()) {
				try{
					j.AssecherCase(this.getCase(mouseX, mouseY));
				}catch(Exception ex){
					System.out.println("case ingé prob");
				}
			}
		}
    }


    private void doubleClicDeplace(int mouseX, int mouseY, Joueur joueurDeplace, Navigateur navigateur) {
		int joueurX = joueurDeplace.getPosx();
		int joueurY = joueurDeplace.getPosy();
	     if(joueurDeplace.clicUtile(mouseX,mouseY,joueurX,joueurY)) {
	        if(!(mouseX==joueurX && mouseY == joueurY)) {
	            this.movePlayer(mouseX, mouseY, joueurDeplace);
	            this.endGame();
	            navigateur.decreaseAction();
	        }
		    
		}
    }

    public void removeJoueurDeplace() {
    	Joueur j = this.getJoueur("P"+this.tour);
    	if(j.getRole() == NAVIGATEUR  ) {
          	Navigateur navigateur = (Navigateur) j;
          	navigateur.setJdeplace(null);
    	}
    }

    
    public void mouseDoublePressed(int mouseX, int mouseY) {
        Joueur j =  this.getJoueur("P"+this.tour); 
        if(j.getRole() == MESSAGER) {
            Messager messager = (Messager) j;
            try {
                Joueur jDeplace=this.getCase(mouseX, mouseY).getJoueur();
                messager.setJdeplace(jDeplace);
                    System.out.println(messager.getPseudo() + "pick"+jDeplace.getPseudo());
            }catch(NullPointerException e) {
                    System.out.println("Personne ici");

            }
        }
        if(j.getRole() == NAVIGATEUR && j.getActionsRestantes() > 0 ) {
              Navigateur navigateur = (Navigateur) j;
              if(navigateur.getJdeplace() != null) {
                  doubleClicDeplace(mouseX,mouseY,navigateur.getJdeplace(),navigateur);
              }else {
                  try {
                      Joueur jDeplace=this.getCase(mouseX, mouseY).getJoueur();
                      if(j != jDeplace) {
                          navigateur.setJdeplace(jDeplace);
                          System.out.println(navigateur.getPseudo() + "pick"+jDeplace.getPseudo());
                      }
                  }catch(NullPointerException e) {
                      System.out.println("Personne ici");
                  }
              }
        }
    }



    public void echangeCle(Cle c) {
    	Joueur j = this.getJoueur("P"+this.tour);
    	if(j.getRole() == MESSAGER) {
    		Messager joueur = (Messager) j;
	    	Joueur autreJoueur= joueur.getJdeplace();
	    	joueur.setJdeplace(autreJoueur);
	    	if(autreJoueur != null) {
	    		joueur.giveKey(c);
	    	}
	    	this.notifyObservers();
    	}
    }


    public ArrayList<Case> getZoneAdjacentes(Case c) {
        ArrayList<Case> zonesAdjacentes = new ArrayList<>();
        if( c.getPosx()>0 && this.tabCase[c.getPosx()-1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()-1][c.getPosx()]);
        if( c.getPosx()<LARGEUR+2 &&this.tabCase[c.getPosx()+1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()+1][c.getPosx()]);
        if(c.getPosx()>0 &&this.tabCase[c.getPosx()][c.getPosx()-1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()-1]);
        if( c.getPosx()<HAUTEUR+2 && this.tabCase[c.getPosx()][c.getPosx()+1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()+1]);

        for (Case casee: zonesAdjacentes){
            if (casee.getLevel().equals(SUBMERGEE)){
                zonesAdjacentes.remove(casee);
            }
        }
        return zonesAdjacentes;
    }


    public boolean partiePerdue(){
        if(this.getHeliport().getLevel()==EtatZone.SUBMERGEE){
            return true;
        }
        for(Joueur j : this.listJoueur.values()){
            if(j.getCaseJoueur().getLevel().equals(EtatZone.SUBMERGEE) && j.getRole()!= PLONGEUR){
                removeJoueur(j);
                return true;
            }
        }
        return false;
    }

    public void removeJoueur(Joueur j){
        this.listJoueur.remove(j);
        notifyObservers();
    }

    public void removeAllJoueur(){
        this.listJoueur.clear();
        notifyObservers();
    }

        public void ajouterJoueur(Joueur jr){
        this.listJoueur.put(jr.getPseudo(),jr);
        for(int i=0;i<LARGEUR;i++){
            for(int j=0; j<HAUTEUR;j++){
                tabCase[i][j] = jr.getCaseJoueur();
            }
        }
        notifyObservers();

    }

    private void nextTour() {
        tour+=1;
        if(tour>nbJoueur-1) {
            tour=0;
        }
    }

    @Override
    public String toString() {
        String s = new String();
        Case c;
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                c = this.tabCase[i][j];
                s += c.toString() + "\t ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean isHeliport(Case c) {
        return c.isHeliport();
    }


    private void movePlayer(int x, int y, Joueur j){
        try {
        	this.getCase(j.getPosx(), j.getPosy()).removeJoueur(j);
            j.deplacer(tabCase[x][y]);
        	this.getCase(x, y).setJoueurs(j);

        }catch (Exception e) {
            System.out.println("la case n'existe pas");
        }
        notifyObservers();
    }


    public void printCoffre() {
        Joueur j = this.getJoueur("P"+this.tour);
        CoffreFort coffre = j.getCoffreFort();
        System.out.println(j.getPseudo()+" "+j.getRole()+coffre.toString());
    }


    public void ramasseArtefact() {
        Joueur j = this.listJoueur.get("P"+this.tour);
        j.ramasseArtefact();
        this.endGame();
        this.notifyObservers();
    }


    public boolean contains( Case c){
        for(int i=0;i<LARGEUR;i++){
            for(int j=0;j<HAUTEUR;j++){
                if(tabCase[i][j].equals(c)){
                    return true;
                }
            }
        }return false;
    }

    private void setPersonnage(String p,int r, Case c){
        Joueur j = null;
        switch (r){
            case 0 :   j=new Explorateur(p,c);   break;
            case 1 :   j=new Ingenieur(p,c); break;
            case 2 :   j=new Messager(p,c); break;
            case 3 :   j=new Navigateur(p,c); break;
            case 4 :   j=new Pilote(p,c); break;
            case 5 :   j=new Plongeur(p,c); break;
        }
        c.setJoueurs(j);
        this.listJoueur.put(p,j);
        System.out.println(p+" "+j.getRole());
    }

    private void addPersonnage(){
        for(Joueur j : this.listJoueur.values()){
            personnages.add(j);
        }
    }



    
}
