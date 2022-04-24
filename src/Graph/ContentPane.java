package Graph;

import Controller.Mouse;
import Game.*;
import Utile.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class ContentPane extends JPanel implements Observer {
    private final static int TAILLE = 70;
    private Grille grille;
    private HashMap<String,Joueur> listJoueur;

    // Fond arte
    private ImageIcon AIR = new ImageIcon("./images/AIR.png");
    private ImageIcon EAU = new ImageIcon("./images/EAU.png");
    private ImageIcon FEU = new ImageIcon("./images/FEU.png");
    private ImageIcon TERRE = new ImageIcon("./images/TERRE.png");

    private ImageIcon player1 = new ImageIcon("./images/joueur.png");
    private ImageIcon player2 = new ImageIcon("./images/joueur2.png");
    private ImageIcon player3 = new ImageIcon("./images/joueur3.png");
    private ImageIcon player4 = new ImageIcon("./images/joueur4.png");

    String[] nom_zones = {
            "Breakers Bridge",
            "Bronze Gate",
            "Cave of Embers",
            "Cave of Shadows",
            "Cliffs of Abandon",
            "Copper Gate",
            "Coral Palace",
            "Crimson Forest",
            "Dunes of Deception",
            "Gold Gate",
            "Howling Garden",
            "Iron Gate",
            "Lost Lagoon",
            "Misty Marsh",
            "Observatory",
            "Phantom Rock",
            "Silver Gate",
            "Temple of the Moon",
            "Temple of the Sun",
            "Tidal Palace",
            "Twilight Hollow",
            "Watchtower",
            "Whispering Garden"
    };
    //Image case normale :
    private ImageIcon[] casenormale = new ImageIcon[6*6];
    //Image case innondé
    private ImageIcon[] caseinondee = new ImageIcon[6*6];;
    //Image case submergé
    private ImageIcon casesub = new ImageIcon("./images/SUBMERGEE.png");

   // Image Heliport normal
    private ImageIcon heliportNormal = new ImageIcon("./images/heliN.png");
    // Image Heliport innonde
    private ImageIcon heliportInnonde = new ImageIcon("./images/heliI.png");
   // Image Heliport submerge
   private ImageIcon heliportSubmerge = new ImageIcon("./images/noHeliport.png");

    public Grille getGrille() {
        return this.grille;
    }

    public ContentPane(Grille grille){
        Random a = new Random();
        this.grille = grille;
        this.listJoueur= this.grille.getListJoueur();

        for (int i = 0; i < 6*6 ; i++) { 
            String nom = this.nom_zones[a.nextInt(this.nom_zones.length)];
            this.casenormale[i] = new ImageIcon("./images/dunes/"+nom+"@2x.png");
            this.caseinondee[i] = new ImageIcon("./images/dunes/"+nom+"_flood@2x.png");
        }

        Dimension dim = new Dimension(TAILLE * Grille.LARGEUR, TAILLE * Grille.HAUTEUR);
        this.setPreferredSize(dim);

        addMouseListener(new Mouse(this.grille));
    }

    private void dessinerGrille(Graphics g){
        for (int i = 1; i <= Grille.LARGEUR; i++) {
            for (int j = 1; j <= Grille.HAUTEUR; j++) {
                Case caseActuelle = grille.getCase(i-1, j-1);
                dessinerZone(g, caseActuelle, (i-1) * TAILLE, (j-1) * TAILLE);
               if(caseActuelle.getArtefact() != null) {
                	dessinerArtefact(g,caseActuelle,(i-1)*TAILLE,(j-1)*TAILLE);
                }
            }
        }
    }

    private void dessinerZone(Graphics g, Case c, int x, int y){
        ImageIcon im = null;
        if(this.grille.isHeliport(c)){
            switch(this.grille.getHeliport().getLevel()){
                case NORMALE:  im = heliportNormal; break;
                case INONDEE: im = heliportInnonde; break;
                case SUBMERGEE: im = heliportSubmerge; break;
            }
        }
        else{
            switch (c.getLevel()) {
                case NORMALE : im = casenormale[c.getPosx()*6 + c.getPosy()]; break ;
                case INONDEE : im = caseinondee[c.getPosx()*6 + c.getPosy()]; break ;
                case SUBMERGEE : im = casesub; break ;
            }
        }
        g.drawImage(im.getImage(), x, y, x+TAILLE, y+TAILLE, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
    }

    private void dessinerArtefact(Graphics g,Case c,int x, int y){
    	ImageIcon im = null;
    	int decalage = 0;
    	for(Artefact a: c.getArtefact()) {
	    	Element e =a.getElement();
	    	int posx,posy;
	    	posx= x+21;
	    	posy = y+21;
	    	switch(e) {
	    	case FEU: im = FEU; break;
	    	case EAU : im = EAU; break;
	    	case AIR:  im = AIR; break;
	    	case TERRE : im = TERRE;break;
	    	}      
	        g.drawImage(im.getImage(),decalage+ posx+TAILLE/3, posy+TAILLE/3, decalage+posx+2*TAILLE/3, posy+2*TAILLE/3, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
	        decalage -= 21;
    	}
    }

    private void dessinerJoueur(Graphics g, Joueur joueur, int id) {
    		Case c = joueur.getCaseJoueur();
	    	ImageIcon player = null;
	        int epsilon = 5;
	        int decalage=0;
	    	if(c.getLevel() != EtatZone.SUBMERGEE) {
                switch(id) {
                    case 0: player = player1; decalage-=2*epsilon; break;
                    case 1: player = player2;decalage=0; break;
                    case 2: player = player3;decalage-=1*epsilon; break;
                    case 3: player = player4; decalage=1*epsilon;break;
                    default: player = player1;
                }
	    	}
	    	else {
	    		player = casesub;
	    	}
	        int posX = joueur.getPosx() * TAILLE+decalage;
	        int posY = joueur.getPosy() * TAILLE+decalage;
	        g.drawImage(player.getImage(),posX+TAILLE/4, posY+TAILLE/4, posX+3*TAILLE/4, posY+3*TAILLE/4, 0,0 , player.getImage().getWidth(null), player.getImage().getHeight(null), null);
    	}

    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        dessinerGrille(g);
        for(int i=0; i<this.listJoueur.size();i++) {
        		Joueur joueur = this.listJoueur.get("P"+i);
              	dessinerJoueur(g,joueur,i);
        }
    }

    public void update() {
    	this.listJoueur = this.grille.getListJoueur();
        repaint();
    }


    public HashMap<String, Joueur> getListJoueur() {
        return listJoueur;
    }
    public int getTaille(){
        return this.TAILLE;
    }
    public Joueur getJoueur(int id) {
        Joueur joueur = this.listJoueur.get("P"+id);
        return joueur;
    }

}