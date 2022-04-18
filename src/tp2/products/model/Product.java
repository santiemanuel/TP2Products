package tp2.products.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Product {

	private String productName;
	private Double productPrice;
	private LocalDate productExpiry;
	private Boolean productDurable;
	
	public Product() {
	};
	
	public Product(String productName, Double productPrice, LocalDate productExpiry, Boolean productDurable) {
		this.productName = productName;
		this.productPrice = productPrice;
		this.productExpiry = productExpiry;
		this.productDurable = productDurable;
	}
	
	public Product(Product prod) {
		this.productName = prod.productName;
		this.productPrice = prod.productPrice;
		this.productExpiry = prod.productExpiry;
		this.productDurable = prod.productDurable;
	}
	
	public Double calculate(LocalDate today) {
		
		if (getProductDurable()) return getProductPrice();
		 
		int daysToExpiry = (int) ChronoUnit.DAYS.between(today, getProductExpiry());
		 
		double discount = switch (daysToExpiry) {
		 						case 1 -> 0.25;
		 						case 2 -> 0.33;
		 						case 3 -> 0.5;
		 						default -> 1;
		};
		 
		return getProductPrice() * discount;		
	}
	
	public String toString() {
		
		String price = String.format("%.2f", getProductPrice());
		return "Producto: " + getProductName() + " \tPrecio de lista: " + price;
	}
	
	public String expiryDate() {
		
		if (getProductDurable()) return "";
		
		String date = this.getProductExpiry().toString();
		
		return "Vencimiento: " + date;
	
	}
	
	public boolean isExpired(LocalDate today) {
		
		if (getProductDurable()) return false;
		else 
		{
			int daysToExpiry = today.until(getProductExpiry()).getDays();	
			return daysToExpiry <= 0;
		}
		
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public LocalDate getProductExpiry() {
		return productExpiry;
	}
	public void setProductExpiry(LocalDate productExpiry) {
		this.productExpiry = productExpiry;
	}
	public Boolean getProductDurable() {
		return productDurable;
	}
	public void setProductDurable(Boolean productDurable) {
		this.productDurable = productDurable;
	}
	
	
}
