package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.LinkedTaskList;
import mx.edu.j2se.chavez.tasks.Task;
import mx.edu.j2se.chavez.tasks.Tasks;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Iterator;

public class LinkedTaskListTest{

    @Test
    public void testTaskList() {


        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de 25. Hasta un tiempo maximo de 300
        int auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(1)); startTime = startTime.plusDays(1)) {
            Task auxiliatyTask = new Task("Task " + auxiliary, startTime, startTime.plusDays(5), 24);
            //Si el startTime es par activamos la tarea.
            System.out.println(startTime);
            if ((auxiliary % 2) == 0) {
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        //Se espera que la lista de tareas contenga 13 de ella.
        Assert.assertEquals(31, tareasSemanales.size());
        //tareasSemanales.showTasks();

        Task nuevaTarea = tareasSemanales.getTask(0);
        Task nuevaTarea1 = tareasSemanales.getTask(6);
        Task nuevaTarea2 = tareasSemanales.getTask(tareasSemanales.size() - 1);

        System.out.println("--------");

        //Imprimimos su nombre
        System.out.println(nuevaTarea.getTitle());
        //Imprimimos su nombre
        System.out.println(nuevaTarea1.getTitle());
        //Imprimimos su nombre
        System.out.println(nuevaTarea2.getTitle());

        System.out.println("--------");

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertTrue(tareasSemanales.remove(nuevaTarea));
        //tareasSemanales.showTasks();

        System.out.println("--------");

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertTrue(tareasSemanales.remove(nuevaTarea1));
        //tareasSemanales.showTasks();

        System.out.println("--------");

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertTrue(tareasSemanales.remove(nuevaTarea2));
        //tareasSemanales.showTasks();

        //Corroboramos que el tamaño de la tarea diminuyo en 1.
        //Se espera que la lista de tareas contenga 12 de ella.
        Assert.assertEquals(28, tareasSemanales.size());

        System.out.println("--------");
        //tareasSemanales.showTasks();
        System.out.println("--------");

        nuevaTarea1 = tareasSemanales.getTask(0);
        System.out.println(nuevaTarea1.getTitle());
        nuevaTarea1 = tareasSemanales.getTask(4);
        System.out.println(nuevaTarea1.getTitle());
        nuevaTarea1 = tareasSemanales.getTask(9);
        System.out.println(nuevaTarea1.getTitle());

        nuevaTarea1 = new Task("Task " + 12, LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(15), 24);

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertFalse(tareasSemanales.remove(nuevaTarea1));
        System.out.println("--------");
        //tareasSemanales.showTasks();
    }



    @Test
    public void testTaskSubList() {


        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de 25. Hasta un tiempo maximo de 300

        int auxiliary = 1;
        for (LocalDateTime startTime = time; startTime.isBefore(time.plusMonths(1)); startTime = startTime.plusDays(1)) {
            Task auxiliatyTask = new Task("Task " + auxiliary, startTime, startTime.plusDays(5), 24);
            //Si el startTime es par activamos la tarea.
            System.out.println(startTime);
            if((auxiliary % 2) == 0){
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
            auxiliary++;
        }

        //tareasSemanales.showTasks();

        Iterator<Task> subTareas = Tasks.incoming(tareasSemanales.iterator(), time.plusHours(10),time.plusHours(200));

        System.out.println("--------");
        while (subTareas.hasNext()) {
            System.out.println(subTareas.next());
        }
        //subTareas.showTasks();
    }
}