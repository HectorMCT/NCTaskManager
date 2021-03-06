package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.ArrayTaskList;
import mx.edu.j2se.chavez.tasks.Task;
import mx.edu.j2se.chavez.tasks.Tasks;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Iterator;

public class ArrayTaskListTest {

    @Test
    public void testTaskList() {

        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        ArrayTaskList tareasSemanales = new ArrayTaskList();

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

        //Se espera que la lista de tareas contenga 13 de ella.
        Assert.assertEquals(31, tareasSemanales.size());
        System.out.println(tareasSemanales);
        //Obtenemos las listas activas dentro del rango de 95 a 185 en tiempo
        //ArrayTaskList nuevasTareasSemanales = (ArrayTaskList) tareasSemanales.incoming(time.plusDays(2), time.plusDays(5));
        //Se espera que se tengan al menos 4 tareas dentro de esta nueva lista.
        //Assert.assertEquals(3, nuevasTareasSemanales.size());

        Iterator<Task> subTareas = Tasks.incoming(tareasSemanales.iterator(), time.plusDays(2),time.plusDays(5));

        System.out.println("----ITERATOR----");
        while (subTareas.hasNext()) {
            System.out.println(subTareas.next());
        }
        System.out.println("----ITERATOR----");

        //Obtenemos la tarea que se encuentra en la posicion 9 de la lista principal
        Task nuevaTarea = tareasSemanales.getTask(5);

        //Imprimimos su nombre
        System.out.println(nuevaTarea.getTitle());

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertTrue(tareasSemanales.remove(nuevaTarea));

        //Corroboramos que el tama??o de la tarea diminuyo en 1.
        //Se espera que la lista de tareas contenga 12 de ella.
        Assert.assertEquals(30, tareasSemanales.size());
    }
}