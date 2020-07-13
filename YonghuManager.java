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
			throw new BusinessException("�û���Ų���Ϊ��");
		}
		if("".equals(xingming) || xingming == null){
			throw new BusinessException("��������Ϊ��");
		}
		if("".equals(xingbie) || xingbie == null){
			throw new BusinessException("�Ա���Ϊ��");
		}
		if(!(xingbie.equals("nan") || xingbie.equals("nv")))
		{
			throw new BusinessException("�Ա���Ϊ����nan������nv����������ַ�");
		}
		if ("".equals(mima) || mima == null)
		{
			throw new BusinessException("���벻��Ϊ��");
		}
		if (!querenmima.equals(mima))
		{
			throw new BusinessException("�����������벻һ��");
		}
		if ("".equals(shoujihaoma) || shoujihaoma == null)
		{
			throw new BusinessException("�ֻ����벻��Ϊ��");
		}
		if ("".equals(youxiang) || youxiang == null)
		{
			throw new BusinessException("���䲻��Ϊ��");
		}
		if ("".equals(suozaichengshi) || suozaichengshi == null)
		{
			throw new BusinessException("���ڳ��в���Ϊ��");
		}
		if (shifouhuiyuan == null || "".equals(shifouhuiyuan))
		{
			throw new BusinessException("�Ƿ��Ա����Ϊ��");
		}
		int shifouhuiyuan1 = Integer.valueOf(shifouhuiyuan);
		if (shifouhuiyuan1 != 0 && shifouhuiyuan1 != 1)
		{
			throw new BusinessException("����1��ʾΪ��Ա��0��ʾ�ǻ�Ա������������������");
		}
		if (shifouhuiyuan1 == 1 && huiyuanjiezhishijian == null)
		{
			throw new BusinessException("��Ա��ֹ����Ϊ��");
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
			throw new BusinessException("��Ա��ֹʱ���ʽ����");
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
			if(rs.next()) throw new BusinessException("�û��˺��Ѿ�����");
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
			JOptionPane.showMessageDialog(null,"ע��ɹ�", "ע����",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("�û���Ų���Ϊ��");
		}
		if (mima == null || "".equals(mima))
		{
			throw new BusinessException("���벻��Ϊ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("Ա���˺Ų�����");
			BeanYonghu u=new BeanYonghu();
			u.setYonghubianhao(rs.getInt(1));
			u.setXingming(rs.getString(2));
			u.setXingbie(rs.getString(3));
			u.setMima(rs.getString(4));
			if(!mima.equals(u.getMima())) throw new BusinessException("�������");
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
		if("".equals(olddenglumima) || olddenglumima==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if("".equals(newdenglumima) || newdenglumima==null) throw new BusinessException("�����벻��Ϊ��");
		if (!newdenglumima.equals(newdenglumima2)) throw new BusinessException("�������������벻ͬ");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų�����");
			if(!BeanYonghu.currentLoginYonghu.getMima().equals(olddenglumima)) throw new BusinessException("ԭʼ�������");
			rs.close();
			pst.close();
			sql="update yonghu set mima = ? where yonghubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newdenglumima);
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"�����޸ĳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("�û���������Ϊ��");
		}
		if (xingbie == null || "".equals(xingbie))
		{
			throw new BusinessException("�û��Ա���Ϊ��");
		}
		if (!(xingbie.equals("nan") || xingbie.equals("nv")))
		{
			throw new BusinessException("�Ա���Ϊ����nan����nv��������ַ�");
		}
		if (shoujihao == null || "".equals(shoujihao))
		{
			throw new BusinessException("�ֻ��Ų���Ϊ��");
		}
		if (youxiang == null || "".equals(youxiang))
		{
			throw new BusinessException("���䲻��Ϊ��");
		}
		if (suozaichengshi == null || "".equals(suozaichengshi))
		{
			throw new BusinessException("���ڳ��в���Ϊ��");
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
			JOptionPane.showMessageDialog(null,"��Ϣ�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("ʡ����Ϊ��");
		}
		if (shi == null || "".equals(shi))
		{
			throw new BusinessException("�в���Ϊ��");
		}
		if (qu == null || "".equals(qu))
		{
			throw new BusinessException("������Ϊ��");
		}
		if (dizhi == null || "".equals(dizhi))
		{
			throw new BusinessException("��ַ����Ϊ��");
		}
		if (lianxiren == null || "".equals(lianxiren))
		{
			throw new BusinessException("��ϵ�˲���Ϊ��");
		}
		if (dianhua == null || "".equals(dianhua))
		{
			throw new BusinessException("�绰����Ϊ��");
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
			JOptionPane.showMessageDialog(null,"��Ϣ�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
		if("".equals(shuliang) || shuliang==null) throw new BusinessException("��������Ϊ��");
		if (Double.valueOf(shuliang) > shangpin.getShuliang() || Double.valueOf(shuliang) == 0)
		{
			throw new BusinessException("�����������ܴ������������Ҳ���Ϊ0");
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
			JOptionPane.showMessageDialog(null,"���빺�ﳵ�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			JOptionPane.showMessageDialog(null,"ȡ������ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("�ʹ�ʱ�䲻��Ϊ��");
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
				JOptionPane.showMessageDialog(null, "��ַ�����ڣ����ȱ༭��ַ", "����",JOptionPane.ERROR_MESSAGE);
				throw new BusinessException("��ַ�����ڣ����ȱ༭��ַ");
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
			throw new BusinessException("�ʹ�ʱ���ʽ����");
		}
		for (int i = 0;i < allGouwuche.size();i++)
		{
			try {
				conn=DBUtil.getConnection();
				String sql="select shuliang from shangpin where shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,allGouwuche.get(i).getShangpinbianhao());
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException ("��Ʒ������");
				else
				{
					double shangpinshuliang = rs.getDouble(1);
					if (shangpinshuliang < allGouwuche.get(i).getShuliang())
					{
						throw new BusinessException("��Ʒ�������㣬�޷�����");
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
				{//�����۱��
				     int manzhebianhao = rs.getInt(1);
				     double zhekou = rs.getDouble(2);
				     double shiyongshangpinshuliang = rs.getDouble(3);
				     if (shiyongshangpinshuliang > allGouwuche.get(i).getShuliang())
				     {//�����۱�ŵ���������
					      sql = "select cuxiaojiage,cuxiaoshuliang from xianshicuxiao where shangpinbianhao = ?";
					      pst = conn.prepareStatement(sql);
					      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
					      rs = pst.executeQuery();
					      if (rs.next())
					      {//�����۱�ŵ������������Ǵ�����Ʒ
					            double cuxiaojiage = rs.getDouble(1);
					            double cuxiaoshuliang = rs.getDouble(2);
					            if (cuxiaoshuliang >= allGouwuche.get(i).getShuliang())
					            {//�����۱�ŵ������������Ǵ�����Ʒ�������������ڵ��ڹ�������
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
					            {//�����۱�ŵ������������Ǵ�����Ʒ����������С�ڹ�������
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
					      {//�����۱�ŵ��������������Ǵ�����Ʒ
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
				{//�����۱��������
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
				{//�����۱��
					sql = "select cuxiaojiage,cuxiaoshuliang from xianshicuxiao where shangpinbianhao = ?";
				      pst = conn.prepareStatement(sql);
				      pst.setInt(1, allGouwuche.get(i).getShangpinbianhao());
				      rs = pst.executeQuery();
				      if (rs.next())
				      {//�����۱�ţ�������ʱ����
				            double cuxiaojiage = rs.getDouble(1);
				            double cuxiaoshuliang = rs.getDouble(2);
				            if (cuxiaoshuliang >= allGouwuche.get(i).getShuliang())
				            {//�����۱�ţ�����ʱ�����������������ڵ��ڹ�������
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
				            {//�����۱�ţ�����ʱ��������������С�ڹ�������
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
				      {//�����۱�ţ�����ʱ����
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
				throw new BusinessException("��ַ�����ڣ����ȱ༭��ַ");
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
				throw new BusinessException("�ʹ�ʱ���ʽ����");
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
					throw new BusinessException("�ʹ�ʱ���ʽ����");
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
		JOptionPane.showMessageDialog(null,"������Ʒ�д��������Żݣ���Ϊ��ʹ�������Ż�\n"+"������Ʒ�д�����ʱ�����Żݣ���Ϊ���������滻�Żݼ۸�\n"+"��ʹ����ӽ����Ĺ�������Ż�ȯ���Ϊ"+youhuiquanbianhao+"���Ż�ȯ��Ϊ����ʡ"+youhuijine+"Ԫ\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 1 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"������Ʒ�д��������Żݣ���Ϊ��ʹ�������Ż�\n"+"��ʹ����ӽ����Ĺ�������Ż�ȯ���Ϊ"+youhuiquanbianhao+"���Ż�ȯ��Ϊ����ʡ"+youhuijine+"Ԫ\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 0 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"������Ʒ�д��������Żݣ���Ϊ��ʹ�������Ż�\n"+"������Ʒ�д�����ʱ�����Żݣ���Ϊ���������滻�Żݼ۸�\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 0 && manzheflag == 1)
		{
			JOptionPane.showMessageDialog(null,"������Ʒ�д��������Żݣ���Ϊ��ʹ�������Ż�\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 1 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"������Ʒ�д�����ʱ�����Żݣ���Ϊ���������滻�Żݼ۸�\n"+"��ʹ����ӽ����Ĺ�������Ż�ȯ���Ϊ"+youhuiquanbianhao+"���Ż�ȯ��Ϊ����ʡ"+youhuijine+"Ԫ\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 1 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"��ʹ����ӽ����Ĺ�������Ż�ȯ���Ϊ"+youhuiquanbianhao+"���Ż�ȯ��Ϊ����ʡ"+youhuijine+"Ԫ\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 1 && youhuiquanflag == 0 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"������Ʒ�д�����ʱ�����Żݣ���Ϊ���������滻�Żݼ۸�\n"+"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xianshicuxiaoflag == 0 && youhuiquanflag == 0 && manzheflag == 0)
		{
			JOptionPane.showMessageDialog(null,"������Ҫ"+jine1+"Ԫ", "���",JOptionPane.PLAIN_MESSAGE);
		}
		return BeanYonghu.currentLoginYonghu;
	}
	
	public BeanYonghu Zengjiapingjia(String shangpinbianhao,String pingjianeirong,String xingji,String zhaopian) throws BaseException
	{
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("��Ʒ��Ų���Ϊ��");
		}
		if (pingjianeirong == null || "".equals(pingjianeirong))
		{
			throw new BusinessException("�������ݲ���Ϊ��");
		}
		if (xingji == null || "".equals(xingji))
		{
			throw new BusinessException("�Ǽ�����Ϊ��");
		}
		if (!(Integer.valueOf(xingji) >=1 && Integer.valueOf(xingji) <=5))
		{
			throw new BusinessException("�Ǽ�����Ϊ��0-5֮�������");
		}
		if (zhaopian == null || "".equals(zhaopian))
		{
			throw new BusinessException("��Ƭ����Ϊ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException ("�����Ѵ���");
			sql = "select shuliang from dingdanxiangqing a,shangpindingdan b where b.dingdanbianhao = a.dingdanbianhao and a.shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (!rs.next())
			{
				throw new BusinessException("��δ�������Ʒ");
			}
			sql = "insert into shangpinpingjia(shangpinbianhao,pingjiayonghubianhao,pingjianeirong,pingjiariqi,xingji,zhaopian) values(?,?,?,NOW(),?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setString(3, pingjianeirong);
			pst.setInt(4, Integer.valueOf(xingji));
			pst.setString(5, zhaopian);
			pst.execute();
			JOptionPane.showMessageDialog(null,"���۳ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("��Ʒ��Ų���Ϊ��");
		}
		if (pingjianeirong == null || "".equals(pingjianeirong))
		{
			throw new BusinessException("�������ݲ���Ϊ��");
		}
		if (xingji == null || "".equals(xingji))
		{
			throw new BusinessException("�Ǽ�����Ϊ��");
		}
		if (!(Integer.valueOf(xingji) >=1 && Integer.valueOf(xingji) <=5))
		{
			throw new BusinessException("�Ǽ�����Ϊ��0-5֮�������");
		}
		if (zhaopian == null || "".equals(zhaopian))
		{
			throw new BusinessException("��Ƭ����Ϊ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("���۲�����");
			sql = "update shangpinpingjia set pingjianeirong = ?,pingjiariqi = NOW(),xingji = ?,zhaopian = ? where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(4, Integer.valueOf(shangpinbianhao));
			pst.setInt(5, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setString(1, pingjianeirong);
			pst.setInt(2, Integer.valueOf(xingji));
			pst.setString(3, zhaopian);
			pst.execute();
			JOptionPane.showMessageDialog(null,"���³ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("��Ʒ��Ų���Ϊ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pingjianeirong from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setInt(2, BeanYonghu.currentLoginYonghu.getYonghubianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("���۲�����");
			sql = "delete from shangpinpingjia where shangpinbianhao = ? and pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setInt(2,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.execute();
			JOptionPane.showMessageDialog(null,"ɾ���ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("��Ʒ��������Ϊ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shuliang from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(Gouwuche.getShangpinbianhao()));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("��Ʒ������");
			else
			{
				double shangpinshuliang = rs.getDouble(1);
				if (shangpinshuliang < Double.valueOf(shuliang))
				{
					throw new BusinessException("��Ʒ�������㣬�޷�����");
				}
			}
			sql = "update gouwuche set shuliang = ? where yonghubianhao = ? and gouwuchebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setDouble(1, Double.valueOf(shuliang));
			pst.setInt(2,BeanYonghu.currentLoginYonghu.getYonghubianhao());
			pst.setInt(3, Gouwuche.getGouwuchebianhao());
			pst.execute();
			JOptionPane.showMessageDialog(null,"�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			JOptionPane.showMessageDialog(null,"����һ�ջ�Ա�ɹ������ﳵ�۸���ȫ����Ϊ��Ա��", "���",JOptionPane.PLAIN_MESSAGE);
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
