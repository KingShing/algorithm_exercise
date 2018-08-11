package algorithm_exercise;

/**
 * 在一个夜黑风高的晚上，有n（n <= 50）个小朋友在桥的这边，现在他们需要过桥，但是由于桥很窄，每次只允许不大于两人通过，
 * 他们只有一个手电筒，所以每次过桥的两个人需要把手电筒带回来，i号小朋友过桥的时间为T[i]，两个人过桥的总时间为二者中时间长者。
 * 问所有小朋友过桥的总时间最短是多少
 * 
 * @author yejincheng
 *
 */
public class 小朋友过桥问题 {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 5, 10 };
		int totalTime = crossRiver(arr);
		System.out.println(totalTime); // 17
	}

	private static int crossRiver(int[] arr) {
		int n = arr.length;
		if (n <= 2)
			return arr[n - 1];
		if (n == 3)
			return arr[0] + arr[1] + arr[2];
		//else n > 3
			int fn_prev2 = arr[1]; // f(i-2)
			int fn_prev1 = arr[0] + arr[1] + arr[2];// f(i-1)
			int fn = 0;
			for (int i = 3; i < arr.length; i++) {
				
				//1)还剩一个人
				//让花费时间最少的人把电筒送过来，然后第i个人和另外一个人一起过河，
				//fn[i] = fn[i-1] + a[1] + a[i] (让花费时间最少的人把手电筒送过来，然后和第i个人一起过河)
				int fn1 = arr[0]+arr[i]+fn_prev1;
				
				//2)还剩二个人(事实上这个是最小时间)
				//fn[i] = fn[i-2] + a[1] + a[i] + 2*a[2]  
				//由于花费时间最少的人在这边，所以下一次送手电筒过来的一定是花费时间第二少的，送过来后花费最少的和花费第二少的一起过河
				int fn2 = arr[0] + 2 * arr[1] + arr[i] + fn_prev2;
				
				//取1)和2)中最短的
				fn= Math.min(fn1, fn2);
				fn_prev2 = fn_prev1;
				fn_prev1 = fn;
			}
			return fn;
	}

}
