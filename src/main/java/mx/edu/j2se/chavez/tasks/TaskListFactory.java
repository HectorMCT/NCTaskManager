package mx.edu.j2se.chavez.tasks;

public class TaskListFactory {

    static public AbstractTaskList createTaskList(ListTypes.types type){
        switch (type){
            case ARRAY: {
                return new ArrayTaskList();
            }
            case LINKED: {
                return new LinkedTaskList();
            }
            default: {
                throw new IllegalArgumentException("Wrong type list! Valid types: ARRAY or LINKED");
            }
        }
    }
}
