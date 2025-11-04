package com.github.sweettooth.model.snacks;

import java.util.ArrayList;

import com.github.sweettooth.model.locations.Location;
import com.github.sweettooth.model.settings.InternSettings;

public class CandyFactory extends SnackFactory {	
	private static CandyFactory uniqueInstance;
	private static final ArrayList<Candy> defaultCandies = new ArrayList<>();
	
	static CandyFactory getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new CandyFactory();
		
		return uniqueInstance;
	}
	
	@Override
	public void changeSnackPrices(Location location) {
		getDefaultSnacks().stream().forEach(  t -> t.setRandomStaticPrice(location) );
	}
	
	@Override
	public ArrayList<Candy> getDefaultSnacks() {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		ArrayList<Candy> copy = new ArrayList<>();
		for(Snack snack : defaultCandies)
			copy.add((Candy)snack);
		return copy;
	}
	
	@Override
	public Candy getRandomSnack() {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		int randomIdx = (int)(Math.random() * defaultCandies.size());
		return defaultCandies.get(randomIdx).cloneSnack();
	}
	
	@Override
	public Snack valueOf(String snackName) {
		if(defaultCandies.isEmpty())
			creatDefaultCandies();
		
		for(Snack snack : defaultCandies) {
			if(snack.getName().equalsIgnoreCase(snackName.strip()))
				return snack.cloneSnack();
		}
		return null;
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
}