package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CurrencyRate {
	USD,THB,EUR,HKD,JPY,CNY;

	public double matchCurrency(String data, CurrencyRate curr) {

		String regex = "\"USD([A-Z]{3})\":\\s*(\\d*.\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(data);

		int offset = 0;
		while (match.find(offset)) {
			String currency = match.group(1);
			String value = match.group(2);
			
			double values = Double.parseDouble(value);

			switch (curr) {
			
			case USD:
				return 1;
			case CNY:
				if(currency.equals("CNY")) return values;
			case EUR:
				if (currency.equals("EUR")) return values;
			case HKD:
				if (currency.equals("HKD")) return values;
			case JPY:
				if (currency.equals("JPY")) return values;
			case THB:
				if (currency.equals("THB")) return values;
			
			}
			
			offset = match.end();
		}
		return 0;
	}
}
