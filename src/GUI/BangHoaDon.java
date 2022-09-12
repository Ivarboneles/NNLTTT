package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Model.CTHoaDon;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;

public class BangHoaDon extends JPanel {

	private static final long serialVersionUID = 3189471001442012787L;

	static JTextField tfID = new JTextField();
	static JComboBox<KhachHang> cbbKhachHang = new JComboBox<KhachHang>();
	static JTextField tfKhachHang = new JTextField();
	static JDateChooser dcNgayGD = new JDateChooser();
	static JTextField tfThanhTien = new JTextField();

	JButton btnLoad = new JButton("View All");
	JButton btnAdd = new JButton("Add");
	JButton btnEdit = new JButton("Edit");
	JButton btnSave = new JButton("Save");
	JButton btnStop = new JButton("Stop");
	JButton btnDelete = new JButton("Delete");

	static JComboBox<NhanVien> cbbNhanVien = new JComboBox<NhanVien>();

	static JComboBox<String> cbbFind = new JComboBox<String>();

	static JComboBox<NhanVien> cbbNhanVienFind = new JComboBox<NhanVien>();

	static JComboBox<KhachHang> cbbKhachHangFind = new JComboBox<KhachHang>();

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model) {

		private static final long serialVersionUID = -261179322330643548L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	static JScrollPane sp = new JScrollPane(table);

	List<HoaDon> list = new ArrayList<HoaDon>();
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	int flag = 0;

	int flagShow = 1;

	public BangHoaDon() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Invoice Form ", TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);

		setBorder(titledBorder);
		setOpaque(false);

		// ------- Panel Text -------//
		JPanel pnTextField = new JPanel();
		pnTextField.setBounds(30, 30, 770, 380);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Infomation ", TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder1.setTitleColor(Color.white);

		pnTextField.setBorder(titledBorder1);
		pnTextField.setOpaque(false);
		pnTextField.setLayout(null);

		// --------- Text Info --------//

		JLabel lbID = new JLabel("ID");
		JLabel lbKhachhang = new JLabel("Customer");
		JLabel lbNgayGD = new JLabel("Date");
		JLabel lbThanhTien = new JLabel("Price");
		JLabel lbNhanVien = new JLabel("Employee");

		lbID.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbKhachhang.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNgayGD.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbThanhTien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNhanVien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbID.setForeground(Color.white);
		lbKhachhang.setForeground(Color.white);
		lbNgayGD.setForeground(Color.white);
		lbThanhTien.setForeground(Color.white);
		lbNhanVien.setForeground(Color.white);

		lbID.setBounds(70, 40, 80, 30);
		lbKhachhang.setBounds(70, 80, 80, 30);
		lbNgayGD.setBounds(70, 120, 80, 30);
		lbThanhTien.setBounds(70, 160, 80, 30);
		lbNhanVien.setBounds(70, 200, 80, 30);

		tfID.setBounds(150, 40, 50, 30);
		cbbKhachHang.setBounds(150, 80, 100, 30);
		tfKhachHang.setBounds(260, 80, 200, 30);
		dcNgayGD.setBounds(150, 120, 200, 30);
		tfThanhTien.setBounds(150, 160, 200, 30);
		cbbNhanVien.setBounds(150, 200, 200, 30);

		tfID.setEditable(false);
		tfKhachHang.setEditable(false);
		tfThanhTien.setEditable(false);

		pnTextField.add(lbID);
		pnTextField.add(lbKhachhang);
		pnTextField.add(lbNgayGD);
		pnTextField.add(lbThanhTien);
		pnTextField.add(lbNhanVien);

		pnTextField.add(tfID);
		pnTextField.add(cbbKhachHang);
		pnTextField.add(tfKhachHang);
		pnTextField.add(dcNgayGD);
		pnTextField.add(tfThanhTien);
		pnTextField.add(cbbNhanVien);

		// -------- Panel Button -------//
		JPanel pnButton = new JPanel();
		pnButton.setBounds(600, 20, 150, 265);
		TitledBorder titledBorder4 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Tools ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder4.setTitleColor(Color.white);

		pnButton.setBorder(titledBorder4);
		pnButton.setOpaque(false);
		pnButton.setLayout(null);

		btnLoad.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnAdd.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnEdit.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnSave.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnStop.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnDelete.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		btnLoad.setBounds(25, 20, 100, 30);
		btnAdd.setBounds(25, 60, 100, 30);
		btnEdit.setBounds(25, 100, 100, 30);
		btnDelete.setBounds(25, 140, 100, 30);
		btnSave.setBounds(25, 180, 100, 30);
		btnStop.setBounds(25, 220, 100, 30);

		btnAdd.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);

		pnButton.add(btnLoad);
		pnButton.add(btnAdd);
		pnButton.add(btnEdit);
		pnButton.add(btnDelete);
		pnButton.add(btnSave);
		pnButton.add(btnStop);

		pnTextField.add(pnButton);

		// -------- Panel Find -------//
		JPanel pnFind = new JPanel();

		pnFind.setBounds(10, 300, 750, 70);
		TitledBorder titledBorder3 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Find ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder3.setTitleColor(Color.white);

		pnFind.setBorder(titledBorder3);
		pnFind.setOpaque(false);
		pnFind.setLayout(null);

		// ------- Text field Find ------//
		cbbFind.setBounds(20, 15, 100, 40);

		cbbFind.addItem("Customer");
		cbbFind.addItem("Employee");

		cbbFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 13));

		cbbNhanVienFind.setBounds(135, 15, 200, 40);
		cbbKhachHangFind.setBounds(135, 15, 200, 40);
		cbbNhanVienFind.setVisible(false);

		JButton btnFind = new JButton("Find");
		btnFind.setBounds(365, 15, 100, 40);

		btnFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		pnFind.add(cbbFind);
		pnFind.add(cbbNhanVienFind);
		pnFind.add(cbbKhachHangFind);
		pnFind.add(btnFind);

		// -------------//
		pnTextField.add(pnFind);

		// ------- Panel Table -------//
		JPanel pnTable = new JPanel();
		pnTable.setBounds(30, 420, 770, 335);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Table ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder2.setTitleColor(Color.white);

		pnTable.setBorder(titledBorder2);
		pnTable.setOpaque(false);
		pnTable.setLayout(null);

		// ------ Table -----//
		Vector<String> column = new Vector<String>();

		column.add("ID");
		column.add("Customer");
		column.add("Date");
		column.add("Price");
		column.add("Employee");

		model.setColumnIdentifiers(column);

		table.setBounds(1, 1, 728, 281);

		// ------- Scroll Panel -----//

		sp.setBounds(20, 30, 730, 285);

		pnTable.add(sp);
		// ----------------------//

		add(pnTextField);
		add(pnTable);

		// -----------------------//
		setLayout(null);

		// ----------- Event ---------//

		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Load();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				flag = 1;

				flagShow = 0;

				tfID.setText("");
				tfKhachHang.setText("");
				dcNgayGD.setCalendar(null);
				tfThanhTien.setText("");
				cbbKhachHang.removeAllItems();
				cbbNhanVien.removeAllItems();

				tfID.setEditable(true);
				tfKhachHang.setEditable(true);
				tfThanhTien.setEditable(true);

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnStop.setEnabled(true);

				loadCombobox();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					flag = 2;

					flagShow = 0;

					tfID.setEditable(false);
					tfKhachHang.setEditable(true);
					tfThanhTien.setEditable(true);

					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
					btnStop.setEnabled(true);

					loadCombobox();
					cbbNhanVien.setSelectedIndex(list.get(index).getNVLapHD().getMaNV() - 1);
					cbbKhachHang.setSelectedIndex(list.get(index).getKhachhang().getMaKH());

				} else {
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Hoá Đơn !!!");
				}
			}

		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					Delete();
				} else {
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Hoá Đơn !!!");
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (flag == 1) {
					Add();
				} else if (flag == 2) {
					Edit();
				}
			}
		});

		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				flagShow = 1;
				ShowData();

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnStop.setEnabled(false);
			}
		});

		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				flag = 0;

				flagShow = 1;

				tfID.setText("");
				tfKhachHang.setText("");
				tfThanhTien.setText("");
				cbbKhachHang.removeAllItems();
				cbbNhanVien.removeAllItems();
				dcNgayGD.setCalendar(null);

				tfID.setEditable(false);
				tfKhachHang.setEditable(false);
				tfThanhTien.setEditable(false);

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnStop.setEnabled(false);

				int check = cbbFind.getSelectedIndex();
				clear();
				if (check == 0) {
					KhachHang kh = (KhachHang) cbbKhachHangFind.getSelectedItem();
					if (kh != null) {
						int id = kh.getMaKH();
						findbyCustomer(id);
					} else {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					NhanVien nv = (NhanVien) cbbNhanVienFind.getSelectedItem();
					if (nv != null) {
						int id = nv.getMaNV();
						findbyEmployee(id);
					} else {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});

		cbbFind.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (cbbFind.getSelectedIndex() == 0) {
					cbbKhachHangFind.setVisible(true);
					cbbNhanVienFind.setVisible(false);

				} else {
					cbbKhachHangFind.setVisible(false);
					cbbNhanVienFind.setVisible(true);
				}
			}
		});

		cbbKhachHang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				KhachHang kh = (KhachHang) cbbKhachHang.getSelectedItem();
				if (kh != null) {
					tfKhachHang.setText(kh.getTenKH());
				} else {
					tfKhachHang.setText("");
				}

			}
		});

		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ShowData();
			}
		});

		// ------------ Load data -----------//

		try {
			List<KhachHang> listkh = KhachHang.getALL();
			cbbKhachHangFind.addItem(null);
			for (int i = 0; i < listkh.size(); i++) {
				cbbKhachHangFind.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			List<NhanVien> listnv = NhanVien.getALL();
			cbbNhanVienFind.addItem(null);
			for (int i = 0; i < listnv.size(); i++) {
				cbbNhanVienFind.addItem(listnv.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void ShowData() {
		int index = table.getSelectedRow();
		if (flagShow == 1) {
			cbbKhachHang.removeAllItems();
			cbbNhanVien.removeAllItems();
			if (index > -1) {
				tfID.setText(table.getModel().getValueAt(index, 0).toString());
				tfKhachHang.setText(table.getModel().getValueAt(index, 1).toString());
				dcNgayGD.setDate(list.get(index).getNgayGD());
				tfThanhTien.setText(String.valueOf(Integer.valueOf(table.getModel().getValueAt(index, 3).toString())));
				cbbNhanVien.addItem(list.get(index).getNVLapHD());
				cbbKhachHang.addItem(list.get(index).getKhachhang());
			}
		}
	}

	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = HoaDon.getALL();

		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaHD()));
			row.add(String.valueOf(list.get(i).getKhachhang()));
			row.add(list.get(i).getNgayGD() == null ? null : DATE_FORMAT.format(list.get(i).getNgayGD()));
			row.add(String.valueOf(list.get(i).getThanhTien()));
			row.add(String.valueOf(list.get(i).getNVLapHD()));

			model.addRow(row);
		}

		tfID.setText("");
		tfKhachHang.setText("");
		tfThanhTien.setText("");
		cbbKhachHang.removeAllItems();
		cbbNhanVien.removeAllItems();
		dcNgayGD.setCalendar(null);

		tfID.setEditable(false);
		tfKhachHang.setEditable(false);
		tfThanhTien.setEditable(false);

		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);

		flag = 0;

		flagShow = 1;
	}

	void loadCombobox() {
		cbbKhachHang.removeAllItems();
		cbbNhanVien.removeAllItems();
		try {
			List<KhachHang> listkh = KhachHang.getALL();
			cbbKhachHang.addItem(null);
			for (int i = 0; i < listkh.size(); i++) {
				cbbKhachHang.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			List<NhanVien> listnv = NhanVien.getALL();
			for (int i = 0; i < listnv.size(); i++) {
				cbbNhanVien.addItem(listnv.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void Add() {
		HoaDon hd = new HoaDon();
		hd.setMaHD(Integer.parseInt(tfID.getText()));
		KhachHang kh = (KhachHang) cbbKhachHang.getSelectedItem();
		if (kh == null) {
			KhachHang kh_temp = (KhachHang) cbbKhachHang.getItemAt(cbbKhachHang.getItemCount() - 1);
			int maKH = kh_temp.getMaKH() + 1;
			String tenKH = tfKhachHang.getText();
			String diachi = "";
			String sdt = "";

			KhachHang temp = new KhachHang(maKH, tenKH, diachi, sdt);
			try {
				if (KhachHang.Insert(temp) == 1) {
					kh = temp;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
		}
		hd.setKhachhang(kh);
		Date ns = null;
		if (dcNgayGD.getDate() != null) {
			ns = new Date(dcNgayGD.getDate().getTime());
		}
		hd.setNgayGD(ns);
		int thanhtien = Integer.valueOf(tfThanhTien.getText());
		hd.setThanhTien(thanhtien);
		NhanVien nv = (NhanVien) cbbNhanVien.getSelectedItem();
		hd.setNVLapHD(nv);

		try {
			if (HoaDon.Insert(hd) == 1) {
				JOptionPane.showMessageDialog(sp, "Thêm Thành Công !!!");
				Load();
			} else {
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
		} catch (HeadlessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}

	void Edit() {
		HoaDon hd = new HoaDon();
		hd.setMaHD(Integer.parseInt(tfID.getText()));
		KhachHang kh = (KhachHang) cbbKhachHang.getSelectedItem();
		if (kh == null) {
			KhachHang kh_temp = (KhachHang) cbbKhachHang.getItemAt(cbbKhachHang.getItemCount() - 1);
			int maKH = kh_temp.getMaKH() + 1;
			String tenKH = tfKhachHang.getText();
			String diachi = "";
			String sdt = "";

			KhachHang temp = new KhachHang(maKH, tenKH, diachi, sdt);
			try {
				if (KhachHang.Insert(temp) == 1) {
					kh = temp;
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
		}
		hd.setKhachhang(kh);
		Date ns = null;
		if (dcNgayGD.getDate() != null) {
			ns = new Date(dcNgayGD.getDate().getTime());
		}
		hd.setNgayGD(ns);
		int thanhtien = Integer.valueOf(tfThanhTien.getText());
		hd.setThanhTien(thanhtien);
		NhanVien nv = (NhanVien) cbbNhanVien.getSelectedItem();
		hd.setNVLapHD(nv);

		if (HoaDon.updatenhoadon(hd) == 1) {
			JOptionPane.showMessageDialog(sp, "Sửa Thành Công !!!");
			try {
				Load();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
		} else {
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}

	void Delete() {
		if (CTHoaDon.deletecthdbyhd(Integer.parseInt(tfID.getText())) == 1) {
			if (HoaDon.deletehoadon(Integer.parseInt(tfID.getText())) == 1) {
				JOptionPane.showMessageDialog(sp, "Xoá Thành Công !!!");
				try {
					Load();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
		}else {
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}

	void findbyCustomer(int id) {

		List<HoaDon> list = HoaDon.findbykhachhang(id);
		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaHD()));
			row.add(String.valueOf(list.get(i).getKhachhang()));
			row.add(list.get(i).getNgayGD() == null ? null : DATE_FORMAT.format(list.get(i).getNgayGD()));
			row.add(String.valueOf(list.get(i).getThanhTien()));
			row.add(String.valueOf(list.get(i).getNVLapHD()));

			model.addRow(row);
		}
	}

	void findbyEmployee(int id) {

		List<HoaDon> list = HoaDon.findbynhanvien(id);
		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaHD()));
			row.add(String.valueOf(list.get(i).getKhachhang()));
			row.add(list.get(i).getNgayGD() == null ? null : DATE_FORMAT.format(list.get(i).getNgayGD()));
			row.add(String.valueOf(list.get(i).getThanhTien()));
			row.add(String.valueOf(list.get(i).getNVLapHD()));

			model.addRow(row);
		}
	}

	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

}
