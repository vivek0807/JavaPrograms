package $Expertise.DataStructures.implemented.Trees;

import java.util.Scanner;

//{-----DFS TRAVERSAL----}
//Inorder-Left Node Right
//PreOrder-Node Left Right
//Post Order-Left Right Node

//{-------BFS-----}
//Just go at each level
class Node{
    int data;
    Node right;
    Node left;

    public Node(int data) {
        this.data = data;
    }
}

class TreeKeeper{

    Node tree=null;



    void creatTree(int data){
        this.tree=insertnode();
    }
    int data;
     static Node insertnode(){
         Scanner sc= new Scanner(System.in);

         System.out.println("Enter root node data");
         int data= sc.nextInt();
         if(data==-1)
             return null;
         Node root= new Node(data);
         System.out.println("Insert right data for "+data);
        root.right= insertnode();
         System.out.println("Insert Left data for "+data);
         root.left=insertnode();

         return root;
    }
}

public class Btree {
    public static void main(String[] args) {
    TreeKeeper treeKeeper= new TreeKeeper();
    treeKeeper.creatTree(5);
    }
}
