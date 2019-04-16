package Abstractions;

public interface ICustomObservable {
	void NotifyObservers();
    void SubScribe(ICustomObserver observer);
    void UnSubScribe(ICustomObserver observer);
    void UnSubScribeAll();
}
