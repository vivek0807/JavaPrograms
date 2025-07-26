package $Expertise.DataStructures.implemented.Graphs;


import java.util.Scanner;

public class Graph {
    void matrixRepresentation(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of nodes");
        int n= sc.nextInt();
        int arrr[][]= new int[n+1][n+1];
        int nodes= n;
        while (--n!=0){
            System.out.println("Enter the two nodes of connection separated by spaces");
            int x= sc.nextInt();
            int y= sc.nextInt();
            if (x>nodes || y>nodes)
            { System.out.println("Graph index out of bound");
            System.exit(-1);
            }
            if (arrr[x][y]==1)
                System.out.println("These nodes are already connected");
            else
                arrr[x][y]=1;

        }

        for (int i = 0; i <=nodes ; i++) {
            for (int j = 0; j <=nodes; j++) {
                if (arrr[i][j]==1)
                    System.out.println("Connection Exists b/w node " +i+" "+j);
            }
        }
    }

    public static void main(String[] args) {

        Graph graph= new Graph();
        graph.matrixRepresentation();
    }
}
