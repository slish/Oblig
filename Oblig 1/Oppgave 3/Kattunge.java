import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Kattunge {

    public static void main(String[] args) throws IOException {
        InputStreamReader fileIn = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(fileIn);
        
        // Les første linje for å hente ut katten sin posisjon
        String[] line = br.readLine().split(" ");
        int intToLookFor = Integer.parseInt(line[0]);
        System.out.print(intToLookFor);
        int firstInt = 0;

        // Marker starten av filen for å kunne resete readeren
        br.mark(50);

        while (!(firstInt == -1)){
            line = br.readLine().split(" ");
            firstInt = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++){
                if (intToLookFor == Integer.parseInt(line[i])){
                    intToLookFor = firstInt;
                    System.out.print(" " + intToLookFor);
                    br.reset();
                }
            }
        }
    }
}
