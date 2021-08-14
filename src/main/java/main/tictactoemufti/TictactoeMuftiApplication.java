package main.tictactoemufti;

import game.GameEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TictactoeMuftiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictactoeMuftiApplication.class, args);
		try {
			GameEngine gameEngine = new GameEngine();
			gameEngine.registerPlayers();
			gameEngine.startGame();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}
	}

}
