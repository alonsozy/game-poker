package com.app.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.game.evaluate.Jugar;

@RestController
@RequestMapping(value = "/api/v1/game")
public class GameController {

	@Autowired
	Jugar jugar;
	
	
	@GetMapping(value = "/evaluar", produces = MediaType.APPLICATION_JSON_VALUE)
	public String listaD1() {
		jugar.validarJuegos();
		return "Se proceso correctamente";
	}
}
