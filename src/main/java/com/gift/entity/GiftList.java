package com.gift.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


@Entity
public class GiftList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	private String description;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	public GiftList(int id, String name, int price, String description, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
	}

	public GiftList() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {  
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
