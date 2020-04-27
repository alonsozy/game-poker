package com.app.game.util;

public class Constantes {

	public static String nombre1="Player_1";
	public static String nombre2="Player_2";
	
	public static enum JUGADA{// numero
        HIGH_CARD(1, "HIGH_CARD"),
        ONE_PAIR(2, "ONE_PAIR"),
        TWO_PAIRS(3, "TWO_PAIRS"),
        THREE_OF_KIND(4, "THREE_OF_KIND"),
        STRAIGHT(5, "STRAIGHT"),
		FLUSH(6, "FLUSH"),
		FULL_HOUSE(7, "FULL_HOUSE"),
		FOUR_OF_KIND(8, "FOUR_OF_KIND"), 
		STRAIGHT_FLUSH(9, "STRAIGHT_FLUSH"),  
		ROYAL_FLUSH(10, "ROYAL_FLUSH");  
        
        private int codigo;
        private String message;
        
        private JUGADA(int codigo, String message){
            this.codigo = codigo;
            this.message = message;
        }

		public int getCodigo() {
			return codigo;
		}

		public String getMessage() {
			return message;
		}    
        
    } 
	
}
