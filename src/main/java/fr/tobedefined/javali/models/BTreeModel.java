package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.common.ErrorRange;
import fr.tobedefined.javali.models.common.Model;
import org.apache.directory.mavibot.btree.BTree;
import org.apache.directory.mavibot.btree.BTreeFactory;
import org.apache.directory.mavibot.btree.exception.KeyNotFoundException;
import org.apache.directory.mavibot.btree.serializer.LongSerializer;

import java.io.IOException;
import java.util.stream.IntStream;

public class BTreeModel implements Model {
    private BTree<Long, Long> bTree;

    private long[] X;
    private long[] y;
    private ErrorRange errorRange;

    public void fit(long[] X) {
        this.bTree = BTreeFactory.createInMemoryBTree(
                "bTreeModel",
                LongSerializer.INSTANCE,
                LongSerializer.INSTANCE
        );
        this.X = X;
        this.y = IntStream.range(0, X.length).mapToLong(x -> x).toArray();
        this.fit();
        this.updateMinMaxError();
    }

    private void fit() {
        if (X == null) {
            throw new RuntimeException("Trying to fit a model without data");
        }

        IntStream.range(0, X.length)
                .forEach(i -> {
                    try {
                        bTree.insert(X[i], y[i]);
                    } catch (IOException e) {
                        throw new RuntimeException("IOError: ", e);
                    }
                });
    }

    public double predict(long x) {
        try {
            return bTree.get(x);
        } catch (KeyNotFoundException e) {
            return -1;
        } catch (IOException e) {
            throw new RuntimeException("IOError: ", e);
        }
    }

    private void updateMinMaxError() {
        this.errorRange = new ErrorRange(0, 0);
    }

    public ErrorRange getErrorRange() {
        return errorRange;
    }
}
