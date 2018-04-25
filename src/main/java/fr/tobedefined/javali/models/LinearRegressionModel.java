package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.common.Model;
import fr.tobedefined.javali.models.common.ErrorRange;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.stream.IntStream;

public class LinearRegressionModel implements Model {
    private SimpleRegression regression;

    private long[] X;
    private long[] y;
    private ErrorRange errorRange;

    public void fit(long[] X) {
        this.X = X;
        this.y = IntStream.range(0, X.length).mapToLong(x -> x).toArray();

        this.fit();
        this.updateMinMaxError();
    }

    private void fit() {
        this.regression = new SimpleRegression();

        if (X == null) {
            throw new RuntimeException("Trying to fit a model without data");
        }

        for (int i = 0; i < this.X.length; i++) {
            regression.addData(this.X[i], this.y[i]);
        }
    }

    public double predict(long x) {
        return regression.predict(x);
    }

    private void updateMinMaxError() {
        this.errorRange = IntStream.range(0, X.length)
                .mapToObj(i ->  y[i] - this.predict(X[i]))
                .reduce(new ErrorRange(0, 0), ErrorRange::ingest, ErrorRange::combine);
    }

    public ErrorRange getErrorRange() {
        return errorRange;
    }
}
