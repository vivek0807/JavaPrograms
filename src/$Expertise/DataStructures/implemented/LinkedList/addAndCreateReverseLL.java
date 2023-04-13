package $Expertise.DataStructures.implemented.LinkedList;
/*Reverse  provided linked List add them and return answer as reversed linked List*/


  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next;}

  }

class Solution {
    ListNode finalList;
   protected ListNode listA;
    ListNode listB;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1_temp=l1;
        ListNode l2_temp=l2;
        double l1_number=0,count1=1;
        double l2_number=0,count2=1;
        while (l1_temp!=null&& l2_temp!=null){

            if (l1_temp!=null){
                l1_number=l1_temp.val*count1+l1_number;
                count1*=10;
                if (l1_temp.next!=null)
                l1_temp=l1_temp.next;
            }
        }


        return finalList;
    }

    void addAtFirst(int data, int condition){
        if(condition==1){
            if(listA==null){
                listA=new ListNode();
                listA.val=data;
            }
            else
            {
                ListNode temp= new ListNode();
                temp.val=data;
                temp.next=listA;
            listA=temp;
            }
                
        } 
        else if (condition==2) {
            if(listB==null){
                listB=new ListNode();
                listB.val=data;
            }
            else
            {
                ListNode temp= new ListNode();
                temp.val=data;
                temp.next=listB;
                listB=temp;
            }
        }
        else {
            if(finalList==null){
                finalList=new ListNode();
                finalList.val=data;
            }
            else
            {
                ListNode temp= new ListNode();
                temp.val=data;
                temp.next=finalList;
                finalList=temp;
            }
        }

    }

    void Iterate(ListNode node){
        ListNode node1=node;

        while (node1!=null){
            System.out.println(node1.val);
            node1=node1.next;
        }
    }

}
public class addAndCreateReverseLL extends Solution{

    public static void main(String[] args) {
        Solution solution= new Solution();
        solution.addAtFirst(1,1);
        solution.addAtFirst(2,1);
        solution.addAtFirst(3,1);
        solution.addAtFirst(4,1);
        solution.addAtFirst(5,1);
        solution.addAtFirst(6,2);
        solution.addAtFirst(7,2);
        solution.addAtFirst(8,2);
        solution.addAtFirst(9,2);
        solution.addAtFirst(10,2);
        solution.addAtFirst(11,2);
        solution.Iterate(solution.listA);
        solution.Iterate(solution.listB);

    }
}
