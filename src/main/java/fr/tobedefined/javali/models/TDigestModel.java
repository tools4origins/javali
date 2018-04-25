package fr.tobedefined.javali.models;

import com.tdunning.math.stats.TDigest;
import fr.tobedefined.javali.models.common.Model;
import fr.tobedefined.javali.models.common.ErrorRange;

import java.util.stream.IntStream;

public class TDigestModel implements Model {
    private TDigest tDigest;

    private double[] X;
    private double[] y;
    private ErrorRange errorRange;

    public void fit(double[] X) {
        this.tDigest = TDigest.createAvlTreeDigest(10);
        this.X = X;
        this.y = IntStream.range(0, X.length).mapToDouble(x -> x).toArray();

        this.fit();
        this.updateMinMaxError();
    }

    private void fit() {
        if (X == null) {
            throw new RuntimeException("Trying to fit a model without data");
        }

        for (double x :this.X) {
            tDigest.add(x);
        }

        tDigest.compress();
    }

    public double predict(double x) {
        return tDigest.cdf(x) * tDigest.size();
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
