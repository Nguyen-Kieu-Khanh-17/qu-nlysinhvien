package com.example.studentmanager.Service;

import com.example.studentmanager.Entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

    public byte[] exportToPDF(List<Student> students) throws DocumentException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();
        // Use a default font that supports Unicode if possible, but basic FontFactory supports ASCII.
        // For robust Vietnamese, a TTF font should be loaded. Here we use Helvetica as a fallback.
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph title = new Paragraph("List of Students", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 2.0f, 4.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, students);

        document.add(table);
        document.close();

        return out.toByteArray();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Student ID", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Avatar URL", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<Student> students) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        for (Student student : students) {
            table.addCell(new Phrase(String.valueOf(student.getId()), font));
            // Unicode characters might not render properly with Helvetica, but we try our best
            table.addCell(new Phrase(student.getName(), font));
            table.addCell(new Phrase(String.valueOf(student.getAge()), font));
            table.addCell(new Phrase(student.getEmail(), font));
            table.addCell(new Phrase(student.getAvatar() != null ? "Yes" : "No", font));
        }
    }

    public byte[] exportToExcel(List<Student> students) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            XSSFSheet sheet = workbook.createSheet("Students");

            writeExcelHeaderLine(workbook, sheet);
            writeExcelDataLines(workbook, sheet, students);

            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void writeExcelHeaderLine(XSSFWorkbook workbook, XSSFSheet sheet) {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        createCell(row, 0, "Student ID", style, sheet);
        createCell(row, 1, "Name", style, sheet);
        createCell(row, 2, "Age", style, sheet);
        createCell(row, 3, "Email", style, sheet);
        createCell(row, 4, "Avatar", style, sheet);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style, XSSFSheet sheet) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeExcelDataLines(XSSFWorkbook workbook, XSSFSheet sheet, List<Student> students) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (Student student : students) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, student.getId(), style, sheet);
            createCell(row, columnCount++, student.getName(), style, sheet);
            createCell(row, columnCount++, student.getAge(), style, sheet);
            createCell(row, columnCount++, student.getEmail(), style, sheet);
            createCell(row, columnCount++, student.getAvatar() != null ? "Yes" : "No", style, sheet);
        }
    }
}
