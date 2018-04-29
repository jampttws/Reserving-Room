package bookingRoom;

public enum box {
	
	Breakfast(0),Yes(300),No(0);
	
	private final int price;
	
	private box(int price){
		this.price = price;
	}
	

}
