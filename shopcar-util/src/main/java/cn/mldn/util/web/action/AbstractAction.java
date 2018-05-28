package cn.mldn.util.web.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import cn.mldn.util.bean.ResourceUtil;
import cn.mldn.util.web.servlet.ModelAndView;
import cn.mldn.util.web.servlet.ServletObjectUtil;

public abstract class AbstractAction {
	/**
	 * 将接收到的id数据进行拆分处理，并且以Set集合的形式返回
	 * @param id 要传递的id数据，格式“id;id;”
	 * @return 处理转换后的Set集合
	 */
	public Set<Long> handleIdToLong(String id) {
		if (id == null || "".equals(id)) {
			return null ;
		}
		Set<Long> result = new HashSet<Long>() ;
		String splitStr [] = id.split(";") ;
		for (int x = 0 ; x < splitStr.length ; x ++) {
			result.add(Long.parseLong(splitStr[x])) ;
		}
		return result ;
	} 
	/**
	 * 从session之中获取登录后的mid数据
	 * @return 返回保存的mid数据
	 */
	public String getMid() {
		return (String) ServletObjectUtil.getRequest().getSession().getAttribute("mid") ;
	}
	
	/**
	 * 实现信息输出
	 * @param obj 要输出的信息内容
	 */
	public void print(Object obj) {
		try {
			ServletObjectUtil.getResponse().getWriter().println(obj);
		} catch (IOException e) {
		}
	} 
	/**
	 * 得到跳转页面
	 * @return 跳转显示页
	 */
	public String getForwardPage() {
		return ResourceUtil.getPage("forward.page");
	}
	/**
	 * 得到公共的跳转页面
	 * @param pageKey 页面key
	 * @return
	 */
	public String getCommonPage(String pageKey) {
		return ResourceUtil.getPage(pageKey) ;
	}
	/**
	 * 获取跳转路径的key名称
	 * @param key
	 * @return
	 */
	public String getPageKey(String key) {
		return this.getClass().getName() + "." + key ;
	}
	/**
	 * 得到跳转路径
	 * @param key
	 * @return
	 */
	public String getPage(String key) {
		return ResourceUtil.getPage(this.getPageKey(key)) ;
	}
	/**
	 * 定义一个专门设置提示信息与跳转路径属性的操作方法
	 * @param mav 要进行返回的ModelAndView对象
	 * @param urlKey 跳转路径的key
	 * @param msgKey 提示消息的文字key
	 * @param msgParam 占位符内容
	 */
	public void setUrlAndMsg(ModelAndView mav, String urlKey, String msgKey,String ... msgParam) {
		mav.add("msg", ResourceUtil.getMessage(msgKey, msgParam)); 
		mav.add("url", ResourceUtil.getPage(urlKey));
	}
	/**
	 * 设置资提示信息
	 * @param mav 要进行返回的ModelAndView对象
	 * @param msgKey 提示消息的文字key
	 * @param msgParam 占位符内容
	 */
	public void setMsg(ModelAndView mav ,String msgKey,String ... msgParam) {
		mav.add("msg", ResourceUtil.getMessage(msgKey, msgParam));
	}
}
