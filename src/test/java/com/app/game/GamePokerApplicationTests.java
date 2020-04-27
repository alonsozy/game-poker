package com.app.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.game.entity.Card;
import com.app.game.entity.Player;
import com.app.game.evaluate.GameIntImpl;
import com.app.game.util.Constantes;

@SpringBootTest
class GamePokerApplicationTests {

	@Test
	void contextLoads() {
		GameIntImpl a = new GameIntImpl();

		List<Player> jugadores = new ArrayList<Player>();

		Player p1 = new Player();
		List<Card> mano = new ArrayList<Card>();
		mano.add(new Card("3", "H"));
		mano.add(new Card("4", "H"));
		mano.add(new Card("5", "H"));
		mano.add(new Card("6", "H"));
		mano.add(new Card("7", "H"));
		p1.setHand(mano);
		p1.setName("Jugador_1");
		jugadores.add(p1);

		Player p2 = new Player();
		List<Card> mano2 = new ArrayList<Card>();
		mano2.add(new Card("4", "H"));
		mano2.add(new Card("5", "H"));
		mano2.add(new Card("6", "H"));
		mano2.add(new Card("7", "H"));
		mano2.add(new Card("8", "H"));
		p2.setHand(mano2);
		p2.setName("Jugador_2");
		jugadores.add(p2);

		Player ganador = a.evaluateGame(jugadores);

		assertEquals("Jugador_2", ganador.getName());

	}
	
	@Test
	void ganador_Royal_Flush() {
		GameIntImpl a = new GameIntImpl();

		List<Player> jugadores = new ArrayList<Player>();

		Player p1 = new Player();
		List<Card> mano = new ArrayList<Card>();
		mano.add(new Card("3", "H"));
		mano.add(new Card("4", "H"));
		mano.add(new Card("5", "H"));
		mano.add(new Card("6", "H"));
		mano.add(new Card("7", "H"));
		p1.setHand(mano);
		p1.setName("Jugador_1");
		jugadores.add(p1);

		Player p2 = new Player();
		List<Card> mano2 = new ArrayList<Card>();
		mano2.add(new Card("10", "H"));
		mano2.add(new Card("J", "H"));
		mano2.add(new Card("Q", "H"));
		mano2.add(new Card("K", "H"));
		mano2.add(new Card("A", "H"));
		p2.setHand(mano2);
		p2.setName("Jugador_2");
		jugadores.add(p2);

		Player ganador = a.evaluateGame(jugadores);

		assertEquals(Constantes.JUGADA.ROYAL_FLUSH.getCodigo(), ganador.getIdJugada());

	}

}
