import java.util.*;
        import java.util.function.BiPredicate;
        import java.util.function.Function;
        import java.util.function.Predicate;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {


        List<Point> path = new LinkedList<>();

        //open list
        Map<Point,Node> openMap = new HashMap<>();
        Comparator<Node> sorter = Comparator.comparing(Node::getF);
        PriorityQueue<Node> openQueue = new PriorityQueue<>(sorter);

        //closed list
        Map<Point, Node> closedMap = new HashMap<>();

        //create current node which is the starting node
        Node current = new  Node(null, start, 0, getDistance(start, end));

        //add to open list
        openMap.put(current.getPosition(), current);
        openQueue.add(current);

        //while target is not within reach
        while (!withinReach.test(current.getPosition(), end)){

            //get list of neighbour points
            List<Point> neighbours = potentialNeighbors.apply(current.getPosition())
                    .filter(canPassThrough)
                    .filter(p -> !closedMap.containsKey(p))
                    .collect(Collectors.toList());


            for (Point neighbour : neighbours){
                // turn point into node
                Node node = new Node(current, neighbour, current.getG() + 1, getDistance(neighbour, end));
                //add adjacent nodes to open list if not already in open list
                if (!openMap.containsKey(node.getPosition())){
                    openQueue.add(node);
                    openMap.put(node.getPosition(), node);
                } else { //if already in open list, check to see if cur g value is better
                    Node oldNode = openMap.get(node.getPosition());
                    if (oldNode.getG() > node.getG()){
                        openQueue.remove(oldNode); //remove old node in list
                        openQueue.add(node); // add new node with better G value in list
                        openMap.replace(node.getPosition(), oldNode, node); // replacing old node with new node with better G value in map
                    }
                }
            }

            openQueue.remove(current); //remove current node from open list
            openMap.remove(current.getPosition());//remove current node from open map
            closedMap.put(current.getPosition(), current); //add current node to closedMap

            //if open list is not empty, sort the open list by F value and get the first node
            if (openQueue.peek() != null){
                current = openQueue.poll();
            } else { // if empty
                return path;
            }
        }
        while (current.getParent() != null) {
            path.add(0, current.getPosition());
            current = current.getParent();
        }
        return path;
    }

    public double getDistance(Point p1, Point p2){
        return Math.abs((p1.getY() - p2.getY()) + (p1.getX() - p2.getX()));
    }

    private class Node{

        private double g, h, f;
        private Node parent;
        private Point position;


        public Node(Node parent, Point position, double g, double h){
            this.parent = parent;
            this.position = position;
            this.g = g;
            this.h = h;
            this.f = g+h;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() == this.getClass()){
                return false;
            }

            boolean parentEQ, posEQ, gEQ, hEQ, fEQ;

            Node other = (Node)obj;
            parentEQ = Objects.equals(this.parent, other.parent);
            posEQ = Objects.equals(this.position, other.position);
            gEQ = this.g == other.g;
            hEQ = this.h == other.h;
            fEQ = this.f == other.f;
            return parentEQ && posEQ && gEQ && hEQ && fEQ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.parent, this.position, this.g, this.h, this.f);
        }

        @Override
        public String toString() {
            return String.valueOf(this.position);
        }

        public Node getParent() {
            return parent;
        }

        public Point getPosition() {
            return position;
        }

        public double getG() {
            return g;
        }

        public double getF() {
            return f;
        }

    }

}

//import java.util.*;
//import java.util.function.BiPredicate;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//class AStarPathingStrategy
//        implements PathingStrategy
//{
//    public List<Point> computePath(Point start, Point end,
//                                   Predicate<Point> canPassThrough,
//                                   BiPredicate<Point, Point> withinReach,
//                                   Function<Point, Stream<Point>> potentialNeighbors)
//    {
//
//
//        List<Point> path = new LinkedList<>();
//
//        //open list
//        List<Node> openList = new LinkedList<>();
//        Map<Point,Node> openMap = new HashMap<>();
//        Comparator<Node> sorter = Comparator.comparing(Node::getF);
//        PriorityQueue<Node> openQueue = new PriorityQueue<>(sorter);
//
//        //closed list
//        Map<Point, Node> closedMap = new HashMap<>();
//
//        //create current node which is the starting node
//        Node current = new  Node(null, start, 0, getDistance(start, end));
//
//        //add to open list
//        openList.add(current);
//        openMap.put(current.getPosition(), current);
//
//        //while target is not within reach
//        while (!withinReach.test(current.getPosition(), end)){
//            //get list of neighbour points
//            List<Point> neighbours = potentialNeighbors.apply(current.getPosition())
//                    .filter(canPassThrough)
//                    .filter(p -> !closedMap.containsKey(p))
//                    .collect(Collectors.toList());
//
//            // make list of neighbour Nodes
////            List<Node> neighbourNodes = new LinkedList<>();
//            // turn point into node and add to neighbourNodes
//            for (Point neighbour : neighbours){
//                Node node = new Node(current, neighbour, current.getG() + 1,getDistance(neighbour, end));
//                //add adjacent nodes to open list if not already in open list
//                if (!openMap.containsKey(node.getPosition())){
//                    openList.add(node);
//                    openMap.put(node.getPosition(), node);
//                } else{ //if already in open list, check to see if cur g value is better
//                    Node oldNode = openMap.get(node.getPosition());
//                    if (oldNode.getG() > node.getG()){
//                        // remove
//                        openList.remove(oldNode); //remove old node in list
//                        openList.add(node); // add new node with better G value in list
//                        openMap.replace(node.getPosition(), oldNode, node); // replacing old node with new node with better G value in map
//                    }
//                }
//            }
//
//            openList.remove(current); //remove current node from open list
//            openMap.remove(current.getPosition());//remove current node from open map
//            closedMap.put(current.getPosition(), current); //add current node to closedMap
//
//            //if open list is not empty, sort the open list by F value and get the first node
//            if (!openList.isEmpty()){
//                openList = openList.stream().sorted(Comparator.comparing(Node::getF)).collect(Collectors.toList());
//                current = openList.get(0);
//            } else { // if empty
//                return path;
//            }
//        }
//        while (current.getParent() != null) {
//            path.add(0, current.getPosition());
//            current = current.getParent();
//        }
//        return path;
//    }
//
//    public double getDistance(Point p1, Point p2){
//    return Math.abs((p1.getY() - p2.getY()) + (p1.getX() - p2.getX()));
//    }
//
//    private class Node{
//
//        private double g, h, f;
//        private Node parent;
//        private Point position;
//
//        public Node(Node parent, Point position, double g, double h){
//            this.parent = parent;
//            this.position = position;
//            this.g = g;
//            this.h = h;
//            this.f = g+h;
//        }
//
//        public Node getParent() {
//            return parent;
//        }
//
//        public Point getPosition() {
//            return position;
//        }
//
//        public double getG() {
//            return g;
//        }
//
//        public double getF() {
//            return f;
//        }
//
//    }
//
//}