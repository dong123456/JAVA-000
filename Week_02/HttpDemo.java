import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


/*
http 测试
*/
public class HttpDemo {

    private final HttpClient client =  HttpClientBuilder.create().build();

    public static void main(String[] args) {
        HttpDemo.getHttpObject();
    }

    public static String getHttpObject() {
        HttpGet get = new HttpGet("http://localhost:8088/api/hello");

        HttpResponse response = this.client.execute(get);
        System.out.println(response.getStatusCode());
        System.out.println(EntityUtils.toString(Response.getEntity()));
    }

}