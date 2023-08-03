package jdemineur;

import jdemineur.metier.*;
import jdemineur.vue.*;
import iut.algo.Clavier;

public class Controleur 
{
	public static final int TAILLE = 10;

	private Plateau      metier;
	private FramePlateau vue;

	public Controleur()
	{
		this.metier = new Plateau     (Controleur.TAILLE);
		this.vue    = new FramePlateau(this);

		/* VERSION CUI */
		
		/* char   saisie;
		String choix = "[A]vertir   une case\n" +
		               "[R]etourner une case\n" +
		               "[Q]uitter";

		System.out.println(this.metier);

		do
		{
			System.out.println(choix);
			saisie = Clavier.lire_char();

			if (saisie == 'R')
			{
				System.out.print("Quelle ligne ? ");
				int lig = Clavier.lire_int();

				System.out.print("Quelle colonne ? ");
				int col = Clavier.lire_int();

				this.retourner(lig,col);
			}

			if (saisie == 'A')
			{
				System.out.print("Quelle ligne ? ");
				int lig = Clavier.lire_int();

				System.out.print("Quelle colonne ? ");
				int col = Clavier.lire_int();

				this.avertir(lig,col);
			}

			System.out.println(this.metier);

		} while (saisie != 'Q' || this.metier.gameOver() <= 0);*/
	}

	public boolean retourner(int lig, int col)
	{
		return this.metier.retourner(lig, col);
	}

	public void avertir(int lig, int col)
	{
		this.metier.avertir(lig, col);
	}

	// VUE

	public String getImage(int lig, int col)
	{
		String strCase = this.metier.getCase(lig, col).toString();

		String urlImg = "./img/";

		return strCase.equals(".") ? urlImg + "case.png" : urlImg + strCase + ".png";
	}

	public boolean gameOver()
	{
		return this.metier.gameOver() == 1;
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}
