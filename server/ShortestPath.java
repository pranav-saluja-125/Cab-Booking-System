import java.util.*;
public class ShortestPath {

    // Dijkstra's algorithm function
    static void Dis(int[][] arr, int s, int destination) {
        int count = arr.length;
        boolean[] vis = new boolean[count];
        int[] dis = new int[count];

        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[s] = 0;
        for (int i = 0; i < count; i++) {
            int u = finder(dis, vis);
            vis[u] = true;

            for (int v = 0; v < count; v++) {
                if (!vis[v] && arr[u][v] != 0 && (dis[u] + arr[u][v] < dis[v])) {
                    dis[v] = dis[u] + arr[u][v];
                }
            }
        }

        System.out.println(dis[destination]); // Printing the desired output for the specific source and destination
    }

    // Finding the minimum vertex
    static int finder(int[] dis, boolean[] vis) {
        int minn = Integer.MAX_VALUE;
        int minnVer = -1;

        for (int i = 0; i < dis.length; i++) {
            if (!vis[i] && dis[i] < minn) {
                minn = dis[i];
                minnVer = i;
            }
        }

        return minnVer;
    }

    public static void main(String[] args) {
        // The desired graph given for the assessment
        int[][] arr = { { 0, 5, 7, 0, 0, 0 }, 
                        { 5, 0, 0, 15, 20, 0 }, 
                        { 7, 0, 0, 5, 35, 0 }, 
                        { 0, 15, 5, 0, 0, 20 }, 
                        { 0, 20, 35, 0, 0, 10 },
                        { 0, 0, 0, 20, 10, 0 } };

        Dis(arr, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
