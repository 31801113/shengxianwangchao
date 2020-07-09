package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FrmYonghuMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_gerenxinxiguanli=new JMenu("个人信息管理");
    private JMenu menu_goumairukou=new JMenu("购买入口");
    private JMenu menu_gengduo=new JMenu("更多");
    
    private JMenuItem  menuItem_Xiugaimima=new JMenuItem("修改密码");
    private JMenuItem  menuItem_Bianjigerenxinxi=new JMenuItem("编辑个人信息");
    private JMenuItem  menuItem_Bianjipeisongdizhi=new JMenuItem("编辑配送地址");
    private JMenuItem  menuItem_Chaxunshangpindingdan=new JMenuItem("查询商品订单");
    private JMenuItem  menuItem_Goumaishangpin=new JMenuItem("购买商品");
    private JMenuItem  menuItem_Chaxunyouhuiquan=new JMenuItem("查询优惠券");
    private JMenuItem  menuItem_Chaxunmanzhexinxi=new JMenuItem("查询满折信息");
    private JMenuItem  menuItem_Chaxunxianshicuxiao=new JMenuItem("查询限时促销");
    private JMenuItem  menuItem_Chengweihuiyuan=new JMenuItem("成为会员");
    
	private JPanel statusBar = new JPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_Xiugaimima)
		{
			FrmXiugaimima dlg=new FrmXiugaimima(this,"修改密码",true);
			dlg.setVisible(true);
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
		this.menu_goumairukou.add(this.menuItem_Goumaishangpin);this.menuItem_Goumaishangpin.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunyouhuiquan);this.menuItem_Chaxunyouhuiquan.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunmanzhexinxi);this.menuItem_Chaxunmanzhexinxi.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunxianshicuxiao);this.menuItem_Chaxunxianshicuxiao.addActionListener(this);
		this.menu_gengduo.add(this.menuItem_Chengweihuiyuan);this.menuItem_Chengweihuiyuan.addActionListener(this);
		
		menubar.add(menu_gerenxinxiguanli);
		menubar.add(menu_goumairukou);
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
