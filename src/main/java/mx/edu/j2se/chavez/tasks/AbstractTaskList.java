package mx.edu.j2se.chavez.tasks;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable, Iterable<Task> {

    /** Size of the current list */
    protected Integer sizeList;

    public abstract void add(Task task) throws NullPointerException;

    public abstract boolean remove(Task task) throws NullPointerException;

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;


    /*public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if(from == null){
            throw new IllegalArgumentException("From-time cannot be negative");
        } else if (to == null) {
            throw new IllegalArgumentException("To-time cannot be negative");
        } else if (from.isAfter(to)) {
            throw new IllegalArgumentException("From-time cannot be greater than To-time");
        } else if (from == to) {
            throw new IllegalArgumentException("To-time cannot be equals than From-time. It has to be greater");
        }

        //AbstractTaskList auxiliaryTaskList = getClass().getDeclaredConstructor().newInstance();;
        AbstractTaskList auxiliaryTaskList;

        if(this instanceof ArrayTaskList){
            auxiliaryTaskList = new ArrayTaskList();
        } else {
            auxiliaryTaskList = new LinkedTaskList();
        }

        getStream().filter(Objects::nonNull).filter(task -> {
                        if(task.nextTimeAfter(from)!=null){
                            return Objects.requireNonNull(task.nextTimeAfter(from)).isBefore(to) || Objects.requireNonNull(task.nextTimeAfter(from)).isEqual(to);
                        } else {
                            return false;
                        }
        }).forEach(auxiliaryTaskList::add);
        System.out.println(auxiliaryTaskList);
        return auxiliaryTaskList;
    }*/

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

        for (Task taskIterator : this) {
            if(!taskIterator.equals(auxiliaryIterator.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     *     Creates and returns a copy of this Object.
     * </p>
     * @return A clone of this instance.
     * @throws CloneNotSupportedException - If this object's class does not implement the Cloneable interface.
     * @since 1.0
     */
    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {

        AbstractTaskList auxiliaryTaskList;

        try {
            auxiliaryTaskList = (AbstractTaskList) super.clone();
            /*for(Task task: this){
                auxiliaryTaskList.add(task);
            }*/
            return auxiliaryTaskList;

        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException("Impossible to clone");
        }
    }

    @Override
    public int hashCode(){
        return 1_000_003 * sizeList.hashCode() * getStream().mapToInt(Task::hashCode).sum();
    }

    public Stream<Task> getStream(){
        Stream.Builder<Task> taskBuilder = Stream.builder();
        for (Task task: this){
            taskBuilder.add(task);
        }
        return taskBuilder.build();
    }
}
