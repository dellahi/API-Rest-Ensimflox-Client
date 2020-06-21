package fr.ensim.interop.eval.exo1;

import java.text.ParseException;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AvisServiceRunner {


	public static void main(String[] args) throws IllegalArgumentException, ParseException {
		SpringApplication.run(AvisServiceRunner.class, args);
		String url = "http://localhost:8080/AvisService";
		System.out.println("Web service disponible sur l'adresse " + url);
		Endpoint.publish(url, new AvisService());
	
	}
	
}
