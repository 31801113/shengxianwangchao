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

public class FrmShanchuXianshicuxiao extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("É¾³ý");
	private JLabel label1 = new JLabel("´ÙÏú±àºÅ£º");
	private JLabel label2 = new JLabel("ÉÌÆ·±àºÅ£º");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	
	public FrmShanchuXianshicuxiao(FrmMain f, String s, boolean b) {
		// TODO Auto-generated constructor stub
		super(f,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btn);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(label1);
		workPane.add(edt1);
		workPane.add(label2);
		workPane.add(edt2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 200);
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
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
			String cuxiaobianhao = new String(this.edt1.getText());
			String shangpinbianhao = new String(this.edt2.getText());
			try {
				BeanGuanliyuan.currentLoginGuanliyuan = Util.guanliyuanManager.ShanchuXianshicuxiao(cuxiaobianhao, shangpinbianhao);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
