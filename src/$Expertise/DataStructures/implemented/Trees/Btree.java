package $Expertise.DataStructures.implemented.Trees;

import java.util.Scanner;
//Inorder-Left Node Right
//PreOrder-Node Left Right
//Post Order-Left Right Node
class Node {
    Node left;
    Node right;
    int data;

    public Node(int data) {

        this.data = data;
    }
}



public class Btree {
    static Node createBT(){
        Node node=null;
        System.out.println("Enter value for this node");
        Scanner sc=new Scanner(System.in);
        int data=sc.nextInt();
        if (data==-1)
            return null;
        node = new Node(data);
        System.out.println("Enter right node value  "+data);
        node.right=createBT();
        System.out.println("Enter left node value"+data);
        node.left=createBT();

        return node;
    }

    static void Inorder(Node node){
        if(node==null)
            return;
        Inorder(node.right);
        System.out.println("/- "+node.data);
        Inorder(node.left);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        createBT();
    }
}
