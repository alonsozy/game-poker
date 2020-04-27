package com.app.game.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.game.entity.Card;
import com.app.game.entity.Game;
import com.app.game.entity.Player;

@Component
public class LoadGames {

	
	
	public static List<Game> load(String ruta) throws Exception {
		FileInputStream fstream = new FileInputStream(ruta);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		List<Game> games=new ArrayList<Game>();
		while ((strLine = br.readLine()) != null)   {
		  String lineC = strLine.toString();
			System.out.println(lineC);
			String jugadas[] = lineC.split(" ");
			Game game = new Game();
			Player p1= new Player();
			p1.setName(Constantes.nombre1);
			List<Card> j1= new ArrayList<Card>();
			Player p2= new Player();
			p2.setName(Constantes.nombre2);
			List<Card> j2= new ArrayList<Card>();
			int i=0;
			for (String j : jugadas) {
				Card c= new Card(j.substring(0, 1), j.substring(1,2));
				if(i<5) {
					j1.add(c);
				}else {
					j2.add(c);					
				}
				i++;				
			}
			p1.setHand(j1);
			p2.setHand(j2);
			List<Player> ps= new ArrayList<Player>();
			ps.add(p1);
			ps.add(p2);
			game.setPlayers(ps);
			games.add(game);
			//System.out.println(new Gson().toJson(game));
			
		}

		fstream.close();
		return games;
	}

	
	public static void writeR(int i,int j,int o,String ruta) {
		
		File fichero = new File(ruta);
		fichero.delete();
		
		
		try(FileWriter fw = new FileWriter(ruta, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			    {
			        out.println("1: "+i);
			        out.println("2: "+j);
			        out.println("3: "+o);
			} catch (IOException e) {
			}
	}

}
