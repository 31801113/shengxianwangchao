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
    private JMenu menu_gerenxinxiguanli=new JMenu("������Ϣ����");
    private JMenu menu_goumairukou=new JMenu("�������");
    private JMenu menu_pingjiaguanli=new JMenu("���۹���");
    private JMenu menu_gengduo=new JMenu("����");
    
    private JMenuItem  menuItem_Xiugaimima=new JMenuItem("�޸�����");
    private JMenuItem  menuItem_Bianjigerenxinxi=new JMenuItem("�༭������Ϣ");
    private JMenuItem  menuItem_Bianjipeisongdizhi=new JMenuItem("�༭���͵�ַ");
    private JMenuItem  menuItem_Chaxunshangpindingdan=new JMenuItem("��ѯ��Ʒ����");
    private JMenuItem  menuItem_Goumaishangpin=new JMenuItem("������Ʒ");
    private JMenuItem  menuItem_Chaxunyouhuiquan=new JMenuItem("��ѯ�Ż�ȯ");
    private JMenuItem  menuItem_Chaxunmanzhexinxi=new JMenuItem("��ѯ������Ϣ");
    private JMenuItem  menuItem_Chaxunxianshicuxiao=new JMenuItem("��ѯ��ʱ����");
    private JMenuItem  menuItem_Zengjiapingjia=new JMenuItem("��������");
    private JMenuItem  menuItem_Bianjipingjia=new JMenuItem("�༭����");
    private JMenuItem  menuItem_Shanchupingjia=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_Chengweihuiyuan=new JMenuItem("��Ϊ��Ա");
    
	private JPanel statusBar = new JPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_Xiugaimima)
		{
			FrmXiugaimima dlg=new FrmXiugaimima(this,"�޸�����",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Bianjigerenxinxi)
		{
			FrmBianjigerenxinxi dlg = new FrmBianjigerenxinxi(this, "�༭������Ϣ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_Bianjipeisongdizhi)
		{
			FrmBianjipeisongdizhi dlg = new FrmBianjipeisongdizhi(this, "�༭���͵�ַ", true);
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
	}
	
	public FrmYonghuMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("�û�ϵͳ");
		
		this.menu_gerenxinxiguanli.add(this.menuItem_Xiugaimima);this.menuItem_Xiugaimima.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Bianjigerenxinxi);this.menuItem_Bianjigerenxinxi.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Bianjipeisongdizhi);this.menuItem_Bianjipeisongdizhi.addActionListener(this);
		this.menu_gerenxinxiguanli.add(this.menuItem_Chaxunshangpindingdan);this.menuItem_Chaxunshangpindingdan.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Goumaishangpin);this.menuItem_Goumaishangpin.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunyouhuiquan);this.menuItem_Chaxunyouhuiquan.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunmanzhexinxi);this.menuItem_Chaxunmanzhexinxi.addActionListener(this);
		this.menu_goumairukou.add(this.menuItem_Chaxunxianshicuxiao);this.menuItem_Chaxunxianshicuxiao.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Zengjiapingjia);this.menuItem_Zengjiapingjia.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Bianjipingjia);this.menuItem_Bianjipingjia.addActionListener(this);
		this.menu_pingjiaguanli.add(this.menuItem_Shanchupingjia);this.menuItem_Shanchupingjia.addActionListener(this);
		this.menu_gengduo.add(this.menuItem_Chengweihuiyuan);this.menuItem_Chengweihuiyuan.addActionListener(this);
		
		menubar.add(menu_gerenxinxiguanli);
		menubar.add(menu_goumairukou);
		menubar.add(menu_pingjiaguanli);
		menubar.add(menu_gengduo);
		
		this.setJMenuBar(menubar);
		
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����!");//�޸ĳ�   ���ã�+��½�û���
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
