package com.ecommerce.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author jfvaldez
 */
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priceList;

	@ManyToOne (cascade=CascadeType.ALL)
	private Brand brand;

	private Long productId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Integer priority;

	private Double price;

	private String currency;

}
