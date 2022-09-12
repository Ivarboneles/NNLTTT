package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.KhachHang;

public class ListCustomer extends JFrame {

	private static final long serialVersionUID = 4241773260302365071L;

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model) {
		private static final long serialVersionUID = 6734961375415447667L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	static JScrollPane sp = new JScrollPane(table);

	static JComboBox<String> cbbFind = new JComboBox<String>();

	List<KhachHang> list = new ArrayList<KhachHang>();

	public ListCustomer() {

		JPanel pnTable = new JPanel();
		pnTable.setBounds(10, 80, 580, 280);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Table ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);

		pnTable.setBorder(titledBorder);
		pnTable.setOpaque(false);
		pnTable.setLayout(null);

		// ------ Find -------//

		cbbFind.setBounds(50, 20, 100, 40);

		cbbFind.addItem("Name");
		cbbFind.addItem("Phone");

		cbbFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		JTextField textFind = new JTextField();
		textFind.setBounds(160, 20, 300, 40);

		JButton btnFind = new JButton("Find");

		btnFind.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 14));

		btnFind.setBounds(480, 20, 100, 40);

		add(cbbFind);
		add(textFind);
		add(btnFind);

		// ------ Table -----//
		Vector<String> column = new Vector<String>();

		column.add("ID");
		column.add("Name");
		column.add("Address");
		column.add("Phone");

		model.setColumnIdentifiers(column);

		table.setBounds(1, 1, 557, 247);

		// ------- Scroll Panel -----//

		sp.setBounds(10, 20, 560, 250);

		pnTable.add(sp);
		// ----------------------//

		add(pnTable);

		// ------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 600, 400);

		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgListCust.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));

		labeltong.setIcon(avtstop);

		add(labeltong);

		setTitle("Form List Customer");
		setSize(600, 400);
		setLayout(null);
		setVisible(true);

		// ------------- Load data -------------//
		try {
			Load();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ----------- Event --------------//
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String text = textFind.getText();
				String check = String.valueOf(cbbFind.getSelectedItem());
				clear();
				if (text.isEmpty() == true) {
					try {
						Load();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					if (check.compareTo("Name") == 0) {
						try {
							list = KhachHang.getbyName(text);
							for (int i = 0; i < list.size(); i++) {
								Vector<String> row = new Vector<String>();

								row.add(String.valueOf(list.get(i).getMaKH()));
								row.add(list.get(i).getTenKH());
								row.add(list.get(i).getDiaChi());
								row.add(list.get(i).getSDT());
								model.addRow(row);
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						try {
							list = KhachHang.getbyPhone(text);
							for (int i = 0; i < list.size(); i++) {
								Vector<String> row = new Vector<String>();

								row.add(String.valueOf(list.get(i).getMaKH()));
								row.add(list.get(i).getTenKH());
								row.add(list.get(i).getDiaChi());
								row.add(list.get(i).getSDT());
								model.addRow(row);
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		});
	}

	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = KhachHang.getALL();

		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaKH()));
			row.add(list.get(i).getTenKH());
			row.add(list.get(i).getDiaChi());
			row.add(list.get(i).getSDT());
			model.addRow(row);
		}
	}

	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
