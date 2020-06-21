package fr.ensim.interop.eval.exo1;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;


@WebService
public class AvisService {

	// TODO Exercice 1 - Implémentation d’un Web Service SOAP d’avis consommateur

	private ArrayList<Avis> listAvis = new ArrayList<>();
	
	/*
	 * umcomment to fill the list with somerandom data 
	 */
	public AvisService() throws IllegalArgumentException, ParseException {
		super();
		listAvis.add(Avis.createRandomAvis("1", "1","10/10/2020"));		
		listAvis.add(Avis.createRandomAvis("2", "1","11/10/2020"));		
		listAvis.add(Avis.createRandomAvis("3", "1","9/10/2020"));		
		listAvis.add(Avis.createRandomAvis("4", "1","12/10/2020"));		
		listAvis.add(Avis.createRandomAvis("1", "2","8/10/2020"));		
		listAvis.add(Avis.createRandomAvis("1", "3","10/10/2020"));		
		listAvis.add(Avis.createRandomAvis("2", "2","5/10/2020"));		
		listAvis.add(Avis.createRandomAvis("3", "2","6/10/2020"));	
		
	}
	
	@WebMethod
	public int enregistrerAvis(
			@XmlElement(required=true) @WebParam(name="createdAt") String createdAt,
			@XmlElement(required=true) @WebParam(name="mark") int mark,
			@XmlElement(required=true) @WebParam(name="comment") String comment,
			@XmlElement(required=true) @WebParam(name="origin") String origin,
			@XmlElement(required=true) @WebParam(name="refProd") String refProd,
			@XmlElement(required=true) @WebParam(name="refProvider") String refProvider) 
					throws IllegalArgumentException, ParseException 
	{
		 
		System.out.println("-> enregistrerAvis ");
		
		Date createdAtDate= new SimpleDateFormat("dd/MM/yyyy").parse(createdAt);  

		Avis avis = new Avis(createdAtDate, mark, comment, origin, refProd, refProvider);
		listAvis.add(avis);
		//System.out.println(Arrays.toString(listAvis.toArray()));
		int num = nombreAvisParFournisseur(refProvider);

		return num;
	}
	
	@WebMethod
	public ArrayList<AvisSortant> listerAvis(@XmlElement(required=true) @WebParam(name="refProd") String refProduit){
		
		System.out.println("-> listerAvis ");

		ArrayList<AvisSortant> listAvisSortant = new ArrayList<>(); 
		
		for (Avis avis : listAvis) {
			if(avis.getRefProd().equals(refProduit)) {
				listAvisSortant.add(new AvisSortant(avis.getMark(),avis.getComment(),avis.getOrigin(),avis.getCreatedAt()));
			}
		}

		Collections.sort(listAvisSortant);
		Collections.reverse(listAvisSortant);
		
		return listAvisSortant;
	}
	
	/*
	 * get number of Avis for a given provider 
	 */
	private int nombreAvisParFournisseur(String refProvider) {
	
		int num = 0;
			
		for(Avis avis: listAvis) {
			if(avis.getRefProvider().equals(refProvider))
				num++;
		}
		return num;
	}
	
	
}

/*
 * represents output data 
 * a minimum version of Avis
 */
class AvisSortant implements Comparable<AvisSortant>{
	public int mark;
	public String comment;
	public String origin;
	public Date createdAt;
	
	public AvisSortant(int mark, String comment, String origin,Date createdAt) {
		super();
		this.comment = comment;
		this.mark = mark;
		this.origin = origin;
		this.createdAt = createdAt;
	}
	
	@Override
	public int compareTo(AvisSortant arg0) {
	    return createdAt.compareTo(arg0.createdAt);
	}
	

}
