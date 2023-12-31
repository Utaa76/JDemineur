package jdemineur.metier;

public class Plateau
{
	private int      hauteur, longueur;
	private Case[][] plateau;
	private int      nbBombes;

	public Plateau(int hauteur, int longueur, double bombRatio)
	{
		this.hauteur  = hauteur;
		this.longueur = longueur;
		this.plateau  = new Case[this.longueur][this.hauteur];
		this.nbBombes = (int)(this.hauteur * this.longueur * bombRatio);

		this.initialiser();
		this.placerBombes(this.nbBombes);
		this.setVoisins();
		this.calculVal();
	}

	public void initialiser()
	{
		for (int i = 0 ; i < this.longueur ; i++)
		{
			for (int j = 0 ; j < this.hauteur ; j++)
			{
				this.plateau[i][j] = new Case();
			}
		}
	}

	public void placerBombes(int nbBombes)
	{
		int x, y;
		for (int i = 0 ; i < nbBombes ; i++)
		{
			do
			{
				x = (int)(Math.random() * this.longueur);
				y = (int)(Math.random() * this.hauteur);
			} while (this.plateau[x][y].isBombe());

			this.plateau[x][y].setBombe();
		}
	}

	public void setVoisins()
	{
		for (int i = 0 ; i < this.longueur ; i++)
		{
			for (int j = 0 ; j < this.hauteur ; j++)
			{
				for (int x = -1 ; x < 2 ; x++)
				{
					for (int y = -1 ; y < 2 ; y++)
					{
						if (!(x == 0 && y == 0) && i+x >= 0 && i+x < this.longueur && j+y >= 0 && j+y < this.hauteur && !this.plateau[i][j].isBombe())
						{
							this.plateau[i][j].addVoisin(this.plateau[i+x][j+y]);
						}
					}
				}
			}
		}
	}

	public void calculVal()
	{
		for (int i = 0 ; i < this.longueur ; i++)
		{
			for (int j = 0 ; j < this.hauteur ; j++)
			{
				this.plateau[i][j].calculVal();
			}
		}
	}

	public void avertir(int lig, int col)
	{
		if (lig < 0 || col < 0 || lig > this.longueur || col > this.hauteur || this.gameOver() != 0) return;

		this.plateau[lig][col].avertir();
	}

	public boolean retourner(int lig, int col)
	{
		if (lig < 0 || col < 0 || lig > this.longueur || col > this.hauteur || this.gameOver() != 0) return false;

		boolean bRet = this.plateau[lig][col].retourner();

		return bRet;
	}

	public int gameOver()
	{
		int iRet = 2; // PARTIE TERMINEE (cas par défaut)

		for (Case[] tabCase : this.plateau)
		{
			for (Case c : tabCase)
			{
				if      ( c.estRetourne() &&  c.isBombe()) return 1; // LOSE
				else if (!c.estRetourne() && !c.isBombe()) iRet = 0; // PARTIE NON TERMINEE
			}
		}

		return iRet;
	}

	public Case getCase(int lig, int col)
	{
		return this.plateau[lig][col];
	}

	public int getNbDrapeauDispo()
	{
		int nbDrapeauDispo = this.nbBombes;

		for (Case[] tabCase : this.plateau)
		{
			for (Case c : tabCase)
			{
				if (c.estAverti()) nbDrapeauDispo--;
			}
		}

		return nbDrapeauDispo;
	}

	public String toString()
	{
		String sRet = "";
		
		for (int i = 0 ; i < this.hauteur ; i++)
		{
			for (int j = 0 ; j < this.longueur ; j++)
			{
				sRet += this.plateau[i][j] + " ";
			}

			sRet += "\n";
		}

		return sRet;
	}
}