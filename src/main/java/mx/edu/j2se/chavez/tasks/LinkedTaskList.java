package mx.edu.j2se.chavez.tasks;

public class LinkedTaskList {

    /** Pointer reference to the first task of the list */
    private Node head;
    /** Pointer reference to the last task of the list */
    private Node tail;
    /** Size of the current list */
    private Integer sizeList;

    /**
     * <p>This is the constructor for the list of tasks.
     * The attribute head and tail set equals to null.
     * The size of the list set to zero.
     * </p>
     * @since 1.0
     */
    LinkedTaskList(){
        this.head = null;
        this.tail = null;
        this.sizeList = 0;
    }

    /**
     * <p> Returns the number of Task in this list. </p>
     * @return The number of Task in this list.
     * @since 1.0
     */
    public int size(){
        return this.sizeList;
    }

    /**
     * <p>
     *     Adds a new task to the current list.
     * </p>
     * @param task - A Task object that will be stored at the end of the list.
     * @throws NullPointerException - If the specified Task is null
     * @since 1.0
     */
    public void add (Task task) throws NullPointerException {
        if (task == null){
            throw new NullPointerException("The specified task is null");
        }

        Node newTaskListed =  new Node(task);
        newTaskListed.next = null;

        if(this.head == null){
            this.head = newTaskListed;
        } else {
            this.tail.next = newTaskListed;
        }
        this.tail = newTaskListed;
        this.sizeList++;
    }

    /**
     * <p> Removes the first occurrence of the specified element from this list, if it is present. </p>
     * @param task - A Task object to be removed from this list, if it is present
     * @return true if this list of tasks contained the specified element; false otherwise.
     * @throws NullPointerException - If the specified Task is null
     * @since 1.0
     */
    public boolean remove (Task task){

        Node currentTask = this.head;
        Node previousTask;

        if (currentTask.taskListed.equals(task)) {
            this.head = this.head.next;
            this.sizeList--;
            return true;
        } else {
            do {
                previousTask = currentTask;
                currentTask = currentTask.next;
                if (currentTask.taskListed.equals(task)) {
                    if (currentTask == this.tail) {
                        this.tail = previousTask;
                        previousTask.next = null;
                    } else {
                        previousTask.next = currentTask.next;
                        currentTask.next = null;
                    }
                    this.sizeList--;
                    return true;
                }
            } while (currentTask.next != null);
            return false;
        }
    }

    /**
     * <p> Returns the Task at the specified position in this list. </p>
     * @param index - index of the element to return.
     * @return The Task at the specified position in this list.
     * @since 1.0
     * @throws IndexOutOfBoundsException - If the specified index argument is negative, or if it is greater than or equal to the length of the specified array
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {

        if ((index >= 0) && (index <= this.sizeList)) {
            Node auxiliary = this.head;
            for (int indexAux = 0; indexAux < index; indexAux++) {
                auxiliary = auxiliary.next;
            }
            return auxiliary.taskListed;
        } else {
            throw new IndexOutOfBoundsException("Index exceeds the permissible limits for the list.");
        }
    }

    /**
     * <p> Returns an LinkedTaskList of the specified range of time within this list. </p>
     * @param from - low startTime (inclusive) of the subList
     * @param to - high startTime (exclusive) of the subList
     * @return An LinkedTaskList of the specified range of time within this list.
     * @throws IllegalArgumentException - If the from-time or to-time are negative, or from-time are greater or equals to To-time
     * @since 1.0
     */
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {

        if (from < 0) {
            throw new IllegalArgumentException("From-time cannot be negative");
        } else if (to < 0) {
            throw new IllegalArgumentException("To-time cannot be negative");
        } else if (from > to) {
            throw new IllegalArgumentException("From-time cannot be greater than To-time");
        } else if (from == to) {
            throw new IllegalArgumentException("To-time cannot be equals than From-time. It has to be greater");
        }

        LinkedTaskList auxiliaryList = new LinkedTaskList();

        if (this.sizeList != 0) {
            int nextTimeAuxiliary;
            Node current = head;
            //Node previous =  null;

            while (current.next != null) {
                nextTimeAuxiliary = current.taskListed.nextTimeAfter(from);
                if ((nextTimeAuxiliary >= from) && (nextTimeAuxiliary <= to)) {
                    auxiliaryList.add(current.taskListed);
                }
                current = current.next;
            }
        }
        return auxiliaryList;
    }

    static class Node{

        Task taskListed;
        Node next;

        Node(Task task){
            this.taskListed = task;
            next = null;
        }
    }


    public void showTasks(){
        Node auxiliary = this.head;

        for(int index = 0; index<this.sizeList; index++){
            System.out.println(auxiliary.taskListed.getTitle());
            auxiliary = auxiliary.next;
        }
    }
}
