package suanfa.kmp;

/**
 * description: 暴力匹配字符串
 * author: Guo Yitao
 * create: 2020-08-05 13:43
 **/
public class ViolenceMatch {
	public static void main(String[] args) {
		String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String str2 = "尚硅谷你尚硅你";

		int index = violenceMatch(str1, str2);
		System.out.println("index=" + index);
	}

	public static int violenceMatch(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();

		int i = 0;
		int j = 0;
		while (i < s1.length && j < s2.length) {
			if (s1[i] == s2[j]) {
				//success
				i++;
				j++;
			} else {
				i = i - (j - 1);
				j = 0;
			}
		}

		if (j == s2.length) {
			return i - j;
		} else {
			return -1;
		}
	}
}
