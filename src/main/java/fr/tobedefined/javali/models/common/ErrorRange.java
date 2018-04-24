package fr.tobedefined.javali.models.common;

public class ErrorRange {
    private double leftError;
    private double rightError;

    public ErrorRange(double leftError, double rightError) {
        this.leftError = leftError;
        this.rightError = rightError;
    }

    /**
     * Return a new ErrorRange containing the current one and the new error value
     * @param error the new value to consider
     * @return ErrorRange
     */
    public ErrorRange ingest(double error) {
        if (error < leftError) {
            return new ErrorRange(error, rightError);
        } else if (error > rightError) {
            return new ErrorRange(leftError, error);
        } else {
            return new ErrorRange(leftError, rightError);
        }
    }

    /**
     * Return a new ErrorRange containing both the current one and the other one
     * @param other the other range to consider
     * @return ErrorRange
     */
    public ErrorRange combine(ErrorRange other) {
        return new ErrorRange(
                Math.min(this.leftError, other.leftError),
                Math.max(this.rightError, other.rightError)
        );
    }

    public double getLeftError() {
        return leftError;
    }

    public double getRightError() {
        return rightError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorRange errorRange = (ErrorRange) o;

        if (Double.compare(errorRange.leftError, leftError) != 0) return false;
        return Double.compare(errorRange.rightError, rightError) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(leftError);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rightError);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ErrorRange{" +
                "leftError=" + leftError +
                ", rightError=" + rightError +
                '}';
    }
}
