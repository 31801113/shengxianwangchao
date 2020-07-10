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

import model.BeanGuanliyuan;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmBianjigerenxinxi extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("更新");
	private JLabel label1 = new JLabel("姓名：");
	private JLabel label2 = new JLabel("性别：");
	private JLabel label3 = new JLabel("手机号：");
	private JLabel label4 = new JLabel("邮箱：");
	private JLabel label5 = new JLabel("所在城市：");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	
	public FrmBianjigerenxinxi(FrmYonghuMain f, String s, boolean b) {
		// TODO Auto-generated constructor stub
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btn);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(label1);
		workPane.add(edt1);
		workPane.add(label2);
		workPane.add(edt2);
		workPane.add(label3);
		workPane.add(edt3);
		workPane.add(label4);
		workPane.add(edt4);
		workPane.add(label5);
		workPane.add(edt5);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 400);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		btn.addActionListener(this);
		
		}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btn)
		{
			String xingming = new String(this.edt1.getText());
			String xingbie = new String(this.edt2.getText());
			String shoujihao = new String(this.edt3.getText());
			String youxiang = new String(this.edt4.getText());
			String suozaichengshi = new String(this.edt5.getText());
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.Bianjigerenxinxi(xingming, xingbie, shoujihao, youxiang, suozaichengshi);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
   }
}
