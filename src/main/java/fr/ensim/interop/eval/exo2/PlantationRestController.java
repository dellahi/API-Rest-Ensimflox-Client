package fr.ensim.interop.eval.exo2;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import modelexo2.Plantation;
import modelexo2.PlantationImpl;

//import javax.ws.rs.Path;
//@Path("/plantation")

@RestController
public class PlantationRestController {

	// TODO Exercice 2 - Implémentation d'une API REST pour la gestion de plantation
	
	private PlantationImpl model;
	
	// Simulation d'un séquenceur pour générer l'Id des plantations
    private AtomicInteger fakeSeq = new AtomicInteger(0);

	
	public PlantationRestController() {
		model = new PlantationImpl();
	}
	
	@GetMapping("/plantations")
	public List<Plantation> listerPlantations(){
		return model.listerPlantation();
	}
	
	@PostMapping(path= "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Plantation> ajouterPlantation(@RequestBody Plantation p) {
		p.setId(fakeSeq.incrementAndGet());
		// URI de localisation de la ressource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(p.getId());
        
        model.ajouterPlantation(p);

        // réponse 201 avec la localisation et la ressource créée
        return ResponseEntity.created(location).body(p);
		
	}
	
	
	
	@DeleteMapping("/plantation/delete/{id}")
	public  ResponseEntity<Plantation> supprimerPlantation(@PathVariable("id") @NotNull long pId) {
		model.supprimerPlantation(pId);
		return ResponseEntity.noContent().build();
	}
	
}