package fr.tobedefined.javali.models.api;

import fr.tobedefined.javali.models.common.ErrorRange;

public interface Model {
    default String getName() {
        return this.getClass().getName();
    }
    void fit(double[] X);
    double predict(double x);
    ErrorRange getErrorRange();
}
