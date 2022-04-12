package final_project_dp.q4;

import final_project_dp.Index;
import final_project_dp.Matrix;

import java.util.*;

public class EasyPath {

    public static void Dijkstra(int [][] twoDArray, Index source,Index destination){
        Matrix matrix = new Matrix(twoDArray);

        source.distance = 0;
        source.parent=null;

        Queue<Index> workingQueue = new LinkedList<>();   // this queue for the current values
        Set<Index> visited = new LinkedHashSet<>(); // set for the visited
        Set<Index> finished = new LinkedHashSet<>(); // set for the finished

        ArrayList<Index> finalTrail = new ArrayList<>();
        Stack<Index> stackFinal = new Stack<>();
        Index destinationTrail = null;

        workingQueue.add(source);
        while(!workingQueue.isEmpty()){
            Index tempI = workingQueue.remove();
            visited.add(tempI);
            finished.add(tempI);
            ArrayList<Index> neighbors =(ArrayList<Index>) matrix.getNeighbors(tempI);

            for(Index item:neighbors){
                item.parent=tempI;
                item.value=twoDArray[item.getRow()][item.getColumn()];
                // we use the stream function because it helps us to convert the data from the set to collection
                Index tempItem = visited.stream().filter(index ->index.equals(item)).findAny().orElse(null);

                if(tempItem != null){
                    item.distance = tempItem.distance;
                }
                if(!item.equals(tempI.parent)){
                    if(item.distance == -1){
                        item.distance = (item.value + item.parent.distance);
                        System.out.println("item: "+item+", item.distance: "+ item.distance);
                        workingQueue.add(item);
                    }
                    else{
                        int tempDistance = item.value + item.parent.distance;
                        if(tempDistance<item.distance){
                            item.distance = tempDistance;
                            item.parent = tempI;

                            System.out.println("from else: "+ "item:"+ item+ ", item.distance: "+item.distance);
                        }
                    }
                }
                if(item.equals(destination)){
                    if(!destination.isVisited()){
                        destinationTrail =item;
                        destination.setVisited(true);
                    }
                    else if(item.distance < destinationTrail.distance)destinationTrail=item;
                }
                if(!finished.contains(item)&& destinationTrail ==null){
                    workingQueue.add(item);
                    visited.add(item);
                }
            }
        }
        if(destinationTrail!=null){
            System.out.println("the distance from source: "+ destinationTrail.distance);
            while(!destinationTrail.equals(source)){
                Index tempIndex = destinationTrail;
                stackFinal.add(tempIndex);
                destinationTrail = destinationTrail.parent;
            }
            stackFinal.add(destinationTrail);
            while(!stackFinal.isEmpty()){
                finalTrail.add(stackFinal.pop());
            }
        }
        System.out.println(finalTrail);

    }

    public static void main(String[] args) {
        int[][]twoDArray={
                {100,100,100},
                {500,900,300}

        };
        Dijkstra(twoDArray,new Index(1,0),new Index(1,2));

    }
}
