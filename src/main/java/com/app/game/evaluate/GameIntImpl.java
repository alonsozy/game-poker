package com.app.game.evaluate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.app.game.entity.Card;
import com.app.game.entity.Player;
import com.app.game.util.Constantes;

@Component
public class GameIntImpl implements GameInt {

	@Override
	public Player evaluateGame(List<Player> players) {
		Player winner = null;
		System.out.println("Evaluando la jugada");
		for (Player a : players) {
			int idJug = jugada(a.getHand());
			a.setIdJugada(idJug);
		}

		Map<Integer, Long> countForId = players.stream()
				.collect(Collectors.groupingBy(Player::getIdJugada, Collectors.counting()));
		
		System.out.println("jugadas: "+countForId);
		if(countForId.size()==1) {
			System.out.println("hay empate");
			for(Player p: players) {
				p.setHand(p.getHand().stream().sorted(Comparator.comparing(Card::getId)).collect(Collectors.toList()));
			}
			boolean encontro=false;
			int i=0;
			while(i<5 && !encontro) {
				
				if(players.get(0).getHand().get(i).getId()>players.get(1).getHand().get(i).getId()) {
					winner=players.get(0);
					encontro=true;
					break;
				}else if(players.get(0).getHand().get(i).getId()<players.get(1).getHand().get(i).getId()) {
					winner=players.get(1);
					encontro=true;
					break;
				}else {
					
				}
				i++;
			}
			if(!encontro) {
				System.out.println("EMPATE GLOBAL");
				return null;
			}
			
			
		}else {
			winner = players
				      .stream()
				      .max(Comparator.comparing(Player::getIdJugada))
				      .orElseThrow(NoSuchElementException::new);
			
			
		}
		System.out.println("ganador: "+winner.toString());
		return winner;
	}

	public int jugada(List<Card> p) {

		int idJugada = 0;

		List<Card> sortedList = p.stream().sorted(Comparator.comparing(Card::getId)).collect(Collectors.toList());

		System.out.println("identificando la jugada");
		System.out.println("jugada ordenada: " + sortedList);
		long contTwoCardSV = 0;
		long maxSame = 0;
		long countPairs = 0;
		String value = "";
		int id = -1;

		boolean esConsecutivo = true;
		for (Card c : sortedList) {

			if (id != -1 && esConsecutivo) {
				if (!(id + 1 == c.getId())) {
					esConsecutivo = false;
				}

			}
			System.out.println("---*" + c.toString());
			if (!c.getValue().equals(value)) {
				contTwoCardSV = p.stream().filter(x -> (x.getValue().equals(c.getValue()))).count();
				value = c.getValue();
				if (contTwoCardSV == 2) {
					countPairs++;
				}
				if (contTwoCardSV > maxSame) {
					maxSame = contTwoCardSV;
				}
			}
			id = c.getId();
		}

		if (maxSame == 1) {

			Map<String, Long> countForId = sortedList.stream()
					.collect(Collectors.groupingBy(Card::getType, Collectors.counting()));
			System.out.println("aaa: " + countForId);
			if (countForId.size() == 1 && esConsecutivo) {
				
				
				if (sortedList.get(0).getId() == 10) {
					idJugada = Constantes.JUGADA.ROYAL_FLUSH.getCodigo();
					System.out.println("-----------JUGADA Es royal flush");
				} else {
					idJugada = Constantes.JUGADA.STRAIGHT_FLUSH.getCodigo();
					System.out.println("-----------JUGADA Es straight flush");
				}
				
			} else if (countForId.size() > 1 && esConsecutivo) {
				idJugada = Constantes.JUGADA.STRAIGHT.getCodigo();
				System.out.println("-----------JUGADA Es straight");
			} else if (countForId.size() == 1 && !esConsecutivo) {

				idJugada = Constantes.JUGADA.FLUSH.getCodigo();
				System.out.println("-----------JUGADA Es flush");
				
				
			} else if(countForId.size() > 1 && !esConsecutivo) {
				idJugada = Constantes.JUGADA.HIGH_CARD.getCodigo();
				System.out.println("-----------JUGADA High card");
			}

		}

		//System.out.println("*****: " + maxSame + " countPairs: " + countPairs + " consecutivo: " + esConsecutivo);

		if (countPairs == 2) {
			idJugada = Constantes.JUGADA.TWO_PAIRS.getCodigo();
			System.out.println("-----------JUGADA TWO PAIRS");
		} else {
			if (maxSame == 2) {
				idJugada = Constantes.JUGADA.ONE_PAIR.getCodigo();
				System.out.println("-----------JUGADA ONE PAIR");

			} else if (maxSame == 3) {

				Map<String, Long> countForId = sortedList.stream()
						.collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
				System.out.println("aaa: " + countForId);
				if (countForId.size() == 2) {
					idJugada = Constantes.JUGADA.FULL_HOUSE.getCodigo();
					System.out.println("-----------JUGADA full house");
				} else {
					idJugada = Constantes.JUGADA.THREE_OF_KIND.getCodigo();
					System.out.println("-----------JUGADA three of kind");
				}
			} else if (maxSame == 4) {
				idJugada = Constantes.JUGADA.FOUR_OF_KIND.getCodigo();
				System.out.println("-----------JUGADA four of a kind");
			} else if (maxSame == 1 && !esConsecutivo) {
				
			}
		}

		return idJugada;
	}

	/*public static void main(String[] args) {
		GameIntImpl a = new GameIntImpl();
		
		List<Player> jugadores = new ArrayList<Player>();
		
		Player p1=new Player();
		List<Card> mano = new ArrayList<Card>();
		mano.add(new Card("3", "H"));
		mano.add(new Card("4", "H"));
		mano.add(new Card("5", "H"));
		mano.add(new Card("6", "H"));
		mano.add(new Card("7", "H"));
		p1.setHand(mano);
		p1.setName("Jugador 1");
		jugadores.add(p1);

		Player p2=new Player();
		List<Card> mano2 = new ArrayList<Card>();
		mano2.add(new Card("3", "H"));
		mano2.add(new Card("4", "H"));
		mano2.add(new Card("5", "H"));
		mano2.add(new Card("6", "H"));
		mano2.add(new Card("7", "H"));
		p2.setHand(mano2);
		p2.setName("Jugador 2");
		jugadores.add(p2);
		
		//a.jugada(mano);
		
		a.evaluateGame(jugadores);
	}*/
}
