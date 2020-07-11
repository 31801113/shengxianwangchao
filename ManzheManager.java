package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IManzheManager;
import model.BeanManzhe;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class ManzheManager implements IManzheManager{

	@Override
	public List<BeanManzhe> loadAll() throws BaseException 
	{
		// TODO Auto-generated method stub
		List<BeanManzhe> result=new ArrayList<BeanManzhe>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select manzhebianhao,neirong,shiyongshangpinshuliang,zhekou,qishiriqi,jieshuriqi from manzhe";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanManzhe u = new BeanManzhe();
			u.setManzhebianhao(rs.getInt(1));
			u.setNeirong(rs.getString(2));
			u.setShiyongshangpinshuliang(rs.getDouble(3));
			u.setZhekou(rs.getDouble(4));
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
