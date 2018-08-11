package algorithm_exercise;

public class 大数相乘 {

	public static void main(String[] args) {

		int[] num1 = new int[] { 9, 9, 9 ,3,3,3,1,2,4,5,2,2,2,3};
		int[] num2 = new int[] { 8, 8,2,2,2,2,2,2,2,3,3,2,1,2,4};
		for (int i : num1) {
			System.out.print(i);
		}
			System.out.print(" * ");
		for (int i : num2) {
			System.out.print(i);
		}
		System.out.print(" = ");
		
		
		
		int[] bigNumberMultiply2 = bigNumberMultiply2(num1, num2);
		for (int i : bigNumberMultiply2) {
			System.out.print(i);
		}
	}

	/**
	 * 求 1234567891011121314151617181920 * 2019181716151413121110987654321 的乘积结果
	 */

	/**
	 * 1)模拟小学乘法：最简单的乘法竖式手算的累加型； 2)分治乘法：最简单的是Karatsuba乘法，一般化以后有Toom-Cook乘法；
	 * 3)快速傅里叶变换FFT：（为了避免精度问题，可以改用快速数论变换FNTT），时间复杂度O(N lgN
	 * lglgN)。具体可参照Schönhage–Strassen algorithm；
	 * 4)中国剩余定理：把每个数分解到一些互素的模上，然后每个同余方程对应乘起来就行； 5)Furer’s
	 * algorithm：在渐进意义上FNTT还快的算法。不过好像不太实用，本文就不作介绍了。大家可以参考维基百科Fürer’s algorithm
	 */

	/**
	 * 小学乘法 (最后处理进位) 时间复杂度O(n^2)
	 * 
	 * 注意:
	 * 这里的进位有个大坑，因为result[]数组是从左到右记录相对位的和（还没有进位），而最后的进位是从右向左累加进位，这样的话，如果最高位，也就是最左侧那一位的累加结果需要进位的话，result[]数组就没有空间存放了
	 * 解决:
	 * 而正好result[]数组的最后一位空置，不可能被占用，我们就响应地把num1的第i位与num2的第j位相乘，结果应该存放在结果的第i+j位上的这个结果往后顺移一位（放到第i+j+1位），最后从右向左累加时就多了一个空间。
	 */

	public static int[] bigNumberMultiply2(int[] num1, int[] num2) {
		// 分配一个空间，用来存储运算的结果，num1长的数 * num2长的数，结果不会超过num1+num2长 
		int[] result = new int[num1.length + num2.length];

		// 先不考虑进位问题，根据竖式的乘法运算，num1的第i位与num2的第j位相乘，结果应该存放在结果的第i+j位上
		for (int i = num1.length - 1; i >= 0; i--) {
			for (int j = num2.length - 1; j >= 0; j--) { //倒序遍历,正序遍历都行,没有影响,因为存的时候根据i+j+1存
				result[i + j + 1] += num1[i] * num2[j]; // (应为进位问题,放置到第i+j+1位)
			}
		}
		// 单独处理进位
		for (int k = result.length - 1; k > 0; k--) {//倒序遍历,逐步进位
			if (result[k] > 10) {
				result[k - 1] += result[k] / 10;
				result[k] %= 10;
			}
		}
		return result;
	}

	/**
	 * Karatsuba 乘法 时间复杂度:O(nlog23)
	 * 
	 * 我们假设要相乘的两个数是x * y。我们可以把x，y写成：
	 * 
	 * x=a∗10n/2+b y=c∗10n/2+d
	 * 这里的n是数字的位数。如果是偶数，则a和b都是n/2位的。如果n是奇数，则你可以让a是n/2+1位，b是n/2位。（例如a = 12，b = 34；a =
	 * 123，b = 45），那么x*y就变成了：
	 * 
	 * x∗y=(a∗10n/2+b)∗(c∗10n/2+d) 进一步计算，
	 * 
	 * x∗y=a∗c∗10n+(a∗d+b∗c)∗10n/2+bd 对比之前的计算过程。结果已经呼之欲出了。这里唯一需要注意的两点就是： 1: (a * d +
	 * b * c)的计算为了防止两次乘法，应该使用之前的计算 2:
	 * 这些乘法在算法里应该是递归实现的，数字很大时，先拆分，然后拆分出来的数字还是很大的话，就继续拆分，直到a *
	 * b已经是一个非常简单的小问题为之。这也是分治的思想。
	 * 
	 */

	/**
	 * 我们举例来尝试一下这种算法，比如计算12345 * 6789，我们让a = 12，b = 345。同时c = 6，d = 789。也就是：
	 * 
	 * 12345=12·1000+3456789=6·1000+789 那么a*c，b*d的结果如下：
	 * 
	 * z2=a∗c=12×6=72 z0=b∗d=345×789=272205
	 * z1=(12+345)×(6+789)−z2−z0=283815−72−272205=11538 最终结果就是：
	 * 
	 * result=z2·102∗3+z1·103+z0 result=72·106+11538·103+272205=83810205.
	 */

	/**
	 * Karatsuba乘法
	 */
	public static long karatsuba(long num1, long num2) {
		// 递归终止条件
		if (num1 < 10 || num2 < 10)
			return num1 * num2;

		// 计算拆分长度
		int size1 = String.valueOf(num1).length();
		int size2 = String.valueOf(num2).length();
		int halfN = Math.max(size1, size2) / 2;

		/* 拆分为a, b, c, d */
		long a = Long.valueOf(String.valueOf(num1).substring(0, size1 - halfN));
		long b = Long.valueOf(String.valueOf(num1).substring(size1 - halfN));
		long c = Long.valueOf(String.valueOf(num2).substring(0, size2 - halfN));
		long d = Long.valueOf(String.valueOf(num2).substring(size2 - halfN));

		// 计算z2, z0, z1, 此处的乘法使用递归
		long z2 = karatsuba(a, c);
		long z0 = karatsuba(b, d);
		long z1 = karatsuba((a + b), (c + d)) - z0 - z2;

		return (long) (z2 * Math.pow(10, (2 * halfN)) + z1 * Math.pow(10, halfN) + z0);
	}
}
