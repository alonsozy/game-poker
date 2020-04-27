package com.app.game.entity;

import javax.validation.constraints.Pattern;

public class Card {

	@Pattern(regexp = "S|D|H|C")
	private String type;

	@Pattern(regexp = "1|2|3|4|5|6|7|8|9|J|Q|K|A")
	private String value;

	private int id;

	public Card() {
	}

	public Card(@Pattern(regexp = "2|3|4|5|6|7|8|9|10|J|Q|K|A") String value, @Pattern(regexp = "S|D|H|C") String type) {
		this.type = type;
		this.value = value;
		if(value.equals("J")) {
			this.id=11;
		}else if (value.equals("Q")) {
			this.id=12;
		}else if (value.equals("K")) {
			this.id=13;
		}else if (value.equals("A")) {
			this.id=14;
		}else {
			try {
				this.id=Integer.parseInt(value);
			}catch(Exception e) {
				this.id=0;
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return value + " " + type;
	}

}
