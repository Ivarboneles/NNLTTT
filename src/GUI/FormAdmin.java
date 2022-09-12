package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Model.NhanVien;

public class FormAdmin extends JFrame {

	private static final long serialVersionUID = 9052112333030448917L;

	JPanel pnMenu = new JPanel();

	JButton btnNhanVien = new JButton(" Nhân Viên ");
	JButton btnKhachHang = new JButton(" Khách Hàng ");
	JButton btnDichVu = new JButton(" Dịch Vụ ");
	JButton btnHoaDon = new JButton(" Hoá Đơn ");
	JButton btnCTHoaDon = new JButton(" CT Hoá Đơn ");
	JButton btnChucVu = new JButton(" Chức Vụ ");

	BangNhanVien fNhanVien = new BangNhanVien();
	BangKhachHang fKhachHang = new BangKhachHang();
	BangHoaDon fHoaDon = new BangHoaDon();
	BangDichVu fDichVu = new BangDichVu();
	BangCTHoaDon fCTHoaDon = new BangCTHoaDon();
	BangChucVu fChucVu = new BangChucVu();

	JLabel lbNamedata = new JLabel();
	JLabel lbUserNamedata = new JLabel();
	JLabel lbPositiondata = new JLabel();
	
	NhanVien nhanvien = new NhanVien();

	public FormAdmin(NhanVien nhanvien) {

		this.nhanvien = nhanvien;
		AddPanelMenu();

		add(fNhanVien);
		add(fKhachHang);
		add(fHoaDon);
		add(fDichVu);
		add(fCTHoaDon);
		add(fChucVu);

		Hide();
		fNhanVien.setVisible(true);

		// ------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 1050, 800);

		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgAdmin.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));

		labeltong.setIcon(avtstop);

		add(labeltong);

		setTitle("Form Admin");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1050, 800);
		setLayout(null);
		setVisible(true);

		// ------------- Event --------------//

		btnNhanVien.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fNhanVien.setVisible(true);

			}
		});

		btnKhachHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fKhachHang.setVisible(true);

			}
		});

		btnHoaDon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fHoaDon.setVisible(true);

			}
		});

		btnDichVu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fDichVu.setVisible(true);

			}
		});

		btnCTHoaDon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fCTHoaDon.setVisible(true);
			}
		});

		btnChucVu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Hide();
				fChucVu.setVisible(true);
			}
		});
		
		//----------- Load data ----------//
		ShowUser();
	}

	void AddPanelMenu() {

		pnMenu.setBounds(5, 1, 200, 765);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Menu Form ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);
		pnMenu.setBorder(titledBorder);
		pnMenu.setOpaque(false);

		btnNhanVien.setBounds(30, 30, 140, 40);
		btnKhachHang.setBounds(30, 80, 140, 40);
		btnDichVu.setBounds(30, 130, 140, 40);
		btnHoaDon.setBounds(30, 180, 140, 40);
		btnCTHoaDon.setBounds(30, 230, 140, 40);
		btnChucVu.setBounds(30, 280, 140, 40);

		pnMenu.add(btnNhanVien);
		pnMenu.add(btnKhachHang);
		pnMenu.add(btnDichVu);
		pnMenu.add(btnHoaDon);
		pnMenu.add(btnCTHoaDon);
		pnMenu.add(btnChucVu);

		btnNhanVien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnKhachHang.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnDichVu.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnHoaDon.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnCTHoaDon.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));
		btnChucVu.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		// -------- Panel Info -------//

		JPanel pnInfoUser = new JPanel();
		pnInfoUser.setBounds(10, 400, 180, 300);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " User ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder1.setTitleColor(Color.white);
		pnInfoUser.setBorder(titledBorder1);
		pnInfoUser.setOpaque(false);

		JLabel lbName = new JLabel("Name", SwingConstants.CENTER);
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

		JLabel lbNameLine = new JLabel("---", SwingConstants.CENTER);
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

		// -------- App Name -------//
		JLabel lbAppName = new JLabel("L I L I E S", SwingConstants.CENTER);
		lbAppName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 32));

		lbAppName.setForeground(Color.white);
		lbAppName.setBounds(10, 715, 180, 30);

		// -------- Add Component -------//
		pnMenu.add(pnInfoUser);
		pnMenu.add(lbAppName);

		pnMenu.setLayout(null);
		this.add(pnMenu);
	}

	void Hide() {
		fNhanVien.setVisible(false);
		fKhachHang.setVisible(false);
		fHoaDon.setVisible(false);
		fDichVu.setVisible(false);
		fCTHoaDon.setVisible(false);
		fChucVu.setVisible(false);
	}
	
	void ShowUser() {
		lbNamedata.setText(nhanvien.getTenNV());
		lbUserNamedata.setText(nhanvien.getUserName());
		lbPositiondata.setText(nhanvien.getChucvu().getTenCV());
	}

}
