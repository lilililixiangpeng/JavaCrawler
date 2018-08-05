package IPPool.IPCheck;


import IPPool.Model.IPMessage;
import IPPool.UserAgentUtils.UserAgent;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lixiangpeng on 2018/7/21.
 */
public class HttpTests {
    private static Logger logger = Logger.getLogger(HttpTests.class.getName());

    public static void httpsRequest(List<IPMessage> ipMessages1){
        for (int i = 0;i < ipMessages1.size();i++){
            String ipaddress = ipMessages1.get(i).getIPAddress();
            int port = Integer.parseInt(ipMessages1.get(i).getIPPort());
            try{
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = null;
                HttpHost proxy = new HttpHost(ipaddress, port);
                RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000).
                        setSocketTimeout(3000).build();
                HttpGet httpGet = new HttpGet("https://www.baidu.com");
                httpGet.setConfig(config);
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                        "q=0.9,image/webp,*/*;q=0.8");
                httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
                httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
                httpGet.setHeader("User-Agent", UserAgent.GetUserAgent());
                response = httpClient.execute(httpGet);

            }catch(Exception e){
                e.printStackTrace();
                ipMessages1.remove(ipMessages1.get(i));
                logger.warning(ipMessages1.get(i).getIPAddress()+"不可用！");
                i--;
            }
        }

    }


}
