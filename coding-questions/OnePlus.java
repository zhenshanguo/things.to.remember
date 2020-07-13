/* this algorithm is not as good/smart as the 'PlusOne' because of the space used and the logic in the for loop is twisting */

import java.util.Arrays;
public class OnePlus {

	public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        int[] rt = new int[digits.length + 1];
        digits[digits.length - 1]++;
        for (int i = digits.length - 1; i >= 0; i--) {
            rt[i + 1] += digits[i];
            rt[i] += rt[i + 1] / 10;
            rt[i + 1] %= 10;
            System.out.println(String.format("i=%s: %s", i, Arrays.toString(rt)));
        }
        if (rt[0] == 0) {
            return Arrays.copyOfRange(rt, 1, rt.length);
        } else {
            return rt;
        }
    }
    public static void main(String[] args) {
    	OnePlus test = new OnePlus();
    	test.plusOne(new int[] {3, 2, 5, 6, 9});
    }
}