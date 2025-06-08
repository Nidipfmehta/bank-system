package com.example.bank_notification_system.dummy;

public interface IObservable {

    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyChange() ;
}
