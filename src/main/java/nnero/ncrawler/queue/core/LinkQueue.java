package nnero.ncrawler.queue.core;

import nnero.ncrawler.util.NLog;

/**
 * **********************************************
 * <p/>
 * Author NNERO
 * <p/>
 * Time : 16/3/30 下午2:13
 * <p/>
 * Function: the url queue which store target urls.
 *           linked list implements.
 *           @since 0.1.0
 * <p/>
 * ************************************************
 */
public class LinkQueue<T> {

    class Node{
        Node pre;
        Node next;
        T data;

        Node(T data){
            this.data = data;
        }
    }

    private Node mHeadNode;
    private Node mTailNode;
    private long mSize; //avoid size is over max value of int.

    public LinkQueue(){
    }

    /**
     * push data in queue's head
     * @param data
     * @since 0.1.0
     */
    public void push(T data){
        if(mHeadNode == null){
            mHeadNode = new Node(data);
            mTailNode = mHeadNode;
        } else {
            Node node = mHeadNode;
            mHeadNode = new Node(data);
            mHeadNode.next = node;
            node.pre = mHeadNode;
        }
        mSize++;
    }

    /**
     * poll queue's tail
     * @since 0.1.0
     * @return
     */
    public T poll(){
        if(mTailNode == null){
            return null;
        }
        Node node = mTailNode;
        if(mSize > 1){
            node.pre.next = null;
            mTailNode = node.pre;
        } else {
            mTailNode = null;
            mHeadNode = null;
        }
        mSize--;
        return node.data;
    }

    /**
     * get queue size.
     * @since 0.1.0
     * @return
     */
    public long size(){
        return mSize;
    }
}
