package $Expertise.DataStructures.implemented.Problems;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//PUT A GIVEN ARRAY WITHIN AN ARRAY IN SUCH A WAY THAT IT IS INCLUDED AS A SUBSET OF THE RANGES GIVEN IN 1 ARRAY
//E.G -->intervals = [[1,3],[6,9]], newInterval = [2,5] --> OP- [[1,5],[6,9]]
//E.G -->intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] O.P- Output: [[1,2],[3,10],[12,16]]
public class InsertInterval {
    static int[][] returnNewSubsetSrray(int[][] intervalsArr,int[] newIntervalArr){
        List<List<Integer>> intervals=Arrays.stream(intervalsArr)
                .map(row -> Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        int n = intervals.size();

        List<Integer> newInterval=Arrays.stream(newIntervalArr)
                .boxed()
                .collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (newInterval.get(1) < intervals.get(i).get(0)) {
                // If the interval to be merged is less than the interval in the array
                result.add(newInterval);
                for (; i < n; i++) {
                    result.add(intervals.get(i));
                }
                int[][] finalArray = new int[result.size()][2];
                for (int k = 0; k <result.size() ; k++) {
                    finalArray[k][0]=result.get(k).get(0);
                    finalArray[k][1]=result.get(k).get(1);
                }
                return finalArray;
            } else if (intervals.get(i).get(1) < newInterval.get(0)) {
                // If the current interval is before the newInterval
                result.add(intervals.get(i));
            } else {
                // Merge overlapping intervals
                newInterval.set(0, Math.min(newInterval.get(0), intervals.get(i).get(0)));
                newInterval.set(1, Math.max(newInterval.get(1), intervals.get(i).get(1)));
            }
        }

        result.add(newInterval);
        int[][] finalArray = new int[result.size()][2];
        for (int i = 0; i <result.size() ; i++) {
            finalArray[i][0]=result.get(i).get(0);
            finalArray[i][1]=result.get(i).get(1);
        }
        return  finalArray;
    }

    public static void main(String[] args) {

        int[][] intervals= {{1,3},{6,9}};
        int[] newintervals={2,5};
        System.out.println(returnNewSubsetSrray(intervals,newintervals));
        ;
    }
}
