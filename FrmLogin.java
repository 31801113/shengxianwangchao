package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.FormatFlagsConversionMismatchException;
import java.util.function.Predicate;

import javax.security.auth.login.FailedLoginException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import model.BeanGuanliyuan;
import model.BeanYonghu;
import starter.Util;
import util.BaseException;

public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("µÇÂ½");
	private JButton btnCancel = new JButton("ÍË³ö");
	private JButton btnRegister = new JButton("×¢²á");
	private JRadioButton radioBtn01 = new JRadioButton("¹ÜÀíÔ±");
    private JRadioButton radioBtn02 = new JRadioButton("ÓÃ»§");
    private ButtonGroup btnGroup = new ButtonGroup();
	private JLabel labelUser = new JLabel("ÕËºÅ£º");
	private JLabel labelPwd = new JLabel("ÃÜÂë£º");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	public int flag1 = 1;
	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		radioBtn01.setEnabled(true);
		radioBtn02.setEnabled(true);
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		btnGroup.add(radioBtn01);
		btnGroup.add(radioBtn02);
		radioBtn01.setSelected(true);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(radioBtn01);
		workPane.add(radioBtn02);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 200);
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public int getflag()
	{
		return this.flag1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin && radioBtn01.isSelected()) {
			String yuangongbianhao=new String(this.edtUserId.getText());
			String denglumima=new String(this.edtPwd.getPassword());
			try {
				BeanGuanliyuan.currentLoginGuanliyuan= Util.guanliyuanManager.login(yuangongbianhao, denglumima);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister && radioBtn01.isSelected()){
			FrmRegister dlg=new FrmRegister(this,"×¢²á",true);
			dlg.setVisible(true);
		}else if (e.getSource() == this.btnLogin && radioBtn02.isSelected()) {
			flag1 = 0;
			String yonghubianhao=new String(this.edtUserId.getText());
			String mima=new String(this.edtPwd.getPassword());
			try {
				BeanYonghu.currentLoginYonghu= Util.yonghuManager.login(yonghubianhao, mima);
				FrmYonghuMain dlg = new FrmYonghuMain();
				dlg.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
	     }
		else if(e.getSource()==this.btnRegister && radioBtn02.isSelected()){
			FrmYonghuRegister dlg=new FrmYonghuRegister(this,"×¢²á",true);
			dlg.setVisible(true);
		}
		
         }
}
