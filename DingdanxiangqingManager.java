package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IDingdanxiangqingManager;
import model.BeanDingdanxiangqing;
import model.BeanShangpindingdan;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class DingdanxiangqingManager implements IDingdanxiangqingManager{
	public List<BeanDingdanxiangqing> loadDingdanxiangqing(BeanShangpindingdan shangpindingdan) throws BaseException
	{
		List<BeanDingdanxiangqing> result=new ArrayList<BeanDingdanxiangqing>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou,manzhebianhao from dingdanxiangqing where dingdanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, shangpindingdan.getDingdanbianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanDingdanxiangqing u = new BeanDingdanxiangqing();
			u.setDingdanbianhao(rs.getInt(1));
			u.setShangpinbianhao(rs.getInt(2));
			u.setShuliang(rs.getDouble(3));
			u.setJiage(rs.getDouble(4));
			u.setZhekou(rs.getDouble(5));
			u.setManzhebianhao(rs.getInt(6));
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
