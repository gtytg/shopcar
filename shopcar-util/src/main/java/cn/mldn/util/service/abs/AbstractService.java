package cn.mldn.util.service.abs;

public abstract class AbstractService {
	/**
	 * 是否需要进行模糊查询处理
	 * @param column 判断的查询列
	 * @param keyWord 判断的关键字
	 * @return 如果需要模糊查询处理返回true，否则返回false
	 */
	public boolean isSearch(String column, String keyWord) {
		if (column == null || "".equals(column) || keyWord == null || "".equals(keyWord)) {
			return false ; // 不需要模糊查询
		}
		return true ;  
	}
}
