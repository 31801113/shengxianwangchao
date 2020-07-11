package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import itf.IShangpindingdanManager;
import model.BeanShangpindingdan;
import model.BeanYonghu;
import util.BaseException;
import util.DBUtil;
import util.DbException;

public class ShangpindingdanManager implements IShangpindingdanManager{
	public List<BeanShangpindingdan> loadAll() throws BaseException
	{
		List<BeanShangpindingdan> result=new ArrayList<BeanShangpindingdan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select dingdanbianhao,yuanshijine,jiesuanjine,shiyongyouhuiquanbianhao,yaoqiusongdashijian,peisongdizhibianhao,dingdanzhuangtai from shangpindingdan where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanShangpindingdan u = new BeanShangpindingdan();
			u.setDingdanbianhao(rs.getInt(1));
			u.setYonghubianhao(BeanYonghu.currentLoginYonghu.getYonghubianhao());
			u.setYuanshijine(rs.getDouble(2));
			u.setJiesuanjine(rs.getDouble(3));
			u.setShiyongyouhuiquanbianhao(rs.getInt(4));
			u.setYaoqiusongdashijian(rs.getTimestamp(5));
			u.setPeisongdizhibianhao(rs.getInt(6));
			u.setDingdanzhuangtai(rs.getString(7));
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
