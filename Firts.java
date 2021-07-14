package pierwszy;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Firts {

	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		System.out.println("witaj");
		
		String[] test = new String[]{"Ehkho","Bobby","Grim","Smurf","Diddy","Zto","Pano","Haboo","Slime","Stinky"};
		WriteFile writeFile;
		int loop=0;
		Lobby lobby = new Lobby();
		lobby.setAllGamers();
		lobby.setLobbyAuto(test);
		lobby.setLobbyMmrTitansWinrate();		
		lobby.checkLobby();
		lobby.checkAllGamers();
		lobby.balanceTeams();
		int i = 100; 
		lobby.message();
		while(loop==0) 
		{	i = lobby.matchResult();
		
			if(i==1 || i==2) {
				lobby.increasePoints(i);
			}			
			else if(i==0) 
			{
				writeFile = new WriteFile();
				writeFile.updateFile(lobby.getAllGamers());
				loop++;
				lobby.scannerClose();
			}
			else if(i==10) {
				loop++;
			}
			else if(i==9)
			{
				lobby.setLobby();
				lobby.setLobbyMmrTitansWinrate();
				lobby.checkLobby();
				lobby.checkAllGamers();
				lobby.balanceTeams();
			}
			else if(i==90)
			{
				lobby.setLobby();
				lobby.setAllGamers();
				lobby.setLobbyMmrTitansWinrate();
				lobby.checkLobby();
				lobby.checkAllGamers();
				lobby.balanceTeams();
			}
			else if(i==3) {
				lobby.setLobbyOwn();
				lobby.checkTeam1();
				lobby.checkTeam2();
				if(!lobby.checkTeamBalance()) {					
					lobby.balanceTeams();
					
				}				
					lobby.message();
				
				
			}
			else if(i==4) {
				lobby.checkLobby();
			}
			else if(i==5) {
				lobby.checkAllGamers();
			}
			else {
				System.out.println("error");
			}
			//lobby.checkLobby();
		}
		System.out.println("thanks for gaming");
		System.exit(0);
		
	}

} 
