// Implementerer heapsort fra forelesning 
// O(n log(n))

public class Heapsort extends Sorter{
    void sort(){
        buildMaxHeap(n);
        for(int i = n-1; geq(i, 0); i--){
            swap(0,i);
            bubbleDown(0, i);
        }
    }

    String algorithmName(){
        return "heapsort";
    }

    void bubbleDown(int i, int n){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i +2;

        if (lt(left, n) && lt(A[largest], A[left])){
            swaps++;
            int temp = largest;
            largest = left;
            left = temp;
        }

        if (lt(right, n) && lt(A[largest], A[right])){
            swaps++;
            int temp = largest;
            largest = right;
            right = temp;
        }

        if (!eq(i, largest)){
            swaps++;
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            bubbleDown(largest, n);
        }
    }

    void buildMaxHeap(int n){
        for (int i = n/2; geq(i, 0); i--){
            bubbleDown(i, n);
        }
    }
}
