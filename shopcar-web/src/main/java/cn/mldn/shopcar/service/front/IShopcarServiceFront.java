package cn.mldn.shopcar.service.front;

import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.vo.Shopcar;

public interface IShopcarServiceFront {
	/**
	 * 进行指定商品信息的修改
	 * @param car 描述要修改的内容
	 * @return 修改成功返回true
	 * @throws Exception 程序异常
	 */
	public boolean editAmount(Shopcar car) throws Exception;
	
	/**
	 * 删除购物车中的指定商品编号
	 * @param mid 用户ID
	 * @param gids 商品编号集合
 	 * @return 删除成功返回true
	 * @throws Exception 程序异常
	 */
	public boolean delete(String mid,Set<Long> gids) throws Exception ;
	/**
	 * 根据指定的用户ID列出对应的购物车中的全部数据信息
	 * @param mid 要查询的用户编号
	 * @return 返回有如下的信息内容：
	 * 1、key = allShopcars、value = 【LIST集合】用户对应的购物车中的所有商品记录；
	 * 2、key = allGoodses、value = 【MAP集合】用户购物车中的商品详情。 
	 * @throws Exception 程序异常
	 */
	public Map<String,Object> list(String mid) throws Exception ;
	
	/**
	 * 实现购物车中的数据增加处理
	 * @param car 要保存的购物车的信息
	 * @return 保存成功返回true，否则返回false
	 * @throws Exception 程序异常
	 */
	public boolean add(Shopcar car) throws Exception ;
}
