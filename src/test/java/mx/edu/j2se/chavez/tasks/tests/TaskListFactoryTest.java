package mx.edu.j2se.chavez.tasks.tests;

import mx.edu.j2se.chavez.tasks.ArrayTaskList;
import mx.edu.j2se.chavez.tasks.LinkedTaskList;
import mx.edu.j2se.chavez.tasks.ListTypes;
import mx.edu.j2se.chavez.tasks.TaskListFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TaskListFactoryTest {

    @Test
    public void testFactory(){

        //Create an instance of ArrayTaskList using de Factory
        assertTrue(String.valueOf(TaskListFactory.createTaskList(ListTypes.types.ARRAY) instanceof ArrayTaskList), true);
        //Create an instance of LinkedTaskList using de Factory
        assertTrue(String.valueOf(TaskListFactory.createTaskList(ListTypes.types.LINKED) instanceof LinkedTaskList), true);
        //Create an instance of ArrayTaskList using de Factory
        assertTrue(String.valueOf(TaskListFactory.createTaskList(ListTypes.types.ARRAY) instanceof ArrayTaskList), true);
        //Create an instance of LinkedTaskList using de Factory
        assertTrue(String.valueOf(TaskListFactory.createTaskList(ListTypes.types.LINKED) instanceof LinkedTaskList), true);
    }
}