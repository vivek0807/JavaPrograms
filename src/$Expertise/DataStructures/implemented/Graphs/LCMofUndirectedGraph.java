package $Expertise.DataStructures.implemented.Graphs;

/**
 * given an undirected graph consisting of n. nodes. there is a no. written on each node which is given in form of an array f N distinct integers , where a[i] represents  the number written on ith node.
 * There is an edge between the node i and j if lcm(a[i], , a[j]) <= 10^8
 *
 * where LCM of 2 numbers is the smallest positive integer which is divisible by both of them .
 * return the number of connected components in graph
 *
 * NOte that 1-based indexing os followed
 * a connected component of an undirected graph is an induced subgraph in which any 2 vertices are connected to each other by paths and which is connected to no additional vertices in the rest of the graph
 *
 *
 *
 * complete the function :
 * static int solve(int N, int[] arr){
 * return result}
 */
import java.util.*;

class GraphComponents {

    public static int solve(int N, int[] arr) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph based on LCM condition
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long lcm = lcm(arr[i], arr[j]);
                if (lcm <= 1e8) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Count connected components using DFS
        boolean[] visited = new boolean[N];
        int components = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                components++;
            }
        }

        return components;
    }

    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    private static long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6};
        System.out.println(solve(arr.length, arr)); // Output depends on LCM conditions
    }
}

public class LCMofUndirectedGraph {

    public static void main(String[] args) {

    }
}
