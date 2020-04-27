package com.app.game.entity;

import java.util.List;

public class Game {

	private List<Player> players;
	private String winner;
	private Player win;

	public Game() {
	}

	public Game(List<Player> players, String winner) {
		this.players = players;
		this.winner = winner;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public Player getWin() {
		return win;
	}

	public void setWin(Player win) {
		this.win = win;
	}

}
