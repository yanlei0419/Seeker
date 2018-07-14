package org.seeker.regis;
import redis.clients.jedis.Jedis;

import java.util.List;
public class RedisKeyJava {
   public static void main(String[] args) {
      //连接本地的 Redis 服务
      Jedis jedis = new Jedis("192.168.60.148");
//      Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server sucessfully");
      
     // 获取数据并输出
     List<String> list = (List<String>) jedis.keys("*");
     for(int i=0; i<list.size(); i++) {
       System.out.println("List of stored keys:: "+list.get(i));
     }
   }
}