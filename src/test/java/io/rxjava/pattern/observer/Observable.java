package io.rxjava.pattern.observer;

/**
 * Created by KDS on 2015-04-06.
 */
public interface Observable {
    //methods to register and unregister observers
    public void register(Observer obj);
    public void unregister(Observer obj);

    //method to notify observers of change
    public void notifyObservers();

    //method to get updates from subject
    public Object getUpdate(Observer obj);
}
