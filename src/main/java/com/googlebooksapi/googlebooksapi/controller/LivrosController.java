package com.googlebooksapi.googlebooksapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.googlebooksapi.googlebooksapi.dto.BooksDTO;
import com.googlebooksapi.googlebooksapi.service.LivroService;


@Controller
@RequestMapping(value="/lista")
public class LivrosController {
	
	@Autowired
	private LivroService livroService;


	@GetMapping
	public String busca(String nomeLivro, Model model) {
		List<BooksDTO> listaLivros = livroService.listaLivros(nomeLivro);
		model.addAttribute("books", listaLivros);
		return "listaLivros";
	}
	
	
	@GetMapping("/{id}")
	public String detalhes(@PathVariable("id") String id, Model model ) {
		BooksDTO detalhesLivro = livroService.detalhes(id);
		model.addAttribute("book", detalhesLivro);
		return "detalhesLivro";
	}
}
