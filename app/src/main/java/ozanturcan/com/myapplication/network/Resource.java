package ozanturcan.com.myapplication.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    public enum State{
        LOADING,
        SUCCESS,
        ERROR
    }

    private T data;
    private Throwable throwable;
    private State state;

    private Resource(@Nullable T data, @Nullable Throwable throwable, @NonNull State state) {
        this.data = data;
        this.throwable = throwable;
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public State getState() {
        return state;
    }

    public boolean isSuccess(){
        return state == Resource.State.SUCCESS;
    }

    public boolean isLoading(){
        return state == State.LOADING;
    }

    public boolean isError(){
        return state == State.ERROR;
    }

    public static <T> Resource<T> succes(T data){
        return new Resource<>(data, null, State.SUCCESS);
    }

    public static <T> Resource<T> error(Throwable throwable){
        return new Resource<>(null, throwable, State.ERROR);
    }

    public static <T> Resource loading(){
        return new Resource<>(null, null, State.LOADING);
    }


}
