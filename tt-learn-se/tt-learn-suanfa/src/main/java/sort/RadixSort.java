package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description: 基数排序（桶排序）                  不支持负数
 * author: Guo Yitao
 * create: 2020-07-28 15:34
 **/
public class RadixSort {

	public static void main(String[] args) {
//		int[] arr = {53, 3, 542, 748, 14, 214};

		int [] arr = new int[80000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()* 800000);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
		radixSort(arr);
		System.out.println(simpleDateFormat.format(new Date()));


//		System.out.println(Arrays.toString(arr));
	}

	/**
	 *
	 * @date 2020/7/28 15:36
	 * @author guoyitao
	 * @param arr 原数组
	 * @return void
	 */
	public static void radixSort(int[] arr){
		//每个1维数组就是一个桶，为了防止溺出，每个桶的大小为arr.length
		int[][] burket = new int[10][arr.length];

		//为了记录每个桶放了多少个数
		//burketElementCounts[0] = burket[0] 放入数据的个数
		int[] burketElementCounts = new int[10];

		int max = arr[0]; //假设第一个数是最大数
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max){
				max = arr[i];
			}
		}
		int maxLength = (max +"").length();
		//一共maxlength轮
		for (int i = 0, n =1; i < maxLength; i++,n*=10) {
			//正对每个元素对应的位排序，例如第一次是各位，第二次是十位，第三次是百位。。。。。
			for (int j = 0; j < arr.length; j++) {
				//取出每个元素对应位的值
				int digitOfElement = arr[j] /n % 10;
				//放到对应的桶中
				burket[digitOfElement][burketElementCounts[digitOfElement]] = arr[j];
				burketElementCounts[digitOfElement]++;
			}

			int index = 0;
			//遍历每一个桶
			for (int k = 0; k < burketElementCounts.length; k++) {
				//如果桶不空
				if (burketElementCounts[k] != 0){
					//循环第k个桶放入原数组
					for (int l = 0; l < burketElementCounts[k]; l++) {
						//放回
						arr[index++] = burket[k][l];
					}
					burketElementCounts[k] = 0;
				}
			}
//			System.out.println("第" +(i+1) + "轮 ："+ Arrays.toString(arr));
		}




	}
}
