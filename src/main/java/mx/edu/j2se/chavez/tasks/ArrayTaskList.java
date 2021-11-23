package mx.edu.j2se.chavez.tasks;

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
public class ArrayTaskList {

    /** Dynamic Array which store Task */
    private Task[] listOfTasks;

    /**
     * <p>Constructor for the ArrayTaskList.
     * The dynamic array start which a size of Zero.
     * </p>
     * @since 1.0
     */
    ArrayTaskList(){
        this.listOfTasks = new Task[0];
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
     * @param task - A Task object that will be stored in the current array.
     * @since 1.0
     */
    public void add (Task task){
        Task[] auxListOfTasks = new Task[listOfTasks.length + 1];
        System.arraycopy(this.listOfTasks, 0, auxListOfTasks, 0, this.listOfTasks.length);
        auxListOfTasks[auxListOfTasks.length - 1] = task;
        this.listOfTasks = auxListOfTasks;
        sortArrayTask(this.listOfTasks, 0, listOfTasks.length - 1);
    }

    /**
     * <p> Removes the first occurrence of the specified element from this array, if it is present. </p>
     * @param task - A Task object to be removed from this array, if present
     * @return true if this Task array contained the specified element; false otherwise.
     * @since 1.0
     */
    public boolean remove (Task task){
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
            }
        }
        return flagTaskExist;
    }

    /**
     * <p> Returns the number of Task in this array. </p>
     * @return The number of Task in this array.
     * @since 1.0
     */
    public int size(){
        return this.listOfTasks.length;
    }

    /**
     * <p> Returns the Task at the specified position in this array. </p>
     * @param index - index of the element to return.
     * @return The Task at the specified position in this Task.
     * @since 1.0
     */
    public Task getTask(int index){
        return ((index < 0) || (index > listOfTasks.length)) ? null : this.listOfTasks[index];
    }

    /**
     * <p> Returns an ArrayTaskList of the specified range of time within this list. </p>
     * @param from - low startTime (inclusive) of the subList
     * @param to - high startTime (exclusive) of the subList
     * @return An ArrayTaskList of the specified range of time within this list.
     * @since 1.0
     */
    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList arrayTaskList = new ArrayTaskList();

        if(this.listOfTasks == null){
            arrayTaskList.listOfTasks = new Task[0];
        } else {
            int nextTimeAuxiliary;

            for (Task taskAuxiliar : this.listOfTasks) {
                nextTimeAuxiliary = taskAuxiliar.nextTimeAfter(from);
                if ((nextTimeAuxiliary >= from) && (nextTimeAuxiliary <= to)) {
                    arrayTaskList.add(taskAuxiliar);
                }
            }
        }
        return arrayTaskList;
    }

    /**
     * <p> Sort this Task array implementing the QuickSort Algorithm. </p>
     * @param listOfTasks - Array of Task to be sorted
     * @param first - the index of the first element of the array.
     * @param last - the index of the last element of the array.
     * @since 1.0
     */
    private void sortArrayTask(Task[] listOfTasks, int first, int last){
        if (first < last) {
            int indexPartition = partition(listOfTasks, first, last);
            sortArrayTask(listOfTasks, first, indexPartition);
            sortArrayTask(listOfTasks, indexPartition + 1, last);
        }
    }

    /**
     * <p> Returns the index in the middle position of the subArray. </p>
     * @param listOfTasks - Array of Task to be sorted
     * @param first - the index of the first element of the subArray.
     * @param last - the index of the last element of the subArray.
     * @since 1.0
     */
    private int partition(Task[] listOfTasks, int first, int last) {
        Task taskPivot = listOfTasks[first];
        while (true){
            while (listOfTasks[first].getStartTime() < taskPivot.getStartTime()){
                first++;
            }
            while (listOfTasks[last].getStartTime() > taskPivot.getStartTime()){
                last--;
            }
            if (first >= last){
                return last;
            } else {
                Task temporalTask = listOfTasks[first];
                listOfTasks[first] = listOfTasks[last];
                listOfTasks[last] = temporalTask;
                first++;
                last--;
            }
        }
    }
}
