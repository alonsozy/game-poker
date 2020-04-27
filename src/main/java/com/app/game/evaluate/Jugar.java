package com.app.game.evaluate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.game.entity.Game;
import com.app.game.entity.Player;
import com.app.game.util.Constantes;
import com.app.game.util.LoadGames;

@Component
public class Jugar {

	@Value("${rutaFile}")
	String ruta;
	
	@Value("${rutaFileRes}")
	String rutaR;
	
	
	public void validarJuegos() {
		GameIntImpl evaluar = new GameIntImpl();
		int ganador_1=0;
		int ganador_2=0;
		int empates=0;
		try {
			System.out.println("Ruta: "+ruta);
			List<Game> juegos = LoadGames.load(ruta);
			for (Game g : juegos) {
				Player p = evaluar.evaluateGame(g.getPlayers());
				g.setWin(p);
				if(p!=null) {
					if(p.getName().equals(Constantes.nombre1)) ganador_1++;

					if(p.getName().equals(Constantes.nombre2)) ganador_2++;
				}else {
					empates++;
				}
				
			}
			System.out.println("Cant1: "+ganador_1+" Cant_2: "+ganador_2+" Empate: "+empates);
			LoadGames.writeR(ganador_1,ganador_2,empates,rutaR);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
