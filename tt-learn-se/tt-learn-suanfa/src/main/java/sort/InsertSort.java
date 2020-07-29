package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description: 插入排序 O（n^2）
 * author: Guo Yitao
 * create: 2020-07-26 19:55
 **/
public class InsertSort {
	public static void main(String[] args) {
//		int[] arr = new int[]{101, 34, 119, 1,-1,89};

		int [] arr = new int[80000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 800000);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));

		insertSort(arr);

		System.out.println(simpleDateFormat.format(new Date()));
//		System.out.println(Arrays.toString(arr));
	}

	//插入排序 时间复杂度 O（n^2）
	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0;
		//使用for循环来把代码简化
		for (int i = 1; i < arr.length; i++) {
			//定义待插入的数
			insertVal = arr[i];
			insertIndex = i - 1; // 即arr[1]的前面这个数的下标

			// 给insertVal 找到插入的位置
			// 说明
			// 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
			// 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
			// 3. 就需要将 arr[insertIndex] 后移
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
				insertIndex--;
			}
			// 当退出while循环时，说明插入的位置找到, insertIndex + 1
			//这里我们判断是否需要赋值
			if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}

//			System.out.println("第"+i+"轮插入");
//			System.out.println(Arrays.toString(arr));
		}

	}
}
