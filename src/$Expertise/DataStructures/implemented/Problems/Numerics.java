package $Expertise.DataStructures.implemented.Problems;

//Bigger numbers can also be handled using the BigInteger class
class Problems{


    void palindrome(long n){                        //PALINDROME
        long num=n;
        long old=0;
        while (n>0){
            old=(old*10)+(n%10);
            n=n/10;
        }
        if(old==num)
            System.out.println("number is palindrome");
        else
            System.out.println("Number not a palindrome");
    }

    void factorialzeroes(long n){                  // Trailing zeroes in a factorial
        int zeroes=0;
        for (long i = 5; i <=n ; i*=5) {
            zeroes+=(n/i);
        }
        System.out.println("Trailing zeroes are -> "+zeroes);
    }

    int numberOfWays(int n,int m){         //FINDING WAYS TO TRAVERSE END CELL OF A N-X-M MATRIX
        if(n==1 || m==1)
            return 1;
        else
            return  numberOfWays(n-1,m)+numberOfWays(n,m-1);

    }

}

public class Numerics {
    public static void main(String[] args) {

        Problems problems= new Problems();

        System.out.println(problems.numberOfWays(3,3));
    }
}
