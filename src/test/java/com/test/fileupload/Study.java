package com.test.fileupload;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Study {

    public void createPDF(String filename) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.addTitle("example of PDF");
            document.open();
            PdfPTable table = createTable(writer);
            document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
    public static PdfPTable createTable(PdfWriter writer) throws DocumentException, IOException{
//        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bfChinese, 10, Font.NORMAL);
        PdfPTable table = new PdfPTable(2);//生成一个两列的表格
        PdfPCell cell;
        int size = 20;
        cell = new PdfPCell(new Paragraph("姓名",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("专业",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("张三",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("计算机科学与技术",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("评分",font));
        cell.setColspan(1);//设置所占列数
        cell.setRowspan(2);//合并行
        cell.setFixedHeight(size*2);//设置高度
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("合格",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("优秀",font));
        cell.setFixedHeight(size);
        table.addCell(cell);
        return table;
    }




    public static void main(String[] args) throws IOException, DocumentException {

        Study pdf = new Study();
        String filename = "D:/test/pdf/testTable1.pdf";
        pdf.createPDF(filename);
        System.out.println("打印完成");
    }
}
