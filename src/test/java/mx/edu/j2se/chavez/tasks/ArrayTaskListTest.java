package mx.edu.j2se.chavez.tasks;

import org.junit.Assert;
import org.junit.Test;

public class ArrayTaskListTest {

    @Test
    public void testTaskList(){

        //Crea una instacia de un ArrayTaskList
        ArrayTaskList tareasSemanales = new ArrayTaskList();

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

        //Se espera que la lista de tareas contenga 13 de ella.
        Assert.assertEquals(13, tareasSemanales.size());
        //Obtenemos las listas activas dentro del rango de 95 a 185 en tiempo
        ArrayTaskList nuevasTareasSemanales = tareasSemanales.incoming(95, 185);
        //Se espera que se tengan al menos 4 tareas dentro de esta nueva lista.
        Assert.assertEquals(4, nuevasTareasSemanales.size());

        //Obtenemos la tarea que se encuentra en la posicion 9 de la lista principal
        Task nuevaTarea = tareasSemanales.getTask(5);

        //Imprimimos su nombre
        System.out.println(nuevaTarea.getTitle());

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertTrue(tareasSemanales.remove(nuevaTarea));

        //Corroboramos que el tamaño de la tarea diminuyo en 1.
        //Se espera que la lista de tareas contenga 12 de ella.
        Assert.assertEquals(12, tareasSemanales.size());
    }
}