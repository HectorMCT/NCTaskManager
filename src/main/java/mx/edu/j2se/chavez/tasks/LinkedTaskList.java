package mx.edu.j2se.chavez.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

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

    @NotNull
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>(){
            private Node<Task> currentNode = head;

            @Override
            public boolean hasNext(){
                return currentNode != null;
            }

            @Override
            public Task next(){

                Task listTask = currentNode.getTaskListed();
                currentNode = currentNode.getNext();

                return listTask;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return super.spliterator();
    }

    @Override
    public String toString(){
        StringBuilder auxStr = new StringBuilder("Linked List: "+ this.size() +" Tasks \n");
        for (Task auxTask : this) {
            auxStr.append(auxTask.toString()).append("\n");
        }
        return auxStr.toString();
    }

    static class Node<Task>{

        private Task taskListed;
        private Node<Task> next;

        Node(Task task){
            this.taskListed = task;
            this.next = null;
        }

        public Task getTaskListed() {
            return taskListed;
        }

        public void setTaskListed(Task taskListed) {
            this.taskListed = taskListed;
        }

        public Node<Task> getNext() {
            return next;
        }

        public void setNext(Node<Task> next) {
            this.next = next;
        }
    }
}
