package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.AbstractTaskList;
import mx.edu.j2se.chavez.tasks.ArrayTaskList;
import mx.edu.j2se.chavez.tasks.LinkedTaskList;
import mx.edu.j2se.chavez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class IterableTaskTest {

    @Test
    public void testIterableArray() throws CloneNotSupportedException {
        //Crea una instacia de un ArrayTaskList
        AbstractTaskList tareasSemanales = new ArrayTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de 25. Hasta un tiempo maximo de 300
        for(int startTime = 0; startTime <= 300; startTime += 25 ){
            Task auxiliatyTask = new Task("Task " + (startTime + 25)/25 ,startTime, startTime + 96, 24);
            //Si el startTime es par activamos la tarea.
            if((startTime % 2) == 0){
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
        }

        //Metodo toString
        System.out.println("Array");
        System.out.println(tareasSemanales);

        //Corroboramos el buen funcionamiento del metodo clone
        AbstractTaskList auxArray = tareasSemanales.clone();
        Assert.assertEquals(tareasSemanales, auxArray);

        for (Task auxTask: tareasSemanales) {
            System.out.println(auxTask);
        }

        //Equals
        System.out.println("Equals Array");
        Assert.assertTrue(tareasSemanales.equals(auxArray));
        System.out.println("______________________________________________________");
    }

    @Test
    public void testIterableList() throws CloneNotSupportedException {
        //Crea una instacia de un ArrayTaskList
        AbstractTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de 25. Hasta un tiempo maximo de 300
        for(int startTime = 0; startTime <= 300; startTime += 25 ){
            Task auxiliatyTask = new Task("Task " + (startTime + 25)/25 ,startTime, startTime + 96, 24);
            //Si el startTime es par activamos la tarea.
            if((startTime % 2) == 0){
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
        }

        //Metodo toString
        System.out.println("List");
        System.out.println(tareasSemanales);

        //Corroboramos el buen funcionamiento del metodo clone
        AbstractTaskList auxArray = tareasSemanales.clone();
        Assert.assertEquals(tareasSemanales, auxArray);

        for (Task auxTask: tareasSemanales) {
            System.out.println(auxTask);
        }

        //Equals
        System.out.println("Equals List");
        Assert.assertTrue(tareasSemanales.equals(auxArray));
        System.out.println("______________________________________________________");
    }
}
