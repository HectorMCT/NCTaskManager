package mx.edu.j2se.chavez.tasks;

import java.time.LocalDateTime;
import java.util.Objects;


public class Task implements Cloneable {

    /** The title of task */
    private String title;
    /** The time when the task it is start. */
    private LocalDateTime start;
    /** The time when the task it is ended. */
    private LocalDateTime end;
    /** The interval time when the task it is going to be executed. */
    private Integer interval;
    /** The state of the task that indicate if it is active or not
     * At the moment of create a new object it set false which mean
     * it is an inactive task. */
    private Boolean state;
    /** Indicates if the task is repetitive (true) or it is a normal task (false).*/
    private Boolean repetitive;

    /**
     * <p>This is the constructor for the repetitive task.
     * The attribute of end time it set equals to the start time.
     * The interval of time it set to zero.
     * The task it is create as inactive, state it set to false.
     * </p>
     * @param title The name of the task.
     * @param time The time when the task it is going to happen.
     * @throws IllegalArgumentException - If the start time it is negative or if the title is an empty String or null value.
     * @since 1.0
     */
    public Task(final String title, final LocalDateTime time) throws IllegalArgumentException {
        if(time == null){
            throw new IllegalArgumentException("You need a Start Time.");
        }
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Task cannot take in an empty String or null value for the \"title\" constructor");
        }
        this.title = title;
        this.start = time;
        this.end = time;
        this.interval = 0;
        this.state = false;
        this.repetitive = false;
    }

    /**
     * <p>This is the constructor for the repeating task.
     * Use the normal task constructor to set all attributes.
     * </p>
     * @param title The name of the task.
     * @param start The time when the task it is going to start.
     * @param end The time when the task end.
     * @param interval The interval of time that indicate when the task happened.
     * @throws IllegalArgumentException - If start time, end time or interval of time are negative,
     * or if start time is greater or equals to end time. Also, if the title is an empty String or null value.
     * @since 1.0
     */
    public Task(final String title, final LocalDateTime start, final LocalDateTime end, final Integer interval) throws IllegalArgumentException {
        this(title, start);
        if (end == null) {
            throw new IllegalArgumentException("You need a End Time.");
        } else if (interval < 0L) {
            throw new IllegalArgumentException("Interval time cannot be negative");
        } else if (end == start) {
            throw new IllegalArgumentException("End time cannot be equals than Start time. It has to be greater");
        } else if (start.isAfter(end)) {
            throw new IllegalArgumentException("End time cannot be less than Start time. It has to be greater");
        }
        this.end = end;
        this.interval = interval;
        this.repetitive = true;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * <p>
     *     Set the new time the task it is going to be executed. If the task is a
     *     repetitive one, the task become non-repetitive. The if this happened the
     *     start time and end time set with the same value and the interval set
     *     the value of zero.
     * </p>
     * @param title - Name to assign to the current Task.
     * @throws IllegalArgumentException - If the title is an empty String or null value
     * @since 1.0
     */
    public void setTitle(final String title) throws IllegalArgumentException {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Task cannot take in an empty String or null value for the \"title\"");
        }
        this.title = title;
    }

    public LocalDateTime getTime() {
        return this.start;
    }

    public LocalDateTime getStartTime(){
        return this.start;
    }

    public LocalDateTime getEndTime() {
        return this.end;
    }

    /**
     * <p>
     *     Set the new time the task it is going to be executed. If the task is a
     *     repetitive one, the task become non-repetitive. The if this happened the
     *     start time and end time set with the same value and the interval set
     *     the value of zero.
     * </p>
     * @return The interval of time the task it is been executed.
     * @since 1.0
     */
    public Integer getRepeatInterval() {
        return (this.repetitive) ? this.interval : 0;
    }

    /**
     * <p>
     *     Set the new time the task it is going to be executed. If the task is a
     *     repetitive one, the task become non-repetitive. The if this happened the
     *     start time and end time set with the same value and the interval set
     *     the value of zero.
     * </p>
     * @param time The new time when the task it is going to be executed.
     * @throws IllegalArgumentException - If time it is negative.
     * @since 1.0
     */
    public void setTime(final LocalDateTime time) throws IllegalArgumentException {
        if (start == null) {
            throw new IllegalArgumentException("You need a Start Time.");
        } else if (time.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Your Time cannot be negative.");
        }
        this.repetitive = false;
        this.start = time;
        this.end = time;
        this.interval = 0;
    }

    /**
     * <p>
     *     Set the new time the task it is going to be executed. If the task is a
     *     non-repetitive one, the task become repetitive.
     * </p>
     * @param start The new start time when the task it is started.
     * @param end The new end time when the task it is ended.
     * @param interval The new interval of time when the task it is going to be executed.
     * @throws IllegalArgumentException - If start time, end time or interval of time are negative, or if start time is greater or equals to end time.
     * @since 1.0
     */
    public void setTime(final LocalDateTime start, final LocalDateTime end, final Integer interval) throws IllegalArgumentException {
        if(start == null){
            throw new IllegalArgumentException("You need a Start Time.");
        } else if (end == null) {
            throw new IllegalArgumentException("You need a End Time.");
        } else if (interval < 0) {
            throw new IllegalArgumentException("Interval time cannot be negative");
        } else if (end == start) {
            throw new IllegalArgumentException("End time cannot be equals than Start time. It has to be greater");
        } else if (start.isAfter(end)) {
            throw new IllegalArgumentException("End time cannot be less than Start time. It has to be greater");
        }
        this.repetitive = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public void setActive (final boolean active) {
        this.state = active;
    }

    public boolean isActive () {
        return this.state;
    }

    public boolean isRepeated () {
        return this.repetitive;
    }

    /**
     * <p>
     *     Obtains the next start time that the task it is going to be executed related to the current time.
     * </p>
     * @param current The current time.
     * @return The next start time of the task execution after the current time.
     * If the current time it is greater than the end time returns -1.
     * If the current time it is smaller than the start time returns the start time.
     * @throws IllegalArgumentException - If the current time it is negative.
     * @since 1.0
     */

    public LocalDateTime nextTimeAfter (LocalDateTime current) throws IllegalArgumentException {
        if(current == null){
            throw new IllegalArgumentException("You need to introduce the Current time");
        }
        if ((this.isRepeated())) {
            if (this.isActive()) {
                LocalDateTime nextTime = this.start;
                if (current.isBefore(this.start)) {
                    return this.start;
                } else if (current.isAfter(this.end)) {
                    return null;
                } else {
                    while (nextTime.isBefore(end) || nextTime.isEqual(end)) {
                        if (nextTime.isAfter(current)) {
                            return nextTime;
                        }
                        nextTime =  nextTime.plusHours(this.interval);
                    }
                }
            }
        } else {
            if(this.isActive()){
                if (current.isBefore(this.start)) {
                    return this.start;
                }
            }
        }
        return null;
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

        if (!(object instanceof Task)) {
            return false;
        }

        Task taskAuxiliary = (Task) object;

        return (Objects.equals(taskAuxiliary.title, this.title)) &&
                (Objects.equals(taskAuxiliary.start, this.start)) &&
                (Objects.equals(taskAuxiliary.end, this.end)) &&
                (Objects.equals(taskAuxiliary.interval, this.interval)) &&
                (taskAuxiliary.state == this.state) &&
                (taskAuxiliary.repetitive == this.repetitive);

    }

    /**
     * <p>
     *     Returns a hash code value for the object.
     * </p>
     * @return A hash code value for this object.
     * @since 1.0
     */
    @Override
    public int hashCode(){
        return 1_000_003 * this.title.hashCode() *
                this.start.hashCode() *
                this.end.hashCode() *
                this.interval.hashCode() *
                this.state.hashCode() *
                this.repetitive.hashCode();
    }

    /**
     * <p>
     *     Returns a string representation of the Task.
     * </p>
     * @return A string representation of the Task.
     * @since 1.0
     */
    @Override
    public String toString(){
        return this.title + ", Starts: " + this.start.toString() + ", Ends: " + this.end.toString() + ", Each: " + this.interval.toString() + ", Active: " + this.state.toString() + ", Repetitive: " + this.repetitive.toString() ;
    }

    /**
     * <p>
     *     Creates and returns a copy of this Task.
     * </p>
     * @return A clone of this instance.
     * @throws CloneNotSupportedException - If this object's class does not implement the Cloneable interface.
     * @since 1.0
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        Task clone;
        try {
            clone = (Task) super.clone();
            clone.repetitive = this.repetitive;
            clone.state = this.state;
            clone.end = this.end;
            clone.interval = this.interval;
            clone.title = this.title;
            clone.start = this.start;
            return clone;
        } catch (CloneNotSupportedException exception) {
            throw new CloneNotSupportedException("Can not be possible make a copy.");
        }
    }
}
