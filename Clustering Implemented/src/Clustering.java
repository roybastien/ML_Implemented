
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;




public class Clustering {

//	public static void main(String[] args) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		makeClusters("C1.txt");
//	}

	public static void makeClusters (String filename) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(filename));
		ArrayList<Point> pointList = new ArrayList<Point>();
		ArrayList<Cluster> clusterSet = new ArrayList<Cluster>();
		
		while (scan.hasNextLine()){
			int index = scan.nextInt();
			double x = scan.nextDouble();
			double y = scan.nextDouble();	
			Point p = new Point(index, x, y);
			pointList.add(p);
			Cluster c = new Cluster(p);
			c.clusterID = index;
			clusterSet.add(c);
		}
		
		
		while (clusterSet.size() > 4){	
			Cluster temp = new Cluster();
			
//			int[] close = findTwoClosestClustersSingleLink(clusterSet);
//			int[] close = findTwoClosestClustersCompleteLink(clusterSet);
			int[] close = findTwoClosestClustersMeanLink(clusterSet);
			
			for (int i = 0; i < clusterSet.size(); i++){
				if (clusterSet.get(i).clusterID == close[0]){
					temp.mergeClusters(clusterSet.get(i));
					clusterSet.remove(i);
					break;
				}
			}
			
			for (int i = 0; i < clusterSet.size(); i++){
				if (clusterSet.get(i).clusterID == close[1]){
					temp.mergeClusters(clusterSet.get(i));
					clusterSet.remove(i);
					break;
				}
			}
			
			clusterSet.add(temp);
		}
		
		for(Cluster fin : clusterSet){
			for(Point p : fin.clusterPointList){
			System.out.println("The remaining cluster " + fin.clusterID + " has point " + p.index + " with a size of " + fin.clusterPointList.size());
			}
		}	
	}
	
	public static double[] averageCluster(Cluster cluster){
		double [] avg = new double [2];
		double sumX = 0;
		double sumY = 0;
		double count = cluster.clusterPointList.size();
		for (Point p : cluster.clusterPointList){
			sumX += p.x;
			sumY += p.y;
		}
		avg[0] = (sumX/count);
		avg[1] = (sumY/count);
		return avg;
	}
	
	public static int[] findTwoClosestClustersCompleteLink(ArrayList<Cluster> set){
		double minDist = Integer.MAX_VALUE;
		int[] closestID = new int[2];
		for (Cluster out : set){
			for (Cluster in : set){
				double maxDistBetweenTheseTwo = Integer.MIN_VALUE;
				for(Point outp : out.clusterPointList){
					for(Point inp : in.clusterPointList){			
						if(inp.getDistance(outp) > maxDistBetweenTheseTwo && inp.getDistance(outp) != 0 && out.clusterID != in.clusterID){
							maxDistBetweenTheseTwo = inp.getDistance(outp);
							if (maxDistBetweenTheseTwo < minDist){	
								minDist = maxDistBetweenTheseTwo;
								closestID[0] = in.clusterID;
								closestID[1] = out.clusterID;
							}	
						}
					}
				}
			}
		}

		return closestID;
	}
	

	
	public static int[] findTwoClosestClustersMeanLink(ArrayList<Cluster> set){
		double minDist = Integer.MAX_VALUE;
		int[] closestID = new int[2];
		for (Cluster out : set){
			double[] avgOut = averageCluster(out);
			for (Cluster in : set){
				double[] avgIn = averageCluster(in);
				double dist = getDistanceArr(avgOut, avgIn);
					if(dist < minDist && dist != 0 && out.clusterID != in.clusterID){
						minDist = dist;
						closestID[0] = in.clusterID;
						closestID[1] = out.clusterID;
					}	
				}
			}	
		
		return closestID;
	}
	
	public static int[] findTwoClosestClustersSingleLink(ArrayList<Cluster> set){
		double minDist = Integer.MAX_VALUE;
		int[] closestID = new int[2];
		for (Cluster out : set){
			for (Cluster in : set){
				for(Point outp : out.clusterPointList){
					for(Point inp : in.clusterPointList){
						if(inp.getDistance(outp) < minDist && inp.getDistance(outp) != 0 && out.clusterID != in.clusterID){
							minDist = inp.getDistance(outp);
							closestID[0] = in.clusterID;
							closestID[1] = out.clusterID;
						}
					}
				}
			}
		}
		
		return closestID;
	}

	public static double getDistanceArr(double[] one, double[] two){
		double xs = Math.pow((two[0] - one[0]), 2);
		double ys = Math.pow((two[1] - one[1]), 2);
		double distance = Math.sqrt(xs + ys);
		return distance;
	}
}

class Cluster{
	
	public int clusterID;
	public Set<Integer> IDSet = new HashSet<Integer>();
	public Set<Point> clusterPointList = new HashSet<Point>();
	
	public Cluster(Point p){
		clusterPointList.add(p);
	}
	
	public Cluster(){
	
	}
	
	public void mergeClusters(Cluster c){
		clusterID = c.clusterID;
		for (Point p : c.clusterPointList){
			clusterPointList.add(p);
		}
		IDSet.add(c.clusterID);
	}
}

class Point{
	int index;
	double x;
	double y;

	
	public Point (int i, double x, double y){
		this.index = i;
		this.x = x;
		this.y = y;
	}
	
	public double getDistance (Point p2){
		double xs = Math.pow((p2.x - x), 2);
		double ys = Math.pow((p2.y - y), 2);
		double distance = Math.sqrt(xs + ys);
//		System.out.println("Distance: " + distance + " Point A: "+ index + " Point B: " + p2.index);
		return distance;
	}
}

