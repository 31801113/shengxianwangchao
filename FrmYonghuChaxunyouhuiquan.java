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

import model.BeanYouhuiquan;
import starter.Util;
import util.BaseException;

public class FrmYonghuChaxunyouhuiquan extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel statusBar = new JPanel();
	
	private Object tblYouhuiquanTitle[]=BeanYouhuiquan.tableTitles;
	private Object tblYouhuiquanData[][];
	DefaultTableModel tabYouhuiquanModel=new DefaultTableModel();
	private JTable dataTableYouhuiquan=new JTable(tabYouhuiquanModel);
	
	List<BeanYouhuiquan> allYouhuiquan=null;
	private void reloadYouhuiquanTable(){//这是测试数据，需要用实际数替换
		try {
			allYouhuiquan=Util.youhuiquanManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblYouhuiquanData =  new Object[allYouhuiquan.size()][BeanYouhuiquan.tableTitles.length];
		for(int i=0;i<allYouhuiquan.size();i++){
			for(int j=0;j<BeanYouhuiquan.tableTitles.length;j++)
				tblYouhuiquanData[i][j]=allYouhuiquan.get(i).getCell(j);
		}
		tabYouhuiquanModel.setDataVector(tblYouhuiquanData,tblYouhuiquanTitle);
		this.dataTableYouhuiquan.validate();
		this.dataTableYouhuiquan.repaint();
	}
	public FrmYonghuChaxunyouhuiquan(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableYouhuiquan), BorderLayout.CENTER);
	  
	    
	    this.reloadYouhuiquanTable();
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
