package $Expertise.DataStructures.implemented.Problems.SlidingWindow;


import java.util.LinkedHashSet;

public class DuplicatesInKwindowSize {
    /**
     * <h1>Find Duplicates in an array with difference of K window</h1>
     * <h3>Approach</h3>
     * <li>Maintain a LinkedHashSet that can contain values of the window</li>
     * <li>keep checking the window with end index element and keep removing the left part when window size grows by k</li>
     */
    public static void main(String[] args) {
        int[] nums = {1,0,1,1};
        int start = 0;
        int end = 0;
        int k=1;

        LinkedHashSet<Integer> windows= new LinkedHashSet<>();

        while (end<nums.length){
            if (end-start>k){
                windows.removeFirst();
                start++;
            }

            if (windows.contains(nums[end]))
            {
                System.out.println("true");
                return;
            }
            windows.addLast(nums[end]);
            end++;
        }
        System.out.println("false");
    }
}
