import java.util.stream.IntStream;

public class LinearRegression {
    private final double intercept, slope;

    public LinearRegression(double[] prediction, double[] response) {
        if (prediction.length != response.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        int total = prediction.length;
        // first pass
        double sumPredict = 0.0, sumResponse = 0.0, sum = 0.0;
        FirstPass firstPass = new FirstPass(prediction, response, total, sumPredict, sumResponse, sum).invoke();
        double predictRate = firstPass.getPredictRate();
        double responseRate = firstPass.getResponseRate();

        // second pass: compute summary statistics
        SummaryStatistics summaryStatistics = new SummaryStatistics(prediction, response, total, predictRate, responseRate).invoke();
        double predictSummary = summaryStatistics.getPredictSummary();
        double summary = summaryStatistics.getSummary();
        slope  = summary / predictSummary;
        intercept = responseRate - slope * predictRate;

        // more statistical analysis

    }

    public double predict(double x)
    {

        return slope * x + intercept;
    }

    private static class FirstPass {
        private double[] prediction;
        private double[] response;
        private int total;
        private double sumPredict;
        private double sumResponse;
        private double predictRate;
        private double responseRate;

        public FirstPass(double[] prediction, double[] response, int total, double sumPredict, double sumResponse, double sum) {
            this.prediction = prediction;
            this.response = response;
            this.total = total;
            this.sumPredict = sumPredict;
            this.sumResponse = sumResponse;
        }

        public double getPredictRate() {
            return predictRate;
        }

        public double getResponseRate() {
            return responseRate;
        }

        public FirstPass invoke() {
            IntStream.range(0, total).forEach(i -> {
                sumPredict += prediction[i];
                sumResponse += response[i];
            });
            predictRate = sumPredict / total;
            responseRate = sumResponse / total;
            return this;
        }
    }

    private static class SummaryStatistics {
        private double[] prediction;
        private double[] response;
        private int total;
        private double predictRate;
        private double responseRate;
        private double predictSummary;
        private double summary;

        public SummaryStatistics(double[] prediction, double[] response, int total, double predictRate, double responseRate) {
            this.prediction = prediction;
            this.response = response;
            this.total = total;
            this.predictRate = predictRate;
            this.responseRate = responseRate;
        }

        public double getPredictSummary() {
            return predictSummary;
        }

        public double getSummary() {
            return summary;
        }

        public SummaryStatistics invoke() {
            predictSummary = 0.0;
            summary = 0.0;
            IntStream.range(0, total).forEach(i -> {
                predictSummary += (prediction[i] - predictRate) * (prediction[i] - predictRate);
                summary += (prediction[i] - predictRate) * (response[i] - responseRate);
            });
            return this;
        }
    }


}