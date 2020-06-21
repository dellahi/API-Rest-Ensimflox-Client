package fr.ensim.interop.eval.exo2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Date;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import modelexo2.Plantation;


public class ClientRestExo2 {
	public static void main(String[] args) throws URISyntaxException {
		// Client REST du framework Spring
		RestTemplate restTemplate = new RestTemplate();
		
		 final String baseUrl = "http://localhost:9090/";
		 URI uri = new URI(baseUrl);
		 Plantation plantation_1 = new Plantation(new Date(), "nom_parcelle_1", "nom_commun_1","nom_famille_1");
		 
		 ResponseEntity<String> result = restTemplate.postForEntity(uri, plantation_1, String.class);
		 
		 //Verify request succeed
		    System.out.println("le code retour est : "+ result.getStatusCodeValue());
		 
		 //Ajouter plantation
		 Plantation plantation_2 = new Plantation(new Date(), "nom_parcelle_2", "nom_commun_2","nom_famille_2");
			 
		 result = restTemplate.postForEntity(uri, plantation_2, String.class);
			 
		 //Verify request succeed
			System.out.println("le code retour est : "+ result.getStatusCodeValue());
			
		//Ajouter plantation
		Plantation plantation_3 = new Plantation(new Date(), "nom_parcelle_3", "nom_commun_3","nom_famille_3");
				 
		result = restTemplate.postForEntity(uri, plantation_3, String.class);
				 
		//Verify request succeed
			System.out.println("le code retour est : "+ result.getStatusCodeValue());	
	   
		ResponseEntity<Plantation[]> response =
			restTemplate.getForEntity(
			 "http://localhost:9090/plantations/",
			 Plantation[].class);
			
		Plantation[] list_Plantations = response.getBody();
			
		for(Plantation p : list_Plantations) {
			System.out.println(p);
		}
		    
		 // Supprimer plantation
		restTemplate.delete("http://localhost:9090/plantation/delete/{id}", 3);
		System.out.println("DELETE");
		
		 response =
			restTemplate.getForEntity(
			 "http://localhost:9090/plantations/",
			 Plantation[].class);
		 
		list_Plantations = response.getBody();
		
		for(Plantation p : list_Plantations) {
			System.out.println(p);
		}

		
	}

}