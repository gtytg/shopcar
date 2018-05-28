package cn.mldn.util.web.validate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.mldn.util.bean.ResourceUtil;
import cn.mldn.util.web.servlet.ServletObjectUtil;

public class ValidateUtil {
	private ValidateUtil() {}
	
	public static Map<String,String> validateCommonMIME(String mimeRule) {
		Set<String> ruleSet = new HashSet<String>() ;
		Map<String,String> errors = new HashMap<String,String>() ;
		ruleSet.addAll(Arrays.asList(mimeRule.split(";"))) ; // 进行数据保存，所有可以使用规则
		Set<String> uploadMime = ServletObjectUtil.getParameter().getMimes() ;// 获取所有的类型
		for (String um : uploadMime) {
			if (!ruleSet.contains(um)) {	// 不在给定的类型范围内
				errors.put("file", ResourceUtil.getMessage("mime.validate.error.msg")) ;
			}
		}
		if (errors.size() > 0) {	// 有错误了
			ServletObjectUtil.getRequest().setAttribute("errors", errors);
		}
		return errors ; 
	}
	
	public static Map<String,String> validateMIME(String mimeRule) {
		Map<String,String> errors = new HashMap<String,String>() ;
		String rules [] = mimeRule.split("\\|") ; // 拆分
		for (int x = 0 ; x < rules.length ; x ++) {
			String rule[] = rules[x].split(":") ;
			Set<String> ruleSet = new HashSet<String>() ;
			ruleSet.addAll(Arrays.asList(rule[1].split(";"))) ; // 进行数据保存，所有可以使用规则
			Set<String> uploadMime = ServletObjectUtil.getParameter().getMimes(rule[0]) ;// 获取所有的类型
			for (String um : uploadMime) {
				if (!ruleSet.contains(um)) {	// 不在给定的类型范围内
					errors.put(rule[0], ResourceUtil.getMessage("mime.validate.error.msg")) ;
				}
			}
		}
		if (errors.size() > 0) {	// 有错误了
			ServletObjectUtil.getRequest().setAttribute("errors", errors);
		}
		return errors ; 
	}
	/**
	 * 进行规则验证处理操作
	 * @param validateRule 验证规则 
	 * @return 如果有错误则Map集合保存错误信息，如果没有错误Map集合长度为0
	 */
	public static Map<String,String> validate(String validateRule) {
		// key = 验证的参数名称、value = 错误信息（Messages.properties）
		Map<String,String> errors = new HashMap<String,String>() ;
		String rules [] = validateRule.split("\\|") ; // 拆分
		for (int x = 0 ; x < rules.length ; x ++) {
			String rule [] = rules[x].split(":") ;
			switch (rule[1]) {
			case "int" : {
				if (!isInt(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("int.validate.error.msg")) ;
				}
				break ;
			}
			case "boolean" : {
				if (!isBoolean(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("int.validate.error.msg")) ;
				}
				break ;
			}
			case "long" : {
				if (!isLong(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("long.validate.error.msg")) ;
				}
				break ;
			}
			case "string" : {
				if (!isString(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("string.validate.error.msg")) ;
				}
				break ;
			}
			case "double" : {
				if (!isDouble(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("double.validate.error.msg")) ;
				}
				break ;
			}
			case "date" : {
				if (!isDate(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("date.validate.error.msg")) ;
				}
				break ;
			}
			case "datetime" : {
				if (!isDatetime(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("datetime.validate.error.msg")) ;
				}
				break ;
			}
			case "rand" : {
				if (!isRand(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("rand.validate.error.msg")) ;
				}
				break ;
			}
			case "int[]" : {
				if (!isIntArray(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("int[].validate.error.msg")) ;
				}
				break ;
			}
			case "long[]" : {
				if (!isLongArray(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("long[].validate.error.msg")) ;
				}
				break ;
			}
			case "string[]" : {
				if (!isStringArray(rule[0])) {	// 没有通过验证
					errors.put(rule[0], ResourceUtil.getMessage("string[].validate.error.msg")) ;
				}
				break ;
			}
			}
		}
		if (errors.size() > 0) {	// 有错误了
			ServletObjectUtil.getRequest().setAttribute("errors", errors);
		}
		return errors ; 
	}
	/**
	 * 验证码数据
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isRand(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			String rand = (String) ServletObjectUtil.getRequest().getSession().getAttribute("rand") ;
			if (rand == null || "".equals(rand)) {
				return false ;
			}
			return rand.equalsIgnoreCase(paramValue) ;
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isDatetime(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return paramValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isBoolean(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return "true".equals(paramValue) || "false".equals(paramValue);
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isDate(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return paramValue.matches("\\d{4}-\\d{2}-\\d{2}") ;
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isDouble(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return paramValue.matches("\\d+(\\.\\d+)?") ;
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isLongArray(String paramName) {
		String paramValue[] = ServletObjectUtil.getParameter().getParameterValues(paramName) ;
		if (paramValue == null) {
			return false ;
		}
		for (int x = 0 ; x < paramValue.length ; x ++) {
			if (!paramValue[x].matches("\\d+")) {
				return false ;
			}
		}
		return true ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isLong(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return paramValue.matches("\\d+") ;
		}
		return false ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isIntArray(String paramName) {
		String paramValue[] = ServletObjectUtil.getParameter().getParameterValues(paramName) ;
		if (paramValue == null) {
			return false ;
		}
		for (int x = 0 ; x < paramValue.length ; x ++) {
			if (!paramValue[x].matches("\\d+")) {
				return false ;
			}
		}
		return true ;
	}
	/**
	 * 验证是否为空以及是否为整数
	 * @param paramName 参数名称
	 * @return 整数返回true
	 */
	public static boolean isInt(String paramName) {
		if (isString(paramName)) {	// 通过了字符串不为空的验证
			String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
			return paramValue.matches("\\d+") ;
		}
		return false ;
	}
	/**
	 * 进行字符串的数据验证
	 * @param paramName 参数名称
	 * @return 不为空返回true
	 */
	public static boolean isStringArray(String paramName) {
		String paramValue[] = ServletObjectUtil.getParameter().getParameterValues(paramName) ;
		if (paramValue == null) {
			return false ;
		}
		for (int x = 0 ; x < paramValue.length ; x ++) {
			if (paramValue[x] == null) {
				return false ;
			}
		}
		return true ;
	}
	/**
	 * 进行字符串的数据验证
	 * @param paramName 参数名称
	 * @return 不为空返回true
	 */
	public static boolean isString(String paramName) {
		String paramValue = ServletObjectUtil.getParameter().getParameter(paramName) ;
		if (paramValue == null || "".equals(paramValue)) {
			return false ;
		}
		return true ;
	}
}
