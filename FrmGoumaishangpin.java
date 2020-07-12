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

import org.jboss.logging.DelegatingBasicLogger;

import model.BeanShangpin;
import model.BeanShengxianleibie;
import starter.Util;
import util.BaseException;

public class FrmGoumaishangpin extends JFrame implements ActionListener {
	private JMenuBar menubar=new JMenuBar();
    private JMenu menu_Goumaishangpin=new JMenu("购买商品");
    
    private JMenuItem  menuItem_Jiarugouwuche=new JMenuItem("加入购物车");
    private JMenuItem  menuItem_Chakangouwuche=new JMenuItem("查看购物车");
    private JMenuItem  menuItem_Fanhui=new JMenuItem("返回");
	private JPanel statusBar = new JPanel();
	
	private Object tblShengxianleibieTitle[]=BeanShengxianleibie.tableTitles;
	private Object tblShengxianleibieData[][];
	DefaultTableModel tabShengxianleibieModel=new DefaultTableModel();
	private JTable dataTableShengxianleibie=new JTable(tabShengxianleibieModel);
	
	
	private Object tblShangpinTitle[]=BeanShangpin.tableTitles;
	private Object tblShangpinData[][];
	DefaultTableModel tabShangpinModel=new DefaultTableModel();
	private JTable dataTableShangpin=new JTable(tabShangpinModel);
	
	private BeanShengxianleibie curShengxianleibie=null;
	List<BeanShengxianleibie> allShengxianleibie=null;
	List<BeanShangpin> planShangpin=null;
	private void reloadShengxianleibieTable(){//这是测试数据，需要用实际数替换
		try {
			allShengxianleibie=Util.shengxianleibieManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShengxianleibieData =  new Object[allShengxianleibie.size()][BeanShengxianleibie.tableTitles.length];
		for(int i=0;i<allShengxianleibie.size();i++){
			for(int j=0;j<BeanShengxianleibie.tableTitles.length;j++)
				tblShengxianleibieData[i][j]=allShengxianleibie.get(i).getCell(j);
		}
		tabShengxianleibieModel.setDataVector(tblShengxianleibieData,tblShengxianleibieTitle);
		this.dataTableShengxianleibie.validate();
		this.dataTableShengxianleibie.repaint();
	}
	private void reloadPlanShangpinTabel(int planIdx){
		if(planIdx<0) return;
		curShengxianleibie=allShengxianleibie.get(planIdx);
		try {
			planShangpin=Util.shangpinManager.loadShangpin(curShengxianleibie);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShangpinData =new Object[planShangpin.size()][BeanShangpin.tableTitles.length];
		for(int i=0;i<planShangpin.size();i++){
			for(int j=0;j<BeanShangpin.tableTitles.length;j++)
				tblShangpinData[i][j]=planShangpin.get(i).getCell(j);
		}
		
		tabShangpinModel.setDataVector(tblShangpinData,tblShangpinTitle);
		this.dataTableShangpin.validate();
		this.dataTableShangpin.repaint();
	}
	public FrmGoumaishangpin(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("用户购买");
	    //菜单
	    this.menu_Goumaishangpin.add(this.menuItem_Jiarugouwuche); this.menuItem_Jiarugouwuche.addActionListener(this);
	    this.menu_Goumaishangpin.add(this.menuItem_Chakangouwuche); this.menuItem_Chakangouwuche.addActionListener(this);
	    this.menu_Goumaishangpin.add(this.menuItem_Fanhui); this.menuItem_Fanhui.addActionListener(this);
	    
	    menubar.add(menu_Goumaishangpin);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShengxianleibie), BorderLayout.WEST);
	    this.dataTableShengxianleibie.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmGoumaishangpin.this.dataTableShengxianleibie.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmGoumaishangpin.this.reloadPlanShangpinTabel(i);
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableShangpin), BorderLayout.CENTER);
	    
	    this.reloadShengxianleibieTable();
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
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
		if(e.getSource()==this.menuItem_Jiarugouwuche){
			int i=FrmGoumaishangpin.this.dataTableShangpin.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				String shuliang = JOptionPane.showInputDialog("请输入购买的数量");
				Util.yonghuManager.Jiarugouwuche(this.planShangpin.get(i), shuliang);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if (e.getSource()==this.menuItem_Chakangouwuche)
		{
			FrmChaxungouwuche dlg = new FrmChaxungouwuche();
			dlg.setVisible(true);
		}
		else if (e.getSource()==this.menuItem_Fanhui)
		{
			this.setVisible(false);
		}
	}
}
