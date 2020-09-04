package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * description: 归并排序
 * author: Guo Yitao
 * create: 2020-07-28 10:15
 **/
public class MergeSort {

	public static void main(String[] args) {
		int[] arr = {8,4,5,7,1,3,6,2};
//		int [] arr = new int[8000000];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = (int)(Math.random()* 800000);
//		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(new Date()));
		mergeSort(arr,0,arr.length -1,new int[arr.length]);
		System.out.println(simpleDateFormat.format(new Date()));

		System.out.println(Arrays.toString(arr));

	}

	//分+合方法
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right) / 2; //中间索引
			//向左递归进行分解
			mergeSort(arr, left, mid, temp);
			//向右递归进行分解
			mergeSort(arr, mid + 1, right, temp);
			//合并
			merge(arr, left, mid, right, temp);

		}
	}

	/**
	 *
	 * @param arr 排序的原始数组
	 * @param left 左边有序序列的初始索引
	 * @param mid 中间索引
	 * @param right 右边索引
	 * @param temp 做中转的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

		int i = left; // 初始化i, 左边有序序列的初始索引
		int j = mid + 1; //初始化j, 右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引

		//(一)
		//先把左右两边(有序)的数据按照规则填充到temp数组
		//直到左右两边的有序序列，有一边处理完毕为止
		while (i <= mid && j <= right) {//继续
			//如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
			//即将左边的当前元素，填充到 temp数组
			//然后 t++, i++
			if(arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else { //反之,将右边有序序列的当前元素，填充到temp数组
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}

		//(二)
		//把有剩余数据的一边的数据依次全部填充到temp
		while( i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}

		while( j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[j];
			t += 1;
			j += 1;
		}

		//(三)
		//将temp数组的元素拷贝到arr
		//注意，并不是每次都拷贝所有
		t = 0;
		int tempLeft = left; //
		//第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
		//最后一次 tempLeft = 0  right = 7
		while(tempLeft <= right) {
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}

	}
}
