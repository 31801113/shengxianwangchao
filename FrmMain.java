package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import util.BaseException;

public class FrmMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_yonghu=new JMenu("用户管理");
    private JMenu menu_shengxianleibie=new JMenu("生鲜类别管理");
    private JMenu menu_shangpin=new JMenu("商品管理");
    private JMenu menu_youhuiquan=new JMenu("优惠券管理");
    private JMenu menu_manzhe=new JMenu("满折管理");
    private JMenu menu_xianshicuxiao=new JMenu("限时促销");
    private JMenu menu_caipu=new JMenu("菜谱管理");
    private JMenu menu_caigou=new JMenu("采购管理");
    private JMenu menu_gengduo=new JMenu("更多");
    
    private JMenuItem  menuItem_ZengjiaYonghu=new JMenuItem("增加用户");
    private JMenuItem  menuItem_ShanchuYonghu=new JMenuItem("删除用户");
    private JMenuItem  menuItem_BianjiYonghu=new JMenuItem("编辑用户");
    private JMenuItem  menuItem_ChaxunYonghu=new JMenuItem("查询用户");
    private JMenuItem  menuItem_ZengjiaShengxianleibie=new JMenuItem("增加生鲜类别");
    private JMenuItem  menuItem_ShanchuShengxianleibie=new JMenuItem("删除生鲜类别");
    private JMenuItem  menuItem_BianjiShengxianleibie=new JMenuItem("编辑生鲜类别");
    private JMenuItem  menuItem_ChaxunShengxianleibie=new JMenuItem("查询生鲜类别");
    private JMenuItem  menuItem_ZengjiaShangpin=new JMenuItem("增加商品");
    private JMenuItem  menuItem_ShanchuShangpin=new JMenuItem("删除商品");
    private JMenuItem  menuItem_BianjiShangpin=new JMenuItem("编辑商品");
    private JMenuItem  menuItem_ChaxunShangpin=new JMenuItem("查询商品");
    private JMenuItem  menuItem_ZengjiaYouhuiquan=new JMenuItem("增加优惠券");
    private JMenuItem  menuItem_ShanchuYouhuiquan=new JMenuItem("删除优惠券");
    private JMenuItem  menuItem_BianjiYouhuiquan=new JMenuItem("编辑优惠券");
    private JMenuItem  menuItem_ChaxunYouhuiquan=new JMenuItem("查询优惠券");
    private JMenuItem  menuItem_ZengjiaManzhe=new JMenuItem("增加满折");
    private JMenuItem  menuItem_ShanchuManzhe=new JMenuItem("删除满折");
    private JMenuItem  menuItem_BianjiManzhe=new JMenuItem("编辑满折");
    private JMenuItem  menuItem_ChaxunManzhe=new JMenuItem("查询满折");
    private JMenuItem  menuItem_ZengjiaXianshicuxiao=new JMenuItem("增加限时促销");
    private JMenuItem  menuItem_ShanchuXianshicuxiao=new JMenuItem("删除限时促销");
    private JMenuItem  menuItem_BianjiXianshicuxiao=new JMenuItem("编辑限时促销");
    private JMenuItem  menuItem_ChaxunXianshicuxiao=new JMenuItem("查询限时促销");
    private JMenuItem  menuItem_ZengjiaCaipu=new JMenuItem("增加菜谱");
    private JMenuItem  menuItem_ShanchuCaipu=new JMenuItem("删除菜谱");
    private JMenuItem  menuItem_BianjiCaipu=new JMenuItem("编辑菜谱");
    private JMenuItem  menuItem_ChaxunCaipu=new JMenuItem("查询菜谱");
    private JMenuItem  menuItem_ZengjiaCaigou=new JMenuItem("增加采购");
    private JMenuItem  menuItem_ShanchuCaigou=new JMenuItem("删除采购");
    private JMenuItem  menuItem_BianjiCaigou=new JMenuItem("编辑采购");
    private JMenuItem  menuItem_ChaxunCaigou=new JMenuItem("查询采购");
    private JMenuItem  menuItem_Xiugaimima=new JMenuItem("修改密码");
    
    private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_ZengjiaYonghu)
		{
			FrmZengjiaYonghu dlg=new FrmZengjiaYonghu(this,"增加用户",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuYonghu)
		{
			FrmShanchuYonghu dlg = new FrmShanchuYonghu(this,"删除用户",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiYonghu)
		{
			FrmBianjiYonghu dlg = new FrmBianjiYonghu(this, "编辑用户", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunYonghu)
		{
			FrmChaxunYonghu dlg = new FrmChaxunYonghu(this, "查询用户", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaShengxianleibie)
		{
			FrmZengjiaShengxianleibie dlg = new FrmZengjiaShengxianleibie(this, "增加生鲜类别", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuShengxianleibie)
		{
			FrmShanchuShengxianleibie dlg = new FrmShanchuShengxianleibie(this, "删除生鲜类别", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiShengxianleibie)
		{
			FrmBianjiShengxianleibie dlg = new FrmBianjiShengxianleibie(this, "编辑生鲜类别", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunShengxianleibie)
		{
			FrmChaxunShengxianleibie dlg = new FrmChaxunShengxianleibie(this, "查询生鲜类别", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaShangpin)
		{
			FrmZengjiaShangpin dlg = new FrmZengjiaShangpin(this, "增加商品", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuShangpin)
		{
			FrmShanchuShangpin dlg = new FrmShanchuShangpin(this, "删除商品", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiShangpin)
		{
			FrmBianjiShangpin dlg = new FrmBianjiShangpin(this, "编辑商品", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunShangpin)
		{
			FrmChaxunShangpin dlg = new FrmChaxunShangpin(this, "查询商品", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaYouhuiquan)
		{
			FrmZengjiaYouhuiquan dlg = new FrmZengjiaYouhuiquan(this, "增加优惠券", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuYouhuiquan)
		{
			FrmShanchuYouhuiquan dlg = new FrmShanchuYouhuiquan(this, "删除优惠券", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiYouhuiquan)
		{
			FrmBianjiYouhuiquan dlg = new FrmBianjiYouhuiquan(this, "编辑优惠券", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunYouhuiquan)
		{
			FrmChaxunYouhuiquan dlg = new FrmChaxunYouhuiquan(this, "查询优惠券", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaManzhe)
		{
			FrmZengjiaManzhe dlg = new FrmZengjiaManzhe(this, "增加满折", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuManzhe)
		{
			FrmShanchuManzhe dlg = new FrmShanchuManzhe(this, "删除满折", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiManzhe)
		{
			FrmBianjiManzhe dlg = new FrmBianjiManzhe(this, "编辑满折", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunManzhe)
		{
			FrmChaxunManzhe dlg = new FrmChaxunManzhe(this, "查询满折", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaXianshicuxiao)
		{
			FrmZengjiaXianshicuxiao dlg = new FrmZengjiaXianshicuxiao(this, "增加限时促销", true);
			dlg.setVisible(true);
		}
	}
	
	public FrmMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("管理员管理系统");
		FrmLogin dlgLogin=null;
		dlgLogin = new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
		
		this.menu_yonghu.add(this.menuItem_ZengjiaYonghu);this.menuItem_ZengjiaYonghu.addActionListener(this);
		this.menu_yonghu.add(this.menuItem_ShanchuYonghu);this.menuItem_ShanchuYonghu.addActionListener(this);
		this.menu_yonghu.add(this.menuItem_BianjiYonghu);this.menuItem_BianjiYonghu.addActionListener(this);
		this.menu_yonghu.add(this.menuItem_ChaxunYonghu);this.menuItem_ChaxunYonghu.addActionListener(this);
		this.menu_shengxianleibie.add(this.menuItem_ZengjiaShengxianleibie);this.menuItem_ZengjiaShengxianleibie.addActionListener(this);
		this.menu_shengxianleibie.add(this.menuItem_ShanchuShengxianleibie);this.menuItem_ShanchuShengxianleibie.addActionListener(this);
		this.menu_shengxianleibie.add(this.menuItem_BianjiShengxianleibie);this.menuItem_BianjiShengxianleibie.addActionListener(this);
		this.menu_shengxianleibie.add(this.menuItem_ChaxunShengxianleibie);this.menuItem_ChaxunShengxianleibie.addActionListener(this);
		this.menu_shangpin.add(this.menuItem_ZengjiaShangpin);this.menuItem_ZengjiaShangpin.addActionListener(this);
		this.menu_shangpin.add(this.menuItem_ShanchuShangpin);this.menuItem_ShanchuShangpin.addActionListener(this);
		this.menu_shangpin.add(this.menuItem_BianjiShangpin);this.menuItem_BianjiShangpin.addActionListener(this);
		this.menu_shangpin.add(this.menuItem_ChaxunShangpin);this.menuItem_ChaxunShangpin.addActionListener(this);
		this.menu_youhuiquan.add(this.menuItem_ZengjiaYouhuiquan);this.menuItem_ZengjiaYouhuiquan.addActionListener(this);
		this.menu_youhuiquan.add(this.menuItem_ShanchuYouhuiquan);this.menuItem_ShanchuYouhuiquan.addActionListener(this);
		this.menu_youhuiquan.add(this.menuItem_BianjiYouhuiquan);this.menuItem_BianjiYouhuiquan.addActionListener(this);
		this.menu_youhuiquan.add(this.menuItem_ChaxunYouhuiquan);this.menuItem_ChaxunYouhuiquan.addActionListener(this);
		this.menu_manzhe.add(this.menuItem_ZengjiaManzhe);this.menuItem_ZengjiaManzhe.addActionListener(this);
		this.menu_manzhe.add(this.menuItem_ShanchuManzhe);this.menuItem_ShanchuManzhe.addActionListener(this);
		this.menu_manzhe.add(this.menuItem_BianjiManzhe);this.menuItem_BianjiManzhe.addActionListener(this);
		this.menu_manzhe.add(this.menuItem_ChaxunManzhe);this.menuItem_ChaxunManzhe.addActionListener(this);
		this.menu_xianshicuxiao.add(this.menuItem_ZengjiaXianshicuxiao);this.menuItem_ZengjiaXianshicuxiao.addActionListener(this);
		this.menu_xianshicuxiao.add(this.menuItem_ShanchuXianshicuxiao);this.menuItem_ShanchuXianshicuxiao.addActionListener(this);
		this.menu_xianshicuxiao.add(this.menuItem_BianjiXianshicuxiao);this.menuItem_BianjiXianshicuxiao.addActionListener(this);
		this.menu_xianshicuxiao.add(this.menuItem_ChaxunXianshicuxiao);this.menuItem_ChaxunXianshicuxiao.addActionListener(this);
		this.menu_caipu.add(this.menuItem_ZengjiaCaipu);this.menuItem_ZengjiaCaipu.addActionListener(this);
		this.menu_caipu.add(this.menuItem_ShanchuCaipu);this.menuItem_ShanchuCaipu.addActionListener(this);
		this.menu_caipu.add(this.menuItem_BianjiCaipu);this.menuItem_BianjiCaipu.addActionListener(this);
		this.menu_caipu.add(this.menuItem_ChaxunCaipu);this.menuItem_ChaxunCaipu.addActionListener(this);
		this.menu_caigou.add(this.menuItem_ZengjiaCaigou);this.menuItem_ZengjiaCaigou.addActionListener(this);
		this.menu_caigou.add(this.menuItem_ShanchuCaigou);this.menuItem_ShanchuCaigou.addActionListener(this);
		this.menu_caigou.add(this.menuItem_BianjiCaigou);this.menuItem_BianjiCaigou.addActionListener(this);
		this.menu_caigou.add(this.menuItem_ChaxunCaigou);this.menuItem_ChaxunCaigou.addActionListener(this);
		this.menu_gengduo.add(this.menuItem_Xiugaimima);this.menuItem_Xiugaimima.addActionListener(this);
		
		menubar.add(menu_yonghu);
		menubar.add(menu_shengxianleibie);
		menubar.add(menu_shangpin);
		menubar.add(menu_youhuiquan);
		menubar.add(menu_manzhe);
		menubar.add(menu_xianshicuxiao);
		menubar.add(menu_caipu);
		menubar.add(menu_gengduo);
		
		this.setJMenuBar(menubar);
		
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
