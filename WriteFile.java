package pierwszy;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class WriteFile {
	
	private PrintWriter zapis;
	
	public WriteFile() throws FileNotFoundException {
	zapis = new PrintWriter("plik.txt");
	}
	
    public void updateFile(List<Gamer> allGamers) {
    	 
   		for(int i=0;i<allGamers.size();i++)
   		{   			
   			zapis.println(allGamers.get(i).getName() + " " + String.valueOf(allGamers.get(i).getMmr()) + " " + allGamers.get(i).getTitan()+ " " + allGamers.get(i).getTitan2()+ " " + allGamers.get(i).getWins()+ " " + allGamers.get(i).getLosses()+ " " + Integer.toBinaryString(allGamers.get(i).getLastTen()));
   			//zapis.println(String.valueOf(allGamers.get(i).getMmr()));	
   		}
   		zapis.close();
    }
}
