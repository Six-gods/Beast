package secondGroup.webmanger.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 *搜索命令信息类
 *
 * @author 高小炎
 */
public class OrderItem {
	
	private int order_number;
	private String good_picture_address;
	private String goods_name;
	private String goods_no;
	private int goods_number;
	private BigDecimal goods_price;
	private Date order_time;
	private String consumer_name;
	
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
	
	public String getGoods_name() {
		return goods_name;
	}
	
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	
	public String getGoods_no() {
		return goods_no;
	}
	
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	
	public int getGoods_number() {
		return goods_number;
	}
	
	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}
	
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	
	public Date getOrder_time() {
		return order_time;
	}
	
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	
	public String getConsumer_name() {
		return consumer_name;
	}
	
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
}
