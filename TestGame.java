
import java.io.*;
public class TestGame {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the number of players: ");
		int nPlayers = Integer.parseInt(br.readLine());
		//int nPlayers = 5;
		GameProxy game = new GameProxy(nPlayers);
		game.startModerator();
		game.startPlayers();
	}
}
