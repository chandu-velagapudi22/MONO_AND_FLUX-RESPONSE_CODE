package com.infotech.in.ENTITY;



import java.util.Date;

import lombok.Data;

@Data
public class StockData {

	private Integer stockId;
	private String stockName;
	private Double stockPrice;
	private Date date;

	public StockData() {

	}

	public StockData(Integer stockId, String stockName, Double stockPrice, Date date) {
		super();
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockPrice = stockPrice;
		this.date = date;
	}

}
