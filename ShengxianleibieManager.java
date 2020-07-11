package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IShengxianleibieManager;
import model.BeanShengxianleibie;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class ShengxianleibieManager implements IShengxianleibieManager{
	public List<BeanShengxianleibie> loadAll() throws BaseException
	{
		List<BeanShengxianleibie> result=new ArrayList<BeanShengxianleibie>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanShengxianleibie u = new BeanShengxianleibie();
			u.setLeibiebianhao(rs.getInt(1));
			u.setLeibiemingcheng(rs.getString(2));
			u.setLeibiemiaoshu(rs.getString(3));
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
