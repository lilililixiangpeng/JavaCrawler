import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import request.HttpRequest;

import java.util.Map;

public class GetTextThread extends Thread{
    private Map<String,String> text;
    private int page;

    public GetTextThread(Map<String,String> text,int page){
        this.text = text;
        this.page = page;
    }

    public void run() {
       /* for(int i=page;i<=page+50;i++){
            String param="p="+i+"&c=&t=";
            String result = HttpRequest.sendGet("https://so.gushiwen.org/mingju/default.aspx", param);
            //String result = HttpRequest.sendPost();
            Document doc = Jsoup.parse(result);
            Elements elements = doc.select("[style = float:left;]");
            for (int j=0;j < elements.size();j+=2){
                text.put(elements.get(j+1).text(),elements.get(j).text());
            }
        }*/
    }
}
