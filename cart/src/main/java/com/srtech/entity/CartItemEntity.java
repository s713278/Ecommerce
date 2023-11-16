package com.srtech.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CartItemEntity extends AbstractAuditingEntity<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3303602146285965443L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private Integer productId;
	
	@Column
	private Integer skuId;
	
	@Column
	private Integer quantity;
	
	@Column
	private Double amount; // qty&sku's list price
}
