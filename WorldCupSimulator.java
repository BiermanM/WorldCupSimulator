// Created by Matthew Bierman
import java.util.*;
public class WorldCupSimulator 
{
	// Creates blank teams
	private Team fail = new Team("Blank", 0, 0, 0);
	
	// Arrays for each round, blank teams are replaced by winners of previous rounds
	// Group Stage Teams
	private Team[][] allTeamsGroups = new Team[8][4];
	// Round of Sixteen Teams
	private Team[] arrTeamsF = new Team[16];
	// Quarterfinal Teams
	private Team[] arrTeamQF = {fail, fail, fail, fail, fail, fail, fail, fail};
	// Semifinal Teams
	private Team[] arrTeamSF = {fail, fail, fail, fail};
	// Final Teams
	private Team[] arrTeamFinal = {fail, fail};
	
	// Sets the number of the cell of the team in
	// allTeams and arranges them by points, greatest
	// to least
	private int[][] newArr = new int[8][4];
		
	// Contains the number of points each team has,
	// from greatest to least
	private int[][] info = new int[8][4]; 
		
	// Contains each of the places, with an extra
	// cell which allows for switching teams
	private String[][] places = new String[8][4];
	
	// shuffles an array of Team
	public void shuffle(Team[] arr)
	 {
		Random rand = new Random();
	    for (int i = arr.length - 1; i > 0; i--)
	    {
	       int index = rand.nextInt(i + 1);
	      
	       Team temp = arr[index];
	       arr[index] = arr[i];
	       arr[i] = temp;
	    }
	 }
	
	// Puts all teams into an array, shuffles them, and puts them into 8 groups for the group stage
	public WorldCupSimulator(Team A, Team B, Team C, Team D, Team E, Team F, Team G, Team H, 
							 Team I, Team J, Team K, Team L, Team M, Team N, Team O, Team P, 
							 Team Q, Team R, Team S, Team T, Team U, Team V, Team W, Team X, 
							 Team Y, Team Z, Team AA, Team AB, Team AC, Team AD, Team AE, Team AF)
	{
		Team[] arr = {A, B,  C,  D,  E,  F,  G,  H, 
				  I,  J,  K,  L,  M,  N,  O,  P, 
				  Q,  R,  S,  T,  U,  V,  W,  X, 
				  Y,  Z,  AA,  AB,  AC,  AD,  AE,  AF};
		
		shuffle(arr);
		
		allTeamsGroups[0][0] = arr[0];
		allTeamsGroups[0][1] = arr[1];
		allTeamsGroups[0][2] = arr[2];
		allTeamsGroups[0][3] = arr[3];
		
		allTeamsGroups[1][0] = arr[4];
		allTeamsGroups[1][1] = arr[5];
		allTeamsGroups[1][2] = arr[6];
		allTeamsGroups[1][3] = arr[7];
		
		allTeamsGroups[2][0] = arr[8];
		allTeamsGroups[2][1] = arr[9];
		allTeamsGroups[2][2] = arr[10];
		allTeamsGroups[2][3] = arr[11];
		
		allTeamsGroups[3][0] = arr[12];
		allTeamsGroups[3][1] = arr[13];
		allTeamsGroups[3][2] = arr[14];
		allTeamsGroups[3][3] = arr[15];
		
		allTeamsGroups[4][0] = arr[16];
		allTeamsGroups[4][1] = arr[17];
		allTeamsGroups[4][2] = arr[18];
		allTeamsGroups[4][3] = arr[19];
		
		allTeamsGroups[5][0] = arr[20];
		allTeamsGroups[5][1] = arr[21];
		allTeamsGroups[5][2] = arr[22];
		allTeamsGroups[5][3] = arr[23];
		
		allTeamsGroups[6][0] = arr[24];
		allTeamsGroups[6][1] = arr[25];
		allTeamsGroups[6][2] = arr[26];
		allTeamsGroups[6][3] = arr[27];
		
		allTeamsGroups[7][0] = arr[28];
		allTeamsGroups[7][1] = arr[29];
		allTeamsGroups[7][2] = arr[30];
		allTeamsGroups[7][3] = arr[31];
	}
	
	// Generates a random number for how many goals the team has scored,
	// greater likelihood to score 3 or less goals
	public int calculateGoals()
	{
		Random random = new Random(); 
		int x = random.nextInt(10);
		if (x > 3)
		{
			x = random.nextInt(10);
			if (x > 3)
			{
				x = random.nextInt(10);
				if (x > 3)
				{
					x = random.nextInt(10);
					if (x > 4)
					{
						x = random.nextInt(10);
						if (x > 5)
						{
							x = random.nextInt(10);
							if (x > 6)
							{
								x = random.nextInt(10);
								if (x > 7)
								{
									x = random.nextInt(10);
									if (x > 8)
									{
										x = random.nextInt(10);
										if (x > 9)
										{
											x = random.nextInt(10);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return x;
	}
	
	// Sets two teams against each other
	public void games(int x, int y, int group)
		{
			// Probabilities based on FIFA 15 ratings
			int totX = 0;
			int totY = 0;
			
			// Team A's attackers compared to Team B's defenders
			if (allTeamsGroups[group][x].att > allTeamsGroups[group][y].def)
			{
				if (Math.random() >= .2)
				{
					totX += 1;
					if (Math.random() >= .75 && (allTeamsGroups[group][x].att - allTeamsGroups[group][y].def) > 5)
						totX += 1;
				}
			}
			else if (allTeamsGroups[group][x].att < allTeamsGroups[group][y].def)
			{
				if (Math.random() >= .2)
				{
					totY += 1;
					if (Math.random() >= .1 && (allTeamsGroups[group][y].def - allTeamsGroups[group][x].att) > 5)
						totY += 1;
				}
			}
				
			// Team A's midfielders compared to Team B's midfielders
			if (allTeamsGroups[group][x].mid < allTeamsGroups[group][y].mid)
				 totY += 1;		
			else if (allTeamsGroups[group][x].mid > allTeamsGroups[group][y].mid)
				 totX += 1;
				
			// Team A's defenders compared to Team B's attackers
			if (allTeamsGroups[group][x].def > allTeamsGroups[group][y].att)
			{
				totY += 1;
				if (Math.random() > .75 && (allTeamsGroups[group][y].att - allTeamsGroups[group][x].def) > 5)
					totY += 1;
			}
			else if (allTeamsGroups[group][x].def < allTeamsGroups[group][y].att) 
			{
				if (Math.random() >= .2)
				{
					totY += 1;
					if (Math.random() >= .75 && (allTeamsGroups[group][x].def - allTeamsGroups[group][y].att) > 5)
						totY += 1;
				}
			}	
					
			// Team A vs. Team B
			allTeamsGroups[group][x].setMatchesPlayed(1);
			allTeamsGroups[group][y].setMatchesPlayed(1);
			
			int aGoals = calculateGoals();
			int bGoals = calculateGoals();
			
			if (totX > totY)
			{
				aGoals += 1;
				if (Math.random() >= .05)
					aGoals += 1;
			}
			
			else if (totY > totX)
			{
				bGoals += 1;
				if (Math.random() >= .05)
					bGoals += 1;
			}
			
			allTeamsGroups[group][x].setFAD(aGoals, bGoals, (aGoals - bGoals));
			allTeamsGroups[group][y].setFAD(bGoals, aGoals, (bGoals - aGoals));
			
			// if Team A wins 
			if (aGoals > bGoals)
			{
				allTeamsGroups[group][x].setWDL(1, 0, 0);
				allTeamsGroups[group][y].setWDL(0, 0, 1);
				allTeamsGroups[group][x].setPoints(3);
			}
			
			// if Team B wins 
			else if (bGoals > aGoals)
			{
				allTeamsGroups[group][x].setWDL(0, 0, 1);
				allTeamsGroups[group][y].setWDL(1, 0, 0);
				allTeamsGroups[group][y].setPoints(3);
			}
				
			// if Team A and B draw
			else
			{
				allTeamsGroups[group][x].setWDL(0, 1, 0);
				allTeamsGroups[group][y].setWDL(0, 1, 0);
				allTeamsGroups[group][x].setPoints(1);
				allTeamsGroups[group][y].setPoints(1);
			}			
		}
	
	// An algorithm creating the league's entire schedule	
	public String fixtures(int x, int y)
	{
		int teams = 4;
		String[][] rounds = new String[6][2];

			for (int round = 0; round < 6; round++) 
			{
				for (int match = 0; match < 2; match++) 
				{
					int home = (round + match) % (teams - 1);
					int away = (teams - 1 - match + round) % (teams - 1);
	        	
					// Last team stays in the same place while the others rotate around it.
					if (match == 0)
						away = teams - 1;
	        	
					rounds[round][match] = home + "," + away;
				}
			}
		return rounds[x][y];
	}
		
	// Takes parts of the String returned in fixtures() 
	// to find the two teams to play each other
	public void weeks(int group2)
	{
		for (int a = 0; a < 6; a++)
		{
			for (int z = 0; z < 2; z++)
			{
				String team1 = fixtures(a,z);
				String team2 = team1.substring(team1.length() - 2);
				
				team1 = team1.substring(0, 2);
				if (team1.indexOf(",") == 1)
					team1 = team1.substring(0, 1);
				if (team2.indexOf(",") == 0)
				{
					team2 = team2.substring(1);
					if (team2.indexOf(",") == 0)
						team2 = team2.substring(1);
				}
			
				int x = Integer.parseInt(team1);
				int y = Integer.parseInt(team2);
			
				games(x, y, group2);
					
			}
		}
	}
	
	// Simplifies the toString
	public String setPlace(int num, int group)
	{	
		return allTeamsGroups[group][num].getName() + "  " 
			 + allTeamsGroups[group][num].getMatchesPlayed() + "   "
			 + allTeamsGroups[group][num].getWDL() 
			 + allTeamsGroups[group][num].getFAD() 
			 + allTeamsGroups[group][num].getPoints();
	}

	// Sets all of the places of each team, which each
	// method checking to make sure the team hasn't been 
	// used yet
	public String firstPlace(int group)
	{
	   int largest = 0;
	   int arr = 0;
	   for(int i = 0; i < 4; i++)
	   {
	    	if (allTeamsGroups[group][i].getIntPoints() > largest)
	       	{
	    		largest = allTeamsGroups[group][i].getIntPoints();
	        	arr = i;
	        }
	    }
	    newArr[group][0] = arr;
	    info[group][0] = largest;

	    return setPlace(arr, group);
	}
	public String secondPlace(int group)
	{
		int largest = 0;
	    int arr = 0;
	    for(int i = 0; i < 4; i++)
	    {
	        if (newArr[group][0] != i)
	        {	
	        	if (allTeamsGroups[group][i].getIntPoints() > largest)
	        	{
	        			largest = allTeamsGroups[group][i].getIntPoints();
	        			arr = i;
	        	}    
	        }
	    }
	    newArr[group][1] = arr;       
	    info[group][1] = largest;

	    return setPlace(arr, group);
	}
	public String thirdPlace(int group)
	{
		int largest = 0;
	    int arr = 0;
	    for(int i = 0; i < 4; i++)
	    {
	    	if (newArr[group][0] != i && newArr[group][1] != i)
	        {	
	        	if (allTeamsGroups[group][i].getIntPoints() > largest)
	        	{
	        		largest = allTeamsGroups[group][i].getIntPoints();
	        		arr = i;
	        	}
	        }
	    }
	    newArr[group][2] = arr;        
	    info[group][2] = largest;
	    
	    return setPlace(arr, group);
	}
	public String fourthPlace(int group)
	{
		int largest = 0;
	    int arr = 0;
	    for(int i = 0; i < 4; i++)
	    {
	        if (newArr[group][0] != i && newArr[group][1] != i && newArr[group][2] != i)
	        {	
	        	largest = allTeamsGroups[group][i].getIntPoints();
	        	arr = i;
	        }
	    }
	    newArr[group][3] = arr;        
	    info[group][3] = largest;

	    return setPlace(arr, group);
	}
	
	// Removes all extra spaces in team names
	public String shortenName(int n, Team[] arr)
	{
		String aT0 = arr[n].getName();
		for (int index = 2; index < 20; index++)
		{
			if (aT0.substring(index - 2, index - 1).equals(" ") && aT0.substring(index - 1, index).equals(" "))
			{
				aT0 = aT0.substring(0, index);
				break;
			}
		}
		aT0 = aT0.substring(0, aT0.length() - 2);
		return aT0;
	}
	
	// Sets each place into its own cell of places,
	// allowing teams to be swapped if equal on points
	// but has different goal differences
	public void swap()
	{	
		for (int group = 0; group < 8; group++)
		{
			places[group][0] = firstPlace(group);
			places[group][1] = secondPlace(group);
			places[group][2] = thirdPlace(group);
			places[group][3] = fourthPlace(group);
		
			for (int b = 0; b < 4; b++)
			{
				if (b > 1 && allTeamsGroups[group][newArr[group][b-2]].getIntPoints() == allTeamsGroups[group][newArr[group][b]].getIntPoints()
						  && allTeamsGroups[group][newArr[group][b-1]].getIntPoints() == allTeamsGroups[group][newArr[group][b]].getIntPoints())
				{
					if (allTeamsGroups[group][newArr[group][b]].diff > allTeamsGroups[group][newArr[group][b-2]].diff)
					{
						String temp = places[group][b];
						places[group][b] = places[group][b-2];
						places[group][b-2] = temp;
					}
				}
				
				if ( b > 0 && allTeamsGroups[group][newArr[group][b-1]].getIntPoints() == allTeamsGroups[group][newArr[group][b]].getIntPoints())
				{
					if (allTeamsGroups[group][newArr[group][b-1]].diff < allTeamsGroups[group][newArr[group][b]].diff)
					{
						String temp = places[group][b];
						places[group][b] = places[group][b-1];
						places[group][b-1] = temp;
					}
				}
			}
		}
	}
	
	// Same as games but for round of sixteen
	public String gamesRS(int x, int y)
	{
		// Probabilities based on FIFA 15 ratings
		int totX = 0;
		int totY = 0;
		
		// Team A's attackers compared to Team B's defenders
		if (arrTeamsF[x].att > arrTeamsF[y].def)
		{
			if (Math.random() >= .2)
			{
				totX += 1;
				if (Math.random() >= .75 && (arrTeamsF[x].att - arrTeamsF[y].def) > 5)
					totX += 1;
			}
		}
		else if (arrTeamsF[x].att < arrTeamsF[y].def)
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .1 && (arrTeamsF[y].def - arrTeamsF[x].att) > 5)
					totY += 1;
			}
		}
			
		// Team A's midfielders compared to Team B's midfielders
		if (arrTeamsF[x].mid < arrTeamsF[y].mid)
			 totY += 1;
				
		else if (arrTeamsF[x].mid > arrTeamsF[y].mid)
			totX +=1;	
			
		// Team A's defenders compared to Team B's attackers
		if (arrTeamsF[x].def > arrTeamsF[y].att)
		{
			totY += 1;
			if (Math.random() > .75 && (arrTeamsF[y].att - arrTeamsF[x].def) > 5)
				totY += 1;
		}
		else if (arrTeamsF[x].def < arrTeamsF[y].att) 
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .75 && (arrTeamsF[x].def - arrTeamsF[y].att) > 5)
					totY += 1;
			}
		}	
				
		// Team A vs. Team B
		int aGoals = calculateGoals();
		int bGoals = calculateGoals();
		
		if (totX > totY)
		{
			aGoals += 1;
			if (Math.random() >= .05)
				aGoals += 1;
		}
		
		else if (totY > totX)
		{
			bGoals += 1;
			if (Math.random() >= .05)
				bGoals += 1;
		}
		
		int aX = 0;
		int bX = 0;
		
		if (aGoals == bGoals)
		{
			for (int r = 0; r < 5; r++)
			{
				double random = Math.random();
				if ( random < .4)
					aX += 1;
				else if (random > .6)
					bX += 1;
				else
				{
					aX += 1;
					bX += 1;
				}
			}
			
			if (aX == bX)
			{
				double random = Math.random();
				if ( random < .5)
					aX += 1;
				else if (random > .5)
					bX += 1;
			}
		}
		
		for (int t = 0; t < 8; t++)
		{
			if (arrTeamQF[t].equals(fail))
			{
				if (aGoals > bGoals || aX > bX)
				{	
					arrTeamQF[t] = arrTeamsF[x];
				}
				else if (bGoals > aGoals || bX > aX)
				{	
					arrTeamQF[t] = arrTeamsF[y];
				}
				break;
			}
		}
		
		String fin = shortenName(x, arrTeamsF);
		int di = shortenName(x, arrTeamsF).length() - shortenName(y, arrTeamsF).length();
		
		if (di < 0)
		{
			for (int di1 = di; di1 < 0; di1++)
			{
				fin += " ";
			}
		}
		
		fin += "  " + aGoals;
				
		if (aGoals == bGoals)
			fin += "  -->  " + aX;
		
		fin += "\n" + shortenName(y, arrTeamsF);
		
		if (di > 0)
		{
			for (int di1 = di; di1 > 0; di1--)
			{
				fin += " ";
			}
		}
		
		fin += "  " + bGoals;
		
		if (aGoals == bGoals)
			fin += "  -->  " + bX;
		
		return fin;
	}
	public String roundOfSixteen()
	{
		Team[] allTeamsGroupsSingleArr = {allTeamsGroups[0][0], allTeamsGroups[0][1], allTeamsGroups[0][2], allTeamsGroups[0][3], 
				  allTeamsGroups[1][0], allTeamsGroups[1][1], allTeamsGroups[1][2], allTeamsGroups[1][3],
				  allTeamsGroups[2][0], allTeamsGroups[2][1], allTeamsGroups[2][2], allTeamsGroups[2][3],
				  allTeamsGroups[3][0], allTeamsGroups[3][1], allTeamsGroups[3][2], allTeamsGroups[3][3],
				  allTeamsGroups[4][0], allTeamsGroups[4][1], allTeamsGroups[4][2], allTeamsGroups[4][3],
				  allTeamsGroups[5][0], allTeamsGroups[5][1], allTeamsGroups[5][2], allTeamsGroups[5][3],
				  allTeamsGroups[6][0], allTeamsGroups[6][1], allTeamsGroups[6][2], allTeamsGroups[6][3],
				  allTeamsGroups[7][0], allTeamsGroups[7][1], allTeamsGroups[7][2], allTeamsGroups[7][3]};
		
		String A1 = places[0][0].substring(0, 21);
		String A2 = places[0][1].substring(0, 21);
		String B1 = places[1][0].substring(0, 21);
		String B2 = places[1][1].substring(0, 21);
		String C1 = places[2][0].substring(0, 21);
		String C2 = places[2][1].substring(0, 21);
		String D1 = places[3][0].substring(0, 21);
		String D2 = places[3][1].substring(0, 21);
		String E1 = places[4][0].substring(0, 21);
		String E2 = places[4][1].substring(0, 21);
		String F1 = places[5][0].substring(0, 21);
		String F2 = places[5][1].substring(0, 21);
		String G1 = places[6][0].substring(0, 21);
		String G2 = places[6][1].substring(0, 21);
		String H1 = places[7][0].substring(0, 21);
		String H2 = places[7][1].substring(0, 21);
		
		String[] arrStr = {A1, A2, B1, B2, C1, C2, D1, D2, E1, E2, F1, F2, G1, G2, H1, H2};		
		
		for (int numb1 = 0; numb1 < 16; numb1++)
		{
			for (int numb = 0; numb < 32; numb++)
			{
				if (arrStr[numb1].substring(0, 3).equals(allTeamsGroupsSingleArr[numb].getName().substring(0, 3)))
					arrTeamsF[numb1] = allTeamsGroupsSingleArr[numb];
			}
		}
				
		return shortenName(0, arrTeamsF) + " vs. " + shortenName(3, arrTeamsF) + "\n" + gamesRS(0,3) + "\n\n" +
			   shortenName(1, arrTeamsF) + " vs. " + shortenName(2, arrTeamsF) + "\n" + gamesRS(1,2) + "\n\n" +
			   shortenName(4, arrTeamsF) + " vs. " + shortenName(7, arrTeamsF) + "\n" + gamesRS(4,7) + "\n\n" +
			   shortenName(5, arrTeamsF) + " vs. " + shortenName(6, arrTeamsF) + "\n" + gamesRS(5,6) + "\n\n" +
			   shortenName(8, arrTeamsF) + " vs. " + shortenName(11, arrTeamsF) + "\n" + gamesRS(8,11) + "\n\n" +
			   shortenName(9, arrTeamsF) + " vs. " + shortenName(10, arrTeamsF) + "\n" + gamesRS(9,10) + "\n\n" +
			   shortenName(12, arrTeamsF) + " vs. " + shortenName(15, arrTeamsF) + "\n" + gamesRS(12,15) + "\n\n" +
			   shortenName(13, arrTeamsF) + " vs. " + shortenName(14, arrTeamsF) + "\n" + gamesRS(13,14) + "\n\n";
	}
	// Same as games but for quarterfinals

	public String gamesQF(int x, int y)
	{
		// Probabilities based on FIFA 15 ratings
		int totX = 0;
		int totY = 0;
		
		// Team A's attackers compared to Team B's defenders
		if (arrTeamQF[x].att > arrTeamQF[y].def)
		{
			if (Math.random() >= .2)
			{
				totX += 1;
				if (Math.random() >= .75 && (arrTeamQF[x].att - arrTeamQF[y].def) > 5)
					totX += 1;
			}
		}
		else if (arrTeamQF[x].att < arrTeamQF[y].def)
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .1 && (arrTeamQF[y].def - arrTeamQF[x].att) > 5)
					totY += 1;
			}
		}
			
		// Team A's midfielders compared to Team B's midfielders
		if (arrTeamQF[x].mid < arrTeamQF[y].mid)
			 totY += 1;
				
		else if (arrTeamQF[x].mid > arrTeamQF[y].mid)
			totX +=1;	
			
		// Team A's defenders compared to Team B's attackers
		if (arrTeamQF[x].def > arrTeamQF[y].att)
		{
			totY += 1;
			if (Math.random() > .75 && (arrTeamQF[y].att - arrTeamQF[x].def) > 5)
				totY += 1;
		}
		else if (arrTeamQF[x].def < arrTeamQF[y].att) 
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .75 && (arrTeamQF[x].def - arrTeamQF[y].att) > 5)
					totY += 1;
			}
		}	
				
		// Team A vs. Team B
		int aGoals = calculateGoals();
		int bGoals = calculateGoals();
		
		if (totX > totY)
		{
			aGoals += 1;
			if (Math.random() >= .05)
				aGoals += 1;
		}
		
		else if (totY > totX)
		{
			bGoals += 1;
			if (Math.random() >= .05)
				bGoals += 1;
		}
		
		int aX = 0;
		int bX = 0;
		
		if (aGoals == bGoals)
		{
			for (int r = 0; r < 5; r++)
			{
				double random = Math.random();
				if ( random < .4)
					aX += 1;
				else if (random > .6)
					bX += 1;
				else
				{
					aX += 1;
					bX += 1;
				}
			}
			
			if (aX == bX)
			{
				double random = Math.random();
				if ( random < .5)
					aX += 1;
				else if (random > .5)
					bX += 1;
			}
		}
		
		for (int t = 0; t < 4; t++)
		{
			if (arrTeamSF[t].equals(fail))
			{
				if (aGoals > bGoals || aX > bX)
					arrTeamSF[t] = arrTeamQF[x];
				else if (bGoals > aGoals || bX > aX)
					arrTeamSF[t] = arrTeamQF[y];
				break;
			}
		}
		
		String fin = shortenName(x, arrTeamQF);
		int di = shortenName(x, arrTeamQF).length() - shortenName(y, arrTeamQF).length();
		
		if (di < 0)
		{
			for (int di1 = di; di1 < 0; di1++)
			{
				fin += " ";
			}
		}
		
		fin += "  " + aGoals;
				
		if (aGoals == bGoals)
			fin += "  -->  " + aX;
		
		fin += "\n" + shortenName(y, arrTeamQF);
		
		if (di > 0)
		{
			for (int di1 = di; di1 > 0; di1--)
			{
				fin += " ";
			}
		}
		
		fin += "  " + bGoals;
		
		if (aGoals == bGoals)
			fin += "  -->  " + bX;
		
		return fin;
	}	
	public String quarterfinals()
	{		
		return shortenName(0, arrTeamQF) + " vs. " + shortenName(2, arrTeamQF) + "\n" + gamesQF(0,2) + "\n\n" +
			   shortenName(1, arrTeamQF) + " vs. " + shortenName(3, arrTeamQF) + "\n" + gamesQF(1,3) + "\n\n" +
			   shortenName(4, arrTeamQF) + " vs. " + shortenName(6, arrTeamQF) + "\n" + gamesQF(4,6) + "\n\n" +
			   shortenName(5, arrTeamQF) + " vs. " + shortenName(7, arrTeamQF) + "\n" + gamesQF(5,7) + "\n\n";
	}
	
	public String gamesSF(int x, int y)
	{
		// Probabilities based on FIFA 15 ratings
		int totX = 0;
		int totY = 0;
		
		// Team A's attackers compared to Team B's defenders
		if (arrTeamSF[x].att > arrTeamSF[y].def)
		{
			if (Math.random() >= .2)
			{
				totX += 1;
				if (Math.random() >= .75 && (arrTeamSF[x].att - arrTeamSF[y].def) > 5)
					totX += 1;
			}
		}
		else if (arrTeamSF[x].att < arrTeamSF[y].def)
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .1 && (arrTeamSF[y].def - arrTeamSF[x].att) > 5)
					totY += 1;
			}
		}
			
		// Team A's midfielders compared to Team B's midfielders
		if (arrTeamSF[x].mid < arrTeamSF[y].mid)
			 totY += 1;
				
		else if (arrTeamSF[x].mid > arrTeamSF[y].mid)
			totX +=1;	
			
		// Team A's defenders compared to Team B's attackers
		if (arrTeamSF[x].def > arrTeamSF[y].att)
		{
			totY += 1;
			if (Math.random() > .75 && (arrTeamSF[y].att - arrTeamSF[x].def) > 5)
				totY += 1;
		}
		else if (arrTeamSF[x].def < arrTeamSF[y].att) 
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .75 && (arrTeamSF[x].def - arrTeamSF[y].att) > 5)
					totY += 1;
			}
		}	
				
		// Team A vs. Team B
		int aGoals = calculateGoals();
		int bGoals = calculateGoals();
		
		if (totX > totY)
		{
			aGoals += 1;
			if (Math.random() >= .05)
				aGoals += 1;
		}
		
		else if (totY > totX)
		{
			bGoals += 1;
			if (Math.random() >= .05)
				bGoals += 1;
		}
		
		int aX = 0;
		int bX = 0;
		
		if (aGoals == bGoals)
		{
			for (int r = 0; r < 5; r++)
			{
				double random = Math.random();
				if ( random < .4)
					aX += 1;
				else if (random > .6)
					bX += 1;
				else
				{
					aX += 1;
					bX += 1;
				}
			}
			
			if (aX == bX)
			{
				double random = Math.random();
				if ( random < .5)
					aX += 1;
				else if (random > .5)
					bX += 1;
			}
		}
		
		for (int t = 0; t < 4; t++)
		{
			if (arrTeamFinal[t].equals(fail))
			{
				if (aGoals > bGoals || aX > bX)
					arrTeamFinal[t] = arrTeamSF[x];
				else if (bGoals > aGoals || bX > aX)
					arrTeamFinal[t] = arrTeamSF[y];
				break;
			}
		}
		
		String fin = shortenName(x, arrTeamSF);
		int di = shortenName(x, arrTeamSF).length() - shortenName(y, arrTeamSF).length();
		
		if (di < 0)
		{
			for (int di1 = di; di1 < 0; di1++)
			{
				fin += " ";
			}
		}
		
		fin += "  " + aGoals;
				
		if (aGoals == bGoals)
			fin += "  -->  " + aX;
		
		fin += "\n" + shortenName(y, arrTeamSF);
		
		if (di > 0)
		{
			for (int di1 = di; di1 > 0; di1--)
			{
				fin += " ";
			}
		}
		
		fin += "  " + bGoals;
		
		if (aGoals == bGoals)
			fin += "  -->  " + bX;
		
		return fin;
	}
	public String semifinals()
	{
		return shortenName(0, arrTeamQF) + " vs. " + shortenName(1, arrTeamQF) + "\n" + gamesSF(0,1) + "\n\n" +
			   shortenName(2, arrTeamQF) + " vs. " + shortenName(3, arrTeamQF) + "\n" + gamesSF(2,3) + "\n\n";
	}
	
	public String gamesF(int x, int y)
	{
		// Probabilities based on FIFA 15 ratings
		int totX = 0;
		int totY = 0;
		
		// Team A's attackers compared to Team B's defenders
		if (arrTeamFinal[x].att > arrTeamFinal[y].def)
		{
			if (Math.random() >= .2)
			{
				totX += 1;
				if (Math.random() >= .75 && (arrTeamFinal[x].att - arrTeamFinal[y].def) > 5)
					totX += 1;
			}
		}
		else if (arrTeamFinal[x].att < arrTeamFinal[y].def)
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .1 && (arrTeamFinal[y].def - arrTeamFinal[x].att) > 5)
					totY += 1;
			}
		}
			
		// Team A's midfielders compared to Team B's midfielders
		if (arrTeamFinal[x].mid < arrTeamFinal[y].mid)
			 totY += 1;
				
		else if (arrTeamFinal[x].mid > arrTeamFinal[y].mid)
			totX +=1;	
			
		// Team A's defenders compared to Team B's attackers
		if (arrTeamFinal[x].def > arrTeamFinal[y].att)
		{
			totY += 1;
			if (Math.random() > .75 && (arrTeamFinal[y].att - arrTeamFinal[x].def) > 5)
				totY += 1;
		}
		else if (arrTeamFinal[x].def < arrTeamFinal[y].att) 
		{
			if (Math.random() >= .2)
			{
				totY += 1;
				if (Math.random() >= .75 && (arrTeamFinal[x].def - arrTeamFinal[y].att) > 5)
					totY += 1;
			}
		}	
				
		// Team A vs. Team B
		int aGoals = calculateGoals();
		int bGoals = calculateGoals();
		
		if (totX > totY)
		{
			aGoals += 1;
			if (Math.random() >= .05)
				aGoals += 1;
		}
		
		else if (totY > totX)
		{
			bGoals += 1;
			if (Math.random() >= .05)
				bGoals += 1;
		}
		
		int aX = 0;
		int bX = 0;
		
		if (aGoals == bGoals)
		{
			for (int r = 0; r < 5; r++)
			{
				double random = Math.random();
				if ( random < .4)
					aX += 1;
				else if (random > .6)
					bX += 1;
				else
				{
					aX += 1;
					bX += 1;
				}
			}
			
			if (aX == bX)
			{
				double random = Math.random();
				if ( random < .5)
					aX += 1;
				else if (random > .5)
					bX += 1;
			}
		}
		
		String fin = shortenName(x, arrTeamFinal);
		int di = shortenName(x, arrTeamFinal).length() - shortenName(y, arrTeamFinal).length();
		
		if (di < 0)
		{
			for (int di1 = di; di1 < 0; di1++)
			{
				fin += " ";
			}
		}
		
		fin += "  " + aGoals;
				
		if (aGoals == bGoals)
			fin += "  -->  " + aX;
		
		fin += "\n" + shortenName(y, arrTeamFinal);
		
		if (di > 0)
		{
			for (int di1 = di; di1 > 0; di1--)
			{
				fin += " ";
			}
		}
		
		fin += "  " + bGoals;
		
		if (aGoals == bGoals)
			fin += "  -->  " + bX;
		
		if (aGoals > bGoals || aX > bX)
			arrTeamsF[0] = arrTeamFinal[x];
		else
			arrTeamsF[0] = arrTeamFinal[y];
		
		
		return fin;
	}
	public String finals()
	{
		for (int i = 0; i < 2; i++)
		{
			arrTeamFinal[i] = arrTeamsF[i];
		}
		
		return shortenName(0, arrTeamFinal) + " vs. " + shortenName(1, arrTeamFinal) + "\n" + gamesF(0,1) + "\n\n";
	}
	
	public String toString()
	{
		for (int num = 0; num < 8; num++)
		{
			weeks(num);
		}
		
		swap();
		
		return "Group A\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[0][0] + "\n2) " + places[0][1] + "\n3) " + places[0][2] + "\n4) " + places[0][3]
			 + "\n\nGroup B\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[1][0] + "\n2) " + places[1][1] + "\n3) " + places[1][2] + "\n4) " + places[1][3]
			 + "\n\nGroup C\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[2][0] + "\n2) " + places[2][1] + "\n3) " + places[2][2] + "\n4) " + places[2][3]
			 + "\n\nGroup D\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[3][0] + "\n2) " + places[3][1] + "\n3) " + places[3][2] + "\n4) " + places[3][3]
			 + "\n\nGroup E\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[4][0] + "\n2) " + places[4][1] + "\n3) " + places[4][2] + "\n4) " + places[4][3]
			 + "\n\nGroup F\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[5][0] + "\n2) " + places[5][1] + "\n3) " + places[5][2] + "\n4) " + places[5][3]
			 + "\n\nGroup G\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[6][0] + "\n2) " + places[6][1] + "\n3) " + places[6][2] + "\n4) " + places[6][3]
			 + "\n\nGroup H\n" +
			   "#  Team                  MP  Win  Draw  Loss  For  Against  Difference  Points"
			 + "\n1) " + places[7][0] + "\n2) " + places[7][1] + "\n3) " + places[7][2] + "\n4) " + places[7][3]
					 
			 + "\n\nRound of Sixteen: \n" + roundOfSixteen()
			 + "\n\nQuarterfinals: \n" + quarterfinals()
			 + "\n\nSemifinals: \n" + semifinals()
			 + "\n\nFinals: \n" + finals()
			 + "\nThe champion is: " + arrTeamsF[0].getName();
		
	}
	
}