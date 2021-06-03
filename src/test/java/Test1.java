import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Test1 {

    public void sendFiles(String path,String requestPath){

        String boundary = "--test";
        String format = "\r\n";
        String formatTwo = "\r\n\r\n";
        try {
            URL url = new URL(requestPath);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(4000);
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Charset","UTF-8");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
            conn.connect();

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(formatTwo.getBytes(StandardCharsets.UTF_8));
            InputStream in =null;
            File file = new File(path);

            for(File file1 : file.listFiles()){

                if(file1.isDirectory()){continue;}

                out.write(("--"+boundary+format).getBytes(StandardCharsets.UTF_8));
                String contentType = "Content-Disposition: form-data; name=\"files\"; filename="+"\""+file1.getName()+"\"";
                out.write((contentType+format).getBytes(StandardCharsets.UTF_8));
                contentType = "Content-Type: text/plain";
                out.write((contentType+formatTwo).getBytes(StandardCharsets.UTF_8));

                in = new FileInputStream(file1);
                byte[] by = new byte[1024];
                int temp = -1;
                while ((temp=in.read(by))!=-1){
                    out.write(by);
                }
                out.write(format.getBytes(StandardCharsets.UTF_8));
            }
            out.write(("--"+boundary).getBytes(StandardCharsets.UTF_8));
            in.close();
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("---line---"+line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.sendFiles("D:"+File.separator+"test","http://127.0.0.1:8080/upload/files");
    }
}
