package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public class CandyFactory extends SnackFactory {	
	private static final ArrayList<Snackable> defaultSnacks = new ArrayList<>();
	
	@Override
	public Snackable create(String snackName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ArrayList<Snackable> getDefaultSnacks() {
		if(defaultSnacks.isEmpty()) {
			defaultSnacks.add(new Bonbon());
			defaultSnacks.add(new BubbleGum());
			defaultSnacks.add(new ChewyCandy());
			defaultSnacks.add(new ChocolateBar());
			defaultSnacks.add(new GummyBears());
			defaultSnacks.add(new Lollipop());
			Snackable.changeSnackPrices(defaultSnacks);
		}
		ArrayList<Snackable> copy = new ArrayList<>();
		for(Snackable s : defaultSnacks)
			copy.add(s);
		return copy;
	}
	
	@Override
	public Snackable getRandom() {
		int randomIdx = (int)(Math.random() * defaultSnacks.size());
		return defaultSnacks.get(randomIdx).clone();
	}
	
	@Override
	public Snackable valueOf(String snackName) {
		for(Snackable s : getDefaultSnacks()) {
			if(s.getName().equalsIgnoreCase(snackName.strip()))
				return s.clone();
		}
		return null;
	}
}
