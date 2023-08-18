package jdemineur.vue;

import javax.swing.JFrame;
import jdemineur.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur ctrl;
	private PanelPlateau panelP;

	public FramePlateau(Controleur ctrl)
	{
		this.setTitle             ("JDémineur by Utaa");
		this.setSize              (40 + Controleur.TAILLE*50, 60 + Controleur.TAILLE*50);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;

		this.panelP = new PanelPlateau(this.ctrl);

		this.add(this.panelP);

		this.setVisible(true);
	}
}
