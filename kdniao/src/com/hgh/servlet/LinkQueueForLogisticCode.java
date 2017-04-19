package com.hgh.servlet;

import com.hgh.zmentity.RequestSystem;

public class LinkQueueForLogisticCode {
    // 链栈的节点
    public class Node<Data> {
    	Data e;
        Node<Data> next;
        public Node() {
        }

        public Node(Data e, Node next) {
            this.e = e;
            this.next = next;
        }

		public Data getE() {
			return e;
		}
    }
    
    private Node front;// 队列头，允许删除  
    private Node rear;// 队列尾，允许插入  
    private int size; //队列当前长度 
    
    private static LinkQueueForLogisticCode lq=new LinkQueueForLogisticCode();
    
    public static LinkQueueForLogisticCode getLinkQueue() {
    	return lq;
	}
    
    private LinkQueueForLogisticCode() {
        front = null;
        rear = null;
    }
    
    //判空
      public boolean empty(){
          return size==0;
      }
      
      //插入
      public synchronized boolean add(RequestSystem e){
          if(empty()){    //如果队列为空
              front = new Node(e,null);//只有一个节点，front、rear都指向该节点
              rear = front;
          }else{
              Node<RequestSystem> newNode = new Node<RequestSystem>(e, null);
              rear.next = newNode; //让尾节点的next指向新增的节点
              rear = newNode; //以新节点作为新的尾节点
          }
          size ++;
          return true;
      }
      
      //返回队首元素，但不删除
      public synchronized Node<RequestSystem> peek(){
          if(empty()){
              throw new RuntimeException("空队列异常！");
          }else{
              return front;
          }
      }
      
      //出队
      public synchronized Node<RequestSystem> poll(){
          if(empty()){
              throw new RuntimeException("空队列异常！");
          }else{
              Node<RequestSystem> value = front; //得到队列头元素
              front = front.next;//让front引用指向原队列头元素的下一个元素
              value.next = null; //释放原队列头元素的next引用
              size --;
              return value;
          }        
      }
      
      //队列长度
      public int length(){
          return size;
      }
}