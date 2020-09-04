package search;

/**
 * description: 搜索  线性查找逐一搜索
 * author: Guo Yitao
 * create: 2020-07-28 17:41
 **/
public class SeqSearch {

	public static void main(String[] args) {
		int arr[] = {1,9,11,-1,34,89};

		System.out.println(seqSearch(arr, 11));
	}

	public static int seqSearch(int[] arr,int value){
		// 线性查找是逐一比对，发现有相同值，就返回下标
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
