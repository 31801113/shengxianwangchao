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
			throw new BusinessException("Ա����Ų���Ϊ��");
		}
		if("".equals(yuangongxingming) || yuangongxingming == null){
			throw new BusinessException("Ա����������Ϊ��");
		}
		if ("".equals(denglumima) || denglumima == null)
		{
			throw new BusinessException("���벻��Ϊ��");
		}
		if(!denglumima.equals(querenmima)){
			throw new BusinessException("���������������һ��");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yuangongbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("����Ա�˺��Ѿ�����");
			rs.close();
			pst.close();
			sql="insert into guanliyuan (yuangongbianhao,yuangongxingming,denglumima) values (?,?,?);";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(yuangongbianhao));
			pst.setString(2, yuangongxingming);
			pst.setString(3, denglumima);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null,"ע��ɹ�", "ע����",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("Ա����Ų���Ϊ��");
			}
			if (denglumima == null || "".equals(denglumima))
			{
				throw new BusinessException("��½���벻��Ϊ��");
			}
			conn=DBUtil.getConnection();
			String sql="select yuangongbianhao,yuangongxingming,denglumima from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yuangongbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException ("Ա���˺Ų�����");
			BeanGuanliyuan u=new BeanGuanliyuan();
			u.setYuangongbianhao(rs.getInt(1));
			u.setYuangongxingming(rs.getString(2));
			u.setDenglumima(rs.getString(3));
			if(!denglumima.equals(u.getDenglumima())) throw new BusinessException("�������");
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
		if("".equals(olddenglumima) || olddenglumima==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
		if("".equals(newdenglumima) || newdenglumima==null) throw new BusinessException("�����벻��Ϊ��");
		if (!newdenglumima.equals(newdenglumima2)) throw new BusinessException("�������������벻ͬ");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from guanliyuan where yuangongbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,BeanGuanliyuan.currentLoginGuanliyuan.getYuangongbianhao());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų�����");
			if(!BeanGuanliyuan.currentLoginGuanliyuan.getDenglumima().equals(olddenglumima)) throw new BusinessException("ԭʼ�������");
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time;
			try {
				time = new Timestamp(format.parse(huiyuanjiezhishijian).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��Ա��ֹʱ���ʽ����");
			}
			if (shifouhuiyuan1 == 1 && time.getTime() < System.currentTimeMillis())
			{
				throw new BusinessException("��Ա��ֹ�������ڵ�ǰʱ�䣬��Ա��Ϣ������");
			}
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("�û��˺��Ѿ�����");
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
			JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BaseException("�û���Ų���Ϊ��");
			}
			if (xingming == null || "".equals(xingming))
			{
				throw new BaseException("�û���������Ϊ��");
			}
			if (mima == null || "".equals(mima))
			{
				throw new BaseException("���벻��Ϊ��");
			}
			conn = DBUtil.getConnection();
			String sql="select yonghubianhao,xingming,mima from yonghu where yonghubianhao = ? and xingming = ? and mima = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			pst.setString(2, xingming);
			pst.setString(3, mima);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�û�������");
			sql = "select dizhibianhao from peisongdizhi where yonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("���͵�ַ�����Ѵ��ڸ��û���Ϣ������ɾ��");
			sql = "select dingdanbianhao from shangpindingdan where yonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ���������Ѵ��ڸ��û���Ϣ������ɾ��");
			sql = "select shangpinbianhao from shangpinpingjia where pingjiayonghubianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			rs=pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ���۱����Ѵ��ڸ��û���Ϣ������ɾ��");
			sql = "delete from yonghu where yonghubianhao = ? and xingming = ? and mima = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, yonghubianhao);
			pst.setString(2, xingming);
			pst.setString(3, mima);
			pst.execute();
			JOptionPane.showMessageDialog(null,"ɾ���ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time;
			try {
				time = new Timestamp(format.parse(huiyuanjiezhishijian).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��Ա��ֹʱ���ʽ����");
			}
			if (shifouhuiyuan1 == 1 && time.getTime() < System.currentTimeMillis())
			{
				throw new BusinessException("��Ա��ֹ�������ڵ�ǰʱ�䣬��Ա��Ϣ������");
			}
			conn=DBUtil.getConnection();
			String sql="select xingming from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�û��˺Ų�����");
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
			JOptionPane.showMessageDialog(null,"�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��ѯ�û���ź���������ͬʱΪ��");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag1 == 0)
			{
			String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ�û�������");
			JOptionPane.showMessageDialog(null,"�û����   "+"����   "+"�Ա�   "+"����   "+"�ֻ�����   "+"����   "+"���ڳ���   "+"ע��ʱ��   "+"�Ƿ��Ա   "+"��Ա��ֹʱ��\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "�û����   "+"����   "+"�Ա�   "+"����   "+"�ֻ�����   "+"����   "+"���ڳ���   "+"ע��ʱ��   "+"�Ƿ��Ա   "+"��Ա��ֹʱ��\n";
				String sql="select yonghubianhao from yonghu where xingming = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,xingming);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ�û�������");
				sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where xingming = ? order by yonghubianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,xingming);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10) + "\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ? and xingming = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(yonghubianhao));
				pst.setString(2, xingming);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ�û�������");
				JOptionPane.showMessageDialog(null,"�û����   "+"����   "+"�Ա�   "+"����   "+"�ֻ�����   "+"����   "+"���ڳ���   "+"ע��ʱ��   "+"�Ƿ��Ա   "+"��Ա��ֹʱ��\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getTimestamp(8)+"   "+ rs.getInt(9)+"   "+rs.getTimestamp(10), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("����Ų���Ϊ��");
		}
		if (leibiemingcheng == null || "".equals(leibiemingcheng))
		{
			throw new BusinessException("������Ʋ���Ϊ��");
		}
		if (leibiemiaoshu == null || "".equals(leibiemiaoshu))
		{
			throw new BusinessException("�����������Ϊ��");
		}
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("����Ѵ���");
			sql = "insert into shengxianleibie(leibiebianhao,leibiemingcheng,leibiemiaoshu) values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			pst.setString(2, leibiemingcheng);
			pst.setString(3, leibiemiaoshu);
			pst.execute();
			JOptionPane.showMessageDialog(null,"��ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("����Ų���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("������𲻴���");
			sql = "select shangpinbianhao from shangpin where leibiebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if(rs.next()) throw new BusinessException("��Ʒ��Ϣ���д��ڴ�������𣬲���ɾ��");
			sql = "delete from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			pst.execute();
			JOptionPane.showMessageDialog(null,"ɾ���ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("����Ų���Ϊ��");
			}
			if (leibiemingcheng == null || "".equals(leibiemingcheng))
			{
				throw new BusinessException("������Ʋ���Ϊ��");
			}
			if (leibiemiaoshu == null || "".equals(leibiemiaoshu))
			{
				throw new BusinessException("�����������Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(leibiebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("����Ų�����");
			sql = "update shengxianleibie set leibiemingcheng = ?,leibiemiaoshu = ? where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, leibiemingcheng);
			pst.setString(2, leibiemiaoshu);
			pst.setInt(3, Integer.valueOf(leibiebianhao));
			pst.execute();
			JOptionPane.showMessageDialog(null,"���³ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��ѯ����ź�������Ʋ���ͬʱΪ��");
			}
            conn = DBUtil.getConnection();
            if (flag1 == 1 && flag2 == 0)
            {
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiebianhao = ?";
			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			   pst.setInt(1,Integer.valueOf(leibiebianhao));
			   java.sql.ResultSet rs=pst.executeQuery();
			   if(!rs.next()) throw new BusinessException("��ѯ������𲻴���");
			   JOptionPane.showMessageDialog(null,"�����   "+"�������   "+"�������\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
			   return BeanGuanliyuan.currentLoginGuanliyuan;
            }
            if (flag1 == 0 && flag2 == 1)
            {
               String resultString = "�����   "+"�������   "+"�������\n";
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiemingcheng = ?";
 			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
 			   pst.setString(1,leibiemingcheng);
 			   java.sql.ResultSet rs=pst.executeQuery();
 			   if(!rs.next()) throw new BusinessException("��ѯ�����������������");
 			   sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiemingcheng = ? order by leibiebianhao";
			   pst=conn.prepareStatement(sql);
			   pst.setString(1,leibiemingcheng);
			   rs=pst.executeQuery();
			   while (rs.next())
			   {
			   resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"\n";
			   }
			   JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
			   return BeanGuanliyuan.currentLoginGuanliyuan;
 			   
            }
            if (flag1 == 1 && flag2 == 1)
            {
               String sql="select leibiebianhao,leibiemingcheng,leibiemiaoshu from shengxianleibie where leibiebianhao = ? and leibiemingcheng = ?";
 			   java.sql.PreparedStatement pst=conn.prepareStatement(sql);
 			   pst.setInt(1,Integer.valueOf(leibiebianhao));
 			   pst.setString(2, leibiemingcheng);
 			   java.sql.ResultSet rs=pst.executeQuery();
 			   if(!rs.next()) throw new BusinessException("��ѯ������𲻴���");
 			   JOptionPane.showMessageDialog(null,"�����   "+"�������   "+"�������\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��Ʒ��Ų���Ϊ��");
			}
			if (shangpinmingcheng == null || "".equals(shangpinmingcheng))
			{
				throw new BusinessException("��Ʒ���Ʋ���Ϊ��");
			}
			if (jiage == null ||"".equals(jiage))
			{
				throw new BusinessException("�۸���Ϊ��");
			}
			if (huiyuanjia == null || "".equals(huiyuanjia))
			{
				throw new BusinessException("��Ա�۲���Ϊ��");
			}
			if (shuliang == null || "".equals(shuliang))
			{
				throw new BusinessException("��������Ϊ��");
			}
			if (guige == null || "".equals(guige))
			{
				throw new BusinessException("�����Ϊ��");
			}
			if (xiangqing == null || "".equals(xiangqing))
			{
				throw new BusinessException("���鲻��Ϊ��");
			}
			if (leibiebianhao == null || "".equals(leibiebianhao ))
			{
				throw new BusinessException("�����");
			}
            conn = DBUtil.getConnection();
            String sql="select yonghubianhao,xingming,xingbie,mima,shoujihaoma,youxiang,suozaichengshi,zhuceshijian,shifouhuiyuan,huiyuanjiezhishijian from yonghu where yonghubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(yonghubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ�û�������");
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
