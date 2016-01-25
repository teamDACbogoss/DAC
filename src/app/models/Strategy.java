package models;

import java.util.Date;
import java.util.HashMap;

public class Strategy {
	private String stratName;
	private HashMap<Asset, Integer> assets;
	
	public Strategy(String stratName, HashMap<Asset, Integer> assets) {
		super();
		this.stratName = stratName;
		this.assets = assets;
	}

	public String getStratName() {
		return stratName;
	}

	public void setStratName(String stratName) {
		this.stratName = stratName;
	}

	public HashMap<Asset, Integer> getAssets() {
		return assets;
	}

	public void setAssets(HashMap<Asset, Integer> assets) {
		this.assets = assets;
	}
	
	public double getPrice(Date date){
		return 0.0;
	}
	
}
