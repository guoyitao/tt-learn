package digui;

/**
 * 8X8国际象棋上面，任意两个皇后都不能处于同一行，同一列，同一斜线，求有多少种摆法
 * description: 八皇后问题 使用递归回溯法 性能不行要运行1.6w多次
 * author: Guo Yitao
 * create: 2020-07-25 15:33
 **/
public class BaHuangHou {

	//皇后数量
	static int max = 8;

	static int count = 0;

	static int countJudge;

	//下标代表行，值代表列
	static int[] array = new int[max];

	private static void print() {
		count++;
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "  ");
		}
		System.out.println();
	}

	//当放置第n个的时候，检测前面已经摆放的是否与第n个是否冲突
	//	array[i] == array[n] 表示是否同一列
	//  Math.abs(n-i) == Math.abs(array[n-i]) 是否同一斜线
	private static boolean judge(int n) {
		countJudge++;
		for (int i = 0; i < n; i++) {
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n]-array[i])) {

				return false;
			}
		}
		return true;
	}

	//放置
	private static void check(int n) {
		if (max == n) {
			//已经到最好
			print();
			return;
		}
		//依次放入皇后，是否冲突
		for (int i = 0; i < max; i++) {
			array[n] = i;
			if (judge(n)) {
				check(n + 1);
			}
		}
	}

	public static void main(String[] args) {
		check(0);
		System.out.println("一共：" + count);
		System.out.println("判断冲突次数：" + countJudge);
	}
}
