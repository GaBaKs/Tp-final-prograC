package segundaEntrega.patrones.patronObserver;
import segundaEntrega.patrones.patronState.IState;

import java.util.Observable;
import java.util.Observer;

public class Observador implements Observer {
    protected Observable observable; //la ambulancia

    public Observador(Observable observable) {
        this.observable = observable;
        this.getObservable().addObserver(this);
    }

    private Observable getObservable() {
        return observable;
    }

    @Override
    public void update(Observable o, Object queestado) {
        if (o != this.observable) {
            throw new IllegalArgumentException();
        }
        IState estado = (IState) queestado;
        this.getObservable().notifyObservers(estado);
        this.observable = (Observable) o;
    }
}