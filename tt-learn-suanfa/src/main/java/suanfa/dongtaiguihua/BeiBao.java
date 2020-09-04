package suanfa.dongtaiguihua;

/**
 * 背包问题：有一个背包，容量为4磅 ， 现有如下物品
 * 物品	重量	价格
 * 吉他(G)	1	1500
 * 音响(S)	4	3000
 * 电脑(L)	3	2000
 * 1、要求达到的目标为装入的背包的总价值最大，并且重量不超出
 * 2、要求装入的物品不能重复
 *
 * description: 动态规划   背包问题
 * author: Guo Yitao
 * create: 2020-08-04 18:06
 **/
public class BeiBao {

	public static void main(String[] args) {
		int[] w = {1,4,3}; //物品重量
		int[] val = {1500,3000,2000}; //物品的价值
		int m = 4; //背包容量
		int  n = val.length; //物品数量

		//表 v[i][j] 在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] v = new int[n+1][m+1];
		//为了记录放入商品的情况，我们定一个二维数组
		int[][] path = new int[n+1][m+1];

		//初始化表的第一行第一列，这里其实默认就是0
		initTable(v);

		for (int i = 1; i < v.length ; i++) { //不处理第一行
			for (int j = 1; j < v[0].length; j++) { //不处理第一列
				if (w[i-1] > j) { //当前新加入的容量大于当前背包的大小，就直接使用上一个单元格的装入策略
					v[i][j] = v[i-1][j];
				} else{
					//说明:
					//因为我们的i 从1开始的， 因此公式需要调整成
					//j-w[i-1]为剩余空间  val[i-1]当前商品的价值
					//v[i-1][j-w[i-1]]为装入i-1商品，到剩余空间j-w[i]的最大值
					//v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
					//为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
					if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
						//把当前的情况记录到path
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		//显示一下表
		print(v);

		System.out.println("----------------------path--------------------------");
		int i = path.length - 1; //行的最大下标
		int j = path[0].length - 1;  //列的最大下标  一共能装的重量
		while(i > 0 && j > 0 ) { //从path的最后开始找
			if(path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n", i);
				j -= w[i-1]; //w[i-1] 减去用掉的重量
			}
			i--; //装入的物品不能重复
		}
	}

	private static void initTable(int[][] v) {
		for (int i = 0; i < v.length; i++) {
			v[i][0] = 0;
		}
		for (int i = 0; i < v.length; i++) {
			v[0][i] = 0;
		}
	}

	private static void print(int[][] v) {
		for (int i = 0; i < v.length ; i++) {
			for (int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
	}
}
