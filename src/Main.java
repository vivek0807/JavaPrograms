import $Expertise.Language.Collection;
import $Expertise.Language.FlatMap;
import org.w3c.dom.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Main {
    public static void main(String[] args) {
       // Try Kadane's Algo
        int max=Integer.MIN_VALUE;
        int array[]={1,2,3,4,5,6,-1,-500,201,1};
        int sum=0;
        for (int i = 0; i < array.length ; i++) {
            sum+=array[i];
            if(sum<=-1)
                sum=0;
            max=Math.max(sum,max);

        }
        System.out.println(max);
    }

 }



