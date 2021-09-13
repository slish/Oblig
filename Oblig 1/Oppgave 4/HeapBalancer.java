import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class HeapBalancer<E> extends PriorityQueue<E> {

    public void sortHeap(){
        // Om vi kommer til siste element, skriv det ut
        if (this.size() == 1){
            System.out.println(this.peek());
        // Del ellers opp listen i to og kjør rekursivt kall
        }else{
            int halfSize = this.size()/2;
        
            HeapBalancer<Integer> pqHalf = new HeapBalancer<>();
            for (int i = 0; i < halfSize; i++){
                pqHalf.offer((int) this.poll());
            }
            System.out.println(this.poll());
            // Om det er flere elementer igjen i heapet, kjør rekursivt kall
            if (this.size() > 0){
                this.sortHeap();
            }
            if (pqHalf.size() > 0){
                pqHalf.sortHeap();
            }
        }
    }

    static public void main(String[] args)throws IOException{
        InputStreamReader seqIn = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(seqIn);

        HeapBalancer<Integer> pq = new HeapBalancer<>();

        for (String line = br.readLine(); line!= null; line = br.readLine()){
            pq.offer(Integer.parseInt(line));
        }
        pq.sortHeap();
    }
}
