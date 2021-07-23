package com.googlebooksapi.googlebooksapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BuscaController {	
	
	@GetMapping("/busca")
	public String home() {
		return "busca"; 
	}
}
