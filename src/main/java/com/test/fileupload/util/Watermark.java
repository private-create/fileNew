package com.test.fileupload.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;


public class Watermark  extends PdfPageEventHelper {

    Font FONT = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new GrayColor(0.95f));
    private String waterCont;//水印内容
    public Watermark() {

    }

    public Watermark(String waterCont) {
        this.waterCont = waterCont;
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            this.FONT=new  Font(bfChinese, 30, Font.BOLD, new GrayColor(0.95f));;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        for(int i=0 ; i<5; i++) {
            for(int j=0; j<5; j++) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER,
                        new Phrase(this.waterCont == null ? "HELLO WORLD" : this.waterCont, FONT),
                        (50.5f+i*350),
                        (40.0f+j*150),
                        writer.getPageNumber() % 2 == 1 ? 45 : -45);
            }
        }
    }

}
