import java.util.PriorityQueue;
import java.util.ArrayList;

class Dijkstra{
    public static void main(String[] args){
        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');

        heap.add(a);
        a.distance = 0;

        a.add(new Edge(10, b));
        a.add(new Edge(2, c));
        b.add(new Edge(3, d));
        c.add(new Edge(5, b));
        d.add(new Edge(1, c));


        Node v;
        while(heap.size() > 0){
            v = heap.poll();
            for (Edge e : v.neighbors){
                if((v.distance + e.weight) < e.dest.distance){
                    e.dest.distance = v.distance + e.weight;
                    heap.add(e.dest);
                }
            }
        }

        System.out.println(String.format("Distances\nA: %d\nB: %d\nC: %d\nD: %d", a.distance, b.distance, c.distance, d.distance));

           }
         static class Node implements Comparable<Node>{
            ArrayList<Edge> neighbors = new ArrayList<Edge>();
            char id;
            int distance;
            public Node(char id){
                this.id = id;
                distance = Integer.MAX_VALUE;
            }

            public void add(Edge n){
                neighbors.add(n);
            }

            @Override
            public int compareTo(Node n){
                return Integer.compare(n.distance, this.distance);
            }
        }

        static class Edge{
            int weight;
            Node dest;

            public Edge(int weight, Node dest){
                this.weight = weight;
                this.dest = dest;
            }
        }

}