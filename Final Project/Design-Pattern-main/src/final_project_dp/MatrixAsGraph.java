package final_project_dp;



import java.util.*;

/**
 * This class uses the Adapter pattern, also known as Wrapper/Decorator.
 * the class adapts a Matrix type to the functionality of IGraph interface
 */

public class MatrixAsGraph implements IGraph<Index> {
    private Matrix innerMatrix;
    private Index source;

    public MatrixAsGraph(Matrix matrix) {
        if (matrix != null) this.innerMatrix = matrix; // if the matrix is empty
        else innerMatrix = new Matrix(); // else create a randomized matrix
        setSource();
    }

    public Matrix getInnerMatrix() {
        return innerMatrix;
    }

    public Index getSource() {
        return source;
    }

    public void setSource() {
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    source = new Index(i, j);
                    return;
                }
            }
        }
    }
    public void setSource(Index source) {
        if (source != null)
            if ((source.row >= 0 && source.row < innerMatrix.primitiveMatrix.length) &&
                    /*
                    we know that matrix  is of size n X n
                    innerMatrix.primitiveMatrix[0].length - number of column in row 0 == number
                    of columns of all other rows
                     */
                    (source.column >= 0 && source.column < innerMatrix.primitiveMatrix[0].length)) {
                this.source = source;
            }
    }
    public boolean nextNodeInTheGraph(){
        int[][] arr = this.innerMatrix.primitiveMatrix;
        for (int j = source.getColumn()+1; j < arr[source.getRow()].length; j++) {
            if (arr[source.getRow()][j] == 1) {
                source = new Index(source.getRow(), j);
                return true;
            }
        }
        for (int i = source.getRow()+1; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 1){
                    source = new Index(i,j);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean backToRoot(){
        setSource();
        return true;
    }

    @Override
    public Node<Index> getNode(Index index) {
        return innerMatrix.getValue(index) == 1 ? new Node<>(index) : null;
    }

    @Override
    public Node<Index> getRoot() {
        return new Node<>(source);
    }

    @Override
    public Collection<Node<Index>> getReachableNodes(Node<Index> aNode) {
        if (innerMatrix.getValue(aNode.getData()) == 1) { // Only if the given node equals 1, we will proceed
            List<Node<Index>> reachableNodes = new ArrayList<>(); // Array list that will contain the reachable nodes
            for (Index index : innerMatrix.getNeighbors(aNode.getData())) {  // go over the neighbor index of the node
                if (innerMatrix.getValue(index) == 1) // if the value in the index is 1
                    reachableNodes.add(new Node<>(index, aNode));  // add it to the array list of the neighbors
            }

            return reachableNodes; // return the list
        }
        return null; // return null if there sre no 1's
    }



}
