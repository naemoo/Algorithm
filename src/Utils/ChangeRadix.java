package Utils;

public class ChangeRadix {
    static int mapToRadix(int num, int radix) {
	return Integer.parseInt(Integer.toString(num, radix), radix);
    }
}
