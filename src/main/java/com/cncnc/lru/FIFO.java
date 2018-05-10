package com.cncnc.lru;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FIFO<K, V> {

    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;


    LinkedHashMap<K, V> map;

    public FIFO(int cacheSize){
        this.MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;


        /**
         * 第三个参数设置为true，代表linkedlist按访问顺序排序，可作为LRU缓存
         * 第三个参数设置为false，代表按插入顺序排序，可作为FIFO缓存
         */
        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, false){
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
        FIFO<Integer, Integer> fifo = new FIFO<>(5);
        fifo.put(1, 1);
        fifo.put(2, 2);
        fifo.put(3, 3);
        System.out.println(fifo);

        System.out.println(fifo.get(1));

        System.out.println(fifo);

        fifo.put(4, 4);
        fifo.put(5, 5);
        fifo.put(6, 6);
        System.out.println(fifo);
    }
}
