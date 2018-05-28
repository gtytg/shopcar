package cn.mldn.shopcar.service.front.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.service.front.IGoodsServiceFront;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;

@Service
public class GoodsServiceFrontImpl extends AbstractService implements IGoodsServiceFront {
	@Autowired
	private IGoodsDAO goodsDAO;

	
	@Override
	public Map<String, Object> list(String column, String keyWord, long currentPage, int lineSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (super.isSearch(column, keyWord)) { // 需要进行模糊查询
			map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize, column, keyWord));
			map.put("allRecorders", this.goodsDAO.getAllCount(column, keyWord));
		} else {
			map.put("allGoods", this.goodsDAO.findSplit(currentPage, lineSize));
			map.put("allRecorders", this.goodsDAO.getAllCount());
		}
		return map;
	}

}
