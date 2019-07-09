package br.equals.DataFlowManager.api;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers(String path);
}
