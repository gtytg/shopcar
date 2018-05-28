package cn.mldn.shopcar.service.front.impl;

import cn.mldn.shopcar.dao.IMemberDAO;
import cn.mldn.shopcar.service.front.IMemberServiceFront;
import cn.mldn.shopcar.vo.Member;
import cn.mldn.util.service.abs.AbstractService;
import cn.mldn.util.web.annotation.Autowired;
import cn.mldn.util.web.annotation.Service;
@Service
public class MemberServiceFrontImpl extends AbstractService implements IMemberServiceFront {
	@Autowired
	private IMemberDAO memberDAO ;
	@Override
	public boolean login(Member vo) throws Exception {
		Member member = this.memberDAO.findById(vo.getMid()) ;	// 根据mid获取Member信息
		if (member != null) {
			return member.getPassword().equals(vo.getPassword()) ;
		}
		return false;
	}

}
