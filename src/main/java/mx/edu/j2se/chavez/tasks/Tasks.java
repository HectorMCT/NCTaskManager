package mx.edu.j2se.chavez.tasks;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

        AbstractTaskList subTasks = new ArrayTaskList();

        for (Task task : tasks) {
            if (task.isActive()) {
                if (task.nextTimeAfter(start).compareTo(end) <= 0) {
                    subTasks.add(task);
                }
            }
        }
        return subTasks;

    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();

        for (Task task : incoming(tasks, start, end)) {
            LocalDateTime nextTime = task.nextTimeAfter(start);
            while ( nextTime != null && (nextTime.isBefore(end) || nextTime.isEqual(end))) {
                if (!calendar.containsKey(nextTime)) {
                    Set<Task> timeTasks = new HashSet<>(Collections.singletonList(task));
                    calendar.put(nextTime, timeTasks);

                } else {

                    Set<Task> timeTasks = calendar.get(nextTime);
                    timeTasks.add(task);
                    calendar.put(nextTime, timeTasks);

                    //calendar.get(nextTime).add(task);
                }
                nextTime = task.nextTimeAfter(nextTime);
            }
        }

        return calendar;
    }
}
