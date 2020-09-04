package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description: 希尔排序(缩小增量排序) O(n^2)
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含
 * 的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 *
 * 性能比插入稳定，是插入的改进版
 * author: Guo Yitao
 * create: 2020-07-26 20:28
 **/
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = {8,9,1,7,2,3,5,4,6,0};

//		int [] arr = new int[80000];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = (int) (Math.random() * 800000);
//		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));

		shellSort2(arr);

		System.out.println(simpleDateFormat.format(new Date()));
	}

	//移位法
	public static void shellSort2(int[] arr){
		// 增量gap, 并逐步的缩小增量
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素，逐个对其所在的组进行直接插入排序
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						//移动
						arr[j] = arr[j-gap];
						j -= gap;
					}
					//当退出while后，就给temp找到插入的位置
					arr[j] = temp;
				}
			}
			System.out.println("==" + Arrays.toString(arr));
		}
	}



	//交换法
	public static void shellSort1(int[] arr){
		int temp = 0;

		//gap步长
		for (int gap = arr.length/2; gap >0 ; gap/=2) {

			for (int i = gap; i < arr.length; i++) {
				//每组2个
				for (int j = i-gap; j >=0; j-=gap) {
					//如果当前元素大于加上步长后的那个元素,则交换
					if (arr[j] > arr[j+gap]){
						temp = arr[j];
						arr[j] =arr[j+gap];
						arr[j+gap] = temp;
					}
				}
			}

//			System.out.println("==" + Arrays.toString(arr));
		}
	}

	public static void shellSort0(int[] arr){
		int temp = 0;
		//第一轮 把10个数分成5组
		for (int i = 5; i < arr.length; i++) {
			//每组2个
			for (int j = i-5; j >=0; j-=5) {
				//如果当前元素大于加上步长后的那个元素,则交换
				if (arr[j] > arr[j+5]){
					temp = arr[j];
					arr[j] =arr[j+5];
					arr[j+5] = temp;
				}
			}
		}
		System.out.println("第1轮=" + Arrays.toString(arr));

		//第1轮 把10个数分成5组
		//第2轮 把5组数分成2组
		for (int i = 2; i < arr.length; i++) {
			//每组2组
			for (int j = i-2; j >=0; j-=2) {
				//如果当前元素大于加上步长后的那个元素,则交换
				if (arr[j] > arr[j+2]){
					temp = arr[j];
					arr[j] =arr[j+2];
					arr[j+2] = temp;
				}
			}
		}
		System.out.println("第2轮=" + Arrays.toString(arr));

		//第1轮 把10个数分成5组
		//第2轮 5/2=2
		//第3轮 2/2=1
		for (int i = 1; i < arr.length; i++) {
			//每组2组
			for (int j = i-1; j >=0; j-=1) {
				//如果当前元素大于加上步长后的那个元素,则交换
				if (arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] =arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("第3轮=" + Arrays.toString(arr));
	}
}
