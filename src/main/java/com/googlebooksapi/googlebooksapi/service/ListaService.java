package com.googlebooksapi.googlebooksapi.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.googlebooksapi.googlebooksapi.dto.BooksDTO;



@Service
public class ListaService {

	public List<BooksDTO> listaLivros(String nomeLivro) {		
		
		
		UriComponents url = controeUrl(nomeLivro);				//constroe url
		
		JSONObject json = consomeAPI(url);						//consome API em formato JSON
		
		JSONArray jsonItems = json.getJSONArray("items");		//cada item do array é um book
						
		
		List<JSONObject> items = new ArrayList<>();
		for (int i=0; i<jsonItems.length(); i++) {	
			items.add(jsonItems.getJSONObject(i));				//cada item da lista torna-se um JSONObject para buscar "volumeInfo"
		}		
		
		List<Object> volumeInfoObject = new ArrayList<>();
		for (int i=0; i<items.size(); i++) {			
			volumeInfoObject.add(items.get(i).get("volumeInfo")); 
		}
		
		List<JSONObject> volumeInfoJson = new ArrayList<>();
		for (Object object: volumeInfoObject) {
			volumeInfoJson.add((JSONObject) object);
		}

		List<BooksDTO> books = new ArrayList<>();
		for (JSONObject volumeInfo : volumeInfoJson) {
			BooksDTO one = new BooksDTO();
			
			buscaAtributosDeInteresse(volumeInfo, one);	//busca title, description, publishedDate e bookCover
			
			books.add(one);		

		}
		
		for (int i=0; i< books.size(); i++) {
			System.out.println(books.get(i));
			System.out.println("\n");
		}
		
		return books;
		
	}

	private void buscaAtributosDeInteresse(JSONObject volumeInfo, BooksDTO one) {
		System.out.println(volumeInfo.toString());
		try {
			one.setTitle(volumeInfo.getString("title"));
		} catch (JSONException e) {
			one.setTitle("");				
		}
		
		try {
			one.setDescription(volumeInfo.getString("description"));
		} catch (JSONException e) {
			one.setDescription("");				
		}
		
		try {
			one.setPublishedDate(volumeInfo.getString("publishedDate"));
		} catch (JSONException e) {
			one.setPublishedDate("");				
		}
		
		try {
			System.out.println("entrou no try\n");
			JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
			one.setBookCoverImg(imageLinks.getString("smallThumbnail")); 
			
		} catch (JSONException e) {
			one.setBookCoverImg("");				
		}
	}

	private JSONObject consomeAPI(UriComponents url) {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();
				
		String body = restTemplate.getForEntity(url.toString(), String.class).getBody();
		JSONObject json = new JSONObject(body);
		return json;
	}

	private UriComponents controeUrl(String nomeLivro) {
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
