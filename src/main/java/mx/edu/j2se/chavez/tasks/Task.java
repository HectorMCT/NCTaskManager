package mx.edu.j2se.chavez.tasks;

/**
 * Task is a base class for all basic tasks which allow to the
 * user create simple tasks according his necessities.
 *
 * A Task object encapsulate the state information related to when
 * the task it is going to occur, and it states.
 * This state information includes:
 *
 * <ul>
 * <li>The title of the task
 * <li>The time of starting.
 * <li>The time of finishing.
 * <li>The interval of time when the task it is going to occur.
 * <li>The current state that indicate if it is an active task or not.
 * <li>The current state that indicate if the task it is repetitive
 * </ul>
 * <p>
 * Tasks can be of two types:
 * <ul>
 *     <li>Normal Task
 *     <li>Repetitive Task
 * </ul>
 * The normal task can only happen once.
 * Meanwhile, Repetitive Task can happen many times according to it is
 * interval of time, and it is time of finish.
 * At the moment of create each new object Task it is created as an
 * inactive task.
 *
 * @author      Hector Chavez
 * @version     %I%, %G%
 * @since       1.0
 */
public class Task {

    /** The title of task */
    private String title;
    /** The time when the task it is start. */
    private int start;
    /** The time when the task it is ended. */
    private int end;
    /** The interval time when the task it is going to be executed. */
    private int interval;
    /** The state of the task that indicate if it is active or not
     * At the moment of create a new object it set false which mean
     * it is an inactive task. */
    private boolean state;
    /** Indicates if the task is repetitive (true) or it is a normal task (false).*/
    private boolean repetitive;

    /**
     * <p>This is the constructor for the repetitive task.
     * The attribute of end time it set equals to the start time.
     * The interval of time it set to zero.
     * The task it is create as inactive, state it set to false.
     * </p>
     * @param title The name of the task.
     * @param time The time when the task it is going to happen.
     * @since 1.0
     */
    Task(String title, int time){
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
     * @since 1.0
     */
    Task(String title, int start, int end, int interval){
        this(title, start);
        this.end = end;
        this.interval = interval;
        this.repetitive = true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return this.start;
    }

    public int getStartTime(){
        return this.start;
    }

    public int getEndTime() {
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
    public int getRepeatInterval() {
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
     * @since 1.0
     */
    public void setTime(int time) {
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
     * @since 1.0
     */
    public void setTime(int start, int end, int interval){
        this.repetitive = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public void setActive (boolean active) {
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
     * @since 1.0
     */

    public int nextTimeAfter (int current){
        if ((current >= this.end) || (!this.isActive())) {
            return -1;
        } else if (current < this.start) {
            return this.start;
        } else {
            for (int index = this.start; index <= this.end; index += this.interval){
                if (index > current) {
                    return index;
                }
            }
        }
        return -1;
    }
}
