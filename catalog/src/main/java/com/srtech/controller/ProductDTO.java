package com.srtech.controller;

import java.util.ArrayList;

import lombok.Data;
import lombok.ToString;

/*
 * "id": 1,
"title": "iPhone 9",
"description": "An apple mobile which is nothing like apple",
"price": 549,
"discountPercentage": 12.96,
"rating": 4.69,
"stock": 94,
"brand": "Apple",
"category": "smartphones",
"thumbnail": "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
"images": [
"https://i.dummyjson.com/data/products/1/1.jpg",
"https://i.dummyjson.com/data/products/1/2.jpg",
"https://i.dummyjson.com/data/products/1/3.jpg",
"https://i.dummyjson.com/data/products/1/4.jpg",
"https://i.dummyjson.com/data/products/1/thumbnail.jpg"
]
 */
@Data
@ToString
public class ProductDTO {

	public int id;
	public String title;
	public String description;
	public int price;
	public double discountPercentage;
	public double rating;
	public int stock;
	public String brand;
	public String category;
	public String thumbnail;
	public ArrayList<String> images;
}
