/**
 * Created by minhhien1996 on 7/7/17.
 */
public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap();
        for (int i = 0; i < 10; i++)
            heap.add(i);
        heap.add(100);
        heap.add(-10);
        System.out.println(heap.getString());
    }
}
