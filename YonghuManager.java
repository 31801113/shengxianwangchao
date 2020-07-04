package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.BeanGuanliyuan;
import model.BeanYonghu;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class YonghuManager {
	public BeanYonghu reg(Integer yonghubianhao, String xingming, String xingbie,String mima,String querenmima,String shoujihaoma,String youxiang,String suozaichengshi,int shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException {
		// TODO Auto-generated method stub
		if("".equals(yonghubianhao.toString()) || yonghubianhao == null){
			throw new BusinessException("用户编号不能为空");
		}
		if("".equals(xingming) || xingming == null){
			throw new BusinessException("姓名不能为空");
		}
		if("".equals(xingbie) || xingbie == null){
			throw new BusinessException("性别不能为空");
		}
		if(!(xingbie.equals("男") || xingbie.equals("女")))
		{
			throw new BusinessException("性别不能为除“男”、“女”外的其它字符");
		}
		if ("".equals(mima) || mima == null)
		{
			throw new BusinessException("密码不能为空");
		}
		if (!querenmima.equals(mima))
		{
			throw new BusinessException("两次输入密码不一致");
		}
		if ("".equals(shoujihaoma) || shoujihaoma == null)
		{
			throw new BusinessException("手机号码不能为空");
		}
		if ("".equals(youxiang) || youxiang == null)
		{
			throw new BusinessException("邮箱不能为空");
		}
		if ("".equals(suozaichengshi) || suozaichengshi == null)
		{
			throw new BusinessException("所在城市不能为空");
		}
		if (shifouhuiyuan != 0 && shifouhuiyuan != 1)
		{
			throw new BusinessException("请用1表示为会员，0表示非会员，请勿输入其它数字");
		}
		if (shifouhuiyuan == 1 && huiyuanjiezhishijian == null)
		{
			throw new BusinessException("会员截止日期为空");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time;
		try {
			time = new Timestamp(format.parse(huiyuanjiezhishijian).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("会员截止时间格式错误");
		}
		if (shifouhuiyuan == 1 && time.getTime() < System.currentTimeMillis())
		{
			throw new BusinessException("会员截止日期早于当前时间，会员信息无意义");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,yonghubianhao);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("用户账号已经存在");
			rs.close();
			pst.close();
			sql="insert into yonghu(yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian) values (?,?,?,?,?,?,?,NOW(),?,?);";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, yonghubianhao);
			pst.setString(2, xingming);
			pst.setString(3, xingbie);
			pst.setString(4, mima);
			pst.setString(5, shoujihaoma);
			pst.setString(6, youxiang);
			pst.setString(7, suozaichengshi);
			pst.setInt(8, shifouhuiyuan);
			pst.setTimestamp(9, time);
			pst.execute();
			pst.close();
			BeanYonghu u=new BeanYonghu();
			u.setYonghubianhao(yonghubianhao);
			u.setXingming(xingming);
			u.setXingbie(xingbie);
			u.setMima(mima);
			u.setShoujihaoma(shoujihaoma);
			u.setYouxiang(youxiang);
			u.setSuozaichengshi(suozaichengshi);
			u.setZhuceshijian(new Timestamp(System.currentTimeMillis()));
			u.setShifouhuiyuan(shifouhuiyuan);
			u.setHuiyuanjiezhiriqi(time);
			return u;
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
	
	public BeanYonghu login(Integer yonghubianhao, String mima) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,yonghubianhao);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("员工账号不存在");
			BeanGuanliyuan u=new BeanGuanliyuan();
			u.setYuangongbianhao(rs.getInt(1));
			u.setYuangongxingming(rs.getString(2));
			u.setDenglumima(rs.getString(3));
			if(!denglumima.equals(u.getDenglumima())) throw new BusinessException("密码错误");
			rs.close();
			pst.close();
			return u;
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
