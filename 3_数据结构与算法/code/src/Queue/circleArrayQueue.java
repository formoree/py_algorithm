package Queue;

/**
 * @Classname circleArrayQueue
 * @Description TODO
 * @Date 2021/10/5 10:21
 * @Created by HP
 */
import java.util.Scanner;

public class circleArrayQueue {
    public static void main(String[] args) {
        //进行环形数组测试 最大数是4但有效数组的最大数为3
        cArrayQueue Queue = new cArrayQueue(4);
        char key = ' ';//接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("s(show): 显示数据");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加一个数据");
            System.out.println("g(get)： 取数据");
            System.out.println("h(head)： 查看队列头");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    Queue.showAll();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    Queue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res = Queue.getQueue();
                        System.out.printf("取出的数据为%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = Queue.showHead();
                        System.out.printf("队列头数据为%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

class cArrayQueue{
    private int maxSize; //队列最大容量
    private int front; //队列头 指向处有元素
    private int rear; //队列尾 指向处没有元素
    private int[] arr; //存放数据，模拟队列

    //创建队列的构造器
    public cArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//指向队列头的前一个位置
        rear = 0;//指向队列尾，指向队列尾的数据（即是最后一个数据）
    }

    //判断队列满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据
    public void addQueue(int n){
        if(isFull()) System.out.println("队列已满 无法增加数据");
        else{
            arr[rear] = n;
            rear = (rear + 1) % maxSize;
        }
    }

    //出队列
    public int getQueue(){
        if(isEmpty())
            throw new RuntimeException("队列为空 不能取数据");
        //这里需要分析出front是指向队列的第一个元素
        //1. 先将front保存起来
        //2. 将front后移并考虑取模
        //3. 将保存的数据返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //求出当前队列的有效个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列所有数据
    public void showAll(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历
        for(int i = front;i < front + size();i++){
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //显示队列头部数据 不是取出数据
    public int showHead(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，不存在头部数据");
        }
        return arr[front];
    }
}