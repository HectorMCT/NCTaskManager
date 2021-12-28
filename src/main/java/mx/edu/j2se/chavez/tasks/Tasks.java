package mx.edu.j2se.chavez.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Tasks {
    public static Iterator<Task> incoming(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) {

        Stream.Builder<Task> subTasks = Stream.builder();


        while (tasks.hasNext()) {
            Task task = tasks.next();
            if (task.isActive()) {
                if (task.nextTimeAfter(start).compareTo(end) <= 0) {
                    subTasks.add(task);
                }
            }
        }
        return subTasks.build().iterator();
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end) {

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
