package com.googlebooksapi.googlebooksapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController faz com o que return devolva uma string na pagina, ou seja, nao chama o template busca.html
@RequestMapping(value="/busca")
public class BuscaController {	
	
	@GetMapping
	public String home() {
		return "busca"; 
	}
}
