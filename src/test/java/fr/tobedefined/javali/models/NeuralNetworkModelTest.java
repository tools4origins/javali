package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.api.ModelContract;
import fr.tobedefined.javali.models.common.Model;

class NeuralNetworkModelTest implements ModelContract {
    @Override
    public Model retrieveModel() {
        return new NeuralNetworkModel();
    }
}