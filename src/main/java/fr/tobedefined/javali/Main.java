package fr.tobedefined.javali;

import fr.tobedefined.javali.models.LinearRegressionModel;
import fr.tobedefined.javali.models.TDigestModel;
import fr.tobedefined.javali.models.api.Model;

import java.util.HashMap;
import java.util.Map;

import static fr.tobedefined.javali.data.datasets.*;

public class Main {
    public static void main(String[] args) {
        Map<String, double[]> datasets = new HashMap<>();
        datasets.put("y=x", Y_EQUALS_X);
        datasets.put("y=x^2", Y_EQUALS_X2);
        datasets.put("y=x^3", Y_EQUALS_X3);

        Model[] models = new Model[]{
                new LinearRegressionModel(),
                new TDigestModel()
        };

        datasets.forEach((datasetName, dataset) -> {
            System.out.println("Dataset: " + datasetName);
            for (Model model : models) {
                IndexedData indexedData = new IndexedData(model);
                indexedData.addData(Y_EQUALS_X);
                System.out.println("Model: " + model.getName() + " - Error:" + indexedData.getErrorRangeRelativeSize());
            }
        });
        System.out.println("Finished");
    }
}
