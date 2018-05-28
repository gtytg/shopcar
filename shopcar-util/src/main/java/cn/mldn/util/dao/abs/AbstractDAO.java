package cn.mldn.util.dao.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import cn.mldn.util.dbc.DatabaseConnection;

public abstract class AbstractDAO {
	protected PreparedStatement pstmt ;
	protected Connection conn ;
	public AbstractDAO () {
		this.conn = DatabaseConnection.getConnection() ; 
	}
	/**
	 * 实现数据批量删除的处理方法
	 * @param tableName 要删除的数据表
	 * @param keyColumn 删除数据主键列的名称
	 * @param ids 删除的ID
	 * @return 删除成功返回true
	 * @throws SQLException 数据库异常 
	 */
	public boolean doRemoveHandleByLong(String tableName,String keyColumn,Set<Long> ids) throws SQLException {
		StringBuffer buf = new StringBuffer("DELETE FROM ") ;
		buf.append(tableName).append(" WHERE ").append(keyColumn).append(" IN (") ;
		for (Long id : ids) {
			buf.append(id).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		this.pstmt = this.conn.prepareStatement(buf.toString()) ;
		return this.pstmt.executeUpdate() > 0 ;
	}
	/**
	 * 对查询全部数据量进行处理
	 * @param tableName 要查询的表名称
	 * @return 记录的统计个数
	 * @throws SQLException SQL异常
	 */
	public long getAllCountHandle(String tableName) throws SQLException {
		String sql = "SELECT COUNT(*) FROM " + tableName ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	}
	/**
	 * 对数据量进行模糊查询操作 
	 * @param tableName 表名称
	 * @param column 列名称
	 * @param keyWord 查询关键字
	 * @return 模糊查询统计结果
	 * @throws SQLException SQL异常
	 */
	public long getAllCountSplitHandle(String tableName,String column,String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + column + " LIKE ?" ;
		this.pstmt = this.conn.prepareStatement(sql) ;
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getLong(1) ;
		}
		return 0L ;
	}
}
