
/**
 * @Classname SparseArray
 * @Description TODO
 * @Date 2021/10/4 23:08
 * @Created by HP
 */
package SparseArray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始数组11 * 11
        //0：没有棋子 1：蓝色棋子 2：黑色棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组~~：");
        for(int[] row : chessArr1){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转化为稀疏数组
        //1.得到非零数组的个数
        int sum = 0;
        for(int i = 0 ;i < 11;i++){
            for(int j = 0; j< 11;j++){
                if(chessArr1[i][j] != 0) sum++;
            }
        }
        //2.创建稀疏数组
        int SparseArr[][] = new int[sum+1][3];
        SparseArr[0][0] = 11;
        SparseArr[0][1] = 11;
        SparseArr[0][2] = sum;
        int count = 1;
        for(int i = 0 ;i < 11;i++){
            for(int j = 0; j< 11;j++){
                if(chessArr1[i][j] != 0) {
                    SparseArr[count][0] = i;
                    SparseArr[count][1] = j;
                    SparseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }
        //3.输出稀疏数组
//        for(int[] row : SparseArr){
//            for(int data : row){
//                System.out.printf("%d\t",data);
//            }
//            System.out.println();
//        }
//
        //将稀疏数组转化为原数组
        int chessArr2[][] = new int[SparseArr[0][0]][SparseArr[0][1]];//此时创建完后 chessArr2全为零
        for(int i = 1;i <= sum;i++){
            chessArr2[SparseArr[i][0]][SparseArr[i][1]] = SparseArr[i][2];
        }
        //输出原数组
        System.out.println("这是原数组~~：");
        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
