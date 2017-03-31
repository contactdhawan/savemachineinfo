package com.murdock.infotech.readipaddress;

import com.typesafe.config.Config;
import redis.clients.jedis.Jedis;


public class JedisClient {

    private static Config config = Configuration.getConfiguration();
    private static String password = config.getString("redis.password");
    private static String host = config.getString("redis.host");
    private static String port = config.getString("redis.port");
    private static String dbName = config.getString("redis.db");
    private static String url = "redis://:"+password+"@"+host+":"+port+"/"+dbName;
    //private static String url = "redis://"+host+":"+port+"/"+dbName;
   
    private static Jedis jedis  = new Jedis(url);
    public static Jedis getJedis(){
    	System.out.println(url);
        try{
            jedis.ping();
        }

        catch (Exception e){
            jedis = new Jedis(url);
        }
        return jedis;
    }

}

