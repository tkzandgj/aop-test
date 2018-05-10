package com.cncnc.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tukangzheng
 * 最近最久未使用的缓存算法
 *
 * 1、固定缓存大小，需要给缓存分配一个固定的大小
 * 2、每次读取缓存都会改变缓存的使用时间，将缓存的存在时间重新刷新
 * 3、需要在缓存满了之后，将最近最久未使用的缓存删除，在添加最新的缓存
 * https://www.cnblogs.com/liuyang0/p/6664586.html
 */
public class LRU<K, V> {

    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    LinkedHashMap<K, V> map;

    public LRU(int cacheSize){
        this.MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;


        /**
         * 传入的第三个参数accessOrder为true的时候，就按照访问顺序对LinkedHashMap排序，
         * 为false的时候就按照插入顺序进行排序，默认是为false的
         */
        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, true){

            /**
             * 当长度大于最大容量的时候，将最近最久未使用的缓存移除
             * @param eldest
             * @return
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }


    public synchronized void put(K key, V value){
        map.put(key, value);
    }


    public synchronized V get(K key){
        return map.get(key);
    }


    public synchronized void remove(K key){
        map.remove(key);
    }


    public synchronized Set<Map.Entry<K, V>> getAll(){
        return map.entrySet();
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()){
            stringBuilder.append(String.format("%s : %s", entry.getKey(), entry.getValue()));
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(5);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);

        System.out.println(lru);
        System.out.println(lru.get(1));
        System.out.println(lru);

        lru.put(4, 4);
        lru.put(5, 5);
        lru.put(6, 6);
        System.out.println(lru);
    }

}
