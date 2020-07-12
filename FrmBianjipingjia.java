package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmBianjipingjia extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("更新");
	private JLabel label1 = new JLabel("商品编号：");
	private JLabel label3 = new JLabel("评价内容：");
	private JLabel label5 = new JLabel("星级：");
	private JLabel label6 = new JLabel("照片：");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	private JTextField edt6 = new JTextField(20);
	
	public FrmBianjipingjia(FrmYonghuMain f, String s, boolean b) {
		// TODO Auto-generated constructor stub
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btn);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(label1);
		workPane.add(edt1);
		workPane.add(label3);
		workPane.add(edt3);
		workPane.add(label5);
		workPane.add(edt5);
		workPane.add(label6);
		workPane.add(edt6);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(330, 400);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		btn.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btn)
		{
			String shangpinbianhao = new String(this.edt1.getText());
			String pingjianeirong = new String(this.edt3.getText());
			String xingji = new String(this.edt5.getText());
			String zhaopian = new String(this.edt6.getText());
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.Bianjipingjia(shangpinbianhao, pingjianeirong, xingji, zhaopian);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
