package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TaskTest {

    @Test
    public void testTaskRepetitive(){

        Integer interval;

        //Crea una instacia de Task repetitivo.

        LocalDateTime initTime = LocalDateTime.now().plusHours(20);

        Task repetitiveTask = new Task("Daily Sprint", initTime, initTime.plusHours(100), 10);

        //Corroboración el nombre de la tarea
        assertEquals("Daily Sprint", repetitiveTask.getTitle());

        interval = 10;
        //Verifica sus tiempos
        assertEquals(initTime, repetitiveTask.getStartTime());
        assertEquals(initTime.plusHours(100), repetitiveTask.getEndTime());
        assertEquals(interval, repetitiveTask.getRepeatInterval());

        //Verifica su estado inactivo y si es una tarea repetivia.
        Assert.assertFalse(repetitiveTask.isActive());
        Assert.assertTrue(repetitiveTask.isRepeated());

        //Activación
        repetitiveTask.setActive(true);
        Assert.assertTrue(repetitiveTask.isActive());

        //Se comprueba que getTime devuelve el startTime de un Task repetitivo.
        assertEquals(initTime, repetitiveTask.getTime());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        assertEquals(initTime.plusHours(10), repetitiveTask.nextTimeAfter(initTime));
        assertEquals(initTime.plusHours(40), repetitiveTask.nextTimeAfter(initTime.plusHours(36)));
        assertEquals(initTime.plusHours(50), repetitiveTask.nextTimeAfter(initTime.plusHours(45)));
        assertNull(repetitiveTask.nextTimeAfter(initTime.plusHours(100)));

        //Convierte en un Task no repetitivo.
        repetitiveTask.setTime(initTime.minusHours(5));

        //Estado actual del Task.
        Assert.assertFalse(repetitiveTask.isRepeated());

        //Verificar sus nuevos posibles nextTimeAfter().
        assertEquals(initTime.minusHours(5), repetitiveTask.nextTimeAfter(initTime.minusHours(10)));
    }

    @Test
    public void testTaskNormal(){

        Integer interval;

        LocalDateTime auxTime = LocalDateTime.now().plusHours(6);
        //Crea una instacia de Task no repetitivo.
        Task normalTask = new Task("Sprint Retrospective",auxTime);

        //Corroboramos el nombre de la tarea
        assertEquals("Sprint Retrospective", normalTask.getTitle());

        normalTask.setTitle("Retrospectiva del Sprint");
        //Corroboramos el nombre de la tarea sea diferente al anterior
        Assert.assertNotEquals("Sprint Retrospective", normalTask.getTitle());

        //Verifica su estado inactivo y si es una tarea no repetivia.
        Assert.assertFalse(normalTask.isActive());
        Assert.assertFalse(normalTask.isRepeated());

        interval = 0;

        //Verifica que su tiempo de intervalo sea 0
        assertEquals(interval, normalTask.getRepeatInterval());

        //Activación.
        normalTask.setActive(true);
        Assert.assertTrue(normalTask.isActive());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        assertEquals(auxTime, normalTask.nextTimeAfter(auxTime.minusHours(1)));
        assertNull(normalTask.nextTimeAfter(LocalDateTime.now().plusHours(6)));
        assertNull(normalTask.nextTimeAfter(LocalDateTime.now().plusHours(6)));

        //Convertir el Task en repetitivo.
        normalTask.setTime(auxTime,auxTime.plusHours(6), 3);

        interval = 3;
        //Verifica sus tiempos
        assertEquals(auxTime, normalTask.getStartTime());
        assertEquals(auxTime.plusHours(6), normalTask.getEndTime());
        assertEquals(interval, normalTask.getRepeatInterval());

        //Estado actual del Task.
        Assert.assertTrue(normalTask.isRepeated());

        //Verifica el funcionamiento del correcto del metodo nextTimeAfter
        assertEquals(auxTime.plusHours(6), normalTask.nextTimeAfter(auxTime.plusHours(5)));
    }

    @Test
    public void testTaskEquals(){
        LocalDateTime time = LocalDateTime.now();
        Task repetitiveTask = new Task("Daily Sprint",time.plusHours(20), time.plusHours(100), 10);
        Task repetitiveTask2 = new Task("Daily Sprint",time.plusHours(20),time.plusHours(100), 10);

        assertEquals(repetitiveTask, repetitiveTask2);

        Task reptitiveClone = null;
        try {
            reptitiveClone = repetitiveTask.clone();
        } catch (CloneNotSupportedException exception) {
            System.out.println("Impossible to clone");
        }
        assertEquals(repetitiveTask, reptitiveClone);
    }

    @Test
    public void testTaskExceptionConstructors(){

        /*
        * Validando excepciones para una tarea no repetitiva
        * */
        //Tiempo de inicio negativo
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",null);
        } catch (IllegalArgumentException exception){
            assertEquals("You need a Start Time.", exception.getMessage());
        }
        //Sin titulo
        try{
            Task repetitiveTask2 = new Task("",LocalDateTime.now().plusHours(20));
        } catch (IllegalArgumentException exception){
            assertEquals("Task cannot take in an empty String or null value for the \"title\" constructor", exception.getMessage());
        }

        /*
         * Validando excepciones para una tarea repetitiva
         * */
        // Intervalo de tiempo negativo
        try{

            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(20), LocalDateTime.now().plusHours(50), -10);
        } catch (IllegalArgumentException exception){
            assertEquals("Interval time cannot be negative", exception.getMessage());
        }
        // Tiempo de finalización negativo
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10), null, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("You need a End Time.", exception.getMessage());
        }
        // Tiempo de inicio y finalización iguales
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(50), LocalDateTime.now().plusHours(50), 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be equals than Start time. It has to be greater", exception.getMessage());
        }
    }

    @Test
    public void testTaskExceptionMethods(){

        /*
         * Setting un titulo vacio
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(50), 10);
            repetitiveTask2.setTitle("");
        } catch (IllegalArgumentException exception){
            assertEquals("Task cannot take in an empty String or null value for the \"title\"", exception.getMessage());
        }

        /*
         * Setting un start time negativo de repetitive a no repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(50), 10);
            repetitiveTask2.setTime(LocalDateTime.now().minusHours(1));
        } catch (IllegalArgumentException exception){
            assertEquals("Your Time cannot be negative.", exception.getMessage());
        }

        /*
         * Setting un start time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10));
            repetitiveTask2.setTime(null, LocalDateTime.now().plusHours(50), 10);
        } catch (IllegalArgumentException exception){
            assertEquals("You need a Start Time.", exception.getMessage());
        }

        /*
         * Setting un end time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10));
            repetitiveTask2.setTime(LocalDateTime.now().plusHours(20), null, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("You need a End Time.", exception.getMessage());
        }

        /*
         * Setting un interval time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10));
            repetitiveTask2.setTime(LocalDateTime.now().plusHours(20), LocalDateTime.now().plusHours(50), -10);
        } catch (IllegalArgumentException exception){
            assertEquals("Interval time cannot be negative", exception.getMessage());
        }

        /*
         * Setting un end time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10));
            repetitiveTask2.setTime(LocalDateTime.now().plusHours(50), LocalDateTime.now().plusHours(50), 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be equals than Start time. It has to be greater", exception.getMessage());
        }

        /*
         * Setting un interval time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(10));
            repetitiveTask2.setTime(LocalDateTime.now().plusHours(70), LocalDateTime.now().plusHours(50), 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be less than Start time. It has to be greater", exception.getMessage());
        }

        /*
         * Current time en nextTimeAfter negativo
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",LocalDateTime.now().plusHours(70), LocalDateTime.now().plusHours(100), 10);
            repetitiveTask2.nextTimeAfter(null);
        } catch (IllegalArgumentException exception){
            assertEquals("You need to introduce the Current time", exception.getMessage());
        }
    }
}