package models;

import java.util.*;

public class Option extends Asset {
	private double strike;
	private Date maturity;
	private Stock underlying;
	private boolean isCall;

	public Option(double strike, Date maturity, Stock underlying, boolean isCall) {
		this.strike = strike;
		this.maturity = maturity;
		this.underlying = underlying;
		this.isCall = isCall;
	}

	
	public double getStrike() {
		return strike;
	}


	public void setStrike(double strike) {
		this.strike = strike;
	}


	public Date getMaturity() {
		return maturity;
	}


	public void setMaturity(Date maturity) {
		this.maturity = maturity;
	}


	public Stock getUnderlying() {
		return underlying;
	}


	public void setUnderlying(Stock underlying) {
		this.underlying = underlying;
	}


	public boolean isCall() {
		return isCall;
	}


	public void setCall(boolean isCall) {
		this.isCall = isCall;
	}


	@Override
	public double getPrice(Date date) {
		return 0;
	}

}
