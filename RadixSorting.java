package RadixSort;
import java.lang.Math;
import java.util.ArrayList;

/* This class displays the radix sorting algorithm. The numbers in the bucket 
 * are compared until all the numbers are sorted. This class is used with Bucket.java
 * and IBucket.java -Alexis U Garcia */

public class RadixSorting implements IBuckets
{
	//Declaring 20 bucket objects
	Bucket<Integer> zero  = new Bucket<Integer>();
	Bucket<Integer> one = new Bucket<Integer>();
	Bucket<Integer> two  = new Bucket<Integer>();
	Bucket<Integer> three = new Bucket<Integer>();
	Bucket<Integer> four  = new Bucket<Integer>();
	Bucket<Integer> five = new Bucket<Integer>();
	Bucket<Integer> six  = new Bucket<Integer>();
	Bucket<Integer> seven = new Bucket<Integer>();
	Bucket<Integer> eight  = new Bucket<Integer>();
	Bucket<Integer> nine = new Bucket<Integer>();
	Bucket<Integer> ten = new Bucket<Integer>();
	Bucket<Integer> eleven = new Bucket<Integer>();
	Bucket<Integer> twelve = new Bucket<Integer>();
	Bucket<Integer> thirteen = new Bucket<Integer>();
	Bucket<Integer> fourteen = new Bucket<Integer>();
	Bucket<Integer> fifteen = new Bucket<Integer>();
	Bucket<Integer> sixteen = new Bucket<Integer>();
	Bucket<Integer> seventeen = new Bucket<Integer>();
	Bucket<Integer> eighteen = new Bucket<Integer>();
	Bucket<Integer> nineteen = new Bucket<Integer>();

	//Creating ArrayList of buckets
	static ArrayList<IBucket<Integer>> buckets = new ArrayList<IBucket<Integer>>();
	
	//Adding individual bucket to an ArrayList
	public RadixSorting()
	{
		buckets.add(zero);
		buckets.add(one);
		buckets.add(two);
		buckets.add(three);
		buckets.add(four);
		buckets.add(five);
		buckets.add(six);
		buckets.add(seven);
		buckets.add(eight);
		buckets.add(nine);
		buckets.add(ten);
		buckets.add(eleven);
		buckets.add(twelve);
		buckets.add(thirteen);
		buckets.add(fourteen);
		buckets.add(fifteen);
		buckets.add(sixteen);
		buckets.add(seventeen);
		buckets.add(eighteen);
		buckets.add(nineteen);
	}	
	
	//Purpose: Returns the length of given number 
	public static int numberLength(int n, int accum)
		{//numberLength(n, 1);
		 if (Math.abs(n) < 10) {return (accum);}
		 else {return (numberLength(Math.abs(n)/10, accum+1));}
		}
	
	//Purpose: to find the largest number in a vector  
	public static int findMax(Integer[] X, int low, int high)
		{if (low + 1 > high) {return X[low];}
		else {return Math.max(Math.abs(X[low]), Math.abs(findMax(X, low + 1, high)));}
		}
	
	//Purpose: to get a digit from a number 
	public static int getDigit(int num, int place)
		{return (int) ((num % Math.pow(10, place)) / Math.pow(10, (place-1)));}
	
	//Purpose: helper add function that adds elements to a bucket
	public void addTo(Integer element, int i)
		{buckets.get(i).add(element);}

	//Purpose: to put vector elements into buckets by place
	public static Integer[] bucketize(Integer[] V, RadixSorting z, int place)
	{int getDigit; //Instance variable getDigit of type int to be used
	 Integer num; //Instance variable num of type Integer to be used
	 for(int i = 0; i < V.length; i++)
	    {//Inv: elements in V[0..V[i]] are bucketized && i = V.length  
		 num = V[i]; //num is assigned to index  of V
		 getDigit = getDigit(num, place); //getDigit is assigned to getDigit function
	    //Inv: elements in V[0..V[i]-1] are bucketized && i < V.length	 
		z.addTo(num, getDigit + 9);
		//Inv: elements in V[0..V[i]] are bucketized && i = V.length
	    }
	 return (V);
	 //Termination: the loop begins at 0 and increments every time by 1 until if finally reaches 
	 //the length of the array and it finally terminates. 
	 }
	
	//Purpose: to empty bucket elements into a vector starting at i
	public static void debucketize(Integer[] A)
	{int i = 0;//Index of next element in A
	 //Inv: elements in buckets[0..bucket[ctr]] are debucketized && ctr = bucket.size() 
	 //&& A[i] = elements in vector 
	 for(int ctr = 0; ctr < buckets.size(); ctr++)
		{//Inv: elements in buckets[0..bucket[ctr]-1] are debucketized && ctr < bucket.size() 
		 //&& A[i]-1 = elements in vector
		  i += buckets.get(ctr).toArray(A, i);
		//Inv: elements in buckets[0..bucket[ctr]] are debucketized && ctr = bucket.size() 
		//&& A[i] = elements in vector	 
		}
	 //Termination: the counter begins at 0 and increments every time by 1 until it finally reaches 
	 //the size of the buckets and it finally terminates. 
	}

	//Purpose: to sort array by bucketizing and debucketizing
	public static Integer[] sort(Integer[] X)
	{RadixSorting z = new RadixSorting(); //Where elements will be stored from array 
	 int place = 0;
	//maxIndex is assigned to numberLength function
	 int maxIndex = numberLength(findMax(X ,0, X.length-1), 1); 
     while (!(place > maxIndex))
     {  //Inv: elements in X[0..X.length-1] are sorted && place<maxIndex  
    	bucketize(X, z, place);
    	//Inv: elements in X[0..length-1]-1 are sorted and place>maxIndex
        debucketize(X);
        //Inv:elements in X[0..length-1] are sorted and place>maxIndex
        place = place + 1;  
        //Inv: elements in X[0..X.length-1] are sorted && place<maxIndex  
     } 
     return (X); 
     //Termination: place starts at 0. It is looped every time until finally place will be smaller
     //than maxIndex in which it will then terminate. 
	}

	public void radixSorting() {
		// TODO Auto-generated method stub
		
	}
	
}
			
			