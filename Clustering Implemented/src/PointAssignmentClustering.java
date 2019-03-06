import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PointAssignmentClustering {

	public static void main(String[] args) {

		Gonzalez();

	}

	/* 
	 * Find the 3 center cost max
	 * Find the 3-means cost
	 * 
	 */
	public static void Gonzalez() {

		String filename = "C2.txt";
		File file = new File(filename);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int k = 3;
		int n = 0;
		int phij = 1;
		ArrayList<Double> xVal = new ArrayList<Double>();
		ArrayList<Double> yVal = new ArrayList<Double>();


		while (sc.hasNextLine())
		{
			String line = sc.nextLine();
			String[] splt = line.split("\\s");
			String p2str = splt[1];
			String p3str = splt[2];
			Double p2 = Double.parseDouble(p2str);
			Double p3 = Double.parseDouble(p3str);
			xVal.add(p2);
			yVal.add(p3);
			n++;

		}

	
		ArrayList<Double> distanceC1C2 = new ArrayList<Double>();
		double xDiff = 0;
		double xDiffC2 = 0;
		double yDiff = 0;
		double yDiffC2 = 0;
		double val = 0;
		double valC2 = 0;
		double maxDistanceC2 = 0;
		double maxXC2 = 0;
		double maxYC2 = 0;
		double maxDistanceC3 = 0;
		double maxXC3 = 0;
		double maxYC3 = 0;
	
		double sum = 0;
	

		/*
		 * Calculate the distance from each point in C2 to the 
		 * point at index C2
		 */
		for(int i = 0; i < xVal.size(); i++)
		{
			xDiff = Math.pow((xVal.get(0)-xVal.get(i)), 2);
			yDiff = Math.pow((yVal.get(0)-yVal.get(i)), 2);

			val = Math.sqrt(xDiff+yDiff);
			distanceC1C2.add(val);
			
			if(val > maxDistanceC2)
			{
				maxDistanceC2 = val;
				maxXC2 = xVal.get(i);
				maxYC2 = yVal.get(i);
			}
		}


		

	/*	for(int i = 0; i < xVal.size(); i++)
		{
			xDiff = Math.pow((xVal.get(xVal.size()-1)-xVal.get(i)), 2);
			yDiff = Math.pow((yVal.get(yVal.size()-1)-yVal.get(i)), 2);

			val = Math.sqrt(xDiff+yDiff);
			distanceC2C3.add(val);

		}

		for(int i = 0; i < xVal.size(); i++)
		{
			xDiff = Math.pow((xVal.get(0)-xVal.get(i)), 2);
			yDiff = Math.pow((yVal.get(0)-yVal.get(i)), 2);

			val = Math.sqrt(xDiff+yDiff);
			distanceC1C3.add(val);

		}*/

		double maxVal = 0;
		double maxXVal = 0;
		double maxYVal = 0;

		for(int i = 0; i < xVal.size(); i++)
		{
			xDiff = Math.pow((xVal.get(0)-xVal.get(i)), 2);
			yDiff = Math.pow((yVal.get(0)-yVal.get(i)), 2);
			xDiffC2 = Math.pow((xVal.get(xVal.size()-1)-xVal.get(i)), 2);
			yDiffC2 = Math.pow((yVal.get(yVal.size()-1)-yVal.get(i)), 2);

			valC2 = Math.sqrt(xDiffC2+yDiffC2);	
			val = Math.sqrt(xDiff+yDiff);
			sum = valC2 + val;

			if(sum > maxVal)
			{
				maxVal = sum;
				maxXVal = xVal.get(i);
				maxYVal = yVal.get(i);
			}


		}

		System.out.println(maxXVal);
		System.out.println(maxYVal);


		double C1Xdist = 0;
		double C1Ydist = 0;
		double C2Xdist = 0;
		double C2Ydist = 0;
		double C3Xdist = 0;
		double C3Ydist = 0;
		double C1dist = 0;
		double C2dist = 0;
		double C3dist = 0;
		double C1X = -19.07480000;
		double C1Y = -8.536;
		double C2X = 27.99678867;
		double C2Y = -15.20905071;
		//double C2X = 29.10710988;
		//double C2Y = -8.527838244;
		double C3X = -40;
		double C3Y = 40;
		double smallest;
		ArrayList<Double> C1listX = new ArrayList<Double>();
		ArrayList<Double> C1listY = new ArrayList<Double>();
		ArrayList<Double> C1listdVal = new ArrayList<Double>();
		ArrayList<Double> C2listX = new ArrayList<Double>();
		ArrayList<Double> C2listY = new ArrayList<Double>();
		ArrayList<Double> C2listdVal = new ArrayList<Double>();
		ArrayList<Double> C3listX = new ArrayList<Double>();
		ArrayList<Double> C3listY = new ArrayList<Double>();
		ArrayList<Double> C3listdVal = new ArrayList<Double>();
		for(int i = 0; i < xVal.size(); i++)
		{
			C1Xdist = Math.pow((C1X-xVal.get(i)), 2);
			C1Ydist = Math.pow((C1Y-yVal.get(i)), 2);
			C2Xdist = Math.pow((C2X-xVal.get(i)), 2);
			C2Ydist = Math.pow((C2Y-yVal.get(i)), 2);
			C3Xdist = Math.pow((C3X-xVal.get(i)), 2);
			C3Ydist = Math.pow((C3Y-yVal.get(i)), 2);

			C1dist = Math.sqrt(C1Xdist + C1Ydist);
			C2dist = Math.sqrt(C2Xdist + C2Ydist);
			C3dist = Math.sqrt(C3Xdist + C3Ydist);

			smallest = Math.min(C1dist, Math.min(C2dist, C2dist));

			if(xVal.get(i) != C2X && xVal.get(i) != C3X)
			{
				if(yVal.get(i) != C2Y && yVal.get(i) != C3X)
				{
					if(smallest == C1dist)
					{
						C1listX.add(xVal.get(i));
						C1listY.add(yVal.get(i));
						C1listdVal.add(smallest);

					}
				}
			}

			if(xVal.get(i) != C1X  && xVal.get(i) != C3X)
			{
				if(yVal.get(i)!= C1Y && yVal.get(i) != C3X)
				{

					if(smallest == C2dist)
					{
						C2listX.add(xVal.get(i));
						C2listY.add(yVal.get(i));
						C2listdVal.add(smallest);

					}
				}
			}

			if(xVal.get(i) != C1X && xVal.get(i) != C2X)
			{
				if(yVal.get(i) != C1Y && yVal.get(i) != C2Y)
				{
					if(smallest == C3dist)
					{
						C3listX.add(xVal.get(i));
						C3listY.add(yVal.get(i));
						C3listdVal.add(smallest);

					}

				}
			}
		}


		double maxC1Distance = 0;
		double maxC1DistanceX = 0;
		double maxC1DistanceY = 0;
		double maxC2Distance = 0;
		double maxC2DistanceX = 0;
		double maxC2DistanceY = 0;
		double maxC3Distance = 0;
		double maxC3DistanceX = 0;
		double maxC3DistanceY = 0;
		ArrayList<Double> C1DistList = new ArrayList<Double>();
		ArrayList<Double> C2DistList = new ArrayList<Double>();
		ArrayList<Double> C3DistList = new ArrayList<Double>();

		for(int i = 0; i < C1listdVal.size(); i++)
		{
			C1Xdist = Math.pow((C1X-C1listX.get(i)), 2);
			C1Ydist = Math.pow((C1Y-C1listY.get(i)), 2);
			C1dist = Math.sqrt(C1Xdist + C1Ydist);
			C1DistList.add(C1dist);

			if (C1dist > maxC1Distance)
			{
				maxC1Distance = C1dist;
				maxC1DistanceX = C1listX.get(i);
				maxC1DistanceY = C1listY.get(i);
			}
		}

		System.out.println("C1 cost: " +  maxC1Distance);
		//System.out.println(maxC1DistanceX);
		//System.out.println(maxC1DistanceY);

		for(int i = 0; i < C2listdVal.size(); i++)
		{
			C2Xdist = Math.pow((C2X-C2listX.get(i)), 2);
			C2Ydist = Math.pow((C2Y-C2listY.get(i)), 2);
			C2dist = Math.sqrt(C2Xdist + C2Ydist);
			C2DistList.add(C2dist);


			if (C2dist > maxC2Distance)
			{
				maxC2Distance = C2dist;
				maxC2DistanceX = C2listX.get(i);
				maxC2DistanceY = C2listY.get(i);
			}



		}

		System.out.println("C2 cost: " +  maxC2Distance);
		//System.out.println(maxC2DistanceX);
		//System.out.println(maxC2DistanceY);

		for(int i = 0; i < C3listdVal.size(); i++)
		{

			C3Xdist = Math.pow((C3X-C3listX.get(i)), 2);
			C3Ydist = Math.pow((C3Y-C3listY.get(i)), 2);
			C3dist = Math.sqrt(C3Xdist + C3Ydist);
			C3DistList.add(C3dist);

			if (C3dist > maxC1Distance)
			{
				maxC3Distance = C3dist;
				maxC3DistanceX = C3listX.get(i);
				maxC3DistanceY = C3listY.get(i);
			}



		}

		System.out.println("C3 cost: " +  maxC3Distance);
		//System.out.println(maxC3DistanceX);
		//System.out.println(maxC3DistanceY);
		
		double C1Sum = 0;
		for(int i = 0; i < C1DistList.size(); i++)
		{
			//C1Sum += C1DistList.get(i);
			C1Sum += (Math.pow(C1DistList.get(i), 2));
		}
		double C2Sum = 0;
		for(int i = 0; i < C2DistList.size(); i++)
		{
			//C2Sum += C2DistList.get(i);
			C2Sum += (Math.pow(C2DistList.get(i), 2));
		}
		double C3Sum = 0;
		/*for(int i = 0; i < C1DistList.size(); i++)
		{
			C3Sum += C3DistList.get(i);
		}*/
		
		System.out.println("C1 mean cost: " + C1Sum);
		System.out.println("C2 mean cost: " + C2Sum);
		System.out.println("C3 mean cost: " + C3Sum);
	}

}

