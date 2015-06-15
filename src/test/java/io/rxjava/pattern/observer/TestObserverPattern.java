package io.rxjava.pattern.observer;

import org.junit.Test;

/**
 * Created by KDS on 2015-04-06.
 */
public class TestObserverPattern {

    @Test
    public void testObserver() {
        //create subject
        MyObservable myObservable = new MyObservable();

        //create observers
        Observer observer1 = new MyObserver("Obj1");
        Observer observer2 = new MyObserver("Obj2");
        Observer observer3 = new MyObserver("Obj3");

        //register observers to the subject
        myObservable.register(observer1);
        myObservable.register(observer2);
        myObservable.register(observer3);

        //attach observer to subject
        observer1.setSubject(myObservable);
        observer2.setSubject(myObservable);
        observer3.setSubject(myObservable);

        //check if any update is available
        observer1.update();

        //now send message to subject
        myObservable.postMessage("New Message");
    }
}
