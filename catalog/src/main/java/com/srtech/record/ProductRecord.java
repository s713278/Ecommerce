package com.srtech.record;

/*
"id": 6,
"title": "MacBook Pro",
"description": "MacBook Pro 2021 with mini-LED display may launch between September, November",
"price": 1749,
"discountPercentage": 11.02,
"rating": 4.57,
"stock": 83,
"brand": "Apple",
"category": "laptops",
"thumbnail": "https://i.dummyjson.com/data/products/6/thumbnail.png",
"images": [
  "https://i.dummyjson.com/data/products/6/1.png",
  "https://i.dummyjson.com/data/products/6/2.jpg",
  "https://i.dummyjson.com/data/products/6/3.png",
  "https://i.dummyjson.com/data/products/6/4.jpg"
]
*/
public record  ProductRecord(Integer id,String title,String desc,float price,float discountPrice,float rating,Long stock,
		String brand,String caregory,String thumbnail,String[] iamges) {

}
