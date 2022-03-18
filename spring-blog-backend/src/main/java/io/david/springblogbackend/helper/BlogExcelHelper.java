package io.david.springblogbackend.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.david.springblogbackend.models.Blog;

public class BlogExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "Id", "Title", "Body", "Publised Date", "Updated Date", "Author", "Topic" };
    static String SHEET = "Blog_Sheet";

    public static ByteArrayInputStream blogsToExcel(List<Blog> blogs) {

        // Creates a new workbook and Byte Stream
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            // Creates a sheet in the workbook
            Sheet sheet = workbook.createSheet(SHEET);

            // Creates the header of the Sheet
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            // Populates the cells in the sheet
            int rowIdx = 1;
            for (Blog blog : blogs) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(blog.getId());
                row.createCell(1).setCellValue(blog.getTitle());
                row.createCell(2).setCellValue(blog.getBody());
                row.createCell(3).setCellValue(String.valueOf(blog.getPublishedDate()));
                row.createCell(4).setCellValue(String.valueOf(blog.getUpdatedDate()));
                row.createCell(5).setCellValue(blog.getAuthor());
                row.createCell(6).setCellValue(String.valueOf(blog.getTopic()));
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }

    }

}
