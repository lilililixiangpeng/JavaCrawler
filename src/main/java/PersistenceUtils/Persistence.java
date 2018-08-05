package PersistenceUtils;

import IPPool.Model.SerializeUtil;
import IPPool.Redis.RedisDB;
import model.TextMessage;
import redis.clients.jedis.Jedis;
import service.TextService;
import java.io.IOException;

/**
 * Created by lixiangpeng on 2018/8/5.
 */
public class Persistence implements Runnable{

    private Jedis jedis = RedisDB.getJedis();
    private TextService service = new TextService();

    @Override
    public void run() {
        while (jedis.llen("Text") != 0){
            TextMessage message = (TextMessage) SerializeUtil.unserialize(jedis.lpop("Text"));
            try {
                service.savetext(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
