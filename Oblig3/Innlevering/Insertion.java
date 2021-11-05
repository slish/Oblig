class Insertion extends Sorter {

    void sort() {
        int j;
        for(int i = 1; lt(i,n); i++){
            j = i;
            while(gt(j,0) && gt(A[j-1], A[j])){
                swap(j-1,j);
                j = j-1;
            }
        }
    }

    String algorithmName() {
        return "insertion";
    }
}
