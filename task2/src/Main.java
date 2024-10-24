import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    private final static int goodRate = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [] userCoordinates = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Coordinates userPosition = new Coordinates(userCoordinates[0], userCoordinates[1]);

        int N = Integer.parseInt(scanner.nextLine().split(" ")[0]);
        int cafesCount = Integer.parseInt(scanner.nextLine().split(" ")[0]);

        PriorityQueue<Cafe> pq = new PriorityQueue<>(new ComparableCafe(userPosition));
        for(int i = 0; i < cafesCount; i++) {
            int[] cafeData = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int curX = cafeData[0], curY = cafeData[1], curRate = cafeData[2];
            if (curRate >= goodRate) {
                var coordinates = new Coordinates(curX, curY);
                pq.add(new Cafe(coordinates, curRate));
            }
        }

        if (pq.isEmpty()) {
            System.out.println("Нет кафе с подходящим рейтингом");
            return;
        }

        int i = 0;
        while(!pq.isEmpty() && i < N) {
            System.out.println(pq.poll().coordinates());
            i++;
        }
    }
}

