import java.util.*;
//class implementing singelton design pattern
public class Gamedata {
	private static Gamedata data = null;
	static int nPlayers = 2;
	public Object lock1 = new Object();
	ArrayList<Integer> numbersAnnounced = new ArrayList<Integer>();
	boolean numberFlag = false;
    boolean playerChanceFlag[] = new boolean[nPlayers];
    int winner = 0;
    boolean gameCompleteFlag = false;
    int numbersGen=0;
    int numWins[] = new int[nPlayers];
    
    private Gamedata() {}
    
    public static Gamedata getInstance(int n) {
    	if (data==null) {
    		nPlayers = n;
    		data = new Gamedata();
    	}
    	return data;
    }
}
