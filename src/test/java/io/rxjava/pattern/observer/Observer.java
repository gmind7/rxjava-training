package io.rxjava.pattern.observer;

/**
 * Created by KDS on 2015-04-06.
 */
public interface Observer {

    //method to update the observer, used by subject
    public void update();

    //attach with subject to observe
    public void setSubject(Observable sub);

}
