package fieldworks.testing.junit5.services;

public class ProductService {
	public double calculatePriceWithTax(double price) {
		if (price < 0)
			throw new IllegalArgumentException("Price cannot be negative");
		double tax = price * 0.18; // 18% GST
		return price + tax;
	}

	public boolean isExpensive(double price) {  
		return price > 500;
	}
}
