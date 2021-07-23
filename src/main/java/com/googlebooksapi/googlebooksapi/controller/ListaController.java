package com.googlebooksapi.googlebooksapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("lista")
public class ListaController {


	@GetMapping("/listaLivros")
	public String busca(String nomeLivro) {
		System.out.println("entrou em lista " + nomeLivro);
		
		return "listaLivros";
	}
}
