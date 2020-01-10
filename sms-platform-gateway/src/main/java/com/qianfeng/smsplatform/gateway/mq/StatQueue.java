package com.qianfeng.smsplatform.gateway.mq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;


public class StatQueue {

  /*** The Log to which logging calls will be made. */
  private final static Logger log = LoggerFactory.getLogger(StatQueue.class);
  static private StatQueue _instance;
  private LinkedList list = new LinkedList();
  private long defaultTimeout = 10000;

  public StatQueue() {
  }

  static synchronized public StatQueue getInstance() {
    if (_instance == null) {
      _instance = new StatQueue();
    }
    return _instance;
  }

  /***
   * Returns the current number of object in the queue
   */
  public synchronized int size() {
    return list.size();
  }

  /***
   * adds a new object to the end of the queue.
   * At least one thread will be notified.
   */
  public synchronized void add(Object object) {
    list.add(object);
    notify();
  }

  /***
   * Removes the first object from the queue, blocking until one is available.
   * Note that this method will never return null and could block forever.
   */
  public synchronized Object remove() {
    while (true) {
      Object answer = removeNoWait();
      if (answer != null) {
	return answer;
      }
      try {
	wait();
      }
      catch (InterruptedException e) {
	log.error("Thread was interrupted: " + e, e);
      }
    }
  }

  /***
   * Removes the first object from the queue, blocking only up to the given
   * timeout time.
   */
  public synchronized Object remove(long timeout) {
    Object answer = removeNoWait();
    if (answer == null) {
      try {
	wait();
      }
      catch (InterruptedException e) {
	log.error("Thread was interrupted: " + e, e);
      }
      answer = removeNoWait();
    }
    return answer;
  }

  /***
   * Removes the first object from the queue without blocking.
   * This method will return immediately with an item from the queue or null.
   *
   * @return the first object removed from the queue or null if the
   * queue is empty
   */
  public synchronized Object removeNoWait() {
    if (!list.isEmpty()) {
      return list.removeFirst();
    }
    return null;
  }

}
