package algorithm_exercise;

/**
  题目描述：
	有N堆石子排成一排，每堆石子有一定的数量。现要将N堆石子并成为一堆。合并的过程只能每次将相邻的两堆石子堆成一堆，每次合并花费的代价为这两堆石子的和，经过N-1次合并后成为一堆。求出总的代价最小值。 
	输入 
	有多组测试数据，输入到文件结束。 
	每组测试数据第一行有一个整数n，表示有n堆石子。 
	接下来的一行有n（0< n <200）个数，分别表示这n堆石子的数目，用空格隔开 
	输出 
	输出总代价的最小值，占单独的一行 
 * @author yejincheng
 *
 */

/**
 * input 3 1 2 3 7 i .... j 13 7 8 16 21 4 18
 * 
 * output 9 239
 *
 */
public class 石子归并 {
	private static final int MAXN = 210;
	private static final double INF = 1 / 0.0;
	private static int[] a = new int[MAXN];
	private static int[][] dp = new int[MAXN][MAXN]; // dp[i][j]表示从i取到j的最小值
	private static int[][] sum = new int[MAXN][MAXN]; // sum[i][j]表示i石子到j石子的值

	public static void solve() {
		// 模拟scan输入
		int n = 7;
		int[] arr = new int[] { 13, 7, 8, 16, 21, 4, 18, };
		//
		for (int i = 1; i <= n; i++) {
			a[i] = arr[i - 1];
		}

		for (int i = 1; i <= n; i++) {
			dp[i][i] = 0;
			sum[i][i] = a[i];
			for (int j = i + 1; j <= n; j++)
				sum[i][j] = sum[i][j - 1] + a[j];
		}

		//
		for (int len = 2; len <= n; len++)// ij之间的区间长度
		{
			for (int i = 1; i <= n - len + 1; i++)// 区间起点
			{
				int j = i + len - 1;// 区间终点
				dp[i][j] = (int) INF;
				for (int k = i; k < j; k++)// 寻找区间中间的点来更新dp值
				{
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[i][j]);
				}
			}
		}
		//

		System.out.println(dp[1][n]);

	}

	public static void main(String[] args) {
		solve();
	}

}
