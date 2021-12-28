package mx.edu.j2se.chavez.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Tasks {

    /**
     * <p> Returns an Iterator <\Task\> of the specified range of time within this list. </p>
     * @param start - low startTime (inclusive) of the subList
     * @param end - high startTime (exclusive) of the subList
     * @return An Iterator of Task of the specified range of time within this list.
     * @throws IllegalArgumentException - If the start time or end time negative are null, or start time are greater or equals to end time
     * @since 1.0
     */
    public static Iterator<Task> incoming(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {

        if(start == null){
            throw new IllegalArgumentException("You need a start time");
        } else if (end == null) {
            throw new IllegalArgumentException("You need an end time");
        } else if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time cannot be greater than End time");
        } else if (start == end) {
            throw new IllegalArgumentException("End time cannot be equals than Start time. It has to be greater");
        }

        Stream.Builder<Task> subTasks = Stream.builder();

        while (tasks.hasNext()) {
            subTasks.add(tasks.next());
        }

        return subTasks.build().filter(Objects::nonNull).filter(task -> {
            if(task.nextTimeAfter(start)!=null){
                return Objects.requireNonNull(task.nextTimeAfter(start)).isBefore(end) || Objects.requireNonNull(task.nextTimeAfter(start)).isEqual(end);
            } else {
                return false;
            }
        }).iterator();
    }

    /**
     * <p> Returns a SortedMap, composed by the star time of the tasks and a Set of Tasks of the specified range of time within this list. </p>
     * @param start - low startTime (inclusive) of the subList
     * @param end - high startTime (exclusive) of the subList
     * @return A SortedMap of Task of the specified range of time within this list.
     * @throws IllegalArgumentException - If the start time or end time negative are null, or start time are greater or equals to end time
     * @since 1.0
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {

        if(start == null){
            throw new IllegalArgumentException("Start time cannot be negative");
        } else if (end == null) {
            throw new IllegalArgumentException("End time cannot be negative");
        } else if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time cannot be greater than End time");
        } else if (start == end) {
            throw new IllegalArgumentException("End time cannot be equals than Start-time. It has to be greater");
        }

        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();

        Iterator<Task> it = incoming(tasks, start, end);

        while (it.hasNext()) {
            Task task = it.next();
            LocalDateTime nextTime = task.nextTimeAfter(start);
            while ( nextTime != null && (nextTime.isBefore(end) || nextTime.isEqual(end))) {
                Set<Task> timeTasks;
                if (!calendar.containsKey(nextTime)) {
                    timeTasks = new HashSet<>(Collections.singletonList(task));

                } else {
                    timeTasks = calendar.get(nextTime);
                    timeTasks.add(task);
                    //calendar.get(nextTime).add(task);
                }
                calendar.put(nextTime, timeTasks);
                nextTime = task.nextTimeAfter(nextTime);
            }
        }

        return calendar;
    }
}
