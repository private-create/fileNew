package com.test.fileupload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {
        try {

            String boundary = "-------------------------7db21231231de";
            String format = "\r\n";

            String fname ="D:\\test\\1.jpg";//要上传的文件
            File file = new File(fname);

            URL url = new URL("http://127.0.0.1:8080/upload/file");

            HttpURLConnection conn =(HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setDoInput(true);

            conn.setDoOutput(true);

            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setConnectTimeout(50000);
            conn.setRequestProperty("Content-Type", "multipart/form-data; " +"boundary="+boundary);

            conn.connect();

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            DataInputStream in = new DataInputStream(new FileInputStream(file));

            out.write(format.getBytes(StandardCharsets.UTF_8));
            out.write(format.getBytes(StandardCharsets.UTF_8));

            out.write(("--"+boundary).getBytes(StandardCharsets.UTF_8));
            out.write(format.getBytes(StandardCharsets.UTF_8));

            String content = "Content-Disposition: form-data; " + "name="+"\"" + "file" +"\"; "  + "filename="+ "\"" + file.getName()+"\"";
            out.write(content.getBytes(StandardCharsets.UTF_8));
            out.write(format.getBytes(StandardCharsets.UTF_8));

            String type = "Content-Type: text/plain";
            out.write(type.getBytes(StandardCharsets.UTF_8));
            out.write(format.getBytes(StandardCharsets.UTF_8));
            out.write(format.getBytes(StandardCharsets.UTF_8));
//            out.write("This is file1.".getBytes(StandardCharsets.UTF_8));

            int bytes = 0;
            byte[] bufferOut = new byte[2048];

            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.write(format.getBytes(StandardCharsets.UTF_8));
            out.write(("--"+boundary).getBytes(StandardCharsets.UTF_8));
            in.close();

            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("---line---"+line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
