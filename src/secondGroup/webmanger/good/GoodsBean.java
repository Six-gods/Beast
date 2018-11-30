package secondGroup.webmanger.good;

import java.math.BigDecimal;
import java.util.Date;

/**
 *商品信息类
 *
 * @author 高小炎
 */
public class GoodsBean {

	private String goodsNo;
	private String goodsName;
	private BigDecimal goodsPrice;
	private String category;
	private String producer;
	private String goodsDeliveryAddress;
	private int goodsMonthSale;
	private int goodsnumber;
	private Date productDate;
	private Date expriDate;
	private int type;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public Date getExpriDate() {
		return expriDate;
	}
	public void setExpriDate(Date expriDate) {
		this.expriDate = expriDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice1(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsDeliveryAddress() {
		return goodsDeliveryAddress;
	}
	public void setGoodsDeliveryAddress(String goodsDeliveryAddress) {
		this.goodsDeliveryAddress = goodsDeliveryAddress;
	}
	public int getGoodsMonthSale() {
		return goodsMonthSale;
	}
	public void setGoodsMonthSale(int goodsMonthSale) {
		this.goodsMonthSale = goodsMonthSale;
	}
	public int getGoodsnumber() {
		return goodsnumber;
	}
	public void setGoodsnumber(int goodsnumber) {
		this.goodsnumber = goodsnumber;
	}
}
