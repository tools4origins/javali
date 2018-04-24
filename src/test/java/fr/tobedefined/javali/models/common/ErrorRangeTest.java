package fr.tobedefined.javali.models.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorRangeTest {

    @Test
    void ingestShouldIgnoreInRangeValues() {
        ErrorRange previousErrorRange = new ErrorRange(-5, 5);
        ErrorRange newErrorRange = previousErrorRange.ingest(1);
        assertEquals(new ErrorRange(-5, 5), newErrorRange);
    }

    @Test
    void ingestShouldUpdateErrorWithLargeLeftError() {
        ErrorRange previousErrorRange = new ErrorRange(-5, 5);
        ErrorRange newErrorRange = previousErrorRange.ingest(-10);
        assertEquals(new ErrorRange(-10, 5), newErrorRange);
    }

    @Test
    void ingestShouldUpdateErrorWithLargeRightError() {
        ErrorRange previousErrorRange = new ErrorRange(-5, 5);
        ErrorRange newErrorRange = previousErrorRange.ingest(10);
        assertEquals(new ErrorRange(-5, 10), newErrorRange);
    }

    @Test
    void combineShouldBeReflective() {
        ErrorRange errorRange1 = new ErrorRange(-5, 1);
        ErrorRange errorRange2 = new ErrorRange(-1, 5);
        assertEquals(errorRange1.combine(errorRange2), errorRange2.combine(errorRange1));
    }

    @Test
    void combineShouldIgnoreSmallerErrors() {
        ErrorRange errorRange1 = new ErrorRange(-5, 5);
        ErrorRange errorRange2 = new ErrorRange(-1, -1);
        assertEquals(new ErrorRange(-5, 5), errorRange1.combine(errorRange2));
    }

    @Test
    void combineShouldConsiderMinLeftErrorAndMaxLeftError() {
        ErrorRange errorRange1 = new ErrorRange(-5, 1);
        ErrorRange errorRange2 = new ErrorRange(-1, 5);
        assertEquals(new ErrorRange(-5, 5), errorRange1.combine(errorRange2));
    }

    @Test
    void combine() {
    }
}