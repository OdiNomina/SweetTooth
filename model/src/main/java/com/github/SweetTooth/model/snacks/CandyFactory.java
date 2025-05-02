package com.github.SweetTooth.model.snacks;

import java.util.ArrayList;

import com.github.SweetTooth.model.locations.Location;

public class CandyFactory extends SnackFactory {	
	private static final ArrayList<Candy> defaultSnacks = new ArrayList<>();
	
	@Override
	public Candy create(String snackName) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public ArrayList<Candy> getDefaultSnacks() {
		if(defaultSnacks.isEmpty()) {
			defaultSnacks.add(new Lollipop());
			defaultSnacks.add(new Bonbon());
			defaultSnacks.add(new BubbleGum());
			defaultSnacks.add(new ChewyCandy());
			defaultSnacks.add(new ChocolateBar());
			defaultSnacks.add(new GummyBears());
			Snackable.changeCandyPrices(defaultSnacks, Location.BRONX);
		}
		ArrayList<Candy> copy = new ArrayList<>();
		for(Candy c : defaultSnacks)
			copy.add(c);
		return copy;
	}
	
	@Override
	public Candy getRandom() {
		int randomIdx = (int)(Math.random() * defaultSnacks.size());
		return defaultSnacks.get(randomIdx).cloneSnack();
	}
	
	@Override
	public Candy valueOf(String snackName) {
		for(Candy c : getDefaultSnacks()) {
			if(c.getName().equalsIgnoreCase(snackName.strip()))
				return c.cloneSnack();
		}
		return null;
	}
}
