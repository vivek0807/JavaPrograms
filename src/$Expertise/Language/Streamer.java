package $Expertise.Language;
//STREAM IS AN INTERFACE
//INTERMEDIATE OPERATIONS -MAP-FILTER SORT
//TERMINAL OPERATIONS -COLLECT FOR-EACH REDUCE
//Stream can be created with collection.stream() , Stream.of() and Stream builder method
//EACH FUNCTION OF STREAM can take lambda expression as an input expression
//DATA manipulators -->.skip(). stream(). findFirst() .sorted(Obj1, Obj2) --> CUstom comaprator
//Optional is a class that is often used with Stream API that has multiple inbuilt methods to avoid null pointer Exceptions
//Optional.IfPresent
//Optional.ofNullable
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

///Stream to do Group By Distinct etc
class Functionalities{
    void putData(){
        List<Student> students= new ArrayList<>();
        students.add(new Student("Monu",25,1));
        students.add(new Student("Yatharth",25,5));
        students.add(new Student("Shukla",25,7));
        students.add(new Student("Vivek",26,0));

       // StreamSort(students);
        StreamGroupingBy(students);
    }

    static void StreamSort(List<Student> students){
        Stream studentStream =students.stream();

     List<Student> sortedStudent= (List<Student>) studentStream.sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getRank()>o2.getRank())
                    return -1;
                else return 1;
            }
        }).collect(Collectors.toList());


    }

    static void StreamGroupingBy(List<Student> students){

        Stream<Student> streamer= students.stream();
        Map<Integer,List<Student>> groupedStudents=streamer.collect(Collectors.groupingBy(Student::getAge));

        System.out.println(groupedStudents);
    }
}
class Student{
    String name;
    int age;
     int rank;

    public Student(String name, int age, int rank) {
        this.name = name;
        this.age = age;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}


class Technique1{
    void main(){

        List<Integer> list =List.of(1,2,3,4,5,6,7,8,9,0,10);

        Stream<Integer> stream =list.stream();

       List<Integer> filteredList= stream.filter(i->i%2==0).collect(Collectors.toList());

       list.stream().map(i->i*i).filter(i->i%2==0).forEach(i-> System.out.println(i));
    }

    void emptyStream() {

        Stream<Object> stream = Stream.empty();
    }

    void fromArray(){

        String arr[]={"Name","array","for","test"};
        Stream<String> stream=Stream.of(arr);



        stream.forEach(e->{
            System.out.println(e);
        });
    }


}



public class Streamer {
    public static void main(String[] args) {

       // Technique1 technique1= new Technique1();
       // technique1.main()

        Functionalities functionalities= new Functionalities();
        functionalities.putData();
    }
}
