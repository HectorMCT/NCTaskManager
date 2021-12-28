package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.AbstractTaskList;
import mx.edu.j2se.chavez.tasks.ArrayTaskList;
import mx.edu.j2se.chavez.tasks.LinkedTaskList;
import mx.edu.j2se.chavez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class IterableTaskTest {

    @Test
    public void testIterableArray() throws CloneNotSupportedException {


        LocalDateTime time = LocalDateTime.now();
        //Crea una instacia de un ArrayTaskList
        AbstractTaskList tareasSemanales = new ArrayTaskList();

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

            auxiliary++;
        }

        //Metodo toString
        System.out.println("Array");
        System.out.println(tareasSemanales);

        //Corroboramos el buen funcionamiento del metodo clone
        AbstractTaskList auxArray = tareasSemanales.clone();
        Assert.assertEquals(tareasSemanales, auxArray);

        System.out.println(System.identityHashCode(tareasSemanales));
        System.out.println(System.identityHashCode(auxArray));

        System.out.println(tareasSemanales.hashCode());
        System.out.println(auxArray.hashCode());

        for (Task auxTask: tareasSemanales) {
            System.out.println(auxTask);
        }

        //Equals
        System.out.println("Equals Array");
        Assert.assertEquals(tareasSemanales, auxArray);
        System.out.println("______________________________________________________");
    }

    @Test
    public void testIterableList() throws CloneNotSupportedException {


        LocalDateTime time = LocalDateTime.now();

        //Crea una instacia de un ArrayTaskList
        AbstractTaskList tareasSemanales = new LinkedTaskList();

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

        //Metodo toString
        System.out.println("List");
        System.out.println(tareasSemanales);

        //Corroboramos el buen funcionamiento del metodo clone
        AbstractTaskList auxArray = tareasSemanales.clone();
        Assert.assertEquals(tareasSemanales, auxArray);


        System.out.println(System.identityHashCode(tareasSemanales));
        System.out.println(System.identityHashCode(auxArray));

        System.out.println(tareasSemanales.hashCode());
        System.out.println(auxArray.hashCode());

        for (Task auxTask: tareasSemanales) {
            System.out.println(auxTask);
        }

        //Equals
        System.out.println("Equals List");
        Assert.assertEquals(tareasSemanales, auxArray);
        System.out.println("______________________________________________________");
    }
}
