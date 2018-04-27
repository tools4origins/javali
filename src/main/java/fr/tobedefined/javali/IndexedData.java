package fr.tobedefined.javali;

import fr.tobedefined.javali.models.common.ErrorRange;
import fr.tobedefined.javali.models.common.Model;

public class IndexedData {
    private Model model;
    private long[] data;

    public IndexedData(Model model) {
        this.model = model;
    }

    public IndexedData(Model model, long[] data) {
        this.model = model;
        this.addData(data);
    }

    public void addData(long[] data) {
        this.data = data;
        this.model.fit(data);
    }

    public int indexOf(long x) {
        double prediction = this.model.predict(x);
        ErrorRange errorRange = this.model.getErrorRange();
        int minPredictedOffset = (int) (prediction + errorRange.getLeftError());
        int maxPredictedOffset = (int) (prediction + errorRange.getRightError() + 1);
        int minOffset = Math.max(0, minPredictedOffset);
        int maxOffset = Math.min(data.length - 1, maxPredictedOffset);
        for (int i = minOffset; i <= maxOffset; i++) {
            if (data[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public double getErrorRangeRelativeSize() {
        ErrorRange errorRange = this.model.getErrorRange();
        return (errorRange.getRightError() - errorRange.getLeftError()) / this.data.length;
    }
}
