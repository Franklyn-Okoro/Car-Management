

public class Car {
	private String customer;
	private String make;
	private String category;
	private String extra;
	private double price;
	
	public Car(String customer, String make, String category, String extraComponents, double price) {
		this.customer = customer;
		this.make = make;
		this.category = category;
		this.extra = extraComponents;
		this.price = price;
	}
	public String getCustomer() {
		return this.customer;
	}
	
	public double getPrice() {
		return this.price;
	}
	public String toString() {
		
		return "Customer: " + this.customer + "-- Car Details: " + this.make + " | " + this.category + " | " + this.extra + " | " + String.valueOf(this.price);
	}
}
