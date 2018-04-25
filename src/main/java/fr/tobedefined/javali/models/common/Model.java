package fr.tobedefined.javali.models.common;

public interface Model {
    default String getName() {
        return this.getClass().getName();
    }
    void fit(double[] X);
    double predict(double x);
    ErrorRange getErrorRange();
}
