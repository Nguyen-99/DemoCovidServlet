package com.nguyenz.file_io;

import com.nguyenz.model.Patient;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PatientExcelIO {
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void writeExcelFile(String fileName, List<Patient> patientList){
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            Sheet sheet = workbook.createSheet("sheet1");
            sheet.setColumnWidth(0, 2000);
            sheet.setColumnWidth(1, 7500);
            sheet.setColumnWidth(2, 2500);
            sheet.setColumnWidth(3, 5000);
            sheet.setColumnWidth(4, 9000);
            sheet.setColumnWidth(5, 5000);
            int rowNum = 0;
            Cell cell;
            Row row;

            HSSFCellStyle style = createStyleForTitle(workbook);
            row = sheet.createRow(rowNum);
            // Id
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Id");
            cell.setCellStyle(style);
            //Name
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Name");
            cell.setCellStyle(style);
            //Gender
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Gender");
            cell.setCellStyle(style);
            //DateOfBirth
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("DateOfBirth");
            cell.setCellStyle(style);
            //Address
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Address");
            cell.setCellStyle(style);
            //Status
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Status");
            cell.setCellStyle(style);
            // Data
            for (Patient patient : patientList) {
                rowNum++;
                row = sheet.createRow(rowNum);

                //Id
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(patient.getId());
                //Name
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(patient.getName());
                // Gender
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(patient.getStringGender());
                // DateOfBirth
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(patient.getStringDob());
                //Address
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(patient.getAddress());
                //Status
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(patient.getLastStatus().getName());
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
