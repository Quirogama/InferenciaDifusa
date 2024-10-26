import java.util.ArrayList;

public class FuzzyInferencia {
    private final ArrayList<FuzzyRegla> reglas;

    public FuzzyInferencia() {
        this.reglas = new ArrayList<>();
    }

    public void agregarRegla(FuzzyRegla regla) {
        reglas.add(regla);
    }

    // Aplica todas las reglas de inferencia
    public void inferir() {
        System.out.println("Ejecutando inferencia difusa...");
        for (FuzzyRegla regla : reglas) {
            System.out.println(regla);
        }
    }

    // Método de defuzzificación (implementación futura)
    public double defuzzificar() {
        return 0.0; // Placeholder para defuzzificación
    }
}
