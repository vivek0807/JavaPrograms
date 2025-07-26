package $Expertise.DataStructures.implemented.Problems;


import java.util.Arrays;
import java.util.Comparator;

//Find Minimum number of arrows that will be required to burst a balloon that is overlaped
public class BallonBurst {
    static  int numberOfArrows(int[][] mainArr){
        if (mainArr.length==1)
            return 1;
        Arrays.sort(mainArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1]>o2[1])
                return 1;
                else if (o1[1]<o2[1])
                    return -1;
                else
                    return 0;
            }
        });
        int arrow=1;
        int overlap=mainArr[0][1];
        for (int i=1;i<mainArr.length;i++){
            if (mainArr[i][0]>overlap){
                ++arrow;
                overlap=mainArr[i][1];
            }
        }

        for (int[]  i :mainArr){
            System.out.println(Arrays.toString(i));
        }
        return arrow;

    }
    public static void main(String[] args) {

        System.out.println(numberOfArrows(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
    }
}
