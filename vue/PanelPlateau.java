package jdemineur.vue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import jdemineur.Controleur;

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;

		GereSouris gs = new GereSouris();

		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		ImageIcon imgCase;

		for (int i = 0 ; i < Controleur.TAILLE ; i++)
			for (int j = 0 ; j < Controleur.TAILLE ; j++)
			{
				imgCase = new ImageIcon(this.ctrl.getImage(i, j));
				imgCase.paintIcon(this, g, 10+(i*50), 10+(j*50));
			}
	}

	public class GereSouris extends MouseAdapter
	{
		private Rectangle[][] ensHitbox;

		public GereSouris()
		{
			this.ensHitbox = new Rectangle[Controleur.TAILLE][Controleur.TAILLE];

			for (int i = 0 ; i < Controleur.TAILLE ; i++)
				for (int j = 0 ; j < Controleur.TAILLE ; j++)
					this.ensHitbox[i][j] = new Rectangle(10+(i*50), 10+(j*50), 50, 50);
		}

		public void mouseClicked(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer[] posCase = this.getCoordCase(posX, posY);

			if (posCase != null)
			{
				if (e.getButton() == MouseEvent.BUTTON1)
					PanelPlateau.this.ctrl.retourner(posCase[0], posCase[1]);
				if (e.getButton() == MouseEvent.BUTTON3)
					PanelPlateau.this.ctrl.avertir  (posCase[0], posCase[1]);
			}

			PanelPlateau.this.repaint();
		}

		public void mouseMoved(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer[] posCase = this.getCoordCase(posX, posY);

			if (posCase != null && !PanelPlateau.this.ctrl.gameOver())
				PanelPlateau.this.setCursor(new Cursor(Cursor.HAND_CURSOR   ));
			else
				PanelPlateau.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			PanelPlateau.this.repaint();
		}

		public Integer[] getCoordCase(int posX, int posY)
		{
			Integer[] posCase = new Integer[2];

			for (int i = 0 ; i < Controleur.TAILLE ; i++)
			{
				for (int j = 0 ; j < Controleur.TAILLE ; j++)
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
