package GUI;

import java.awt.Color;
import java.awt.Font;
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

import Model.ChucVu;
import Model.NhanVien;

public class BangNhanVien extends JPanel {

	private static final long serialVersionUID = -8396302271833115969L;

	static JTextField tfID = new JTextField();
	static JTextField tfName = new JTextField();
	static JDateChooser dcBirthday = new JDateChooser();
	static JTextField tfSex = new JTextField();
	static JComboBox<ChucVu> cbbPosition = new JComboBox<ChucVu>();
	static JTextField tfUsername = new JTextField();
	static JTextField tfPassword = new JTextField();

	JButton btnLoad = new JButton("View All");
	JButton btnAdd = new JButton("Add");
	JButton btnEdit = new JButton("Edit");
	JButton btnSave = new JButton("Save");
	JButton btnStop = new JButton("Stop");
	JButton btnDelete = new JButton("Delete");

	static JTextField textFind = new JTextField();

	static JComboBox<String> cbbFind = new JComboBox<String>();

	static JComboBox<ChucVu> cbbPositionFind = new JComboBox<ChucVu>();

	static DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	static JTable table = new JTable(model) {

		private static final long serialVersionUID = -8793891710080884042L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	JScrollPane sp = new JScrollPane(table);

	List<NhanVien> list = new ArrayList<NhanVien>();

	List<ChucVu> listChucVu = new ArrayList<ChucVu>();

	int flag = 0;
	int flagShow = 1;

	public BangNhanVien() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Employee Form ", TitledBorder.CENTER, TitledBorder.TOP);
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
		JLabel lbName = new JLabel("Name");
		JLabel lbBirthday = new JLabel("Birthday");
		JLabel lbSex = new JLabel("Sex");
		JLabel lbPosition = new JLabel("Position");
		JLabel lbUsername = new JLabel("Username");
		JLabel lbPassword = new JLabel("Password");

		lbID.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbBirthday.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbSex.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbPosition.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbUsername.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbPassword.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbID.setForeground(Color.white);
		lbName.setForeground(Color.white);
		lbBirthday.setForeground(Color.white);
		lbSex.setForeground(Color.white);
		lbPosition.setForeground(Color.white);
		lbUsername.setForeground(Color.white);
		lbPassword.setForeground(Color.white);

		lbID.setBounds(70, 25, 80, 30);
		lbName.setBounds(70, 65, 80, 30);
		lbBirthday.setBounds(70, 105, 80, 30);
		lbSex.setBounds(70, 145, 80, 30);
		lbPosition.setBounds(70, 185, 80, 30);
		lbUsername.setBounds(70, 225, 80, 30);
		lbPassword.setBounds(70, 265, 80, 30);

		tfID.setBounds(150, 25, 50, 30);
		tfName.setBounds(150, 65, 200, 30);
		dcBirthday.setBounds(150, 105, 200, 30);
		tfSex.setBounds(150, 145, 100, 30);
		cbbPosition.setBounds(150, 185, 200, 30);
		tfUsername.setBounds(150, 225, 200, 30);
		tfPassword.setBounds(150, 265, 200, 30);

		tfID.setEditable(false);
		tfName.setEditable(false);
		tfSex.setEditable(false);
		tfUsername.setEditable(false);
		tfPassword.setEditable(false);

		pnTextField.add(lbID);
		pnTextField.add(lbName);
		pnTextField.add(lbBirthday);
		pnTextField.add(lbSex);
		pnTextField.add(lbPosition);
		pnTextField.add(lbUsername);
		pnTextField.add(lbPassword);

		pnTextField.add(tfID);
		pnTextField.add(tfName);
		pnTextField.add(dcBirthday);
		pnTextField.add(tfSex);
		pnTextField.add(cbbPosition);
		pnTextField.add(tfUsername);
		pnTextField.add(tfPassword);

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
		JPanel pnFind1 = new JPanel();

		pnFind1.setBounds(10, 300, 750, 70);
		TitledBorder titledBorder3 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Find ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder3.setTitleColor(Color.white);

		pnFind1.setBorder(titledBorder3);
		pnFind1.setOpaque(false);
		pnFind1.setLayout(null);

		// ------- Text field Find ------//
		cbbFind.setBounds(20, 15, 100, 40);

		cbbFind.addItem("Name");
		cbbFind.addItem("Sex");
		cbbFind.addItem("Position");

		cbbFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 13));

		textFind.setBounds(120, 15, 500, 40);
		cbbPositionFind.setBounds(135, 15, 200, 40);
		cbbPositionFind.setVisible(false);
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(630, 15, 100, 40);

		btnFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		pnFind1.add(cbbFind);
		pnFind1.add(textFind);
		pnFind1.add(cbbPositionFind);
		pnFind1.add(btnFind);

		// -------------//
		pnTextField.add(pnFind1);

		// ------- Panel Table -------//
		JPanel pnTable1 = new JPanel();
		pnTable1.setBounds(30, 420, 770, 335);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Table ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder2.setTitleColor(Color.white);

		pnTable1.setBorder(titledBorder2);
		pnTable1.setOpaque(false);
		pnTable1.setLayout(null);

		// ------ Table -----//
		Vector<String> column1 = new Vector<String>();

		column1.add("ID");
		column1.add("Name");
		column1.add("Birthday");
		column1.add("Sex");
		column1.add("Position");
		column1.add("Username");
		column1.add("Password");

		model.setColumnIdentifiers(column1);

		table.setBounds(1, 1, 728, 281);

		// ------- Scroll Panel -----//

		sp.setBounds(20, 30, 730, 285);

		pnTable1.add(sp);

		// ----------------------//
		add(pnTextField);
		add(pnTable1);

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
				tfName.setText("");
				dcBirthday.setCalendar(null);
				tfSex.setText("");
				tfUsername.setText("");
				tfPassword.setText("");

				tfID.setEditable(true);
				tfName.setEditable(true);
				tfSex.setEditable(true);
				tfUsername.setEditable(true);
				tfPassword.setEditable(true);

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnStop.setEnabled(true);

				loadPosition();
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
					tfName.setEditable(true);
					tfSex.setEditable(true);
					tfUsername.setEditable(true);
					tfPassword.setEditable(true);

					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
					btnStop.setEnabled(true);

					loadPosition();
					cbbPosition.setSelectedIndex(list.get(index).getChucvu().getMaCV() - 1);

				} else {
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Nhân Viên !!!");
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
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Nhân Viên !!!");
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
				flagShow = 1;
				flag = 0;
				
				tfID.setText("");
				tfName.setText("");
				tfSex.setText("");
				tfUsername.setText("");
				tfPassword.setText("");

				tfID.setEditable(false);
				tfName.setEditable(false);
				dcBirthday.setCalendar(null);
				tfSex.setEditable(false);
				tfUsername.setEditable(false);
				tfPassword.setEditable(false);

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnStop.setEnabled(false);
				

				int check = cbbFind.getSelectedIndex();
				clear();

				switch (check) {
				case 0: {

					String text = textFind.getText();
					if (text.isEmpty() == true) {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							findbyname(text);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				}
				case 1: {
					String text = textFind.getText();
					if (text.isEmpty() == true) {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						findbysex(text);
					}
					break;
				}
				case 2: {
				
					if (cbbPositionFind.getSelectedItem() == null){
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						ChucVu cv = (ChucVu) cbbPositionFind.getSelectedItem();
						int id = cv.getMaCV();
						findbyposition(id);
					}
					break;
				}
				default:
					break;

				}
			}
		});

		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ShowData();
			}
		});

		cbbFind.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (cbbFind.getSelectedIndex() == 2) {
					textFind.setVisible(false);
					cbbPositionFind.setVisible(true);
					btnFind.setBounds(365, 15, 100, 40);
				} else {
					textFind.setVisible(true);
					btnFind.setBounds(630, 15, 100, 40);
					cbbPositionFind.setVisible(false);
				}
			}
		});

		// ------------- Load data ----------//
		try {
			List<ChucVu> listcv = ChucVu.getALL();
			cbbPositionFind.addItem(null);
			for (int i = 0; i < listcv.size(); i++) {
				cbbPositionFind.addItem(listcv.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void Load() throws ClassNotFoundException, SQLException {

		cbbPosition.removeAllItems();
		clear();

		list = NhanVien.getALL();

		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaNV()));
			row.add(list.get(i).getTenNV());
			row.add(list.get(i).getNgaySinh() == null ? null : DATE_FORMAT.format(list.get(i).getNgaySinh()));
			row.add(list.get(i).getGioiTinh());
			row.add(String.valueOf(list.get(i).getChucvu()));
			row.add(list.get(i).getUserName());
			row.add(list.get(i).getPassword());

			model.addRow(row);
		}

		tfID.setText("");
		tfName.setText("");
		tfSex.setText("");
		tfUsername.setText("");
		tfPassword.setText("");

		tfID.setEditable(false);
		tfName.setEditable(false);
		dcBirthday.setCalendar(null);
		tfSex.setEditable(false);
		tfUsername.setEditable(false);
		tfPassword.setEditable(false);

		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);

		flag = 0;
		flagShow = 1;
	}

	void ShowData() {

		int row = table.getSelectedRow();
		if (flagShow == 1) {
			cbbPosition.removeAllItems();
			if (row > -1) {

				tfID.setText(table.getValueAt(row, 0).toString());
				tfName.setText(table.getValueAt(row, 1).toString());

				if (table.getValueAt(row, 2) != null)
					dcBirthday.setDate(list.get(row).getNgaySinh());
				else
					dcBirthday.setCalendar(null);

				if (table.getValueAt(row, 3) != null)
					tfSex.setText(table.getValueAt(row, 3).toString());
				else
					tfSex.setText("");

				cbbPosition.addItem(list.get(row).getChucvu());

				if (table.getValueAt(row, 5) != null)
					tfUsername.setText(table.getValueAt(row, 5).toString());
				else
					tfUsername.setText("");

				if (table.getValueAt(row, 6) != null)
					tfPassword.setText(table.getValueAt(row, 6).toString());
				else
					tfPassword.setText("");
			}
		}

		tfID.setEditable(false);
		tfName.setEditable(false);
		tfSex.setEditable(false);
		tfUsername.setEditable(false);
		tfPassword.setEditable(false);
	}

	void Delete() {
		if (NhanVien.deletenhanvien(Integer.parseInt(tfID.getText())) == 1) {
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
	}

	void Add() {
		NhanVien nv = new NhanVien();
		nv.setMaNV(Integer.parseInt(tfID.getText()));
		nv.setTenNV(tfName.getText());

		Date ns = null;
		if (dcBirthday.getDate() != null) {
			ns = new Date(dcBirthday.getDate().getTime());
		}
		nv.setNgaySinh(ns);
		nv.setGioiTinh(tfSex.getText().isEmpty() == true ? "" : tfSex.getText());
		ChucVu cv = (ChucVu) cbbPosition.getSelectedItem();
		nv.setChucvu(cv);
		nv.setUserName(tfUsername.getText().isEmpty() == true ? "" : tfUsername.getText());
		nv.setPassword(tfPassword.getText().isEmpty() == true ? "" : tfPassword.getText());

		if (NhanVien.addnhanvien(nv) == 1) {
			JOptionPane.showMessageDialog(sp, "Thêm Thành Công !!!");
			try {
				Load();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}

	void Edit() {
		NhanVien nv = new NhanVien();
		nv.setMaNV(Integer.parseInt(tfID.getText()));
		nv.setTenNV(tfName.getText());

		Date ns = null;
		if (dcBirthday.getDate() != null) {
			ns = new Date(dcBirthday.getDate().getTime());
		}
		nv.setNgaySinh(ns);
		nv.setGioiTinh(tfSex.getText().isEmpty() == true ? "" : tfSex.getText());
		ChucVu cv = (ChucVu) cbbPosition.getSelectedItem();
		nv.setChucvu(cv);
		nv.setUserName(tfUsername.getText().isEmpty() == true ? "" : tfUsername.getText());
		nv.setPassword(tfPassword.getText().isEmpty() == true ? "" : tfPassword.getText());

		if (NhanVien.updatenhanhvien(nv) == 1) {
			JOptionPane.showMessageDialog(sp, "Sửa Thành Công !!!");
			try {
				Load();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}

	void findbyname(String TenNV) throws ClassNotFoundException, SQLException {

		List<NhanVien> list = NhanVien.getbyName(TenNV);
		for (int i = 0; i < list.size(); i++) {

			Vector<String> row = new Vector<String>();
			row.add(String.valueOf(list.get(i).getMaNV()));
			row.add(list.get(i).getTenNV());
			row.add(list.get(i).getNgaySinh() == null ? null : DATE_FORMAT.format(list.get(i).getNgaySinh()));
			row.add(list.get(i).getGioiTinh());
			row.add(String.valueOf(list.get(i).getChucvu()));
			row.add(list.get(i).getUserName());
			row.add(list.get(i).getPassword());

			model.addRow(row);
		}
	}

	void findbysex(String GioiTinh) {

		List<NhanVien> list = NhanVien.findbysex(GioiTinh);
		for (int i = 0; i < list.size(); i++) {

			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaNV()));
			row.add(list.get(i).getTenNV());
			row.add(list.get(i).getNgaySinh() == null ? null : DATE_FORMAT.format(list.get(i).getNgaySinh()));
			row.add(list.get(i).getGioiTinh());
			row.add(String.valueOf(list.get(i).getChucvu()));
			row.add(list.get(i).getUserName());
			row.add(list.get(i).getPassword());

			model.addRow(row);
		}
	}

	void findbyposition(int id) {

		List<NhanVien> list = NhanVien.findbyposition(id);
		for (int i = 0; i < list.size(); i++) {

			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaNV()));
			row.add(list.get(i).getTenNV());
			row.add(list.get(i).getNgaySinh() == null ? null : DATE_FORMAT.format(list.get(i).getNgaySinh()));
			row.add(list.get(i).getGioiTinh());
			row.add(String.valueOf(list.get(i).getChucvu()));
			row.add(list.get(i).getUserName());
			row.add(list.get(i).getPassword());

			model.addRow(row);
		}
	}

	void loadPosition() {
		cbbPosition.removeAllItems();
		try {
			listChucVu = ChucVu.getALL();
			for (int i = 0; i < listChucVu.size(); i++) {
				cbbPosition.addItem(listChucVu.get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
