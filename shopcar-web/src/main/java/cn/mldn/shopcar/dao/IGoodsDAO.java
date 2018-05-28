package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.Map;

import cn.mldn.shopcar.vo.Goods;
import cn.mldn.util.dao.IDAO;

public interface IGoodsDAO extends IDAO<Long, Goods> {
	/**
	 * 根据用户购物车之中保存的商品编号查询出所有的商品信息
	 * @param mid 用户编号
	 * @return 一个用户购物车中对应商品的完整信息
	 * @throws SQLException SQL
	 */
	public Map<Long,Goods> findAllByShopcar(String mid) throws SQLException ;
}
