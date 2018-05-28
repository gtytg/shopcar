package cn.mldn.shopcar.action.front;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;

import cn.mldn.shopcar.service.front.IShopcarServiceFront;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
@Controller
@RequestMapping("/pages/front/center/shopcar/*")
public class ShopcarActionFront extends AbstractAction {
	@Autowired
	private IShopcarServiceFront shopcarServiceFront ;
	
	@RequestMapping("shopcar_edit")
	public void edit(Shopcar car) throws Exception {
		car.setMid(super.getMid()); // 设置用户名
		Map<String,Object> result = new HashMap<String,Object>() ;
		result.put("flag", this.shopcarServiceFront.editAmount(car)) ;
		super.print(JSON.toJSONString(result)); 
	} 
	
	@RequestMapping("shopcar_remove")
	public void remove(String ids) throws Exception {
		Set<Long> gids = super.handleIdToLong(ids) ; // 进行ids的数据处理
		Map<String,Object> result = new HashMap<String,Object>() ;
		result.put("flag", this.shopcarServiceFront.delete(super.getMid(), gids)) ;
		super.print(JSON.toJSONString(result));
	} 
	
	@RequestMapping("shopcar_list")
	public ModelAndView list() throws Exception {
		ModelAndView mav = new ModelAndView(super.getPage("list.page")) ;
		mav.addMap(this.shopcarServiceFront.list(super.getMid())); 
		return mav ;
	}	
	 
	
	@RequestMapping("shopcar_add") 
	public void add(Shopcar car) throws Exception {	// 回避了关键字new
		Map<String,Object> result = new HashMap<String,Object>() ;
		car.setMid(super.getMid()); // 购物车里面需要保存有用户编号数据
		result.put("flag", this.shopcarServiceFront.add(car)) ;
		super.print(JSON.toJSONString(result));
	}
	
	
	
}
