package cn.mldn.shopcar.action.front;

import cn.mldn.shopcar.service.front.IGoodsServiceFront;
import cn.mldn.util.web.SplitPageUtil;
import cn.mldn.util.web.action.AbstractAction;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Controller;
import cn.mldn.util.web.annotation.RequestMapping;
import cn.mldn.util.web.servlet.ModelAndView;
@Controller
@RequestMapping("/pages/front/goods/*")
public class GoodsActionFront extends AbstractAction {
	@Autowired
	private IGoodsServiceFront goodsService;
	@RequestMapping("goods_list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(super.getPage("list.page"));
		SplitPageUtil spu = new SplitPageUtil("商品名称:name", super.getPageKey("list.action"));
		try {
			System.out.println("***************************");
			mav.addMap(
					this.goodsService.list(spu.getColumn(), spu.getKeyword(), spu.getCurrentPage(), spu.getLineSize()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
