class Quick extends Sorter {

    boolean first = true;

    void sort() {

        Quicksort(0, n-1);
    }

    void Quicksort(int low, int high){
        if(geq(low, high)){
            return;
        }
        int p = Partition(low, high);

        // Test for å se hva første pivot blir
        /*if(first){
            System.out.println("p er " + p);
            System.out.println("tallet er " + A[p]);
            first = false;
        }*/

        Quicksort(low, p-1);
        Quicksort(p+1, high);
    }

    int Partition(int low, int high){
        int temp;
        int p = ChoosePivot(low, high);

        swaps++;
        temp = A[p];
        A[p] = A[high];
        A[high] = temp;        

        int pivot = A[high];
        int left = low;
        int right = high - 1;

        while(leq(left, right)){
            while(leq(left, right) && leq(A[left], pivot)){
                left = left + 1;
            }
            while(geq(right, left) && geq(A[right], pivot)){
                right = right - 1;
            }
            if(lt(left, right)){
                swaps++;
                temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }
        swaps++;
        temp = A[left];
        A[left] = A[high];
        A[high] = temp;
        return left;
    }

    // Velger pivot mellom A[low], A["middle"], A[high]
    int ChoosePivot(int low, int high){
        int first = A[low];
        int middle = A[high-((high-low)/2)];
        int last = A[high];
        // Om midten er mellom første og siste, returner midterste tall
        if((lt(first,middle) && lt(middle,last)) || (lt(last,middle) && lt(middle,first))){
            return high-((high-low)/2);
        // Om første tall er mellom midten og siste tall, returner første tall
        }else if((lt(middle, first) && lt(first, last)) || (lt(last, first) && lt(first, middle))){
            return low;
        // Ellers returner siste tall
        }else{
            return high;
        }
    }

    String algorithmName() {
        return "quick";
    }
}
