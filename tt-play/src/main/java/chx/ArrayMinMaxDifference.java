package chx;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayMinMaxDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] split = scanner.nextLine().split(" ");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        System.out.println(findMinDifference(arr, n));
    }
    public static int findMinDifference(int[] arr, int k) {
        Arrays.sort(arr); // 先将数组排序
        int n = arr.length;
        int[][] dp = new int[n + 1][k + 1]; // 动态规划数组，dp[i][j] 表示前i个数中去掉j个数的最小差值
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = arr[i - 1] - arr[0];
                } else if (j >= i) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int l = 0; l < i; l++) {
                        dp[i][j] = Math.min(dp[i][j], dp[l][j - 1] + arr[i - 1] - arr[l]);
                    }
                }
            }
        }
        return dp[n][k];
    }
}
