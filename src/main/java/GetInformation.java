import ThreadClass.GetTextThread;
import ThreadPool.ThreadPoolUtils;
import model.TextMessage;
import service.TextService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GetInformation {
    public static void main(String[] args) throws InterruptedException, IOException {
        Map<String,String> text = new ConcurrentHashMap<String, String>();
        ThreadPoolUtils.Startup(text);
        if (text.size() > 0){
            for (Map.Entry<String, String> entry : text.entrySet()) {
                TextMessage textMessage1 = new TextMessage();
                textMessage1.setName(entry.getKey());
                textMessage1.setContent(entry.getValue());
                TextService textService = new TextService();
                textService.savetext(textMessage1);
            }
        }

    }

}
