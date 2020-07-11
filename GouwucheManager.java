package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IGouwucheManager;
import model.BeanGouwuche;
import model.BeanYonghu;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class GouwucheManager implements IGouwucheManager{

	@Override
	public List<BeanGouwuche> loadAll() throws BaseException 
	{
		List<BeanGouwuche> result=new ArrayList<BeanGouwuche>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select gouwuchebianhao,yonghubianhao,shangpinbianhao,shangpinmingcheng,yonghuorder,shuliang,jiage from gouwuche where yonghubianhao = ? order by yonghuorder";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanGouwuche u = new BeanGouwuche();
			u.setGouwuchebianhao(rs.getInt(1));
			u.setYonghubianhao(rs.getInt(2));
			u.setShangpinbianhao(rs.getInt(3));
			u.setShangpinmingcheng(rs.getString(4));
			u.setYonghuorder(rs.getInt(5));
			u.setShuliang(rs.getDouble(6));
			u.setJiage(rs.getDouble(7));
			result.add(u);
			}
			pst.close();
			rs.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}
