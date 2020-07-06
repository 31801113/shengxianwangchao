package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itf.IGuanliyuanManager;
import model.BeanGuanliyuan;
import model.BeanYonghu;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;


public class GuanliyuanManager implements IGuanliyuanManager{
	public BeanGuanliyuan reg(String yuangongbianhao, String yuangongxingming, String denglumima,String querenmima) throws BaseException {
		// TODO Auto-generated method stub
		if("".equals(yuangongbianhao) || yuangongbianhao == null){
			throw new BusinessException("员工编号不能为空");
		}
		if("".equals(yuangongxingming) || yuangongxingming == null){
			throw new BusinessException("员工姓名不能为空");
		}
		if ("".equals(denglumima) || denglumima == null)
		{
			throw new BusinessException("密码不能为空");
		}
		if(!denglumima.equals(querenmima)){
			throw new BusinessException("两次输入密码必须一致");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yuangongbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("管理员账号已经存在");
			rs.close();
			pst.close();
			sql="insert into guanliyuan (yuangongbianhao,yuangongxingming,denglumima) values (?,?,?);";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(yuangongbianhao));
			pst.setString(2, yuangongxingming);
			pst.setString(3, denglumima);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"注册成功", "注册结果",JOptionPane.PLAIN_MESSAGE);
			BeanGuanliyuan u=new BeanGuanliyuan();
			u.setYuangongbianhao(Integer.valueOf(yuangongbianhao));
			u.setYuangongxingming(yuangongxingming);
			u.setDenglumima(denglumima);
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
	
	public BeanGuanliyuan login(String yuangongbianhao, String denglumima) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			if (yuangongbianhao == null || "".equals(yuangongbianhao))
			{
				throw new BusinessException("员工编号不能为空");
			}
			if (denglumima == null || "".equals(denglumima))
			{
				throw new BusinessException("登陆密码不能为空");
			}
			conn=DBUtil.getConnection();
			String sql="select yuangongbianhao,yuangongxingming,denglumima from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yuangongbianhao));
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
	
	public void changePwd(BeanGuanliyuan user, String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException {
		// TODO Auto-generated method stub
		if("".equals(olddenglumima) || olddenglumima==null) throw new BusinessException("原始密码不能为空");
		if("".equals(newdenglumima) || newdenglumima==null) throw new BusinessException("新密码不能为空");
		if (!newdenglumima.equals(newdenglumima2)) throw new BusinessException("新密码两次输入不同");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanGuanliyuan.currentLoginGuanliyuan.getYuangongbianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!BeanGuanliyuan.currentLoginGuanliyuan.getDenglumima().equals(olddenglumima)) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update guanliyuan set denglumima = ? where yuangongbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newdenglumima);
			pst.setInt(2, user.getYuangongbianhao());
			pst.execute();
			pst.close();
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
	
	public BeanGuanliyuan ZengjiaYonghu(String yonghubianhao, String xingming, String xingbie,String mima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			if("".equals(yonghubianhao) || yonghubianhao == null){
				throw new BusinessException("用户编号不能为空");
			}
			if("".equals(xingming) || xingming == null){
				throw new BusinessException("姓名不能为空");
			}
			if("".equals(xingbie) || xingbie == null){
				throw new BusinessException("性别不能为空");
			}
			if(!(xingbie.equals("nan") || xingbie.equals("nv")))
			{
				throw new BusinessException("性别不能为除“nan”、“nv”外的其它字符");
			}
			if ("".equals(mima) || mima == null)
			{
				throw new BusinessException("密码不能为空");
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
			if (shifouhuiyuan == null || "".equals(shifouhuiyuan))
			{
				throw new BusinessException("是否会员不能为空");
			}
			int shifouhuiyuan1 = Integer.valueOf(shifouhuiyuan);
			if (shifouhuiyuan1 != 0 && shifouhuiyuan1 != 1)
			{
				throw new BusinessException("请用1表示为会员，0表示非会员，请勿输入其它数字");
			}
			if (shifouhuiyuan1 == 1 && huiyuanjiezhishijian == null)
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
			if (shifouhuiyuan1 == 1 && time.getTime() < System.currentTimeMillis())
			{
				throw new BusinessException("会员截止日期早于当前时间，会员信息无意义");
			}
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("用户账号已经存在");
			sql="insert into yonghu(yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian) values (?,?,?,?,?,?,?,NOW(),?,?);";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(yonghubianhao));
			pst.setString(2, xingming);
			pst.setString(3, xingbie);
			pst.setString(4, mima);
			pst.setString(5, shoujihaoma);
			pst.setString(6, youxiang);
			pst.setString(7, suozaichengshi);
			pst.setInt(8, shifouhuiyuan1);
			pst.setTimestamp(9, time);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan ShanchuYonghu(String yonghubianhao,String xingming,String mima) throws BaseException
	{
		Connection conn=null;
		try {
			if (yonghubianhao == null || "".equals(yonghubianhao))
			{
				throw new BaseException("用户编号不能为空");
			}
			if (xingming == null || "".equals(xingming))
			{
				throw new BaseException("用户姓名不能为空");
			}
			if (mima == null || "".equals(mima))
			{
				throw new BaseException("密码不能为空");
			}
			conn = DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,mima from yonghu where yonghubianhao = ? and xingming = ? and mima = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			pst.setString(2, xingming);
			pst.setString(3, mima);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户不存在");
			sql = "select dizhibianhao from peisongdizhi where yonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("配送地址表中已存在该用户信息，不能删除");
			sql = "select dingdanbianhao from shangpindingdan where yonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品订单表中已存在该用户信息，不能删除");
			sql = "select shangpinbianhao from shangpinpingjia where pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品评价表中已存在该用户信息，不能删除");
			sql = "delete from yonghu where yonghubianhao = ? and xingming = ? and mima = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			pst.setString(2, xingming);
			pst.setString(3, mima);
			pst.execute();
			JOptionPane.showMessageDialog(null,"删除成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
		}catch (SQLException e) {
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
	
	public BeanGuanliyuan BianjiYonghu(String yonghubianhao, String xingming, String xingbie,String mima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException
	{
		Connection conn=null;
		try {
			if("".equals(yonghubianhao) || yonghubianhao == null){
				throw new BusinessException("用户编号不能为空");
			}
			if("".equals(xingming) || xingming == null){
				throw new BusinessException("姓名不能为空");
			}
			if("".equals(xingbie) || xingbie == null){
				throw new BusinessException("性别不能为空");
			}
			if(!(xingbie.equals("nan") || xingbie.equals("nv")))
			{
				throw new BusinessException("性别不能为除“nan”、“nv”外的其它字符");
			}
			if ("".equals(mima) || mima == null)
			{
				throw new BusinessException("密码不能为空");
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
			if (shifouhuiyuan == null || "".equals(shifouhuiyuan))
			{
				throw new BusinessException("是否会员不能为空");
			}
			int shifouhuiyuan1 = Integer.valueOf(shifouhuiyuan);
			if (shifouhuiyuan1 != 0 && shifouhuiyuan1 != 1)
			{
				throw new BusinessException("请用1表示为会员，0表示非会员，请勿输入其它数字");
			}
			if (shifouhuiyuan1 == 1 && huiyuanjiezhishijian == null)
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
			if (shifouhuiyuan1 == 1 && time.getTime() < System.currentTimeMillis())
			{
				throw new BusinessException("会员截止日期早于当前时间，会员信息无意义");
			}
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("用户账号不存在");
			sql="update yonghu set xingming = ?,xingbie = ?,mima = ?,shoujihaoma = ?,youxiang = ?,suozaichengshi = ?,shifouhuiyuan = ?,huiyuanjiezhishijian = ? where yonghubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(yonghubianhao));
			pst.setString(9, xingming);
			pst.setString(2, xingbie);
			pst.setString(3, mima);
			pst.setString(4, shoujihaoma);
			pst.setString(5, youxiang);
			pst.setString(6, suozaichengshi);
			pst.setInt(7, shifouhuiyuan1);
			pst.setTimestamp(8, time);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan ChaxunYonghu(String yonghubianhao, String xingming)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0,flag2 = 0;
			if (yonghubianhao == null || "".equals(yonghubianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (xingming == null || "".equals(xingming))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询用户编号和姓名不能同时为空");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag1 == 0)
			{
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询用户不存在");
			JOptionPane.showMessageDialog(null,"用户编号   "+"姓名   "+"性别   "+"密码   "+"手机号码   "+"邮箱   "+"所在城市   "+"注册时间   "+"是否会员   "+"会员截止时间\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10), "查询结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "用户编号   "+"姓名   "+"性别   "+"密码   "+"手机号码   "+"邮箱   "+"所在城市   "+"注册时间   "+"是否会员   "+"会员截止时间\n";
				String sql="select yonghubianhao from yonghu where xingming = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,xingming);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询用户不存在");
				sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where xingming = ? order by yonghubianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,xingming);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10) + "\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ? and xingming = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(yonghubianhao));
				pst.setString(2, xingming);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询用户不存在");
				JOptionPane.showMessageDialog(null,"用户编号   "+"姓名   "+"性别   "+"密码   "+"手机号码   "+"邮箱   "+"所在城市   "+"注册时间   "+"是否会员   "+"会员截止时间\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10), "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			return BeanGuanliyuan.currentLoginGuanliyuan;
		}catch (SQLException e) {
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
	
	public BeanGuanliyuan ZengjiaShengxianleibie(String leibiebianhao,String leibiemingcheng,String leibiemiaoshu) throws BaseException
	{
		if (leibiebianhao == null || "".equals(leibiebianhao))
		{
			throw new BusinessException("类别编号不能为空");
		}
		if (leibiemingcheng == null || "".equals(leibiemingcheng))
		{
			throw new BusinessException("类别名称不能为空");
		}
		if (leibiemiaoshu == null || "".equals(leibiemiaoshu))
		{
			throw new BusinessException("类别描述不能为空");
		}
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("类别已存在");
			sql = "insert into shengxianleibie(leibiebianhao,leibiemingcheng,leibiemiaoshu) values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			pst.setString(2, leibiemingcheng);
			pst.setString(3, leibiemiaoshu);
			pst.execute();
			JOptionPane.showMessageDialog(null,"添加成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan ShanchuShengxianleibie(String leibiebianhao) throws BaseException
	{
		Connection conn=null;
		try {
			if (leibiebianhao == null || "".equals(leibiebianhao))
			{
				throw new BusinessException("类别编号不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("生鲜类别不存在");
			sql = "select shangpinbianhao from shangpin where leibiebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品信息表中存在此生鲜类别，不能删除");
			sql = "delete from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			pst.execute();
			JOptionPane.showMessageDialog(null,"删除成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan BianjiShengxianleibie(String leibiebianhao,String leibiemingcheng,String leibiemiaoshu) throws BaseException
	{
		Connection conn=null;
		try {
			if (leibiebianhao == null || "".equals(leibiebianhao))
			{
				throw new BusinessException("类别编号不能为空");
			}
			if (leibiemingcheng == null || "".equals(leibiemingcheng))
			{
				throw new BusinessException("类别名称不能为空");
			}
			if (leibiemiaoshu == null || "".equals(leibiemiaoshu))
			{
				throw new BusinessException("类别描述不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("类别编号不存在");
			sql = "update shengxianleibie set leibiemingcheng = ?,leibiemiaoshu = ? where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, leibiemingcheng);
			pst.setString(2, leibiemiaoshu);
			pst.setInt(3, Integer.valueOf(leibiebianhao));
			pst.execute();
			JOptionPane.showMessageDialog(null,"更新成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan ChaxunShengxianleibie(String leibiebianhao, String leibiemingcheng)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0;
			int flag2 = 0;
			if (leibiebianhao == null || "".equals(leibiebianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (leibiebianhao == null || "".equals(leibiemingcheng))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询类别编号和类别名称不能同时为空");
			}
            conn = DBUtil.getConnection();
            if (flag1 == 1 && flag2 == 0)
            {
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiebianhao = ?";
			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			   pst.setInt(1,Integer.valueOf(leibiebianhao));
			   java.sql.ResultSet rs=pst.executeQuery();
			   if(!rs.next()) throw new BusinessException("查询生鲜类别不存在");
			   JOptionPane.showMessageDialog(null,"类别编号   "+"类别名称   "+"类别描述\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3),"查询结果",JOptionPane.PLAIN_MESSAGE);
			   return BeanGuanliyuan.currentLoginGuanliyuan;
            }
            if (flag1 == 0 && flag2 == 1)
            {
               String resultString = "类别编号   "+"类别名称   "+"类别描述\n";
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiemingcheng = ?";
 			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
 			   pst.setString(1,leibiemingcheng);
 			   java.sql.ResultSet rs=pst.executeQuery();
 			   if(!rs.next()) throw new BusinessException("查询生鲜类别姓名不存在");
 			   sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiemingcheng = ? order by leibiebianhao";
			   pst=conn.prepareStatement(sql);
			   pst.setString(1,leibiemingcheng);
			   rs=pst.executeQuery();
			   while (rs.next())
			   {
			   resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"\n";
			   }
			   JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
			   return BeanGuanliyuan.currentLoginGuanliyuan;
 			   
            }
            if (flag1 == 1 && flag2 == 1)
            {
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiebianhao = ? and leibiemingcheng = ?";
 			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
 			   pst.setInt(1,Integer.valueOf(leibiebianhao));
 			   pst.setString(2, leibiemingcheng);
 			   java.sql.ResultSet rs=pst.executeQuery();
 			   if(!rs.next()) throw new BusinessException("查询生鲜类别不存在");
 			   JOptionPane.showMessageDialog(null,"类别编号   "+"类别名称   "+"类别描述\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3),"查询结果",JOptionPane.PLAIN_MESSAGE);
 			   return BeanGuanliyuan.currentLoginGuanliyuan;
            }
            return BeanGuanliyuan.currentLoginGuanliyuan;
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
	
	public BeanGuanliyuan ZengjiaShangpin(String shangpinbianhao, String shangpinmingcheng, String jiage,String huiyuanjia, String shuliang , String guige, String xiangqing, String leibiebianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (shangpinbianhao == null || "".equals(shangpinbianhao))
			{
				throw new BusinessException("商品编号不能为空");
			}
			if (shangpinmingcheng == null || "".equals(shangpinmingcheng))
			{
				throw new BusinessException("商品名称不能为空");
			}
			if (jiage == null ||"".equals(jiage))
			{
				throw new BusinessException("价格不能为空");
			}
			if (huiyuanjia == null || "".equals(huiyuanjia))
			{
				throw new BusinessException("会员价不能为空");
			}
			if (shuliang == null || "".equals(shuliang))
			{
				throw new BusinessException("数量不能为空");
			}
			if (guige == null || "".equals(guige))
			{
				throw new BusinessException("规格不能为空");
			}
			if (xiangqing == null || "".equals(xiangqing))
			{
				throw new BusinessException("详情不能为空");
			}
			if (leibiebianhao == null || "".equals(leibiebianhao ))
			{
				throw new BusinessException("类别编号");
			}
            conn = DBUtil.getConnection();
            String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询用户不存在");
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
