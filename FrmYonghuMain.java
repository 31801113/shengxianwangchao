package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.BeanCaipu;
import model.BeanManzhe;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmYonghuMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_gerenxinxiguanli=new JMenu("个人信息管理");
    private JMenu menu_goumairukou=new JMenu("购买入口");
    private JMenu menu_pingjiaguanli=new JMenu("评价管理");
    
    private JMenuItem  menuItem_Xiugaimima=new JMenuItem("修改密码");
    private JMenuItem  menuItem_Bianjigerenxinxi=new JMenuItem("编辑个人信息");
    private JMenuItem  menuItem_Bianjipeisongdizhi=new JMenuItem("编辑配送地址");
    private JMenuItem  menuItem_Chaxunshangpindingdan=new JMenuItem("查询商品订单");
    private JMenuItem  menuItem_Tuichudenglu=new JMenuItem("退出登录");
    private JMenuItem  menuItem_Chengweihuiyuan=new JMenuItem("成为会员");
    private JMenuItem  menuItem_Goumaishangpin=new JMenuItem("购买商品");
    private JMenuItem  menuItem_Chaxunyouhuiquan=new JMenuItem("查询优惠券");
    private JMenuItem  menuItem_Chaxunmanzhexinxi=new JMenuItem("查询满折信息");
    private JMenuItem  menuItem_Chaxunxianshicuxiao=new JMenuItem("查询限时促销");
    private JMenuItem  menuItem_Zengjiapingjia=new JMenuItem("增加评价");
    private JMenuItem  menuItem_Bianjipingjia=new JMenuItem("编辑评价");
    private JMenuItem  menuItem_Shanchupingjia=new JMenuItem("删除评价");
    private JMenuItem  menuItem_Chakanpingjia=new JMenuItem("查看评价");
    
	private JPanel statusBar = new JPanel();
	
	private Object tblCaipuTitle[]=BeanCaipu.tableTitles;
	private Object tblCaipuData[][];
	DefaultTableModel tabCaipuModel=new DefaultTableModel();
	private JTable dataTableCaipu=new JTable(tabCaipuModel);
	
	List<BeanCaipu> allCaipu=null;
	private void reloadCaipuTable(){//这是测试数据，需要用实际数替换
		try {
			allCaipu=Util.caipuManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCaipuData =  new Object[allCaipu.size()][BeanCaipu.tableTitles.length];
		for(int i=0;i<allCaipu.size();i++){
			for(int j=0;j<BeanCaipu.tableTitles.length;j++)
				tblCaipuData[i][j]=allCaipu.get(i).getCell(j);
		}
		tabCaipuModel.setDataVector(tblCaipuData,tblCaipuTitle);
		this.dataTableCaipu.validate();
		this.dataTableCaipu.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_Xiugaimima)
		{
			FrmXiugaimima dlg=new FrmXiugaimima(this,"修改密码",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Bianjigerenxinxi)
		{
			FrmBianjigerenxinxi dlg = new FrmBianjigerenxinxi(this, "编辑个人信息", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Bianjipeisongdizhi)
		{
			FrmBianjipeisongdizhi dlg = new FrmBianjipeisongdizhi(this, "编辑配送地址", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chaxunshangpindingdan)
		{
			FrmChaxunshangpindingdan dlg= new FrmChaxunshangpindingdan();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Goumaishangpin)
		{
			FrmGoumaishangpin dlg= new FrmGoumaishangpin();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chaxunyouhuiquan)
		{
			FrmYonghuChaxunyouhuiquan dlg = new FrmYonghuChaxunyouhuiquan();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chaxunmanzhexinxi)
		{
			FrmYonghuChaxunmanzhe dlg = new FrmYonghuChaxunmanzhe();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chaxunxianshicuxiao)
		{
			FrmYonghuChaxunxianshicuxiao dlg = new FrmYonghuChaxunxianshicuxiao();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Zengjiapingjia)
		{
			FrmZengjiapingjia dlg = new FrmZengjiapingjia(this, "增加评价", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Bianjipingjia)
		{
			FrmBianjipingjia dlg = new FrmBianjipingjia(this, "编辑评价", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Shanchupingjia)
		{
			FrmShanchupingjia dlg = new FrmShanchupingjia(this, "删除评价", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chakanpingjia)
		{
			FrmChakanpingjia dlg = new FrmChakanpingjia();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Tuichudenglu)
		{
			this.setVisible(false);
			FrmMain dlg = new FrmMain();
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Chengweihuiyuan)
		{
			if (BeanYonghu.currentLoginYonghu.getShifouhuiyuan() == 1)
			{
				JOptionPane.showMessageDialog(null,"您已是会员", "结果",JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				try {
					BeanYonghu.currentLoginYonghu = Util.yonghuManager.Chengweihuiyuan(BeanYonghu.currentLoginYonghu);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
		}
	}
	
	public FrmYonghuMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("用户系统");
		
		this.menu_gerenxinxiguanli.add(this.menuItem_Xiugaimima);this.menuItem_Xiugaimima.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Bianjigerenxinxi);this.menuItem_Bianjigerenxinxi.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Bianjipeisongdizhi);this.menuItem_Bianjipeisongdizhi.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Chaxunshangpindingdan);this.menuItem_Chaxunshangpindingdan.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Tuichudenglu);this.menuItem_Tuichudenglu.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Chengweihuiyuan);this.menuItem_Chengweihuiyuan.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Goumaishangpin);this.menuItem_Goumaishangpin.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunyouhuiquan);this.menuItem_Chaxunyouhuiquan.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunmanzhexinxi);this.menuItem_Chaxunmanzhexinxi.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunxianshicuxiao);this.menuItem_Chaxunxianshicuxiao.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Zengjiapingjia);this.menuItem_Zengjiapingjia.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Bianjipingjia);this.menuItem_Bianjipingjia.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Shanchupingjia);this.menuItem_Shanchupingjia.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Chakanpingjia);this.menuItem_Chakanpingjia.addActionListener(this);
		
		menubar.add(menu_gerenxinxiguanli);
		menubar.add(menu_goumairukou);
		menubar.add(menu_pingjiaguanli);
		
		this.setJMenuBar(menubar);
		
this.getContentPane().add(new JScrollPane(this.dataTableCaipu), BorderLayout.CENTER);
	  
	    
	    this.reloadCaipuTable();
		
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
}
