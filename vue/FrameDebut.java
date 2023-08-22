package jdemineur.vue;

import javax.swing.JFrame;
import jdemineur.Controleur;
import java.awt.BorderLayout;

public class FrameDebut extends JFrame
{
	private Controleur ctrl;
	private PanelDebut panelD;

	public FrameDebut(Controleur ctrl)
	{
		this.setTitle             ("JDemineur by Utaa (Lanceur)");
		this.setSize              (400, 400);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.ctrl = ctrl;

		this.panelD = new PanelDebut(this.ctrl);

		this.add(this.panelD);

		this.setVisible(true);
	}
}