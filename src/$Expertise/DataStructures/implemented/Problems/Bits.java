package $Expertise.DataStructures.implemented.Problems;
//bit Manipulation
//>> divide thenumber by 2
//<<multiply the number by 2

class PowersetString{

    void powerset(String s){
        int len=s.length();

        for (int i=0;i<(1<<len);i++){
            String substr="";
            for (int j = 0; j <len ; j++) {
                if((i & (1<<j))!=0)
                    substr=substr+s.charAt(j);
            }
            System.out.println(substr);
        }
    }

    void test(){
        System.out.println(3&(1<<4));
    }
}
public class Bits {
    public static void main(String[] args) {
      PowersetString powersetString = new PowersetString();
      powersetString.test();
    }
}
