package fr.tobedefined.javali;

import fr.tobedefined.javali.models.common.ErrorRange;
import fr.tobedefined.javali.models.api.Model;

public class IndexedData {
    private Model model;
    private double[] data;

    public IndexedData(Model model) {
        this.model = model;
    }

    public IndexedData(Model model, double[] data) {
        this.model = model;
        this.addData(data);
    }

    public void addData(double[] data) {
        this.data = data;
        this.model.fit(data);
    }

    public int indexOf(double x) {
        double position = this.model.predict(x);
        ErrorRange errorRange = this.model.getErrorRange();
        int minPredictedOffset = (int) (position + errorRange.getLeftError());
        int maxPredictedOffset = (int) (position + errorRange.getRightError() + 1);
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
