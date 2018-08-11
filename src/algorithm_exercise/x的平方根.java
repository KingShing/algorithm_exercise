package algorithm_exercise;

public class x的平方根 {
	/**
	 * 实现 double sqrt(int x) 函数，计算并返回 x 的平方根。 挑战 O(log(x))
	 */
	public static void main(String[] args) {
		double res = sqrt(123);
		System.out.println(res);// 11.090536506409418
	}

	// 牛顿迭代法求平方根 
	public static double sqrt(int x) {
		if (x < 0)
			return 0;
		double err = 1e-15;
		double t = x;
		while (Math.abs(t - x / t) > err * t)
			t = (x / t + t) / 2.0;
		return t;
	}

	// 2)同一 原理参考:https://blog.csdn.net/w20810/article/details/49030961
	/**
	 * 笔记:假如求a的平方根 假设根就是x,   只要满足x*x  -a < 精度  ;就可以了  ,设一个函数 f = x^2 -a (a是常数) , 即f<精度,就可以了, 
	 * 先 假设根x就在a处, 显然f不会小于精度,然后就求f在x=a处的切线,与x轴的交点m1,把这个交点m1作为新的x,
	 * 再带入判断f是不是小于精度,不小于,再求x=m1处的切线与x轴的焦点m2,
	 * 由f图像的性质,形状,来看,m1,m2,m3….越来越接近fn=0的根
	 * 
	 */
	public double sqr(double x) {
		double k = x;
		while (k * k - x > 1e-15)
			k = 0.5 * (k + x / k); 
		return k;

	}
	
	
	/**
	 * 二分法
	 */
		
	
	
}
