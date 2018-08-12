package algorithm_exercise;

import java.util.Scanner;

/**
 * 一节课有n分钟，在每一分钟他状态是清醒的或者睡着的，某一分钟如果是清醒的就可以获得一个权值,睡着的,可以叫醒一次,然后保持清醒k分钟,求权值最大
 * @author yejincheng
 *
 */
public class 瞌睡问题 {
	int n, k;
	int a[] = new int[1000005];
	int b[] = new int[100005];
	int dp[] = new int[100005];
	int dp1[] = new int[100005];
	Scanner scanner;
	/**
	 * dp[i]  表示i分钟后可以获得的权值 
	 * dp1[i] 表示i分钟后清醒时获得的权值
	每次遇到睡觉时 ，就叫醒保存最大值（关键处理O(1) 得到这个值）
	 */
	public void solve() {
		scanner = new Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();//权值 4 5 2 3 1
		}
		for (int i = 0; i < n; i++) {
			b[i] = scanner.nextInt();//    1 1 0 1 0
		}
		for (int i = n - 1; i >= 0; i--) {
			dp[i] = a[i] + dp[i + 1];
			dp1[i] = dp1[i + 1];
			if (b[i] == 1) {
				dp1[i] += a[i];
			}
		}
		int sum = 0, ans = 0;
		for (int i = 0; i < n; i++) {
			if (b[i] == 1) {
				sum += a[i];
			} else {
				int j = Math.min(i + k - 1, n);
				ans = Math.max(ans, sum + dp[i] - dp[j + 1] + dp1[j + 1]);
			}
		}
		ans = Math.max(ans, sum);
		System.out.println(ans);
	}

	public static void main(String[] args) {
		new 瞌睡问题().solve();
	}
}