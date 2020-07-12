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

import model.BeanShangpinpingjia;
import model.BeanYouhuiquan;
import starter.Util;
import util.BaseException;

public class FrmChakanpingjia extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel statusBar = new JPanel();
	
	private Object tblShangpinpingjiaTitle[]=BeanShangpinpingjia.tableTitles;
	private Object tblShangpinpingjiaData[][];
	DefaultTableModel tabShangpinpingjiaModel=new DefaultTableModel();
	private JTable dataTableShangpinpingjia=new JTable(tabShangpinpingjiaModel);
	
	List<BeanShangpinpingjia> allShangpinpingjia=null;
	private void reloadShangpinpingjiaTable(){//这是测试数据，需要用实际数替换
		try {
			allShangpinpingjia=Util.shangpinpingjiaManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShangpinpingjiaData =  new Object[allShangpinpingjia.size()][BeanShangpinpingjia.tableTitles.length];
		for(int i=0;i<allShangpinpingjia.size();i++){
			for(int j=0;j<BeanShangpinpingjia.tableTitles.length;j++)
				tblShangpinpingjiaData[i][j]=allShangpinpingjia.get(i).getCell(j);
		}
		tabShangpinpingjiaModel.setDataVector(tblShangpinpingjiaData,tblShangpinpingjiaTitle);
		this.dataTableShangpinpingjia.validate();
		this.dataTableShangpinpingjia.repaint();
	}
	public FrmChakanpingjia(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("查询结果");
	    //菜单
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShangpinpingjia), BorderLayout.CENTER);
	  
	    
	    this.reloadShangpinpingjiaTable();
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
