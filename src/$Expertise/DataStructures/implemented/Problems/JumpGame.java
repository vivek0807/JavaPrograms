package $Expertise.DataStructures.implemented.Problems;
// To know if the from starting array index element we can sum up and reach to the end or not
public class JumpGame {

    static boolean canReachEnd(int arr[]){
        int curgoal=arr.length-1;
        // Keep shifting the goal from right to left adn keep checking if the goal can be reached
        // from the previous index then allow
        for (int i = arr.length-1; i >-1 ; i--) {
            if (i+arr[curgoal]>=curgoal)
                curgoal=i;

        }

        if (curgoal<=0)
            return true;
        return false;
    }
    public static void main(String[] args) {
        System.out.println(canReachEnd(new int[]{0,1}));
    }
}
