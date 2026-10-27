package modelo;

public enum TipoPasajero {
    GENERAL(1),
    ESTUDIANTE(2),
    JUBILADO(3),
    PERSONA_CON_DISCAPACIDAD(4);

    private final int codigo;

    TipoPasajero(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoPasajero desdeCodigo(int codigo) {
        for (TipoPasajero t : values()) {
            if (t.codigo == codigo) {
                return t;
            }
        }
        throw new IllegalArgumentException("Código de tipo de pasajero inválido: " + codigo);
    }
}