package com.microservice.productService.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.microservice.productService.entity.Product;
import com.microservice.productService.payload.response.ProductResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ProductResponse> listProducts;

    public ExcelReporter(List<ProductResponse> listProducts) {
        this.listProducts = listProducts;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Products");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();


        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "productName", style);
        createCell(row, 1, "productId", style);
        createCell(row, 2, "quantity", style);
        createCell(row, 3, "price", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SPARSE_DOTS);


        CellStyle style1 = workbook.createCellStyle();
        style1.setFont(font);

        style1.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style1.setFillPattern(FillPatternType.SPARSE_DOTS);


        for (ProductResponse product : listProducts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, product.getProductName(), style);
            createCell(row, columnCount++, product.getProductId(), style1);
            createCell(row, columnCount++, product.getQuantity(), style);
            createCell(row, columnCount++, product.getPrice(), style1);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}