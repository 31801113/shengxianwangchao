package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BeanCaipu;

import util.BaseException;
import util.DBUtil;
import util.DbException;

public class CaipuManager {
	public List<BeanCaipu> loadAll() throws BaseException
	{
		List<BeanCaipu> result=new ArrayList<BeanCaipu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanCaipu u = new BeanCaipu();
			u.setCaipubianhao(rs.getInt(1));
			u.setCaipumingcheng(rs.getString(2));
			u.setBuzhou(rs.getString(3));
			u.setTupian(rs.getString(4));
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
