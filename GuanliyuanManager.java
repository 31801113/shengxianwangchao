package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import itf.IGuanliyuanManager;
import model.BeanGuanliyuan;
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
			if(!(xingbie.equals("��") || xingbie.equals("Ů")))
			{
				throw new BusinessException("�Ա���Ϊ�����С�����Ů����������ַ�");
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
