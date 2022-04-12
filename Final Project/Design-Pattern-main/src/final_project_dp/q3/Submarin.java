package final_project_dp.q3;

import final_project_dp.*;
import final_project_dp.q1.DfsConnectedComponents;

import java.util.*;
import java.util.stream.Collectors;

public class Submarin {
    static int Row =0;
    static int Column =0;

    static boolean isValid(Index index){
        return (index.getRow()>=0)&& (index.getRow()<Row)&&
                (index.getColumn()>=0)&&(index.getColumn()<Column);
    }
    public Collection<Index> traverse(IGraph<Index> someGraph){
        Stack<Node<Index>> workingStack = new Stack<>();
        Set<Node<Index>> finished = new LinkedHashSet<>();
        workingStack.push(someGraph.getRoot());
        while(!workingStack.isEmpty()){
            Node<Index> removed = workingStack.pop();
            finished.add(removed);
            Collection<Node<Index>> reachableNodes = someGraph.getReachableNodes(removed);
            for(Node<Index> reachableNode: reachableNodes){
                if(!finished.contains(reachableNode)
                        && !workingStack.contains(reachableNode)){
                    workingStack.push(reachableNode);
                }
            }
        }
        HashSet<Index> blackSet = new LinkedHashSet<>();
        for(Node<Index> node: finished){
            blackSet.add(node.getData());
        }
        // we only have the finished set. finished:
        finished.clear();
        return blackSet;
    }

    // Generic method to convert a set to a list
    public static <T> List<T> convertToList(Set<T> set) {
        return new ArrayList<>(set);
    }

    // counts the valid submarines
    public static int allSubMarines(int [][] twoDArray){
        Matrix matrix = new Matrix(twoDArray);
        MatrixAsGraph matrixAsGraph = new MatrixAsGraph(matrix);
        ArrayList<ArrayList <Index>> listOfArrayList = new ArrayList<>();
        ArrayList<Index>listOfIndexesWithValue = new ArrayList<>();
        Queue<Index> workingQueue = new LinkedList<>();
        DfsConnectedComponents<Index> dfsConnectedComponents = new DfsConnectedComponents<>();
        listOfIndexesWithValue = (ArrayList<Index>) (dfsConnectedComponents.allConnectedComponents(matrixAsGraph));
//        listOfIndexesWithValue = dfsConnectedComponents.allConnectedComponents(matrixAsGraph);
//        ArrayList<Index> arr1=new ArrayList<>(listOfIndexesWithValue);
//        for(Index i:listOfIndexesWithValue)
//            arr1.add(i);


        Submarin submarin = new Submarin();
        for(Index index: listOfIndexesWithValue){
            if(!workingQueue.contains(index)){
                matrixAsGraph.setSource(index);
                ArrayList<Index> traverseList = new ArrayList<>();
                traverseList= (ArrayList<Index>) (submarin.traverse(matrixAsGraph));
                workingQueue.addAll(traverseList);
                listOfArrayList.add(traverseList);
                System.out.println("current traverseList"+ traverseList.toString());
            }
        }
        System.out.println("ListOfArrays "+ listOfArrayList.toString());
        Row= twoDArray.length;
        Column= twoDArray[0].length;

        int subMarinesCounter = 0;
        for (int i=0;i<listOfArrayList.size();i++){
            if(isValidMarin(listOfArrayList.get(i)))subMarinesCounter++;
        }
        System.out.println();
        return subMarinesCounter;
    }

                                                                                                                                    public static int allSubMarinies(int [][]twoD){
                                                                                                                                                  return  2;
                                                                                                                                                                 }

    public static  boolean isValidMarin(ArrayList<Index> marines){
        if(marines.size()<2) return false;
        for (Index index:marines) {

            Index right =new Index(index.getRow(), index.getColumn()+1);
            Index left =new Index(index.getRow(), index.getColumn()+1);

            Index down =new Index(index.getRow()+1, index.getColumn());
            Index up =new Index(index.getRow()-1, index.getColumn());

            Index rightUp =new Index(index.getRow()-1, index.getColumn());
            Index leftUp =new Index(index.getRow()-1, index.getColumn()-1 );

            Index downRight =new Index(index.getRow()+1, index.getColumn());
            Index downLeft =new Index(index.getRow()+1, index.getColumn()-1 );

            if (isValid(right) && marines.contains(right)){
                if(isValid(down)&& marines.contains(down)){
                    if(!marines.contains(downRight))
                        return false;
                }
                if(isValid(downRight)&& marines.contains(downRight)){
                    if(!marines.contains(down))
                        return false;
                }
                if(isValid(rightUp)&& marines.contains(rightUp)){
                    if(!marines.contains(up))
                        return false;
                }
            }
            if(isValid(down)&&marines.contains(down)){
                if(isValid(downRight)&& marines.contains(rightUp)){
                    if(!marines.contains(right))
                        return false;
                }
            }

            //diagonal 1 - downLeft
            if(isValid(downLeft)&& marines.contains(downLeft)){
                if(isValid(downRight)&& !marines.contains(downRight)&& isValid(left)&&!marines.contains((left))){
                    if(isValid(downRight)&&!marines.contains(down))
                        return false;
                }
            }
            //diagonal 2 - downRight
            if(isValid(downRight)&& marines.contains(downRight)){
                if(isValid(downLeft)&& !marines.contains(downLeft)&& isValid(left)&&!marines.contains((right))){
                    if(isValid(downLeft)&&!marines.contains(downLeft))
                        return false;
                }
            }
            //diagonal 3 - rightUp
            if(isValid(rightUp)&& marines.contains(rightUp)) {
                if (isValid(up) && !marines.contains(up))
                    return false;
            }

            //diagonal 4 - leftUp
            if(isValid(leftUp)&& marines.contains(leftUp)){
                    if(isValid(up)&&!marines.contains(up))
                        return false;

            }


        }

        return true;
    }

    public static void main(String[] args) {
        int[][] mat3 = {
                //  0  1  2
                {1, 0, 0}, // 0
                {1, 0, 1}, // 1
                {0, 0, 1} // 2

        };
        System.out.println(allSubMarinies(mat3));
    }
}
