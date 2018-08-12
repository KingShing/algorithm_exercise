package algorithm_exercise;
/**
 * 有个字典，字典内每个单词都只包含 n个'a' 和 m个'z' 并且所有单词按字典序排列
		查找第k个单词 不存在输出-1
 * @author yejincheng
 *
 */

/**
	
	思路：   令dp[i][j] 表示 i个z和j个a 的方案数 所以dp[i][j] = dp[i-1][j]+dp[i][j-1]
	我们考虑第i个字符，如果当前选了a那么后面剩余的a和z的个数是a,b 那么 如果k<=dp[b][a] 那么就能选a （前提还有a）
	否则选z  那么k-=dp[b][a] 因为要减去a的方案数
 * @author yejincheng
 *
 */
public class 字典序 {

}
