package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import itf.IXianshicuxiaoManager;
import model.BeanXianshicuxiao;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class XianshicuxiaoManager implements IXianshicuxiaoManager{

	@Override
	public List<BeanXianshicuxiao> loadAll() throws BaseException 
	{
		// TODO Auto-generated method stub
		List<BeanXianshicuxiao> result=new ArrayList<BeanXianshicuxiao>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanXianshicuxiao u = new BeanXianshicuxiao();
			u.setCuxiaobianhao(rs.getInt(1));
			u.setShangpinbianhao(rs.getInt(2));
			u.setCuxiaojiage(rs.getDouble(3));
			u.setCuxiaoshuliang(rs.getDouble(4));
			u.setQishiriqi(rs.getTimestamp(5));
			u.setJieshuriqi(rs.getTimestamp(6));
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
