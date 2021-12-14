package mx.edu.j2se.chavez.tasks;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractTaskList implements Cloneable, Iterable<Task> {

    /** Size of the current list */
    protected Integer sizeList;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract Task getTask(int index);

    /**
     * <p> Returns an AbstractTaskList of the specified range of time within this list. </p>
     * @param from - low startTime (inclusive) of the subList
     * @param to - high startTime (exclusive) of the subList
     * @return An AbstractTaskList of the specified range of time within this list.
     * @throws IllegalArgumentException - If the from-time or to-time are negative, or from-time are greater or equals to To-time
     * @since 1.0
     */
    public AbstractTaskList incoming(int from, int to)throws IllegalArgumentException {

        if(from < 0){
            throw new IllegalArgumentException("From-time cannot be negative");
        } else if (to < 0) {
            throw new IllegalArgumentException("To-time cannot be negative");
        } else if (from > to) {
            throw new IllegalArgumentException("From-time cannot be greater than To-time");
        } else if (from == to) {
            throw new IllegalArgumentException("To-time cannot be equals than From-time. It has to be greater");
        }

        AbstractTaskList auxiliaryTaskList;

        if(this instanceof ArrayTaskList){
            auxiliaryTaskList = new ArrayTaskList();
        } else {
            auxiliaryTaskList = new LinkedTaskList();
        }

        for (int index = 0; index < this.sizeList; index++){
            Task task = getTask(index);
            if ((task.nextTimeAfter(from) >= from) && (task.nextTimeAfter(from) <= to)) {
                auxiliaryTaskList.add(task);
            }
        }

        return auxiliaryTaskList;
    }

    /**
     * <p> Returns the number of Tasklist </p>
     * @return The number of Taskr.
     * @since 1.0
     */
    public int size(){
        return this.sizeList;
    }


    /**
     * <p>
     *     Indicates whether some other Task is "equal to" this one.
     * </p>
     * @param object - the reference Task with which to compare.
     * @return True if this object is the same as the object argument; false otherwise.
     * @throws NullPointerException - If the specified Task is null
     * @since 1.0
     */
    @Override
    public boolean equals(Object object) throws NullPointerException{

        if(object == null){
            throw new NullPointerException("The specified task is null");
        }

        if (object == this) {
            return true;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        if (!Objects.equals(this.sizeList, ((AbstractTaskList) object).sizeList)) {
            return false;
        }

        Iterator<Task> auxiliaryIterator = ((AbstractTaskList) object).iterator();

        for(Task taskIterator : this) {
            if(!taskIterator.equals(auxiliaryIterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public AbstractTaskList clone() {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
