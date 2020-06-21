package modelexo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class PlantationImpl implements IPlantation {
	
	private Map<Long, Plantation> listPlantations = new HashMap<Long, Plantation>();

	@Override
	public Plantation ajouterPlantation(Plantation p) {
		listPlantations.put(p.getId(), p);
		return p;
	}

	@Override
	public boolean supprimerPlantation(long pId) {
		if(listPlantations.get(pId)!=null) {
			listPlantations.remove(pId);
			return true;
		}
		else throw new RuntimeException("Plantation introuvable");
	}

	@Override
	public List<Plantation> listerPlantation() {
		List<Plantation> plants = new ArrayList<Plantation>();
		for(Plantation p : listPlantations.values()) {
			plants.add(p);
		}
		return plants;
		
	}

}
