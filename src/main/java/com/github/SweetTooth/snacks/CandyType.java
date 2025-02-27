package com.github.SweetTooth.snacks;

public enum CandyType {
	BONBON(new Bonbon()), 
	BUBBLEGUM(new BubbleGum()), 
	CHEWY_CANDY(new ChewyCandy()), 
	CHOCOLATE_BAR(new ChocolateBar()), 
	GUMMY_BEARS(new GummyBears()), 
	LOLLIPOP(new Lollipop());
	
	private static int size = Integer.valueOf(6);
	private Candy defaultObject;
	
	CandyType(Candy defaultObject) {
		this.defaultObject = defaultObject;
		this.defaultObject.setRandomStaticPrice();
	}
	
	public static Snackable getRandom() {
		int randomIdx = (int)(Math.random() * size);
		return values()[randomIdx].defaultObject;
	}
	
	public Snackable getCandy() {
		return defaultObject;
	}
}