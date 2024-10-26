import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Cargar variables lingüísticas desde el archivo
        ArrayList<VariableLinguistica> variables = VariableLinguistica.cargarDesdeArchivo("variables.txt");
        
        // Mostrar las variables cargadas y sus conjuntos difusos
        for (VariableLinguistica variable : variables) {
            System.out.println("Variable: " + variable.getNombre() + " (" + variable.getTipo() + ")");
            variable.fuzzificar(12);  // Prueba de fuzzificación para un valor de ejemplo
        }

        // Cargar reglas difusas desde el archivo utilizando FuzzyDatos
        FuzzyDatos fuzzyDatos = FuzzyDatos.cargarDesdeArchivo("reglas.txt");

        // Mostrar las reglas cargadas
        fuzzyDatos.mostrarReglas();
    }
}
