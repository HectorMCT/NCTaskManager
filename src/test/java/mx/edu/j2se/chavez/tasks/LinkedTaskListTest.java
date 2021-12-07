package mx.edu.j2se.chavez.tasks;

import org.junit.Assert;
import org.junit.Test;

public class LinkedTaskListTest{

    @Test
    public void testTaskList() {
        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

        //Creamos varias instacias de Task las cuales se crearan con un startTime con respecto a
        //la variable iterable en el for en incrementos de 25. Hasta un tiempo maximo de 300
        for (int startTime = 0; startTime <= 300; startTime += 25) {
            Task auxiliatyTask = new Task("Task " + (startTime + 25) / 25, startTime, startTime + 96, 24);
            //Si el startTime es par activamos la tarea.
            if ((startTime % 2) == 0) {
                auxiliatyTask.setActive(true);
            }
            //Agregamos la tarea al arreglo de tareas
            tareasSemanales.add(auxiliatyTask);
        }

        //Se espera que la lista de tareas contenga 13 de ella.
        Assert.assertEquals(13, tareasSemanales.size());
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
        Assert.assertEquals(10, tareasSemanales.size());

        System.out.println("--------");
        //tareasSemanales.showTasks();
        System.out.println("--------");

        nuevaTarea1 = tareasSemanales.getTask(0);
        System.out.println(nuevaTarea1.getTitle());
        nuevaTarea1 = tareasSemanales.getTask(4);
        System.out.println(nuevaTarea1.getTitle());
        nuevaTarea1 = tareasSemanales.getTask(9);
        System.out.println(nuevaTarea1.getTitle());

        nuevaTarea1 = new Task("Task " + 12, 100, 100 + 96, 24);

        //Eliminamos la nuevaTarea del arreglo.
        Assert.assertFalse(tareasSemanales.remove(nuevaTarea1));
        System.out.println("--------");
        //tareasSemanales.showTasks();
    }



    @Test
    public void testTaskSubList(){
        //Crea una instacia de un ArrayTaskList
        LinkedTaskList tareasSemanales = new LinkedTaskList();

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

        //tareasSemanales.showTasks();

        LinkedTaskList subTareas = (LinkedTaskList) tareasSemanales.incoming(10,200);

        //subTareas.showTasks();
    }
}