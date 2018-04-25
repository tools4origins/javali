package fr.tobedefined.javali;

import fr.tobedefined.javali.models.BTreeModel;
import fr.tobedefined.javali.models.HDRHistogramModel;
import fr.tobedefined.javali.models.LinearRegressionModel;
import fr.tobedefined.javali.models.TDigestModel;
import fr.tobedefined.javali.models.api.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static fr.tobedefined.javali.data.datasets.*;

public class Main {
    public static void main(String[] args) {
        Map<String, double[]> datasets = new HashMap<>();
        datasets.put("y=x", Y_EQUALS_X);
        datasets.put("y=x^2", Y_EQUALS_X2);
        datasets.put("y=x^3", Y_EQUALS_X3);

        List<Supplier<Model>> models = new ArrayList<>();
        models.add(BTreeModel::new);
        models.add(LinearRegressionModel::new);
        models.add(TDigestModel::new);
        models.add(HDRHistogramModel::new);

        datasets.forEach((datasetName, dataset) -> {
            System.out.printf("Dataset: %s%n", datasetName);
            for (Supplier<Model> modelGenerator : models) {
                Model model = modelGenerator.get();
                System.out.printf("Model: %s - ", model.getName());
                IndexedData indexedData = new IndexedData(model);
                indexedData.addData(dataset);
                System.out.printf("Error: %s%n", indexedData.getErrorRangeRelativeSize());
            }
        });
        System.out.println("Finished");
    }
}
