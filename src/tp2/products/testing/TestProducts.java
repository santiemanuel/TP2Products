package tp2.products.testing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

import tp2.products.model.*;

public class TestProducts {

	public static void main(String[] args) {

		/*
		 * - Crear una clase Productos con atributos: Nombre, precios, vencimiento, con 2 tipos
		     de productos perecederos y no perecederos.
		   
		   - Crear sus constructores getters y setters.
		   - crear una función llamada calcular: en producto simplemente seria calcular
			 precio por cantidad, en perecederos el precio se reducirá según los días a
			 caducar, si le queda 1 día se reducirá 4 veces el precio, si le quedan 2 días se
			 reducirá el precio 3 veces, si le quedan 3 días se reducirá la mitad el precio.
			 Crear una clase ejecutable y crear un array de productos y muestra el precio
			 total de vender 5 productos de cada uno.

		 */
			
		ArrayList<Product> productList = new ArrayList<>();
		ProductCreator generator = new ProductCreator();
		LocalDate today = LocalDate.now();
		
		for (int i = 0; i < 5; i++) {
			generator.generateProduct(true);
			productList.add(new Product(generator.getProduct()));
		}
		
		for (int i = 0; i < 5; i++) {
			generator.generateProduct(false);
			productList.add(new Product(generator.getProduct()));
		}
		
		LocalDate fechaPapa = LocalDate.of(2022, 4, 20);
		Product special = new Product("Choclo", 200.0, fechaPapa, false);
		
		
		productList.add(special);
		
		System.out.println("Lista de Productos sin depurar vencidos");
		
		showProducts(productList, today);
		
		ListIterator<Product> it = productList.listIterator();
		
		while (it.hasNext()) {
			if (it.next().isExpired(today))
				it.remove();
		}
		
		System.out.println("\nLista de Productos depurada");
		
		showProducts(productList, today);
		
		
		
	}
	
	public static void showProducts(ArrayList<Product> productList, LocalDate today) {
		Double total = 0.0;
		Double price;
		
		int size = productList.size();
		
		for (int i = 0; i < size; i++) {
			System.out.print(productList.get(i) + "\t" + productList.get(i).expiryDate() + " ");
			price = productList.get(i).calculate(today);
			System.out.printf("Precio actual: %.2f\n", price);
			total += price;
		}
		
		System.out.printf("\nTotal: %.2f", total);
	}

}
