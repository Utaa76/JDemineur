package jdemineur.metier;

public class Plateau
{
	private int      taille;
	private Case[][] plateau;

	public Plateau(int taille)
	{
		this.taille  = taille;
		this.plateau = new Case[this.taille][this.taille];

		this.initialiser();
		this.placerBombes((int)(this.taille * this.taille * 0.10));
		this.setVoisins();
		this.calculVal();
	}

	public void initialiser()
	{
		for (int i = 0 ; i < this.taille ; i++)
		{
			for (int j = 0 ; j < this.taille ; j++)
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
				x = (int)(Math.random() * this.taille);
				y = (int)(Math.random() * this.taille);
			} while (this.plateau[x][y].isBombe());

			this.plateau[x][y].setBombe();
		}
	}

	public void setVoisins()
	{
		for (int i = 0 ; i < this.taille ; i++)
		{
			for (int j = 0 ; j < this.taille ; j++)
			{
				for (int x = -1 ; x < 2 ; x++)
				{
					for (int y = -1 ; y < 2 ; y++)
					{
						if (!(x == 0 && y == 0) && i+x >= 0 && i+x < this.taille && j+y >= 0 && j+y < this.taille && !this.plateau[i][j].isBombe())
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
		for (int i = 0 ; i < this.taille ; i++)
		{
			for (int j = 0 ; j < this.taille ; j++)
			{
				this.plateau[i][j].calculVal();
			}
		}
	}

	public void avertir(int lig, int col)
	{
		if (lig < 0 || col < 0 || lig > this.taille || col > this.taille) return;

		this.plateau[lig][col].avertir();
	}

	public boolean retourner(int lig, int col)
	{
		if (lig < 0 || col < 0 || lig > this.taille || col > this.taille) return false;

		boolean bRet = this.plateau[lig][col].retourner();

		switch (this.gameOver())
		{
			case 1 -> System.out.println("Perdu");
			case 2 -> System.out.println("Gagné");
		}

		return bRet;
	}

	public int gameOver() // A CORRIGER --> LE CAS 2 NE FONCTIONNE PAS
	{
		int iRet = 0;

		for (Case[] tabCase : this.plateau)
		{
			for (Case c : tabCase)
			{
				if      ( c.estRetourne() &&  c.isBombe()) return 1;
				else if (!c.estRetourne() && !c.isBombe()) iRet = 2; // A REVOIR
				else                                       iRet = 0;
			}
		}

		return iRet;
	}

	public String toString()
	{
		String sRet = "";
		
		for (int i = 0 ; i < this.taille ; i++)
		{
			for (int j = 0 ; j < this.taille ; j++)
			{
				sRet += this.plateau[i][j] + " ";
			}

			sRet += "\n";
		}

		return sRet;
	}

	public static void main(String[] a)
	{
		Plateau p = new Plateau(10);

		System.out.println(p);
	}
}