package LinkedList;

/**
 * @Classname CircleLinkedList
 * @Description TODO
 * @Date 2021/10/17 21:29
 * @Created by HP
 */

import java.util.Scanner;

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleLinkedList L = new CircleLinkedList();
        L.addCNode(5);//尾插法添加元素
        L.showAll();
        System.out.println("现在开始进行出圈实验");
        L.getOut(5,1,2);
    }
}

//构造循环链表
class CircleLinkedList{
    CNode first = new CNode(-1);//

    //增加链表节点，传参决定添加几个节点
    public void addCNode(int count){
        //校验count的值
        if(count <= 0){
            System.out.println("输入值有误，请重新输入");
            return;
        }

        //添加一个辅助指针，指向节点
        CNode temp = first;
        while(count > 0){
            //输入值
            Scanner input = new Scanner(System.in);
            int data = input.nextInt();
            if(temp.next == null){
                temp.data = data;
                temp.next = temp;
            }else{
                //创建一个新节点 尾插法
                CNode newNode = new CNode(data);
                newNode.next = temp.next;
                temp.next = newNode;
                temp = temp.next;
            }
            count--;
        }
    }

    //输出所有节点
    public void showAll(){
        CNode helper = first;
        //空链表直接输出
        if(helper.next == null){
            System.out.println("空链表");
            return;
        }

        //正常遍历
        while(helper.next != first){
            System.out.println(helper.data);
            helper = helper.next;
        }
        System.out.println(helper.data);
    }

    //解决出圈问题 count代表圈中人数；k代表从第几个人开始数；m代表数的次数；
    public void getOut(int count,int k,int m){
        //helpre的位置应该在尾端
        CNode helper = first;
        while(helper.next != first){
            helper = helper.next;
        }

        //确定开始报数的位置
        for(int i = k;i > 1;i--){
            helper = helper.next;
            first = first.next;
        }

        //开始进行报数
        while(count > 0){
            for(int i = m;i > 1;i--){
                helper = helper.next;
                first = first.next;
            }
            System.out.println(first.data);
            //这里并不是删除first指针，而是是变化first指向的位置。
            first = first.next;
            helper.next = first;
            count--;
        }
    }
}


//构造循环链表节点
class CNode{
    public int data;
    public CNode next;
    public CNode(int data){
        this.data = data;
        this.next = next;
    }

}