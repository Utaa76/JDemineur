package jdemineur.metier;

import java.util.ArrayList;

public class Case
{
	private int             val;
	private boolean         estRetourne;
	private boolean         estAverti;
	private ArrayList<Case> alVoisins;

	public Case()
	{
		this.val         = 0;
		this.estRetourne = false;
		this.estAverti   = false;
		this.alVoisins   = new ArrayList<>();
	}

	public void addVoisin(Case c)
	{
		this.alVoisins.add(c);
	}

	public boolean retourner()
	{
		if (this.estRetourne) return false;

		this.estRetourne = true;

		if (this.val == 0)
		{

			for (Case c : this.alVoisins)
				if (c.val == 0) c.retourner();
		}
		
		return true;
	}

	public void avertir()
	{
		if (!this.estRetourne) this.estAverti = !this.estAverti;
	}

	public boolean estRetourne()
	{
		return this.estRetourne;
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
		if (this.estAverti)
			return "!";
		else if (!this.estRetourne)
			return ".";
		else if (this.isBombe())
			return "X";
		else
			return "" + val;
	}
}
