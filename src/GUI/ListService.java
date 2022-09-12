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

import Model.DichVu;

public class ListService extends JFrame {

	private static final long serialVersionUID = 1511624696694559621L;

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model);

	static JScrollPane sp = new JScrollPane(table);

	static JComboBox<String> cbbFind = new JComboBox<String>();

	List<DichVu> list = new ArrayList<DichVu>();

	public ListService() {

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
		column.add("Price");

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

		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgListService.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));

		labeltong.setIcon(avtstop);

		add(labeltong);
		
		setTitle("Form List Service");
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
						list = DichVu.getbyName(text.trim());
						for (int i = 0; i < list.size(); i++) {
							Vector<String> row = new Vector<String>();

							row.add(String.valueOf(list.get(i).getMaDV()));
							row.add(list.get(i).getTenDV());
							row.add(String.valueOf(list.get(i).getGiaDV()));

							model.addRow(row);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
	}

	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = DichVu.getALL();

		for (int i = 0; i < list.size(); i++) {
			Vector<String> row = new Vector<String>();

			row.add(String.valueOf(list.get(i).getMaDV()));
			row.add(list.get(i).getTenDV());
			row.add(String.valueOf(list.get(i).getGiaDV()));

			model.addRow(row);
		}

	}

	void clear() {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}
}
