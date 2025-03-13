package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public class DefaultCandyFactory extends CandyFactory {	
	private static final ArrayList<Snackable> defaultSnacks = new ArrayList<>();
	
	@Override
	public ArrayList<Snackable> getDefaultSnacks() {
		if(defaultSnacks.isEmpty()) {
			defaultSnacks.add(new Bonbon());
			defaultSnacks.add(new BubbleGum());
			defaultSnacks.add(new ChewyCandy());
			defaultSnacks.add(new ChocolateBar());
			defaultSnacks.add(new GummyBears());
			defaultSnacks.add(new Lollipop());
			Snackable.changePrices(defaultSnacks);
		}
		ArrayList<Snackable> copy = new ArrayList<>();
		for(Snackable s : defaultSnacks)
			copy.add(s);
		return copy;
	}
	
	@Override
	public Snackable create(String snackName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Snackable valueOf(String snackName) {
		for(Snackable s : getDefaultSnacks()) {
			if(s.getName().equalsIgnoreCase(snackName.strip()))
				return s.clone();
		}
		return null;
	}
	
	@Override
	public Snackable getRandom() {
		int randomIdx = (int)(Math.random() * defaultSnacks.size());
		return defaultSnacks.get(randomIdx).clone();
	}
}
