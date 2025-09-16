package com.appsoft.springproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="product_tbl")
public class Product {

	@Id
	private int id;
	private String title;
	private double price;
	@Column(columnDefinition = "longtext")//as string can't handle paragraph
	private String description;
	private String category;
	private String image;//in double quotation
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="ratingId")
	private Rating rating;

}
