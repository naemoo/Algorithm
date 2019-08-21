package Search;

class Solution1 {
	public static int gcd(int m,int n) {
		return m%n == 0?n:gcd(n,m%n);
	}
	public static int lcm(int m,int n) {
		return m%n ==0?m:m*n/gcd(m,n);
	}
	public static int[] Solution11(int n, int m) {
	    return new int[]{gcd(Math.max(m, n),Math.min(m, n)),lcm(Math.max(m, n),Math.min(m, n))};
	 }
}
public class Gcd {
    public static void main(String[] args) {
    	System.out.println(Solution1.gcd(5, 10));
    	System.out.println(Solution1.lcm(5, 10));
    }
}
