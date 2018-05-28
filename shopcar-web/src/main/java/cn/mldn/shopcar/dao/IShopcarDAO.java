package cn.mldn.shopcar.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.dao.IDAO;

public interface IShopcarDAO extends IDAO<Long, Shopcar> {
	/**
	 * 实现指定用户购买的指定商品的数量变更
	 * @param mid 用户编号
	 * @param gid 商品编号
	 * @param amount 数量
	 * @return 修改成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditAmount(String mid, Long gid, Integer amount) throws SQLException;
	
	/**
	 * 根据购物车中的保存信息编号进行商品数量的变更处理
	 * @param scid 购物车ID
	 * @param amount 要更新的数量，在已有的数量上进行叠加
	 * @return 更新成功返回true
	 * @throws SQLException SQL
	 */
	public boolean doEditAmountIncrement(Long scid,Integer amount) throws SQLException ;
	/**
	 * 删除购物车之中指定用户和指定商品编号的信息
	 * @param mid 用户编号
	 * @param gids 商品编号
	 * @return 删除成功返回true
	 * @throws Exception SQL
	 */
	public boolean doRemoveByMember(String mid, Set<Long> gids) throws Exception;
	
	/**
	 * 根据用户编号获取当前用户的购物车全部信息
	 * @param mid 用户ID
	 * @return 当前用户的购物车信息
	 * @throws SQLException SQL
	 */
	public List<Shopcar> findAllByMember(String mid) throws SQLException ;
	
	/**
	 * 根据用户编号和商品编号获取一个购物车的数据信息
	 * @param mid 用户ID
	 * @param gid 商品ID
	 * @return 如果该信息存在则返回VO对象，否则返回null
	 * @throws SQLException SQL
	 */
	public Shopcar findByMemberAndGoods(String mid,Long gid) throws SQLException ;

}
