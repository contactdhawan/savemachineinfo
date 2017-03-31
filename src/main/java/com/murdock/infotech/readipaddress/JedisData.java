package com.murdock.infotech.readipaddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.google.gson.Gson;

public class JedisData {
	private static Gson gson = new Gson();
	public static <T> void set(String jsonFormatted, String keyName) throws Exception{
       // String jsonFormatted = gson.toJson(object, object.getClass());
        JedisClient.getJedis().set(keyName, jsonFormatted);
    }

    public static <T> T get(String keyName, Class clazz) throws Exception{
        String jsonFormatted = JedisClient.getJedis().get(keyName);
        T object = (T) gson.fromJson(jsonFormatted, clazz);
        return object;
    }
    public static <T> ArrayList<T> getEntityList(Class clazz){
        Set<String> set = JedisClient.getJedis().zrangeByScore(clazz.getSimpleName(), 0, -1);
        ArrayList<T> arrayList = new ArrayList<T>();
        for (String string:set){
            arrayList.add((T) gson.fromJson(string, clazz));
        }

        return arrayList;
    }

    public static <T> Long loadToJedis(List<T> list, String keyName) throws Exception{
        for (T object:list){
            String jsonFormatted = gson.toJson(object,object.getClass());
            JedisClient.getJedis().zadd(keyName, 0, jsonFormatted);
        }

        Long loadCount = JedisClient.getJedis().zcount(keyName,0d,-1);

        if (loadCount > list.size()){
            JedisClient.getJedis().zremrangeByScore(keyName, 0, -1);
            throw new Exception("Attempt to load "+list.size()+" elements to key "+keyName+" failed by creating duplicates, reverting to empty list instead");
        }

        return loadCount;

    }

    public static  ArrayList<String> getEntityList(String key){
    	System.out.println("Key "+key);
        Set<String> set = JedisClient.getJedis().zrange(key,0,-1);
               // .zrangeByScore(key, 0, -1);
        System.out.println(set.size());
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String value:set){
            arrayList.add(value);
        }

        return arrayList;
    }

    public static Long loadListToJedis(List<String> list, String keyName) throws Exception{
        for (String object:list) {
            System.out.println(keyName + " " + object);
            //String jsonFormatted = gson.toJson(machineInfo,machineInfo.getClass());
            JedisClient.getJedis().zadd(keyName.toString(), 0, object+"");
            //email id , zadd 
        }

        Long loadCount = JedisClient.getJedis().zcount(keyName,0d,-1);

       /* if (loadCount > list.size()){
            JedisClient.getJedis().zremrangeByScore(keyName, 0, -1);
            throw new Exception("Attempt to load "+list.size()+" elements to key "+keyName+" failed by creating duplicates, reverting to empty list instead");
        }
*/
        return loadCount;

    }


}
