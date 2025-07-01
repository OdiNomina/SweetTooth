package com.github.sweettooth.model.snacks;

import java.util.ArrayList;

import com.github.sweettooth.model.api.SnackFactory;
import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.locations.Location;

public class CandyFactory extends SnackFactory {	
	private static CandyFactory uniqueInstance;
	
	public static CandyFactory getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new CandyFactory();
		
		return uniqueInstance;
	}
	
	private final ArrayList<Candy> defaultCandies;
	
	private CandyFactory() {
		defaultCandies = new ArrayList<>();
	}
	
	private void creatDefaultCandies() {
		defaultCandies.add(new Lollipop());
		defaultCandies.add(new Bonbon());
		defaultCandies.add(new BubbleGum());
		defaultCandies.add(new ChewyCandy());
		defaultCandies.add(new ChocolateBar());
		defaultCandies.add(new GummyBears());
		Snackable.changeSnackPrices(defaultCandies, Location.BRONX);
	}
	
	@Override
	public ArrayList<Snackable> getDefaultSnacks() {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		ArrayList<Snackable> copy = new ArrayList<>();
		for(Candy c : defaultCandies)
			copy.add(c);
		return copy;
	}
	
	@Override
	public Candy getRandom() {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		int randomIdx = (int)(Math.random() * defaultCandies.size());
		return defaultCandies.get(randomIdx).cloneSnack();
	}
	
	@Override
	public Candy valueOf(String snackName) {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		for(Candy c : defaultCandies) {
			if(c.getName().equalsIgnoreCase(snackName.strip()))
				return c.cloneSnack();
		}
		return null;
	}
}
