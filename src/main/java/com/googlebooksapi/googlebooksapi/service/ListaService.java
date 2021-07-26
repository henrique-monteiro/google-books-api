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

	public void listaLivros(String nomeLivro) {		
		String nomeParametro = nomeLivro.replace(" ", "+");
		
		//https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=AIzaSyCFDNPNyLHN36rher3RVNbC-yxQm-Flyhw
		UriComponents url = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("www.googleapis.com/books")
				.path("v1/volumes")
				.queryParam("q", nomeParametro)
				.queryParam("key", "AIzaSyCFDNPNyLHN36rher3RVNbC-yxQm-Flyhw")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();
				
		String body = restTemplate.getForEntity(url.toString(), String.class).getBody();
		JSONObject json = new JSONObject(body);
		
		JSONArray jsonItems = json.getJSONArray("items");	//cada item do array é um book
						
		
		List<JSONObject> items = new ArrayList<>();
		for (int i=0; i<jsonItems.length(); i++) {	
			items.add(jsonItems.getJSONObject(i));	//cada item da lista torna-se um JSONObject para buscar "volumeInfo"
		}
		
		System.out.println(items.size() + "\n");		
		
		List<Object> volumeInfoObject = new ArrayList<>();
		for (int i=0; i<items.size(); i++) {			
			volumeInfoObject.add(items.get(i).get("volumeInfo")); 
		}
		
		List<JSONObject> volumeInfoJson = new ArrayList<>();
		for (Object object: volumeInfoObject) {
			volumeInfoJson.add((JSONObject) object);
		}
		
		for (JSONObject jsonObject : volumeInfoJson) {
			System.out.println(jsonObject);
		}

		
		BooksDTO one = new BooksDTO();
		List<BooksDTO> books = new ArrayList<>();
		for (JSONObject volumeInfo : volumeInfoJson) {
			
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
			
			books.add(one);		
			System.out.println(one);
			System.out.println("\n");
		}
		
		System.out.println(books.size());
		
		for (int i=0; i< books.size(); i++) {
			System.out.println(books.get(i));
			System.out.println("\n");
		}
		
	}
}
