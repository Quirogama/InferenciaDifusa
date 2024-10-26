public class FuzzySet {
    private final String nombre;
    private final double[] parametros;
    private final String tipo; // triangular o trapezoidal

    public FuzzySet(String nombre, double[] parametros, String tipo) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.tipo = tipo;
    }

    // Calcula el grado de pertenencia de un valor en el conjunto difuso
    public double pertenencia(double valor) {
        if (tipo.equals("triangular")) {
            return pertenenciaTriangular(valor);
        } else if (tipo.equals("trapezoidal")) {
            return pertenenciaTrapezoidal(valor);
        }
        return 0.0;
    }

    private double pertenenciaTriangular(double valor) {
        double a = parametros[0];
        double b = parametros[1];
        double c = parametros[2];
        return Math.max(Math.min((valor - a) / (b - a), (c - valor) / (c - b)), 0);
    }

    private double pertenenciaTrapezoidal(double valor) {
        double a = parametros[0];
        double b = parametros[1];
        double c = parametros[2];
        double d = parametros[3];
        return Math.max(Math.min(Math.min((valor - a) / (b - a), 1), (d - valor) / (d - c)), 0);
    }

    public String getNombre() {
        return nombre;
    }
}