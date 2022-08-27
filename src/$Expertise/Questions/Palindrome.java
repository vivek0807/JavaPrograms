package $Expertise.Questions;
class checkPalin{

    boolean check(int x){
        int n=0;
int store=x;
        while (x>0){

            n=n*10+(x%10);
            x/=10;
        }
        if(store==n)
        return true;
        else
            return false;
    }

}
public class Palindrome {
    public static void main(String[] args) {
        checkPalin checkPalin=new checkPalin();
        System.out.println(checkPalin.check(121));
    }
}
