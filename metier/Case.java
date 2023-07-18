package jdemineur.metier;

import java.util.ArrayList;

public class Case
{
	private int             val;
	private boolean         estRetourne;
	public ArrayList<Case> alVoisins;

	public Case()
	{
		this.val         = 0;
		this.estRetourne = true;
		this.alVoisins   = new ArrayList<>();
	}

	public void addVoisin(Case c)
	{
		this.alVoisins.add(c);
	}

	public void retourner()
	{
		// TO DO : Retourner tous les voisins si tous les voisins sont à 0
		this.estRetourne = true;
	}

	public void calculVal()
	{
		for (Case c : this.alVoisins)
		{
			if (c.isBombe()) this.val++;
		}
	}

	/*             */
	/*    Bombe    */
	/*             */
	public void setBombe()
	{
		this.val = -1;
	}

	public boolean isBombe()
	{
		return this.val == -1;
	}

	public String toString()
	{
		if (!this.estRetourne)
			return ".";
		else if (this.isBombe())
			return "X";
		else
			return "" + val;
	}
}
