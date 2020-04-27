package com.app.game.entity;

import java.util.List;

import com.google.gson.Gson;

public class Player {

	private String name;
	private List<Card> hand;
	private int idJugada;

	public Player() {
	}

	public Player(String name, List<Card> hand) {
		this.name = name;
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public int getIdJugada() {
		return idJugada;
	}

	public void setIdJugada(int idJugada) {
		this.idJugada = idJugada;
	}

	@Override
	public String toString() {
		return "Name: "+name +" - Hand: "+new Gson().toJson(hand);
	}

}
