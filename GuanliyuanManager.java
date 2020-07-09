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
	
	public BeanGuanliyuan changePwd(BeanGuanliyuan user, String olddenglumima, String newdenglumima,
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
			JOptionPane.showMessageDialog(null,"�����޸ĳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			if (flag1 == 1 && flag2 == 0)
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
			if (Double.valueOf(huiyuanjia) > Double.valueOf(jiage))
			{
				throw new BusinessException("��Ա�۴���ƽʱ�۸�");
			}
            conn = DBUtil.getConnection();
            String sql="select leibiebianhao from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("��Ʒ����Ѵ���");
			sql = "select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if (!rs.next()) throw new BusinessException("����Ų�����");
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
	
	public BeanGuanliyuan ShanchuShangpin(String shangpinbianhao, String shangpinmingcheng)throws BaseException
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
            conn = DBUtil.getConnection();
            String sql="select shangpinmingcheng from shangpin where shangpinbianhao = ? and shangpinmingcheng = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			pst.setString(2, shangpinmingcheng);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��Ʒ������");
			sql = "select caipubianhao from shangpincaiputuijian where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ����Ʒ�����Ƽ����г��֣�����ɾ��");
			sql = "select pingjiayonghubianhao from shangpinpingjia where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ����Ʒ���۱��г��֣�����ɾ��");
			sql = "select manzhebianhao from manzheshangpinguanlian where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ��������Ʒ�������г��֣�����ɾ��");
			sql = "select cuxiaobianhao from xianshicuxiao where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ����ʱ�������г��֣�����ɾ��");
			sql = "select dingdanbianhao from dingdanxiangqing where shangpinbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			rs = pst.executeQuery();
			if (rs.next()) throw new BusinessException("��Ʒ�ڶ���������г��֣�����ɾ��");
			sql = "delete from shangpin where shangpinbianhao = ? and shangpinmingcheng = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(shangpinbianhao));
			pst.setString(2, shangpinmingcheng);
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
	
	public BeanGuanliyuan BianjiShangpin(String shangpinbianhao, String shangpinmingcheng, String jiage,String huiyuanjia, String shuliang , String guige, String xiangqing, String leibiebianhao)throws BaseException
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
			if (Double.valueOf(huiyuanjia) > Double.valueOf(jiage))
			{
				throw new BusinessException("��Ա�۴���ƽʱ�۸�");
			}
            conn = DBUtil.getConnection();
            String sql="select shangpinmingcheng from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ�û�������");
			sql = "select leibiemingcheng from shengxianleibie where leibiebianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(leibiebianhao));
			rs = pst.executeQuery();
			if (!rs.next()) throw new BusinessException("����Ų�����");
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
				throw new BusinessException("��ѯ��Ʒ��ź���Ʒ���Ʋ���ͬʱΪ��");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ��Ʒ������");
			JOptionPane.showMessageDialog(null,"��Ʒ���   "+"��Ʒ����   "+"�۸�   "+"��Ա��   "+"����   "+"���   "+"����   "+"�����\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "��Ʒ���   "+"��Ʒ����   "+"�۸�   "+"��Ա��   "+"����   "+"���   "+"����   "+"�����\n";
				String sql="select shangpinbianhao from shangpin where shangpinmingcheng = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,shangpinmingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ��Ʒ������");
				sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinmingcheng = ? order by shangpinbianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,shangpinmingcheng);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select shangpinbianhao,shangpinmingcheng,jiage,huiyuanjia,shuliang,guige,xiangqing,leibiebianhao from shangpin where shangpinmingcheng = ? and shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(shangpinbianhao));
				pst.setString(1, shangpinmingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ��Ʒ������");
				JOptionPane.showMessageDialog(null,"��Ʒ���   "+"��Ʒ����   "+"�۸�   "+"��Ա��   "+"����   "+"���   "+"����   "+"�����\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getDouble(5)+"   "+rs.getString(6)+"   "+rs.getString(7)+"   "+rs.getInt(8), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("�Ż�ȯ��Ų���Ϊ��");
			}
			if (neirong == null || "".equals(neirong))
			{
				throw new BusinessException("���ݲ���Ϊ��");
			}
			if (shiyongjine == null ||"".equals(shiyongjine))
			{
				throw new BusinessException("ʹ�ý���Ϊ��");
			}
			if (jianmianjine == null || "".equals(jianmianjine))
			{
				throw new BusinessException("�������Ϊ��");
			}
			if (qishiriqi == null || "".equals(qishiriqi))
			{
				throw new BusinessException("��ʼ���ڲ���Ϊ��");
			}
			if (jiezhiriqi == null || "".equals(jiezhiriqi))
			{
				throw new BusinessException("��ֹ���ڲ���Ϊ��");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time1,time2;
			try {
				time1 = new Timestamp(format.parse(qishiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��ʼ����ʱ���ʽ����");
			}
			try {
				time2 = new Timestamp(format.parse(jiezhiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��ֹ����ʱ���ʽ����");
			}
			if (time1.getTime() > time2.getTime())
			{
				throw new BusinessException("��ʼ�������ڽ�ֹ����");
			}
			if (Double.valueOf(shiyongjine) < Double.valueOf(jianmianjine))
			{
				throw new BusinessException("���������ʹ�ý��");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("�Ż�ȯ����Ѵ���");
			sql = "insert into youhuiquan(youhuiquanbianhao,neirong,shiyongjine,jianmianjine,qishiriqi,jieshuriqi) values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.valueOf(youhuiquanbianhao));
			pst.setString(2, neirong);
			pst.setDouble(3, Double.valueOf(shiyongjine));
			pst.setDouble(4, Double.valueOf(jianmianjine));
			pst.setTimestamp(5, time1);
			pst.setTimestamp(6, time2);
			pst.execute();
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
	
	public BeanGuanliyuan ShanchuYouhuiquan(String youhuiquanbianhao)throws BaseException
	{
		Connection conn=null;
		try {
			if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
			{
				throw new BusinessException("�Ż�ȯ��Ų���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�Ż�ȯ������");
			sql="select dingdanbianhao from shangpindingdan where shiyongyouhuiquanbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("�Ż�ȯ����Ʒ�������г��֣�����ɾ��");
			sql="delete from youhuiquan where youhuiquanbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
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
	
	public BeanGuanliyuan BianjiYouhuiquan(String youhuiquanbianhao, String neirong,String shiyongjine,String jianmianjine,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
			if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
			{
				throw new BusinessException("�Ż�ȯ��Ų���Ϊ��");
			}
			if (neirong == null || "".equals(neirong))
			{
				throw new BusinessException("���ݲ���Ϊ��");
			}
			if (shiyongjine == null ||"".equals(shiyongjine))
			{
				throw new BusinessException("ʹ�ý���Ϊ��");
			}
			if (jianmianjine == null || "".equals(jianmianjine))
			{
				throw new BusinessException("�������Ϊ��");
			}
			if (qishiriqi == null || "".equals(qishiriqi))
			{
				throw new BusinessException("��ʼ���ڲ���Ϊ��");
			}
			if (jieshuriqi == null || "".equals(jieshuriqi))
			{
				throw new BusinessException("��ֹ���ڲ���Ϊ��");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp time1,time2;
			try {
				time1 = new Timestamp(format.parse(qishiriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��ʼ����ʱ���ʽ����");
			}
			try {
				time2 = new Timestamp(format.parse(jieshuriqi).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new BusinessException("��ֹ����ʱ���ʽ����");
			}
			if (time1.getTime() > time2.getTime())
			{
				throw new BusinessException("��ʼ�������ڽ�ֹ����");
			}
			if (Double.valueOf(shiyongjine) < Double.valueOf(jianmianjine))
			{
				throw new BusinessException("���������ʹ�ý��");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from youhuiquan where youhuiquanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�Ż�ȯ��Ų�����");
			sql = "update youhuiquan set neirong = ?,shiyongjine = ?,jianmianjine = ?,qishiriqi = ?,jieshuriqi = ? where youhuiquanbianhao = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(6, Integer.valueOf(youhuiquanbianhao));
			pst.setString(1, neirong);
			pst.setDouble(2, Double.valueOf(shiyongjine));
			pst.setDouble(3, Double.valueOf(jianmianjine));
			pst.setTimestamp(4, time1);
			pst.setTimestamp(5, time2);
			pst.execute();
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
	
	public BeanGuanliyuan ChaxunYouhuiquan(String youhuiquanbianhao)throws BaseException
	{
		Connection conn=null;
		try {
		if (youhuiquanbianhao == null || "".equals(youhuiquanbianhao))
		{
			throw new BusinessException("��ѯ�Ż�ȯ��Ų���Ϊ��");
		}
		conn = DBUtil.getConnection();
		String sql="select youhuiquanbianhao,neirong,shiyongjine,jianmianjine,qishiriqi,jieshuriqi from youhuiquan where youhuiquanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(youhuiquanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("��ѯ�Ż�ȯ������");
		JOptionPane.showMessageDialog(null,"�Ż�ȯ���   "+"����   "+"ʹ�ý��   "+"������   "+"��ʼ����   "+"��������\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("���۱�Ų���Ϊ��");
		}
		if (neirong == null || "".equals(neirong))
		{
			throw new BusinessException("���ݲ���Ϊ��");
		}
		if (shiyongshangpinshuliang == null || "".equals(shiyongshangpinshuliang))
		{
			throw new BusinessException("������Ʒ��������Ϊ��");
		}
		if (zhekou == null || "".equals(zhekou))
		{
			throw new BusinessException("�ۿ۲���Ϊ��");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("��ʼ���ڲ���Ϊ��");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("�������ڲ���Ϊ��");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��ʼ����ʱ���ʽ����");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��������ʱ���ʽ����");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("��ʼ�������ڽ�������");
		}
		if (Double.valueOf(zhekou) > 1)
		{
			throw new BusinessException("�ۿ۴���1");
		}
		conn = DBUtil.getConnection();
        String sql="select neirong from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("���۱���Ѵ���");
		sql = "insert manzhe(manzhebianhao,neirong,shiyongshangpinshuliang,zhekou,qishiriqi,jieshuriqi)values(?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(manzhebianhao));
		pst.setString(2, neirong);
		pst.setDouble(3, Double.valueOf(shiyongshangpinshuliang));
		pst.setDouble(4, Double.valueOf(zhekou));
		pst.setTimestamp(5, time1);
		pst.setTimestamp(6, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("���۱�Ų���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select neirong from manzhe where manzhebianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("���۲�����");
			sql="select shangpinbianhao from manzheshangpinguanlian where manzhebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("������������Ʒ�������г��֣�����ɾ��");
			sql="delete from manzhe where manzhebianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(manzhebianhao));
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
	
	public BeanGuanliyuan BianjiManzhe(String manzhebianhao, String neirong,String shiyongshangpinshuliang,String zhekou,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (manzhebianhao == null || "".equals(manzhebianhao))
		{
			throw new BusinessException("���۱�Ų���Ϊ��");
		}
		if (neirong == null || "".equals(neirong))
		{
			throw new BusinessException("���ݲ���Ϊ��");
		}
		if (shiyongshangpinshuliang == null || "".equals(shiyongshangpinshuliang))
		{
			throw new BusinessException("������Ʒ��������Ϊ��");
		}
		if (zhekou == null || "".equals(zhekou))
		{
			throw new BusinessException("�ۿ۲���Ϊ��");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("��ʼ���ڲ���Ϊ��");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("�������ڲ���Ϊ��");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��ʼ����ʱ���ʽ����");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��������ʱ���ʽ����");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("��ʼ�������ڽ�������");
		}
		if (Double.valueOf(zhekou) > 1)
		{
			throw new BusinessException("�ۿ۴���1");
		}
		conn = DBUtil.getConnection();
        String sql="select neirong from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("���۱�Ų�����");
		sql = "update manzhe set neirong = ?,shiyongshangpinshuliang = ?,zhekou = ?,qishiriqi = ?,jieshuriqi = ? where manzhebianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(6, Integer.valueOf(manzhebianhao));
		pst.setString(1, neirong);
		pst.setDouble(2, Double.valueOf(shiyongshangpinshuliang));
		pst.setDouble(3, Double.valueOf(zhekou));
		pst.setTimestamp(4, time1);
		pst.setTimestamp(5, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("��ѯ���۱�Ų���Ϊ��");
		}
		conn = DBUtil.getConnection();
		String sql="select manzhebianhao,neirong,shiyongshangpinshuliang,zhekou,qishiriqi,jieshuriqi from manzhe where manzhebianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(manzhebianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("��ѯ���۲�����");
		JOptionPane.showMessageDialog(null,"���۱��   "+"����   "+"ʹ����Ʒ����   "+"�ۿ�   "+"��ʼ����   "+"��������\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("������Ų���Ϊ��");
		}
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("���ݲ���Ϊ��");
		}
		if (cuxiaojiage == null || "".equals(cuxiaojiage))
		{
			throw new BusinessException("�����۸���Ϊ��");
		}
		if (cuxiaoshuliang == null || "".equals(cuxiaoshuliang))
		{
			throw new BusinessException("������������Ϊ��");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("��ʼ���ڲ���Ϊ��");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("�������ڲ���Ϊ��");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��ʼ����ʱ���ʽ����");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��������ʱ���ʽ����");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("��ʼ�������ڽ�������");
		}
		conn = DBUtil.getConnection();
        String sql="select shangpinbianhao from xianshicuxiao where cuxiaobianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(cuxiaobianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("��������Ѵ���");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shangpinbianhao));
		rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("��Ʒ��Ų�����");
		sql = "insert into xianshicuxiao(cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi) values(?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(cuxiaobianhao));
		pst.setInt(2, Integer.valueOf(shangpinbianhao));
		pst.setDouble(3, Double.valueOf(cuxiaojiage));
		pst.setDouble(4, Double.valueOf(cuxiaoshuliang));
		pst.setTimestamp(5, time1);
		pst.setTimestamp(6, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("������Ų���Ϊ��");
			}
			if (shangpinbianhao == null || "".equals(shangpinbianhao))
			{
				throw new BusinessException("��Ʒ��Ų���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select cuxiaojiage from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			pst.setInt(2, Integer.valueOf(shangpinbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ʱ����������");
			sql="delete from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			pst.setInt(2, Integer.valueOf(shangpinbianhao));
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
	
	public BeanGuanliyuan BianjiXianshicuxiao(String cuxiaobianhao, String shangpinbianhao,String cuxiaojiage,String cuxiaoshuliang,String qishiriqi,String jieshuriqi)throws BaseException
	{
		Connection conn=null;
		try {
		if (cuxiaobianhao == null || "".equals(cuxiaobianhao))
		{
			throw new BusinessException("������Ų���Ϊ��");
		}
		if (shangpinbianhao == null || "".equals(shangpinbianhao))
		{
			throw new BusinessException("���ݲ���Ϊ��");
		}
		if (cuxiaojiage == null || "".equals(cuxiaojiage))
		{
			throw new BusinessException("�����۸���Ϊ��");
		}
		if (cuxiaoshuliang == null || "".equals(cuxiaoshuliang))
		{
			throw new BusinessException("������������Ϊ��");
		}
		if (qishiriqi == null || "".equals(qishiriqi))
		{
			throw new BusinessException("��ʼ���ڲ���Ϊ��");
		}
		if (jieshuriqi == null || "".equals(jieshuriqi))
		{
			throw new BusinessException("�������ڲ���Ϊ��");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time1,time2;
		try {
			time1 = new Timestamp(format.parse(qishiriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��ʼ����ʱ���ʽ����");
		}
		try {
			time2 = new Timestamp(format.parse(jieshuriqi).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new BusinessException("��������ʱ���ʽ����");
		}
		if (time1.getTime() > time2.getTime())
		{
			throw new BusinessException("��ʼ�������ڽ�������");
		}
		conn = DBUtil.getConnection();
        String sql="select shangpinbianhao from xianshicuxiao where cuxiaobianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(cuxiaobianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("������Ų�����");
		sql = "update xianshicuxiao set shangpinbianhao = ?,cuxiaojiage = ?,cuxiaoshuliang = ?,qishiriqi = ?,jieshuriqi = ? where cuxiaobianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(6, Integer.valueOf(cuxiaobianhao));
		pst.setInt(1, Integer.valueOf(shangpinbianhao));
		pst.setDouble(2, Double.valueOf(cuxiaojiage));
		pst.setDouble(3, Double.valueOf(cuxiaoshuliang));
		pst.setTimestamp(4, time1);
		pst.setTimestamp(5, time2);
		pst.execute();
		JOptionPane.showMessageDialog(null,"�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��ѯ������ź���Ʒ��Ų���ͬʱΪ��");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where cuxiaobianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(cuxiaobianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ����������");
			JOptionPane.showMessageDialog(null,"�������   "+"��Ʒ���   "+"�����۸�   "+"��������   "+"��ʼ����   "+"��������\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "�������   "+"��Ʒ���   "+"�����۸�   "+"��������   "+"��ʼ����   "+"��������\n";
				String sql="select cuxiaojiage from xianshicuxiao where shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shangpinbianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ����������");
				sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where shangpinbianhao = ? order by cuxiaobianhao";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shangpinbianhao));
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select cuxiaobianhao,shangpinbianhao,cuxiaojiage,cuxiaoshuliang,qishiriqi,jieshuriqi from xianshicuxiao where cuxiaobianhao = ? and shangpinbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(shangpinbianhao));
				pst.setInt(1, Integer.valueOf(cuxiaobianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ����������");
				JOptionPane.showMessageDialog(null,"�������   "+"��Ʒ���   "+"�����۸�   "+"��������   "+"��ʼ����   "+"��������\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getDouble(4)+"   "+rs.getTimestamp(5)+"   "+rs.getTimestamp(6), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("���ױ�Ų���Ϊ��");
		}
		if (caipumingcheng == null || "".equals(caipumingcheng))
		{
			throw new BusinessException("�������Ʋ���Ϊ��");
		}
		if (caipuyongliao == null || "".equals(caipuyongliao))
		{
			throw new BusinessException("�������ϲ���Ϊ��");
		}
		if (buzhou == null || "".equals(buzhou))
		{
			throw new BusinessException("���費��Ϊ��");
		}
		if (tupian == null || "".equals(tupian))
		{
			throw new BusinessException("ͼƬ����Ϊ��");
		}
		conn = DBUtil.getConnection();
        String sql="select caipumingcheng from caipu where caipubianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caipubianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("���ױ���Ѵ���");
		sql = "insert into caipu(caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian) values(?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(caipubianhao));
		pst.setString(2, caipumingcheng);
		pst.setString(3, caipuyongliao);
		pst.setString(4, buzhou);
		pst.setString(5, tupian);
		pst.execute();
		JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("���ױ�Ų���Ϊ��");
			}
			if (caipumingcheng == null || "".equals(caipumingcheng))
			{
				throw new BusinessException("�������Ʋ���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select caipumingcheng from caipu where caipubianhao = ? and caipumingcheng = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			pst.setString(2, caipumingcheng);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("���ײ�����");
			sql="select shangpinbianhao from shangpincaiputuijian where caipubianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("��������Ʒ�����Ƽ����г��֣�����ɾ��");
			sql="delete from caipu where caipubianhao = ? and caipumingcheng = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			pst.setString(2, caipumingcheng);
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
	
	public BeanGuanliyuan BainjiCaipu(String caipubianhao,String caipumingcheng,String caipuyongliao,String buzhou,String tupian)throws BaseException
	{
		Connection conn=null;
		try {
		if (caipubianhao == null || "".equals(caipubianhao))
		{
			throw new BusinessException("���ױ�Ų���Ϊ��");
		}
		if (caipumingcheng == null || "".equals(caipumingcheng))
		{
			throw new BusinessException("�������Ʋ���Ϊ��");
		}
		if (caipuyongliao == null || "".equals(caipuyongliao))
		{
			throw new BusinessException("�������ϲ���Ϊ��");
		}
		if (buzhou == null || "".equals(buzhou))
		{
			throw new BusinessException("���費��Ϊ��");
		}
		if (tupian == null || "".equals(tupian))
		{
			throw new BusinessException("ͼƬ����Ϊ��");
		}
		conn = DBUtil.getConnection();
        String sql="select caipumingcheng from caipu where caipubianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caipubianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("���ײ�����");
		sql = "update caipu set caipumingcheng = ?,caipuyongliao = ?,buzhou = ?,tupian = ? where caipubianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(5, Integer.valueOf(caipubianhao));
		pst.setString(1, caipumingcheng);
		pst.setString(2, caipuyongliao);
		pst.setString(3, buzhou);
		pst.setString(4, tupian);
		pst.execute();
		JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��ѯ���ױ�źͲ������Ʋ���ͬʱΪ��");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipubianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caipubianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ���ײ�����");
			JOptionPane.showMessageDialog(null,"���ױ��   "+"��������   "+"��������   "+"����   "+"ͼƬ\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "���ױ��   "+"��������   "+"��������   "+"����   "+"ͼƬ\n";
				String sql="select caipubianhao from caipu where caipumingcheng = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ���ײ�����");
				sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipumingcheng = ? order by caipubianhao";
				pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select caipubianhao,caipumingcheng,caipuyongliao,buzhou,tupian from caipu where caipumingcheng = ? and caipubianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,caipumingcheng);
				pst.setInt(2, Integer.valueOf(caipubianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ���ײ�����");
				JOptionPane.showMessageDialog(null,"���ױ��   "+"��������   "+"��������   "+"����   "+"ͼƬ\n"+rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getString(5), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
			throw new BusinessException("�ɹ���ŵ�����Ϊ��");
		}
		if (shicaibianhao == null || "".equals(shicaibianhao))
		{
			throw new BusinessException("ʳ�ı�Ų���Ϊ��");
		}
		if (shuliang == null || "".equals(shuliang))
		{
			throw new BusinessException("��������Ϊ��");
		}
		if (zhuangtai == null || "".equals(zhuangtai))
		{
			throw new BusinessException("״̬����Ϊ��");
		}
		if (!(zhuangtai.equals("xiadan") || zhuangtai.equals("zaitu") || zhuangtai.equals("ruku")))
		{
			throw new BusinessException("״ֻ̬��Ϊ��xiadan����zaitu����ruku��");
		}
		conn = DBUtil.getConnection();
        String sql="select shicaibianhao from shangpincaigou where caigoudanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caigoudanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) throw new BusinessException("�ɹ�������Ѵ���");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		rs = pst.executeQuery();
		if (!rs.next()) throw new BusinessException("ʳ�ı�Ų�����");
		sql = "insert into shangpincaigou(caigoudanbianhao,shicaibianhao,shuliang,zhuangtai) values(?,?,?,?)";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(caigoudanbianhao));
		pst.setInt(2, Integer.valueOf(shicaibianhao));
		pst.setDouble(3, Double.valueOf(shuliang));
		pst.setString(4, zhuangtai);
		pst.execute();
		JOptionPane.showMessageDialog(null,"���ӳɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("�ɹ�����Ų���Ϊ��");
			}
			if (shicaibianhao == null || "".equals(shicaibianhao))
			{
				throw new BusinessException("ʳ�ı�Ų���Ϊ��");
			}
            conn = DBUtil.getConnection();
            String sql="select shuliang from shangpincaigou where caigoudanbianhao = ? and shicaibianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			pst.setInt(2, Integer.valueOf(shicaibianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�ɹ�������");
			sql="delete from shangpincaigou where caigoudanbianhao = ? and shicaibianhao = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			pst.setInt(2, Integer.valueOf(shicaibianhao));
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
	
	public BeanGuanliyuan BianjiCaigou(String caigoudanbianhao,String shicaibianhao,String shuliang,String zhuangtai)throws BaseException
	{
		Connection conn=null;
		try {
		if (caigoudanbianhao == null || "".equals(caigoudanbianhao))
		{
			throw new BusinessException("�ɹ���ŵ�����Ϊ��");
		}
		if (shicaibianhao == null || "".equals(shicaibianhao))
		{
			throw new BusinessException("ʳ�ı�Ų���Ϊ��");
		}
		if (shuliang == null || "".equals(shuliang))
		{
			throw new BusinessException("��������Ϊ��");
		}
		if (zhuangtai == null || "".equals(zhuangtai))
		{
			throw new BusinessException("״̬����Ϊ��");
		}
		if (!(zhuangtai.equals("xiadan") || zhuangtai.equals("zaitu") || zhuangtai.equals("ruku")))
		{
			throw new BusinessException("״ֻ̬��Ϊ��xiadan����zaitu����ruku��");
		}
		conn = DBUtil.getConnection();
        String sql="select shicaibianhao from shangpincaigou where caigoudanbianhao = ?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,Integer.valueOf(caigoudanbianhao));
		java.sql.ResultSet rs=pst.executeQuery();
		if(!rs.next()) throw new BusinessException("�ɹ�����Ų�����");
		sql = "select shangpinmingcheng from shangpin where shangpinbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		rs = pst.executeQuery();
		if (!rs.next()) throw new BusinessException("ʳ�ı�Ų�����");
		sql = "update shangpincaigou set shicaibianhao = ?,shuliang = ?,zhuangtai = ? where caigoudanbianhao = ?";
		pst = conn.prepareStatement(sql);
		pst.setInt(4, Integer.valueOf(caigoudanbianhao));
		pst.setInt(1, Integer.valueOf(shicaibianhao));
		pst.setDouble(2, Double.valueOf(shuliang));
		pst.setString(3, zhuangtai);
		pst.execute();
		JOptionPane.showMessageDialog(null,"�༭�ɹ�", "���",JOptionPane.PLAIN_MESSAGE);
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
				throw new BusinessException("��ѯ�ɹ�����ź�ʳ�ı�Ų���ͬʱΪ��");
			}
			conn = DBUtil.getConnection();
			if (flag1 == 1 && flag2 == 0)
			{
			String sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where caigoudanbianhao = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.valueOf(caigoudanbianhao));
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��ѯ�ɹ�������");
			JOptionPane.showMessageDialog(null,"�ɹ������   "+"ʳ�ı��   "+"����   "+"״̬\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4),"��ѯ���",JOptionPane.PLAIN_MESSAGE);
			return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if(flag1 == 0 && flag2 == 1)
			{
				String resultString = "�ɹ������   "+"ʳ�ı��   "+"����   "+"״̬\n";
				String sql="select shuliang from shangpincaigou where shicaibianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shicaibianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ�ɹ�������");
				sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where shicaibianhao = ? order by caigoudanbianhao";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,Integer.valueOf(shicaibianhao));
				rs=pst.executeQuery();
				while (rs.next())
				{
				resultString += rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4)+"\n";
				}
				JOptionPane.showMessageDialog(null,resultString, "��ѯ���",JOptionPane.PLAIN_MESSAGE);
				return BeanGuanliyuan.currentLoginGuanliyuan;
			}
			else if (flag1 == 1 && flag2 == 1)
			{
				String sql="select caigoudanbianhao,shicaibianhao,shuliang,zhuangtai from shangpincaigou where shicaibianhao = ? and caigoudanbianhao = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(2,Integer.valueOf(caigoudanbianhao));
				pst.setInt(1, Integer.valueOf(shicaibianhao));
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��ѯ�ɹ�������");
				JOptionPane.showMessageDialog(null,"�ɹ������   "+"ʳ�ı��   "+"����   "+"״̬\n"+rs.getInt(1)+"   "+rs.getInt(2)+"   "+rs.getDouble(3)+"   "+rs.getString(4), "��ѯ���",JOptionPane.PLAIN_MESSAGE);
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
