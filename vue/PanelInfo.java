package jdemineur.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jdemineur.Controleur;

public class PanelInfo extends JPanel
{
	private Controleur ctrl;

	public PanelInfo(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setPreferredSize(new Dimension(0, 35));
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawString((Controleur.HAUTEUR * Controleur.LONGUEUR) + " cases      " + (Controleur.BOMBE_RATIO * 100) + "% de bombes", 10, 20);

		g.setFont(new Font("", Font.BOLD, 15));

		g.drawString("" + this.ctrl.getNbDrapeauDispo(), this.getWidth() -55, 20);

		ImageIcon imgBombe;

		imgBombe = new ImageIcon("./img/drapeau.png");
		imgBombe.paintIcon(this, g, this.getWidth() - 50, -10);
	}
}
