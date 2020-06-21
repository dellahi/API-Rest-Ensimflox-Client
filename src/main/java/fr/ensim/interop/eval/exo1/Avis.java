package fr.ensim.interop.eval.exo1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Avis implements Comparable<Avis>  {

/*
 *Date/Heure de l’avis date/heure
 *Note entier (de 0 à 10 inclus)
 *Commentaire chaîne de caractères (longueur max 300, caractères libres)
 *Provenance de l’avis code pays (ISO 3166-1 alpha-2)
 *Référence produit chaîne de caractères (longueur max 20, caractères alphanumériques uniquement)
 *Référence du fournisseur chaîne de caractères (longueur max 15, caractères alphanumériques uniquement)
 */
	
	private Date createdAt;
	private int mark;
	private String comment;
	private String origin;
	private String refProd;
	private String refProvider;
	
	
	@Override
	public String toString() {
		return "Avis [createdAt=" + createdAt + ", mark=" + mark + ", comment=" + comment + ", origin=" + origin
				+ ", refProd=" + refProd + ", refProvider=" + refProvider + "]";
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) throws IllegalArgumentException {
		if(mark<0 || mark>10) throw new IllegalArgumentException("Avis.mark should be between 0 and 10");
		this.mark = mark;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) throws IllegalArgumentException {
		if(comment.length()>300) throw new IllegalArgumentException("Avis.comment should be under 300 character");
		this.comment = comment;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getRefProd() {
		return refProd;
	}
	public void setRefProd(String refProd) throws IllegalArgumentException {
		if(refProd.length()>20 || !refProd.matches("[A-Za-z0-9]+") ) throw new IllegalArgumentException("Avis.refProd should be under 20 character and alphanumeric");
		this.refProd = refProd;
	}

	
	public String getRefProvider() {
		return refProvider;
	}
	public void setRefProvider(String refProvider) throws IllegalArgumentException {
		if(refProvider.length()>15 || !refProvider.matches("[A-Za-z0-9]+") ) throw new IllegalArgumentException("Avis.refProvider should be under 15 character and alphanumeric");
		this.refProvider = refProvider;
	}
	
	/**
	 * @param createdAt
	 * @param mark
	 * @param comment
	 * @param origin
	 * @param refProd
	 * @param refProvider
	 * @throws IllegalArgumentException 
	 */
	public Avis(Date createdAt, 
			int mark, 
			String comment, 
			String origin, 
			String refProd, 
			String refProvider) throws IllegalArgumentException {
		super();
		this.setCreatedAt(createdAt);
		this.setMark(mark);
		this.setComment(comment);
		this.setOrigin(origin);
		this.setRefProd(refProd);
		this.setRefProvider(refProvider);
	}
	
	public static Avis createRandomAvis(String refProd,String refProvider,String createdAt) throws IllegalArgumentException, ParseException {
		
		return new Avis(new SimpleDateFormat("dd/MM/yyyy").parse(createdAt),
				(int)(Math.random() * 10),
				"random comment created to fill the gap don't mind me ",
				"ClassAvis :p",
				refProd,
				refProvider);
	}

	@Override
	public int compareTo(Avis arg0) {
	    return getCreatedAt().compareTo(arg0.getCreatedAt());
	}
	
	
	
}
