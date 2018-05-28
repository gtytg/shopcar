package cn.mldn.shopcar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.shopcar.dao.IGoodsDAO;
import cn.mldn.shopcar.vo.Goods;
import cn.mldn.util.dao.abs.AbstractDAO;
import cn.mldn.util.web.annotation.Repository;
@Repository
public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO {

	@Override
	public Map<Long, Goods> findAllByShopcar(String mid) throws SQLException {
		Map<Long,Goods> all = new HashMap<Long,Goods>() ;
		String sql = "SELECT gid,name,price,photo FROM goods WHERE gid IN ("
				+ " SELECT gid FROM shopcar WHERE mid=?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid); 
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Goods vo = new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			all.put(rs.getLong(1), vo) ;
		}
		return all;
	}
	
	@Override
	public boolean doCreate(Goods vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doEdit(Goods vo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Long> ids) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Goods findById(Long id) throws SQLException {
		String sql = "SELECT gid,name,price,photo FROM goods WHERE gid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Goods vo = new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			return vo;
		}
		return null;
	}

	@Override
	public List<Goods> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findSplit(Long currentPage, Integer lineSize) throws SQLException { 
		List<Goods> all = new ArrayList<Goods>();
		String sql = "SELECT gid,name,price,photo FROM goods LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setLong(1, (currentPage - 1) * lineSize);
		super.pstmt.setLong(2, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Goods vo = new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Goods> findSplit(Long currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
		List<Goods> all = new ArrayList<Goods>();
		String sql = "SELECT gid,name,price,photo FROM goods WHERE " + column + " LIKE ? LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setLong(2, (currentPage - 1) * lineSize);
		super.pstmt.setLong(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Goods vo = new Goods();
			vo.setGid(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setPrice(rs.getDouble(3));
			vo.setPhoto(rs.getString(4));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Long getAllCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM goods";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws SQLException {
		String sql = "SELECT COUNT(*) FROM goods WHERE " + column + " LIKE ?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return 0L;
	}

}
