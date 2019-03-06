import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class kMedianCost {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		calc4Cost("C3cent.txt");
//		calc4Cost("C3cent1.txt");
//		calc4Cost("C3cent2.txt");
//		calc5Cost("5C3cent.txt");
//		calc5Cost("5C3cent1.txt");
//		calc3Cost("LLoyd123Center.txt");
//		calc3Cost("LLoydGonzCenter.txt");
		calc3Cost("LLoydCDF.txt");
		
	}
	
	public static void calc3Cost(String filename) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filename));

			double centA1 = scan.nextDouble();
			double centB1 = scan.nextDouble();	
			
			double centA2 = scan.nextDouble();
			double centB2 = scan.nextDouble();	
			
			double centA3 = scan.nextDouble();
			double centB3 = scan.nextDouble();			
			
			double aCost;
			double bCost;

			double totalCost = 0;
		
		while (scan.hasNextLine()){
			int index = scan.nextInt();
			double a = scan.nextDouble();
			double b = scan.nextDouble();	

			int centroid =scan.nextInt();
			
			switch(centroid){
			case 1:
				aCost = Math.pow(centA1-a, 2);
				bCost = Math.pow(centB1-b, 2);
				totalCost+=Math.pow(Math.sqrt(aCost+bCost),2);
				break;
			case 2:
				aCost = Math.pow(centA2-a, 2);
				bCost = Math.pow(centB2-b, 2);
				totalCost+=Math.pow(Math.sqrt(aCost+bCost),2);
				break;
			case 3:
				aCost = Math.pow(centA3-a, 2);
				bCost = Math.pow(centB3-b, 2);
				totalCost+=Math.pow(Math.sqrt(aCost+bCost),2);
				break;
			}
		}
		System.out.println("The calculated cost is: " + totalCost);
	}
	public static void calc5Cost(String filename) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filename));

			double centA1 = scan.nextDouble();
			double centB1 = scan.nextDouble();	
			double centC1 = scan.nextDouble();
			double centD1 = scan.nextDouble();
			double centE1 = scan.nextDouble();
			
			double centA2 = scan.nextDouble();
			double centB2 = scan.nextDouble();	
			double centC2 = scan.nextDouble();
			double centD2 = scan.nextDouble();
			double centE2 = scan.nextDouble();
			
			double centA3 = scan.nextDouble();
			double centB3 = scan.nextDouble();	
			double centC3 = scan.nextDouble();
			double centD3 = scan.nextDouble();
			double centE3 = scan.nextDouble();
			
			double centA4 = scan.nextDouble();
			double centB4 = scan.nextDouble();	
			double centC4 = scan.nextDouble();
			double centD4 = scan.nextDouble();
			double centE4 = scan.nextDouble();
			
			double centA5 = scan.nextDouble();
			double centB5 = scan.nextDouble();	
			double centC5 = scan.nextDouble();
			double centD5 = scan.nextDouble();
			double centE5 = scan.nextDouble();
			
			double aCost;
			double bCost;
			double cCost;
			double dCost;
			double eCost;
			double totalCost = 0;
		
		while (scan.hasNextLine()){
			int index = scan.nextInt();
			double a = scan.nextDouble();
			double b = scan.nextDouble();	
			double c = scan.nextDouble();
			double d = scan.nextDouble();
			double e = scan.nextDouble();
			int centroid =scan.nextInt();
			
			switch(centroid){
			case 1:
				aCost = Math.pow(centA1-a, 2);
				bCost = Math.pow(centB1-b, 2);
				cCost = Math.pow(centC1-c, 2);
				dCost = Math.pow(centD1-d, 2);
				eCost = Math.pow(centE1-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 2:
				aCost = Math.pow(centA2-a, 2);
				bCost = Math.pow(centB2-b, 2);
				cCost = Math.pow(centC2-c, 2);
				dCost = Math.pow(centD2-d, 2);
				eCost = Math.pow(centE2-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 3:
				aCost = Math.pow(centA3-a, 2);
				bCost = Math.pow(centB3-b, 2);
				cCost = Math.pow(centC3-c, 2);
				dCost = Math.pow(centD3-d, 2);
				eCost = Math.pow(centE3-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 4:
				aCost = Math.pow(centA4-a, 2);
				bCost = Math.pow(centB4-b, 2);
				cCost = Math.pow(centC4-c, 2);
				dCost = Math.pow(centD4-d, 2);
				eCost = Math.pow(centE4-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 5:
				aCost = Math.pow(centA5-a, 2);
				bCost = Math.pow(centB5-b, 2);
				cCost = Math.pow(centC5-c, 2);
				dCost = Math.pow(centD5-d, 2);
				eCost = Math.pow(centE5-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;

			}
		}
		System.out.println("The calculated cost is: " + totalCost);
	}
	public static void calc4Cost(String filename) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filename));

			double centA1 = scan.nextDouble();
			double centB1 = scan.nextDouble();	
			double centC1 = scan.nextDouble();
			double centD1 = scan.nextDouble();
			double centE1 = scan.nextDouble();
			
			double centA2 = scan.nextDouble();
			double centB2 = scan.nextDouble();	
			double centC2 = scan.nextDouble();
			double centD2 = scan.nextDouble();
			double centE2 = scan.nextDouble();
			
			double centA3 = scan.nextDouble();
			double centB3 = scan.nextDouble();	
			double centC3 = scan.nextDouble();
			double centD3 = scan.nextDouble();
			double centE3 = scan.nextDouble();
			
			double centA4 = scan.nextDouble();
			double centB4 = scan.nextDouble();	
			double centC4 = scan.nextDouble();
			double centD4 = scan.nextDouble();
			double centE4 = scan.nextDouble();
			
			double aCost;
			double bCost;
			double cCost;
			double dCost;
			double eCost;
			double totalCost = 0;
		
		while (scan.hasNextLine()){
			int index = scan.nextInt();
			double a = scan.nextDouble();
			double b = scan.nextDouble();	
			double c = scan.nextDouble();
			double d = scan.nextDouble();
			double e = scan.nextDouble();
			int centroid =scan.nextInt();
			
			switch(centroid){
			case 1:
				aCost = Math.pow(centA1-a, 2);
				bCost = Math.pow(centB1-b, 2);
				cCost = Math.pow(centC1-c, 2);
				dCost = Math.pow(centD1-d, 2);
				eCost = Math.pow(centE1-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 2:
				aCost = Math.pow(centA2-a, 2);
				bCost = Math.pow(centB2-b, 2);
				cCost = Math.pow(centC2-c, 2);
				dCost = Math.pow(centD2-d, 2);
				eCost = Math.pow(centE2-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 3:
				aCost = Math.pow(centA3-a, 2);
				bCost = Math.pow(centB3-b, 2);
				cCost = Math.pow(centC3-c, 2);
				dCost = Math.pow(centD3-d, 2);
				eCost = Math.pow(centE3-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;
			case 4:
				aCost = Math.pow(centA4-a, 2);
				bCost = Math.pow(centB4-b, 2);
				cCost = Math.pow(centC4-c, 2);
				dCost = Math.pow(centD4-d, 2);
				eCost = Math.pow(centE4-e, 2);
				totalCost+=Math.sqrt(aCost+bCost+cCost+dCost+eCost);
				break;

			}
		}
		System.out.println("The calculated cost is: " + totalCost);
	}
}
