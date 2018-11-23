package secondGroup.webmanger.order;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {

	private int order_number;
	
	private String good_picture_address;
	
	private String good_name;
	
	private int good_id;
	
	private int good_number;
	
	private BigDecimal good_price;
	
	private BigDecimal total_money;
	
	private Date order_time;
	
	private int consumer_name;
	
	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getGood_picture_address() {
		return good_picture_address;
	}

	public void setGood_picture_address(String good_picture_address) {
		this.good_picture_address = good_picture_address;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}

	public int getGood_id() {
		return good_id;
	}

	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}

	public int getGood_number() {
		return good_number;
	}

	public void setGood_number(int good_number) {
		this.good_number = good_number;
	}

	public BigDecimal getGood_price() {
		return good_price;
	}

	public void setGood_price(BigDecimal good_price) {
		this.good_price = good_price;
	}

	public BigDecimal getTotal_money() {
		return total_money;
	}

	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public int getConsumer_name() {
		return consumer_name;
	}

	public void setConsumer_name(int consumer_name) {
		this.consumer_name = consumer_name;
	}

}
