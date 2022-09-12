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

public class FormReportDate extends JFrame {

	private static final long serialVersionUID = 7053340952826672344L;

	String timeStamp = "";
	String printStart = "";
	String printEnd = "";
	int avg = 0;

	int[] report = { 0, 0 };
	NhanVien nhanvien = new NhanVien();
	
	JTextField tfTenFile = new JTextField();

	public FormReportDate(String timeStamp,String printStart,String printEnd,int avg,int[] report,NhanVien nhanvien) {
		this.timeStamp = timeStamp;
		this.printStart = printStart;
		this.printEnd = printEnd;
		this.avg = avg;
		this.report = report;
		this.nhanvien = nhanvien;
		JPanel pn = new JPanel();
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), " Report ",
				TitledBorder.CENTER, TitledBorder.TOP);

		pn.setBorder(titledBorder);
		pn.setOpaque(false);
		pn.setLayout(null);

		pn.setBounds(10, 10, 380, 550);

		JLabel lbBaoCao = new JLabel("Báo Cáo Doanh Thu", SwingConstants.CENTER);
		lbBaoCao.setFont(new Font("SVN-Franko", Font.BOLD, 20));
		lbBaoCao.setBounds(10, 20, 360, 30);

		JLabel lbNgayLap = new JLabel("Ngày Lập Báo Cáo:  " + timeStamp);
		lbNgayLap.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbNgayLap.setBounds(50, 60, 360, 30);

		JLabel lbStart = new JLabel("Ngày Bắt Đầu:  " + printStart);
		lbStart.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbStart.setBounds(50, 100, 360, 30);

		JLabel lbEnd = new JLabel("Ngày Kết Thúc:  "+ printEnd);
		lbEnd.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbEnd.setBounds(50, 140, 360, 30);

		JLabel lbSLHD = new JLabel("Số Lượng Hoá Đơn:  "+ String.valueOf(report[1]));
		lbSLHD.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbSLHD.setBounds(50, 180, 360, 30);

		JLabel lbTongTien = new JLabel("Tổng Tiền:  "+FormatMoney(report[0]) + " VND");
		lbTongTien.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTongTien.setBounds(50, 220, 360, 30);

		JLabel lbTrungBinh = new JLabel("Trung Bình Mỗi Ngày:  "+FormatMoney(avg) + " VND");
		lbTrungBinh.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTrungBinh.setBounds(50, 260, 360, 30);

		pn.add(lbBaoCao);
		pn.add(lbNgayLap);
		pn.add(lbStart);
		pn.add(lbEnd);
		pn.add(lbSLHD);
		pn.add(lbTongTien);
		pn.add(lbTrungBinh);
		add(pn);

		// --------- panel Backup --------//

		JPanel pnBK = new JPanel();
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				" Backup File ", TitledBorder.CENTER, TitledBorder.TOP);

		pnBK.setBorder(titledBorder1);
		pnBK.setOpaque(false);
		pnBK.setLayout(null);

		pnBK.setBounds(20, 340, 340, 190);

		JLabel lbTenFile = new JLabel("Tên File");
		lbTenFile.setFont(new Font("SVN-Franko", Font.BOLD, 14));
		lbTenFile.setBounds(40, 40, 60, 30);

		
		tfTenFile.setBounds(100, 45, 200, 30);

		JButton btnSave = new JButton("Export");
		btnSave.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 18));
		btnSave.setBounds(120, 100, 120, 40);

		JLabel lbPath = new JLabel("Path: /Users/kietnguyen/Documents/");
		lbPath.setFont(new Font("SVN-Franko", Font.BOLD | Font.ITALIC, 12));
		lbPath.setBounds(80, 150, 200, 30);

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
				heading.appendText("Báo Cáo Doanh Thu");

				Paragraph tab0 = section.addParagraph();
				tab0.appendText(" ");

				// Add table
				Table table = section.addTable(true);
				String[][] data = { new String[] { "Ngày Lập Báo Cáo:", "\t\t" + timeStamp },
						new String[] { "Ngày Bắt Đầu:", "\t\t" + printStart },
						new String[] { "Ngày Cuối:", "\t\t" + printEnd },
						new String[] { "Số Lượng Hoá Đơn:", "\t\t" + String.valueOf(report[1]) },
						new String[] { "Tổng Tiền:", "\t\t" + FormatMoney(report[0]) + " VND" },
						new String[] { "Trung Bình Mỗi Ngày:", "\t\t" + FormatMoney(avg) + " VND" } };

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
				SignalEmployee.appendText("Nhân Viên Lập Báo Cáo ");
				ParagraphFormat format1 = SignalEmployee.getFormat();
				format1.setHorizontalAlignment(HorizontalAlignment.Right);

				Paragraph tab2 = section.addParagraph();
				tab2.appendText(" ");

				Paragraph Employee = section.addParagraph();
				Employee.appendText(nhanvien.getTenNV() + "\t");
				ParagraphFormat format2 = Employee.getFormat();
				format2.setHorizontalAlignment(HorizontalAlignment.Right);

				// so that it is easily distinguishable
				heading.applyStyle(BuiltinStyle.Title);
				SignalEmployee.applyStyle(BuiltinStyle.Heading_3);

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

				JOptionPane.showMessageDialog(pn, "Lập Thành Công ");
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
