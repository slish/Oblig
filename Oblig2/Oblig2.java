import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.HashSet;

public class Oblig2{

    public static void main(String[] args) throws IOException{
        // Lagrer alle filmer og skuespillere i hashmaps for å kunne hente de
        // ut senere via ID
        HashMap<String, Movie> movieDict = new HashMap<>();
        HashMap<String, Node> allNodes = new HashMap<>();

        InputStreamReader movieIn = new InputStreamReader(
            new FileInputStream("movies.tsv"), "UTF-8");
        BufferedReader movieReader = new BufferedReader(movieIn);

        // Les movies.tsv og legg til alle filmer i hashmap
        for (String line = movieReader.readLine(); 
                line != null; 
                line = movieReader.readLine()){
            String[] lineSplit = line.split("\t");
            Movie movie = new Movie(lineSplit[0], 
                                    lineSplit[1], 
                                    Float.parseFloat(lineSplit[2]));
            movieDict.put(lineSplit[0], movie);
        }

        InputStreamReader actorIn = new InputStreamReader(
            new FileInputStream("actors.tsv"), "UTF-8");
        BufferedReader actorReader = new BufferedReader(actorIn);

        // Les actors.tsv og legg til alle skuespillere i hashmap
        for (String line = actorReader.readLine();
                line != null;
                line = actorReader.readLine()){

            String[] lineSplit = line.split("\t");

            // For hver filmId i skuespillerlinje, sjekk om filmen er i 
            // biblioteket over filmer. Om den er med, legg den til film i 
            // skuespillernoden og legg til skuespiller i filmobjektet
            Node actor = new Node(lineSplit[0], lineSplit[1]);
            for (int i = 2; i < lineSplit.length; i++){
                if(movieDict.containsKey(lineSplit[i])){
                    Movie movieToAdd = movieDict.get(lineSplit[i]);
                    actor.addMovie(movieToAdd);
                    movieToAdd.addActor(actor);
                }
            }
            allNodes.put(lineSplit[0], actor);
        }

        // For hver film i filmbiblioteket, knytt alle skuespillere som naboer
        for(Movie m : movieDict.values()){
            float rating = m.getRating();
            ArrayList<Node> actorsInMovie = m.getActors();

            for(int i = 0; i < actorsInMovie.size(); i++){
                Node thisActor = actorsInMovie.get(i);

                // Knytt hver skuespiller til hver skuespiller i den resterende delen 
                // av listen. De har allerede blitt knyttet til skuespillere som kommer
                // før dem i listen, og kan overse disse.
                for(int j = i+1; j < actorsInMovie.size(); j++){
                    Node nextActor = actorsInMovie.get(j);
                    thisActor.addNeighbour(rating, nextActor, m.getTitle());
                }                    
            }
        }

        int Nodes = 0;
        int Edges = 0;
        for(Node n : allNodes.values()){
            Nodes ++;
            Edges += n.getEdges().size();
        }

        // Deler edges på 2 for å eliminere de som peker på hverandre
        Edges /= 2;
        System.out.println("Oppgave 1 \n");
        System.out.println("Nodes: " + Nodes);
        System.out.println("Edges: " + Edges + "\n");

        // Kjører oppgave 2 og 3 i egne metoder som er nederst i fil.
        // Sender med skuespillere i 2 og 3 og i tillegg alle nodene til 
        // oppgave 3.

        System.out.println("Oppgave 2 \n");

        oppgave2(allNodes.get("nm2255973"), allNodes.get("nm0000460"));
        oppgave2(allNodes.get("nm0424060"), allNodes.get("nm0000243"));
        oppgave2(allNodes.get("nm4689420"), allNodes.get("nm0000365"));
        oppgave2(allNodes.get("nm0000288"), allNodes.get("nm0001401"));
        oppgave2(allNodes.get("nm0031483"), allNodes.get("nm0931324"));

        System.out.println("Oppgave 3 \n");

        /*oppgave3(allNodes.get("nm2255973"), 
                    allNodes.get("nm0000460"), 
                    allNodes);
        oppgave3(allNodes.get("nm0424060"), 
                    allNodes.get("nm0000243"), 
                    allNodes);
        oppgave3(allNodes.get("nm4689420"), 
                    allNodes.get("nm0000365"), 
                    allNodes);
        oppgave3(allNodes.get("nm0000288"), 
                    allNodes.get("nm0001401"), 
                    allNodes);
        oppgave3(allNodes.get("nm0031483"),
                    allNodes.get("nm0931324"), 
                    allNodes);*/

        System.out.println("Oppgave 4 \n");

        oppgave4(allNodes);
    }

    static class Node implements Comparable<Node>{
        String nmId;
        String name;
        ArrayList<Edge> neighbours = new ArrayList<>();
        ArrayList<Movie> moviesPlayedIn = new ArrayList<>();
        Edge leadingHere;
        float weight;

        public Node(String nmId, String name){
            this.nmId = nmId;
            this.name = name;
        }

        public String getId(){return nmId;}
        public String getName(){return name;}
        public ArrayList<Edge> getEdges(){return neighbours;}
        public ArrayList<Movie> getMovies(){return moviesPlayedIn;}
        public void addMovie(Movie m){
            moviesPlayedIn.add(m);
        }
        public void setLeadingEdge(Edge e){leadingHere = e;}
        public Edge getLeadingEdge(){return leadingHere;}
        public void setWeight(float w){weight = w;}
        public float getWeight(){return weight;}

        // Lag en edge fra denne noden til nabo, og fra nabo til denne noden
        public void addNeighbour(float weight, Node neighbour, String movieTitle){
            Edge thisEdge = new Edge(weight, this, neighbour, movieTitle);
            this.addEdge(thisEdge);
            neighbour.addEdge(thisEdge);
        }

        public void addEdge(Edge e){
            neighbours.add(e);
        }

        @Override
        public int compareTo(Node n){
            if(this.weight == n.getWeight()){
                return 0;
            } else if(this.weight > n.getWeight()){
                return -1;
            }else{
                return 1;
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        float weight;
        Node n1;
        Node n2;
        String movieName;

        public Edge(float weight,  Node n1, Node n2, String movieName){
            this.weight = weight;
            this.n1 = n1;
            this.n2 = n2;
            this.movieName = movieName;
        }

        public Node getn1(){return n1;}
        public Node getn2(){return n2;}
        public String getMovieName(){return movieName;}
        public float getWeight(){return weight;}

        @Override
        public int compareTo(Edge e){
            if(this.weight == e.getWeight()){
                return 0;
            } else if(this.weight > e.getWeight()){
                return 1;
            }else{
                return -1;
            }
        }
    }

    //Lagrer alle filmer som en klasse for å kunne hente ut info
    static class Movie{
        String ttId;
        String title;
        float rating;
        ArrayList<Node> actors = new ArrayList<>();

        public Movie(String ttId, String title, float rating){
            this.ttId = ttId;
            this.title = title;
            this.rating = rating;
        }
        public String getId()       {return ttId;}
        public String getTitle()    {return title;}
        public float getRating()    {return rating;}
        public void addActor(Node n){
            actors.add(n);
        }
        public ArrayList<Node> getActors() {return actors;}
    }

    static void oppgave2(Node fromActor, Node toActor){
        System.out.println(fromActor.getName());
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<ArrayList<Edge>> edgeLayers = new ArrayList<>();
        ArrayList<ArrayList<Node>> nodeLayers = new ArrayList<>();

        queue.add(fromActor);
        visited.add(fromActor);
        nodeLayers.add(visited);

        outerloop:
        while(!queue.isEmpty()){
            ArrayList<Edge> newEdges = new ArrayList<>();
            ArrayList<Node> newNodes = new ArrayList<>();

            // Hvor mange ganger vi skal kjøre loopen, for å vite hvor mange noder
            // som skal legges til dette laget
            int numNodesInLayer = queue.size();
            for(int i = 0; i < numNodesInLayer; i++){
                Node currentNode = queue.poll();
                
                for(Edge e : currentNode.getEdges()){

                    Node destNode;
                    if(currentNode == e.getn1()){
                        destNode = e.getn2();
                    }else{
                        destNode = e.getn1();
                    }

                    if(!visited.contains(destNode)){
                        destNode.setLeadingEdge(e);
                        newEdges.add(e);
                        newNodes.add(destNode);
                        queue.add(destNode);
                        visited.add(destNode);
                    }
                    
                    if(destNode.equals(toActor)){

                        edgeLayers.add(newEdges);
                        nodeLayers.add(newNodes);
                        ArrayList<String> result = new ArrayList<>();

                        Node indexNode = destNode;

                        for(int j = edgeLayers.size()-1; j != -1; j--){
                            String actor = indexNode.getName();
                            String movieName = indexNode.getLeadingEdge().getMovieName();
                            float rating = indexNode.getLeadingEdge().getWeight();
                            result.add("===[ " + movieName + " (" + rating + ") ===>  " + actor);
                            if (indexNode == indexNode.getLeadingEdge().getn1()){
                                indexNode = indexNode.getLeadingEdge().getn2();
                            }else{
                                indexNode = indexNode.getLeadingEdge().getn1();
                            }
                        }
                        for(int j = result.size()-1; j != -1; j--){
                            System.out.println(result.get(j));
                        }
                        System.out.println("");
                        break outerloop;
                    }
                }
            }
            edgeLayers.add(newEdges);
            nodeLayers.add(newNodes);
        }
    }

    static void oppgave3(Node fromActor, Node toActor, HashMap<String, Node> allNodes){
        System.out.println(fromActor.getName());
        PriorityQueue<Node> pQueue = new PriorityQueue<>();

        for (Node n : allNodes.values()){
            n.setWeight(Float.MAX_VALUE);
        }
        fromActor.setWeight(0);
        pQueue.add(fromActor);
        float currentShortestDistance = toActor.getWeight();

        // Ta ut minste node i kø til tomt
        while(!pQueue.isEmpty()){
            Node currentNode = pQueue.poll();

            // Gå gjennom hver kant i noden til tuppelet
            for(Edge e : currentNode.getEdges()){

                float fromWeight = currentNode.getWeight();
                float weightFunc = 10-e.getWeight();
                // Om vekten på nåværende node og kant er større enn 
                // korteste distanse funnet, bryt og prøv neste iterasjon
                if((fromWeight + weightFunc) > currentShortestDistance){
                    continue;
                }

                Node destNode;
                if(currentNode == e.getn1()){
                    destNode = e.getn2();
                }else{
                    destNode = e.getn1();
                }
            
                // Om vekt på nåværende node og kant er mindre enn vekten til 
                // destinasjonsnode, oppdater vekt på destinasjonsnode
                if((fromWeight + weightFunc) < destNode.getWeight()){
                    destNode.setLeadingEdge(e);
                    destNode.setWeight(fromWeight + weightFunc);
                    pQueue.add(destNode);
                }

                //Om node i tillegg er korrekt node, oppdater korteste distanse
                if(destNode == toActor){
                    currentShortestDistance = destNode.getWeight();
                }
            }
        }

        ArrayList<String> result = new ArrayList<>();
        Node indexNode = toActor;
        
        // Kjør gjennom ledende stier til indexNoden er start-skuespiller
        while(!(indexNode==fromActor)){
            String actor = indexNode.getName();
            String movieName = indexNode.getLeadingEdge().getMovieName();
            float rating = indexNode.getLeadingEdge().getWeight();
            result.add("===[ " + movieName + " (" + rating + ") ===>  " + actor);
            if(indexNode == indexNode.getLeadingEdge().getn1()){
                indexNode = indexNode.getLeadingEdge().getn2();
            }else{
                indexNode = indexNode.getLeadingEdge().getn1();
            }
        }
        for(int j = result.size()-1; j != -1; j--){
            System.out.println(result.get(j));
        }
        System.out.println("Total weight: " + currentShortestDistance + "\n");
    }

    static void oppgave4(HashMap<String, Node> allNodes){
        // Beholder for komponentene vi skal bygge opp
        HashSet<HashSet<Node>> componentBeholder = new HashSet<>();
        HashSet<Edge> allEdges = new HashSet<>();

        for(Node n : allNodes.values()){
            for(Edge e : n.getEdges()){
                allEdges.add(e);
            }
        }
        
        while(!allEdges.isEmpty()){
            Edge currentEdge = null;
            // Hent ut vilkårlig edge fra Hashset
            for(Edge e : allEdges){
                currentEdge = e;
                break;
            }
            allEdges.remove(currentEdge);

            Queue<Node> nodesToCheck = new LinkedList<>();
            HashSet<Node> currentComponent = new HashSet<>();
            currentComponent.add(currentEdge.getn1());
            currentComponent.add(currentEdge.getn2());
            nodesToCheck.add(currentEdge.getn1());
            nodesToCheck.add(currentEdge.getn2());

            while(!nodesToCheck.isEmpty()){
                Node currentNode = nodesToCheck.poll();
                for(Edge e : currentNode.getEdges()){
                    allEdges.remove(e);
                    Node destNode;
                    if(currentNode == e.getn1()){
                        destNode = e.getn2();
                    }else{
                        destNode = e.getn1();
                    }
                    if(!currentComponent.contains(destNode)){
                        currentComponent.add(destNode);
                        nodesToCheck.add(destNode);
                    }
                }
            }
            componentBeholder.add(currentComponent);
        }

        for(HashSet<Node> c : componentBeholder){
            int componentSize = c.size();
        }


        /*HashMap<Integer, HashSet<Node>> componentCounter = new HashMap<>();
        for(HashSet<Node> e : componentBeholder){
            int integer = e.size();
            componentCounter.put(integer, e);
        }
        System.out.println(componentCounter.size());
        System.out.println(componentCounter.get(2).size());*/
        /*for(int i : componentCounter.keySet()){
            System.out.println("There are " + i + " components of size " + i);
        }*/
    }

}