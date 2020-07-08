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
    private JMenu menu_yonghu=new JMenu("�û�����");
    private JMenu menu_shengxianleibie=new JMenu("����������");
    private JMenu menu_shangpin=new JMenu("��Ʒ����");
    private JMenu menu_youhuiquan=new JMenu("�Ż�ȯ����");
    private JMenu menu_manzhe=new JMenu("���۹���");
    private JMenu menu_xianshicuxiao=new JMenu("��ʱ����");
    private JMenu menu_caipu=new JMenu("���׹���");
    private JMenu menu_caigou=new JMenu("�ɹ�����");
    private JMenu menu_gengduo=new JMenu("����");
    
    private JMenuItem  menuItem_ZengjiaYonghu=new JMenuItem("�����û�");
    private JMenuItem  menuItem_ShanchuYonghu=new JMenuItem("ɾ���û�");
    private JMenuItem  menuItem_BianjiYonghu=new JMenuItem("�༭�û�");
    private JMenuItem  menuItem_ChaxunYonghu=new JMenuItem("��ѯ�û�");
    private JMenuItem  menuItem_ZengjiaShengxianleibie=new JMenuItem("�����������");
    private JMenuItem  menuItem_ShanchuShengxianleibie=new JMenuItem("ɾ���������");
    private JMenuItem  menuItem_BianjiShengxianleibie=new JMenuItem("�༭�������");
    private JMenuItem  menuItem_ChaxunShengxianleibie=new JMenuItem("��ѯ�������");
    private JMenuItem  menuItem_ZengjiaShangpin=new JMenuItem("������Ʒ");
    private JMenuItem  menuItem_ShanchuShangpin=new JMenuItem("ɾ����Ʒ");
    private JMenuItem  menuItem_BianjiShangpin=new JMenuItem("�༭��Ʒ");
    private JMenuItem  menuItem_ChaxunShangpin=new JMenuItem("��ѯ��Ʒ");
    private JMenuItem  menuItem_ZengjiaYouhuiquan=new JMenuItem("�����Ż�ȯ");
    private JMenuItem  menuItem_ShanchuYouhuiquan=new JMenuItem("ɾ���Ż�ȯ");
    private JMenuItem  menuItem_BianjiYouhuiquan=new JMenuItem("�༭�Ż�ȯ");
    private JMenuItem  menuItem_ChaxunYouhuiquan=new JMenuItem("��ѯ�Ż�ȯ");
    private JMenuItem  menuItem_ZengjiaManzhe=new JMenuItem("��������");
    private JMenuItem  menuItem_ShanchuManzhe=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_BianjiManzhe=new JMenuItem("�༭����");
    private JMenuItem  menuItem_ChaxunManzhe=new JMenuItem("��ѯ����");
    private JMenuItem  menuItem_ZengjiaXianshicuxiao=new JMenuItem("������ʱ����");
    private JMenuItem  menuItem_ShanchuXianshicuxiao=new JMenuItem("ɾ����ʱ����");
    private JMenuItem  menuItem_BianjiXianshicuxiao=new JMenuItem("�༭��ʱ����");
    private JMenuItem  menuItem_ChaxunXianshicuxiao=new JMenuItem("��ѯ��ʱ����");
    private JMenuItem  menuItem_ZengjiaCaipu=new JMenuItem("���Ӳ���");
    private JMenuItem  menuItem_ShanchuCaipu=new JMenuItem("ɾ������");
    private JMenuItem  menuItem_BianjiCaipu=new JMenuItem("�༭����");
    private JMenuItem  menuItem_ChaxunCaipu=new JMenuItem("��ѯ����");
    private JMenuItem  menuItem_ZengjiaCaigou=new JMenuItem("���Ӳɹ�");
    private JMenuItem  menuItem_ShanchuCaigou=new JMenuItem("ɾ���ɹ�");
    private JMenuItem  menuItem_BianjiCaigou=new JMenuItem("�༭�ɹ�");
    private JMenuItem  menuItem_ChaxunCaigou=new JMenuItem("��ѯ�ɹ�");
    private JMenuItem  menuItem_Xiugaimima=new JMenuItem("�޸�����");
    
    private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == menuItem_ZengjiaYonghu)
		{
			FrmZengjiaYonghu dlg=new FrmZengjiaYonghu(this,"�����û�",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuYonghu)
		{
			FrmShanchuYonghu dlg = new FrmShanchuYonghu(this,"ɾ���û�",true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiYonghu)
		{
			FrmBianjiYonghu dlg = new FrmBianjiYonghu(this, "�༭�û�", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunYonghu)
		{
			FrmChaxunYonghu dlg = new FrmChaxunYonghu(this, "��ѯ�û�", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaShengxianleibie)
		{
			FrmZengjiaShengxianleibie dlg = new FrmZengjiaShengxianleibie(this, "�����������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuShengxianleibie)
		{
			FrmShanchuShengxianleibie dlg = new FrmShanchuShengxianleibie(this, "ɾ���������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiShengxianleibie)
		{
			FrmBianjiShengxianleibie dlg = new FrmBianjiShengxianleibie(this, "�༭�������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunShengxianleibie)
		{
			FrmChaxunShengxianleibie dlg = new FrmChaxunShengxianleibie(this, "��ѯ�������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaShangpin)
		{
			FrmZengjiaShangpin dlg = new FrmZengjiaShangpin(this, "������Ʒ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuShangpin)
		{
			FrmShanchuShangpin dlg = new FrmShanchuShangpin(this, "ɾ����Ʒ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiShangpin)
		{
			FrmBianjiShangpin dlg = new FrmBianjiShangpin(this, "�༭��Ʒ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunShangpin)
		{
			FrmChaxunShangpin dlg = new FrmChaxunShangpin(this, "��ѯ��Ʒ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaYouhuiquan)
		{
			FrmZengjiaYouhuiquan dlg = new FrmZengjiaYouhuiquan(this, "�����Ż�ȯ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuYouhuiquan)
		{
			FrmShanchuYouhuiquan dlg = new FrmShanchuYouhuiquan(this, "ɾ���Ż�ȯ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiYouhuiquan)
		{
			FrmBianjiYouhuiquan dlg = new FrmBianjiYouhuiquan(this, "�༭�Ż�ȯ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunYouhuiquan)
		{
			FrmChaxunYouhuiquan dlg = new FrmChaxunYouhuiquan(this, "��ѯ�Ż�ȯ", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaManzhe)
		{
			FrmZengjiaManzhe dlg = new FrmZengjiaManzhe(this, "��������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ShanchuManzhe)
		{
			FrmShanchuManzhe dlg = new FrmShanchuManzhe(this, "ɾ������", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_BianjiManzhe)
		{
			FrmBianjiManzhe dlg = new FrmBianjiManzhe(this, "�༭����", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ChaxunManzhe)
		{
			FrmChaxunManzhe dlg = new FrmChaxunManzhe(this, "��ѯ����", true);
			dlg.setVisible(true);
		}
		else if (e.getSource() == menuItem_ZengjiaXianshicuxiao)
		{
			FrmZengjiaXianshicuxiao dlg = new FrmZengjiaXianshicuxiao(this, "������ʱ����", true);
			dlg.setVisible(true);
		}
	}
	
	public FrmMain()
	{
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("����Ա����ϵͳ");
		FrmLogin dlgLogin=null;
		dlgLogin = new FrmLogin(this,"��½",true);
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
