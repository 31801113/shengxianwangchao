package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import itf.IYonghuManager;
import model.BeanGuanliyuan;
import model.BeanYonghu;
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
}
