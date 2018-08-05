package ThreadClass;

import IPPool.Model.IPMessage;
import IPPool.Redis.MyRedis;
import IPPool.Startup.MyJob;
import Redis.TextRedis;
import model.TextMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import request.HttpRequest;
import java.util.Map;
import java.util.logging.Logger;

public class GetTextThread implements Runnable{
    private Map<String,String> text;
    private int singlePageNum;
    private int num;
    private Logger logger = Logger.getLogger(Thread.currentThread().getName());
    private TextRedis redis = new TextRedis();
    private MyJob job = new MyJob();
    private MyRedis poolRedis = new MyRedis();

    public GetTextThread(Map<String,String> text, int singlePageNum, int num){
        this.text = text;
        this.singlePageNum = singlePageNum;
        this.num = num;
        job.Start();
    }

    public void run() {
        for(int i=num;i<=num+singlePageNum;i++){
            String param= "p=" + i + "&c=&t=";
            String result = HttpRequest.sendGet("https://so.gushiwen.org/mingju/default.aspx",param);
            if (result == null){
                IPMessage ipMessage= poolRedis.getIPByList();
                result = IPPool.HttpRequest.HttpRequest.getHtml("https://so.gushiwen.org/mingju/default.aspx?"+param,ipMessage.getIPAddress(),ipMessage.getIPPort());
            }else{
                Document doc = Jsoup.parse(result);
                Elements elements = doc.select("[style = float:left;]");
                for (int j=0;j < elements.size();j+=2){
                    text.put(elements.get(j+1).text(),elements.get(j).text());
                    TextMessage textMessage = new TextMessage();
                    textMessage.setName(elements.get(j+1).text());
                    textMessage.setContent(elements.get(j).text());
                    redis.setIPToList(textMessage);
                }
            }

        }
        logger.info(Thread.currentThread().getName() + "爬取完毕！");
    }
}
