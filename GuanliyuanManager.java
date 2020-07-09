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
	
	public BeanGuanliyuan changePwd(BeanGuanliyuan user, String olddenglumima, String newdenglumima,
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
			JOptionPane.showMessageDialog(null,"密码修改成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
			if (flag1 == 1 && flag2 == 0)
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
			if (Double.valueOf(huiyuanjia) > Double.valueOf(jiage))
			{
				throw new BusinessException("会员价大于平时价格");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiebianhao from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("商品编号已存在");
			sql = "select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if (!rs.next()) throw new BusinessException("类别编号不存在");
			sql = "insert into shangpin(shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao) values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setString(2, shangpinmingcheng);
			pst.setDouble(3, Double.valueOf(jiage));
			pst.setDouble(4, Double.valueOf(huiyuanjia));
			pst.setDouble(5, Double.valueOf(shuliang));
			pst.setString(6, guige);
			pst.setString(7, xiangqing);
			pst.setInt(8, Integer.valueOf(leibiebianhao));
			pst.execute();
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
	
	public BeanGuanliyuan ShanchuShangpin(String shangpinbianhao, String shangpinmingcheng)throws BaseException
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
            conn = DBUtil.getConnection();
            String sql="select shangpinmingcheng from shangpin where shangpinbianhao = ? and shangpinmingcheng = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setString(2, shangpinmingcheng);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("商品不存在");
			sql = "select caipubianhao from shangpincaiputuijian where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品在商品菜谱推荐表中出现，不能删除");
			sql = "select pingjiayonghubianhao from shangpinpingjia where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品在商品评价表中出现，不能删除");
			sql = "select manzhebianhao from manzheshangpinguanlian where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品在满折商品关联表中出现，不能删除");
			sql = "select cuxiaobianhao from xianshicuxiao where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品在限时促销表中出现，不能删除");
			sql = "select dingdanbianhao from dingdanxiangqing where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("商品在订单详情表中出现，不能删除");
			sql = "delete from shangpin where shangpinbianhao = ? and shangpinmingcheng = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setString(2, shangpinmingcheng);
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
	
	public BeanGuanliyuan BianjiShangpin(String shangpinbianhao, String shangpinmingcheng, String jiage,String huiyuanjia, String shuliang , String guige, String xiangqing, String leibiebianhao)throws BaseException
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
			if (Double.valueOf(huiyuanjia) > Double.valueOf(jiage))
			{
				throw new BusinessException("会员价大于平时价格");
			}
            conn = DBUtil.getConnection();
            String sql="select shangpinmingcheng from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询用户不存在");
			sql = "select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if (!rs.next()) throw new BusinessException("类别编号不存在");
			sql = "update shangpin set shangpinmingcheng = ?,jiage = ?,huiyuanjia = ?,shuliang = ?,guige = ?,xiangqing = ?,leibiebianhao = ? where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(8, Integer.valueOf(shangpinbianhao));
			pst.setString(1, shangpinmingcheng);
			pst.setDouble(2, Double.valueOf(jiage));
			pst.setDouble(3, Double.valueOf(huiyuanjia));
			pst.setDouble(4, Double.valueOf(shuliang));
			pst.setString(5, guige);
			pst.setString(6, xiangqing);
			pst.setInt(7, Integer.valueOf(leibiebianhao));
			pst.execute();
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
	
	public BeanGuanliyuan ChaxunShangpin(String shangpinbianhao, String shangpinmingcheng)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0,flag2 = 0;
			if (shangpinbianhao == null || "".equals(shangpinbianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (shangpinmingcheng == null || "".equals(shangpinmingcheng))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询商品编号和商品名称不能同时为空");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询商品不存在");
			JOptionPane.showMessageDialog(null,"商品编号   "+"商品名称   "+"价格   "+"会员价   "+"数量   "+"规格   "+"详情   "+"类别编号\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8),"查询结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "商品编号   "+"商品名称   "+"价格   "+"会员价   "+"数量   "+"规格   "+"详情   "+"类别编号\n";
				String sql="select shangpinbianhao from shangpin where shangpinmingcheng = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,shangpinmingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询商品不存在");
				sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinmingcheng = ? order by shangpinbianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,shangpinmingcheng);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinmingcheng = ? and shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(shangpinbianhao));
				pst.setString(1, shangpinmingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询商品不存在");
				JOptionPane.showMessageDialog(null,"商品编号   "+"商品名称   "+"价格   "+"会员价   "+"数量   "+"规格   "+"详情   "+"类别编号\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8), "查询结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ZengjiaYouhuiquan(String youhuiquanbianhao, String neirong,String shiyongjine,String jianmianjine,String qishiriqi,String jiezhiriqi)throws BaseException
	{
		Connection conn=null;
		try {
			if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
			{
				throw new BusinessException("优惠券编号不能为空");
			}
			if (neirong == null || "".equals(neirong))
			{
				throw new BusinessException("内容不能为空");
			}
			if (shiyongjine == null ||"".equals(shiyongjine))
			{
				throw new BusinessException("使用金额不能为空");
			}
			if (jianmianjine == null || "".equals(jianmianjine))
			{
				throw new BusinessException("减免金额不能为空");
			}
			if (qishiriqi == null || "".equals(qishiriqi))
			{
				throw new BusinessException("起始日期不能为空");
			}
			if (jiezhiriqi == null || "".equals(jiezhiriqi))
			{
				throw new BusinessException("截止日期不能为空");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time1,time2;
			try {
				time1 = new Timestamp(format.parse(qishiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("起始日期时间格式错误");
			}
			try {
				time2 = new Timestamp(format.parse(jiezhiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("截止日期时间格式错误");
			}
			if (time1.getTime() > time2.getTime())
			{
				throw new BusinessException("起始日期晚于截止日期");
			}
			if (Double.valueOf(shiyongjine) < Double.valueOf(jianmianjine))
			{
				throw new BusinessException("减免金额大于使用金额");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("优惠券编号已存在");
			sql = "insert into youhuiquan(youhuiquanbianhao,neirong,shiyongjine,jianmianjine,qishiriqi,jieshuriqi) values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(youhuiquanbianhao));
			pst.setString(2, neirong);
			pst.setDouble(3, Double.valueOf(shiyongjine));
			pst.setDouble(4, Double.valueOf(jianmianjine));
			pst.setTimestamp(5, time1);
			pst.setTimestamp(6, time2);
			pst.execute();
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
	
	public BeanGuanliyuan ShanchuYouhuiquan(String youhuiquanbianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
			{
				throw new BusinessException("优惠券编号不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("优惠券不存在");
			sql="select dingdanbianhao from shangpindingdan where shiyongyouhuiquanbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("优惠券在商品订单表中出现，不能删除");
			sql="delete from youhuiquan where youhuiquanbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
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
	
	public BeanGuanliyuan BianjiYouhuiquan(String youhuiquanbianhao, String neirong,String shiyongjine,String jianmianjine,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
			if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
			{
				throw new BusinessException("优惠券编号不能为空");
			}
			if (neirong == null || "".equals(neirong))
			{
				throw new BusinessException("内容不能为空");
			}
			if (shiyongjine == null ||"".equals(shiyongjine))
			{
				throw new BusinessException("使用金额不能为空");
			}
			if (jianmianjine == null || "".equals(jianmianjine))
			{
				throw new BusinessException("减免金额不能为空");
			}
			if (qishiriqi == null || "".equals(qishiriqi))
			{
				throw new BusinessException("起始日期不能为空");
			}
			if (jieshuriqi == null || "".equals(jieshuriqi))
			{
				throw new BusinessException("截止日期不能为空");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time1,time2;
			try {
				time1 = new Timestamp(format.parse(qishiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("起始日期时间格式错误");
			}
			try {
				time2 = new Timestamp(format.parse(jieshuriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("截止日期时间格式错误");
			}
			if (time1.getTime() > time2.getTime())
			{
				throw new BusinessException("起始日期晚于截止日期");
			}
			if (Double.valueOf(shiyongjine) < Double.valueOf(jianmianjine))
			{
				throw new BusinessException("减免金额大于使用金额");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("优惠券编号不存在");
			sql = "update youhuiquan set neirong = ?,shiyongjine = ?,jianmianjine = ?,qishiriqi = ?,jieshuriqi = ? where youhuiquanbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(6, Integer.valueOf(youhuiquanbianhao));
			pst.setString(1, neirong);
			pst.setDouble(2, Double.valueOf(shiyongjine));
			pst.setDouble(3, Double.valueOf(jianmianjine));
			pst.setTimestamp(4, time1);
			pst.setTimestamp(5, time2);
			pst.execute();
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
	
	public BeanGuanliyuan ChaxunYouhuiquan(String youhuiquanbianhao)throws BaseException
	{
		Connection conn=null;
		try {
		if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
		{
			throw new BusinessException("查询优惠券编号不能为空");
		}
		conn = DBUtil.getConnection();
		String sql="select youhuiquanbianhao,neirong,shiyongjine,jianmianjine,qishiriqi,jieshuriqi from youhuiquan where youhuiquanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("查询优惠券不存在");
		JOptionPane.showMessageDialog(null,"优惠券编号   "+"内容   "+"使用金额   "+"减免金额   "+"起始日期   "+"结束日期\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"查询结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ZengjiaManzhe(String manzhebianhao, String neirong,String shiyongshangpinshuliang,String zhekou,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (manzhebianhao == null || "".equals(manzhebianhao))
		{
			throw new BusinessException("满折编号不能为空");
		}
		if (neirong == null || "".equals(neirong))
		{
			throw new BusinessException("内容不能为空");
		}
		if (shiyongshangpinshuliang == null || "".equals(shiyongshangpinshuliang))
		{
			throw new BusinessException("适用商品数量不能为空");
		}
		if (zhekou == null || "".equals(zhekou))
		{
			throw new BusinessException("折扣不能为空");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("起始日期不能为空");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("结束日期不能为空");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("起始日期时间格式错误");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("结束日期时间格式错误");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("起始日期晚于结束日期");
		}
		if (Double.valueOf(zhekou) > 1)
		{
			throw new BusinessException("折扣大于1");
		}
		conn = DBUtil.getConnection();
        String sql="select neirong from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("满折编号已存在");
		sql = "insert manzhe(manzhebianhao,neirong,shiyongshangpinshuliang,zhekou,qishiriqi,jieshuriqi)values(?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(manzhebianhao));
		pst.setString(2, neirong);
		pst.setDouble(3, Double.valueOf(shiyongshangpinshuliang));
		pst.setDouble(4, Double.valueOf(zhekou));
		pst.setTimestamp(5, time1);
		pst.setTimestamp(6, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ShanchuManzhe(String manzhebianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (manzhebianhao == null || "".equals(manzhebianhao))
			{
				throw new BusinessException("满折编号不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from manzhe where manzhebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("满折不存在");
			sql="select shangpinbianhao from manzheshangpinguanlian where manzhebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("满折在满折商品关联表中出现，不能删除");
			sql="delete from manzhe where manzhebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
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
	
	public BeanGuanliyuan BianjiManzhe(String manzhebianhao, String neirong,String shiyongshangpinshuliang,String zhekou,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (manzhebianhao == null || "".equals(manzhebianhao))
		{
			throw new BusinessException("满折编号不能为空");
		}
		if (neirong == null || "".equals(neirong))
		{
			throw new BusinessException("内容不能为空");
		}
		if (shiyongshangpinshuliang == null || "".equals(shiyongshangpinshuliang))
		{
			throw new BusinessException("适用商品数量不能为空");
		}
		if (zhekou == null || "".equals(zhekou))
		{
			throw new BusinessException("折扣不能为空");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("起始日期不能为空");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("结束日期不能为空");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("起始日期时间格式错误");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("结束日期时间格式错误");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("起始日期晚于结束日期");
		}
		if (Double.valueOf(zhekou) > 1)
		{
			throw new BusinessException("折扣大于1");
		}
		conn = DBUtil.getConnection();
        String sql="select neirong from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("满折编号不存在");
		sql = "update manzhe set neirong = ?,shiyongshangpinshuliang = ?,zhekou = ?,qishiriqi = ?,jieshuriqi = ? where manzhebianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(6, Integer.valueOf(manzhebianhao));
		pst.setString(1, neirong);
		pst.setDouble(2, Double.valueOf(shiyongshangpinshuliang));
		pst.setDouble(3, Double.valueOf(zhekou));
		pst.setTimestamp(4, time1);
		pst.setTimestamp(5, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ChaxunManzhe(String manzhebianhao)throws BaseException
	{
		Connection conn=null;
		try {
		if (manzhebianhao == null || "".equals(manzhebianhao))
		{
			throw new BusinessException("查询满折编号不能为空");
		}
		conn = DBUtil.getConnection();
		String sql="select manzhebianhao,neirong,shiyongshangpinshuliang,zhekou,qishiriqi,jieshuriqi from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("查询满折不存在");
		JOptionPane.showMessageDialog(null,"满折编号   "+"内容   "+"使用商品数量   "+"折扣   "+"起始日期   "+"结束日期\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"查询结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ZengjiaXianshicuxiao(String cuxiaobianhao, String shangpinbianhao,String cuxiaojiage,String cuxiaoshuliang,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (cuxiaobianhao == null || "".equals(cuxiaobianhao))
		{
			throw new BusinessException("促销编号不能为空");
		}
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("内容不能为空");
		}
		if (cuxiaojiage == null || "".equals(cuxiaojiage))
		{
			throw new BusinessException("促销价格不能为空");
		}
		if (cuxiaoshuliang == null || "".equals(cuxiaoshuliang))
		{
			throw new BusinessException("促销数量不能为空");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("起始日期不能为空");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("结束日期不能为空");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("起始日期时间格式错误");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("结束日期时间格式错误");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("起始日期晚于结束日期");
		}
		conn = DBUtil.getConnection();
        String sql="select shangpinbianhao from xianshicuxiao where cuxiaobianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(cuxiaobianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("促销编号已存在");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shangpinbianhao));
		rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("商品编号不存在");
		sql = "insert into xianshicuxiao(cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi) values(?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(cuxiaobianhao));
		pst.setInt(2, Integer.valueOf(shangpinbianhao));
		pst.setDouble(3, Double.valueOf(cuxiaojiage));
		pst.setDouble(4, Double.valueOf(cuxiaoshuliang));
		pst.setTimestamp(5, time1);
		pst.setTimestamp(6, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ShanchuXianshicuxiao(String cuxiaobianhao, String shangpinbianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (cuxiaobianhao == null || "".equals(cuxiaobianhao))
			{
				throw new BusinessException("促销编号不能为空");
			}
			if (shangpinbianhao == null || "".equals(shangpinbianhao))
			{
				throw new BusinessException("商品编号不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select cuxiaojiage from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			pst.setInt(2, Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("限时促销不存在");
			sql="delete from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			pst.setInt(2, Integer.valueOf(shangpinbianhao));
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
	
	public BeanGuanliyuan BianjiXianshicuxiao(String cuxiaobianhao, String shangpinbianhao,String cuxiaojiage,String cuxiaoshuliang,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (cuxiaobianhao == null || "".equals(cuxiaobianhao))
		{
			throw new BusinessException("促销编号不能为空");
		}
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("内容不能为空");
		}
		if (cuxiaojiage == null || "".equals(cuxiaojiage))
		{
			throw new BusinessException("促销价格不能为空");
		}
		if (cuxiaoshuliang == null || "".equals(cuxiaoshuliang))
		{
			throw new BusinessException("促销数量不能为空");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("起始日期不能为空");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("结束日期不能为空");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("起始日期时间格式错误");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("结束日期时间格式错误");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("起始日期晚于结束日期");
		}
		conn = DBUtil.getConnection();
        String sql="select shangpinbianhao from xianshicuxiao where cuxiaobianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(cuxiaobianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("促销编号不存在");
		sql = "update xianshicuxiao set shangpinbianhao = ?,cuxiaojiage = ?,cuxiaoshuliang = ?,qishiriqi = ?,jieshuriqi = ? where cuxiaobianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(6, Integer.valueOf(cuxiaobianhao));
		pst.setInt(1, Integer.valueOf(shangpinbianhao));
		pst.setDouble(2, Double.valueOf(cuxiaojiage));
		pst.setDouble(3, Double.valueOf(cuxiaoshuliang));
		pst.setTimestamp(4, time1);
		pst.setTimestamp(5, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ChaxunXianshicuxiao(String cuxiaobianhao, String shangpinbianhao)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0,flag2 = 0;
			if (cuxiaobianhao == null || "".equals(cuxiaobianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (shangpinbianhao == null || "".equals(shangpinbianhao))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询促销编号和商品编号不能同时为空");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where cuxiaobianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询促销不存在");
			JOptionPane.showMessageDialog(null,"促销编号   "+"商品编号   "+"促销价格   "+"促销数量   "+"起始日期   "+"结束日期\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"查询结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "促销编号   "+"商品编号   "+"促销价格   "+"促销数量   "+"起始日期   "+"结束日期\n";
				String sql="select cuxiaojiage from xianshicuxiao where shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shangpinbianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询促销不存在");
				sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where shangpinbianhao = ? order by cuxiaobianhao";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shangpinbianhao));
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(shangpinbianhao));
				pst.setInt(1, Integer.valueOf(cuxiaobianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询促销不存在");
				JOptionPane.showMessageDialog(null,"促销编号   "+"商品编号   "+"促销价格   "+"促销数量   "+"起始日期   "+"结束日期\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6), "查询结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ZengjiaCaipu(String caipubianhao,String caipumingcheng,String caipuyongliao,String buzhou,String tupian)throws BaseException
	{
		Connection conn=null;
		try {
		if (caipubianhao == null || "".equals(caipubianhao))
		{
			throw new BusinessException("菜谱编号不能为空");
		}
		if (caipumingcheng == null || "".equals(caipumingcheng))
		{
			throw new BusinessException("菜谱名称不能为空");
		}
		if (caipuyongliao == null || "".equals(caipuyongliao))
		{
			throw new BusinessException("菜谱用料不能为空");
		}
		if (buzhou == null || "".equals(buzhou))
		{
			throw new BusinessException("步骤不能为空");
		}
		if (tupian == null || "".equals(tupian))
		{
			throw new BusinessException("图片不能为空");
		}
		conn = DBUtil.getConnection();
        String sql="select caipumingcheng from caipu where caipubianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caipubianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("菜谱编号已存在");
		sql = "insert into caipu(caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian) values(?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(caipubianhao));
		pst.setString(2, caipumingcheng);
		pst.setString(3, caipuyongliao);
		pst.setString(4, buzhou);
		pst.setString(5, tupian);
		pst.execute();
		JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ShanchuCaipu(String caipubianhao,String caipumingcheng)throws BaseException
	{
		Connection conn=null;
		try {
			if (caipubianhao == null || "".equals(caipubianhao))
			{
				throw new BusinessException("菜谱编号不能为空");
			}
			if (caipumingcheng == null || "".equals(caipumingcheng))
			{
				throw new BusinessException("菜谱名称不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select caipumingcheng from caipu where caipubianhao = ? and caipumingcheng = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			pst.setString(2, caipumingcheng);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("菜谱不存在");
			sql="select shangpinbianhao from shangpincaiputuijian where caipubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("菜谱在商品菜谱推荐表中出现，不能删除");
			sql="delete from caipu where caipubianhao = ? and caipumingcheng = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			pst.setString(2, caipumingcheng);
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
	
	public BeanGuanliyuan BainjiCaipu(String caipubianhao,String caipumingcheng,String caipuyongliao,String buzhou,String tupian)throws BaseException
	{
		Connection conn=null;
		try {
		if (caipubianhao == null || "".equals(caipubianhao))
		{
			throw new BusinessException("菜谱编号不能为空");
		}
		if (caipumingcheng == null || "".equals(caipumingcheng))
		{
			throw new BusinessException("菜谱名称不能为空");
		}
		if (caipuyongliao == null || "".equals(caipuyongliao))
		{
			throw new BusinessException("菜谱用料不能为空");
		}
		if (buzhou == null || "".equals(buzhou))
		{
			throw new BusinessException("步骤不能为空");
		}
		if (tupian == null || "".equals(tupian))
		{
			throw new BusinessException("图片不能为空");
		}
		conn = DBUtil.getConnection();
        String sql="select caipumingcheng from caipu where caipubianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caipubianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("菜谱不存在");
		sql = "update caipu set caipumingcheng = ?,caipuyongliao = ?,buzhou = ?,tupian = ? where caipubianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(5, Integer.valueOf(caipubianhao));
		pst.setString(1, caipumingcheng);
		pst.setString(2, caipuyongliao);
		pst.setString(3, buzhou);
		pst.setString(4, tupian);
		pst.execute();
		JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ChaxunCaipu(String caipubianhao,String caipumingcheng)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0,flag2 = 0;
			if (caipubianhao == null || "".equals(caipubianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (caipumingcheng == null || "".equals(caipumingcheng))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询菜谱编号和菜谱名称不能同时为空");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询菜谱不存在");
			JOptionPane.showMessageDialog(null,"菜谱编号   "+"菜谱名称   "+"菜谱用料   "+"步骤   "+"图片\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5),"查询结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "菜谱编号   "+"菜谱名称   "+"菜谱用料   "+"步骤   "+"图片\n";
				String sql="select caipubianhao from caipu where caipumingcheng = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询菜谱不存在");
				sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipumingcheng = ? order by caipubianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipumingcheng = ? and caipubianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				pst.setInt(2, Integer.valueOf(caipubianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询菜谱不存在");
				JOptionPane.showMessageDialog(null,"菜谱编号   "+"菜谱名称   "+"菜谱用料   "+"步骤   "+"图片\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5), "查询结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ZengjiaCaigou(String caigoudanbianhao,String shicaibianhao,String shuliang,String zhuangtai)throws BaseException
	{
		Connection conn=null;
		try {
		if (caigoudanbianhao == null || "".equals(caigoudanbianhao))
		{
			throw new BusinessException("采购编号单不能为空");
		}
		if (shicaibianhao == null || "".equals(shicaibianhao))
		{
			throw new BusinessException("食材编号不能为空");
		}
		if (shuliang == null || "".equals(shuliang))
		{
			throw new BusinessException("数量不能为空");
		}
		if (zhuangtai == null || "".equals(zhuangtai))
		{
			throw new BusinessException("状态不能为空");
		}
		if (!(zhuangtai.equals("xiadan") || zhuangtai.equals("zaitu") || zhuangtai.equals("ruku")))
		{
			throw new BusinessException("状态只能为“xiadan”或“zaitu”或“ruku”");
		}
		conn = DBUtil.getConnection();
        String sql="select shicaibianhao from shangpincaigou where caigoudanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caigoudanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("采购单编号已存在");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		rs = pst.executeQuery();
		if (!rs.next()) throw new BusinessException("食材编号不存在");
		sql = "insert into shangpincaigou(caigoudanbianhao,shicaibianhao,shuliang,zhuangtai) values(?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(caigoudanbianhao));
		pst.setInt(2, Integer.valueOf(shicaibianhao));
		pst.setDouble(3, Double.valueOf(shuliang));
		pst.setString(4, zhuangtai);
		pst.execute();
		JOptionPane.showMessageDialog(null,"增加成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ShanchuCaigou(String caigoudanbianhao,String shicaibianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (caigoudanbianhao == null || "".equals(caigoudanbianhao))
			{
				throw new BusinessException("采购单编号不能为空");
			}
			if (shicaibianhao == null || "".equals(shicaibianhao))
			{
				throw new BusinessException("食材编号不能为空");
			}
            conn = DBUtil.getConnection();
            String sql="select shuliang from shangpincaigou where caigoudanbianhao = ? and shicaibianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			pst.setInt(2, Integer.valueOf(shicaibianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("采购不存在");
			sql="delete from shangpincaigou where caigoudanbianhao = ? and shicaibianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			pst.setInt(2, Integer.valueOf(shicaibianhao));
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
	
	public BeanGuanliyuan BianjiCaigou(String caigoudanbianhao,String shicaibianhao,String shuliang,String zhuangtai)throws BaseException
	{
		Connection conn=null;
		try {
		if (caigoudanbianhao == null || "".equals(caigoudanbianhao))
		{
			throw new BusinessException("采购编号单不能为空");
		}
		if (shicaibianhao == null || "".equals(shicaibianhao))
		{
			throw new BusinessException("食材编号不能为空");
		}
		if (shuliang == null || "".equals(shuliang))
		{
			throw new BusinessException("数量不能为空");
		}
		if (zhuangtai == null || "".equals(zhuangtai))
		{
			throw new BusinessException("状态不能为空");
		}
		if (!(zhuangtai.equals("xiadan") || zhuangtai.equals("zaitu") || zhuangtai.equals("ruku")))
		{
			throw new BusinessException("状态只能为“xiadan”或“zaitu”或“ruku”");
		}
		conn = DBUtil.getConnection();
        String sql="select shicaibianhao from shangpincaigou where caigoudanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caigoudanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("采购单编号不存在");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		rs = pst.executeQuery();
		if (!rs.next()) throw new BusinessException("食材编号不存在");
		sql = "update shangpincaigou set shicaibianhao = ?,shuliang = ?,zhuangtai = ? where caigoudanbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(4, Integer.valueOf(caigoudanbianhao));
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		pst.setDouble(2, Double.valueOf(shuliang));
		pst.setString(3, zhuangtai);
		pst.execute();
		JOptionPane.showMessageDialog(null,"编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanGuanliyuan ChaxunCaigou(String caigoudanbianhao,String shicaibianhao)throws BaseException
	{
		Connection conn=null;
		try {
			int flag1 = 0,flag2 = 0;
			if (caigoudanbianhao == null || "".equals(caigoudanbianhao))
			{
				flag1 = 0;
			}
			else
			{
				flag1 = 1;
			}
			if (shicaibianhao == null || "".equals(shicaibianhao))
			{
				flag2 = 0;
			}
			else
			{
				flag2 = 1;
			}
			if (flag1 == 0 && flag2 == 0)
			{
				throw new BusinessException("查询采购单编号和食材编号不能同时为空");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where caigoudanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("查询采购不存在");
			JOptionPane.showMessageDialog(null,"采购单编号   "+"食材编号   "+"数量   "+"状态\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4),"查询结果",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "采购单编号   "+"食材编号   "+"数量   "+"状态\n";
				String sql="select shuliang from shangpincaigou where shicaibianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shicaibianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询采购不存在");
				sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where shicaibianhao = ? order by caigoudanbianhao";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shicaibianhao));
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "查询结果",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where shicaibianhao = ? and caigoudanbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(caigoudanbianhao));
				pst.setInt(1, Integer.valueOf(shicaibianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("查询采购不存在");
				JOptionPane.showMessageDialog(null,"采购单编号   "+"食材编号   "+"数量   "+"状态\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4), "查询结果",JOptionPane.PLAIN_MESSAGE);
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
}
