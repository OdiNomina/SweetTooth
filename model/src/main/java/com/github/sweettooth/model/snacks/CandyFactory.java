package com.github.sweettooth.model.snacks;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.locations.Location;

public class CandyFactory extends SnackFactory implements ISnackFactory {	
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
		defaultCandies.add(new Lollipop(0.6, 1.2));
		defaultCandies.add(new Bonbon(0.15, 0.3));
		defaultCandies.add(new BubbleGum(0.25, 0.5));
		defaultCandies.add(new ChewyCandy(0.2, 0.4));
		defaultCandies.add(new ChocolateBar(1.5, 3.0));
		defaultCandies.add(new GummyBears(0.8, 1.6));
		changeSnackPrices(InternSettings.HOMETOWN);
	}
	
	@Override
	public void changeSnackPrices(Location location) {
		getDefaultSnacks().stream().forEach(  t -> t.setRandomStaticPrice(location) );
	}
	
	@Override
	public ArrayList<Snack> getDefaultSnacks() {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		ArrayList<Snack> copy = new ArrayList<>();
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