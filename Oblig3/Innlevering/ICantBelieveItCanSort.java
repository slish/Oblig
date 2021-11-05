// Har implementert sorteringsmetoden som ble nevnt i forelesning,
// beskrevet her: https://arxiv.org/pdf/2110.01111v1.pdf
// O(n^2)

public class ICantBelieveItCanSort extends Sorter {
    void sort(){
        for(int i = 0; lt(i,n); i++){
            for(int j = 0; lt(j,n); j++){
                if(lt(A[i],A[j])){
                    swaps++;
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
    }

    String algorithmName(){
        return "icantbelieveitcansort";
    }
}
