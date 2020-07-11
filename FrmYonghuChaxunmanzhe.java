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

import model.BeanManzhe;
import starter.Util;
import util.BaseException;

public class FrmYonghuChaxunmanzhe extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel statusBar = new JPanel();
	
	private Object tblManzheTitle[]=BeanManzhe.tableTitles;
	private Object tblManzheData[][];
	DefaultTableModel tabManzheModel=new DefaultTableModel();
	private JTable dataTableManzhe=new JTable(tabManzheModel);
	
	List<BeanManzhe> allManzhe=null;
	private void reloadManzheTable(){//这是测试数据，需要用实际数替换
		try {
			allManzhe=Util.manzheManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblManzheData =  new Object[allManzhe.size()][BeanManzhe.tableTitles.length];
		for(int i=0;i<allManzhe.size();i++){
			for(int j=0;j<BeanManzhe.tableTitles.length;j++)
				tblManzheData[i][j]=allManzhe.get(i).getCell(j);
		}
		tabManzheModel.setDataVector(tblManzheData,tblManzheTitle);
		this.dataTableManzhe.validate();
		this.dataTableManzhe.repaint();
	}
	public FrmYonghuChaxunmanzhe(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableManzhe), BorderLayout.CENTER);
	  
	    
	    this.reloadManzheTable();
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
