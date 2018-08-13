package api;

import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

public class Request {

    public static String get(String url) throws IOException {
        String USER_AGENT = "CompuServe Classic/1.22";
        String request = "http://sis.rutgers.edu/soc/";
        request += url;
        URL obj = new URL(request);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(100000);
        con.setReadTimeout(100000);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // Authorization: Bearer ACCESS_TOKEN
        //con.setRequestProperty("Authorization","Bearer "+ Genius.accessToken);
        int responseCode = con.getResponseCode();

        InputStream unGzipped = decompressStream(con.getInputStream());

        BufferedReader in = new BufferedReader(new InputStreamReader(unGzipped));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();



        return response.toString();
    }

    public static void post(String data, String url) throws IOException {

        System.out.println(data);
        CloseableHttpClient hc = HttpClients.createDefault();

       //final HttpPost postMethod = new HttpPost(url);
        //postMethod.addRequestHeader("Content-Type", "application/json");
        //postMethod.setEntity();

        StringEntity requestEntity = new StringEntity(
                data,
                ContentType.APPLICATION_JSON);

        HttpPost postMethod = new HttpPost(url);
        postMethod.setEntity(requestEntity);

        HttpResponse rawResponse = hc.execute(postMethod);





//        String USER_AGENT = "CompuServe Classic/1.22";
//        URL url = new URL("http://localhost:3000/projects/rusnipe/local");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setConnectTimeout(5000);//5 secs
//        connection.setReadTimeout(5000);//5 secs
//
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestProperty("User-Agent", USER_AGENT);
//        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//        out.write(data.getBytes());
//        out.flush();
//        out.close();
//
//        int res = connection.getResponseCode();
//
//        System.out.println(res);
//
//
//        InputStream is = connection.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        String line = null;
//        while((line = br.readLine() ) != null) {
//            //System.out.println(line);
//        }
//        connection.disconnect();
    }

    public static InputStream decompressStream(InputStream input) throws IOException {
        PushbackInputStream pb = new PushbackInputStream( input, 2 );
        byte [] signature = new byte[2];
        int len = pb.read( signature );
        pb.unread( signature, 0, len );
        if( signature[ 0 ] == (byte) 0x1f && signature[ 1 ] == (byte) 0x8b )
            return new GZIPInputStream( pb );
        else
            return pb;
    }


}