import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VariableLinguistica {
    private final String nombre;
    private final String tipo; // entrada o salida
    private final ArrayList<FuzzySet> conjuntosDifusos;

    public VariableLinguistica(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.conjuntosDifusos = new ArrayList<>();
    }

    public void agregarConjuntoDifuso(FuzzySet conjunto) {
        conjuntosDifusos.add(conjunto);
    }

    // Realiza la fuzzificación para un valor real
    public void fuzzificar(double valorReal) {
        for (FuzzySet conjunto : conjuntosDifusos) {
            double gradoPertenencia = conjunto.pertenencia(valorReal);
            System.out.println("Grado de pertenencia de " + conjunto.getNombre() + " es: " + gradoPertenencia);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    // Método estático para cargar variables desde un archivo de texto
    public static ArrayList<VariableLinguistica> cargarDesdeArchivo(String nombreArchivo) {
        ArrayList<VariableLinguistica> variables = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            VariableLinguistica variableActual = null;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                // Saltar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                // Si la línea tiene dos elementos, es una nueva variable lingüística
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombreVariable = partes[0].trim();
                    String tipoVariable = partes[1].trim();
                    variableActual = new VariableLinguistica(nombreVariable, tipoVariable);
                    variables.add(variableActual);

                // Si la línea tiene 4 o 5 elementos, es un conjunto difuso de la variable actual
                } else if (partes.length >= 4 && variableActual != null) {
                    String nombreConjunto = partes[0].trim();
                    String tipoFuncion = partes[1].trim();
                    double a = Double.parseDouble(partes[2].trim());
                    double b = Double.parseDouble(partes[3].trim());
                    double c = Double.parseDouble(partes[4].trim());
                    double[] parametros;

                    if (tipoFuncion.equalsIgnoreCase("triangular") && partes.length == 5) {
                        parametros = new double[]{a, b, c};
                    } else if (tipoFuncion.equalsIgnoreCase("trapezoidal") && partes.length == 6) {
                        double d = Double.parseDouble(partes[5].trim());
                        parametros = new double[]{a, b, c, d};
                    } else {
                        System.out.println("Error en la línea de conjunto difuso: " + linea);
                        continue;
                    }

                    FuzzySet conjunto = new FuzzySet(nombreConjunto, parametros, tipoFuncion);
                    variableActual.agregarConjuntoDifuso(conjunto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return variables;
    }
}
