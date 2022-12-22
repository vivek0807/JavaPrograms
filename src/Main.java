


class Nodes{
    int data;
    Nodes next;
}

class LinkedList{
    int length=0;
    Nodes head=null;
    Nodes tail=null;




    void printList(){
        Nodes current=head;

        while (current!=null){

            System.out.println(current.data);
            current=current.next;
        }
    }

    void addAtLast(int data){
        if(head==null){
             head=new Nodes();
             head.next=null;
            head.data=data;
            length++;

        }
        else {
            Nodes curr=head;
            while(curr.next!=null){
                curr=curr.next;
            }
            curr.next=new Nodes();
            curr.next.data=data;
            length++;
        }
    }
    void reverseList(){
        Nodes back=null;
        Nodes cur=head;
        while (cur!=null){
            Nodes forward=cur.next;
            cur.next=back;
            back=cur;
            cur=forward;
        }
        head=back;
    }
    void addAtFirst(int data){
        if(head==null)
        {
            head=new Nodes();
            head.data=data;
            length++;
            tail=head;
        }
        else {
            Nodes newNode= new Nodes();
            newNode.data=data;
            newNode.next=head;
            head=newNode;
            length++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList= new LinkedList();
                linkedList.addAtFirst(5);
                linkedList.addAtFirst(4);
                linkedList.addAtFirst(3);
                linkedList.addAtFirst(2);
                linkedList.addAtFirst(1);
                linkedList.addAtFirst(0);
                linkedList.printList();
        linkedList.reverseList();
        linkedList.printList();
    }
    }



