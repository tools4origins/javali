package fr.tobedefined.javali.models.api;

import fr.tobedefined.javali.IndexedData;
import fr.tobedefined.javali.models.common.Model;
import org.junit.jupiter.api.Test;

import static fr.tobedefined.javali.data.datasets.Y_EQUALS_X2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface ModelContract {
    long[] DATASET = Y_EQUALS_X2;
    int DATASET_SIZE = DATASET.length;
    int FIRST_POSITION = 0;
    int LAST_POSITION = DATASET_SIZE - 1;
    long MIN_VALUE = DATASET[FIRST_POSITION];
    long MAX_VALUE = DATASET[LAST_POSITION];

    Model retrieveModel();

    default IndexedData retrieveIndexedData() {
        return new IndexedData(retrieveModel(), DATASET);
    }

    @Test
    default void modelShouldPermitPositionRetrievalOfTheFirstValue() {
        assertEquals(FIRST_POSITION, retrieveIndexedData().indexOf(MIN_VALUE));
    }

    @Test
    default void modelShouldPermitPositionRetrievalOfSmallValues() {
        assertEquals(5, retrieveIndexedData().indexOf(DATASET[5]));
    }

    @Test
    default void modelShouldPermitPositionRetrievalOfLargeValues() {
        assertEquals(DATASET_SIZE - 5, retrieveIndexedData().indexOf(DATASET[DATASET_SIZE - 5]));
    }

    @Test
    default void modelShouldPermitPositionRetrievalOfTheLastValue() {
        assertEquals(LAST_POSITION, retrieveIndexedData().indexOf(MAX_VALUE));
    }

    @Test
    default void modelShouldReturnMinus1ForTooSmallValues() {
        assertEquals(-1, retrieveIndexedData().indexOf(MIN_VALUE - 1));
    }

    @Test
    default void modelShouldReturnMinus1ForTooBigValues() {
        assertEquals(-1, retrieveIndexedData().indexOf(MAX_VALUE + 1));
    }

}