package suanfa.kmp;

import java.util.Arrays;

/**
 * description: KMP字符串匹配   https://www.cnblogs.com/zzuuoo666/p/9028287.html
 * author: Guo Yitao
 * create: 2020-08-05 13:59
 **/
public class KMP {

	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		int[] kmpNext = kmpNext(str2);
		System.out.println("部分匹配表: " + Arrays.toString(kmpNext));

		System.out.println(kmpSearch(str1, str2, kmpNext));
	}


	/**
	 *
	 * @param str1 源字符串
	 * @param str2 子串
	 * @param next 部分匹配表, 是子串对应的部分匹配表
	 * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
	 */
	public static int kmpSearch(String str1, String str2, int[] next) {

		//遍历
		for(int i = 0, j = 0; i < str1.length(); i++) {

			//需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
			//KMP算法核心点, 可以验证...
			while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j-1];
			}

			if(str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if(j == str2.length()) {//找到了 // j = 3 i
				return i - j + 1;
			}
		}
		return  -1;
	}

	//部分匹配表
	public static int[] kmpNext(String dest) {
		int[] next = new int[dest.length()];
		next[0] = 0; //如果长度==1，部分匹配值为0
		for (int i = 1, j = 0; i < dest.length(); i++) {
			//当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
			//直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
			//这时kmp算法的核心点
			while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
				j = next[j-1];
			}
			//当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
			if(dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}

}
