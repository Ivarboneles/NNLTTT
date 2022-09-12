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
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Model.CTHoaDon;
import Model.DichVu;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;

public class BangThanhToan extends JPanel {

	private static final long serialVersionUID = -8911394595290703201L;

	static JTextField tfMaHD = new JTextField();
	static JTextField tfNgayGD = new JTextField();

	static JComboBox<KhachHang> cbbKhachHang = new JComboBox<KhachHang>();
	static JTextField tfKhachHang = new JTextField();
	static JTextField tfDiaChi = new JTextField();
	static JTextField tfSDT = new JTextField();

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model){
		private static final long serialVersionUID = -5472487318223928180L;

		@Override
		   public boolean isCellEditable(int row, int column) {
				if (column == 2 || column == 0 ) {
		            return false;
		        } else {
		            return true;
		        }  
		   }
		};

	static JScrollPane sp = new JScrollPane(table);
	JComboBox<DichVu> cbbDichVu = new JComboBox<DichVu>();

	JComboBox<NhanVien> cbbNhanVien = new JComboBox<NhanVien>();
	int sodong = 1;

	JTextField tfThanhTien = new JTextField();
	JTextField tfTienNhan = new JTextField();
	JTextField tfTienThua = new JTextField();
	
	List<KhachHang> listKH = new ArrayList<KhachHang>();

	List<DichVu> listDV = new ArrayList<DichVu>();

	List<NhanVien> listNV = new ArrayList<NhanVien>();
	
	int max = -1;
	public NhanVien nhanvien = new NhanVien();
	public BangThanhToan() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Transaction Form ", TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);

		setBorder(titledBorder);
		setOpaque(false);

		// ------- Panel Text -------//
		JPanel pnTextField = new JPanel();
		pnTextField.setBounds(30, 30, 770, 180);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Infomation ", TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder1.setTitleColor(Color.white);

		pnTextField.setBorder(titledBorder1);
		pnTextField.setOpaque(false);
		pnTextField.setLayout(null);

		// --------- Text Info --------//

		JLabel lbMaHD = new JLabel("Invoice ID");
		JLabel lbNgayGD = new JLabel("Date");
		JLabel lbKhachHang = new JLabel("Customer");
		JLabel lbDiaChi = new JLabel("Address");
		JLabel lbSDT = new JLabel("Phone");

		lbMaHD.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNgayGD.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbKhachHang.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbDiaChi.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbSDT.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbMaHD.setForeground(Color.white);
		lbNgayGD.setForeground(Color.white);
		lbKhachHang.setForeground(Color.white);
		lbDiaChi.setForeground(Color.white);
		lbSDT.setForeground(Color.white);
		
		
		lbMaHD.setBounds(80, 20, 80, 30);
		lbNgayGD.setBounds(240, 20, 40, 30);
		lbKhachHang.setBounds(80, 60, 80, 30);
		lbDiaChi.setBounds(80, 100, 80, 30);
		lbSDT.setBounds(80, 140, 80, 30);

		tfMaHD.setBounds(160, 20, 70, 30);
		tfNgayGD.setBounds(280, 20, 200, 30);
		cbbKhachHang.setBounds(160, 60, 150, 30);
		tfKhachHang.setBounds(320, 60, 160, 30);
		tfDiaChi.setBounds(160, 100, 320, 30);
		tfSDT.setBounds(160, 140, 200, 30);

		String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		tfNgayGD.setText(timeStamp);
		tfNgayGD.setEditable(false);
		try {
			List<HoaDon> listHD = HoaDon.getALL();
			max = listHD.get(listHD.size()-1).getMaHD() + 1;
			tfMaHD.setText(String.valueOf(max));
			tfMaHD.setEditable(false);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cbbKhachHang.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		pnTextField.add(lbMaHD);
		pnTextField.add(lbNgayGD);
		pnTextField.add(lbKhachHang);
		pnTextField.add(lbDiaChi);
		pnTextField.add(lbSDT);

		pnTextField.add(tfMaHD);
		pnTextField.add(tfNgayGD);
		pnTextField.add(cbbKhachHang);
		pnTextField.add(tfKhachHang);
		pnTextField.add(tfDiaChi);
		pnTextField.add(tfSDT);

		// -------- Panel Tools -------//
		JPanel pnTool = new JPanel();
		pnTool.setBounds(500, 20, 250, 150);
		TitledBorder titledBorder5 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Tools ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder5.setTitleColor(Color.white);

		pnTool.setBorder(titledBorder5);
		pnTool.setOpaque(false);
		pnTool.setLayout(null);

		JButton btnXemKhach = new JButton("View All Customer");
		JButton btnXemDichVu = new JButton("View All Service");

		btnXemKhach.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		btnXemDichVu.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		btnXemKhach.setBounds(50, 30, 150, 40);
		btnXemDichVu.setBounds(50, 90, 150, 40);

		pnTool.add(btnXemKhach);
		pnTool.add(btnXemDichVu);

		pnTextField.add(pnTool);

		// ------- Panel Table -------//

		JPanel pnTable = new JPanel();
		pnTable.setBounds(30, 220, 770, 335);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Table ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder2.setTitleColor(Color.white);

		pnTable.setBorder(titledBorder2);
		pnTable.setOpaque(false);
		pnTable.setLayout(null);

		// ------ Table -----//
		Vector<String> column = new Vector<String>();
		column.add("STT");
		column.add("Service");
		column.add("Price");
		column.add("Employee");

		model.setColumnIdentifiers(column);

		// ------- Test --------//

		TableColumn testColumn1 = table.getColumnModel().getColumn(1);
		testColumn1.setCellEditor(new DefaultCellEditor(cbbDichVu));

		TableColumn testColumn2 = table.getColumnModel().getColumn(3);
		testColumn2.setCellEditor(new DefaultCellEditor(cbbNhanVien));
		
		

		table.setBounds(1, 1, 728, 284);

		// ------- Scroll Panel -----//

		sp.setBounds(20, 20, 730, 285);

		// -------- Add - Remove Row ----//
		JButton btnAdd = new JButton("+");
		JButton btnRemove = new JButton("-");

		btnAdd.setBounds(709, 308, 18, 18);
		btnRemove.setBounds(732, 308, 18, 18);

		pnTable.add(sp);
		pnTable.add(btnAdd);
		pnTable.add(btnRemove);

		// ----------- Panel Pay-------//
		JPanel pnPay = new JPanel();
		pnPay.setBounds(30, 565, 770, 190);
		TitledBorder titledBorder4 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Payment ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder4.setTitleColor(Color.white);

		pnPay.setBorder(titledBorder4);
		pnPay.setOpaque(false);
		pnPay.setLayout(null);

		JLabel lbThanhTien = new JLabel("Total");
		JLabel lbTienNhan = new JLabel("Payments");
		JLabel lbTienThua = new JLabel("Exchanges");

		lbThanhTien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbTienNhan.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbTienThua.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbThanhTien.setForeground(Color.white);
		lbTienNhan.setForeground(Color.white);
		lbTienThua.setForeground(Color.white);

		lbThanhTien.setBounds(80, 40, 80, 30);
		lbTienNhan.setBounds(80, 80, 80, 30);
		lbTienThua.setBounds(80, 120, 80, 30);


		tfThanhTien.setBounds(160, 40, 200, 30);
		tfTienNhan.setBounds(160, 80, 200, 30);
		tfTienThua.setBounds(160, 120, 200, 30);

		tfThanhTien.setEditable(false);
		tfTienThua.setEditable(false);

		JLabel lbdvThanhTien = new JLabel("VND");
		JLabel lbdvTienNhan = new JLabel("VND");
		JLabel lbdvTienThua = new JLabel("VND");

		lbdvThanhTien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbdvTienNhan.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbdvTienThua.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbdvThanhTien.setForeground(Color.white);
		lbdvTienNhan.setForeground(Color.white);
		lbdvTienThua.setForeground(Color.white);

		lbdvThanhTien.setBounds(370, 40, 40, 30);
		lbdvTienNhan.setBounds(370, 80, 40, 30);
		lbdvTienThua.setBounds(370, 120, 40, 30);

		JButton btnSave = new JButton("SAVE");
		JButton btnClear = new JButton("CLEAR");

		btnSave.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 20));
		btnClear.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 20));

		btnSave.setBounds(430, 40, 150, 110);
		btnClear.setBounds(600, 40, 150, 110);

		pnPay.add(btnSave);
		pnPay.add(btnClear);

		pnPay.add(lbThanhTien);
		pnPay.add(lbTienNhan);
		pnPay.add(lbTienThua);

		pnPay.add(tfThanhTien);
		pnPay.add(tfTienNhan);
		pnPay.add(tfTienThua);

		pnPay.add(lbdvThanhTien);
		pnPay.add(lbdvTienNhan);
		pnPay.add(lbdvTienThua);

		// --------- Add Panel----------//

		add(pnTextField);
		add(pnTable);
		add(pnPay);

		// ----------- Set Layout---------//
		setLayout(null);

		// ----------- Event ---------//

		btnXemKhach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new ListCustomer();
			}
		});

		btnXemDichVu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new ListService();
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				AddRow();
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				RemoveRow();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try {
					Add();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clear();
			}
		});

		cbbDichVu.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int index = table.getSelectedRow();
				if (index > -1) {
					DichVu dv = (DichVu) cbbDichVu.getSelectedItem();
					table.setValueAt(dv.getGiaDV(), index, 2);
					tfThanhTien.setText(String.valueOf(tinhTien()));
					UpdateValue();
				}
			}
		});

		cbbKhachHang.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				KhachHang kh = (KhachHang) cbbKhachHang.getSelectedItem();
				if(kh != null) {
					 tfKhachHang.setText(kh.getTenKH());
					 tfDiaChi.setText(kh.getDiaChi());
					 tfSDT.setText(kh.getSDT());
				}else {
					tfKhachHang.setText("");
					 tfDiaChi.setText("");
					 tfSDT.setText("");
				}

			}
		});
		

		tfTienNhan.getDocument().addDocumentListener((DocumentListener) new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				UpdateValue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				UpdateValue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				UpdateValue();
			}

			
		});

		// ------------------- Load Data --------------//
		try {
			GetData();
			for (int i = 0; i < listKH.size(); i++) {
				cbbKhachHang.addItem(listKH.get(i));
			}

			for (int i = 0; i < listDV.size(); i++) {
				cbbDichVu.addItem(listDV.get(i));
			}

			for (int i = 0; i < listNV.size(); i++) {
				cbbNhanVien.addItem(listNV.get(i));
			}

		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void AddRow() {
		Vector<String> row = new Vector<String>();
		row.add(String.valueOf(sodong));
		row.add(cbbDichVu.getItemAt(0).toString());
		row.add(String.valueOf(cbbDichVu.getItemAt(0).getGiaDV()));
		row.add(cbbNhanVien.getItemAt(0).toString());
		model.addRow(row);
		sodong++;
		tfThanhTien.setText(String.valueOf(tinhTien()));
		UpdateValue();
	}

	void RemoveRow() {
		int index = table.getSelectedRow();

		model.removeRow(index);
		sodong--;
		for (int i = 0; i < sodong - 1; i++) {
			model.setValueAt(i + 1, i, 0);
		}
		tfThanhTien.setText(String.valueOf(tinhTien()));
		UpdateValue();
	}

	int tinhTien() {
		int ThanhTien = 0;
		for (int i = 0; i < sodong - 1; i++) {
			ThanhTien += Integer.valueOf(String.valueOf(model.getValueAt(i, 2)));
		}
		return ThanhTien;
	}

	void GetData() throws ClassNotFoundException, SQLException {
		listKH = KhachHang.getALL();
		listKH.add(0, null);
		listDV = DichVu.getALL();
		listNV = NhanVien.getALL();
	}
	
	void clear() {
		while(model.getRowCount() > 0)
		{
			model.removeRow(0);
		}
		tfThanhTien.setText("");
		tfTienNhan.setText("");
		tfTienThua.setText("");
		cbbKhachHang.setSelectedIndex(0);
		tfDiaChi.setText("");
		tfSDT.setText("");
		tfKhachHang.setText("");
		sodong = 1;
	}
	
	void Add() throws ClassNotFoundException, SQLException {
		int mahd = Integer.valueOf(tfMaHD.getText());
		KhachHang kh = (KhachHang) cbbKhachHang.getSelectedItem();
		if(kh == null) {
			int maKH = listKH.get(listKH.size()-1).getMaKH() +1;
			String tenKH = tfKhachHang.getText();
			String diachi = tfDiaChi.getText();
			String sdt = tfSDT.getText();
			
			KhachHang temp = new KhachHang(maKH, tenKH, diachi, sdt);
			if(KhachHang.Insert(temp) == 1) {
				kh = temp;
			}
		}
		String[] date = tfNgayGD.getText().split("/");
		Date ngay =  Date.valueOf(date[2] + "-" + date[1] +"-"+ date[0]);
		int thanhtien = Integer.valueOf(tfThanhTien.getText());
		HoaDon hd = new HoaDon(mahd, kh, ngay, thanhtien, nhanvien);
		boolean flag = true;
		try {
			if(HoaDon.Insert(hd) == 1) {
				for(int i =0; i< sodong - 1 ; i++) {
					NhanVien nv = NhanVien.getbyName(String.valueOf(table.getModel().getValueAt(i, 3))).get(0);
					DichVu dv = DichVu.getbyName(String.valueOf(table.getModel().getValueAt(i, 1))).get(0) ;
					CTHoaDon cthd = new CTHoaDon(hd,dv,nv);
					if(CTHoaDon.Insert(cthd) != 1) {
						flag = false;
					}
				}
				if(flag) {
					JOptionPane.showMessageDialog(table, "Thêm thành công!!!");
					clear();
					max++;
					tfMaHD.setText(String.valueOf(max));
				}else {
					JOptionPane.showMessageDialog(table, "Lỗi rồi!!!");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(table, "Lỗi rồi!!!");
		}
				
	}
	
	public void UpdateValue() {
		if (tfTienNhan.getText().isEmpty() == false) {
			try {
				int TienThua = 0;
				int ThanhTien = Integer.valueOf(tfThanhTien.getText());
				int TienNhan = Integer.valueOf(tfTienNhan.getText());

				TienThua = TienNhan - ThanhTien;

				tfTienThua.setText(String.valueOf(TienThua));
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(sp, "Vui Lòng Chỉ Nhập Số");
			}
		} else {
			tfTienThua.setText("0");
		}
	}

}
