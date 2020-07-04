package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.BaseException;

public class FrmMain extends JFrame implements ActionListener{
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public FrmMain()
	{
		// ���� JFrame ʵ��
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ������壬��������� HTML �� div ��ǩ
         * ���ǿ��Դ��������岢�� JFrame ��ָ��λ��
         * ��������ǿ�������ı��ֶΣ���ť�����������
         */
        JPanel panel = new JPanel();    
        // ������
        frame.add(panel);
        /* 
         * �����û�����ķ����������������
         */
        placeComponents(panel);

        // ���ý���ɼ�
        frame.setVisible(true);
	}
	 private static void placeComponents(JPanel panel) {

	        /* ���ֲ���������߲���������
	         * ������ò���Ϊ null
	         */
	        panel.setLayout(null);

	        // ���� JLabel
	        JLabel userLabel = new JLabel("�û�:");
	        /* ������������������λ�á�
	         * setBounds(x, y, width, height)
	         * x �� y ָ�����Ͻǵ���λ�ã��� width �� height ָ���µĴ�С��
	         */
	        userLabel.setBounds(10,20,80,25);
	        panel.add(userLabel);

	        /* 
	         * �����ı��������û�����
	         */
	        JTextField userText = new JTextField(20);
	        userText.setBounds(100,20,165,25);
	        panel.add(userText);

	        // ����������ı���
	        JLabel passwordLabel = new JLabel("����:");
	        passwordLabel.setBounds(10,50,80,25);
	        panel.add(passwordLabel);

	        /* 
	         *�����������������ı���
	         * �����������Ϣ���Ե�Ŵ��棬���ڰ�������İ�ȫ��
	         */
	        JPasswordField passwordText = new JPasswordField(20);
	        passwordText.setBounds(100,50,165,25);
	        panel.add(passwordText);

	        // ������¼��ť
	        JButton loginButton = new JButton("login");
	        loginButton.setBounds(10, 80, 80, 25);
	        panel.add(loginButton);
	    }
}
