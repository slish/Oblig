import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BalanceTree {
    void balancer(ArrayList<Integer> listToCheck){
        int low = 0;
        int high = listToCheck.size() - 1;
        int mid = (int) Math.ceil((double) (low + high)/2);
        System.out.println(listToCheck.get(mid));
        balancer(listToCheck, mid+1, high);
        balancer(listToCheck, low, mid-1);
    }

    void balancer(ArrayList<Integer> listToCheck, int low, int high){
        if (low>high){
            return;
        }
        int mid = (int) Math.ceil((double) (low + high)/2);
        System.out.println(listToCheck.get(mid));
        balancer(listToCheck, mid+1, high);
        balancer(listToCheck, low, mid-1);
    }

    static public void main(String[] args)throws IOException {
        InputStreamReader seqIn = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(seqIn);
        ArrayList<Integer> sequenceArray = new ArrayList<>();

        for (String line = br.readLine(); line != null; line = br.readLine()){
            sequenceArray.add(Integer.parseInt(line));
        }

        BalanceTree myTree = new BalanceTree();
        myTree.balancer(sequenceArray);
    }
}
