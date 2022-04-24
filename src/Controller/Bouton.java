package Controller;

import Game.Grille;
import Game.Joueur;
import Utile.Jeu;
import Graph.ContentPane;
import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Bouton extends JPanel{
	
	/**************** Constructeur ****************/	
    public Bouton(Grille grille){
         // Init fin de tour
        JButton bouton = new JButton("Fin de tour"); 
        bouton.setPreferredSize(new Dimension(150,40));
        this.add(bouton);

        bouton.addActionListener(e -> {
            try {
                grille.tourCaseI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Mouse.boutonEffect();
            grille.printCoffre();
            if(grille.partiePerdue()) {
                JOptionPane.showMessageDialog(this, "Game Over, t'es nul ", "", JOptionPane.YES_NO_OPTION);
            }
        }
        );
        
    	// Keybind fin de tour
        InputMap im = bouton.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = bouton.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickMe");
        am.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
        });
        

        //Init ramasser
        JButton ramasse = new JButton("Tresor");
        ramasse.setPreferredSize(new Dimension(150,40));
        this.add(ramasse);
        
        ramasse.addActionListener(e->{
            grille.ramasseArtefact();
        });

        // Keybind ramasser

        InputMap im2 = ramasse.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am2 = ramasse.getActionMap();
        im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "clickMe");
        am2.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
        });

        

        //Bouton Rejour
        JButton rejouer = new JButton("Rejouer");
        ramasse.setPreferredSize(new Dimension(150,40));
        rejouer.setBackground(Color.YELLOW);


        rejouer.addActionListener(e -> {
            for(Frame jf : Graph.getFrames()){
                jf.dispose();
            }
            Utile.Jeu.lancerJeu();
        }
        );
        // Tuto
        JButton tuto = new JButton("tuto");
        bouton.setPreferredSize(new Dimension(150,40));
        tuto.setBackground(Color.PINK);
        tuto.addActionListener(e->{
            System.out.println("\nEnter = FIN DE TOUR");
            System.out.println("P = RAMASSER CLE/ARTEFACT");
            System.out.println("ESC = FERMER LE JEU");
            System.out.println("E = ECHANGER");
            System.out.println("P1= Bleue, P2= Rouge, P3= Vert, P4= Orange");});

        this.add(bouton);
        this.add(rejouer);
        this.add(tuto);
        setVisible(true);
        
        
    	
    }


   



}