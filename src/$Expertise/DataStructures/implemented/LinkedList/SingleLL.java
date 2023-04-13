package $Expertise.DataStructures.implemented.LinkedList;

import java.util.HashSet;

class Node{
    int data;
    Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
class LinkedList {
    int length;
    Node head;
    Node tail;
    void addAtLast(int data){
        if(head==null){
            Node newnode= new Node(data,null);
            this.head=newnode;
            this.tail=newnode;
        }
        else{
           Node newnode=new Node(data,null);
           this.tail.next=newnode;
           this.tail=newnode;


        }
    }

    void addAtFirst(int data){

        if(head==null) {
            {
                Node newnode = new Node(data, null);
                head = newnode;
                tail = head;
            }
        }
            Node newnode= new Node(data,null);
            newnode.next=head;
            head=newnode;


    }

    void deletenode(int data){
        if(head.data==data && head.next==null)
            head=head.next;
        if(head!=null) {
            Node current=head.next;
            Node backward=head;
            while (current!=null){
                if(current.data==data){
                    backward.next=current.next;
                    current=current.next;
                }
                else {
                    current = current.next;
                    backward = backward.next;
                }

            }
        }

    }

    boolean findLoop(){
        HashSet hashSet =new HashSet();
        Node pointer=head;
        while (pointer.next!=null){
            if(hashSet.contains(pointer))
                return true;
            else
            {
                hashSet.add(pointer);
            }
            pointer=pointer.next;
        }
        return false;
    }

    void fast_slow(){

        Node fast=head;

        Node slow=head;

        while (fast.next!=null&&slow!=null&&fast!=null){
            System.out.println("Fast data->"+fast.data);
            System.out.println("Slow data->"+slow.data);

            fast=fast.next.next;
            slow=slow.next;
        }
    }

    void remove_duplicates(){
        System.out.println("Removing duplicates");
        Node pointer=head;

        while (pointer.next!=null){
            if(pointer.data==pointer.next.data){
                Node connector=pointer.next;
                while (connector.data==pointer.data && connector.next!=null)
                {
                    connector=connector.next;
                }
               pointer.next=connector;
            }
            pointer=pointer.next;
        }
    }

    void nthLastNode(int n){
        Node refer=head;
        Node main=head;
        while (n-->=0 && refer!=null){
            refer=refer.next;
        }
        while (refer!=null){
            main=main.next;
            refer=refer.next;
        }
        System.out.println("Nth node from the end is"+main.data);
    }
    // ref pointer till n then both pointer till null

    void reverselist(){
        if(head.next==null){
            printlist();
        }
        else {
            Node prev=null;     //KEY_POINT change the middle node pinter to backward
            Node current=head;
            while(current!=null){
                Node temp=current.next;
                current.next=prev;
                prev=current;
                current=temp;
            }
            head=prev;

        }
    }

    void printlist(){
        System.out.println("List printer");
        Node pointer=head;
        while (pointer!=null)
        {  System.out.println(pointer.data);
            pointer=pointer.next;}
    }




}


public class SingleLL {
    public static void main(String[] args) {
        System.out.println("Linked list program begin");
        LinkedList linkedList= new LinkedList();
        linkedList.head=null;
        System.out.println("List Initialised");

        linkedList.addAtFirst(5);
        linkedList.addAtFirst(4);
        linkedList.addAtFirst(3);
        linkedList.addAtFirst(2);
        linkedList.addAtFirst(1);
        linkedList.addAtFirst(0);
        linkedList.printlist();
        linkedList.reverselist();

        linkedList.printlist();


    }
}
