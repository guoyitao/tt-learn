package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 原始的数组 ： 101, 34, 119, 1
 * 第一轮排序 :   1, 34, 119, 101
 * 第二轮排序 :   1, 34, 119, 101
 * 第三轮排序 :   1, 34, 101, 119
 * <p>
 * 说明：
 * 1. 选择排序一共有 数组大小 - 1 轮排序
 * 2. 每1轮排序，又是一个循环, 循环的规则(代码)
 * 2.1先假定当前这个数是最小数
 * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
 * 2.3 当遍历到数组的最后时，就得到本轮最小数和下标
 * 2.4 交换 [代码中再继续说 ]
 * <p>
 * description: 选择排序 O（n^2）
 * author: Guo Yitao
 * create: 2020-07-26 17:12
 **/
public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = new int[]{101, 34, 119, 1};

//		int [] arr = new int[80000];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = (int)(Math.random()* 800000);
//		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));

		selectSort(arr);

		System.out.println(simpleDateFormat.format(new Date()));
		System.out.println(Arrays.toString(arr));
	}

	private static void selectSort(int[] arr) {
		//排序时间复杂度 O（n^2）
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { //判断假定的最小值是不是最小
					min = arr[j];
					minIndex = j;
				}
			}
			//假定的最小不是最小的时候就交换
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}
}
