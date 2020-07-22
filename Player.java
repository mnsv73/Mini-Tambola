import java.util.*;
import java.lang.Math;
public class Player implements Runnable{
	private int id;
    ArrayList<Integer> playerNumbers = new ArrayList<Integer>();
    private Gamedata data;
    Thread th;

    public Player(String name, Gamedata data, int id) {
        this.data = data;
        this.id = id;
        th = new Thread(this, name);
        int n = 0;
        while (n < 10) {
            int randomNum = (int) (Math.random() * (50) + 1);
            playerNumbers.add(randomNum);
            n++;
        }
    }

    public void displayList() {
        for (int i = 0; i < 10; ++i) {
            System.out.print(playerNumbers.get(i) + " ");
        }
        System.out.println();
    }

    public void run() {
        synchronized (data.lock1) {
            while (!data.gameCompleteFlag) {
                while (!data.numberFlag || data.playerChanceFlag[id-1]) {
                    try {
                        data.lock1.wait();
                    } catch (InterruptedException e) {
                    }
                }
                int lastNo = data.numbersAnnounced.get(data.numbersAnnounced.size() - 1);
                if (playerNumbers.contains(lastNo)) {
                    data.numWins[id-1]++;
                    playerNumbers.set(playerNumbers.indexOf(lastNo), -1);
                    if (data.numWins[id-1] == 3) {
                        data.winner = id;
                    }
                }
                
                data.playerChanceFlag[id-1]=true;
                if (checkPlayerFlag()) {
                    data.numberFlag=false;
                }
                data.lock1.notifyAll();
            }
        }
    }
    private boolean checkPlayerFlag() {
    	for (int i=0; i<Gamedata.nPlayers; ++i) {
    		if (data.playerChanceFlag[i]==false) {
    			return false;
    		}
    	}
    	return true;
    }
}
