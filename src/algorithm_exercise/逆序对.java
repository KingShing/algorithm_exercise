package algorithm_exercise;

public class 逆序对 {
	/**
	 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
		概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。
		样例
			序列 [2, 4, 1, 3, 5] 中，有 3 个逆序对 (2, 1), (4, 1), (4, 3)，则返回 3 。
			
	 */
	
	public static void main(String[] args) {

		int arr[] = {2, 4, 1, 3, 5};
		long res = reversePairs(arr);
		System.out.println(res);
	}


	
	
	 public  static long reversePairs(int[] arr) {
        int count = 0;
        
        for(int i = 0;i<arr.length-1;i++){
            
            for(int j = i+1;j<arr.length;j++){
            
                if(arr[i]>arr[j])
                    count++;
             }
        }
        
        return count;
    }

}
