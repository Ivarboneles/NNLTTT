package GUI;

import java.awt.Color;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.CTHoaDon;

public class ListChiTietCuaHD extends JFrame {

	private static final long serialVersionUID = -7745856333189249453L;

	static DefaultTableModel model = new DefaultTableModel();

	static JTable table = new JTable(model) {

		private static final long serialVersionUID = -6171471209298150283L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	static JScrollPane sp = new JScrollPane(table);
	
	List<CTHoaDon> list = new ArrayList<CTHoaDon>();
	
	int id = -1;

	public ListChiTietCuaHD(int id) {

		this.id = id;
		JPanel pnTable = new JPanel();
		pnTable.setBounds(30, 20, 520, 340);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Table ",
				TitledBorder.CENTER, TitledBorder.TOP);
		titledBorder.setTitleColor(Color.white);

		pnTable.setBorder(titledBorder);
		pnTable.setOpaque(false);
		pnTable.setLayout(null);

		// ------ Table -----//
		Vector<String> column = new Vector<String>();
		
		column.add("Number");
		column.add("Service");
		column.add("Price");
		column.add("Employee");

		model.setColumnIdentifiers(column);

		table.setBounds(1, 1, 478, 287);

		// ------- Scroll Panel -----//

		sp.setBounds(20, 30, 480, 290);

		pnTable.add(sp);
		// ----------------------//

		add(pnTable);

		// ------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 600, 400);

		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgListDetail.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));

		labeltong.setIcon(avtstop);

		add(labeltong);

		setTitle("Form List Detail");
		setSize(600, 400);
		setLayout(null);
		setVisible(true);
		
		//-------------- Load Data ------------//
		try {
			Load();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void Load() throws ClassNotFoundException, SQLException {
		clear();
		list = CTHoaDon.getALLbyID(id);
		
		for(int i =0; i<list.size(); i++) {
			Vector<String> row = new Vector<String>();
			row.add(String.valueOf(i+1));
			row.add(String.valueOf(list.get(i).getDichvu()));
			row.add(String.valueOf(list.get(i).getDichvu().getGiaDV()));
			row.add(String.valueOf(list.get(i).getNVPhuTrach()));
			
			model.addRow(row);
		}
		
	}
	
	void clear() {
		while(model.getRowCount() > 0)
		{
			model.removeRow(0);
		}
	}
}
