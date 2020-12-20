

/**
 * user: guoyitao
 * date: 2020/11/19 19:07
 * version:1.0
 */
public class CHX {
    public static void main(String[] args) {
//        test1();
//        test2();
    }
    public static void test1(){
        int i;
        int[] f=new int[20];//数组定义和初始化
        f[0]=1;             //赋初值
        f[1]=2;
        for(i= 2;i< f.length      ;i++) {
            f[i] = f[i-1] + f[i-2]; //找规律：当前值等于前2项之和
        }


        for(i=0;i<f.length;i++)  //循环输出所有数组元素
        {
            System.out.print(f[i]+" ");
        }
    }

    public static void test2(){
        char[][] ch=new char[10][10];
        for(int i=0;i<ch.length;i++) {
            for(int j=0;j<ch[0].length;j++) {
                /*
                * j==0 || j==ch[0].length -1 || i==0 || i==ch.length-1  || i==j || i+j ==9
                * j==0                 第一列的时候
                * j==ch[0].length -1  最后一列的时候
                * i==0                第0行
                * i==ch.length-1      最后一行
                * i==j                左上角到右下角    找规律发现需要输入*的点为  [0,0],[1,1],[2,2]...
                * i+j ==9             右上角到左下角    找规律                   [1,8],[2,7],[3,6]...   以此类推
                *
                * 可以删除几个条件看看打印出来什么东西
                * */
                if(j==0 || j==ch[0].length -1|| i==0 || i==ch.length-1 || i==j || i+j ==9)//填写输出*号的条件
                {
                    ch[i][j]='*';
                } else {
                    ch[i][j]=' ';
                }
            }
        }
//        输出二维数组
        for(int i=0;i<ch.length;i++){  //遍历行

            for(int j=0;j<ch[0].length;j++) //遍历列
            {
                System.out.print(ch[i][j]+" ");
            }
            System.out.println();
        }
    }
}
