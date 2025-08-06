import java.io.*;

class Node{
    int val;
    Node next;
    Node(int val, Node node){
        this.val=val;
        this.next= node;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Node head= new Node(1, new Node(2,new Node(3,new Node(4,new Node(5,null)))));

        Node fast=head;
        Node slow=head;

        while (fast.next!=null){
           // slow=slow.next;
            fast=fast.next;
        }

        System.out.println(fast.val);
        System.out.println(slow.val);
    }
}

