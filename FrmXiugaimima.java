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

public class FrmXiugaimima extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("∏¸–¬");
	private JLabel label1 = new JLabel("æ…√‹¬Î£∫");
	private JLabel label2 = new JLabel("–¬√‹¬Î£∫");
	private JLabel label3 = new JLabel("»∑»œ√‹¬Î£∫");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	
	public FrmXiugaimima(FrmYonghuMain f, String s, boolean b) {
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
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(290, 400);
		// ∆¡ƒªæ”÷–œ‘ æ
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
			String jiumima = new String(this.edt1.getText());
			String xinmima = new String(this.edt2.getText());
			String querenmima = new String(this.edt3.getText());
			try {
				BeanYonghu.currentLoginYonghu = Util.yonghuManager.changePwd(BeanYonghu.currentLoginYonghu, jiumima, xinmima, querenmima);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
   }
}
