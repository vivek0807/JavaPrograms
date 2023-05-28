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
        List<Character> list= new ArrayList<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');

        Map<Character,Long> hashMap=list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
    }

 }



