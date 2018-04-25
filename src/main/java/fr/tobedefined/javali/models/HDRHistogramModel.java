package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.common.Model;
import fr.tobedefined.javali.models.common.ErrorRange;
import org.HdrHistogram.Histogram;

import java.util.stream.IntStream;

public class HDRHistogramModel implements Model {
    private Histogram histogram;

    private long[] X;
    private long[] y;
    private ErrorRange errorRange;

    public void fit(long[] X) {
        this.X = X;
        this.y = IntStream.range(0, X.length).mapToLong(x -> x).toArray();

        long maxValue = X[X.length - 1];
        this.histogram = new Histogram(maxValue,2);
        this.fit();
        this.updateMinMaxError();
    }

    private void fit() {
        if (X == null) {
            throw new RuntimeException("Trying to fit a model without data");
        }

        for (long x :this.X) {
            histogram.recordValue(x);
        }
    }

    public double predict(long x) {
        return histogram.getPercentileAtOrBelowValue((long) x) * histogram.getTotalCount() / 100;
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
