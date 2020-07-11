package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IShangpinManager;
import model.BeanShangpin;
import model.BeanShengxianleibie;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class ShangpinManager implements IShangpinManager{
	public List<BeanShangpin> loadShangpin(BeanShengxianleibie shengxianleibie) throws BaseException
	{
		List<BeanShangpin> result=new ArrayList<BeanShangpin>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, shengxianleibie.getLeibiebianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanShangpin u = new BeanShangpin();
			u.setShangpinbianhao(rs.getInt(1));
			u.setShangpinmingcheng(rs.getString(2));
			u.setJiage(rs.getDouble(3));
			u.setHuiyuanjia(rs.getDouble(4));
			u.setShuliang(rs.getDouble(5));
			u.setGuige(rs.getString(6));
			u.setXiangqing(rs.getString(7));
			u.setLeibiebianhao(rs.getInt(8));
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
