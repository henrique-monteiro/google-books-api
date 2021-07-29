package com.googlebooksapi.googlebooksapi.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.googlebooksapi.googlebooksapi.dto.BooksDTO;
import com.googlebooksapi.googlebooksapi.repository.LivroRepository;



@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public List<BooksDTO> listaLivros(String string) {			
		UriComponents url = constroeUrl(string);		//constroe url
		JSONObject json = consomeAPI(url);				//consome API em formato JSON
		return livroRepository.lista(json);
	}
	
	public BooksDTO detalhes(String id) {
		UriComponents url = constroeUrl(id);			//constroe url
		JSONObject json = consomeAPI(url);				//consome API em formato JSON
		BooksDTO detalhesLivro = livroRepository.detalhesLivro(json);				
		return detalhesLivro;
	}

	private JSONObject consomeAPI(UriComponents url) {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();
				
		String body = restTemplate.getForEntity(url.toString(), String.class).getBody();
		JSONObject json = new JSONObject(body);
		return json;
	}

	private UriComponents constroeUrl(String nomeLivro) {
		String nomeParametro = nomeLivro.replace(" ", "+");
		
		//https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=AIzaSyCFDNPNyLHN36rher3RVNbC-yxQm-Flyhw
		UriComponents url = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("www.googleapis.com/books")
				.path("v1/volumes")
				.queryParam("q", nomeParametro)
				.queryParam("key", "AIzaSyCFDNPNyLHN36rher3RVNbC-yxQm-Flyhw")
				.build();
		return url;
	}
}
