package ThreadPool;

import PersistenceUtils.Persistence;
import Redis.TextRedis;
import ThreadClass.GetTextThread;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lixiangpeng on 2018/8/5.
 */
public class ThreadPoolUtils {

    //加载配置文件
    private static ResourceBundle rb = ResourceBundle.getBundle("Config");
    private static int threadNum;
    private static int pageNum;
    static {
        threadNum = Integer.parseInt(rb.getString("threadNum"));
        pageNum = Integer.parseInt(rb.getString("pageNum"));
    }
    private static ExecutorService crawlerPool = Executors.newFixedThreadPool(threadNum);
    private static TextRedis redis = new TextRedis();

    public static void Startup(Map<String,String> text){
        redis.deleteKey("Text");
        int singlePageNum = pageNum/threadNum;
        int num = 1;
        for (int i=0; i<threadNum; i++){
            crawlerPool.submit(new GetTextThread(text, singlePageNum, num));
            num += singlePageNum;
        }
        crawlerPool.submit(new Persistence());
        crawlerPool.shutdown();
    }
}
