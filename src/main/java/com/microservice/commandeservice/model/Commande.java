package com.microservice.commandeservice.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Commande {

	public Commande(int id, int idArticle, int prix, int quantity, int date, String state)  {
		super();
		this.id = id;
		this.idArticle = idArticle;
		this.prix = prix;
		this.quantity = quantity;
		this.date = date;
		this.state = CommandStateEnum.valueOf(state);
	}
	public Commande() {

	}

	public enum CommandStateEnum {
    ATT_PAIE,
	  EXPE,
    PREP
	}

	@Override
	public String toString() {
		return "Commande :" +
				"\n\tid : " + this.id +
				"\n\tidArticle : " + this.idArticle +
				"\n\tprix : " + this.prix+
        "\n\tquantity : " + this.quantity +
        "\n\tdate : " + this.date +
        "\n\tstate : " + this.state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
  public int getQuantity() { return quantity;}
  public void setQuantity(int quantity) { this.quantity = quantity; }
  public int getDate() { return date;}
  public void setDate(int date) { this.date = date; }
  public CommandStateEnum getState() { return state;}
  public void setState(CommandStateEnum state) { this.state = state; }
	@Id
	//@GeneratedValue
	private int id;
	private int idArticle;
	private int prix;
  private int quantity;
  private int date;
  private CommandStateEnum state;

}
