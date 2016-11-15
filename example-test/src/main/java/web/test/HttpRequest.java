package web.test;

import org.apache.http.client.HttpClient;
import web.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/15.
 */
public class HttpRequest {

    public static void main(String[] args) {
        Map<String,String> params = new HashMap<String, String>();

        for (int i=0;i<20;i++){
            String response = HttpUtil.httpGet(params, "http://127.0.0.1:7003/compute");
            System.out.println("response = " + response);
        }


    }
}
