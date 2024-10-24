import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> unique = new HashSet<>();

        for(int i = 0; i < n; i++) {
            int num = scanner.nextInt();

            if (unique.contains(num))
                continue;

            if (pq.size() < 3) {
                pq.add(num);
                unique.add(num);
            } else if (num > pq.peek()){
                unique.remove(pq.poll());
                pq.add(num);
                unique.add(num);
            }
        }

        if (unique.size() < 3) {
            System.out.println("Недостаточно уникальных элементов");
        } else {
            System.out.println(pq.peek());
        }
    }
}