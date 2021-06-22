package br.com.supera.gamestore.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Comparable<Product> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "{productName.not.blank}")
	@Size(min = 2, max = 50, message = "{productName.size}")
	private String name;
	
	@NotNull(message = "{price.not.null}")
	@Min(value = 1, message = "{price.min}")
	@Max(value = 10000, message = "{price.max}")
	private BigDecimal price;
	
	@NotNull(message = "{score.not.null}")
	@Min(value = 1, message = "{score.min}")
	@Max(value = 1000, message = "{score.max}")
	private short score;
	private String image;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public short getScore() {
		return score;
	}
	
	public void setScore(short score) {
		this.score = score;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + score;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	@Override
	public int compareTo(Product otherProduct) {
		if (this.price.compareTo(otherProduct.getPrice()) == -1) {
			return 1;
		} else if (this.price.compareTo(otherProduct.getPrice()) == 1) {
			return -1;
		}
		return 0;
	}
}
