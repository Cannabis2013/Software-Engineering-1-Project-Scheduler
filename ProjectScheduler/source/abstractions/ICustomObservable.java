package abstractions;

public interface ICustomObservable {
	void NotifyObservers();
    void SubScribe(ICustomObserver observer);
    void UnSubScribe(ICustomObserver observer);
    void UnSubScribeAll();
}
