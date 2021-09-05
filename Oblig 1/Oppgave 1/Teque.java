import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.InvocationTargetException;

/*
Pseudokode 1a)
Jeg velger å implementere en Teque ved å kombinere to Deque. Da vil jeg fort
kunne finne midten ved å enten se etter det siste/først elementet i enten 
den første eller siste dequen. 

Deque<Integer> first = new ArrayDeque<Integer>();
Deque<Integer> last = new ArrayDeque<Integer>();
Input: Int x
1.	Procedure push_front(x)
2.	 | if first.size > last.size do
3.	 | | last.addFirst(first.removeLast())
4.	 | | first.addFirst(x)
5.	 | else
6.	 | | first.addFirst(x)

1.	Procedure push_back(x)
2.	 | if first.size < last.size do
3.	 | | first.addLast(last.removeFirst())
4.	 | | last.addLast(x)
5.	 | else 
6.	 | | last.addLast(x)

1.	Procedure push_middle(x)
2.	 | if first.size < last.size do
3.	 | | first.addLast(last.removeFirst())
4.	 | | last.addFirst(x)
5.	 | else
6.	 | | last.addFirst(x)

1.	Procedure get(x)
2.	 | if x < first.size() do 
3.	 | | Iterator itr = first.iterator
4.	 | | for i <- 0 to x do
5.	 | | | itr.next
6.	 | | print(itr.next)
7.	 | else 
8.	 | | Iterator itr = last.iterator
9.	 | | for I <- 0 to x – first.size do
10.	 | | | itr.next
11.	 | | print(itr.next)
*/

public class Teque<T> {

    // Bygger opp Tequet som to Deques.
    Deque<Integer> first = new ArrayDeque<Integer>();
    Deque<Integer> last = new ArrayDeque<Integer>();

    /* Om første deque er størst, flytt siste integer i første deque til 
       første integer i siste deque før nytt integer settes inn */
    public void push_front(int x){
        if (first.size() > last.size()){
            last.addFirst(first.removeLast());
        }
        first.addFirst(x);
    }

    /* Om første deque er minst, flytt første integer i siste deque til 
       siste integer i første deque før nytt integer settes inn */
    public void push_back(int x){
        if (first.size() < last.size()){
            first.addLast(last.removeFirst());
        }
        last.addLast(x);
    }

    /* Om første deque er minst, flytt første integer i siste deque til 
       siste integer i første deque før nytt integer settes inn */
    public void push_middle(int x){
        if (first.size() < last.size()){
            first.addLast(last.removeFirst());
        }
        last.addFirst(x);
    }

    /* Har brukt en iterator for å kunne kjøre gjennom deque, basert på 
    størrelsen til x. Mistenker at det finnes en bedre måte å gjøre dette
    på, men har ikke kommet frem til noen bedre løsning på det. */
    public void get(int x){
        if (x < first.size()){
            Iterator<Integer> itr = first.iterator();
            for (int i = 0; i < x; i++){
                itr.next();
            }
            /* Måtte bruke print med en newline character, ellers fikk jeg
            en feilmelding på cmp-kommandoen i terminalen. */
            System.out.print(itr.next() + "\n");
        } else{
            x = x - first.size();
            Iterator<Integer> itr = last.iterator();
            for (int i = 0; i < x; i++){
                itr.next();
            }
            System.out.print(itr.next() + "\n");
        }    
    }

    /**
     * Test-kommandoer for å se hvordan arrayene blir bygd opp
     */
    public void printFirst(){
        System.out.println(first);
    }
    public void printLast(){
        System.out.println(last);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Teque<Integer> myTeque = new Teque<Integer>();
        
        for (int i = 0; i < N; i++){
            String[] line = br.readLine().split(" ");
            String cmd = line[0];
            int x = Integer.parseInt(line[1]);
            try{
                Method method = myTeque.getClass().getMethod(cmd, int.class);
                method.invoke(myTeque, x);
            } catch(NoSuchMethodException e){
                System.out.println(e.toString());
            } catch(IllegalAccessException e){
                System.out.println(e.toString());
            } catch(InvocationTargetException e){
                System.out.println(e.toString());
            }
        }
    }
}
