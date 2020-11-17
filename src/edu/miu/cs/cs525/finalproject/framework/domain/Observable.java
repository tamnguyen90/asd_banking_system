package edu.miu.cs.cs525.finalproject.framework.domain;

public interface Observable {
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers(double change);
}
