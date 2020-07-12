package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import model.BeanGouwuche;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;
import util.BusinessException;

public class FrmChaxungouwuche extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_Ganligouwuche=new JMenu("�����ﳵ");
    
    private JMenuItem  menuItem_Quxiaogoumai=new JMenuItem("ȡ������");
    private JMenuItem  menuItem_Fukuangoumai=new JMenuItem("�����");
    private JMenuItem  menuItem_Xiugaishuliang=new JMenuItem("�޸�����");
    private JMenuItem  menuItem_Shuaxin=new JMenuItem("ˢ��");
    private JMenuItem  menuItem_Fanhui=new JMenuItem("����");
	private JPanel statusBar = new JPanel();
	
	private Object tblGouwucheTitle[]=BeanGouwuche.tableTitles;
	private Object tblGouwucheData[][];
	DefaultTableModel tabGouwucheModel=new DefaultTableModel();
	private JTable dataTableGouwuche=new JTable(tabGouwucheModel);

	List<BeanGouwuche> allGouwuche=null;
	private void reloadGouwucheTable(){//���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			allGouwuche=Util.gouwucheManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGouwucheData =  new Object[allGouwuche.size()][BeanGouwuche.tableTitles.length];
		for(int i=0;i<allGouwuche.size();i++){
			for(int j=0;j<BeanGouwuche.tableTitles.length;j++)
				tblGouwucheData[i][j]=allGouwuche.get(i).getCell(j);
		}
		tabGouwucheModel.setDataVector(tblGouwucheData,tblGouwucheTitle);
		this.dataTableGouwuche.validate();
		this.dataTableGouwuche.repaint();
	}
	public FrmChaxungouwuche(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("���ﳵ");
	    //�˵�
	    this.menu_Ganligouwuche.add(this.menuItem_Quxiaogoumai); this.menuItem_Quxiaogoumai.addActionListener(this);
	    this.menu_Ganligouwuche.add(this.menuItem_Fukuangoumai); this.menuItem_Fukuangoumai.addActionListener(this);
	    this.menu_Ganligouwuche.add(this.menuItem_Xiugaishuliang); this.menuItem_Xiugaishuliang.addActionListener(this);
	    this.menu_Ganligouwuche.add(this.menuItem_Shuaxin); this.menuItem_Shuaxin.addActionListener(this);
	    this.menu_Ganligouwuche.add(this.menuItem_Fanhui); this.menuItem_Fanhui.addActionListener(this);
	    
	    menubar.add(menu_Ganligouwuche);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableGouwuche), BorderLayout.WEST);
	    this.dataTableGouwuche.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmChaxungouwuche.this.dataTableGouwuche.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadGouwucheTable();
	    //״̬��
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����!");//�޸ĳ�   ���ã�+��½�û���
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
             }
        });
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_Quxiaogoumai){
			int i=FrmChaxungouwuche.this.dataTableGouwuche.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Util.yonghuManager.Quxiaogoumai(allGouwuche.get(i));
				this.setVisible(false);
				FrmChaxungouwuche dlg = new FrmChaxungouwuche();
				dlg.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if (e.getSource()==this.menuItem_Fukuangoumai)
		{
			if (allGouwuche.size() == 0)
			{
				JOptionPane.showMessageDialog(null,"���ﳵ��Ʒ������Ϊ0", "����",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			String songdashijian = JOptionPane.showInputDialog("�������ʹ�ʱ��");
			if (songdashijian == null || songdashijian.equals(""))
			{
				JOptionPane.showMessageDialog(null,"�ʹ�ʱ�䲻��Ϊ��", "����",JOptionPane.ERROR_MESSAGE);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Timestamp time = new Timestamp(format.parse(songdashijian).getTime());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				    e1.printStackTrace();
				    JOptionPane.showMessageDialog(null,"�ʹ�ʱ���ʽ����", "����",JOptionPane.ERROR_MESSAGE);
				}
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.Fukuangoumai(allGouwuche, songdashijian);
				this.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		else if (e.getSource() == this.menuItem_Xiugaishuliang)
		{
			int i=FrmChaxungouwuche.this.dataTableGouwuche.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				String shuliang = JOptionPane.showInputDialog("�������޸�����");
				Util.yonghuManager.Xiugaishuliang(allGouwuche.get(i), shuliang);
				this.setVisible(false);
				FrmChaxungouwuche dlg = new FrmChaxungouwuche();
				dlg.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if (e.getSource()==this.menuItem_Shuaxin)
		{
			this.setVisible(false);
			FrmChaxungouwuche dlg = new FrmChaxungouwuche();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.menuItem_Fanhui)
		{
			this.setVisible(false);
		}
	}
}
