package priorityqueue.minheap;

public class Test {
    public static void start() {
        MyHeapPriorityQueue<Employee> queue = new MyHeapPriorityQueue<>(30);
        // show the queue is empty
        System.out.println("isEmpty before insert: " + queue.isEmpty());
        // add data to queue
        create(queue);
        // show the queue is empty
        System.out.println("isEmpty after insert: " + queue.isEmpty());
        // sort the queue
        System.out.println("sort : " + queue.sort());
        // remove one data from queue and show data
        testRemove(queue);
    }

    private static void testRemove(MyHeapPriorityQueue<Employee> queue){
        Employee remove = queue.remove();
        System.out.println("remove:" + remove);
        System.out.println("sort afrer remove : " + queue.sort());
    }

    private static void create(MyHeapPriorityQueue<Employee> queue) {
        Employee employee = new Employee("Lames Butt",30000.00);
        Employee employee2 = new Employee("Josephine Darakjy",4500.00);
        Employee employee3 = new Employee("Art Venere",12000.00);
        Employee employee4 = new Employee("Lenna Paprock",500.00);
        Employee employee5 = new Employee("Donette Foller",30005.00);
        Employee employee6 = new Employee("Simona Morasc",30060.00);
        Employee employee7 = new Employee("Kiley Caldarera",2000.00);

        queue.insert(employee);
        queue.insert(employee2);
        queue.insert(employee3);
        queue.insert(employee4);
        queue.insert(employee5);
        queue.insert(employee6);
        queue.insert(employee7);
    }
}
