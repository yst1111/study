package com.yst.app.controller.messTest;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * @creator: ly-yangst
 * @date: 2022/4/27
 */
//@SpringBootTest
public class RedisJava {
    //String操作
    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.41.188", 6379);
//        System.out.println(jedis.ping());//测试连接,PING PONG

        //清空
        remove(jedis);

        String k1 = "k1";
        String v1 = "v1";
        jedis.set("k1",k1);
        jedis.expire("k1",15);//key的过期时间设置

//        System.out.println(jedis.get("k1"));//String类型
//        System.out.println(jedis.exists("k1")+","+jedis.exists("v1"));//key值是否存在
//        System.out.println(jedis.type("k1"));//value的类型
//        System.out.println(jedis.type("v1"));//none表示不存在
        jedis.append(k1, "v1V2");
//        System.out.println(jedis.get(k1));//append拼接

        //设置多个key
        String key1 = companyKeyFactory();
        String key2 = companyKeyFactory();
        jedis.mset(key1,key2);
        jedis.expire(key1,15);
        jedis.expire(key2,15);

        //删除
        jedis.del(key2);
        System.out.println(jedis.keys("*"));//查看所有key

        //自增
//        jedis.incr(companyKeyFactory());




    }


    @Test
    //List操作
    void TestList(){

        Jedis jedis = new Jedis("192.168.41.188", 6379,10);
        jedis.select(1);//切换DB
        remove(jedis);

        //从头部插入
        String key1 = companyKeyFactory();
        jedis.lpush(key1,"ok","3","6","hi");//头插
        jedis.rpush(key1,"e","n","d");//尾插
//        System.out.println(jedis.lrange(key1,0,-1));//查询list
//        System.out.println(jedis.llen(key1));//查list长度
        jedis.lpop(key1);//头部删除一个元素
//        System.out.println(jedis.lrange(key1,0,-1));
//        System.out.println(jedis.lpop(key1));//头部删除一个元素,并返回删除的元素
//        System.out.println(jedis.rpop(key1));//尾部删除一个元素,并返回删除的元素
        jedis.expire(key1,15);//key的过期时间设置

    }

    @Test
    //Hash操作
    void HashTest(){

        Jedis jedis = new Jedis("192.168.41.188", 6379);
        remove(jedis);
        jedis.select(2);

        HashMap<String, String> map = new HashMap<>();//key和value必须都是 String
        map.put("k1","1");
        map.put("k2","2");
        jedis.hmset("map1",map);//整个map插入
        jedis.hset("map1","field","value");//按key,value插入
        jedis.expire("map1",200);

        //获取单个数据
        System.out.println(jedis.hget("map1", "k1"));
        //获取多个value
        System.out.println(jedis.hmget("map1", "k1", "field"));
        //获取所有
        System.out.println(jedis.hgetAll("map1"));
        //获取所有的key
        System.out.println(jedis.hkeys("map1"));
        //获取所有的value
        System.out.println(jedis.hvals("map1"));

    }

    @Test
    //Set操作(去重,无序)
    void testSet(){

        Jedis jedis = new Jedis("192.168.41.188", 6379);
        remove(jedis);
        jedis.select(0);

        jedis.sadd("setKey1","a","a","b","1");
        jedis.sadd("setKey1","ed","0");
        jedis.expire("setKey1",100);

        //返回集合
        jedis.select(1);

        jedis.sadd("setKey1","a","a","b","1");
        jedis.sadd("setKey1","ed","0");
        jedis.expire("setKey1",100);

        jedis.sadd("setKey2","a","a","c","1");
        jedis.sadd("setKey2","ed","1");
        jedis.expire("setKey2",100);

        //返回所有元素
        System.out.println(jedis.smembers("setKey1"));//所有元素
        System.out.println(jedis.scard("setKey1"));//元素个数

        //交集,并集,差集(必须在一个DB里边)
        System.out.println("交集 "+jedis.sinter("setKey1", "setKey2"));//交集
        System.out.println("并集 "+jedis.sunion("setKey1", "setKey2"));//并集
        System.out.println("差集 "+jedis.sdiff("setKey2", "setKey1"));//差集

        //移除
        jedis.srem("setKey1","a");
        System.out.println(jedis.smembers("setKey1"));

    }

    @Test
    //Zset有序
    void testZset(){

        Jedis jedis = new Jedis("192.168.41.188", 6379);
        remove(jedis);
        jedis.select(0);

        HashMap<String, Double> map = new HashMap<>();//必须是<String,Double>
        map.put("ao",1.1);
        map.put("oc",1.3);
        map.put("pop",2.0);
        jedis.zadd("rank",1.0,"1.0");
        jedis.zadd("rank",1.1,"1.1");
        jedis.zadd("rank",1.2,"1.2");
        jedis.zadd("rank",map);

        jedis.expire("rank",100);
        System.out.println(jedis.zrange("rank", 0, -1));//返回所有成员
        //加分,分高者靠后
        jedis.zincrby("rank",0.1,"ao");
        System.out.println("0~3索引 "+jedis.zrange("rank", 0, 3));//返回0~3索引

        System.out.println("分数1.2~2.0 "+jedis.zrangeByScore("rank", 1.2, 2.0));


    }


    //删库跑路
    private static void remove(Jedis jedis) {
        jedis.flushDB();
    }

    //key自动生成策略
    public static String companyKeyFactory() {
        String keyCompany = "company_"+UUID.randomUUID().toString();
        return keyCompany;
    }

}
