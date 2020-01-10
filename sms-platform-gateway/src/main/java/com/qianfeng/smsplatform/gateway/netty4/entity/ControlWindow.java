package com.qianfeng.smsplatform.gateway.netty4.entity;
import java.util.HashMap;

public class ControlWindow {
  private static ControlWindow _instance;
  private HashMap hashMap = new HashMap();

  public static ControlWindow getInstance() {
    if (_instance == null) {
      _instance = new ControlWindow();
    }
    return _instance;
  }

  /***
   * Returns the current number of object in the queue
   */
  public synchronized int size() {
    return hashMap.size();
  }

  /***
   * adds HashMap.
   * At least one thread will be notified.
   */
  public synchronized void put(long key,Object Value) {
    hashMap.put(key,Value);
  }

  /***
   * Removes the first object from the HashTable, blocking until one is available.
   * Note that this method will never return null and could block forever.
   */
  public synchronized Object remove(long key) {
    return hashMap.remove(key);
  }

  public synchronized Object get(long key) {
    Object value = hashMap.get(key);
    return  value;
  }
}