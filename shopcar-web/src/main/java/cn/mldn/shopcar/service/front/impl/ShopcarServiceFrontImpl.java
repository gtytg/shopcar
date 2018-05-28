package cn.mldn.shopcar.service.front.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.dao.IShopcarDAO;
import cn.mldn.shopcar.service.front.IShopcarServiceFront;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
@Service
public class ShopcarServiceFrontImpl extends AbstractService implements IShopcarServiceFront {
	@Autowired
	private IShopcarDAO shopcarDAO ;
	@Autowired
	private IGoodsDAO goodsDAO ;
	@Override
	public boolean editAmount(Shopcar car) throws Exception {
		return this.shopcarDAO.doEditAmount(car.getMid(), car.getGid(), car.getAmount());
	}
	@Override
	public boolean delete(String mid, Set<Long> gids) throws Exception {
		if (gids == null || gids.size() == 0) {
			return false;
		}
		return this.shopcarDAO.doRemoveByMember(mid, gids) ;
	}	
	
	@Override
	public Map<String, Object> list(String mid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allShopcars", this.shopcarDAO.findAllByMember(mid)) ;// 查询购物车
		map.put("allGoodses", this.goodsDAO.findAllByShopcar(mid)) ; // 购物车中的商品
		return map;
	}
	
	@Override
	public boolean add(Shopcar car) throws Exception {
		Shopcar oldCar = this.shopcarDAO.findByMemberAndGoods(car.getMid(), car.getGid()) ;
		if (oldCar == null) {  // 新增加的商品编号
			car.setAmount(1); // 购物车数据第一次添加的时候所保存的商品数量一定是1
			return this.shopcarDAO.doCreate(car);
		} else {	// 已经保存过了该商品数据
			return this.shopcarDAO.doEditAmountIncrement(oldCar.getScid(), 1) ; // 数量追加1
		}
	}

}
