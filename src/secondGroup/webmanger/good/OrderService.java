package secondGroup.webmanger.good;

import org.apache.commons.beanutils.BeanUtils;
import secondGroup.webmanger.order.OrderItem;

/**
 *搜索命令服务操作类
 *
 * @author 高小炎
 */
public class OrderService {

	private GoodsDao gd = new GoodsDao();
	
	public OrderItem getOrderItemByBarcode(String goodsNo) throws Exception {
		OrderItem oi = new OrderItem();
		GoodsBean goods = gd.getGoodsByBarCode(goodsNo);
		BeanUtils.copyProperties(oi, goods);
		return oi;
	}
}
