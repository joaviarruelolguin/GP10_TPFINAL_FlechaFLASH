
package controlador;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotificadorCambios {

    // Nombre del evento general.
    private static final String CAMBIO = "CAMBIO";

    // Tipos de entidades que pueden modificarse.
    public static final String PASAJEROS = "PASAJEROS";
    public static final String RECORRIDOS = "RECORRIDOS";
    public static final String COLECTIVOS = "COLECTIVOS";
    public static final String ASIENTOS = "ASIENTOS";
    public static final String SALIDAS = "SALIDAS";
    public static final String VENTAS = "VENTAS";

    // Mecanismo central de comunicación.
    private static final PropertyChangeSupport cambios
            = new PropertyChangeSupport(NotificadorCambios.class);

    // Registrar un componente interesado en recibir notificaciones.
    public static void agregarListener(PropertyChangeListener listener) {
        cambios.addPropertyChangeListener(listener);
    }

    // Dejar de recibir notificaciones.
    public static void quitarListener(PropertyChangeListener listener) {
        cambios.removePropertyChangeListener(listener);
    }

    // Comunicar que una entidad sufrió un cambio.
    public static void notificarCambio(String entidad) {
        cambios.firePropertyChange(CAMBIO, null, entidad);
    }

}
