package $Expertise.DataStructures.implemented.Problems;

import java.util.Arrays;

/**
 * <h1>Gas Station</h1>
 * <h3>Return the index from where if started, deducting the cost of travel from the cost array you could run in a circular ring</h3>
 * <h3>Approach :-</h3>
 * <li>Check if total sum of gas is less than sum of cost, return -1</li>
 * <li>If any time gas[i]-cost[i]+total is less than 0 that means we gonna fail there so set total to 0 </li>
 * <li>set start to i+1</li>
 */
public class GasStation {
    public static void main(String[] args) {
        int[] gas={2,3,4};
        int[] cost={3,4,3};

        if (Arrays.stream(gas).sum()<Arrays.stream(cost).sum())
            System.out.println(-1);
        int total=0;
        int start=0;
        for (int i = 0; i < gas.length; i++) {
            total+=(gas[i]-cost[i]);
            if (total<0) {
                total = 0;
            start=i+1;
            }
        }
        System.out.println(start);
    }
}
