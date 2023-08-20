package jdemineur.vue;

import javax.swing.JFrame;
import jdemineur.Controleur;
import java.awt.BorderLayout;

public class FramePlateau extends JFrame
{
	private Controleur ctrl;
	private PanelPlateau panelP;
	private PanelInfo    panelI;

	public FramePlateau(Controleur ctrl)
	{
		this.setTitle             ("JDémineur by Utaa");
		this.setSize              (40 + Controleur.TAILLE*50, 80 + Controleur.TAILLE*50);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;

		this.panelP = new PanelPlateau(this.ctrl);
		this.panelI = new PanelInfo   (this.ctrl);

		this.add(this.panelP, BorderLayout.CENTER);
		this.add(this.panelI, BorderLayout.SOUTH );

		this.setVisible(true);
	}

	public void majIHM()
	{
		this.panelP.repaint();
		this.panelI.repaint();
	}
}
