public class FuzzyRegla {
    private final String condicion;
    private final String conclusion;

    public FuzzyRegla(String condicion, String conclusion) {
        this.condicion = condicion;
        this.conclusion = conclusion;
    }

    public String getCondicion() {
        return condicion;
    }

    public String getConclusion() {
        return conclusion;
    }

    @Override
    public String toString() {
        return "SI " + condicion + " ENTONCES " + conclusion;
    }
}