package search;

/**
 * description: 插值查找算法
 * author: Guo Yitao
 * create: 2020-07-28 20:13
 **/
public class InsertValueSearch {

	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		binarySearch(arr,0,arr.length-1,1);
//		insertValueSearch(arr,0,arr.length-1,1);
	}


	public static int binarySearch(int[] arr,int left,int right,int findVal){
		System.out.println("11111");
		int mid = (left + right) /2;
		int midVal = arr[mid];

		//递归结束
		if (left > right){
			return -1;
		}

		if (findVal > midVal){
			return binarySearch(arr,mid+1,right,findVal);
		}else if (findVal < midVal){
			return binarySearch(arr,left,mid-1,findVal);
		}else {
			return mid;
		}
	}

	//编写插值查找算法
	//说明：插值查找算法，也要求数组是有序的
	/**
	 *
	 * @param arr 数组
	 * @param left 左边索引
	 * @param right 右边索引
	 * @param findVal 查找值
	 * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
	 */
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

		System.out.println("插值查找次数~~");

		//注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
		//否则我们得到的 mid 可能越界
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		// 求出mid, 自适应  算出一个接近要找的数的index
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if (findVal > midVal) { // 说明应该向右边递归
			return insertValueSearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // 说明向左递归查找
			return insertValueSearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}

	}
}
