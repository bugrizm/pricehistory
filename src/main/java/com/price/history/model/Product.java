package com.price.history.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategory productCategory;

	@ManyToOne
	private Store store;

	@OneToMany(mappedBy="product")
	private List<ProductPrice> productPrices;
	
	private boolean active;
	
	private String link;
	
	@Column(name="creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<ProductPrice> getProductPrices() {
		return this.productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public ProductPrice addProductPrice(ProductPrice productPrice) {
		getProductPrices().add(productPrice);
		productPrice.setProduct(this);

		return productPrice;
	}

	public ProductPrice removeProductPrice(ProductPrice productPrice) {
		getProductPrices().remove(productPrice);
		productPrice.setProduct(null);

		return productPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}