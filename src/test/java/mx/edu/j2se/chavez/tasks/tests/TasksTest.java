package mx.edu.j2se.chavez.tasks.tests;

import junit.framework.TestCase;
import mx.edu.j2se.chavez.tasks.LinkedTaskList;
import mx.edu.j2se.chavez.tasks.Task;
import mx.edu.j2se.chavez.tasks.Tasks;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class TasksTest {

    @Test
    public void testTasksIterableIncoming() {
        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de un dia. Hasta un tiempo maximo de un mes al tiempo inicial

        int auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(1)); startTime = startTime.plusDays(7)) {
            Task auxiliatyTask = new Task("Daily Spring " + auxiliary, startTime, startTime.plusDays(5), 24);
            //Si el startTime es par activamos la tarea.
            if((auxiliary % 2) == 0){
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        Iterator<Task> subTareas = Tasks.incoming(tareasSemanales.iterator(), time.plusHours(10),time.plusHours(200));

        System.out.println("--------");
        while (subTareas.hasNext()) {
            System.out.println(subTareas.next());
        }
    }

    @Test
    public void testTasksCalendar() {
        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de un dia. Hasta un tiempo maximo de un mes al tiempo inicial

        int auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(4)); startTime = startTime.plusDays(7)) {
            Task auxiliatyTask = new Task("Running " + auxiliary, startTime, startTime.plusDays(5), 24);
            //Si el startTime es par activamos la tarea.
            if ((auxiliary % 2) == 0) {
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(4)); startTime = startTime.plusDays(7)) {
            Task auxiliatyTask = new Task("Medication " + auxiliary, startTime, startTime.plusDays(5), 24);
            //Si el startTime es par activamos la tarea.
            if ((auxiliary % 2) == 0) {
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(4)); startTime = startTime.plusDays(7)) {
            Task auxiliatyTask = new Task("Meeting " + auxiliary, startTime, startTime.plusHours(2), 24);
            //Si el startTime es par activamos la tarea.
            auxiliatyTask.setActive(true);
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(tareasSemanales.iterator(), time.plusDays(25), time.plusDays(85));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        calendar.forEach((localDateTime, tasks) -> {
            System.out.println("------------------------------------------------------------------------------");
            for (Task task : tasks) {
                System.out.println(localDateTime.format(formatter) + " - " + task);
            }
        });

    }
}