package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Model.NhanVien;

public class FormNhanVien extends JFrame {

	private static final long serialVersionUID = -8383898853331765261L;

	JPanel pnMenu = new JPanel();

	JButton btnThanhToan = new JButton(" Thanh Toán ");
	JButton btnBaoCao = new JButton(" Báo Cáo ");
	
	NhanVien nhanvien = new NhanVien();
	
	BangThanhToan fThanhToan = new BangThanhToan();
	BangBaoCao fBaoCao = new BangBaoCao();
	

	
	JLabel lbNamedata = new JLabel();
	JLabel lbUserNamedata = new JLabel();
	JLabel lbPositiondata = new JLabel();

	public FormNhanVien(NhanVien nhanvien) {

		this.nhanvien = nhanvien;
		fThanhToan.nhanvien = nhanvien;
		fBaoCao.nhanvien = nhanvien;
		AddPanelMenu();
		add(fThanhToan);
		add(fBaoCao);

		Hide();
		fThanhToan.setVisible(true);

		// ------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 1050, 800);

		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgNhanVien.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));

		labeltong.setIcon(avtstop);

		add(labeltong);

		setTitle("Form Employee");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1050, 800);
		setLayout(null);
		setVisible(true);

		// --------- Event ----------//

		btnThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fThanhToan.clear();
				fThanhToan.setVisible(true);
				
			}
		});

		btnBaoCao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				try {
					fBaoCao.Load();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fBaoCao.showData();
				fBaoCao.setVisible(true);
			}
		});
		
		
		//--------- Load data ------//
		ShowUser();

	}

	void AddPanelMenu() {
		//-------- Panel Menu -------//
		pnMenu.setBounds(5, 1, 200, 765);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Menu ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);
		pnMenu.setBorder(titledBorder);
		pnMenu.setOpaque(false);

		btnThanhToan.setBounds(30, 30, 140, 40);
		btnBaoCao.setBounds(30, 80, 140, 40);
		
		btnThanhToan.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnBaoCao.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		pnMenu.add(btnThanhToan);
		pnMenu.add(btnBaoCao);
		
		//-------- Panel Info -------//
		
		JPanel pnInfoUser = new JPanel();
		pnInfoUser.setBounds(10, 400, 180, 300);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " User ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder1.setTitleColor(Color.white);
		pnInfoUser.setBorder(titledBorder1);
		pnInfoUser.setOpaque(false);
		
		JLabel lbName = new JLabel("Name",SwingConstants.CENTER);
		JLabel lbUserName = new JLabel("UserName", SwingConstants.CENTER);
		JLabel lbPosition = new JLabel("Position", SwingConstants.CENTER);
		
		
		lbName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbUserName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbPosition.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		
		lbName.setForeground(Color.white);
		lbUserName.setForeground(Color.white);
		lbPosition.setForeground(Color.white);
		
		lbName.setBounds(10, 30, 160, 30);
		lbUserName.setBounds(10, 110, 160, 30);
		lbPosition.setBounds(10, 190, 160, 30);
		
		JLabel lbNameLine = new JLabel("---",SwingConstants.CENTER);
		JLabel lbUserNameLine = new JLabel("---", SwingConstants.CENTER);
		JLabel lbPositionLine = new JLabel("---", SwingConstants.CENTER);
		
		lbNameLine.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbUserNameLine.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbPositionLine.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		
		lbNameLine.setForeground(Color.white);
		lbUserNameLine.setForeground(Color.white);
		lbPositionLine.setForeground(Color.white);
		
		lbNameLine.setBounds(10, 50, 160, 20);
		lbUserNameLine.setBounds(10, 130, 160, 20);
		lbPositionLine.setBounds(10, 210, 160, 20);
		
		lbNamedata.setHorizontalAlignment(SwingConstants.CENTER);
		lbUserNamedata.setHorizontalAlignment(SwingConstants.CENTER);
		lbPositiondata.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbNamedata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbUserNamedata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		lbPositiondata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		
		lbNamedata.setForeground(Color.white);
		lbUserNamedata.setForeground(Color.white);
		lbPositiondata.setForeground(Color.white);
		
		lbNamedata.setBounds(1, 70, 179, 30);
		lbUserNamedata.setBounds(10, 150, 160, 30);
		lbPositiondata.setBounds(10, 230, 160, 30);

		pnInfoUser.add(lbName);
		pnInfoUser.add(lbUserName);
		pnInfoUser.add(lbPosition);
		
		pnInfoUser.add(lbNameLine);
		pnInfoUser.add(lbUserNameLine);
		pnInfoUser.add(lbPositionLine);
		
		pnInfoUser.add(lbNamedata);
		pnInfoUser.add(lbUserNamedata);
		pnInfoUser.add(lbPositiondata);
		
		pnInfoUser.setLayout(null);
		
		//-------- App Name -------//
		JLabel lbAppName = new JLabel("L I L I E S", SwingConstants.CENTER);
		lbAppName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 32));
		
		lbAppName.setForeground(Color.white);
		lbAppName.setBounds(10, 715, 180, 30);
		
		//-------- Add Component -------//
		pnMenu.add(pnInfoUser);
		pnMenu.add(lbAppName);
		pnMenu.setLayout(null);
		this.add(pnMenu);
	}
	

	void Hide() {
		fThanhToan.setVisible(false);
		fBaoCao.setVisible(false);
	}
	
	void ShowUser() {
		lbNamedata.setText(nhanvien.getTenNV());
		lbUserNamedata.setText(nhanvien.getUserName());
		lbPositiondata.setText(nhanvien.getChucvu().getTenCV());
	}
}
