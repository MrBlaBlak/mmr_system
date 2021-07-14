package pierwszy;


public class Gamer {
	
	private float mmr;
	private String name;
	private String titan;
	private String titan2;
	private int wins;
	private int losses;
	private int lastTen;
	
	public Gamer(String n, float m, String t, String t2, int w, int l, int lt)
	{
		mmr=m;
		name=n;
		titan=t;
		titan2=t2;
		wins=w;
		losses=l;
		lastTen=lt;
		
	}
	public Gamer()
	{
		mmr=560;
		name="";
		titan="??";
		titan2="??";
		wins=0;
		losses=0;
		lastTen=0;
		
	}
	public void setName(String n) {
		this.name = n;
	}
	public void setMmr(float n) {
		this.mmr = n;
	}
	public void setTitan(String t, String t2) {
		titan=t;
		titan2=t2;
	}
	public void setWinsAndLosses(int w, int l) {
		wins=w;
		losses=l;
	}
	public void setLastTen(int lt) {
		lastTen=lt;
	}
	public String getName() {
		return this.name;
	}
	public float getMmr() {
		return this.mmr;
	}
	public String getTitan() {
		return this.titan;
	}
	public String getTitan2() {
		return this.titan2;
	}
	public int getWins() {
		return this.wins;
	}
	public int getLosses() {
		return this.losses;
	}
	public int getLastTen() {
		return this.lastTen;
	}
}
//assertArrayEquals
//String.split()
