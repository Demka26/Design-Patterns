package final_project_dp;


import final_project_dp.q1.DfsConnectedComponents;
import final_project_dp.q2.ShortsPath;

import java.util.*;

public class Demo {

    public static void main(String[] args) {

        // בדיקת תקינות משימה 1
        int[][] mat1 = {
                //0  1  2
                {1, 0, 0}, // 0
                {1, 0, 1}, // 1
                {0, 0, 1} // 2

        };
        MatrixAsGraph matrixAsGraph1 = new MatrixAsGraph(new Matrix(mat1));
        DfsConnectedComponents<Index> dfsConnectedComponents = new DfsConnectedComponents<>();
        System.out.println(dfsConnectedComponents.allConnectedComponents(matrixAsGraph1)); // [[(0,0), (1,0), (2,1), (1,2), (2,2)]]

        int[][] mat = {
                //  0  1  2
                {1, 0, 0}, // 0
                {1, 1, 0}, // 1
                {0, 1, 0}, // 2
                {1, 0, 1}, // 3
                {1, 1, 1}, // 4
        };

        MatrixAsGraph matrixAsGraph2 = new MatrixAsGraph(new Matrix(mat));
        ShortsPath<Index> bfs = new ShortsPath<>();
        LinkedList<Queue<Index>> s= bfs.shortsPath(matrixAsGraph2,new Index(0,0),new Index(4,2));
        System.out.println("Short path: ");
        System.out.println(s);


        //  Set<Index> c = bfs.bfs(matrixAsGraph, new Index(1,1));
        //Set<Index> cc = bfs.shortsPath(matrixAsGraph, new Index(0,0), new Index(4,2));
////        Set<Set<Index>> cc2 = dfsConnectedComponents.allConnectedComponents(matrixAsGraph);
//
      //  System.out.println(cc);
     //   System.out.println(c);
////        System.out.println(cc2);

        int[][] mat3 = {
                //0  1  2
                {1, 0, 1, 1}, // 0
                {1, 1, 1, 1} // 1

        };
        MatrixAsGraph matrixAsGraph3 = new MatrixAsGraph(new Matrix(mat3));
        System.out.println(matrixAsGraph3.getInnerMatrix());
        matrixAsGraph3.setSource(new Index(0,0));
        DfsVisit<Index> algorithm = new DfsVisit<>();
        List<Index> ccc= new ArrayList<>(algorithm.traverse(matrixAsGraph2));
        System.out.println(ccc);

    }

}
