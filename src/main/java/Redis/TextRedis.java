package Redis;

import IPPool.Model.IPMessage;
import IPPool.Model.SerializeUtil;
import IPPool.Redis.RedisDB;
import model.TextMessage;
import redis.clients.jedis.Jedis;

import java.util.List;

import static java.lang.System.out;
import static java.lang.System.setOut;

/**
 * Created by lixiangpeng on 2018/8/5.
 */
public class TextRedis {
    Jedis jedis = RedisDB.getJedis();

    //将ip信息保存在Redis列表中
    public void setIPToList(TextMessage textMessage) {
        String ip = SerializeUtil.serialize(textMessage);
        jedis.rpush("Text", ip);

    }

    //将Redis中保存的对象进行反序列化
    public TextMessage getIPByList() {
        //随机抽取一个代理实例
        int rand = (int)(Math.random()*jedis.llen("Text"));

        Object o = SerializeUtil.unserialize(jedis.lindex("Text", rand));
        if (o instanceof TextMessage) {
            return (TextMessage)o;
        } else {
            out.println("不是TextMessage的实例");
            return null;
        }
    }

    public void deleteKey(String key) {
        jedis.del(key);
    }

    public void close() {
        RedisDB.close(jedis);
    }
}
