import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FuzzyDatos {
    private final ArrayList<FuzzyRegla> reglas;

    public FuzzyDatos() {
        this.reglas = new ArrayList<>();
    }

    // Método para agregar una regla difusa
    public void agregarRegla(FuzzyRegla regla) {
        reglas.add(regla);
    }

    // Método para visualizar todas las reglas en la base de conocimiento
    public void mostrarReglas() {
        System.out.println("Reglas en la Base de Conocimiento:");
        for (FuzzyRegla regla : reglas) {
            System.out.println(regla);
        }
    }

    // Método para cargar reglas desde un archivo de texto
    public static FuzzyDatos cargarDesdeArchivo(String nombreArchivo) {
        FuzzyDatos fuzzyDatos = new FuzzyDatos();

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int lineaNumero = 0;

            while ((linea = lector.readLine()) != null) {
                lineaNumero++;
                linea = linea.trim();

                // Saltar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                // Separar la línea por comas
                String[] partes = linea.split(","); // Separar por comas
                System.out.println("Línea " + lineaNumero + " - Longitud de partes: " + partes.length);
                System.out.println("Contenido de partes: " + String.join(", ", partes));

                // Validación de formato exacto esperado
                if (partes.length == 12) {
                    try {
                        // Extraer partes específicas de la regla según el formato esperado
                        String variable1 = partes[1].trim();
                        String conjunto1 = partes[3].trim();
                        String variable2 = partes[5].trim();  // corregido
                        String conjunto2 = partes[7].trim();  // corregido
                        String variableSalida = partes[9].trim();
                        String conjuntoSalida = partes[11].trim(); // Última parte es el conjunto de salida

                        // Construir la regla sin duplicar palabras
                        String condicion = variable1 + " ES " + conjunto1 + " Y " + variable2 + " ES " + conjunto2;
                        String conclusion = variableSalida + " ES " + conjuntoSalida;

                        FuzzyRegla regla = new FuzzyRegla(condicion, conclusion);
                        fuzzyDatos.agregarRegla(regla);

                    } catch (Exception e) {
                        System.out.println("Error al procesar la línea " + lineaNumero + ": " + linea);
                    }
                } else {
                    System.out.println("Formato incorrecto en la línea " + lineaNumero + ": " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de reglas: " + e.getMessage());
        }

        return fuzzyDatos;
    }
}
