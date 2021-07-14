package pierwszy;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Math.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Lobby   { 
	
	 
	private Gamer[] lobby;
	private Gamer[] team1;
	private Gamer[] team2;
	private List<Gamer> allGamers;
	private Scanner inputScanner;
	private File file;
	private  Scanner fileScanner;
	
	
	public Lobby() throws FileNotFoundException 
	{		
		lobby = new Gamer[10];
		team1 = new Gamer[5];
		team2 = new Gamer[5];
		allGamers = new ArrayList<>();
		inputScanner = new Scanner(System.in);
		file = new File("plik.txt");
		
		for(int a=0;a<5;a++)
		{
			team1[a]= new Gamer();
			team2[a]= new Gamer();
		}
	}
	public void setLobby()
	{				
		for(int a=0;a<10;a++)
		   {			
			System.out.print("Gimme Gamer"+ (a+1) +" - "); 
			lobby[a]= new Gamer();
			lobby[a].setName(inputScanner.nextLine());	
		   } 
	}
	public void setLobbyAuto(String[] test) //tu trzeba zrobiæ zmianê
	{
		for(int a=0;a<10;a++)
		   {
			lobby[a]= new Gamer();
			lobby[a].setName(test[a]);			
		   }
		
	}
	public void setLobbyOwn(){
		for(int a=0;a<5;a++)
		   {			
			System.out.print("Gimme Team 1 Player"+ (a+1) +" - "); 
			team1[a].setName(inputScanner.nextLine());	
		   }
		for(int a=0;a<5;a++)
		   {			
			System.out.print("Gimme Team 2 Player"+ (a+1) +" - "); 
			team2[a].setName(inputScanner.nextLine());	
		   }
		for(int a=0;a<5;a++)
   		{
   			for(int b=0;b<10;b++) {   				   			
   				if(team1[a].getName().equalsIgnoreCase(lobby[b].getName()))
   				{
   					team1[a].setMmr(lobby[b].getMmr());	
   					team1[a].setWinsAndLosses(lobby[b].getWins(),lobby[b].getLosses());
   				} 
   				else if(team2[a].getName().equalsIgnoreCase(lobby[b].getName()))
   				{
   					team2[a].setMmr(lobby[b].getMmr());	
   					team2[a].setWinsAndLosses(lobby[b].getWins(),lobby[b].getLosses());
   					
   				}
   			}
   		}  
	}
	
	public void setAllGamers() throws IOException {
		fileScanner= new Scanner(file);
		boolean exists = file.exists();
		if(!exists) 
		{ 
			file.createNewFile();
		}
		allGamers.clear();
		String str;
		
		while(fileScanner.hasNext())			
		{ 
			str=fileScanner.nextLine();
			String[] actualValue = str.split(" ");
			allGamers.add(new Gamer(actualValue[0], Float.parseFloat(actualValue[1]), actualValue[2], actualValue[3], Integer.parseInt(actualValue[4]), Integer.parseInt(actualValue[5]), Integer.parseInt(actualValue[6], 2)));			
		}
		fileScanner.close();
	}
	public void setLobbyMmrTitansWinrate() {
		for(int a=0;a<10;a++)
		   {
		   		for(int b=0;b<allGamers.size();b++)
		   		if(lobby[a].getName().equalsIgnoreCase(allGamers.get(b).getName()))   //allGamers.get(b).getName()
				{
					lobby[a].setMmr(allGamers.get(b).getMmr());	
					lobby[a].setTitan(allGamers.get(b).getTitan(), allGamers.get(b).getTitan2());
					lobby[a].setWinsAndLosses(allGamers.get(b).getWins(), allGamers.get(b).getLosses());	
					lobby[a].setLastTen(allGamers.get(b).getLastTen());
				} 	 
		   }
	}

	public void balanceTeams() {
		float sum=checkPerfectBalance();   
		int   spr5=0, d=0; 
		float  licznik=0, test=0, best=0, bestd=1000;
		for(int i=1023;i>0;i--) 
		   {  
				
				  for(int k=0;k<10;++k)
				  {
					  if(((i>>k)&1)==1) 
					  {
					  	licznik=licznik+lobby[k].getMmr(); 
						spr5++;
					  }
				  }			   	
				  test= abs(licznik-sum);
				  if(test<bestd && spr5==5) 
				  {
				  	best = licznik;
				  	bestd = test;
				  	for(int b=0;b<10;++b)
				  	{
				  		if(((i>>b)&1)==1) 
					  	{
							team1[d].setMmr(lobby[b].getMmr());
							team1[d].setName(lobby[b].getName());
							team1[d].setTitan(lobby[b].getTitan(), lobby[b].getTitan2());
							team1[d].setWinsAndLosses(lobby[b].getWins(), lobby[b].getLosses());
							team1[d].setLastTen(lobby[b].getLastTen());
							d++;
					  	}
				  	}				
				  }
			   licznik=0;
			   spr5=0;
			   d=0;
		   }
		System.out.println("team nr1 points - "+ best + "\n"); 
		   checkTeam1();
		   int test2=0, m=0;
		   float  best2=0;
		   for(int c=0;c<10;c++)
		   {
		   		for(int d1=0;d1<5;d1++)
				   {
				   		if(lobby[c].getName()==team1[d1].getName()) test2++;
				   }
				   if(test2==0) 
				   {		   	
						team2[m].setName(lobby[c].getName());
						team2[m].setMmr(lobby[c].getMmr());
						team2[m].setTitan(lobby[c].getTitan(), lobby[c].getTitan2());
						team2[m].setWinsAndLosses(lobby[c].getWins(), lobby[c].getLosses());
						team2[m].setLastTen(lobby[c].getLastTen());
						best2=best2+team2[m].getMmr();   
		   				m++;
				   }
		   		test2=0;  	
		   }  
		   System.out.println("team nr2 points - "+ best2 + "\n");
		   checkTeam2();		   		   
	}
	public int matchResult() {
		String result = inputScanner.nextLine();		
		int i=Integer.parseInt(result); 		
		return i;				
	}
	
		
	public float checkPerfectBalance() {
		float sum=0;
		for(Gamer a : lobby)
		{
			sum = sum + a.getMmr(); 
		}
		   sum=sum/2;
		   System.out.println("perfect balance is - "+ sum + "\n");
		   return sum;
	}
	public boolean checkTeamBalance(){
		float sum=0, sum2=0;
		boolean balanced= true;
		for(Gamer a : team1)
		{
			sum = sum + a.getMmr(); 
		}
		System.out.println("team nr1 points - "+ sum + "\n");
		for(Gamer a : team2)
		{
			sum2 = sum2 + a.getMmr(); 
		}
		System.out.println("team nr2 points - "+ sum2 + "\n");
		if(abs(sum-sum2)>20) {
			System.out.println("Teams are not balanced, gamers mmr is not gonna change");
			balanced = false;
		}
		return 	balanced;	
	}
	
	public void checkLobby()
	{
		for(Gamer a : lobby)
		{
			System.out.print(a.getName()+ " - ");
			System.out.print(a.getMmr()+", ");
		} 
		System.out.println("lobby checked");
	}
	
	public void checkAllGamers() 
	{
		for(Gamer a : allGamers)
		{
			System.out.print(a.getName()+ " - ");
			System.out.print(a.getMmr()+", ");
		}
		System.out.println("all gamers checked");
	}
	public void checkTeam1()
	{
		for(Gamer a : team1)
		{
			System.out.println(a.getName() + " - " + a.getMmr() + " - " + a.getTitan() + " - " + a.getTitan2());	
		}
		System.out.println("team1 checked \n");
	}
	public void checkTeam2()
	{
		for(Gamer a : team2)
		{
			System.out.println(a.getName() + " - " + a.getMmr() + " - " + a.getTitan() + " - " + a.getTitan2());
		} 
		System.out.println("team2 checked \n");
	}
	
	public void increasePoints(int whoWon)
	{
		int p=0, streak=0, streak2=0, changeT1=0, changeT2=0;
		float points=0, points2=0;
		for(int i=0;i<5;i++)
		{	
			int countDown=team1[i].getLastTen();
			int countDown2=team2[i].getLastTen();
			
			for(int a=0;a<10;a++)
			{ 
				if ((countDown & 1 )== 1) streak++; 
				countDown=countDown>>1;
				if ((countDown2 & 1 )== 1) streak2++; 
				countDown2=countDown2>>1;
			}	
			
			if(whoWon==1) {
				changeT1=1;
				changeT2=0;
				team1[i].setLastTen((team1[i].getLastTen()>>1) | 512);
				team2[i].setLastTen(team2[i].getLastTen()>>1);
			}
			else if(whoWon==2) {
				changeT1=0;
				changeT2=1;
				team1[i].setLastTen(team1[i].getLastTen()>>1);
				team2[i].setLastTen((team2[i].getLastTen()>>1) | 512);
			}	
			if(streak==7 && whoWon==1) points=1.5f;
			else if(streak==3 && whoWon==2) points=-1.5f;
			else if(streak>2 && whoWon==2) points=-1;
			else if(streak>=8 && whoWon==1) points=2;
			else if(streak<=2 && whoWon==2) points=-2;
			else if(streak<8 && whoWon==1) points=1; 
			
			
			if(streak2==7 && whoWon==2) points2=1.5f;
			else if(streak2==3 && whoWon==1) points2=-1.5f;
			else if(streak2>2 && whoWon==1) points2=-1;
			else if(streak2>=8 && whoWon==2) points2=2;
			else if(streak2<=2 && whoWon==1) points2=-2;
			else if(streak2<8 && whoWon==2) points2=1; 
			
			if(team1[i].getWins()+team1[i].getLosses()<10) points=points/abs(points)*4;
			if(team2[i].getWins()+team2[i].getLosses()<10) points2=points2/abs(points2)*4;
			
			
			
			team1[i].setMmr(team1[i].getMmr()+points);
			team2[i].setMmr(team2[i].getMmr()+points2);
			team1[i].setWinsAndLosses(team1[i].getWins()+changeT1, team1[i].getLosses()+changeT2);
			team2[i].setWinsAndLosses(team2[i].getWins()+changeT2, team2[i].getLosses()+changeT1);
			streak=0; streak2=0;
		}
		for(int i=0;i<5;i++)
		{
			lobby[p].setMmr(team1[i].getMmr());
			lobby[p].setName(team1[i].getName());
			lobby[p].setWinsAndLosses(team1[i].getWins(), team1[i].getLosses());
			lobby[p].setTitan(team1[i].getTitan(), team1[i].getTitan2());
			lobby[p].setLastTen(team1[i].getLastTen());
			p++;
			lobby[p].setMmr(team2[i].getMmr());
			lobby[p].setName(team2[i].getName());
			lobby[p].setWinsAndLosses(team2[i].getWins(), team2[i].getLosses());
			lobby[p].setTitan(team2[i].getTitan(), team2[i].getTitan2());
			lobby[p].setLastTen(team2[i].getLastTen());
			p++;		
		}
		for(int a=0;a<10;a++)
	    {
	   		for(int b=0;b<allGamers.size();b++)
	   		if(lobby[a].getName().equalsIgnoreCase(allGamers.get(b).getName())) 
			{
				allGamers.get(b).setMmr(lobby[a].getMmr());
				allGamers.get(b).setWinsAndLosses(lobby[a].getWins(), lobby[a].getLosses());				
				allGamers.get(b).setLastTen(lobby[a].getLastTen());
			} 	 
	    }
		
		
	}
	public List<Gamer> getAllGamers() {
		return this.allGamers;
	}
	public void scannerClose() {
		fileScanner.close();
		inputScanner.close();
	}
	public void message() {
		System.out.println("If you want to: \n claim team1 win, type - 1 \n claim team 2 win, type - 2 \n create your own teams(same players, keep results), type - 3 \n check lobby, type - 4 \n check allGamers, type - 5 \n leave the programm, type - 0  \n start new teams(new players, keep results), type - 9 \n cancel matches results and start new teams(new teams forget results), type - 90");	
	}
	
	
}
