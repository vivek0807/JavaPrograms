package ds.recurssion;

public class recSimple {
    int javelin(int n){

        if(n==1 || n==0)
            return 1;
        else
            return n*javelin(n-1);

    }
    public static void main(String[] args) {

        recSimple rc=new recSimple();

        System.out.println(rc.javelin(5));
    }
}
