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

public class FrmShanchuCaipu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btn = new JButton("ɾ��");
	private JLabel label1 = new JLabel("���ױ�ţ�");
	private JLabel label2 = new JLabel("�������ƣ�");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	
	public FrmShanchuCaipu(FrmMain f, String s, boolean b) {
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
			String caipubianhao = new String(this.edt1.getText());
			String caipumingcheng = new String(this.edt2.getText());
			try {
				BeanGuanliyuan.currentLoginGuanliyuan = Util.guanliyuanManager.ShanchuCaipu(caipubianhao, caipumingcheng);
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		}
	}
}
