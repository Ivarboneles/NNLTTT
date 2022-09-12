package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.NhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class FormDangNhap extends JFrame{

	private static final long serialVersionUID = -3100006640859757983L;
	
	JTextField tfUsername = new JTextField();
	JPasswordField tfPassword = new JPasswordField();
	
	int flag = 0;
	
	public FormDangNhap() {
		
		
		//------- Tilte --------//
		JLabel lbLogin = new JLabel("L I L I E S");
		lbLogin.setBounds(130,80,180,30);
		lbLogin.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 32));
		lbLogin.setForeground(Color.white);
		
		add(lbLogin);
		
		//-------- Login form ------// 
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setFont(new Font("SVN-Franko", Font.BOLD, 16));
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("SVN-Franko", Font.BOLD, 16));
		
		lbUsername.setForeground(Color.white);
		lbPassword.setForeground(Color.white);
		
		lbUsername.setBounds(70,130,80,30);
		lbPassword.setBounds(70,170,80,30);
		
		
		tfUsername.setBounds(150,135,150,30);
		tfPassword.setBounds(150,175,150,30);
		
		add(tfUsername);
		add(tfPassword);
		
		add(lbUsername);
		add(lbPassword);
		
		//----- check box ----//
		
		JCheckBox cbAdmin = new JCheckBox("Admin");
		JCheckBox cbNhanVien = new JCheckBox("Employee");
		
		cbAdmin.setFont(new Font("SVN-Franko", Font.BOLD, 12));
		cbNhanVien.setFont(new Font("SVN-Franko", Font.BOLD, 12));
		
		cbAdmin.setForeground(Color.white);
		cbNhanVien.setForeground(Color.white);
		
		cbAdmin.setBounds(100,210,80,30);
		cbNhanVien.setBounds(180,210,100,30);
		
		add(cbAdmin);
		add(cbNhanVien);
		
		//-------- Button -------//
		JButton btnLogin = new JButton("LOGIN");
		
		btnLogin.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		
		btnLogin.setBounds(140,250,100,33);
		
		add(btnLogin);
		
		//------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 400, 400);
		
		ImageIcon avtstop =new ImageIcon(new ImageIcon(this.getClass().getResource("/background.jpeg")).getImage().getScaledInstance(labeltong.getWidth(),labeltong.getHeight(), Image.SCALE_SMOOTH));
		
		labeltong.setIcon(avtstop);
		
		add(labeltong);
		
		setTitle("Form Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setLayout(null);
		setVisible(true);
		
		
		//---------- Event --------// 
		
		cbAdmin.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = 1;
				cbNhanVien.setSelected(false);
			}
		});
		
		cbNhanVien.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = 2;
				cbAdmin.setSelected(false);
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String Username = tfUsername.getText();
				String Password = String.valueOf(tfPassword.getPassword());
				NhanVien nv;
				try {
					nv = NhanVien.getbyUsername(Username, Password);
					
					if(nv != null) {
						if(flag == 0) {
							JOptionPane.showMessageDialog(labeltong, "Vui lòng chọn quyền đăng nhập !!!");
						}else if(flag == 1) {
								if(nv.getChucvu().getMaCV() == 1) {
									JOptionPane.showMessageDialog(labeltong, "Đăng nhập thành công !!!");
									new FormAdmin(nv);
									dispose();
								}else {
									JOptionPane.showMessageDialog(labeltong, "Nhân viên không có quyền admin !!!");
								}
							}else {
								JOptionPane.showMessageDialog(labeltong, "Đăng nhập thành công !!!");
								new FormNhanVien(nv);
								dispose();
							}
					}else {
						JOptionPane.showMessageDialog(labeltong, "Tài khoản hoặc mật khẩu không hợp lệ !!!");
					}
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(labeltong, "Lỗi rồi !!!");
				}
			}
		});
	}
}
