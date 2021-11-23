package mx.edu.j2se.chavez.tasks;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testTaskRepetitive(){
        //Crea una instacia de Task repetitivo.
        Task repetitiveTask = new Task("Daily Sprint",20, 100, 10);

        //Corroboración el nombre de la tarea
        Assert.assertEquals("Daily Sprint", repetitiveTask.getTitle());

        //Verifica sus tiempos
        Assert.assertEquals(20, repetitiveTask.getStartTime());
        Assert.assertEquals(100, repetitiveTask.getEndTime());
        Assert.assertEquals(10, repetitiveTask.getRepeatInterval());

        //Verifica su estado inactivo y si es una tarea repetivia.
        Assert.assertFalse(repetitiveTask.isActive());
        Assert.assertTrue(repetitiveTask.isRepeated());

        //Activación
        repetitiveTask.setActive(true);
        Assert.assertTrue(repetitiveTask.isActive());

        //Se comprueba que getTime devuelve el startTime de un Task repetitivo.
        Assert.assertEquals(20, repetitiveTask.getTime());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        Assert.assertEquals(30, repetitiveTask.nextTimeAfter(20));
        Assert.assertEquals(40, repetitiveTask.nextTimeAfter(36));
        Assert.assertEquals(50, repetitiveTask.nextTimeAfter(45));
        Assert.assertEquals(-1, repetitiveTask.nextTimeAfter(100));

        //Convierte en un Task no repetitivo.
        repetitiveTask.setTime(15);

        //Estado actual del Task.
        Assert.assertFalse(repetitiveTask.isRepeated());

        //Verificar sus nuevos posibles nextTimeAfter().
        Assert.assertEquals(15, repetitiveTask.nextTimeAfter(10));
    }

    @Test
    public void testTaskNormal(){
        //Crea una instacia de Task no repetitivo.
        Task normalTask = new Task("Sprint Retrospective",6);

        //Corroboramos el nombre de la tarea
        Assert.assertEquals("Sprint Retrospective", normalTask.getTitle());

        normalTask.setTitle("Retrospectiva del Sprint");
        //Corroboramos el nombre de la tarea sea diferente al anterior
        Assert.assertNotEquals("Sprint Retrospective", normalTask.getTitle());

        //Verifica su estado inactivo y si es una tarea no repetivia.
        Assert.assertFalse(normalTask.isActive());
        Assert.assertFalse(normalTask.isRepeated());

        //Verifica que su tiempo de intervalo sea 0
        Assert.assertEquals(0, normalTask.getRepeatInterval());

        //Activación.
        normalTask.setActive(true);
        Assert.assertTrue(normalTask.isActive());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        Assert.assertEquals(6, normalTask.nextTimeAfter(5));
        Assert.assertEquals(-1, normalTask.nextTimeAfter(6));
        Assert.assertEquals(-1, normalTask.nextTimeAfter(7));

        //Convertir el Task en repetitivo.
        normalTask.setTime(6,12, 3);

        //Verifica sus tiempos
        Assert.assertEquals(6, normalTask.getStartTime());
        Assert.assertEquals(12, normalTask.getEndTime());
        Assert.assertEquals(3, normalTask.getRepeatInterval());

        //Estado actual del Task.
        Assert.assertTrue(normalTask.isRepeated());

        //Verifica el funcionamiento del correcto del metodo nextTimeAfter
        Assert.assertEquals(12, normalTask.nextTimeAfter(11));
    }

    @Test
    public void testTaskEquals(){
        Task repetitiveTask = new Task("Daily Sprint",20, 100, 10);
        Task repetitiveTask2 = new Task("Daily Sprint",20, 100, 10);

        Assert.assertEquals(repetitiveTask, repetitiveTask2);

    }
}