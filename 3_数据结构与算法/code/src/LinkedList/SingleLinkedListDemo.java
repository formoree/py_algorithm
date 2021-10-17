package LinkedList;

/**
 * @Classname SingleLinkedList
 * @Description TODO
 * @Date 2021/10/5 13:14
 * @Created by HP
 */

import java.util.Scanner;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList L = new SingleLinkedList();
        L.addNode();
        L.addNode();
        L.addNode();
        System.out.println(L.deleteNode(2));
        L.showAll();
    }
}

class SingleLinkedList{
    private Node head = new Node(0);

    //1.增加新节点:顺序插入
    public void addNode(){
        Scanner scanner = new Scanner(System.in);
        int data = scanner.nextInt();
        Node temp = head;
        while(true){
            if(temp.next == null)
                break;
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    //2.将节点插入指定位置
    public void addByOrder(int site){
        Scanner scanner = new Scanner(System.in);
        int data = scanner.nextInt();
        Node temp = head;
        //找到需要插入的位置
        for(int i = 0; i < site - 1;i++){
            temp = temp.next;
        }
        Node temp0 = new Node(data);
        temp0.next = temp.next;
        temp.next = temp0;
    }

    //删除某位置节点
    public int deleteNode(int site){
        Node temp = head;
        for(int i = 0;i < site - 1;i++){
            temp = temp.next;
        }
        int res = temp.next.data;
        temp.next =temp.next.next;
        return res;
    }

    //展示所有元素
    public void showAll(){
        if(head.next == null)
            System.out.println("此链表为空");
        Node temp = head;
        while(true){
            if(temp.next != null){
                temp = temp.next;
                System.out.println(temp.data);
            }else break;
        }
    }

}
class Node{
    public int data;
    public Node next;
    public Node(int data){
        this.data = data;
    }
}