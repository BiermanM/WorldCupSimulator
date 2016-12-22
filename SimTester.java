// Created by Matthew Bierman
public class SimTester 
{
	public static void main(String[] args) 
	{
		Team algeria     = new Team("Algeria             ", 77, 73, 69);
		Team argentina   = new Team("Argentina           ", 84, 80, 79);
		Team australia   = new Team("Australia           ", 76, 71, 66);
		Team belgium     = new Team("Belgium             ", 76, 80, 80);
		Team bosnia      = new Team("Bosnia              ", 76, 75, 76);
		Team brazil      = new Team("Brazil              ", 82, 81, 82);
		Team cameroon    = new Team("Cameroon            ", 76, 71, 75);
		Team chile       = new Team("Chile               ", 77, 75, 70);
		Team colombia    = new Team("Colombia            ", 82, 78, 74);
		Team costaRica   = new Team("Costa Rica          ", 76, 72, 75);
		Team croatia     = new Team("Croatia             ", 75, 74, 73);
		Team ecuador     = new Team("Ecuador             ", 74, 71, 68);
		Team england     = new Team("England             ", 82, 77, 78);
		Team france      = new Team("France              ", 85, 80, 80);
		Team germany     = new Team("Germany             ", 83, 83, 79);
		Team ghana       = new Team("Ghana               ", 77, 73, 75);
		Team greece      = new Team("Greece              ", 76, 71, 75);
		Team honduras    = new Team("Honduras            ", 65, 60, 59);
		Team italy       = new Team("Italy               ", 79, 77, 78);
		Team iran        = new Team("Iran                ", 73, 76, 70);
		Team ivoryCoast  = new Team("Ivory Coast         ", 77, 77, 74);
		Team japan       = new Team("Japan               ", 71, 75, 70);
		Team korea       = new Team("Korea               ", 71, 70, 67);
		Team mexico      = new Team("Mexico              ", 76, 74, 71);
		Team netherlands = new Team("Netherlands         ", 83, 77, 76);
		Team nigeria     = new Team("Nigeria             ", 75, 77, 65);
		Team portugal    = new Team("Portugal            ", 83, 79, 79);
		Team russia      = new Team("Russia              ", 78, 76, 75);
		Team spain       = new Team("Spain               ", 86, 83, 82);
		Team switzerland = new Team("Switzerland         ", 75, 77, 76);
		Team uruguay     = new Team("Uruguay             ", 78, 75, 76);
		Team usa         = new Team("USA                 ", 76, 74, 71);
		
		
		WorldCupSimulator sim = new WorldCupSimulator(algeria, argentina, australia, belgium, bosnia, brazil, cameroon, 
													  chile, colombia, costaRica, croatia, ecuador, england, france, 
													  germany, ghana, greece, honduras, italy, iran, ivoryCoast, japan, 
													  korea, mexico, netherlands, nigeria, portugal, russia, spain, 
													  switzerland, uruguay, usa);
		System.out.println(sim);
		
	}
}
