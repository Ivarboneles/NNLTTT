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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.spire.doc.CellWidthType;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableRow;
import com.spire.doc.documents.BorderStyle;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.DefaultTableStyle;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.TableRowHeightType;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.formatting.ParagraphFormat;

import Model.NhanVien;

public class FormReportEmployee extends JFrame {

	private static final long serialVersionUID = 1145500728629135598L;

	String timeStamp = "";
	String printStart = "";
	String printEnd = "";
	int avg = 0;

	int[] reportDaLap = { 0, 0 };
	int[] reportDaLam = { 0, 0 };
	NhanVien nhanvien = new NhanVien();
	
	JTextField tfTenFile = new JTextField();
	String tenNV = "";

	public FormReportEmployee(String tenNV, String timeStamp,String printStart,String printEnd,int[] reportDaLap,int[] reportDaLam ,NhanVien nhanvien) {
		this.tenNV = tenNV;
		this.timeStamp = timeStamp;
		this.printStart = printStart;
		this.printEnd = printEnd;
		this.reportDaLap = reportDaLap;
		this.reportDaLam = reportDaLam;
		this.nhanvien = nhanvien;
		JPanel pn = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Report ",
				TitledBorder.CENTER, TitledBorder.TOP);

		pn.setBorder(titledBorder);
		pn.setOpaque(false);
		pn.setLayout(null);

		pn.setBounds(10, 10, 380, 550);

		JLabel lbBaoCao = new JLabel("B??o C??o Doanh Thu", SwingConstants.CENTER);
		lbBaoCao.setFont(new Font("SVN-Franko", Font.BOLD, 20));
		lbBaoCao.setBounds(10, 20, 360, 30);
		
		JLabel lbDV = new JLabel("C???a Nh??n Vi??n "+ tenNV, SwingConstants.CENTER);
		lbDV.setFont(new Font("SVN-Franko", Font.BOLD, 16));
		lbDV.setBounds(10, 50, 360, 30);

		JLabel lbNgayLap = new JLabel("Ng??y L???p B??o C??o:  " + timeStamp);
		lbNgayLap.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbNgayLap.setBounds(70, 100, 360, 30);

		JLabel lbStart = new JLabel("Ng??y B???t ?????u:  " + printStart);
		lbStart.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbStart.setBounds(70, 140, 360, 30);

		JLabel lbEnd = new JLabel("Ng??y K???t Th??c:  "+ printEnd);
		lbEnd.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbEnd.setBounds(70, 180, 360, 30);

		JLabel lbSLHDDaLap = new JLabel("S??? L?????ng Ho?? ????n ???? L???p:  "+ String.valueOf(reportDaLap[1]));
		lbSLHDDaLap.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbSLHDDaLap.setBounds(70, 220, 360, 30);

		JLabel lbTongTienDaLap = new JLabel("T???ng Ti???n Ho?? ????n ???? L???p:  "+FormatMoney(reportDaLap[0]) + " VND");
		lbTongTienDaLap.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTongTienDaLap.setBounds(70, 260, 360, 30);

		JLabel lbSLHDDaLam = new JLabel("S??? L?????ng Ho?? ????n ???? L??m:  "+FormatMoney(reportDaLam[1]));
		lbSLHDDaLam.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbSLHDDaLam.setBounds(70, 300, 360, 30);
		
		JLabel lbTrungBinhDaLam = new JLabel("T???ng Ti???n Ho?? ????n ???? L??m:  "+FormatMoney(reportDaLam[0]) + " VND");
		lbTrungBinhDaLam.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTrungBinhDaLam.setBounds(70, 340, 360, 30);

		pn.add(lbBaoCao);
		pn.add(lbDV);
		pn.add(lbNgayLap);
		pn.add(lbStart);
		pn.add(lbEnd);
		pn.add(lbSLHDDaLap);
		pn.add(lbTongTienDaLap);
		pn.add(lbSLHDDaLam);
		pn.add(lbTrungBinhDaLam);
		add(pn);

		// --------- panel Backup --------//

		JPanel pnBK = new JPanel();
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Backup File ", TitledBorder.CENTER, TitledBorder.TOP);

		pnBK.setBorder(titledBorder1);
		pnBK.setOpaque(false);
		pnBK.setLayout(null);

		pnBK.setBounds(20, 390, 340, 150);

		JLabel lbTenFile = new JLabel("T??n File");
		lbTenFile.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTenFile.setBounds(40, 20, 60, 30);

		
		tfTenFile.setBounds(100, 25, 200, 30);

		JButton btnSave = new JButton("Export");
		btnSave.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 18));
		btnSave.setBounds(120, 65, 120, 40);

		JLabel lbPath = new JLabel("Path: /Users/kietnguyen/Documents/");
		lbPath.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 12));
		lbPath.setBounds(80, 110, 200, 30);

		pnBK.add(lbTenFile);
		pnBK.add(tfTenFile);
		pnBK.add(btnSave);
		pnBK.add(lbPath);

		pn.add(pnBK);

		// ------ background --------//
		JLabel labeltong = new JLabel();
		labeltong.setBounds(0, 0, 400, 600);
		ImageIcon avtstop = new ImageIcon(new ImageIcon(this.getClass().getResource("/bgReport.jpeg")).getImage()
				.getScaledInstance(labeltong.getWidth(), labeltong.getHeight(), Image.SCALE_SMOOTH));
		labeltong.setIcon(avtstop);
		add(labeltong);

		setTitle("Form Report");
		setSize(400, 600);
		setLayout(null);
		setVisible(true);

		// --------- Event ---------//
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Document document = new Document();

				// Add a section
				Section section = document.addSection();

				// Add a heading
				Paragraph heading = section.addParagraph();
				heading.appendText("B??o C??o Doanh Thu");

				Paragraph texttenDV = section.addParagraph();
				texttenDV.appendText("C???a Nh??n Vi??n "+tenNV);
				ParagraphFormat format1 = texttenDV.getFormat();
				format1.setHorizontalAlignment(HorizontalAlignment.Center);
				
				Paragraph tab0 = section.addParagraph();
				tab0.appendText(" ");

				// Add table
				Table table = section.addTable(true);
				String[][] data = { new String[] { "Ng??y L???p B??o C??o:", "\t\t" + timeStamp },
						new String[] { "Ng??y B???t ?????u:", "\t\t" + printStart },
						new String[] { "Ng??y Cu???i:", "\t\t" + printEnd },
						new String[] { "S??? L?????ng Ho?? ????n ???? L???p:", "\t\t" + String.valueOf(reportDaLap[1]) },
						new String[] { "T???ng Ti???n Ho?? ????n ???? L???p:", "\t\t" + FormatMoney(reportDaLap[0]) + " VND" },
						new String[] { "S??? L?????ng Ho?? ????n ???? L??m:", "\t\t" + String.valueOf(reportDaLam[1]) },
						new String[] { "T???ng Ti???n Ho?? ????n ???? L??m:", "\t\t" + FormatMoney(reportDaLam[0]) + " VND" },
						 };

				table.resetCells(data.length + 1, 2);
				// Set the first row as table header and add data
				table.getRows().get(0).setHeight(0.5F);
				TableRow row = table.getRows().get(0);
				row.isHeader(false);
				row.setHeightType(TableRowHeightType.Exactly);
				for (int i = 0; i < 2; i++) {
					row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
					Paragraph p = row.getCells().get(i).addParagraph();
					p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
					p.appendText("\t\t\t\t\t\t\t\t\t\t");

				}

				// Add data to the rest of rows
				for (int r = 0; r < data.length; r++) {
					TableRow dataRow = table.getRows().get(r + 1);
					dataRow.setHeight(25);
					dataRow.setHeightType(TableRowHeightType.Exactly);
					dataRow.getRowFormat().setBackColor(Color.white);

					for (int c = 0; c < data[r].length; c++) {
						dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
						dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);

					}
				}

				for (int i = 0; i < table.getRows().getCount(); i++) {
					table.get(i, 0).setCellWidth(600, CellWidthType.Percentage);
					table.get(i, 1).setCellWidth(600, CellWidthType.Percentage);
				}

				table.applyStyle(DefaultTableStyle.Dark_List);

				// set left border of table
				table.getTableFormat().getBorders().getTop().setBorderType(BorderStyle.None);
				table.getTableFormat().getBorders().getRight().setBorderType(BorderStyle.None);
				table.getTableFormat().getBorders().getLeft().setBorderType(BorderStyle.None);
				table.getTableFormat().getBorders().getBottom().setBorderType(BorderStyle.None);
				table.getTableFormat().getBorders().getVertical().setBorderType(BorderStyle.None);

				table.getFirstRow().getRowFormat().getBorders().getHorizontal().setBorderType(BorderStyle.None);
				table.getTableFormat().getBorders().getHorizontal().setBorderType(BorderStyle.Single);
				table.getTableFormat().getBorders().getHorizontal().setLineWidth(1.5F);
				table.getTableFormat().getBorders().getHorizontal().setColor(Color.black);

				Paragraph tab1 = section.addParagraph();
				tab1.appendText(" ");

				Paragraph SignalEmployee = section.addParagraph();
				SignalEmployee.appendText("Nh??n Vi??n L???p B??o C??o ");
				ParagraphFormat format3 = SignalEmployee.getFormat();
				format3.setHorizontalAlignment(HorizontalAlignment.Right);

				Paragraph tab2 = section.addParagraph();
				tab2.appendText(" ");

				Paragraph Employee = section.addParagraph();
				Employee.appendText(nhanvien.getTenNV() + "\t");
				ParagraphFormat format2 = Employee.getFormat();
				format2.setHorizontalAlignment(HorizontalAlignment.Right);

				// so that it is easily distinguishable
				heading.applyStyle(BuiltinStyle.Title);
				SignalEmployee.applyStyle(BuiltinStyle.Heading_3);
				texttenDV.applyStyle(BuiltinStyle.Heading_2);

				// Customize a paragraph style
				ParagraphStyle style = new ParagraphStyle(document);

				// Paragraph name
				style.setName("paraStyle");
				// Paragraph format
				style.getCharacterFormat().setFontName("Arial");
				// Paragraph font size
				style.getCharacterFormat().setFontSize(14f);
				// Adding styles using inbuilt method
				document.getStyles().add(style);

				// Iteration for white spaces
				for (int i = 0; i < section.getParagraphs().getCount(); i++) {

					// Automatically add whitespaces
					// to every paragraph in the file
					section.getParagraphs().get(i).getFormat().setAfterAutoSpacing(true);
				}

				// Save the document
				document.saveToFile("/Users/kietnguyen/Documents/"+tfTenFile.getText()+".docx", FileFormat.Docx);

				JOptionPane.showMessageDialog(pn, "L???p Th??nh C??ng ");
			}
		});

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
