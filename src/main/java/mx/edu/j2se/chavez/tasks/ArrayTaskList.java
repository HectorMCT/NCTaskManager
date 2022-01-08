package mx.edu.j2se.chavez.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * ArrayTaskList is a class that permit store objects from the Task class.
 *
 * An ArrayTaskList object encapsulate a List sorted of Task by its start time
 * in an increasing order.
 *
 * @author      Hector Chavez
 * @version     %I%, %G%
 * @since       1.0
 */
public class ArrayTaskList extends AbstractTaskList {

    /** Dynamic Array which store Task */
    private Task[] listOfTasks;

    /**
     * <p>Constructor for the ArrayTaskList.
     * The dynamic array start which a size of Zero.
     * </p>
     * @since 1.0
     */
    public ArrayTaskList(){
        this.listOfTasks = new Task[0];
        this.sizeList = 0;
    }

    /**
     * <p> Add a new Task object to the array and sort the Array of Task
     * according to its start time in a increasing order.
     *
     * Copy the content from the current array on an auxiliary array and
     * add the new task at the end of the auxiliary array, then replace
     * the current array with the content of the auxiliary array and sort
     * the array using the QuickSort algorithm.
     * </p>
     * @param task - A Task object that will be stored in the current array
     * @throws NullPointerException - If the specified Task is null
     * @since 1.0
     */
    public void add (Task task) throws NullPointerException {
        if (task == null){
            throw new NullPointerException("The specified task is null");
        }
        Task[] auxListOfTasks = new Task[listOfTasks.length + 1];
        System.arraycopy(this.listOfTasks, 0, auxListOfTasks, 0, this.listOfTasks.length);
        auxListOfTasks[auxListOfTasks.length - 1] = task;
        this.listOfTasks = auxListOfTasks;
        sizeList++;
    }

    /**
     * <p> Removes the first occurrence of the specified element from this array, if it is present. </p>
     * @param task - A Task object to be removed from this array, if present
     * @return true if this Task array contained the specified element; false otherwise.
     * @throws NullPointerException - If the specified Task is null
     * @since 1.0
     */
    public boolean remove (Task task) throws NullPointerException {
        if (task == null){
            throw new NullPointerException("The specified task is null");
        }
        int indexOfTask = 0;
        boolean flagTaskExist =  false;
        if (this.listOfTasks.length != 0) {
            for (Task iteratorTask : this.listOfTasks) {
                if (iteratorTask.equals(task)) {
                    flagTaskExist = true;
                    break;
                }
                indexOfTask++;
            }
            if (flagTaskExist) {
                Task[] auxListOfTasks = new Task[(listOfTasks.length - 1)];
                if (indexOfTask == 0) {
                    System.arraycopy(this.listOfTasks, 1, auxListOfTasks, 0, this.listOfTasks.length - 1);
                } else if (indexOfTask == (this.listOfTasks.length - 1)) {
                    System.arraycopy(this.listOfTasks, 0, auxListOfTasks, 0, this.listOfTasks.length - 1);
                } else {
                    System.arraycopy(this.listOfTasks, 0, auxListOfTasks, 0, indexOfTask);
                    System.arraycopy(this.listOfTasks, indexOfTask + 1, auxListOfTasks, indexOfTask, this.listOfTasks.length - (indexOfTask + 1));
                    this.listOfTasks = auxListOfTasks;
                }
                sizeList--;
            }
        }
        return flagTaskExist;
    }

    /**
     * <p> Returns the Task at the specified position in this array. </p>
     * @param index - index of the element to return.
     * @return The Task at the specified position in this array.
     * @since 1.0
     * @throws IndexOutOfBoundsException - If the specified index argument is negative, or if it is greater than or equal to the length of the specified array
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if((index < 0) || (index > this.sizeList - 1)) {
            throw new IndexOutOfBoundsException("The index is negative, it is greater than or equal to the length of the specified array");
        }
        return this.listOfTasks[index];
    }

    /**
     * <p>
     *     Allows iterate over the array
     * </p>
     * @return an iterator object that can be used to iterate over the array.
     * @throws NoSuchElementException whether the iterator has no more items to iterate over
     */
    @NotNull
    @Override
    public Iterator<Task> iterator() throws NoSuchElementException {
        return new Iterator<Task>(){

            /**
             * <p>
             *     Initialize at the beginning of the array for iteration.
             * </p>
             */
            private Integer index = 0;

            /**
             * <p>
             *     Checks if the next element exists.
             * </p>
             */
            @Override
            public boolean hasNext() {
                return index < sizeList;
            }

            /**
             * <p>
             *     Moves the cursor/iterator to next element.
             * </p>
             */
            @Override
            public Task next() {
                if(Objects.equals(index, sizeList)) {
                    throw new NoSuchElementException("Iterator reached last position!");
                }
                return listOfTasks[index++];
            }

            /**
             * <p>
             *     Used to remove an element.
             * </p>
             */
            @Override
            public void remove() {
                Iterator.super.remove();
            }
        };
    }

    /**
     * <p>
     *     Returns a string representation of this ArrayList.
     *     The string representation consists of an Array of Tasks mappings in the order iterated over the array.
     *     Start with the next text "Array: ", next by the size list and continuous with " Tasks \n".
     *     Each task are separated by the "\n", an its text start with its array index plus 1 next to " - "(space, middle dash and space).
     * </p>
     * @return a string representation of this array
     */
    @Override
    public String toString(){
        StringBuilder auxStr = new StringBuilder("Array: "+ this.size() +" Tasks \n");
        for (int index = 0; index < this.sizeList; index++) {
            auxStr.append(index + 1).append(" - ").append(this.listOfTasks[index].toString()).append("\n");
        }
        return auxStr.toString();
    }
}
