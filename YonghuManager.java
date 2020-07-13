package control;

import java.math.BigDecimal;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;
import javax.swing.JOptionPane;

import org.hibernate.query.criteria.internal.expression.function.SubstringFunction;
import org.hibernate.sql.Insert;
import org.hibernate.sql.Select;

import itf.IYonghuManager;
import model.BeanGouwuche;
import model.BeanGuanliyuan;
import model.BeanShangpin;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class YonghuManager implements IYonghuManager {
	public BeanYonghu reg(String yonghubianhao, String xingming, String xingbie,String mima,String querenmima,String shoujihaoma,String youxiang,String suozaichengshi,String shifouhuiyuan,String huiyuanjiezhishijian) throws BaseException {
		// TODO Auto-generated method stub
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
		Timestamp time;
		if(!(huiyuanjiezhishijian == null || huiyuanjiezhishijian.equals("")))
		{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			time = new Timestamp(format.parse(huiyuanjiezhishijian).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("会员截止时间格式错误");
		}
		}
		else
		{
			time = new Timestamp(System.currentTimeMillis());
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("用户账号已经存在");
			rs.close();
			pst.close();
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
			BeanYonghu u=new BeanYonghu();
			u.setYonghubianhao(Integer.valueOf(yonghubianhao));
			u.setXingming(xingming);
			u.setXingbie(xingbie);
			u.setMima(mima);
			u.setShoujihaoma(shoujihaoma);
			u.setYouxiang(youxiang);
			u.setSuozaichengshi(suozaichengshi);
			u.setZhuceshijian(new Timestamp(System.currentTimeMillis()));
			u.setShifouhuiyuan(shifouhuiyuan1);
			u.setHuiyuanjiezhiriqi(time);
			JOptionPane.showMessageDialog(null,"注册成功", "注册结果",JOptionPane.PLAIN_MESSAGE);
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
	
	public BeanYonghu login(String yonghubianhao, String mima) throws BaseException {
		// TODO Auto-generated method stub
		if (yonghubianhao == null || "".equals(yonghubianhao))
		{
			throw new BusinessException("用户编号不能为空");
		}
		if (mima == null || "".equals(mima))
		{
			throw new BusinessException("密码不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("员工账号不存在");
			BeanYonghu u=new BeanYonghu();
			u.setYonghubianhao(rs.getInt(1));
			u.setXingming(rs.getString(2));
			u.setXingbie(rs.getString(3));
			u.setMima(rs.getString(4));
			if(!mima.equals(u.getMima())) throw new BusinessException("密码错误");
			u.setShoujihaoma(rs.getString(5));
			u.setYouxiang(rs.getString(6));
			u.setSuozaichengshi(rs.getString(7));
			u.setZhuceshijian(rs.getTimestamp(8));
			u.setShifouhuiyuan(rs.getInt(9));
			u.setHuiyuanjiezhiriqi(rs.getTimestamp(10));
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
	
	public List<BeanYonghu> loadAll() throws BaseException{
		List<BeanYonghu> result=new ArrayList<BeanYonghu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while (rs.next()) {
			BeanYonghu u = new BeanYonghu();
			u.setYonghubianhao(rs.getInt(1));
			u.setXingming(rs.getString(2));
			u.setXingbie(rs.getString(3));
			u.setMima(rs.getString(4));
			u.setShoujihaoma(rs.getString(5));
			u.setYouxiang(rs.getString(6));
			u.setSuozaichengshi(rs.getString(7));
			u.setZhuceshijian(rs.getTimestamp(8));
			u.setShifouhuiyuan(rs.getInt(9));
			u.setHuiyuanjiezhiriqi(rs.getTimestamp(10));
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
	
	public BeanYonghu changePwd(BeanYonghu user, String olddenglumima, String newdenglumima,
			String newdenglumima2) throws BaseException
	{
		if("".equals(olddenglumima) || olddenglumima==null) throw new BusinessException("原始密码不能为空");
		if("".equals(newdenglumima) || newdenglumima==null) throw new BusinessException("新密码不能为空");
		if (!newdenglumima.equals(newdenglumima2)) throw new BusinessException("新密码两次输入不同");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!BeanYonghu.currentLoginYonghu.getMima().equals(olddenglumima)) throw new BusinessException("原始密码错误");
			rs.close();
			pst.close();
			sql="update yonghu set mima = ? where yonghubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newdenglumima);
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"密码修改成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Bianjigerenxinxi(String xingming, String xingbie,String shoujihao,String youxiang,String suozaichengshi) throws BaseException
	{
		if (xingming == null || "".equals(xingming))
		{
			throw new BusinessException("用户姓名不能为空");
		}
		if (xingbie == null || "".equals(xingbie))
		{
			throw new BusinessException("用户性别不能为空");
		}
		if (!(xingbie.equals("nan") || xingbie.equals("nv")))
		{
			throw new BusinessException("性别不能为除“nan”或“nv”以外的字符");
		}
		if (shoujihao == null || "".equals(shoujihao))
		{
			throw new BusinessException("手机号不能为空");
		}
		if (youxiang == null || "".equals(youxiang))
		{
			throw new BusinessException("邮箱不能为空");
		}
		if (suozaichengshi == null || "".equals(suozaichengshi))
		{
			throw new BusinessException("所在城市不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update yonghu set xingming = ?,xingbie = ?,shoujihaoma = ?,youxiang = ?,suozaichengshi = ? where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,xingming);
			pst.setString(2, xingbie);
			pst.setString(3, shoujihao);
			pst.setString(4, youxiang);
			pst.setString(5, youxiang);
			pst.setInt(6, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"信息编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Bianjipeisongdizhi(String sheng, String shi,String qu,String dizhi,String lianxiren,String dianhua) throws BaseException
	{
		if (sheng == null || "".equals(sheng))
		{
			throw new BusinessException("省不能为空");
		}
		if (shi == null || "".equals(shi))
		{
			throw new BusinessException("市不能为空");
		}
		if (qu == null || "".equals(qu))
		{
			throw new BusinessException("区不能为空");
		}
		if (dizhi == null || "".equals(dizhi))
		{
			throw new BusinessException("地址不能为空");
		}
		if (lianxiren == null || "".equals(lianxiren))
		{
			throw new BusinessException("联系人不能为空");
		}
		if (dianhua == null || "".equals(dianhua))
		{
			throw new BusinessException("电话不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select dizhibianhao from peisongdizhi where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
			{
				int dizhibianhao = 1;
				String sql1 = "select max(dizhibianhao) from peisongdizhi";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				java.sql.ResultSet rs1 = pst1.executeQuery();
				if (rs1.next())
				{
					dizhibianhao = rs1.getInt(1) + 1;
				}
				sql1 = "insert into peisongdizhi(dizhibianhao,yonghubianhao,sheng,shi,qu,dizhi,lianxiren,dianhua) values(?,?,?,?,?,?,?,?)";
				pst1 = conn.prepareStatement(sql1);
				pst1.setInt(1, dizhibianhao);
				pst1.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
				pst1.setString(3, sheng);
				pst1.setString(4, shi);
				pst1.setString(5, qu);
				pst1.setString(6, dizhi);
				pst1.setString(7, lianxiren);
				pst1.setString(8, dianhua);
				pst1.execute();
			}
			else
			{
				int dizhibianhao = 1;
				dizhibianhao = rs.getInt(1);
				sql = "update peisongdizhi set sheng = ?,shi = ?,qu = ?,dizhi = ?,lianxiren = ?,dianhua = ? where dizhibianhao = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, sheng);
				pst.setString(2, shi);
				pst.setString(3, qu);
				pst.setString(4, dizhi);
				pst.setString(5, lianxiren);
				pst.setString(6, dianhua);
				pst.setInt(7, dizhibianhao);
				pst.execute();
			}
			JOptionPane.showMessageDialog(null,"信息编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanYonghu.currentLoginYonghu;
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

	@Override
	public BeanYonghu Jiarugouwuche(BeanShangpin shangpin, String shuliang) throws BaseException 
	{
		if("".equals(shuliang) || shuliang==null) throw new BusinessException("数量不能为空");
		if (Double.valueOf(shuliang) > shangpin.getShuliang() || Double.valueOf(shuliang) == 0)
		{
			throw new BusinessException("输入数量不能大于现在数量且不能为0");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int gouwuchebianhao = 1;
			int yonghuorder = 1;
			String sql="select gouwuchebianhao from gouwuche";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next())
			{
				gouwuchebianhao = 1;
			}
			else
			{
			    sql="select max(gouwuchebianhao) from gouwuche";
			    pst=conn.prepareStatement(sql);
			    rs=pst.executeQuery();
			    rs.next();
			    gouwuchebianhao = rs.getInt(1) + 1;
			}
			sql = "select yonghuorder from gouwuche where yonghubianhao = ?";
			pst =conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			rs = pst.executeQuery();
			if (!rs.next())
			{
				yonghuorder = 1;
			}
			else
			{
				sql = "select max(yonghuorder) from gouwuche where yonghubianhao = ?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
				rs = pst.executeQuery();
				rs.next();
				yonghuorder = rs.getInt(1) + 1;
			}
			double zuizhongjiage;
			double jiage;
			double huiyuanjia;
			jiage = shangpin.getJiage();
			huiyuanjia = shangpin.getHuiyuanjia();
			if (BeanYonghu.currentLoginYonghu.getShifouhuiyuan() == 1)
			{
				zuizhongjiage = huiyuanjia;
			}
			else
			{
				zuizhongjiage = jiage;
			}
			sql = "select shuliang from gouwuche where yonghubianhao = ? and shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(2, shangpin.getShangpinbianhao());
			rs = pst.executeQuery();
			if (!rs.next())
			{
			sql="insert into gouwuche(gouwuchebianhao,yonghubianhao,shangpinbianhao,shangpinmingcheng,yonghuorder,shuliang,jiage) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, gouwuchebianhao);
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(3, shangpin.getShangpinbianhao());
			pst.setString(4, shangpin.getShangpinmingcheng());
			pst.setInt(5, yonghuorder);
			pst.setDouble(6, Double.valueOf(shuliang));
			pst.setDouble(7, zuizhongjiage);
			pst.execute();
			pst.close();
			}
			else
			{
				sql = "update gouwuche set shuliang = shuliang + ? where yonghubianhao = ? and shangpinbianhao = ?";
				pst = conn.prepareStatement(sql);
				pst.setDouble(1, Double.valueOf(shuliang));
				pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
				pst.setInt(3, shangpin.getShangpinbianhao());
				pst.execute();
			}
			JOptionPane.showMessageDialog(null,"加入购物车成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Quxiaogoumai(BeanGouwuche gouwuche) throws BaseException
	{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from gouwuche where yonghubianhao = ? and yonghuorder = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(2, gouwuche.getYonghuorder());
			pst.execute();
			sql = "update gouwuche set yonghuorder = -yonghuorder where yonghubianhao = ? and yonghuorder > ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(2, gouwuche.getYonghuorder());
			pst.execute();
			sql = "update gouwuche set yonghuorder = -yonghuorder  - 1 where yonghuorder < 0";
			pst = conn.prepareStatement(sql);
			pst.execute();
			JOptionPane.showMessageDialog(null,"取消购买成功", "结果",JOptionPane.PLAIN_MESSAGE);
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Fukuangoumai(List<BeanGouwuche> allGouwuche,String songdashijian) throws BaseException
	{
		if (songdashijian == null || "".equals(songdashijian))
		{
			throw new BusinessException("送达时间不能为空");
		}
		int xianshicuxiaoflag = 0;
		int youhuiquanflag = 0;
		int manzheflag = 0;
		double yuanjine = 0;
		double jine = 0;
		int youhuiquanbianhao;
		double youhuijine;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int peisongdizhibianhao;
			String sql = "select dizhibianhao from peisongdizhi where yonghubianhao = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
			{
				JOptionPane.showMessageDialog(null, "地址不存在，请先编辑地址", "错误",JOptionPane.ERROR_MESSAGE);
				throw new BusinessException("地址不存在，请先编辑地址");
			}
			else
			{
				peisongdizhibianhao = rs.getInt(1);
			}} catch (SQLException e) {
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Timestamp time = new Timestamp(format.parse(songdashijian).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("送达时间格式错误");
		}
		for (int i = 0;i < allGouwuche.size();i++)
		{
			try {
				conn=DBUtil.getConnection();
				String sql="select shuliang from shangpin where shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,allGouwuche.get(i).getShangpinbianhao());
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException ("商品不存在");
				else
				{
					double shangpinshuliang = rs.getDouble(1);
					if (shangpinshuliang < allGouwuche.get(i).getShuliang())
					{
						throw new BusinessException("商品数量不足，无法够买");
					}
				}
				rs.close();
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
		for (int i = 0;i < allGouwuche.size();i++)
		{
			double meixiangjine = 0;
			double yuanmeixiangjine = 0;
			try {
				conn=DBUtil.getConnection();
				String sql="select a.manzhebianhao,zhekou,shiyongshangpinshuliang from manzheshangpinguanlian a,manzhe b where a.manzhebianhao = b.manzhebianhao and a.shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,allGouwuche.get(i).getShangpinbianhao());
				java.sql.ResultSet rs = pst.executeQuery();
				if (rs.next())
				{//有满折编号
				     int manzhebianhao = rs.getInt(1);
				     double zhekou = rs.getDouble(2);
				     double shiyongshangpinshuliang = rs.getDouble(3);
				     if (shiyongshangpinshuliang > allGouwuche.get(i).getShuliang())
				     {//有满折编号但数量不够
					      sql = "select cuxiaojiage,cuxiaoshuliang from xianshicuxiao where shangpinbianhao = ?";
					      pst = conn.prepareStatement(sql);
					      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
					      rs = pst.executeQuery();
					      if (rs.next())
					      {//有满折编号但数量不够，是促销商品
					            double cuxiaojiage = rs.getDouble(1);
					            double cuxiaoshuliang = rs.getDouble(2);
					            if (cuxiaoshuliang >= allGouwuche.get(i).getShuliang())
					            {//有满折编号但数量不够，是促销商品，促销数量大于等于购买数量
					            	xianshicuxiaoflag = 1;
					            	meixiangjine = cuxiaojiage * allGouwuche.get(i).getShuliang();
					            	yuanmeixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
					            	int dingdanbianhao = 1;
								      sql = "select yonghubianhao from shangpindingdan";
								      pst = conn.prepareStatement(sql);
								      rs = pst.executeQuery();
								      if (rs.next())
								      {
									       sql = "select max(dingdanbianhao) from shangpindingdan";
									       pst = conn.prepareStatement(sql);
									       rs = pst.executeQuery();
									       rs.next();
									       dingdanbianhao = rs.getInt(1) + 1;
								      }
								      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, dingdanbianhao);
								      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								      pst.setDouble(3, allGouwuche.get(i).getShuliang());
								      pst.setDouble(4, cuxiaojiage);
								      pst.setDouble(5, 1);
								      pst.execute();
								      sql = "update xianshicuxiao set cuxiaoshuliang = cuxiaoshuliang - ? where shangpinbianhao = ?";
								      pst = conn.prepareStatement(sql);
								      pst.setDouble(1,allGouwuche.get(i).getShuliang());
								      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								      pst.execute();
								      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
									  pst = conn.prepareStatement(sql);
									  pst.setDouble(1, allGouwuche.get(i).getShuliang());
									  pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
									  pst.execute();
					            }
					            else
					            {//有满折编号但数量不够，是促销商品，促销数量小于购买数量
					            	meixiangjine = cuxiaoshuliang * cuxiaojiage + (allGouwuche.get(i).getShuliang() - cuxiaoshuliang) * allGouwuche.get(i).getJiage();
					            	yuanmeixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
					            	int dingdanbianhao = 1;
								      sql = "select yonghubianhao from shangpindingdan";
								      pst = conn.prepareStatement(sql);
								      rs = pst.executeQuery();
								      if (rs.next())
								      {
									       sql = "select max(dingdanbianhao) from shangpindingdan";
									       pst = conn.prepareStatement(sql);
									       rs = pst.executeQuery();
									       rs.next();
									       dingdanbianhao = rs.getInt(1) + 1;
								      }
								      if (cuxiaoshuliang != 0)
								      {
								      xianshicuxiaoflag = 1;
								      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, dingdanbianhao);
								      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								      pst.setDouble(3, cuxiaoshuliang);
								      pst.setDouble(4, cuxiaojiage);
								      pst.setDouble(5, 1);
								      pst.execute();
								      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, dingdanbianhao);
								      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								      pst.setDouble(3, allGouwuche.get(i).getShuliang() - cuxiaoshuliang);
								      pst.setDouble(4, allGouwuche.get(i).getJiage());
								      pst.setDouble(5, 1);
								      pst.execute();
								      sql = "update xianshicuxiao set cuxiaoshuliang = 0 where shangpinbianhao = ?";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
								      pst.execute();
								      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
									  pst = conn.prepareStatement(sql);
									  pst.setDouble(1, allGouwuche.get(i).getShuliang());
									  pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
									  pst.execute();
								      }
								      else
								      {
								    	  sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
									      pst = conn.prepareStatement(sql);
									      pst.setInt(1, dingdanbianhao);
									      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
									      pst.setDouble(3, allGouwuche.get(i).getShuliang() - cuxiaoshuliang);
									      pst.setDouble(4, allGouwuche.get(i).getJiage());
									      pst.setDouble(5, 1);
									      pst.execute();
									      sql = "update xianshicuxiao set cuxiaoshuliang = 0 where shangpinbianhao = ?";
									      pst = conn.prepareStatement(sql);
									      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
									      pst.execute();
									      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
										  pst = conn.prepareStatement(sql);
										  pst.setDouble(1, allGouwuche.get(i).getShuliang());
										  pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
										  pst.execute();
								      }
					            }
					            
					      }
					      else
					      {//有满折编号但数量不够，不是促销商品
					           meixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
					           yuanmeixiangjine = meixiangjine;
					           int dingdanbianhao = 1;
							      sql = "select yonghubianhao from shangpindingdan";
							      pst = conn.prepareStatement(sql);
							      rs = pst.executeQuery();
							      if (rs.next())
							      {
								       sql = "select max(dingdanbianhao) from shangpindingdan";
								       pst = conn.prepareStatement(sql);
								       rs = pst.executeQuery();
								       rs.next();
								       dingdanbianhao = rs.getInt(1) + 1;
							      }
							      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
							      pst = conn.prepareStatement(sql);
							      pst.setInt(1, dingdanbianhao);
							      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							      pst.setDouble(3, allGouwuche.get(i).getShuliang());
							      pst.setDouble(4, allGouwuche.get(i).getJiage());
							      pst.setDouble(5, 1);
							      pst.execute();
							      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
								  pst = conn.prepareStatement(sql);
								  pst.setDouble(1, allGouwuche.get(i).getShuliang());
								  pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								  pst.execute();
					      }
				}
				else 
				{//有满折编号数量够
					 manzheflag = 1;
				     meixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang() * zhekou;
				     yuanmeixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
				     int dingdanbianhao = 1;
				     sql = "select yonghubianhao from shangpindingdan";
				     pst = conn.prepareStatement(sql);
				     rs = pst.executeQuery();
				     if (rs.next())
				     {
					      sql = "select max(dingdanbianhao) from shangpindingdan";
					      pst = conn.prepareStatement(sql);
					      rs = pst.executeQuery();
					      rs.next();
					      dingdanbianhao = rs.getInt(1) + 1;
				     }
				     sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou,manzhebianhao) values(?,?,?,?,?,?)";
				     pst = conn.prepareStatement(sql);
				     pst.setInt(1, dingdanbianhao);
				     pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
				     pst.setDouble(3, allGouwuche.get(i).getShuliang());
				     pst.setDouble(4, allGouwuche.get(i).getJiage());
				     pst.setDouble(5, zhekou);
				     pst.setInt(6, manzhebianhao);
				     pst.execute();
				     sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
						pst = conn.prepareStatement(sql);
						pst.setDouble(1, allGouwuche.get(i).getShuliang());
						pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
						pst.execute();
				}
			    }
				else
				{//无满折编号
					sql = "select cuxiaojiage,cuxiaoshuliang from xianshicuxiao where shangpinbianhao = ?";
				      pst = conn.prepareStatement(sql);
				      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
				      rs = pst.executeQuery();
				      if (rs.next())
				      {//无满折编号，但有限时促销
				            double cuxiaojiage = rs.getDouble(1);
				            double cuxiaoshuliang = rs.getDouble(2);
				            if (cuxiaoshuliang >= allGouwuche.get(i).getShuliang())
				            {//无满折编号，有限时促销，促销数量大于等于购买数量
				            	xianshicuxiaoflag = 1;
				            	meixiangjine = cuxiaojiage * allGouwuche.get(i).getShuliang();
				            	yuanmeixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
				            	int dingdanbianhao = 1;
							      sql = "select yonghubianhao from shangpindingdan";
							      pst = conn.prepareStatement(sql);
							      rs = pst.executeQuery();
							      if (rs.next())
							      {
								       sql = "select max(dingdanbianhao) from shangpindingdan";
								       pst = conn.prepareStatement(sql);
								       rs = pst.executeQuery();
								       rs.next();
								       dingdanbianhao = rs.getInt(1) + 1;
							      }
							      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
							      pst = conn.prepareStatement(sql);
							      pst.setInt(1, dingdanbianhao);
							      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							      pst.setDouble(3, allGouwuche.get(i).getShuliang());
							      pst.setDouble(4, cuxiaojiage);
							      pst.setDouble(5, 1);
							      pst.execute();
							      sql = "update xianshicuxiao set cuxiaoshuliang = cuxiaoshuliang - ? where shangpinbianhao = ?";
							      pst = conn.prepareStatement(sql);
							      pst.setDouble(1,allGouwuche.get(i).getShuliang());
							      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							      pst.execute();
							      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
								    pst = conn.prepareStatement(sql);
									pst.setDouble(1, allGouwuche.get(i).getShuliang());
									pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
									pst.execute();
				            }
				            else
				            {//无满折编号，有限时促销，促销数量小于购买数量
				            	meixiangjine = cuxiaoshuliang * cuxiaojiage + (allGouwuche.get(i).getShuliang() - cuxiaoshuliang) * allGouwuche.get(i).getJiage();
				            	yuanmeixiangjine = allGouwuche.get(i).getJiage() * allGouwuche.get(i).getShuliang();
				            	int dingdanbianhao = 1;
							      sql = "select yonghubianhao from shangpindingdan";
							      pst = conn.prepareStatement(sql);
							      rs = pst.executeQuery();
							      if (rs.next())
							      {
								       sql = "select max(dingdanbianhao) from shangpindingdan";
								       pst = conn.prepareStatement(sql);
								       rs = pst.executeQuery();
								       rs.next();
								       dingdanbianhao = rs.getInt(1) + 1;
							      }
							      if (cuxiaoshuliang != 0)
							      {
							      xianshicuxiaoflag = 1;
							      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
							      pst = conn.prepareStatement(sql);
							      pst.setInt(1, dingdanbianhao);
							      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							      pst.setDouble(3, cuxiaoshuliang);
							      pst.setDouble(4, cuxiaojiage);
							      pst.setDouble(5, 1);
							      pst.execute();
							      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
							      pst = conn.prepareStatement(sql);
							      pst.setInt(1, dingdanbianhao);
							      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							      pst.setDouble(3, allGouwuche.get(i).getShuliang() - cuxiaoshuliang);
							      pst.setDouble(4, allGouwuche.get(i).getJiage());
							      pst.setDouble(5, 1);
							      pst.execute();
							      sql = "update xianshicuxiao set cuxiaoshuliang = 0 where shangpinbianhao = ?";
							      pst = conn.prepareStatement(sql);
							      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
							      pst.execute();
							      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
									pst = conn.prepareStatement(sql);
									pst.setDouble(1, allGouwuche.get(i).getShuliang());
									pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
									pst.execute();
							      }
							      else
							      {
							    	  sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, dingdanbianhao);
								      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
								      pst.setDouble(3, allGouwuche.get(i).getShuliang() - cuxiaoshuliang);
								      pst.setDouble(4, allGouwuche.get(i).getJiage());
								      pst.setDouble(5, 1);
								      pst.execute();
								      sql = "update xianshicuxiao set cuxiaoshuliang = 0 where shangpinbianhao = ?";
								      pst = conn.prepareStatement(sql);
								      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
								      pst.execute();
								      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
										pst = conn.prepareStatement(sql);
										pst.setDouble(1, allGouwuche.get(i).getShuliang());
										pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
										pst.execute();
							      }
				            }
				      }
				      else
				      {//无满折编号，无限时促销
				    	  meixiangjine = allGouwuche.get(i).getShuliang() * allGouwuche.get(i).getJiage();
				    	  yuanmeixiangjine = meixiangjine;
				    	  int dingdanbianhao = 1;
					      sql = "select yonghubianhao from shangpindingdan";
					      pst = conn.prepareStatement(sql);
					      rs = pst.executeQuery();
					      if (rs.next())
					      {
						       sql = "select max(dingdanbianhao) from shangpindingdan";
						       pst = conn.prepareStatement(sql);
						       rs = pst.executeQuery();
						       rs.next();
						       dingdanbianhao = rs.getInt(1) + 1;
					      }
					      sql = "insert into dingdanxiangqing(dingdanbianhao,shangpinbianhao,shuliang,jiage,zhekou) values(?,?,?,?,?)";
					      pst = conn.prepareStatement(sql);
					      pst.setInt(1, dingdanbianhao);
					      pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
					      pst.setDouble(3, allGouwuche.get(i).getShuliang());
					      pst.setDouble(4, allGouwuche.get(i).getJiage());
					      pst.setDouble(5, 1);
					      pst.execute();
					      sql = "update shangpin set shuliang = shuliang - ? where shangpinbianhao = ?";
							pst = conn.prepareStatement(sql);
							pst.setDouble(1, allGouwuche.get(i).getShuliang());
							pst.setInt(2, allGouwuche.get(i).getShangpinbianhao());
							pst.execute();
				      }
				}
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
			jine = jine + meixiangjine;
			yuanjine = yuanjine + yuanmeixiangjine;
		}
		try {
			conn=DBUtil.getConnection();
			int peisongdizhibianhao;
			String sql = "select dizhibianhao from peisongdizhi where yonghubianhao = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
			{
				throw new BusinessException("地址不存在，请先编辑地址");
			}
			else
			{
				peisongdizhibianhao = rs.getInt(1);
			}
			int flag = 1;
			int j = 1;
			double yuanjianmianjine = 0;
			double yuanshiyongjine = 0;
			double jianmianjine = 0;
			double shiyongjine = 0;
			while(flag == 1)
			{
			sql = "select shiyongjine,jianmianjine from youhuiquan where youhuiquanbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, j);
			rs = pst.executeQuery();
			if (rs.next())
			{
				shiyongjine = rs.getDouble(1);
				jianmianjine = rs.getDouble(2);
				if (shiyongjine <= jine)
				{
					j++;
					yuanjianmianjine = jianmianjine;
					yuanshiyongjine = shiyongjine;
				}
				else
				{
					flag = 0;
					break;
				}
			}
			else
			{
				flag = 0;
				break;
			}
			}
			j = j - 1;
			youhuiquanbianhao = j;
			youhuijine = yuanjianmianjine;
			jine = jine - yuanjianmianjine;
			int dingdanbianhao = 1;
			sql = "select yonghubianhao from shangpindingdan";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next())
			{
				sql = "select max(dingdanbianhao) from shangpindingdan";
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				rs.next();
				dingdanbianhao = rs.getInt(1) + 1;
			}
			if (youhuiquanbianhao != 0)
			{
		    youhuiquanflag = 1;
		    BigDecimal bg = new BigDecimal(jine);
		    double jine1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			sql = "insert into shangpindingdan(dingdanbianhao,yonghubianhao,yuanshijine,jiesuanjine,shiyongyouhuiquanbianhao,yaoqiusongdashijian,peisongdizhibianhao,dingdanzhuangtai) values(?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, dingdanbianhao);
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setDouble(3, yuanjine);
			pst.setDouble(4, jine1);
			pst.setInt(5,j);
		    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Timestamp time = new Timestamp(format.parse(songdashijian).getTime());
				pst.setTimestamp(6, time);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("送达时间格式错误");
			}
			pst.setInt(7, peisongdizhibianhao);
			String a = "xiadan";
			pst.setString(8, a);
			pst.execute();
			}
			else
			{
				youhuiquanflag = 0;
				BigDecimal bg = new BigDecimal(jine);
			    double jine1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				sql = "insert into shangpindingdan(dingdanbianhao,yonghubianhao,yuanshijine,jiesuanjine,yaoqiusongdashijian,peisongdizhibianhao,dingdanzhuangtai) values(?,?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, dingdanbianhao);
				pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
				pst.setDouble(3, yuanjine);
				pst.setDouble(4, jine1);
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Timestamp time = new Timestamp(format.parse(songdashijian).getTime());
					pst.setTimestamp(5, time);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					throw new BusinessException("送达时间格式错误");
				}
				pst.setInt(6, peisongdizhibianhao);
				String a = "xiadan";
				pst.setString(7, a);
				pst.execute();
			}
			sql="delete from gouwuche where yonghubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();		
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
		BigDecimal bg = new BigDecimal(jine);
	    double jine1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if (xianshicuxiaoflag == 1 && youhuiquanflag == 1 && manzheflag == 1)
		{
		JOptionPane.showMessageDialog(null,"您的商品中存在满折优惠，已为您使用满折优惠\n"+"您的商品中存在限时促销优惠，已为您尽可能替换优惠价格\n"+"已使用最接近您的购买金额的优惠券编号为"+youhuiquanbianhao+"的优惠券，为您节省"+youhuijine+"元\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 1 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"您的商品中存在满折优惠，已为您使用满折优惠\n"+"已使用最接近您的购买金额的优惠券编号为"+youhuiquanbianhao+"的优惠券，为您节省"+youhuijine+"元\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 0 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"您的商品中存在满折优惠，已为您使用满折优惠\n"+"您的商品中存在限时促销优惠，已为您尽可能替换优惠价格\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 0 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"您的商品中存在满折优惠，已为您使用满折优惠\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 1 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"您的商品中存在限时促销优惠，已为您尽可能替换优惠价格\n"+"已使用最接近您的购买金额的优惠券编号为"+youhuiquanbianhao+"的优惠券，为您节省"+youhuijine+"元\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 1 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"已使用最接近您的购买金额的优惠券编号为"+youhuiquanbianhao+"的优惠券，为您节省"+youhuijine+"元\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 0 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"您的商品中存在限时促销优惠，已为您尽可能替换优惠价格\n"+"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 0 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"购买需要"+jine1+"元", "结果",JOptionPane.PLAIN_MESSAGE);
		}
		return BeanYonghu.currentLoginYonghu;
	}
	
	public BeanYonghu Zengjiapingjia(String shangpinbianhao,String pingjianeirong,String xingji,String zhaopian) throws BaseException
	{
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("商品编号不能为空");
		}
		if (pingjianeirong == null || "".equals(pingjianeirong))
		{
			throw new BusinessException("评价内容不能为空");
		}
		if (xingji == null || "".equals(xingji))
		{
			throw new BusinessException("星级不能为空");
		}
		if (!(Integer.valueOf(xingji) >=1 && Integer.valueOf(xingji) <=5))
		{
			throw new BusinessException("星级不能为除0-5之外的数字");
		}
		if (zhaopian == null || "".equals(zhaopian))
		{
			throw new BusinessException("照片不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException ("评价已存在");
			sql = "select shuliang from dingdanxiangqing a,shangpindingdan b where b.dingdanbianhao = a.dingdanbianhao and a.shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (!rs.next())
			{
				throw new BusinessException("您未购买此商品");
			}
			sql = "insert into shangpinpingjia(shangpinbianhao,pingjiayonghubianhao,pingjianeirong,pingjiariqi,xingji,zhaopian) values(?,?,?,NOW(),?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setString(3, pingjianeirong);
			pst.setInt(4, Integer.valueOf(xingji));
			pst.setString(5, zhaopian);
			pst.execute();
			JOptionPane.showMessageDialog(null,"评价成功", "结果",JOptionPane.PLAIN_MESSAGE);
			rs.close();
			pst.close();
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Bianjipingjia(String shangpinbianhao,String pingjianeirong,String xingji,String zhaopian) throws BaseException
	{
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("商品编号不能为空");
		}
		if (pingjianeirong == null || "".equals(pingjianeirong))
		{
			throw new BusinessException("评价内容不能为空");
		}
		if (xingji == null || "".equals(xingji))
		{
			throw new BusinessException("星级不能为空");
		}
		if (!(Integer.valueOf(xingji) >=1 && Integer.valueOf(xingji) <=5))
		{
			throw new BusinessException("星级不能为除0-5之外的数字");
		}
		if (zhaopian == null || "".equals(zhaopian))
		{
			throw new BusinessException("照片不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("评价不存在");
			sql = "update shangpinpingjia set pingjianeirong = ?,pingjiariqi = NOW(),xingji = ?,zhaopian = ? where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(4, Integer.valueOf(shangpinbianhao));
			pst.setInt(5, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setString(1, pingjianeirong);
			pst.setInt(2, Integer.valueOf(xingji));
			pst.setString(3, zhaopian);
			pst.execute();
			JOptionPane.showMessageDialog(null,"更新成功", "结果",JOptionPane.PLAIN_MESSAGE);
			rs.close();
			pst.close();
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Shanchupingjia(String shangpinbianhao) throws BaseException
	{
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("商品编号不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("评价不存在");
			sql = "delete from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setInt(2,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			JOptionPane.showMessageDialog(null,"删除成功", "结果",JOptionPane.PLAIN_MESSAGE);
			rs.close();
			pst.close();
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Xiugaishuliang(BeanGouwuche Gouwuche,String shuliang) throws BaseException
	{
		if (shuliang == null || "".equals(shuliang))
		{
			throw new BusinessException("商品数量不能为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shuliang from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(Gouwuche.getShangpinbianhao()));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("商品不存在");
			else
			{
				double shangpinshuliang = rs.getDouble(1);
				if (shangpinshuliang < Double.valueOf(shuliang))
				{
					throw new BusinessException("商品数量不足，无法够买");
				}
			}
			sql = "update gouwuche set shuliang = ? where yonghubianhao = ? and gouwuchebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, Double.valueOf(shuliang));
			pst.setInt(2,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(3, Gouwuche.getGouwuchebianhao());
			pst.execute();
			JOptionPane.showMessageDialog(null,"编辑成功", "结果",JOptionPane.PLAIN_MESSAGE);
			rs.close();
			pst.close();
			return BeanYonghu.currentLoginYonghu;
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
	
	public BeanYonghu Chengweihuiyuan(BeanYonghu user) throws BaseException
	{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update yonghu set shifouhuiyuan = 1,huiyuanjiezhishijian = ? where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			Timestamp time = new Timestamp(System.currentTimeMillis() + 60 * 60 * 24 * 1000);
			pst.setTimestamp(1,time);
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			List<BeanGouwuche> allGouwuche = Util.gouwucheManager.loadAll();
			for (int i = 0;i < allGouwuche.size();i++)
			{
				sql = "select huiyuanjia from shangpin where shangpinbianhao = ?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
				java.sql.ResultSet rs = pst.executeQuery();
				rs.next();
				double huiyuanjia = rs.getDouble(1);
				sql = "update gouwuche set jiage = ? where yonghubianhao = ? and shangpinbianhao = ?";
				pst = conn.prepareStatement(sql);
				pst.setDouble(1, huiyuanjia);
				pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
				pst.setInt(3, allGouwuche.get(i).getShangpinbianhao());
				pst.execute();
			}
			JOptionPane.showMessageDialog(null,"开启一日会员成功，购物车价格已全部改为会员价", "结果",JOptionPane.PLAIN_MESSAGE);
			pst.close();
			return BeanYonghu.currentLoginYonghu;
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
