package pierwszy;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestList {
	
	private List<String> list = new ArrayList<>();
	
	public TestList() {
		
	}
	
}
/*
System.out.println("witaj");
String[] test = new String[]{"Blak","Timo","Pano","Grim","Zim","Alvin","Bobby","Stn","Eth","Zto"};
Gamer[] lobby = new Gamer[10];	
// tu zaczynam now¹ klasê			
File file = new File("plik.txt");
Scanner fileScanner = new Scanner(file);
Scanner inputScanner = new Scanner(System.in);
boolean exists = file.exists();
List<Gamer> allGamers = new ArrayList<>();
if(!exists) 
{ 
	file.createNewFile();
}		
for(int a=0;a<10;a++)
   {
	System.out.print("Gimme Gamer"+ (a+1) +" - "); 
	lobby[a]= new Gamer();
//	lobby[a].setName(inputScanner.nextLine());	druga wersja
	lobby[a].setName(test[a]);
	
   }	
while(fileScanner.hasNext())
{   								
	allGamers.add(new Gamer(fileScanner.nextLine().replaceAll(" ", ""), Integer.parseInt(fileScanner.nextLine())));			
}
fileScanner.close(); 
inputScanner.close();
System.out.println(allGamers.size());
//dopisujê do tablicy 10 graczy ich mmr 
for(int a=0;a<10;a++)
   {
   		for(int b=0;b<allGamers.size();b++)
   		if(lobby[a].getName().equals(allGamers.get(b).getName()))   //allGamers.get(b).getName()
		{
			lobby[a].setMmr(allGamers.get(b).getMmr());			   		
		} 	 
   }
//sprawdzenie
for(Gamer a : allGamers)
{
	System.out.print(a.getName()+ " - ");
	System.out.println(a.getMmr());
}
for(Gamer a : lobby)
{
	System.out.print(a.getName()+ " - ");
	System.out.println(a.getMmr());
} 
//perfect balance
int sum=0;
for(Gamer a : lobby)
{
	sum = sum + a.getMmr(); 
}
   sum=sum/2;
   System.out.println("perfect balance is - "+ sum);
*/