package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.ChucVu;

public class BangChucVu extends JPanel {

	private static final long serialVersionUID = -2281388263126419822L;

	static JTextField tfID = new JTextField();
	static JTextField tfName = new JTextField();

	JButton btnLoad = new JButton("View All");
	JButton btnAdd = new JButton("Add");
	JButton btnEdit = new JButton("Edit");
	JButton btnSave = new JButton("Save");
	JButton btnStop = new JButton("Stop");
	JButton btnDelete = new JButton("Delete");
	
	static JTextField textFind = new JTextField();

	static JComboBox<String> cbbFind = new JComboBox<String>();

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model){
		
		private static final long serialVersionUID = -2566047303693724557L;

		public boolean isCellEditable(int row, int column) {
			    return false;
			   }
	};

	static JScrollPane sp = new JScrollPane(table);
	
	List<ChucVu> list = new ArrayList<ChucVu>();
	
	int flag = 0;
	int flagShow = 1;

	public BangChucVu() {
		// ------- Panel Info -------//
		setBounds(215, 1, 828, 765);

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Position Form ", TitledBorder.CENTER, TitledBorder.TOP);
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
		
		lbID.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
		lbName.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));
	
		lbID.setForeground(Color.white);
		lbName.setForeground(Color.white);
		

		lbID.setBounds(70, 40, 80, 30);
		lbName.setBounds(70, 80, 80, 30);
		

		tfID.setBounds(150, 40, 50, 30);
		tfName.setBounds(150, 80, 200, 30);
		
		tfID.setEditable(false);
		tfName.setEditable(false);
		

		pnTextField.add(lbID);
		pnTextField.add(lbName);
	

		pnTextField.add(tfID);
		pnTextField.add(tfName);
		

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

		cbbFind.addItem("Name");
		
		cbbFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 13));

		
		textFind.setBounds(120, 15, 500, 40);

		JButton btnFind = new JButton("Find");
		btnFind.setBounds(630, 15, 100, 40);
		
		btnFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 16));

		pnFind.add(cbbFind);
		pnFind.add(textFind);
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
		column.add("Name");

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
				flagShow = 1;
				tfID.setText("");
				tfName.setText("");
				
				tfID.setEditable(true);
				tfName.setEditable(true);

				btnAdd.setEnabled(false);
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				btnSave.setEnabled(true);
				btnStop.setEnabled(true);
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					flag = 2;
					flagShow = 1;

					tfID.setEditable(false);
					tfName.setEditable(true);

					btnAdd.setEnabled(false);
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);
					btnStop.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(pnTable, "Vui lòng chọn Chức Vụ !!!");
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = table.getSelectedRow();
				if (index > -1) {
					Delete();
				}else {
					JOptionPane.showMessageDialog(pnTable, "Vui lòng chọn Chức Vụ !!!");
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

				tfID.setText("");
				tfName.setText("");
				

				tfID.setEditable(false);
				tfName.setEditable(false);
		

				btnAdd.setEnabled(true);
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);
				btnSave.setEnabled(false);
				btnStop.setEnabled(false);

				flag = 0;
				
				flagShow = 1;
				
				String text = textFind.getText();
				clear();

				if (text.isEmpty() == true) {
					try {
						Load();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						findbyName(text);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ShowData();
			}
		});

	}
	
	void ShowData() {
		int row = table.getSelectedRow();
		if( flagShow == 1) {
			if (row > -1) {

				tfID.setText(table.getValueAt(row, 0).toString());
				tfName.setText(table.getValueAt(row, 1).toString());
				
			}
		}
		tfID.setEditable(false);
		tfName.setEditable(false);
		
	}
	
	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = ChucVu.getALL();
		
		for(int i =0; i<list.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(list.get(i).getMaCV()));
			row.add(list.get(i).getTenCV());
		
			model.addRow(row);
		}
		
		tfID.setText("");
		tfName.setText("");
		

		tfID.setEditable(false);
		tfName.setEditable(false);
		

		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnDelete.setEnabled(true);
		btnSave.setEnabled(false);
		btnStop.setEnabled(false);

		flag = 0;
		
		flagShow = 1;
		
	}
	
	void Delete() {

		if (ChucVu.deletechucvu(Integer.parseInt(tfID.getText())) == 1) {
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
		ChucVu cv = new ChucVu();
		cv.setMaCV(Integer.parseInt(tfID.getText()));
		cv.setTenCV(tfName.getText());

		if (ChucVu.addchucvu(cv) == 1) {
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
		ChucVu cv = new ChucVu();
		cv.setMaCV(Integer.parseInt(tfID.getText()));
		cv.setTenCV(tfName.getText());

		if (ChucVu.updatechucvu(cv) == 1) {
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
	
	void findbyName(String TenDV) throws ClassNotFoundException, SQLException {

		List<ChucVu> list = ChucVu.findByName(TenDV);
		for(int i =0; i<list.size(); i++) {
			Vector<String> row = new Vector<String>();
			
			row.add(String.valueOf(list.get(i).getMaCV()));
			row.add(list.get(i).getTenCV());
		
			model.addRow(row);
		
		}
	}
	
	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
