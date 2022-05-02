package com.upc.ia.entity;

import javax.persistence.*;

@Entity
@Table(name = "car_plate")
public class CarPlate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;

	@Column(name = "brand")
	private String brand;

	@Column(name = "keyf")
	private String key;

	@Lob
	@Column(name = "imageBase64", columnDefinition="BLOB")
	private String imageBase64;

	@Column(name = "size")
	private String size;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public CarPlate() {
		super();
	}

	public CarPlate(String description, String image,String brand,String key, String imageBase64, String size) {
		this.description = description;
		this.image = image;
		this.brand=brand;
		this.key=key;
		this.imageBase64=imageBase64;
		this.size=size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "CarPlate{" +
				"id=" + id +
				", description='" + description + '\'' +
				", image='" + image + '\'' +
				", brand='" + brand + '\'' +
				", key='" + key + '\'' +
				", imageBase64='" + imageBase64 + '\'' +
				", size='" + size + '\'' +
				'}';
	}
}
