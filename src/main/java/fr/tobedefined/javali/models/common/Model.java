package fr.tobedefined.javali.models.common;

public interface Model {
    default String getName() {
        return this.getClass().getName();
    }
    void fit(long[] X);
    double predict(long x);
    ErrorRange getErrorRange();
}
