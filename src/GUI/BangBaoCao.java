package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Model.DichVu;
import Model.HoaDon;
import Model.NhanVien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;



public class BangBaoCao extends JPanel {

	private static final long serialVersionUID = 5691983618739945526L;

	static JTextField tfMaHD = new JTextField();
	static JTextField tfNgayGD = new JTextField();
	static JTextField tfKhachHang = new JTextField();
	static JTextField tfThanhTien = new JTextField();
	static JTextField tfNhanVien = new JTextField();

	JLabel lbDateStartdata = new JLabel();
	JLabel lbDateEnddata = new JLabel();
	JLabel lbCountdata = new JLabel();
	JLabel lbTotaldata = new JLabel();

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model) {

		private static final long serialVersionUID = -7678334207003770192L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	static JScrollPane sp = new JScrollPane(table);

	JDateChooser dcNgayDau = new JDateChooser();
	JDateChooser dcNgayCuoi = new JDateChooser();

	JComboBox<DichVu> cbbDichVu = new JComboBox<DichVu>();
	
	JComboBox<NhanVien> cbbNhanVien = new JComboBox<NhanVien>();

	List<HoaDon> list = new ArrayList<HoaDon>();
	List<DichVu> listDV = new ArrayList<DichVu>();
	List<NhanVien> listNV = new ArrayList<NhanVien>();
	

	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	NhanVien nhanvien = new NhanVien();

	public BangBaoCao() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Report Form ", TitledBorder.CENTER, TitledBorder.TOP);
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
		JLabel lbThanhTien = new JLabel("Price");
		JLabel lbNhanVien = new JLabel("Employee");

		lbMaHD.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNgayGD.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbKhachHang.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbThanhTien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNhanVien.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbMaHD.setForeground(Color.white);
		lbNgayGD.setForeground(Color.white);
		lbKhachHang.setForeground(Color.white);
		lbThanhTien.setForeground(Color.white);
		lbNhanVien.setForeground(Color.white);

		lbMaHD.setBounds(80, 20, 80, 30);
		lbNgayGD.setBounds(250, 20, 40, 30);
		lbKhachHang.setBounds(80, 60, 80, 30);
		lbThanhTien.setBounds(80, 100, 80, 30);
		lbNhanVien.setBounds(80, 140, 80, 30);

		tfMaHD.setBounds(160, 20, 70, 30);
		tfNgayGD.setBounds(290, 20, 170, 30);
		tfKhachHang.setBounds(160, 60, 300, 30);
		tfThanhTien.setBounds(160, 100, 300, 30);
		tfNhanVien.setBounds(160, 140, 300, 30);
		
		tfMaHD.setEditable(false);
		tfNgayGD.setEditable(false);
		tfKhachHang.setEditable(false);
		tfThanhTien.setEditable(false);
		tfNhanVien.setEditable(false);

		pnTextField.add(lbMaHD);
		pnTextField.add(lbNgayGD);
		pnTextField.add(lbKhachHang);
		pnTextField.add(lbThanhTien);
		pnTextField.add(lbNhanVien);

		pnTextField.add(tfMaHD);
		pnTextField.add(tfNgayGD);
		pnTextField.add(tfKhachHang);
		pnTextField.add(tfThanhTien);
		pnTextField.add(tfNhanVien);

		// -------- Panel General -------//
		JPanel pnGeneral = new JPanel();
		pnGeneral.setBounds(500, 20, 250, 150);
		TitledBorder titledBorder5 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " General ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder5.setTitleColor(Color.white);

		JLabel lbDateStart = new JLabel("Date Start: ");
		JLabel lbDateEnd = new JLabel("Date End: ");
		JLabel lbCount = new JLabel("Count: ");
		JLabel lbTotal = new JLabel("Total: ");

		lbDateStart.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbDateEnd.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbCount.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbTotal.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbDateStart.setForeground(Color.white);
		lbDateEnd.setForeground(Color.white);
		lbCount.setForeground(Color.white);
		lbTotal.setForeground(Color.white);

		lbDateStart.setBounds(20, 20, 80, 30);
		lbDateEnd.setBounds(20, 50, 80, 30);
		lbCount.setBounds(20, 80, 50, 30);
		lbTotal.setBounds(20, 110, 50, 30);

		lbDateStartdata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbDateEnddata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbCountdata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbTotaldata.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbDateStartdata.setForeground(Color.white);
		lbDateEnddata.setForeground(Color.white);
		lbCountdata.setForeground(Color.white);
		lbTotaldata.setForeground(Color.white);

		lbDateStartdata.setBounds(100, 20, 100, 30);
		lbDateEnddata.setBounds(100, 50, 100, 30);
		lbCountdata.setBounds(70, 80, 100, 30);
		lbTotaldata.setBounds(70, 110, 150, 30);

		pnGeneral.setBorder(titledBorder5);
		pnGeneral.setOpaque(false);
		pnGeneral.setLayout(null);

		pnGeneral.add(lbDateStart);
		pnGeneral.add(lbDateEnd);
		pnGeneral.add(lbCount);
		pnGeneral.add(lbTotal);

		pnGeneral.add(lbDateStartdata);
		pnGeneral.add(lbDateEnddata);
		pnGeneral.add(lbCountdata);
		pnGeneral.add(lbTotaldata);

		pnTextField.add(pnGeneral);

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
		column.add("ID");
		column.add("Customer");
		column.add("Date");
		column.add("Price");
		column.add("Employee");

		model.setColumnIdentifiers(column);

		// ------- Test --------//

		table.setBounds(1, 1, 728, 281);

		// ------- Scroll Panel -----//

		sp.setBounds(20, 30, 730, 285);

		pnTable.add(sp);

		// ----------- Panel Report-------//
		JPanel pnReport = new JPanel();
		pnReport.setBounds(30, 565, 770, 190);
		TitledBorder titledBorder4 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Report ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder4.setTitleColor(Color.white);

		pnReport.setBorder(titledBorder4);
		pnReport.setOpaque(false);
		pnReport.setLayout(null);

		// --------- Service ---------//
		JPanel pnService = new JPanel();
		pnService.setBounds(20, 20, 425, 50);
		TitledBorder titledBorder6 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Service ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder6.setTitleColor(Color.white);

		pnService.setBorder(titledBorder6);
		pnService.setOpaque(false);
		pnService.setLayout(null);

		JLabel lbDichVu = new JLabel("Serivce");

		lbDichVu.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		lbDichVu.setForeground(Color.white);

		lbDichVu.setBounds(30, 10, 60, 30);
		cbbDichVu.setBounds(105, 13, 210, 30);

		JButton btnCreateService = new JButton("Create");

		btnCreateService.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		btnCreateService.setBounds(325, 13, 90, 30);

		pnService.add(lbDichVu);
		pnService.add(cbbDichVu);
		pnService.add(btnCreateService);

		// --------- Date ---------//
		JPanel pnDate = new JPanel();
		pnDate.setBounds(20, 80, 425, 100);
		TitledBorder titledBorder7 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Date ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder7.setTitleColor(Color.white);

		pnDate.setBorder(titledBorder7);
		pnDate.setOpaque(false);
		pnDate.setLayout(null);

		JLabel lbNgayDau = new JLabel("Date Start");
		JLabel lbNgayCuoi = new JLabel("Date End");

		lbNgayDau.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbNgayCuoi.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		lbNgayDau.setForeground(Color.white);
		lbNgayCuoi.setForeground(Color.white);

		lbNgayDau.setBounds(30, 15, 80, 30);
		lbNgayCuoi.setBounds(30, 50, 80, 30);

		// dcNgayDau.get

		dcNgayDau.setBounds(110, 15, 200, 30);
		dcNgayCuoi.setBounds(110, 50, 200, 30);

		JButton btnCreateDate = new JButton("Create");

		btnCreateDate.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		btnCreateDate.setBounds(330, 15, 80, 65);
		
		pnDate.add(lbNgayDau);
		pnDate.add(lbNgayCuoi);

		pnDate.add(dcNgayDau);
		pnDate.add(dcNgayCuoi);

		pnDate.add(btnCreateDate);

		pnReport.add(pnService);
		pnReport.add(pnDate);
		
		//---------- Employee ---------//
		
		JPanel pnEmployee = new JPanel();
		pnEmployee.setBounds(455, 20, 300, 160);
		TitledBorder titledBorder8 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Employee ",
				TitledBorder.LEFT, TitledBorder.TOP);
		titledBorder8.setTitleColor(Color.white);

		pnEmployee.setBorder(titledBorder8);
		pnEmployee.setOpaque(false);
		pnEmployee.setLayout(null);

		JLabel lbEmployee = new JLabel("Employee", SwingConstants.CENTER);

		lbEmployee.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		lbEmployee.setForeground(Color.white);

		lbEmployee.setBounds(30, 20, 240, 30);
		cbbNhanVien.setBounds(40, 60, 220, 30);

		JButton btnCreateEmployee = new JButton("Create");

		btnCreateEmployee.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		btnCreateEmployee.setBounds(90, 100, 120, 50);

		pnEmployee.add(lbEmployee);
		pnEmployee.add(cbbNhanVien);
		pnEmployee.add(btnCreateEmployee);
		
		pnReport.add(pnEmployee);

		// --------- Add Panel----------//

		add(pnTextField);
		add(pnTable);
		add(pnReport);
	
		// ----------- Set Layout---------//
		setLayout(null);

		// ----------- Event ---------//

		table.addMouseListener((MouseListener) new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {

				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					int index = table.getSelectedRow();
					new ListChiTietCuaHD(Integer.valueOf(String.valueOf(table.getValueAt(index, 0))));
				}

			}
		});
		
		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				showInfo();
			}
		});

		btnCreateDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
					Date start = list.get(0).getNgayGD();
					Date end = list.get(list.size() - 1).getNgayGD();

					if (dcNgayDau.getDate() != null) {

						start = new Date(dcNgayDau.getDate().getTime());
					}

					if (dcNgayCuoi.getDate() != null) {

						end = new Date(dcNgayCuoi.getDate().getTime());
					}

					int[] report = { 0, 0 };

					report = HoaDon.BaoCao(start, end);
	
					String printStart = new SimpleDateFormat("dd/MM/yyyy").format(start);
					String printEnd = new SimpleDateFormat("dd/MM/yyyy").format(end);
					int avg = report[0] / HoaDon.CountDate(start, end);

					
					new FormReportDate(timeStamp, printStart, printEnd, avg, report, nhanvien);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCreateService.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
					Date start = list.get(0).getNgayGD();
					Date end = list.get(list.size() - 1).getNgayGD();
					DichVu dv = (DichVu) cbbDichVu.getSelectedItem();
					int[] report = { 0, 0 };

					report = HoaDon.BaoCaoDichVu(dv.getMaDV());
	
					String printStart = new SimpleDateFormat("dd/MM/yyyy").format(start);
					String printEnd = new SimpleDateFormat("dd/MM/yyyy").format(end);
					int avg = report[0] / HoaDon.CountDate(start, end);

					
					new FormReportService(dv.getTenDV(),timeStamp, printStart, printEnd, avg, report, nhanvien);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnCreateEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
					Date start = list.get(0).getNgayGD();
					Date end = list.get(list.size() - 1).getNgayGD();
					NhanVien nv = (NhanVien) cbbNhanVien.getSelectedItem();
					int[] reportDaLap = { 0, 0 };
					int[] reportDaLam = { 0, 0 };

					reportDaLap = HoaDon.BaoCaoNhanVienDaLap(nv.getMaNV());
					reportDaLam = HoaDon.BaoCaoNhanVienDaLam(nv.getMaNV());
	
					String printStart = new SimpleDateFormat("dd/MM/yyyy").format(start);
					String printEnd = new SimpleDateFormat("dd/MM/yyyy").format(end);
					

					
					new FormReportEmployee(nv.getTenNV(),timeStamp, printStart, printEnd, reportDaLap,reportDaLam, nhanvien);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// ---------- Load data -------//
		try {
			Load();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showData();
		
		try {
			listDV = DichVu.getALL();
			for(int i = 0 ; i< listDV.size(); i++) {
				cbbDichVu.addItem(listDV.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			listNV = NhanVien.getALL();
			for(int i = 0 ; i< listNV.size(); i++) {
				cbbNhanVien.addItem(listNV.get(i));
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = HoaDon.getALL();

		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaHD()));
			row.add(String.valueOf(list.get(i).getKhachhang()));
			row.add(DATE_FORMAT.format(list.get(i).getNgayGD()));
			row.add(String.valueOf(list.get(i).getThanhTien()));
			row.add(String.valueOf(list.get(i).getNVLapHD()));

			model.addRow(row);
		}

	}

	void showData() {
		lbDateStartdata.setText(DATE_FORMAT.format(list.get(0).getNgayGD()));
		lbDateEnddata.setText(DATE_FORMAT.format(list.get(list.size() - 1).getNgayGD()));
		lbCountdata.setText(String.valueOf(list.size()));
		int Total = 0;
		for (int i = 0; i < list.size(); i++) {
			Total += list.get(i).getThanhTien();
		}
		lbTotaldata.setText(FormatMoney(Total) + " VND");
	}

	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	void showInfo() {
		int index = table.getSelectedRow();
		if (index != -1) {

			tfMaHD.setText(table.getModel().getValueAt(index, 0).toString());
			tfKhachHang.setText(table.getModel().getValueAt(index, 1).toString());
			tfNgayGD.setText(table.getModel().getValueAt(index, 2).toString());
			tfThanhTien.setText(FormatMoney(Integer.valueOf(table.getModel().getValueAt(index, 3).toString()))+ " VND");
			tfNhanVien.setText(table.getModel().getValueAt(index, 4).toString());

		}

	}
	
	String FormatMoney(int n) {
		int sizetotal = String.valueOf(n).length();
		String total = String.valueOf(n);
		if (sizetotal % 3 == 0) {
			sizetotal--;
		}
		for (int i = 0; i < sizetotal / 3; i++) {
			String a = total.substring(0, total.length() - i - 3 * (i + 1));
			String b = total.substring(total.length() - i - 3 * (i + 1));

			total = a + "." + b;
		}

		return total;
	}


}
