package io.rxjava.pattern.observer;

/**
 * Created by KDS on 2015-04-06.
 */
public class MyObserver implements Observer {

    private String name;
    private Observable topic;

    public MyObserver(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
            System.out.println(name+":: Consuming message::"+msg);
    }

    @Override
    public void setSubject(Observable sub) {
        this.topic=sub;
    }

}
