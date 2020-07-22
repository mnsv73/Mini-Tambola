
public class GameProxy {
	//proxy pattern implemented
	private Gamedata data;
	private Moderator moderator;
	private Player[] players;
	
	public GameProxy(int n) {
		data = Gamedata.getInstance(n);
		moderator = new Moderator(data, "Moderator");
		players = new Player[Gamedata.nPlayers];
        for (int i = 0; i < Gamedata.nPlayers; ++i) {
            String name = "Player " + Integer.toString(i + 1);
            players[i] = new Player(name, data, i + 1);
            System.out.print(name + " tokens are: ");
            players[i].displayList();
        }
	}
	
	public void startModerator() {
		moderator.th.start();
	}
	public void startPlayers() {
		for (int i = 0; i < Gamedata.nPlayers; ++i) {
            players[i].th.start();
        }
	}

}
