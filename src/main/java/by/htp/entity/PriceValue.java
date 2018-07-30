package by.htp.entity;

public class PriceValue implements Comparable<PriceValue> {
	
	private int price;
	private String date;
	
	public PriceValue(int price, String date) {
		super();
		this.price = price;
		this.date = date;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getDate() {
		return date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + price;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceValue other = (PriceValue) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PriceValue [price=" + price + ", date=" + date + "]";
	}

	public int compareTo(PriceValue o) {
		return Integer.compare(price, o.price);
	}
}

