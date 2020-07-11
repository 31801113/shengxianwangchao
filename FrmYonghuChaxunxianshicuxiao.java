package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.BeanXianshicuxiao;
import starter.Util;
import util.BaseException;

public class FrmYonghuChaxunxianshicuxiao extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel statusBar = new JPanel();
	
	private Object tblXianshicuxiaoTitle[]=BeanXianshicuxiao.tableTitles;
	private Object tblXianshicuxiaoData[][];
	DefaultTableModel tabXianshicuxiaoModel=new DefaultTableModel();
	private JTable dataTableXianshicuxiao=new JTable(tabXianshicuxiaoModel);
	
	List<BeanXianshicuxiao> allXianshicuxiao=null;
	private void reloadXianshicuxiaoTable(){//这是测试数据，需要用实际数替换
		try {
			allXianshicuxiao=Util.xianshicuxiaoManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblXianshicuxiaoData =  new Object[allXianshicuxiao.size()][BeanXianshicuxiao.tableTitles.length];
		for(int i=0;i<allXianshicuxiao.size();i++){
			for(int j=0;j<BeanXianshicuxiao.tableTitles.length;j++)
				tblXianshicuxiaoData[i][j]=allXianshicuxiao.get(i).getCell(j);
		}
		tabXianshicuxiaoModel.setDataVector(tblXianshicuxiaoData,tblXianshicuxiaoTitle);
		this.dataTableXianshicuxiao.validate();
		this.dataTableXianshicuxiao.repaint();
	}
	public FrmYonghuChaxunxianshicuxiao(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableXianshicuxiao), BorderLayout.CENTER);
	  
	    
	    this.reloadXianshicuxiaoTable();
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!");//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
