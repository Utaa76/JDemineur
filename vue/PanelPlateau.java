package jdemineur.vue;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import jdemineur.Controleur;

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;
	private Integer[]  posCase;

	public PanelPlateau(Controleur ctrl)
	{
		this.posCase    =  null;
		this.ctrl       = ctrl;

		GereSouris gs = new GereSouris();

		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		ImageIcon imgCase;

		for (int i = 0 ; i < Controleur.LONGUEUR ; i++)
			for (int j = 0 ; j < Controleur.HAUTEUR ; j++)
			{
				imgCase = new ImageIcon(this.ctrl.getImage(i, j));
				imgCase.paintIcon(this, g, 10+(i*50), 10+(j*50));
			}

		g.setColor(Color.WHITE);
		
		if (this.ctrl.gameOver() == 2) // VICTOIRE
		{
			ImageIcon gifVictoire = new ImageIcon("./img/feu_dartifice.gif");
			int x = (Controleur.LONGUEUR*51) / 2 - (gifVictoire.getIconWidth () / 2) + 5;
			int y = (Controleur.HAUTEUR*51) / 2 - (gifVictoire.getIconHeight() / 2) + 5;
			
			gifVictoire.paintIcon(this, g, x, y);
		}

		if (this.ctrl.gameOver() == 1) // DEFAITE
		{
			ImageIcon gifDefaite = new ImageIcon("./img/explosion2.gif");
			int x = (this.posCase[0]) * 50 - (gifDefaite.getIconWidth () / 2 - 40);
			int y = (this.posCase[1]) * 50 - (gifDefaite.getIconHeight() / 2 - 25);
			
			gifDefaite.paintIcon(this, g, x, y);
		}
	}

	public class GereSouris extends MouseAdapter
	{
		private Rectangle[][] ensHitbox;
		private boolean mouseClickedEnCours;

		public GereSouris()
		{
			this.ensHitbox = new Rectangle[Controleur.LONGUEUR][Controleur.HAUTEUR];

			for (int i = 0 ; i < Controleur.LONGUEUR ; i++)
				for (int j = 0 ; j < Controleur.HAUTEUR ; j++)
					this.ensHitbox[i][j] = new Rectangle(10+(i*50), 10+(j*50), 50, 50);
		}

		public void mouseClicked(MouseEvent e)
		{
			this.mouseClickedEnCours = true;

			int posX = e.getX();
			int posY = e.getY();

			Integer[] posCase = this.getCoordCase(posX, posY);

			if (posCase != null)
			{
				PanelPlateau.this.posCase = posCase;

				if (e.getButton() == MouseEvent.BUTTON1)
					PanelPlateau.this.ctrl.retourner(posCase[0], posCase[1]);
				if (e.getButton() == MouseEvent.BUTTON3)
					PanelPlateau.this.ctrl.avertir  (posCase[0], posCase[1]);
			}
			
			PanelPlateau.this.ctrl.majIHM();
			
			int etatPartie = PanelPlateau.this.ctrl.gameOver();
			if (etatPartie != 0)
			{
				String resultat = etatPartie == 1 ? "Défaite. Vous avez perdu." : "Victoire. Vous avez gagné !";

				Object[] options = {"Rejouer", "Reconfigurer", "Quitter"};
				int choix = JOptionPane.showOptionDialog
				(
					null,
					resultat,
					"Fin de partie",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[0]
				);

				switch (choix)
				{
					case 0 -> PanelPlateau.this.ctrl.rejouer();
					case 1 ->
					{
						PanelPlateau.this.ctrl.quitter();
						new Controleur();
					}
					case 2 -> PanelPlateau.this.ctrl.quitter();
				}
			}

			this.mouseClickedEnCours = false;
		}

		public void mouseMoved(MouseEvent e)
		{
			if (!this.mouseClickedEnCours)
			{
				int posX = e.getX();
				int posY = e.getY();
	
				Integer[] posCase = this.getCoordCase(posX, posY);
	
				if (posCase != null && PanelPlateau.this.ctrl.gameOver() == 0)
					PanelPlateau.this.setCursor(new Cursor(Cursor.HAND_CURSOR   ));
				else
					PanelPlateau.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	
				PanelPlateau.this.repaint();
			}
		}

		public Integer[] getCoordCase(int posX, int posY)
		{
			Integer[] posCase = new Integer[2];

			for (int i = 0 ; i < Controleur.LONGUEUR ; i++)
			{
				for (int j = 0 ; j < Controleur.HAUTEUR ; j++)
				{
					if (this.ensHitbox[i][j].contains(posX, posY))
					{
						posCase[0] = i;
						posCase[1] = j;

						return posCase;
					}
				}
			}

			return null;
		}
	}
}
