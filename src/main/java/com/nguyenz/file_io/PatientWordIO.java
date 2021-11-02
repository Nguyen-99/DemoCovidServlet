package com.nguyenz.file_io;

import com.nguyenz.model.Patient;
import com.nguyenz.model.Status;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PatientWordIO {
    public static void writeWordFile(String fileName, Patient patient){
        List<String> list = new ArrayList<>();
        list.add("Id:"+patient.getId());
        list.add("Name:"+patient.getName());
        list.add("Gender:"+patient.getStringGender());
        list.add("DateOfBirth:"+patient.getStringDob());
        list.add("Address:"+patient.getAddress());
        list.add("Status:"+patient.getLastStatus().getName());

        XWPFDocument document = new XWPFDocument();
        try {
            OutputStream outputStream = new FileOutputStream(fileName);
            for (String s : list) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setBold(true);
                run.setText(s);
            }
            // Creating Table
            XWPFTable tab = document.createTable();
            XWPFTableRow row = tab.getRow(0); // First row
            //
            XWPFParagraph p1 = row.getCell(0).getParagraphs().get(0);
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setText("Status");

            // write to first row, second column
            XWPFParagraph p2 = row.addNewTableCell().getParagraphs().get(0);
            p2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r2 = p2.createRun();
            r2.setBold(true);
            r2.setText("StartDate");

            // write to first row, third column
            XWPFParagraph p3 = row.addNewTableCell().getParagraphs().get(0);
            p3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r3 = p3.createRun();
            r3.setBold(true);
            r3.setText("EndDate");
            List<Status> statusList = patient.getStatusList();
            for (Status status:statusList) {
                row = tab.createRow();
                row.getCell(0).setText(status.getName());
                row.getCell(1).setText(status.getStringDate(status.getStartDate()));
                row.getCell(2).setText(status.getStringDate(status.getEndDate()));
            }
            document.write(outputStream);
            outputStream.close();
            document.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
