package web;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import web.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAdminServer//开启对于其他应用的监控
public class ExampleApplication {

    public static final Map<String,String> params = new HashMap<String, String>();

    public static void main(String[] args) {
        new SpringApplicationBuilder(ExampleApplication.class).web(true).run(args);

      /*  Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    String response = HttpUtil.httpGet(params, "http://127.0.0.1:7003/compute");
                    System.out.println(i + "**7003 response = " + response);
                }
            }
        };


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String response = HttpUtil.httpGet(params, "http://127.0.0.1:7002/compute");
                    System.out.println(i + "**7002 response = " + response);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread thread2 = new Thread(runnable2);
        thread2.start();
*/

    }

}
