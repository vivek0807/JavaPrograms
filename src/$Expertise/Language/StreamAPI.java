package $Expertise.Language;




import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;
/*
* COPYOnWriteArrayList is a threadSafe version of ArrayList that allows concurrent modification while Iterating.
* Any other way wil throw concurrent moDification error

* */
 class Student_Stream {

    /** The id. */
    private int id;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The age. */
    private int age;

    /** The gender. */
    private String gender;

    /** The departmant name. */
    private String departmantName;

    /** The joined year. */
    private int joinedYear;

    /** The city. */
    private String city;

    /** The rank. */
    private int rank;

    /**
     * Instantiates a new employee.
     *
     * @param id the id
     * @param firstName the first name
     * @param lastName the last name
     * @param age the age
     * @param gender the gender
     * @param departmantName the departmant name
     * @param joinedYear the joined year
     * @param city the city
     * @param rank the rank
     */
    public Student_Stream(int id, String firstName, String lastName, int age, String gender, String departmantName,
                   int joinedYear, String city, int rank) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmantName = departmantName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the departmant name.
     *
     * @return the departmant name
     */
    public String getDepartmantName() {
        return departmantName;
    }

    /**
     * Sets the departmant name.
     *
     * @param departmantName the new departmant name
     */
    public void setDepartmantName(String departmantName) {
        this.departmantName = departmantName;
    }

    /**
     * Gets the joined year.
     *
     * @return the joined year
     */
    public int getJoinedYear() {
        return joinedYear;
    }

    /**
     * Sets the joined year.
     *
     * @param joinedYear the new joined year
     */
    public void setJoinedYear(int joinedYear) {
        this.joinedYear = joinedYear;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the rank.
     *
     * @param rank the new rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", gender=" + gender + ", departmantName=" + departmantName + ", joinedYear=" + joinedYear + ", city="
                + city + ", rank=" + rank + "]";
    }

    static  List<Student_Stream> getObjects(){
        List<Student_Stream> list = Arrays.asList(
                new Student_Stream(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
                new Student_Stream(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
                new Student_Stream(3, "ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
                new Student_Stream(4, "satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
                new Student_Stream(5, "roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
                new Student_Stream(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
                new Student_Stream(7, "arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
                new Student_Stream(8, "nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
                new Student_Stream(9, "sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
                new Student_Stream(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

        return list;
    }


}

class StreamCodes{
    /**
     Intermediate Operations and Performance:*
     *
     <h1>Question: Explain the difference between intermediate and terminal operations in the Stream API. How do intermediate
     operations affect the performance of a stream pipeline?</h1>
     Answer: Intermediate operations are lazy and return a new stream, allowing for further operations to be chained.
     Terminal operations are eager and trigger the processing of the stream pipeline. Intermediate operations do not process elements until a terminal operation is invoked, which can lead to performance optimizations through short-circuiting and lazy evaluation.
     Parallel Streams:
     <li> liat view</li>
     Question: How do you convert a sequential stream to a parallel stream? What are the potential pitfalls of using parallel streams?
     Answer: You can convert a sequential stream to a parallel stream using the parallel() method. Potential pitfalls include thread-safety issues, increased overhead from context switching, and performance degradation if the tasks are not CPU-bound or if the data source is not efficiently splittable.
     Custom Collectors:

     Question: How do you create a custom collector in the Stream API? Provide an example.
     Answer: A custom collector can be created by implementing the Collector interface or using the Collector.of method. For example, a collector that concatenates strings with a delimiter:
     public static Collector<CharSequence, ?, String> joiningWithDelimiter(String delimiter) {
     return Collector.of(
     StringBuilder::new,
     (sb, s) -> sb.append(s).append(delimiter),
     (sb1, sb2) -> sb1.append(sb2),
     sb -> sb.length() > 0 ? sb.substring(0, sb.length() - delimiter.length()) : sb.toString()
     );
     }
     Stream Pipelines:

     Question: Given a list of employees, how would you use the Stream API to find the highest-paid employee in each department?
     Answer: You can use Collectors.groupingBy to group employees by department and Collectors.maxBy to find the highest-paid employee in each group:
     Map<String, Optional<Employee>> highestPaidByDept = employees.stream()
     .collect(Collectors.groupingBy(
     Employee::getDepartment,
     Collectors.maxBy(Comparator.comparing(Employee::getSalary))
     ));
     Handling Nulls:

     Question: How do you handle null values in a stream? Provide an example.
     Answer: You can filter out null values using the filter method:
     List<String> nonNullStrings = strings.stream()
     .filter(Objects::nonNull)
     .collect(Collectors.toList());

     */
   static void generateAvegarageFromArray(int arr[]){

       Arrays.stream(arr).average().ifPresent(System.out::println);
    }

    static void firstNameStartsWithA(List<Student_Stream> list){
       List<Student_Stream> li= list.stream().filter(e->e.getFirstName().startsWith("A")).collect(Collectors.toList());
        System.out.println(li);
    }

    static void groupByDepartMentNames(List<Student_Stream> list){
       list.stream().collect(Collectors.groupingBy(Student_Stream::getDepartmantName)).forEach((e, f)->System.out.println(e+" "+f));
    }

    static  void maxAgeOfStudentDetails(List<Student_Stream> list){
        System.out.println(list.stream().max(Comparator.comparing(Student_Stream::getAge)));
    }
    static  void findAllDistinctDepartments(List<Student_Stream> list){
        System.out.println(list.stream().map(Student_Stream::getDepartmantName).distinct().collect(Collectors.toList()));
    }

    static void StudentsInEachDepartMent(List<Student_Stream> list){
        System.out.println(list.stream().collect(Collectors.groupingBy(Student_Stream::getDepartmantName,Collectors.counting())));
    }

    static void findAverageRankInAllDepartMents(List<Student_Stream> list){
       Map<String,Double> maps= list.stream().collect(Collectors.groupingBy(Student_Stream::getDepartmantName,Collectors.averagingInt(Student_Stream::getRank)));
        System.out.println(maps.entrySet());

    }

    static void manual(List<Student_Stream> list){
        System.out.println(list.stream().filter(e->e.getFirstName().length()>=5).map(i->i.getFirstName().substring(0,1).toUpperCase()+i.getFirstName().substring(1)).collect(Collectors.toList()));
    }


}
public class StreamAPI {
    public static void main(String[] args) {
       // StreamCodes.generateAvegarageFromArray(new int[]{1,2,3,4,5,6,7,8,9});;
      // StreamCodes.get2ndLargestSalary();

        List<Student_Stream> list= Student_Stream.getObjects();
        StreamCodes.manual(list);

    }
}

