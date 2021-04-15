#include <iostream>
#include <cstring>
#include <numeric>
#include <cmath>
#include <fstream>
#include <cstdlib>
#include <cctype>
#include <algorithm>
using namespace std;

class gamer{
	public:
	string name;
	int mmr;
};
void increse_points(gamer str[], gamer str2[], gamer str3[], int line_nr, gamer all_gamers[])
{
	unsigned p=0;
	for(unsigned i=0;i<5;i++)
	{
		str2[i].mmr=str2[i].mmr+2;
		str3[i].mmr=str3[i].mmr-2;
	}
	for(unsigned i=0;i<5;i++)
	{
		str[p]=str2[i];
		p++;
		str[p]=str3[i];
		p++;		
	}
	for(unsigned a=0;a<10;a++)
   {
   		for(unsigned b=0;b<line_nr;b++)
   		if(str[a].name==all_gamers[b].name)
		{
			all_gamers[b].mmr=str[a].mmr;			   		
		} 	 
   }
	
	
}
void create_teams(gamer str[], gamer str2[], gamer str3[], gamer all_gamers[], int line_nr)
{
   fstream plik;
   plik.open("mmr.txt", ios::in);
   string line;
   unsigned len=10, best=0, bestd=1000, spr5=0, d=0, count=0;   
   int sum=0, test=0, licznik=0; 
   
   for(unsigned a=0;a<10;a++)
   {
		cout<<"Gimme Gamer"<<a+1<<" "; 
		cin>>str[a].name;
   }
   cout<<endl;
//tworzy tablicê all gamers ze wszystkimi danymi   
   while (getline(plik, line))
   {
   		line.erase(std::remove_if(line.begin(), line.end(), ::isspace), line.end());
   		all_gamers[count].name=line;
   		getline(plik, line);
   		all_gamers[count].mmr=atoi(line.c_str());  		
   		count++;
   }
//ustawiam na pocz¹tek pliku
   count=0;
   plik.clear();
   plik.seekg(0, ios::beg);  
//dopisujê do tablicy 10 graczy ich mmr   
   for(unsigned a=0;a<10;a++)
   {
   		for(unsigned b=0;b<line_nr;b++)
   		if(str[a].name==all_gamers[b].name)
		{
			str[a].mmr=all_gamers[b].mmr;			   		
		} 	 
   }
    
   for(unsigned a=0;a<10;a++) sum = sum +str[a].mmr; 
   sum=sum/2;
   cout<<"perfect balance is - "<<sum<<endl;
//tu dzieje siê magia i dru¿yny dziel¹ siê pod wzglêdem mmr   
   for(unsigned i=(1<<len);--i;) 
   {  
		  for(unsigned k=0;k<len;++k)if((i>>k)&1) 
		  {
		  	licznik=licznik+str[k].mmr; 
			spr5++;
		  }
	   	
		  test= abs(licznik-sum);
		  if(test<bestd && spr5==5) 
		  {
		  	best = licznik;
		  	bestd = test;
		  	for(unsigned b=0;b<len;++b)
			if((i>>b)&1) 
		  	{
		 		str2[d].mmr=str[b].mmr;
		 		str2[d].name=str[b].name;
		 		d++;
		  	}
		  }
	   licznik=0;
	   spr5=0;
	   d=0;
   }
//wypisujemy punkty dru¿yn
   cout<<"team nr1 points - "<<best<<endl<<endl;
   for(unsigned c=0;c<5;c++) 
   {
   		cout<<str2[c].name<<" - ";
   		cout<<str2[c].mmr<<endl;  		
   }
   unsigned test2=0, m=0, best2=0;
   
   for(unsigned c=0;c<10;c++)
   {
   		for(unsigned d=0;d<5;d++)
		   {
		   		if(str[c].name==str2[d].name) test2++;
		   }
		   if(test2==0) 
		   {		   	
				str3[m].name=str[c].name;
   				str3[m].mmr=str[c].mmr; 
				best2=best2+str3[m].mmr;   
   				m++;
		   }
   		test2=0;  	
   }
   cout<<endl<<"team nr2 points - "<<best2<<endl<<endl;
   for(unsigned c=0;c<5;c++) 
   {
   		cout<<str3[c].name<<" - ";
   		cout<<str3[c].mmr<<endl;  		
   }  
   plik.close();  
}
int main()
  {
   gamer str[10];
   gamer str2[5];
   gamer str3[5];
   string line;
   unsigned line_nr=0, loop=0, count=0;
   fstream plik;
   plik.open("mmr.txt", ios::in);
   while (getline(plik, line))
   {
   		line_nr++;  	
   } 
   plik.clear();
   plik.seekg(0, ios::beg); 
   line_nr=line_nr/2;
   gamer all_gamers[line_nr];
   plik.close();
   
   create_teams(str, str2, str3, all_gamers, line_nr);
   
   
//tu koñczy siê pierwsza czêœæ programu
   while(!loop)
   {
   cout<<"type 1 for team1 win, 2 for team 2 win, 3 to create your own teams(same players) 0 to leave the programm, 9 to start new teams(new players)"<<endl;
   cin>>count;  
   if(count==1) increse_points(str, str2, str3, line_nr, all_gamers);
   else if(count==2) increse_points(str, str3, str2, line_nr, all_gamers);
//ZAPISUJEMY NOWE DANE i ZAMYKAMY PROGRAM 
   else if(count==0)
   {
   		loop++;
   		plik.open("mmr.txt", ios::out | ios::trunc);
   		for(unsigned i=0;i<line_nr;i++)
   		{
   			plik<<all_gamers[i].name<<endl;
   			plik<<all_gamers[i].mmr<<endl;	
   		}
   		plik.close();
   }
//ZAPISUJEMY DANE I ZACZYNAMY NOWY MECZ
   else if(count==9)
   {
   		plik.open("mmr.txt", ios::out | ios::trunc);
   		for(unsigned i=0;i<line_nr;i++)
   		{
   			plik<<all_gamers[i].name<<endl;
   			plik<<all_gamers[i].mmr<<endl;	
   		}
   		
   		plik.close();
   		create_teams(str, str2, str3, all_gamers, line_nr);
   }
   else if(count==3)    
   {
   		
   		for(unsigned a=0;a<5;a++)
   		{
			cout<<"Gimme Team 1 Player"<<a+1<<" "; 
			cin>>str2[a].name;			
   		}
   		cout<<endl;
   		for(unsigned a=0;a<5;a++)
   		{
			cout<<"Gimme Team 2 Player"<<a+1<<" "; 
			cin>>str3[a].name;			
   		}
   		cout<<endl;
   		for(unsigned a=0;a<5;a++)
   		{
   			for(unsigned b=0;b<10;b++)
   			if(str2[a].name==str[b].name)
			{
				str2[a].mmr=str[b].mmr;			   		
			} 
			else if(str3[a].name==str[b].name)
			{
				str3[a].mmr=str[b].mmr;			   		
			} 	 
   		}   		
   }           
   
   }
   
   return 0;
  }
