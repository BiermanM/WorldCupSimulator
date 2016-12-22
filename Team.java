// Created by Matthew Bierman
public class Team 
{
	int pl, mp, w, d, l, f, a, diff, p, att, mid, def = 0;
	String nm;
	
	public Team(String name, int a, int m, int d )
	{
		nm = name;
		att = a;
		mid = m;
		def = d;
	}
	
	public void setName(String name)
	{
		nm = name;
	}
	
	public String getName()
	{
		return nm;
	}
	
	public void setMatchesPlayed(int matchesPlayed)
	{
		mp += matchesPlayed;
	}
	
	public int getMatchesPlayed()
	{
		return mp;
	}
	
	public void setWDL(int win, int draw, int loss)
	{
		w += win;
		d += draw;
		l += loss;
	}
	
	public String getWDL()
	{
		if ( Integer.toString(w).length() == 2 && Integer.toString(d).length() == 2 )
			return w + "   " + d + "    " + l;
		else if ( Integer.toString(w).length() == 2 )
			return w + "   " + d + "     " + l;
		else if ( Integer.toString(d).length() == 2 )
			return w + "    " + d + "    " + l;
		
		
			return w + "    " + d + "     " + l;
	}
	
	public void setFAD(int forGoals, int againstGoals, int difference)
	{
		f += forGoals;
		a += againstGoals;
		diff += difference;
	}
	
	public String getFAD()
	{	
		if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 1 && diff > 0)
			return "     " + f + "    " + a + "        +" + diff;
		else if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 1 && diff == 0)
			return "     " + f + "    " + a + "         " + diff;
		else if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 1 && diff < 0)
			return "     " + f + "    " + a + "        " + diff;
		
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 1 && diff > 0)
			return "     " + f + "   " + a + "        +" + diff;
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 1 && diff == 0)
			return "     " + f + "   " + a + "         " + diff;
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 1 && diff < 0)
			return "     " + f + "   " + a + "        " + diff;
		
		else if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 2 && diff > 0)
			return "     " + f + "    " + a + "       +" + diff;
		else if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 2 && diff == 0)
			return "     " + f + "    " + a + "        " + diff;
		else if (Integer.toString(f).length() == 1 && Integer.toString(a).length() == 2 && diff < 0)
			return "     " + f + "    " + a + "       " + diff;
		
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 2 && diff > 0)
			return "     " + f + "   " + a + "       +" + diff;
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 2 && diff == 0)
			return "     " + f + "   " + a + "        " + diff;
		else if (Integer.toString(f).length() == 2 && Integer.toString(a).length() == 2 && diff < 0)
			return "     " + f + "   " + a + "       " + diff;
		
		else
			return "     " + f + "      " + a + "        " + diff;
	}
	
	public void setPoints(int points)
	{
		p += points;
	}
	
	public String getPoints()
	{
		if (diff > 9 || diff < -9)
			return "         " + p;
		else if (diff < 9)
			return "          " + p;
		else
			return "          " + p;
	}
	
	public int getIntPoints()
	{
		return p;
	}
}