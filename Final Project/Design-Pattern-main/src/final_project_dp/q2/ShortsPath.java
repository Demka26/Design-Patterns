package final_project_dp.q2;

import final_project_dp.IGraph;
import final_project_dp.Index;
import final_project_dp.Node;

import java.util.*;

public class ShortsPath<T> {

    private Stack<Node<Index>> workingQ; // באיזה קודקודים אני מבקר
    private Set<Node<Index>> finished; // סט של מי שאני כבר ביקרתי אצלו בסיבוב הנוכחי
    private Set<Node<T>> visitedVertices; // סט של כל הקודקודים שביקרתי בהם


    public ShortsPath() {
        this.workingQ = new Stack<>();
        this.finished = new LinkedHashSet<>();
        this.visitedVertices = new LinkedHashSet<>();
    }

    public LinkedList<Queue<Index>> shortsPath(IGraph<Index> graph, Index source, Index destination) {

        LinkedList<Queue<Index>> allPathsFromSourceToDestination = new LinkedList<>();// רשימה הכוללת את כל המסלולים מהמקור ליעד
        LinkedHashMap<Index, Queue<Index>> paths = new LinkedHashMap<>(); // מפה שמכילה את המסלול לפי כל אינדקס
        // השמה לnode
        Node<Index> sourceNode = new Node<>(source);
        Node<Index> destinationNode = new Node<>(destination);

        // תור בשביל הצומת יעד
        Queue<Index> path = new LinkedList<>();

        paths.put(source, path);
        workingQ.push(sourceNode);

        while (!workingQ.isEmpty()) {
            Node<Index> removed = workingQ.pop(); // מוציאים מראש המחסנית
            finished.add(removed); // סימון שביקרנו
            paths.get(removed.getData()).add(removed.getData()); // מכניסים לתור את ה removed

            Collection<Node<Index>> neighbors = graph.getReachableNodes(removed); //קודקודים שכנים
            for (Node<Index> neighbor : neighbors) {
                if (neighbor.equals(destinationNode)) { // אם מצאנו שהשכן הוא היעד
                    paths.get(removed.getData()).add(neighbor.getData());// נוסיף אותו למסלול
                    allPathsFromSourceToDestination.add(paths.get(removed.getData())); // וגם מוסיפים אותו לרשימה של המסלולים
                }
                if (!finished.contains(neighbor) && !workingQ.contains(neighbor)) { // אם לא ביקרתי בו עדיין
                    workingQ.add(neighbor); // מוסיפים אותו לרישמה
                    // להוסיף את המסלול למפה
                    Queue<Index> q = new LinkedList<>(paths.get(removed.getData()));
                    paths.put(neighbor.getData(), q);
                }
            }

        }

        // צריך מכל המסלולים לפלטר את הכי קצרים
        int minLength = allPathsFromSourceToDestination.getFirst().size(); // הגודל של המסלול הכי קצר
        // למצוא את הכוגל שלו
        for (Queue<Index> q : allPathsFromSourceToDestination)
            minLength = Math.min(minLength, q.size());

        LinkedList<Queue<Index>> finalPath = new LinkedList<>(); // הרשימה של התורים שאותם נחזיר
        // נכניס לרשימה רק את התורים שהגודל שלהם שווה לגודל המינימאלי
        for (Queue<Index> q : allPathsFromSourceToDestination)
            if (q.size() == minLength)
                finalPath.add(q);

        return finalPath;
    }
}
