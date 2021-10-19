package Stack;

/**
 * @Classname StackDemo
 * @Description 初始栈
 * @Date 2021/10/17 22:36
 * @Created by HP
 */
public class StackDemo {
    public static void main(String[] args) {
        stack s = new stack(10);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.pop();
        s.list();
    }
}

class stack{
    private int MaxSize;
    private int []stack;
    private int top = -1;

    //构造器
    public stack(int MaxSize){
        this.MaxSize = MaxSize;
        stack = new int[this.MaxSize];
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断栈满
    public boolean isFull(){
        return top == MaxSize - 1;
    }

    //入栈
    public void push(int data){
        if(isFull()){
            System.out.println("栈满，无法再添加元素");
            return;
        }
        stack[++top] = data;
    }

    //出栈
    public void pop(){
        if(isEmpty()){
            System.out.println("栈空，无法再移除元素");
            return;
        }
        System.out.println("移除的元素为" + stack[top]);
        top--;
    }

    //显示 从头到尾显示
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for(int i = top;i > -1;i-- )
            System.out.println(stack[i]);
    }
}