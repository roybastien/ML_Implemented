import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class SVM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		runExperiments();
		
	}
	
	public static void runExperiments() throws IOException{
		
		String training = "astro/original/train";
		String test = "astro/original/test";
		ArrayList<Vector> trainingVectorList = readNewData(training);
		ArrayList<Vector> testVectorList = readNewData(test);
		System.out.println("Max Point and Distance for original and original transformed: ");
		transformData(trainingVectorList, "astro/original/originalTrainTransformed");
		transformData(testVectorList, "astro/original/originalTestTransformed");
		System.out.println();
		training = "astro/scaled/train";
		test = "astro/scaled/test";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		System.out.println("Max Point and Distance for scaled and scaled Transformed: ");
		transformData(trainingVectorList, "astro/scaled/scaledTrainTransformed");
		transformData(testVectorList, "astro/scaled/scaledTestTransformed");
		
		System.out.println();
		System.out.println("10-fold Cross Validation on data0.10");
		training = "data0/train0.10";
		test = "data0/test0.10";
		trainingVectorList = readData(training);
		testVectorList = readData(test);
		crossValidation(trainingVectorList, testVectorList);
		
		System.out.println();
		System.out.println("10-fold Cross Validation on original");
		training = "astro/original/train";
		test = "astro/original/test";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		crossValidation(trainingVectorList, testVectorList);
		
		System.out.println();
		System.out.println("10-fold Cross Validation on scaled");
		training = "astro/scaled/train";
		test = "astro/scaled/test";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		crossValidation(trainingVectorList, testVectorList);
		
		System.out.println();
		System.out.println("10-fold Cross Validation on original Transformed");
		training = "astro/original/originalTrainTransformed";
		test = "astro/original/originalTestTransformed";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		crossValidation(trainingVectorList, testVectorList);
		
		System.out.println();
		System.out.println("10-fold Cross Validation on scaled Transformed");
		training = "astro/scaled/scaledTrainTransformed";
		test = "astro/scaled/scaledTestTransformed";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		crossValidation(trainingVectorList, testVectorList);

		System.out.println();
		System.out.println("Validation on combined data with 30 epochs");
		training = "astro/trainComb";
		test = "astro/testComb";
		trainingVectorList = readNewData(training);
		testVectorList = readNewData(test);
		crossValidation(trainingVectorList, testVectorList, 1, 10, 30);
	}
	
	public static void crossValidation(ArrayList<Vector> trainingVectorList, ArrayList<Vector> testVectorList){
		double[] rates = new double[]{0.001, 0.01, 0.1, 1.0};
		double[] C = new double[]{0.001, 0.01, 0.1, 1.0, 10};
		
		for(int i = 0; i < rates.length; i++){
			for(int j = 0; j < C.length; j++){
				runSVM(trainingVectorList, testVectorList, rates[i], C[j], 10);
			}
		}
	}
	
	public static void crossValidation(ArrayList<Vector> trainingVectorList, ArrayList<Vector> testVectorList, double initialRate, double initialC,  int epoch){
				runSVM(trainingVectorList, testVectorList, initialRate, initialC, epoch);
	}
	
	public static void transformData(ArrayList<Vector> vectorList, String filename) throws IOException{
		File file = new File(filename);
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter writer = new BufferedWriter(fw);

		
		double distance = 0.0;
		double maxDistance = Double.MIN_VALUE;
		ArrayList<Double> maxPoint = new ArrayList<Double>();
		
		double initialDistance = 0.0;
		double maxInitialDistance = Double.MIN_VALUE;
		ArrayList<Double> maxInitialPoint = new ArrayList<Double>();
		ArrayList<Double> transformed = new ArrayList<Double>();
		
		for (Vector trainingV : vectorList){
			
			for(int i = 1; i < trainingV.features.size(); i++){
				for(int j = 1; j < trainingV.features.size(); j++){
					transformed.add(trainingV.features.get(i) * trainingV.features.get(j));
				}
			}
			writer.write((int)trainingV.label + " ");
			for(int i = 0; i < transformed.size(); i++){
				distance += transformed.get(i)*transformed.get(i);
				writer.write((i+1)+":"+transformed.get(i).toString()+" ");
			}
			writer.write("\n");

			for(int i = 0; i < trainingV.features.size(); i++){
				initialDistance += trainingV.features.get(i)*trainingV.features.get(i);
			}
			
			distance = Math.sqrt(distance);
			if (distance > maxDistance){
				maxDistance = distance;
				maxPoint.clear();
				for (int i = 0; i < transformed.size(); i ++){
					maxPoint.add(transformed.get(i));
				}
			}
			
			initialDistance = Math.sqrt(initialDistance);
			if (initialDistance > maxInitialDistance){
				maxInitialDistance = initialDistance;
				maxInitialPoint.clear();
				for (int i = 1; i < trainingV.features.size(); i ++){
					maxInitialPoint.add(trainingV.features.get(i));
				}
			}
			
			distance = 0.0;
			initialDistance = 0.0;
			transformed.clear();
		}
		
		if(!filename.contains("Test")){
			System.out.println(maxInitialPoint);
			System.out.println(maxInitialDistance);
			System.out.println(maxPoint);
			System.out.println(maxDistance);
		}
		writer.close();
		
		
	}
	
	public static void runSVM(ArrayList<Vector> trainingVectorList, ArrayList<Vector> testVectorList, double initialRate, double initialC, int epoch){
		
		double learningRate = initialRate;
		double C = initialC;
		double capacity = trainingVectorList.get(0).features.size();
		ArrayList<Double> weightVector = generateSVMWeightVector(capacity);
		double errors = 0;
		double rows = trainingVectorList.size();
		double t = 0;
		
		for (int epochs = 0; epochs < epoch; epochs++){
			Collections.shuffle(trainingVectorList);
			for (Vector trainingV : trainingVectorList){
				t++;
//				System.out.println(learningRate);
				learningRate = calcLearningRate(learningRate, t, C);
				double dotProd = dotProduct(trainingV.features, weightVector);
				double ywTx = trainingV.label * dotProd;
				if (ywTx <= 1){ //<=1
					// perceptron = w <- w + r * Yi * Xi
					// w<- (1 - rate) w + (rate * C * Yi * Xi
					
					ArrayList<Double> term1 = multiplyDoubleAndVector((1-learningRate), weightVector);
					double mult = learningRate * C * trainingV.label;
					ArrayList<Double> term2 = multiplyDoubleAndVector(mult, trainingV.features);
					weightVector = addVectors(term1, term2);
//					printVector(weightVector);
				}
				else{
					// w <- (1 - rate) w
					weightVector = multiplyDoubleAndVector((1-learningRate), weightVector);
				}
			}
		}
		
//		System.out.println(weightVector);
		
		for (Vector testV : testVectorList){
			double wTx = dotProduct(testV.features, weightVector);
			double test_ywTx = testV.label * wTx;
			if (test_ywTx <= 0){
				errors++;
			}
		}
			
		double accuracy = 1 - (errors/rows);
//		System.out.println("Accuracy: " + accuracy);
		System.out.println("SVM Accuracy: " +"\t" + accuracy  +"\t" + " Learning rate: " +"\t" + initialRate +"\t" + " C: " +"\t" + C);
	}
	
	public static double calcLearningRate(double initialRate, double t, double C){
		double potC = (initialRate * t) / C;
		double ret = initialRate / (1 + potC);
		return ret;
	}
	
	public static void runPerceptron(ArrayList<Vector> trainingVectorList, ArrayList<Vector> testVectorList){
		
		double learningRate = 0.5;
		double margin = 4;
		int capacity = trainingVectorList.get(0).features.size();
		ArrayList<Double> weightVector = generateRandomWeightVector(capacity);
//		printVector(weightVector);
		double errors = 0;
		double rows = trainingVectorList.size();
		double changes = 0;
		
		for (int epochs = 0; epochs < 10; epochs++){
			Collections.shuffle(trainingVectorList);
			for (Vector trainingV : trainingVectorList){
				double dotProd = dotProduct(trainingV.features, weightVector);
				double change = trainingV.label * dotProd;
				if (change <= margin){ //<=1
					changes++;
					double mult = trainingV.label * learningRate;
					ArrayList<Double> multVector = multiplyDoubleAndVector(mult, trainingV.features);
					weightVector = addVectors(weightVector, multVector);
//					printVector(weightVector);
				}
			}
		}
		
		for (Vector testV : testVectorList){
			double dotProd = dotProduct(testV.features, weightVector);
			double change = testV.label * dotProd;
			if (change <= margin){
				errors++;
			}
		}
			
		double accuracy = 1 - (errors/rows);
//		System.out.println(accuracy + "\t with \t" + changes + "\t changes");
		System.out.println("Perceptron Accuracy: " + accuracy + " Rows: " + rows + " Features: " + capacity + " Errors: " + errors);
	}
	
	
	
	
	public static ArrayList<Double> multiplyDoubleAndVector(Double d, ArrayList<Double> a){
		ArrayList<Double> multVector = new ArrayList<Double>();
		for (int i = 0; i < a.size(); i++){
			double prod = a.get(i) * d;
			multVector.add(prod);
		}
		return multVector;
	}
	
	public static ArrayList<Double> addVectors(ArrayList<Double> a, ArrayList<Double> b){
		ArrayList<Double> sumVector = new ArrayList<Double>(a.size());
		for (int i = 0; i < a.size(); i++){
			double sum = a.get(i) + b.get(i);
			sumVector.add(sum);
		}
		return sumVector;
	}
	
	public static ArrayList<Double> generateSVMWeightVector(double capacity){
		ArrayList<Double> weightVector = new ArrayList<Double>();
//		Random rand = new Random();
		for (int i = 0; i < capacity; i++){
			weightVector.add(0.0);
		}
		return weightVector;
	}
	
	public static ArrayList<Double> generateRandomWeightVector(int capacity){
		ArrayList<Double> weightVector = new ArrayList<Double>();
		Random rand = new Random();
		for (int i = 0; i < capacity; i++){
			double weight = ThreadLocalRandom.current().nextDouble(-1, 1);
			weightVector.add(weight);
		}
		return weightVector;
	}
	
	public static double dotProduct(ArrayList<Double> a, ArrayList<Double> b){
		double sum = 0;
		for (int i = 0; i < a.size(); i ++){
			sum += a.get(i) * b.get(i);
		}
//		System.out.println(sum);
		return sum;
	}
	
	public static ArrayList<Vector> readNewData(String filename){
		File file = new File(filename);
//		System.out.println(filename + "\t ");
		Scanner scanner;
		ArrayList<Vector> vectorList = new ArrayList<Vector>();
		
		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine())		
			{
				String line = scanner.nextLine();
				Scanner lineScanner = new Scanner(line);
				Vector vector = new Vector();
				vector.label = Double.parseDouble(lineScanner.next());
				if (vector.label == 0.0){
					vector.label = -1.0;
				}
//				vector.bias = Character.getNumericValue(lineScanner.next().charAt(2));
				vector.bias = 1;
//				System.out.println(vector.label);
				vector.features.add(vector.bias);
				while (lineScanner.hasNext()){
					String str = lineScanner.next();
					String[] arr = str.split(":");
					double d = Double.parseDouble(arr[1]);
					vector.features.add(d);
				}
//				printVector(vector.features);
				vector.capacity = vector.features.size();
				vectorList.add(vector);
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vectorList;
	}

	public static ArrayList<Vector> readData(String filename){
		File file = new File(filename);
//		System.out.println(filename + "\t ");
		Scanner scanner;
		ArrayList<Vector> vectorList = new ArrayList<Vector>();
		
		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine())		
			{
				String line = scanner.nextLine();
				Scanner lineScanner = new Scanner(line);
				Vector vector = new Vector();
				vector.label = Double.parseDouble(lineScanner.next());
				vector.bias = Character.getNumericValue(lineScanner.next().charAt(2));
				vector.features.add(vector.bias);
				while (lineScanner.hasNext()){
					String str = lineScanner.next();
					String[] arr = str.split(":");
					double d = Double.parseDouble(arr[1]);
					vector.features.add(d);
				}
				vector.capacity = vector.features.size();
				vectorList.add(vector);
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vectorList;
	}
	
	public static void printVector(ArrayList<Double> vector){
		for(double d : vector){
			System.out.println(d);
		}
	}
}

class Vector{
	int capacity;
	double label;
	double bias;
	ArrayList<Double> features = new ArrayList<Double>();
	
}
