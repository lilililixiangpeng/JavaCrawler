import model.Text;
import request.HttpRequest;
import service.TextService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GetInformation {
    public static void main(String[] args) throws InterruptedException, IOException {
        /*Map<String,String> text = new ConcurrentHashMap<String, String>();
        saveinformation(text);
        for (Map.Entry<String, String> entry : text.entrySet()) {
            Text text1 = new Text();
            text1.setName(entry.getKey());
            text1.setContent(entry.getValue());
            TextService textService = new TextService();
            textService.savetext(text1);
        }*/
        String test = HttpRequest.sendGet("http://www.xicidaili.com/nn/1");

    }

   /* public static void saveinformation(Map<String,String> text) throws InterruptedException {
        int page = 1;
        for (int i=0;i<4;i++){
            GetTextThread getTextThread = new GetTextThread(text,page);
            getTextThread.start();
            getTextThread.join();
            page += 50;
        }

    }*/

}
