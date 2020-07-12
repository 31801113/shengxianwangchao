package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IShangpinpingjiaManager;
import model.BeanShangpinpingjia;
import model.BeanYonghu;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class ShangpinpingjiaManager implements IShangpinpingjiaManager{
	public List<BeanShangpinpingjia> loadAll() throws BaseException 
	{
		// TODO Auto-generated method stub
		List<BeanShangpinpingjia> result=new ArrayList<BeanShangpinpingjia>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shangpinbianhao,pingjiayonghubianhao,pingjianeirong,pingjiariqi,xingji,zhaopian from shangpinpingjia where pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanShangpinpingjia u = new BeanShangpinpingjia();
			u.setShangpinbianhao(rs.getInt(1));
			u.setPingjiayonghubianhao(rs.getInt(2));
			u.setPingjianeirong(rs.getString(3));
			u.setPingjiariqi(rs.getTimestamp(4));
			u.setXingji(rs.getInt(5));
			u.setZhaopian(rs.getString(6));
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
