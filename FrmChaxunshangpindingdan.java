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

import model.BeanShangpindingdan;
import starter.Util;
import util.BaseException;

public class FrmChaxunshangpindingdan extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel statusBar = new JPanel();
	
	private Object tblShangpindingdanTitle[]=BeanShangpindingdan.tableTitles;
	private Object tblShangpindingdanData[][];
	DefaultTableModel tabShangpindingdanModel=new DefaultTableModel();
	private JTable dataTableShangpindingdan=new JTable(tabShangpindingdanModel);
	
	List<BeanShangpindingdan> allShangpindingdan=null;
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
	public FrmChaxunshangpindingdan(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShangpindingdan), BorderLayout.CENTER);
	  
	    
	    this.reloadShangpindingdanTable();
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
