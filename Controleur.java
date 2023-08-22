package jdemineur;

import jdemineur.metier.*;
import jdemineur.vue.*;
import iut.algo.Clavier;

public class Controleur 
{
	public static int    HAUTEUR     = 10;
	public static int    LONGUEUR    = 10;
	public static double BOMBE_RATIO = 0.10;

	private Plateau      metier;
	private FramePlateau vue;
	private FrameDebut   config;

	public Controleur()
	{
		this.config = new FrameDebut(this);
	}

	public void initialiserJeu()
	{
		this.metier = new Plateau     (Controleur.HAUTEUR, Controleur.LONGUEUR, Controleur.BOMBE_RATIO);
		this.vue    = new FramePlateau(this);

		this.config.dispose();
	}

	public void rejouer()
	{
		this.vue.dispose();
		this.initialiserJeu();
	}

	public boolean retourner(int lig, int col)
	{
		return this.metier.retourner(lig, col);
	}

	public void avertir(int lig, int col)
	{
		this.metier.avertir(lig, col);
	}

	public int getNbDrapeauDispo()
	{
		return this.metier.getNbDrapeauDispo();
	}

	public int gameOver()
	{
		return this.metier.gameOver();
	}

	// VUE

	public String getImage(int lig, int col)
	{
		String strCase = this.metier.getCase(lig, col).toString();

		String urlImg = "./img/";

		return strCase.equals(".") ? urlImg + "case.png" : urlImg + strCase + ".png";
	}

	public void majIHM()
	{
		this.vue.majIHM();
	}

	public void quitter()
	{
		this.vue.dispose();
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}
