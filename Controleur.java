package jdemineur;

import jdemineur.metier.*;
import iut.algo.Clavier;

public class Controleur 
{
	Plateau metier;

	public Controleur()
	{
		this.metier = new Plateau(5);

		char   saisie;
		String choix = "[A]vertir   une case\n" +
		               "[R]etourner une case\n" +
		               "[Qjuitter";

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

		} while (saisie != 'Q' || this.metier.gameOver() <= 0); // A REVOIR
	}

	public boolean retourner(int lig, int col)
	{
		return this.metier.retourner(lig, col);
	}

	public void avertir(int lig, int col)
	{
		this.metier.avertir(lig, col);
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}
