package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.BeanGuanliyuan;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmYonghuRegister extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	private JLabel label1 = new JLabel("用户编号：");
	private JLabel label2 = new JLabel("姓名：");
	private JLabel label3 = new JLabel("性别：");
	private JLabel label4 = new JLabel("密码：");
	private JLabel label5 = new JLabel("确认密码：");
	private JLabel label6 = new JLabel("手机号码：");
	private JLabel label7 = new JLabel("邮箱：");
	private JLabel label8 = new JLabel("所在城市：");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	private JTextField edt6 = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmYonghuRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(label1);
		workPane.add(edt1);
		workPane.add(label2);
		workPane.add(edt2);
		workPane.add(label3);
		workPane.add(edt3);
		workPane.add(label4);
		workPane.add(edtPwd);
		workPane.add(label5);
		workPane.add(edtPwd2);
		workPane.add(label6);
		workPane.add(edt4);
		workPane.add(label7);
		workPane.add(edt5);
		workPane.add(label8);
		workPane.add(edt6);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 550);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String yonghubianhao=new String(this.edt1.getText());
			String xingming = new String(this.edt2.getText());
			String xingbie = new String(this.edt3.getText());
			String shoujihaoma = new String(this.edt4.getText());
			String youxiang = new String(this.edt5.getText());
			String suozaichengshi = new String(this.edt6.getText());
			String mima=new String(this.edtPwd.getPassword());
			String querenmima=new String(this.edtPwd2.getPassword());
			try {
				BeanYonghu user=Util.yonghuManager.reg(yonghubianhao, xingming, xingbie, mima, querenmima, shoujihaoma, youxiang, suozaichengshi, "0", null);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}
}
