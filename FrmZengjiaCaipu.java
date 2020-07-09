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

public class FrmZengjiaCaipu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("Ôö¼Ó");
	private JLabel label1 = new JLabel("²ËÆ×±àºÅ£º");
	private JLabel label2 = new JLabel("²ËÆ×Ãû³Æ£º");
	private JLabel label3 = new JLabel("²ËÆ×ÓÃÁÏ£º");
	private JLabel label4 = new JLabel("²½Öè£º");
	private JLabel label5 = new JLabel("Í¼Æ¬£º");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	
	public FrmZengjiaCaipu(FrmMain f, String s, boolean b) {
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
		this.setSize(280, 400);
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
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
			String caipubianhao = new String(this.edt1.getText());
			String caipumingcheng = new String(this.edt2.getText());
			String caipuyongliao = new String(this.edt3.getText());
			String buzhou = new String(this.edt4.getText());
			String tupian = new String(this.edt5.getText());
			try {
				BeanGuanliyuan.currentLoginGuanliyuan = Util.guanliyuanManager.ZengjiaCaipu(caipubianhao, caipumingcheng, caipuyongliao, buzhou, tupian);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
