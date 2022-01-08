package mx.edu.j2se.chavez.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

public class TaskIO {

    /**
     * <p>
     *     Stored the list of task to stream in a binary format.
     * </p>
     * @param tasks List of task to be store.
     * @param out Where the tasks are going to be stored.
     * @throws IllegalArgumentException if the list or out OutputStream are null
     */
    public void write(AbstractTaskList tasks, OutputStream out) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (out == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }

        DataOutput outStream = new DataOutputStream(out);
        //Size of the list
        outStream.writeInt(tasks.size());

        tasks.getStream().filter(Objects::nonNull).forEach(task -> {
            try {
                //Title length
                outStream.writeInt(task.getTitle().length());
                //Title
                outStream.writeUTF(task.getTitle());

                // State: 1 active - 0 inactive
                if (task.isActive()) {
                    outStream.writeInt(1);
                } else {
                    outStream.writeInt(0);
                }
                // Repetition interval
                outStream.writeInt(task.getRepeatInterval());

                if (task.isRepeated()) {
                    //Start time
                    outStream.writeLong(task.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                    //End time
                    outStream.writeLong(task.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                }
                else {
                    //Start time
                    outStream.writeLong(task.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * <p>
     *     Reads tasks from the binary stream to the current task list.
     * </p>
     * @param tasks The list to store the tasks.
     * @param in Where are obtained the tasks
     * @throws IllegalArgumentException if the list or in InputStream are null
     */
    public void read(AbstractTaskList tasks, InputStream in) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (in == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }

        DataInputStream inStream = new DataInputStream(in);
        //Number of task (list size)
        int taskCount = inStream.readInt();

        for (int i = 0; i < taskCount; i++) {
            try {
                Task task;
                int titleLength = inStream.readInt();
                String title = inStream.readUTF();
                boolean isActive = inStream.readInt() == 1;
                int interval = inStream.readInt();
                if (interval > 0) {
                    // Get startTime and endTime for repetitive task
                    LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(inStream.readLong()), TimeZone.getDefault().toZoneId());
                    LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(inStream.readLong()), TimeZone.getDefault().toZoneId());
                    task = new Task(title, startTime, endTime, interval);
                    task.setActive(isActive);
                }
                else {
                    // Get time for no repetitive task
                    LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(inStream.readLong()), TimeZone.getDefault().toZoneId());
                    task = new Task(title, time);
                    task.setActive(isActive);
                }
                // Add task to the list
                tasks.add(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>
     *     Store the list of task to the binary file
     * </p>
     * @param tasks The list to be store
     * @param file Where the tasks will be store
     * @throws IllegalArgumentException if the list or the file arguments are null
     */
    public void writeBinary(AbstractTaskList tasks, File file) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (file == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        FileOutputStream fos = new FileOutputStream(file);
        write(tasks, fos);
        fos.flush();
    }

    /**
     * <p>
     *     Read the list of task to the binary file
     * </p>
     * @param tasks The list to be read
     * @param file Where the tasks will be read
     * @throws IllegalArgumentException if the list or the file arguments are null
     */
    public void readBinary(AbstractTaskList tasks, File file) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (file == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        FileInputStream fos = new FileInputStream(file);
        read(tasks, fos);
        fos.close();

    }

    /**
     * <p>
     *     Writes tasks from the list to the stream in the JSON format.
     * </p>
     * @param tasks The list to be stored
     * @param out Where the tasks are going to be stored
     * @throws IllegalArgumentException if the list or out writer are null
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (out == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        Gson gson = new Gson();
        ArrayTaskList tasksArray = new ArrayTaskList();
        tasks.getStream().filter(Objects::nonNull).forEach(tasksArray::add);
        gson.toJson(tasksArray, out);
        out.flush();
        out.close();
    }


    /**
     * <p>
     *     Reads the JSON format and convert to a list.
     * </p>
     * @param tasks list where the tasks will be stored
     * @param in Where we will obtain the task list
     * @throws IllegalArgumentException if the list or in reader are null.
     */
    public static void read(AbstractTaskList tasks, Reader in) throws IllegalArgumentException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (in == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        Gson gson = new Gson();
        ArrayTaskList taskArray = gson.fromJson(in, ArrayTaskList.class);
        for (Task task: taskArray) {
            if (task!=null) {
                tasks.add(task);
            }
        }
    }

    /**
     * <p>
     *     Store the list to the file in JSON format
     * </p>
     * @param tasks The list to be stored
     * @param file Where the tasks will be stored
     * @throws IllegalArgumentException if the list or the file are null.
     */
    public static void writeText(AbstractTaskList tasks, File file) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (file == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        Gson gson = new Gson();
        ArrayTaskList tasksArray = new ArrayTaskList();
        tasks.getStream().filter(Objects::nonNull).forEach(tasksArray::add);
        gson.toJson(tasksArray, new FileWriter(file));
    }


    /**
     * <p>
     *     Read the list from the JSON file
     * </p>
     * @param tasks The task list where the tasks will be stored
     * @param file  The file where we will obtain the task list
     * @throws IllegalArgumentException if the list or the file are null
     */
    public static void readText(AbstractTaskList tasks, File file) throws IllegalArgumentException, IOException {

        if (tasks == null ) {
            throw new IllegalArgumentException("Your tasks list must not be null");
        } else if (file == null) {
            throw new IllegalArgumentException("The file must not be null");
        }

        Gson gson = new Gson();
        ArrayTaskList taskArray = gson.fromJson(new FileReader(file), ArrayTaskList.class);
        for (Task task: taskArray) {
            if (task!=null) {
                tasks.add(task);
            }
        }
    }
}
