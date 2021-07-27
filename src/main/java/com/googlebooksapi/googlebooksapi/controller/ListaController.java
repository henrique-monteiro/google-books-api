package com.googlebooksapi.googlebooksapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlebooksapi.googlebooksapi.dto.BooksDTO;
import com.googlebooksapi.googlebooksapi.service.ListaService;


@Controller
@RequestMapping("lista")
public class ListaController {
	
	@Autowired
	private ListaService listaService;


	@GetMapping("/listaLivros")
	public String busca(String nomeLivro, Model model) {
		List<BooksDTO> listaLivros = listaService.listaLivros(nomeLivro);
//		model.addAttribute("books", listaLivros);)
		model.addAttribute("books", listaLivros);
		System.out.println("\nantes do return!!");
		return "listaLivros";
	}
}
