import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main
{
    public List<String> summaryRanges(int[] nums) {
        List<String> rt = new ArrayList<String>();
        if (nums == null || nums.length == 0) {
            return rt;
        }
        for (int i = 0; i < nums.length; i++) {
            int st = nums[i];
            int ed = st;
            while (i + 1 < nums.length && nums[i + 1] - ed == 1) {
                i++;
                ed++;
            }
            if (ed == st) {
                rt.add(st + "");
            } else {
                rt.add(st + "->" + ed);
            }
        }
        System.out.println(rt.toString());
        return rt;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.summaryRanges(new int[]{1,2,3,3,4,5,7,8,9});
	}
	
}
