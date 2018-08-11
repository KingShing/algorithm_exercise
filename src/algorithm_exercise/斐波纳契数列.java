package algorithm_exercise;

public class 斐波纳契数列 {
	// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

	
	public static void main(String[] args) {
		int res1 = fibonacci(6);
		int res2 = fibonacci1(6);
		System.out.println(res1);
		System.out.println(res2);
	}
	
	/**
	 * 递归 (空间消耗巨大)
	 */
	
	public static  int fibonacci1(int n) {
	        if( n == 1)
	            return 0;
	        if( n == 2)
	            return 1;
	        else
	        	return fibonacci1(n-1)+fibonacci1(n-2);
		
	}
	
	/**
	 * 	动态规划 
	 * @param n
	 * @return
	 */
	public static  int fibonacci(int n) {
      // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
      int oneNumber = 0;
      int twoNumber = 1;
      int result = 0 ;
 
        if( n == 1)
            return oneNumber;
        if( n == 2)
            return twoNumber;
        //用循环递推,只保存fn前面两个变量,每一次循环都更新前面连个递推变量
	    for (int i = 3; i <= n; i++){
	        result = oneNumber + twoNumber;
	        oneNumber = twoNumber;
	        twoNumber = result;
	      }
	      return result;
    }  
}
