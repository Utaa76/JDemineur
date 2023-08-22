package jdemineur.vue;

import jdemineur.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;

public class PanelDebut extends JPanel implements ActionListener
{
	private Controleur         ctrl;
	private JComboBox<Integer> ddlstRatioBomb;
	private JTextField         txtHauteur, txtLongueur;
	private JLabel             lblErreur;
	private JButton            btnLancer;

	private int                longueur;
	private int                hauteur;
	private int                ratioBombe;

	public PanelDebut(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		/* HAUT DE LA PAGE */
		JLabel lblLogo = new JLabel(new ImageIcon("./img/logo.png"));

		/* MILIEU DE LA PAGE */
		JPanel panelCentre = new JPanel(new GridLayout(3,1));

		Integer[] tabRatioBomb = new Integer[16];

		for (int i = 5, cpt = 0 ; i <= 80; i+=5, cpt++)
			tabRatioBomb[cpt] = i;

		this.ddlstRatioBomb = new JComboBox<>(tabRatioBomb);

		this.txtLongueur = new JTextField(2);
		this.txtHauteur  = new JTextField(2);

		JPanel panelTmpTop = new JPanel();
		panelTmpTop.add(new JLabel("Ratio de bombes (%)"));
		panelTmpTop.add(this.ddlstRatioBomb);

		JPanel panelTmpBot = new JPanel();
		panelTmpBot.add(new JLabel("Hauteur"));
		panelTmpBot.add(this.txtHauteur);
		panelTmpBot.add(new JLabel());
		panelTmpBot.add(new JLabel("Longueur"));
		panelTmpBot.add(this.txtLongueur);

		JPanel panelJsp = new JPanel();

		this.lblErreur = new JLabel();
		this.lblErreur.setForeground(Color.RED);
		panelJsp.add(this.lblErreur);

		panelCentre.add(panelTmpTop);
		panelCentre.add(panelTmpBot);
		panelCentre.add(panelJsp);

		/* BAS DE LA PAGE */

		this.btnLancer = new JButton("Lancer");
		this.btnLancer.addActionListener(this);

		JPanel panelSud = new JPanel();
		panelSud.add(this.btnLancer);

		this.add(lblLogo    , BorderLayout.NORTH );
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelSud   , BorderLayout.SOUTH );
	}

	public void actionPerformed(ActionEvent e)
	{
		this.lblErreur.setText("<html>");

		String hauteur  = this.txtHauteur .getText();
		String longueur = this.txtLongueur.getText();

		if (hauteur .length() < 1) this.lblErreur.setText(this.lblErreur.getText() + "Hauteur invalide <br>");
		else
		{
			try
			{
				int tailleHauteur = Integer.parseInt(hauteur);
				if (tailleHauteur > 20) this.lblErreur.setText(this.lblErreur.getText() + "Hauteur trop grande (20 max) <br>");
				else                    this.hauteur = tailleHauteur;
			}
			catch (Exception ex)
			{
				this.lblErreur.setText(this.lblErreur.getText() + "Hauteur invalide <br>");
			}
		}

		if (longueur.length() < 1) this.lblErreur.setText(this.lblErreur.getText() + "Longueur invalide ");
		else
		{
			try
			{
				int tailleLongueur = Integer.parseInt(longueur);
				if (tailleLongueur > 20) this.lblErreur.setText(this.lblErreur.getText() + "Longueur trop grande (20 max) <br>");
				else                     this.longueur = tailleLongueur;
			}
			catch (Exception ex)
			{
				this.lblErreur.setText(this.lblErreur.getText() + "Longueur invalide <br>");
			}
		}

		this.lblErreur.setText(this.lblErreur.getText() + "</html>");

		if (this.lblErreur.getText().length() <= 15)
		{
			Controleur.LONGUEUR    = this.longueur;
			Controleur.HAUTEUR     = this.hauteur;
			Controleur.BOMBE_RATIO = (double)((int)(this.ddlstRatioBomb.getSelectedItem()) / 100.0);

			this.ctrl.initialiserJeu();
		}
	}
}
