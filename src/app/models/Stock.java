package models;

import java.util.Date;

public class Stock extends Asset {
	private String stockCode;
	private double price;

	public Stock(double price, String stockCode) {
		this.price = price;
		this.stockCode = stockCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double getPrice(Date date) {
		return 0;
	}

}
