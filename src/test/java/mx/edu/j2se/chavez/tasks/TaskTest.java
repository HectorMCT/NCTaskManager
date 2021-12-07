package mx.edu.j2se.chavez.tasks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void testTaskRepetitive(){
        //Crea una instacia de Task repetitivo.
        Task repetitiveTask = new Task("Daily Sprint",20, 100, 10);

        //Corroboración el nombre de la tarea
        assertEquals("Daily Sprint", repetitiveTask.getTitle());

        //Verifica sus tiempos
        assertEquals(20, repetitiveTask.getStartTime());
        assertEquals(100, repetitiveTask.getEndTime());
        assertEquals(10, repetitiveTask.getRepeatInterval());

        //Verifica su estado inactivo y si es una tarea repetivia.
        Assert.assertFalse(repetitiveTask.isActive());
        Assert.assertTrue(repetitiveTask.isRepeated());

        //Activación
        repetitiveTask.setActive(true);
        Assert.assertTrue(repetitiveTask.isActive());

        //Se comprueba que getTime devuelve el startTime de un Task repetitivo.
        assertEquals(20, repetitiveTask.getTime());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        assertEquals(30, repetitiveTask.nextTimeAfter(20));
        assertEquals(40, repetitiveTask.nextTimeAfter(36));
        assertEquals(50, repetitiveTask.nextTimeAfter(45));
        assertEquals(-1, repetitiveTask.nextTimeAfter(100));

        //Convierte en un Task no repetitivo.
        repetitiveTask.setTime(15);

        //Estado actual del Task.
        Assert.assertFalse(repetitiveTask.isRepeated());

        //Verificar sus nuevos posibles nextTimeAfter().
        assertEquals(15, repetitiveTask.nextTimeAfter(10));
    }

    @Test
    public void testTaskNormal(){
        //Crea una instacia de Task no repetitivo.
        Task normalTask = new Task("Sprint Retrospective",6);

        //Corroboramos el nombre de la tarea
        assertEquals("Sprint Retrospective", normalTask.getTitle());

        normalTask.setTitle("Retrospectiva del Sprint");
        //Corroboramos el nombre de la tarea sea diferente al anterior
        Assert.assertNotEquals("Sprint Retrospective", normalTask.getTitle());

        //Verifica su estado inactivo y si es una tarea no repetivia.
        Assert.assertFalse(normalTask.isActive());
        Assert.assertFalse(normalTask.isRepeated());

        //Verifica que su tiempo de intervalo sea 0
        assertEquals(0, normalTask.getRepeatInterval());

        //Activación.
        normalTask.setActive(true);
        Assert.assertTrue(normalTask.isActive());

        //Test del metodo nextTimeAfter con diferentes valores actuales.
        assertEquals(6, normalTask.nextTimeAfter(5));
        assertEquals(-1, normalTask.nextTimeAfter(6));
        assertEquals(-1, normalTask.nextTimeAfter(7));

        //Convertir el Task en repetitivo.
        normalTask.setTime(6,12, 3);

        //Verifica sus tiempos
        assertEquals(6, normalTask.getStartTime());
        assertEquals(12, normalTask.getEndTime());
        assertEquals(3, normalTask.getRepeatInterval());

        //Estado actual del Task.
        Assert.assertTrue(normalTask.isRepeated());

        //Verifica el funcionamiento del correcto del metodo nextTimeAfter
        assertEquals(12, normalTask.nextTimeAfter(11));
    }

    @Test
    public void testTaskEquals(){
        Task repetitiveTask = new Task("Daily Sprint",20, 100, 10);
        Task repetitiveTask2 = new Task("Daily Sprint",20, 100, 10);

        assertEquals(repetitiveTask, repetitiveTask2);

    }

    @Test
    public void testTaskExceptionConstructors(){

        /*
        * Validando excepciones para una tarea no repetitiva
        * */
        //Tiempo de inicio negativo
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",-20);
        } catch (IllegalArgumentException exception){
            assertEquals("Start time cannot be negative", exception.getMessage());
        }
        //Sin titulo
        try{
            Task repetitiveTask2 = new Task("",20);
        } catch (IllegalArgumentException exception){
            assertEquals("Task cannot take in an empty String or null value for the \"title\" constructor", exception.getMessage());
        }

        /*
         * Validando excepciones para una tarea repetitiva
         * */
        // Intervalo de tiempo negativo
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",20, 50, -10);
        } catch (IllegalArgumentException exception){
            assertEquals("Interval time cannot be negative", exception.getMessage());
        }
        // Tiempo de finalización negativo
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",20, -50, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be negative", exception.getMessage());
        }
        // Tiempo de inicio y finalización iguales
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",50, 50, 10);
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
            Task repetitiveTask2 = new Task("Daily Sprint",10, 50, 10);
            repetitiveTask2.setTitle("");
        } catch (IllegalArgumentException exception){
            assertEquals("Task cannot take in an empty String or null value for the \"title\"", exception.getMessage());
        }

        /*
         * Setting un start time negativo de repetitive a no repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10, 50, 10);
            repetitiveTask2.setTime(-10);
        } catch (IllegalArgumentException exception){
            assertEquals("Time cannot be negative", exception.getMessage());
        }

        /*
         * Setting un start time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10);
            repetitiveTask2.setTime(-20, 50, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("Start time cannot be negative", exception.getMessage());
        }

        /*
         * Setting un end time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10);
            repetitiveTask2.setTime(20, -50, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be negative", exception.getMessage());
        }

        /*
         * Setting un interval time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10);
            repetitiveTask2.setTime(20, 50, -10);
        } catch (IllegalArgumentException exception){
            assertEquals("Interval time cannot be negative", exception.getMessage());
        }

        /*
         * Setting un end time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10);
            repetitiveTask2.setTime(50, 50, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be equals than Start time. It has to be greater", exception.getMessage());
        }

        /*
         * Setting un interval time negativo de no repetitive a repetitive Task
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",10);
            repetitiveTask2.setTime(70, 50, 10);
        } catch (IllegalArgumentException exception){
            assertEquals("End time cannot be less than Start time. It has to be greater", exception.getMessage());
        }

        /*
         * Current time en nextTimeAfter negativo
         * */
        try{
            Task repetitiveTask2 = new Task("Daily Sprint",70, 100, 10);
            repetitiveTask2.nextTimeAfter(-1);
        } catch (IllegalArgumentException exception){
            assertEquals("Current time cannot be negative", exception.getMessage());
        }
    }
}