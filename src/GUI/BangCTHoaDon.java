package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.CTHoaDon;
import Model.DichVu;
import Model.HoaDon;
import Model.NhanVien;

public class BangCTHoaDon extends JPanel {

	private static final long serialVersionUID = -780295808015027202L;

	static JComboBox<HoaDon> cbbHoaDon = new JComboBox<HoaDon>();
	static JComboBox<DichVu>  cbbDichVu = new JComboBox<DichVu> ();
	static JComboBox<NhanVien>  cbbNVPhuTrach = new JComboBox<NhanVien> ();


	static JComboBox<String> cbbFind = new JComboBox<String>();
	
	static JComboBox<HoaDon> cbbHoaDonFind = new JComboBox<HoaDon>();
	static JComboBox<DichVu>  cbbDichVuFind = new JComboBox<DichVu> ();
	static JComboBox<NhanVien>  cbbNVPhuTrachFind = new JComboBox<NhanVien> ();
	
	JButton btnLoad = new JButton("View All");
	JButton btnAdd = new JButton("Add");
	JButton btnEdit = new JButton("Edit");
	JButton btnSave = new JButton("Save");
	JButton btnStop = new JButton("Stop");
	JButton btnDelete = new JButton("Delete");

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model){
		
		private static final long serialVersionUID = -5973359946177045549L;

		public boolean isCellEditable(int row, int column) {
			    return false;
			   }
	};

	static JScrollPane sp = new JScrollPane(table);

	List<CTHoaDon> list = new ArrayList<CTHoaDon>();
	

	int flag = 0;

	int flagShow = 1;
	
	public BangCTHoaDon() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Detail Invoice Form ", TitledBorder.CENTER, TitledBorder.TOP);
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

		JLabel lbHoaDon = new JLabel("Invoice");
		JLabel lbDichVu = new JLabel("Service");
		JLabel lbNVPhuTrach = new JLabel("Employee");
		
		lbHoaDon.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbDichVu.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNVPhuTrach.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		
		lbHoaDon.setForeground(Color.white);
		lbDichVu.setForeground(Color.white);
		lbNVPhuTrach.setForeground(Color.white);
		
		lbHoaDon.setBounds(70, 40, 80, 30);
		lbDichVu.setBounds(70, 80, 80, 30);
		lbNVPhuTrach.setBounds(70, 120, 80, 30);

		cbbHoaDon.setBounds(150, 40, 200, 30);
		cbbDichVu.setBounds(150, 80, 200, 30);
		cbbNVPhuTrach.setBounds(150, 120, 200, 30);

		pnTextField.add(lbHoaDon);
		pnTextField.add(lbDichVu);
		pnTextField.add(lbNVPhuTrach);

		pnTextField.add(cbbHoaDon);
		pnTextField.add(cbbDichVu);
		pnTextField.add(cbbNVPhuTrach);

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

		pnButton.add(btnLoad);
		pnButton.add(btnAdd);
		pnButton.add(btnEdit);
		pnButton.add(btnDelete);
		pnButton.add(btnSave);
		pnButton.add(btnStop);

		btnAdd.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDelete.setEnabled(false);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);
		
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

		cbbFind.addItem("Invoice");
		cbbFind.addItem("Service");
		cbbFind.addItem("Employee");
		
		cbbFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 13));

		cbbHoaDonFind.setBounds(135, 15, 200, 40);
		cbbDichVuFind.setBounds(135, 15, 200, 40);
		cbbNVPhuTrachFind.setBounds(135, 15, 200, 40);
		
		cbbDichVuFind.setVisible(false);
		cbbNVPhuTrachFind.setVisible(false);

		JButton btnFind = new JButton("Find");
		btnFind.setBounds(365, 15, 100, 40);
		
		btnFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		pnFind.add(cbbFind);
		pnFind.add(cbbHoaDonFind);
		pnFind.add(cbbDichVuFind);
		pnFind.add(cbbNVPhuTrachFind);
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

		column.add("Invoice");
		column.add("Service");
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
				
				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnStop.setEnabled(true);
				
				cbbHoaDon.removeAllItems();
				cbbDichVu.removeAllItems();
				cbbNVPhuTrach.removeAllItems();
				
				loadCombobox();
				loadComboBoxNV();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					flag = 2;

					flagShow = 0;
					
					cbbNVPhuTrach.removeAllItems();
					
					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
					btnStop.setEnabled(true);
					
					loadComboBoxNV();
					
					cbbNVPhuTrach.setSelectedIndex(list.get(index).getNVPhuTrach().getMaNV() - 1);
				}
				else {
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Chi Tiết Hoá Đơn !!!");
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					Delete();
				}
				else {
					JOptionPane.showMessageDialog(sp, "Vui lòng chọn Chi Tiết Hoá Đơn !!!");
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
				
				cbbHoaDon.removeAllItems();
				cbbDichVu.removeAllItems();
				cbbNVPhuTrach.removeAllItems();
				
				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnStop.setEnabled(false);
				clear();
				int check = cbbFind.getSelectedIndex();
				switch (check) {
				case 0:{
					HoaDon hd = (HoaDon) cbbHoaDonFind.getSelectedItem();
					if(hd != null) {
						findbyHoadon(hd.getMaHD());
					}else {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				}
				case 1:{
					DichVu dv = (DichVu) cbbDichVuFind.getSelectedItem();
					if(dv != null) {
						findbyDichvu(dv.getMaDV());
					}else {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				}
				case 2:{
					NhanVien nv = (NhanVien) cbbNVPhuTrachFind.getSelectedItem();
					
					if(nv != null) {
						findbyNhanvien(nv.getMaNV());
					}else {
						try {
							Load();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				}
				default:
					break;
				}
			}
		});
		
		cbbFind.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (cbbFind.getSelectedIndex() == 0) {
					cbbHoaDonFind.setVisible(true);
					cbbDichVuFind.setVisible(false);
					cbbNVPhuTrachFind.setVisible(false);

				} else if(cbbFind.getSelectedIndex() == 1) {
					cbbHoaDonFind.setVisible(false);
					cbbDichVuFind.setVisible(true);
					cbbNVPhuTrachFind.setVisible(false);
				}else {
					cbbHoaDonFind.setVisible(false);
					cbbDichVuFind.setVisible(false);
					cbbNVPhuTrachFind.setVisible(true);
				}
			}
		});
		
		
		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				ShowData();
			}
		});
		
		//---------- Load Data ---------//
		try {
			List<HoaDon> listkh = HoaDon.getALL();
			cbbHoaDonFind.addItem(null);
			for (int i = 0; i < listkh.size(); i++) {
				cbbHoaDonFind.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			List<DichVu> listkh = DichVu.getALL();
			cbbDichVuFind.addItem(null);
			for (int i = 0; i < listkh.size(); i++) {
				cbbDichVuFind.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			List<NhanVien> listkh = NhanVien.getALL();
			cbbNVPhuTrachFind.addItem(null);
			for (int i = 0; i < listkh.size(); i++) {
				cbbNVPhuTrachFind.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	void ShowData() {
		int index = table.getSelectedRow();
		if (flagShow == 1) {
			
			cbbHoaDon.removeAllItems();
			cbbDichVu.removeAllItems();
			cbbNVPhuTrach.removeAllItems();
			if (index > -1) {
				cbbHoaDon.addItem(list.get(index).getHoadon());
				cbbDichVu.addItem(list.get(index).getDichvu());
				cbbNVPhuTrach.addItem(list.get(index).getNVPhuTrach());
			}
		}
	}
	
	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = CTHoaDon.getALL();
		
		for(int i =0; i<list.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(list.get(i).getHoadon()));
			row.add(String.valueOf(list.get(i).getDichvu()));
			row.add(String.valueOf(list.get(i).getNVPhuTrach()));
			
			model.addRow(row);
		}
		
		cbbHoaDon.removeAllItems();
		cbbDichVu.removeAllItems();
		cbbNVPhuTrach.removeAllItems();
		
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);

		flag = 0;

		flagShow = 1;
	}
	
	void Add() {
		CTHoaDon cthd = new CTHoaDon();
		HoaDon hd = (HoaDon) cbbHoaDon.getSelectedItem();
		DichVu dv = (DichVu) cbbDichVu.getSelectedItem();
		cthd.setHoadon(hd);
		cthd.setDichvu(dv);
		cthd.setNVPhuTrach((NhanVien) cbbNVPhuTrach.getSelectedItem());
		
		hd.setThanhTien(hd.getThanhTien() + dv.getGiaDV());
		try {
			if(CTHoaDon.Insert(cthd) == 1) {
				if(HoaDon.updatenhoadon(hd) == 1) {
					JOptionPane.showMessageDialog(sp, "Thêm Thành Công !!!");
					Load();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}
	
	void Edit() {
		CTHoaDon cthd = new CTHoaDon();
		cthd.setHoadon((HoaDon) cbbHoaDon.getSelectedItem());
		cthd.setDichvu((DichVu) cbbDichVu.getSelectedItem());
		cthd.setNVPhuTrach((NhanVien) cbbNVPhuTrach.getSelectedItem());
		
		try {
			if(CTHoaDon.updatecthd(cthd) == 1) {
				JOptionPane.showMessageDialog(sp, "Sửa Thành Công !!!");
				Load();
			}
			else {
				JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}
	
	void Delete() {
		HoaDon hd = (HoaDon) cbbHoaDon.getSelectedItem();
		DichVu dv = (DichVu) cbbDichVu.getSelectedItem();
		
		hd.setThanhTien(hd.getThanhTien() - dv.getGiaDV());
		if(CTHoaDon.deletecthd(hd.getMaHD(), dv.getMaDV())==1 ) {
			if(HoaDon.updatenhoadon(hd) == 1) {
				try {
					JOptionPane.showMessageDialog(sp, "Xoá Thành Công !!!");
					Load();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(sp, "Lỗi rồi !!!");
		}
	}
	
	void findbyHoadon(int id) {
		List<CTHoaDon> listcthd = CTHoaDon.findBYHD(id);
		for(int i =0; i<listcthd.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(listcthd.get(i).getHoadon()));
			row.add(String.valueOf(listcthd.get(i).getDichvu()));
			row.add(String.valueOf(listcthd.get(i).getNVPhuTrach()));
			
			model.addRow(row);
		}
	}
	
	void findbyDichvu(int id) {
		List<CTHoaDon> listcthd = CTHoaDon.findBYDV(id);
		for(int i =0; i<listcthd.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(listcthd.get(i).getHoadon()));
			row.add(String.valueOf(listcthd.get(i).getDichvu()));
			row.add(String.valueOf(listcthd.get(i).getNVPhuTrach()));
			
			model.addRow(row);
		}
	}

	void findbyNhanvien(int id) {
		List<CTHoaDon> listcthd = CTHoaDon.findBYNV(id);
		for(int i =0; i<listcthd.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(listcthd.get(i).getHoadon()));
			row.add(String.valueOf(listcthd.get(i).getDichvu()));
			row.add(String.valueOf(listcthd.get(i).getNVPhuTrach()));
			
			model.addRow(row);
		}
	}
	
	void loadCombobox() {
		try {
			List<HoaDon> listkh = HoaDon.getALL();
			for (int i = 0; i < listkh.size(); i++) {
				cbbHoaDon.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			List<DichVu> listkh = DichVu.getALL();
			for (int i = 0; i < listkh.size(); i++) {
				cbbDichVu.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void loadComboBoxNV() {
		try {
			List<NhanVien> listkh = NhanVien.getALL();
			for (int i = 0; i < listkh.size(); i++) {
				cbbNVPhuTrach.addItem(listkh.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
