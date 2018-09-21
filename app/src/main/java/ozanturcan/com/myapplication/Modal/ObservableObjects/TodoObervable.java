package ozanturcan.com.myapplication.Modal.ObservableObjects;

import java.util.List;
import java.util.Observable;

import ozanturcan.com.myapplication.Modal.TaskTodo;
import ozanturcan.com.myapplication.Modal.User;

public class TodoObervable extends Observable {

    private static TodoObervable USER_REPO_INSTANCE = null;
    private static Object mLock = new Object();
    private List<TaskTodo> taskTodoList;
    private List<User> userList;
    private UserObservable userObservable = UserObservable.getInstance();

    public TodoObervable() {

    }

    /* get Singleton instance of UserRepository  */
    public static TodoObervable getInstance() {
        synchronized (mLock) {
            if (USER_REPO_INSTANCE == null)
                USER_REPO_INSTANCE = new TodoObervable();
            return USER_REPO_INSTANCE;
        }
    }

    public List<TaskTodo> getTaskTodoList() {
        return taskTodoList;
    }

    public void setTaskTodoList(List<TaskTodo> taskTodoList) {
        this.taskTodoList = taskTodoList;
        mergerUserFromTodo();
        setChanged();
        notifyObservers();
    }

    private void mergerUserFromTodo() {
        userList = userObservable.getUserList();
        for (TaskTodo task : taskTodoList) {
            for (User user : userList) {
                if (task.getUserId() == user.id) {
                    task.setUserName(user.name);
                    break;
                }
            }
        }
    }

}

