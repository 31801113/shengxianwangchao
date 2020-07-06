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
import starter.Util;
import util.BaseException;

public class FrmBianjiYonghu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("����");
	private JLabel label1 = new JLabel("�û���ţ�");
	private JLabel label2 = new JLabel("������");
	private JLabel label3 = new JLabel("�Ա�");
	private JLabel label4 = new JLabel("���룺");
	private JLabel label5 = new JLabel("�ֻ����룺");
	private JLabel label6 = new JLabel("���䣺");
	private JLabel label7 = new JLabel("���ڳ��У�");
	private JLabel label9 = new JLabel("�Ƿ��Ա��");
	private JLabel label10 = new JLabel("��ֹʱ�䣺");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	private JTextField edt6 = new JTextField(20);
	private JTextField edt7 = new JTextField(20);
	private JTextField edt9 = new JTextField(20);
	private JTextField edt10 = new JTextField(20);
	
	public FrmBianjiYonghu(FrmMain f, String s, boolean b) {
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
		workPane.add(label7);
		workPane.add(edt7);
		workPane.add(label9);
		workPane.add(edt9);
		workPane.add(label10);
		workPane.add(edt10);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 400);
		// ��Ļ������ʾ
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
			String yonghubianhao = new String(this.edt1.getText());
			String xingming = new String(this.edt2.getText());
			String xingbie = new String(this.edt3.getText());
			String mima = new String(this.edt4.getText());
			String shoujihaoma = new String(this.edt5.getText());
			String youxiang = new String(this.edt6.getText());
			String suozaichengshi = new String(this.edt7.getText());
			String shifouhuiyuan =  new String(this.edt9.getText());
			String huiyuanjiezhishijian = new String(this.edt10.getText());
			try {
				BeanGuanliyuan.currentLoginGuanliyuan = Util.guanliyuanManager.BianjiYonghu(yonghubianhao, xingming, xingbie, mima, shoujihaoma, youxiang, suozaichengshi, shifouhuiyuan, huiyuanjiezhishijian);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
   }
}
