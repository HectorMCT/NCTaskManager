package mx.edu.j2se.chavez.tasks;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedTaskList is a class that permit store objects from the Task class.
 *
 * @author      Hector Chavez
 * @version     %I%, %G%
 * @since       1.0
 */
public class LinkedTaskList extends AbstractTaskList {

    /** Pointer reference to the first task of the list */
    private Node<Task> head;
    /** Pointer reference to the last task of the list */
    private Node<Task> tail;

    /**
     * <p>This is the constructor for the list of tasks.
     * The attribute head and tail set equals to null.
     * The size of the list set to zero.
     * </p>
     * @since 1.0
     */
    public LinkedTaskList(){
        this.head = null;
        this.tail = null;
        this.sizeList = 0;
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

        Node<Task> newTaskListed = new Node<>(task);
        newTaskListed.setNext(null);

        if(this.head == null){
            this.head = newTaskListed;
        } else {
            this.tail.setNext(newTaskListed);
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

        Node<Task> currentTask = this.head;
        Node<Task> previousTask;

        if (currentTask.taskListed.equals(task)) {
            this.head = this.head.getNext();
            this.sizeList--;
            return true;
        } else {
            do {
                previousTask = currentTask;
                currentTask = currentTask.getNext();
                if (currentTask.taskListed.equals(task)) {
                    if (currentTask == this.tail) {
                        this.tail = previousTask;
                        previousTask.setNext(null);
                    } else {
                        previousTask.setNext(currentTask.getNext());
                        currentTask.setNext(null);
                    }
                    this.sizeList--;
                    return true;
                }
            } while (currentTask.getNext() != null);
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

        if ((index < 0) || (index > this.sizeList - 1)) {
            throw new IndexOutOfBoundsException("Index exceeds the permissible limits for the list.");
        } else {
            Node<Task> auxiliary = this.head;
            for (int indexAux = 0; indexAux < index; indexAux++) {
                auxiliary = auxiliary.getNext();
            }
            return auxiliary.getTaskListed();
        }
    }

    /**
     * <p>
     *     Allows iterate over the list.
     * </p>
     * @return an iterator object that can be used to iterate over the list.
     * @throws NoSuchElementException whether the iterator has no more items to iterate over
     */
    @NotNull
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>(){

            /**
             * <p>
             *     initialize pointer to head of the list for iteration.
             * </p>
             */
            private Node<Task> currentNode = head;


            /**
             * <p>
             *     Returns false if next element does not exist.
             * </p>
             * @return false if the next element does not exist, true otherwise.
             */
            @Override
            public boolean hasNext(){
                return currentNode != null;
            }

            /**
             * <p>
             *     Moves the cursor/iterator to next element.
             * </p>
             */
            @Override
            public Task next(){

                Task listTask = currentNode.getTaskListed();
                currentNode = currentNode.getNext();

                return listTask;
            }

            /**
             * <p>
             *     Used to remove an element.
             * </p>
             * @throws UnsupportedOperationException - if it is used.
             */
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * <p>
     *     Returns a string representation of this LinkedList.
     *     The string representation consists of a list of Tasks mappings in the order returned by the list's iterator.
     *     Start with the next text "Linked List: ", next by the size list and continuous with " Tasks \n".
     *     Each task are separated by the "\n".
     * </p>
     * @return a string representation of this list
     */
    @Override
    public String toString(){
        StringBuilder auxStr = new StringBuilder("Linked List: "+ this.size() +" Tasks \n");
        for (Task auxTask : this) {
            auxStr.append(auxTask.toString()).append("\n");
        }
        return auxStr.toString();
    }

    /**
     * <p>
     *     Node is a class that encapsulate a Task class and a reference to the next class.
     * <p/>
     * @author      Hector Chavez
     * @version     %I%, %G%
     * @since       1.0
     */
    static class Node<Task> implements Serializable {

        /**
         * <p>
         *     Task that is be stored.
         * <p/>
         */
        private final Task taskListed;
        /**
         * <p>
         *     Reference to the next task.
         * <p/>
         */
        private Node<Task> next;

        /**
         * <p>
         *     This is the constructor for the node of the list.
         *     The attribute next set equals to null.
         *     The task is stored.
         * </p>
         * @since 1.0
         */
        Node(Task task){
            this.taskListed = task;
            this.next = null;
        }

        /**
         * <p>
         *     Return the task inside the node.
         * </p>
         * @return The task stored in the node.
         */
        public Task getTaskListed() {
            return taskListed;
        }

        /**
         * <p>
         *     Return reference to the next node in the list.
         * </p>
         * @return A reference to the next node.
         */
        public Node<Task> getNext() {
            return next;
        }

        /**
         * <p>
         *     Update the reference to the next node.
         * </p>
         * @param next Reference to the next node
         */
        public void setNext(Node<Task> next) {
            this.next = next;
        }
    }
}
