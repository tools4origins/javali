package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.api.Model;
import fr.tobedefined.javali.models.api.ModelContract;

class HDRHistogramModelTest implements ModelContract {
    @Override
    public Model retrieveModel() {
        return new HDRHistogramModel();
    }
}