package com.googlebooksapi.googlebooksapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.googlebooksapi.googlebooksapi.dto.BooksDTO;

@Service
public class LivroRepository {
	
	List<BooksDTO> books = new ArrayList<>();
	JSONArray jsonItems = null;
	
	public List<BooksDTO> lista(JSONObject json){
		try {
			jsonItems = json.getJSONArray("items");		//cada item do array é um book
		} catch(JSONException e) {		
			System.out.println("\n");					//tratar essa exception
			System.out.println(e.getMessage());
			return null;
		}
		
								
		
		List<JSONObject> itemsJson = new ArrayList<>();
		for (int i=0; i<jsonItems.length(); i++) {	
			itemsJson.add(jsonItems.getJSONObject(i));			//cada item da lista torna-se um JSONObject para buscar "volumeInfo"
		}	
		
		for (JSONObject itemJson : itemsJson) {
			BooksDTO livro = new BooksDTO();
			livro.setId(itemJson.getString("id"));
			livro.setUrlJson(itemJson.getString("selfLink"));
			
			JSONObject volumeInfoJson = new JSONObject();
			volumeInfoJson = (JSONObject) itemJson.get("volumeInfo");
			setAtributosDeInteresse(volumeInfoJson, livro);
			
			books.add(livro);
			
			System.out.println(livro.getId());
			System.out.println(livro.getUrlJson());			
			
		}
		
		return books;
		
	}
	
	public BooksDTO detalhesLivro(JSONObject json) {
		BooksDTO livro = new BooksDTO();
		JSONArray items = json.getJSONArray("items");		
		
		JSONObject item = new JSONObject();
		item = (JSONObject) items.get(0);		
		
		JSONObject volumeInfoJson = new JSONObject();
		volumeInfoJson = item.getJSONObject("volumeInfo");		
		
		setAtributosDeInteresse(volumeInfoJson, livro);
		
		return livro;
	}
	
	private void setAtributosDeInteresse(JSONObject volumeInfo, BooksDTO one) {
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
			JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
			one.setBookCoverImg(imageLinks.getString("smallThumbnail")); 
			
		} catch (JSONException e) {
			one.setBookCoverImg("Nenhuma imagem disponível");				
		}
		
	}
}
