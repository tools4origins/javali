package fr.tobedefined.javali.models.api;

import fr.tobedefined.javali.IndexedData;
import org.junit.jupiter.api.Test;

import static fr.tobedefined.javali.data.datasets.Y_EQUALS_X;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface ModelContract {
    double[] DATASET = Y_EQUALS_X;
    int DATASET_SIZE = Y_EQUALS_X.length;

    Model retrieveModel();

    default IndexedData retrieveIndexedData() {
        return new IndexedData(retrieveModel(), Y_EQUALS_X);
    }

    @Test
    default void modelShouldWorkWithSmallValues() {
        assertEquals(0, retrieveIndexedData().indexOf(Y_EQUALS_X[0]));
    }

    @Test
    default void modelShouldWorkWithLargeValues() {
        assertEquals(DATASET_SIZE -1, retrieveIndexedData().indexOf(Y_EQUALS_X[DATASET_SIZE -1]));
    }

    @Test
    default void modelShouldNotWorkWithTooSmallValues() {
        assertEquals(-1, retrieveIndexedData().indexOf(DATASET[0]-1));
    }

    @Test
    default void modelShouldNotWorkWithTooBigValues() {
        assertEquals(-1, retrieveIndexedData().indexOf(DATASET[DATASET_SIZE-1] + 1));
    }

}