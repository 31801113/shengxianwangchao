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

import model.BeanDingdanxiangqing;
import model.BeanShangpindingdan;
import starter.Util;
import util.BaseException;

public class FrmChaxunshangpindingdan extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JMenu menu_Tuichu=new JMenu("退出");
	private JMenuItem  menuItem_Tuichu=new JMenuItem("退出");
	private JPanel statusBar = new JPanel();
	
	private Object tblShangpindingdanTitle[]=BeanShangpindingdan.tableTitles;
	private Object tblShangpindingdanData[][];
	DefaultTableModel tabShangpindingdanModel=new DefaultTableModel();
	private JTable dataTableShangpindingdan=new JTable(tabShangpindingdanModel);
	
	private Object tblDingdanxiangqingTitle[]=BeanDingdanxiangqing.tableTitles;
	private Object tblDingdanxiangqingData[][];
	DefaultTableModel tabDingdanxiangqingModel=new DefaultTableModel();
	private JTable dataTableDingdanxiangqing=new JTable(tabDingdanxiangqingModel);
	
	private BeanShangpindingdan curShangpindingdan=null;
	List<BeanShangpindingdan> allShangpindingdan=null;
	List<BeanDingdanxiangqing> planDingdanxiangqing=null;
	private void reloadShangpindingdanTable(){//这是测试数据，需要用实际数替换
		try {
			allShangpindingdan=Util.ShangpindingdanManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShangpindingdanData =  new Object[allShangpindingdan.size()][BeanShangpindingdan.tableTitles.length];
		for(int i=0;i<allShangpindingdan.size();i++){
			for(int j=0;j<BeanShangpindingdan.tableTitles.length;j++)
				tblShangpindingdanData[i][j]=allShangpindingdan.get(i).getCell(j);
		}
		tabShangpindingdanModel.setDataVector(tblShangpindingdanData,tblShangpindingdanTitle);
		this.dataTableShangpindingdan.validate();
		this.dataTableShangpindingdan.repaint();
	}
	private void reloadPlanDingdanxiangqingTabel(int planIdx){
		if(planIdx<0) return;
		curShangpindingdan=allShangpindingdan.get(planIdx);
		try {
			planDingdanxiangqing=Util.dingdanxiangqingManager.loadDingdanxiangqing(curShangpindingdan);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDingdanxiangqingData =new Object[planDingdanxiangqing.size()][BeanDingdanxiangqing.tableTitles.length];
		for(int i=0;i<planDingdanxiangqing.size();i++){
			for(int j=0;j<BeanDingdanxiangqing.tableTitles.length;j++)
				tblDingdanxiangqingData[i][j]=planDingdanxiangqing.get(i).getCell(j);
		}
		
		tabDingdanxiangqingModel.setDataVector(tblDingdanxiangqingData,tblDingdanxiangqingTitle);
		this.dataTableDingdanxiangqing.validate();
		this.dataTableDingdanxiangqing.repaint();
	}
	public FrmChaxunshangpindingdan(){
		this.menu_Tuichu.add(this.menuItem_Tuichu); this.menuItem_Tuichu.addActionListener(this);
		 menubar.add(menu_Tuichu);
		    this.setJMenuBar(menubar);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShangpindingdan), BorderLayout.WEST);
	    this.dataTableShangpindingdan.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmChaxunshangpindingdan.this.dataTableShangpindingdan.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmChaxunshangpindingdan.this.reloadPlanDingdanxiangqingTabel(i);
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableDingdanxiangqing), BorderLayout.CENTER);
	    
	    this.reloadShangpindingdanTable();
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.menuItem_Tuichu)
		{
			this.setVisible(false);
		}
	}
}
