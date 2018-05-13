package bookingRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import controller.SigninController;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * This class use itext api to print receipt on pdf file.
 * @author Narisa and Tanasorn
 *
 */
public class Receipt {

	private Total total = Total.getinstance();
	private static BookingRequest br = BookingRequest.getInstance();
	private String arrive = br.getListFile().get(0).getCheckin();
	private String depart = br.getListFile().get(0).getCheckout();
	private int day = br.getListFile().get(0).getDay();
	private int sum = total.getRoomPrice() + total.showBreakfast() + total.showExtraBed();

	/**
	 * Print receipt on PDF file.
	 */
	public void receipt(String nameUser) {
		Document document = new Document(PageSize.A5);
		FileChooser fileChooser;
		File output = null;

		fileChooser = new FileChooser();
		fileChooser.setInitialFileName("bookingReceipt.pdf");
		fileChooser.setInitialDirectory(new File("src"));
		output = fileChooser.showSaveDialog(new Stage());

		try {
			PdfWriter.getInstance(document, new FileOutputStream(output));
			document.open();

			Paragraph titleShow = new Paragraph("receipt", FontFactory.getFont(FontFactory.HELVETICA, 18));
			titleShow.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(titleShow);

			Paragraph namePdf = new Paragraph(String.format("%-20s %40s", "Name:", nameUser),
					FontFactory.getFont(FontFactory.HELVETICA, 16));
			document.add(namePdf);

			Paragraph arrivePdf = new Paragraph(String.format("%-20s %42s", "Arrive date:", arrive),
					FontFactory.getFont(FontFactory.HELVETICA, 16));
			document.add(arrivePdf);

			Paragraph departPdf = new Paragraph(String.format("%-20s %41s", "Depart date:", depart),
					FontFactory.getFont(FontFactory.HELVETICA, 16));
			departPdf.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(departPdf);

			Paragraph roomPdf = new Paragraph(String.format("%-20s %45s", "Room:", nameR()),
					FontFactory.getFont(FontFactory.HELVETICA, 16));
			roomPdf.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(roomPdf);

			Paragraph dayPdf = new Paragraph(String.format("%-20s %45s", "Day:", day),
					FontFactory.getFont(FontFactory.HELVETICA, 16));
			dayPdf.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(dayPdf);

			String bed = String.format("Add extra-bed %d bed = %30d Baht", total.countExtraBed(), total.showExtraBed());
			Paragraph bedN = new Paragraph(bed, FontFactory.getFont(FontFactory.HELVETICA, 16));
			bedN.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(bedN);

			String bf = String.format("Add Breakfast %d bed = %30d Baht", total.countBreakfast(),
					total.showBreakfast());
			Paragraph breakfastN = new Paragraph(bf, FontFactory.getFont(FontFactory.HELVETICA, 16));
			breakfastN.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(breakfastN);

			String rp = String.format("Total Room: %50d", total.getRoomPrice());
			Paragraph roomT = new Paragraph(rp, FontFactory.getFont(FontFactory.HELVETICA, 16));
			roomT.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(roomT);

			String totalSum = String.format("Total : %60d", sum * day);
			Paragraph sumPdf = new Paragraph(totalSum, FontFactory.getFont(FontFactory.HELVETICA, 16));
			sumPdf.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(sumPdf);

			if (SigninController.checkMember) {
				String totalDiscount = String.format("Total discount: %4g", (sum * day) * 0.85);
				Paragraph discount = new Paragraph(totalDiscount, FontFactory.getFont(FontFactory.HELVETICA, 16));
				discount.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(discount);
			}

			document.close();

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}

	}
	
	public String nameR(){
		String name = "";
		for (String n : total.getNameRoom()) {
			name += " " + n + " ";
		}
		return name;
	}
}