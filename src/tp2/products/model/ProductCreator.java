package tp2.products.model;
import java.time.LocalDate;
import java.util.Random;

public class ProductCreator {
	
	private static final String[] NAMES = {"Harina", "Gaseosa", "Panificado", "Azúcar", "Yerba", "Jugo pera", "Chicle", "Chocolate", 
			  							   "Sal fina", "Servilleta", "Bolsa", "Lápiz", "Cigarrillo", "Encendedor", "Enlatado", "Poroto" };
	private static final int COUNT = 8;
	private static final int MIN_PRICE = 20;
	
	private Product product;
	private Random random;
	
	public ProductCreator() {
		this.product = new Product();
		this.random = new Random();
	}
	
	public void generateProduct(boolean isDurable) {
		
		this.product.setProductDurable(isDurable);
		
		if (isDurable)
			this.product.setProductName(NAMES[random.nextInt(COUNT) + COUNT]);
		else
		{
			this.product.setProductName(NAMES[random.nextInt(COUNT)]);
			this.product.setProductExpiry(randomDate());
		}
		
		this.product.setProductPrice(random.nextDouble(200.0) + MIN_PRICE);
	}
	
	private LocalDate randomDate() {
		int day = random.nextInt(31) + 1;
		int month = random.nextInt(12) + 1;
		int year = random.nextInt(2) + LocalDate.now().getYear();
		
		int days = switch(month) {
						case 1, 3, 5, 7, 8, 10, 12 -> day;
						case 4, 6, 9, 11 -> day % 30;
						case 2 -> (year % 4 == 0)? day % 29: day % 28; 
						default -> 0;
		};
		
		if (days == 0) days++;
				
		return LocalDate.of(year, month, days);
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
			
	
}
