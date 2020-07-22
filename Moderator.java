
class Moderator implements Runnable {
    private Gamedata data;
    Thread th;

    public Moderator(Gamedata data, String name) {
        this.data = data;
        th = new Thread(this, name);
    }

    public void run() {
        synchronized (data.lock1) {
            while (!data.gameCompleteFlag && !data.numberFlag && data.numbersGen < 10) {
                int n = (int) (Math.random() * (50) + 1);
                data.numbersAnnounced.add(n);
                System.out.println("Moderator Generated: " + n);
                data.numbersGen++;
                data.numberFlag = true;
                data.lock1.notifyAll();
                try {
                     Thread.sleep(1000);
                } catch (InterruptedException e) {}
             
                while (data.numberFlag) {
                    try {
                        data.lock1.wait(3000);
                    } catch (InterruptedException e) {}
                }
                if (data.winner != 0) {
                    for (int k=0; k<Gamedata.nPlayers; ++k) {
                        System.out.println("Player " + (k+1) + " number of matches: "+ data.numWins[k]);
                    }
                    System.out.println("Player " + data.winner + " has won!");
                    System.out.println("Game Over");
                    data.gameCompleteFlag = true;
                    System.exit(0);
                }
                for (int j = 0; j < Gamedata.nPlayers; ++j) {
                    data.playerChanceFlag[j]=false;
                }
            }
            
            for (int k=0; k<Gamedata.nPlayers; ++k) {
            	System.out.println("Player " + (k+1) + " number of matches: "+ data.numWins[k]);
            }
            System.out.println("No one won");
            System.out.println("Game Over");
            System.exit(0);
            data.gameCompleteFlag = true;
            data.lock1.notifyAll();
        }
    }
}