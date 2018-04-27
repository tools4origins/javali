package fr.tobedefined.javali.models;

import fr.tobedefined.javali.models.common.ErrorRange;
import fr.tobedefined.javali.models.common.Model;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.NeuralNetworkFactory;

import java.util.stream.IntStream;

public class NeuralNetworkModel implements Model {
    private NeuralNetwork<BackPropagation> neuralNetwork;

    private long[] X;
    private long[] y;
    private ErrorRange errorRange;

    public void fit(long[] X) {
        this.X = X;
        this.y = IntStream.range(0, X.length).mapToLong(x -> x).toArray();

        this.neuralNetwork = this.createNeuralNetwork();
        this.fit();
        this.updateMinMaxError();
    }

    private NeuralNetwork<BackPropagation> createNeuralNetwork() {
        NeuralNetwork<BackPropagation> neuralNetwork = new NeuralNetwork<>();

        Layer inputLayer = new Layer();
        inputLayer.addNeuron(new Neuron());

        Layer firstHiddenLayer = new Layer();
//        Layer secondHiddenLayer = new Layer();
        for (int i = 0; i < 2; i++) {
            firstHiddenLayer.addNeuron(new Neuron());
//            secondHiddenLayer.addNeuron(new Neuron());
        }

        Layer outputLayer = new Layer();
        outputLayer.addNeuron(new Neuron());

        neuralNetwork.addLayer(inputLayer);
        neuralNetwork.addLayer(firstHiddenLayer);
//        neuralNetwork.addLayer(secondHiddenLayer);
        neuralNetwork.addLayer(outputLayer);

        ConnectionFactory.fullConnect(inputLayer, firstHiddenLayer);
//        ConnectionFactory.fullConnect(firstHiddenLayer, secondHiddenLayer);
//        ConnectionFactory.fullConnect(secondHiddenLayer, outputLayer);
        ConnectionFactory.fullConnect(firstHiddenLayer, outputLayer);

        NeuralNetworkFactory.setDefaultIO(neuralNetwork);

        return neuralNetwork;
    }

    private void fit() {
        if (X == null) {
            throw new RuntimeException("Trying to fit a model without data");
        }

        DataSet dataSet = new DataSet(1, 1);
        IntStream.range(0, X.length)
                .forEach(i -> dataSet.addRow(
                        new double[]{X[i]},
                        new double[]{y[i]}
                ));
        BackPropagation backPropagation = new BackPropagation();
        backPropagation.setMaxIterations(10);
        this.neuralNetwork.learn(dataSet, backPropagation);
    }

    public double predict(long x) {
        this.neuralNetwork.setInput(x);
        this.neuralNetwork.calculate();
        return this.neuralNetwork.getOutput()[0];
    }

    private void updateMinMaxError() {
        this.errorRange = IntStream.range(0, X.length)
                .mapToObj(i ->  y[i] - this.predict(X[i]))
                .reduce(new ErrorRange(0, 0), ErrorRange::ingest, ErrorRange::combine);
    }

    public ErrorRange getErrorRange() {
        return errorRange;
    }
}
