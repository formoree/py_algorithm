package Stack;

/**
 * @Classname CalculateDemo
 * @Description 实现计算器(中缀表达式)
 * @Date 2021/10/17 22:58
 * @Created by HP
 * @conclusion
 *      1. 空字符的正确声明方法。char ch = '\0';不能直接不赋值，不然会报错。
 *      2. Interger.parseInt返回整数值(字符串必须是数字或者带'-'、'+')
 *      3. 遍历字符串的方法。substring.
 *      4. 数字的累加方式,多位数的扫描。
 *      5. 空字符串可以直接用 String s = "";
 */

import java.util.Scanner;

public class CalculateDemo {
    public static void main(String[] args) {
        //输入一段字符串并进行扫描
        String expression = "70+7*5-50+50"; //100

        stack2 numStack = new stack2(10);
        stack2 operStack = new stack2(10);
        //定义规定变量
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char s = '\0';
        int index = 0;
        String MulNum = "";
        //逐个扫描
        while(true){
            s = expression.substring(index,index+1).charAt(0);
            //如果s是数字 直接入栈
            if(!operStack.isOper(s)){
                //numStack.push(s-48);
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression 的表达式的index 后再看一位,如果是数就进行扫描，如果是符才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                MulNum += s;
                //如果数字为最后一位 直接如数字栈
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(MulNum));//Interger.parseInt返回整数值
                }else{
                    //扫描后一位，如果是字符，便将当前MulNum入栈；否则继续循环。
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(MulNum));
                        MulNum = "";
                    }
                }
            }else{
                //如果s是运算符
                //并且运算符栈为空，直接加入到字符栈中
                if(operStack.isEmpty()){
                    operStack.push(s);
                }else{
                    //如果字符栈不为空，比较栈顶字符与当前字符优先级
                    if(operStack.priority(s) <= operStack.priority(operStack.showTop())){
                        //如果当前字符串优先级较小 数字栈pop两个 字符栈pop一个进行运算
                        //运算结束后，将运算结果放入数字栈，将当前字符放入字符栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.calculate(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(s);
                    }else{
                        //当前运算符优先级大，直接放入栈中。
                        operStack.push(s);
                    }
                }
            }
            index++;
            //数组的遍历方式 记得加上=
            if(index >= expression.length())
                break;
        }

        //进行最后的扫描
        while(true){
            if(operStack.isEmpty())
                break;

            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = operStack.calculate(num1,num2,oper);
            numStack.push(res);
        }

        int res2 = numStack.pop();
        System.out.println("最后结果为"+res2);

    }
}

class stack2{
    private int MaxSize;
    private int []stack;
    private int top = -1;

    //构造器
    public stack2(int MaxSize){
        this.MaxSize = MaxSize;
        stack = new int[this.MaxSize];
    }

    //显示栈顶元素
    public int showTop(){
        return stack[top];
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
    public int pop(){
        if(isEmpty()){
            System.out.println("栈空，无法再移除元素");
            return 0;
        }
        return stack[top--];
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

    //判断是否是操作符
    public boolean isOper(int a){
        return a == '+'||a == '-'||a == '*'||a == '/';
    }

    //返回运算符优先级
    public int priority(int val){
        if(val == '*'||val == '/' )
            return 1;
        else if(val == '+'||val == '-')
            return 0;
        else
            return -1;
    }

    //进行计算
    public int calculate(int num1,int num2,int oper){
        int res = 0;
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}