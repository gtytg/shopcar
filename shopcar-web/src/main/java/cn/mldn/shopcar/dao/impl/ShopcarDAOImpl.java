package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.shopcar.dao.IShopcarDAO;
import cn.mldn.shopcar.vo.Shopcar;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
@Repository
public class ShopcarDAOImpl extends AbstractDAO implements IShopcarDAO {
 
	@Override
	public boolean doRemoveByMember(String mid, Set<Long> gids) throws Exception {
		StringBuffer sql = new StringBuffer() ;
		sql.append("DELETE FROM shopcar WHERE mid=? AND gid IN (") ;
		for (int x = 0 ; x < gids.size() ; x ++) {
			sql.append("?,") ;
		}
		sql.delete(sql.length() - 1, sql.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(sql.toString()) ;
		super.pstmt.setString(1, mid);
		int foot = 2 ; 
		Iterator<Long> iter = gids.iterator() ;
		while (iter.hasNext()) {
			super.pstmt.setLong(foot ++, iter.next());
		}
		return super.pstmt.executeUpdate() > 0 ;
	} 
	
	@Override
	public List<Shopcar> findAllByMember(String mid) throws SQLException {
		List<Shopcar> all = new ArrayList<Shopcar>() ;	
		String sql = "SELECT scid,amount,mid,gid FROM shopcar WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Shopcar car = new Shopcar() ;
			car.setScid(rs.getLong(1));
			car.setAmount(rs.getInt(2));
			car.setMid(rs.getString(3));
			car.setGid(rs.getLong(4));
			all.add(car) ;
		}
		return all; 
	}
	
	@Override
	public Shopcar findByMemberAndGoods(String mid, Long gid) throws SQLException {
		String sql = "SELECT scid,amount FROM shopcar WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, mid);
		super.pstmt.setLong(2, gid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Shopcar car = new Shopcar() ;
			car.setScid(rs.getLong(1));
			car.setAmount(rs.getInt(2));
			return car ;
		}
		return null;
	}
	
	@Override
	public boolean doEditAmount(String mid,Long gid, Integer amount) throws SQLException {
		String sql = "UPDATE shopcar SET amount=? WHERE mid=? AND gid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, amount);
		super.pstmt.setString(2, mid);
		super.pstmt.setLong(3, gid);
		return super.pstmt.executeUpdate() > 0 ;
	} 
	
	@Override
	public boolean doEditAmountIncrement(Long scid, Integer amount) throws SQLException {
		String sql = "UPDATE shopcar SET amount=amount+" + amount + " WHERE scid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setLong(1, scid); 
		return super.pstmt.executeUpdate() > 0 ;
	}
	
	@Override
	public boolean doCreate(Shopcar vo) throws SQLException {
		String sql = "INSERT INTO shopcar (mid,gid,amount) VALUES (?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setLong(2,vo.getGid());
		super.pstmt.setInt(3, vo.getAmount());
		return super.pstmt.executeUpdate() > 0 ; 
	}

	@Override
	public boolean doEdit(Shopcar vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Shopcar findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findSplit(Long currentPage, Integer lineSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shopcar> findSplit(Long currentPage, Integer lineSize, String column, String keyWord)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
