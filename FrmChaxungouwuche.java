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
import model.BeanShangpin;
import model.BeanShengxianleibie;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmChaxungouwuche extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_Ganligouwuche=new JMenu("�����ﳵ");
    
    private JMenuItem  menuItem_Quxiaogoumai=new JMenuItem("ȡ������");
    private JMenuItem  menuItem_Fukuangoumai=new JMenuItem("�����");
	private JPanel statusBar = new JPanel();
	
	private Object tblGouwucheTitle[]=BeanGouwuche.tableTitles;
	private Object tblGouwucheData[][];
	DefaultTableModel tabGouwucheModel=new DefaultTableModel();
	private JTable dataTableGouwuche=new JTable(tabGouwucheModel);

	
	private BeanGouwuche curGouwuche=null;
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
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if (e.getSource()==this.menuItem_Fukuangoumai)
		{
			String songdashijian = JOptionPane.showInputDialog("�������ʹ�ʱ��");
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.Fukuangoumai(allGouwuche, songdashijian);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
