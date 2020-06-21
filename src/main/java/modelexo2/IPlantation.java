package modelexo2;

import java.util.List;

public interface IPlantation {
	public Plantation ajouterPlantation(Plantation p);
	public boolean supprimerPlantation(long pId);
	public List<Plantation> listerPlantation();
	
}
