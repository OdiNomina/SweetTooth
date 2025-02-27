package com.github.SweetTooth.snacks;

class DefaultCandyFactory extends CandyFactory {	
	/**
	 * Returns a new Instance of the candy type at runtime.
	 * @param candy a type that is concrete at runtime.
	 * @exception IllegalArgumentException
	 * 				if argument is null.
	 */
	@Override
	public Snackable create(Snackable candy) {
		if(candy == null)
			throw new IllegalArgumentException("Argument must not be null.");
		Candy newInstance = null;
		switch((Candy)candy) {
			case Bonbon bonbon -> newInstance = new Bonbon();
			case BubbleGum bubblegum -> newInstance = new BubbleGum();
			case ChewyCandy chewycandy -> newInstance = new ChewyCandy();
			case ChocolateBar chocolatebar -> newInstance = new ChocolateBar();
			case GummyBears gummybears -> newInstance = new GummyBears();
			case Lollipop lollipop -> newInstance = new Lollipop();
		}
		return newInstance;
	}
}
