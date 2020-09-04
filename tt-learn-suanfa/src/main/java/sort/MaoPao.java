package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description: 冒泡排序法 O（n^2）
 * <p>
 * 原始数组：3, 9, -1, 10, 20
 * <p>
 * 第一趟排序
 * (1)  3, 9, -1, 10, 20   // 如果相邻的元素逆序就交换
 * (2)  3, -1, 9, 10, 20
 * (3)  3, -1, 9, 10, 20
 * (4)  3, -1, 9, 10, 20
 * <p>
 * 第二趟排序
 * (1) -1, 3, 9, 10, 20 //交换
 * (2) -1, 3, 9, 10, 20
 * (3) -1, 3, 9, 10, 20 //已经排好了
 * <p>
 * 第三趟排序
 * (1) -1, 3, 9, 10, 20
 * (2) -1, 3, 9, 10, 20
 * <p>
 * 第四趟排序
 * (1) -1, 3, 9, 10, 20
 * <p>
 * <p>
 * 小结冒泡排序规则
 * (1) 一共进行 数组的大小-1 次 大的循环
 * (2)每一趟排序的次数在逐渐的减少
 * (3) 如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序。这个就是优化
 * <p>
 * author: Guo Yitao
 * create: 2020-07-26 16:15
 **/
public class MaoPao {
	public static void main(String[] args) {
//		int[] arr = new int[]{3, 9, -1, 10, 20};
//		int[] arr = new int[]{3,9,-1,10,-2};

		int [] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
		sort(arr);
		System.out.println(simpleDateFormat.format(new Date()));

	}
	//冒泡排序时间复杂度 O（n^2）
	private static void sort(int[] arr) {
		int temp = 0;
		boolean flag = false; //表示是否进行过交换
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

//			System.out.println("第" + (i + 1) + "次");
//			System.out.println(Arrays.toString(arr));

			//一趟中，一次交换都没有发生，已经排好了
			if (!flag){
				break;
			}else {
				flag = false;
			}
		}
	}
}
