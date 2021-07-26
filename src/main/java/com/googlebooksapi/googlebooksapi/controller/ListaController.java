package com.googlebooksapi.googlebooksapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlebooksapi.googlebooksapi.service.ListaService;


@Controller
@RequestMapping("lista")
public class ListaController {
	
	@Autowired
	private ListaService listaService;


	@GetMapping("/listaLivros")
	public String busca(String nomeLivro) {
		listaService.listaLivros(nomeLivro);
		
		return "listaLivros";
	}
}
