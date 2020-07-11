package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IYouhuiquanManager;
import model.BeanYouhuiquan;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class YouhuiquanManager implements IYouhuiquanManager{

	@Override
	public List<BeanYouhuiquan> loadAll() throws BaseException 
	{
		// TODO Auto-generated method stub
		List<BeanYouhuiquan> result=new ArrayList<BeanYouhuiquan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select youhuiquanbianhao,neirong,shiyongjine,jianmianjine,qishiriqi,jieshuriqi from youhuiquan";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanYouhuiquan u = new BeanYouhuiquan();
			u.setYouhuiquanbianhao(rs.getInt(1));
			u.setNeirong(rs.getString(2));
			u.setShiyongjine(rs.getDouble(3));
			u.setJianmianjine(rs.getDouble(4));
			u.setQishishijian(rs.getTimestamp(5));
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
