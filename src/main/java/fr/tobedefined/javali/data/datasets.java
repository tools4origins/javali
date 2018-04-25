package fr.tobedefined.javali.data;

import java.util.stream.IntStream;

public abstract class datasets {
    private static final int OFFSET = 50000;
    private static final int DATASET_SIZE = 1000000;

    public static final long[] Y_EQUALS_X = IntStream.range(OFFSET, OFFSET + DATASET_SIZE)
            .mapToLong(x -> x)
            .toArray();

    public static final long[] Y_EQUALS_X2 = IntStream.range(OFFSET, OFFSET + DATASET_SIZE)
            .mapToLong(x -> (long) Math.pow(x, 2))
            .toArray();

    public static final long[] Y_EQUALS_X3 = IntStream.range(OFFSET, OFFSET + DATASET_SIZE)
            .mapToLong(x -> (long) Math.pow(x, 3))
            .toArray();
}
