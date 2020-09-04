package suanfa.fenzhi;

/**
 * description: 汉诺塔 分治算法
 * author: Guo Yitao
 * create: 2020-08-03 17:38
 **/
public class Hanoitower {

	public static void main(String[] args) {
		hanoiTower(3, 'A', 'B', 'C');
	}

	public static void hanoiTower(int num, char a, char b, char c) {
		if (num ==1) {
			System.out.println("第1个盘从" + a + "->" + c);
		}else {
			//把上面所有盘从A-》B
			hanoiTower(num -1,a,c,b);
			System.out.println("第"+ num +"个盘从  " + a + "->" + c);
			//B->C
			hanoiTower(num - 1, b, a, c);
		}
	}
}
