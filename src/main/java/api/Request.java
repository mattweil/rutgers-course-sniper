package api;

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

    public static InputStream decompressStream(InputStream input) throws IOException {
        PushbackInputStream pb = new PushbackInputStream( input, 2 ); //we need a pushbackstream to look ahead
        byte [] signature = new byte[2];
        int len = pb.read( signature ); //read the signature
        pb.unread( signature, 0, len ); //push back the signature to the stream
        if( signature[ 0 ] == (byte) 0x1f && signature[ 1 ] == (byte) 0x8b ) //check if matches standard gzip magic number
            return new GZIPInputStream( pb );
        else
            return pb;
    }


}