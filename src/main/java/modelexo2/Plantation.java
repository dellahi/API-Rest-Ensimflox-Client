package modelexo2;

import java.io.Serializable;
import java.util.Date;


public class Plantation implements Serializable {
	private long id;
	private Date date;
	private String parcelle;
	private String nomCommun;
	private String famille;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	} 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getParcelle() {
		return parcelle;
	}
	public void setParcelle(String parcelle) {
		this.parcelle = parcelle;
	}
	public String getNomCommun() {
		return nomCommun;
	}
	public void setNomCommun(String nomCommun) {
		this.nomCommun = nomCommun;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public Plantation(Date date, String parcelle, String nomCommun, String famille) {
		super();
		this.date = date;
		this.parcelle = parcelle;
		this.nomCommun = nomCommun;
		this.famille = famille;
	}
	public Plantation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return "" + getId()  + "  " + getDate() + "  " + getParcelle() + "  " + getNomCommun() + "  " + getFamille() + "  ";
	}
	
	
	

}
