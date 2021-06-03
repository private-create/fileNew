package com.test.fileupload.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.mail.util.MailSSLSocketFactory;
import com.test.fileupload.entity.Accountbill;
import com.test.fileupload.mapper.AccountbillMapper;
import com.test.fileupload.service.AccountbillService;
import com.test.fileupload.util.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource({"classpath:static/emailConfig.properties"})
public class AccountbillServiceImpl implements AccountbillService {

    @Value("${host}")
    private String host;
    @Value("${port}")
    private String port;
    @Value("${from}")
    private String form;

    @Value("${user}")
    private String user;

    @Value("${pass}")
    private String pass;

    @Value("${socketFactoryClass}")
    private String socketFactoryClass;

    @Autowired
    private AccountbillMapper accountbillMapper;
    @Override
    public List<Accountbill> queryAccountbillAll() {
        List<Accountbill> accountbills = accountbillMapper.queryAccountbillAll();
        PdfUtils pdfUtils = new PdfUtils();
        List<String> column = new ArrayList<>();
        column.add("主键");column.add("机构代码"); column.add("运输费");column.add("线路名"); column.add("机构名"); column.add("车牌号"); column.add("全票数");column.add("半票");
        column.add("免票数");column.add("席位数"); column.add("检票数");column.add("站务费"); column.add("管理费");
        String fileName = "D:"+ File.separator+"test"+File.separator+"pdf"+File.separator+"account.pdf";
        pdfUtils.generatePdfToFile(fileName,accountbills,column);
        return accountbills;
    }


    @Override
    public boolean sendEmail(){
        boolean flag =false;
        try {
            MailAccount account = new MailAccount();
            account.setHost(host);
            account.setPort(Integer.valueOf(port));
            account.setAuth(true);
            account.setFrom(form);
            account.setUser(user);
            System.out.println(pass.trim());
            account.setPass(pass.trim());
            account.setSslEnable(true);
            account.setSocketFactoryClass(socketFactoryClass);
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            account.setCustomProperty("mail.smtp.ssl.socketFactory", sf);


            MailUtil.send(account, CollUtil.newArrayList("2312635193@qq.com"), "测试", "邮件来自Hutool测试", true, FileUtil.file("D:"+ File.separator+"test"+File.separator+"pdf"+File.separator+"account.pdf"));
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }



    public <T> PdfPTable createTable(PdfWriter writer,List<T> list,T t)  {
        PdfPTable table = null;


        try {
            Field[] fields = t.getClass().getDeclaredFields();
//            BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 10, Font.NORMAL);


            table= new PdfPTable(3);//生成一个两列的表格

            PdfPCell header=new PdfPCell(new Phrase("hello world"));
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBackgroundColor(new BaseColor(216, 218, 220));
            table.addCell(header);
            PdfPCell cell;
            int size = 30;


                cell = new PdfPCell(new Paragraph("主键",font));
                cell.setFixedHeight(size);//设置高度
                table.addCell(cell);

            cell = new PdfPCell(new Paragraph("机构代码",font));
            cell.setFixedHeight(size);//设置高度
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("总金额",font));
            cell.setFixedHeight(size);//设置高度
            table.addCell(cell);
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < fields.length; j++) {

                    fields[j].setAccessible(true);

                    if(fields[j].get(list.get(i))!=null){
                        System.out.println(fields[j].get(list.get(i)));
                        cell = new PdfPCell(new Paragraph(String.valueOf(fields[j].get(list.get(i))),font));
                        cell.setFixedHeight(size);//设置高度
                        table.addCell(cell);
                    }

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return table;
    }
}
