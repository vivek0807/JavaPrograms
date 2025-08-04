package $Expertise.DataStructures.implemented.LinkedList;

import java.util.HashSet;

class Node{
    int val;
    Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
    Node(){}
}
class MergeTwoSortedList{
    public Node mergeTwoSortedList(Node list1,Node list2){
        Node newList= new Node();
        Node tail=newList;
        while (list1!=null && list2!=null){
            if(list1.val< list2.val)
            {
                tail.next=list1;
                list1=list1.next;

            }
            else
            {
                tail.next=list2;
                list2=list2.next;

            }
            tail=tail.next;
        }

        if (list2!=null ){
            tail.next=list2;
        }
        else if (list1!=null ){
            tail.next=list1;
        }
        return newList.next;
    }
}
class AddTwoNumebrs{
    /**
     *<h1>Adding two numbers from linkedList</h1>
     * <li>Create a new Node</li>
     * <li>Iterate through end of both the LLs</li>
     * <li>add carry to sum and then add the value of the node with the sum</li>
     * <li>Separate out carry and sum</li>
     * <li>If carry remains one at the end add and extra node with 1 at the end of the list</li>
     */
    public Node addTwoNumbers(Node listOne,Node listTwo){
        Node addedNumber= new Node(0,null);
        Node pointer=addedNumber;
        int carry=0;
        while (listOne!=null || listTwo!=null){
            int sum=carry;
            if (listOne!=null){
                sum=sum+ listOne.val;
                listOne=listOne.next;
            }
            if (listTwo!=null){
                sum=sum+ listTwo.val;
                listTwo=listTwo.next;
            }

            carry=sum/10;
            sum=sum%10;
            pointer.next=new Node(sum,null);
            pointer=pointer.next;
        }
        if (carry==1)
            pointer.next=new Node(1,null);

        return addedNumber;
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
                this.head = newnode;
                this.tail = head;
            }
        }
        else {
            Node newnode = new Node(data, null);
            newnode.next = this.head;
            this.head = newnode;
        }

    }

    void deletenode(int data){
        if(head.val ==data && head.next==null)
            head=head.next;
        if(head!=null) {
            Node current=head.next;
            Node backward=head;
            while (current!=null){
                if(current.val ==data){
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
        HashSet<Node> hashSet =new HashSet<>();
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
            System.out.println("Fast data->"+fast.val);
            System.out.println("Slow data->"+slow.val);

            fast=fast.next.next;
            slow=slow.next;
        }
    }

    void remove_duplicates(){
        System.out.println("Removing duplicates");
        Node pointer=head;

        while (pointer.next!=null){
            if(pointer.val ==pointer.next.val){
                Node connector=pointer.next;
                while (connector.val ==pointer.val && connector.next!=null)
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
        System.out.println("Nth node from the end is"+main.val);
    }
    // ref pointer till n then both pointer till null

    void reverselist(){
        if(head.next==null){
            printlist();
        }
        else {
            Node prev=null;     //KEY_POINT change the middle node pointer to backward
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
        {  System.out.println(pointer.val);
            pointer=pointer.next;}
    }
    void printlist(Node node){
        while (node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }

    public  Node getHead(){
        return this.head;
    }
void rotateRight(int k, Node head){
        int length=0;
        Node curNode=head;
   while (curNode!=null){
       ++length;
       curNode= curNode.next;
   }
   // System.out.println("L of LL "+length);
     k=k%length;
    curNode=head;
    Node newhead=null;
    if (k==length ||k==0)
        return;
    for (int i = 0; i <length ; i++) {
        if (i==k-1){
            Node temp=curNode.next;
            curNode.next=null;
            curNode=temp;
            newhead=curNode;
        }
        if (i==length-2){
            curNode.next=head;
        }
        if (curNode!=null)
            curNode=curNode.next;
    }
    this.head=newhead;
}


}


public class SingleLL {
    public static void main(String[] args) {
//        System.out.println("Linked list program begin");
//        LinkedList linkedList= new LinkedList();
//        linkedList.head=null;
//        System.out.println("List Initialised");
//
//        linkedList.addAtFirst(1);
//        linkedList.addAtFirst(2);
//        linkedList.addAtFirst(3);
//
//        linkedList.printlist();
//        linkedList.rotateRight(1,linkedList.getHead());
//
//        linkedList.printlist();

        Node list1=new Node(1,new Node(1,new Node(1,null)));
        Node list2= new Node(1,new Node(1,new Node(1,null)));

        Node node = new AddTwoNumebrs().addTwoNumbers(list1,list2);
        new LinkedList().printlist(node);


    }
}
