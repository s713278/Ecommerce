package com.srtech.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String title;
	@Column
	private String description;
	@Column
	private Double price;
	@Column
	private Double discountPercentage;
	@Column
	private Double rating;
	@Column
	private Integer stock;
	@Column
	private String brand;
	@Column
	private String category;
	@Column
	private String thumbnail;
	
	//@Transient
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@Column
	private List<Image> images;

	/*
	 * @Temporal(value = TemporalType.TIMESTAMP)
	 * 
	 * @CreatedDate private Date createdDateTime;
	 * 
	 * @Temporal(TemporalType.TIMESTAMP) private Date updateddDateTime;
	 */

}
