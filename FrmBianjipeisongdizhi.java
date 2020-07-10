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

public class FrmBianjipeisongdizhi extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("更新");
	private JLabel label1 = new JLabel("省：");
	private JLabel label2 = new JLabel("市：");
	private JLabel label3 = new JLabel("区：");
	private JLabel label4 = new JLabel("地址：");
	private JLabel label5 = new JLabel("联系人：");
	private JLabel label6 = new JLabel("电话：");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	private JTextField edt6 = new JTextField(20);
	
	public FrmBianjipeisongdizhi(FrmYonghuMain f, String s, boolean b) {
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
		workPane.add(label6);
		workPane.add(edt6);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(270, 400);
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
			String sheng = new String(this.edt1.getText());
			String shi = new String(this.edt2.getText());
			String qu = new String(this.edt3.getText());
			String dizhi = new String(this.edt4.getText());
			String lianxiren = new String(this.edt5.getText());
			String dianhua = new String(this.edt6.getText());
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.Bianjipeisongdizhi(sheng, shi, qu, dizhi, lianxiren, dianhua);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
   }
}
