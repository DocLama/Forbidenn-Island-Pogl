package Graph;

import Controller.Bouton;
import Controller.Echange;
import Controller.Exit;
import Game.Grille;

import javax.swing.*;

import java.awt.*;

public class Graph extends JFrame {
    /***************** Attributs ******************/
    private JFrame frame;
    //Plateau
    private ContentPane panel;
    // background
    private RootPane rootPane;
    private static ImageIcon icon = new ImageIcon("./images/icon.jpeg");

    //Constructeur
    public Graph(Grille maGrille) {
        super();
        frame = new JFrame();
        frame.setTitle("Ile Interdite");
        frame.setIconImage(icon.getImage());
        frame.setLayout(new FlowLayout());
        rootPane = new RootPane(70);
        panel = new ContentPane(maGrille);
        maGrille.addObserver(panel);
        rootPane.setLayout(new BorderLayout());
        frame.add(rootPane);
        OptionsView optview = new OptionsView(maGrille);
        maGrille.addObserver(optview);
        rootPane.addKeyListener(new Exit());
        rootPane.addKeyListener(new Echange(maGrille));
        rootPane.setFocusable(true);
        rootPane.add(panel, BorderLayout.CENTER);
        rootPane.add(new Tour(), BorderLayout.NORTH);
        rootPane.add(optview, BorderLayout.EAST);
        rootPane.add(new Options(), BorderLayout.WEST);
        rootPane.add(new ActionRestantes(maGrille), BorderLayout.SOUTH);
        frame.add(new Bouton(maGrille));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}