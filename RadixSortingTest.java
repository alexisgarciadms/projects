package RadixSort;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Testing the radix sorting algorithm

class RadixSortingTest {

	@Test
	void test() {
		
		Integer one = new Integer(1);
		Integer two = new Integer(2);
		Integer three = new Integer(3);
		Integer fiveHundred = new Integer(500);
		Integer[] A1 = {one, two, three, fiveHundred};
		Integer[] A2 = {fiveHundred, two, one, three};
		
		Integer a = new Integer(-1);
		Integer b = new Integer(-5);
		Integer c = new Integer(-8);
		Integer[] B1 = {c, a, b};
		Integer[] B2 = {c, b, a};
	
		Integer[] X = {-1, 2, -3, 1};
		Integer[] Y = {-610000, 400, 210000000, -1000};	
		Integer[] Z = {9, -10002, 424, 45};
	    
		//Tests numberLength
		assertEquals(RadixSorting.numberLength(-10, 1), 2);
		assertEquals(RadixSorting.numberLength(-100, 1), 3);
		assertEquals(RadixSorting.numberLength(1000, 1), 4);
	
		//Tests findMax
		assertEquals(RadixSorting.findMax(X, 0, 3), 3);
		assertEquals(RadixSorting.findMax(Y, 0, 3), 210000000);
		assertEquals(RadixSorting.findMax(Z, 0, 3), 10002);

		//Tests getDigit
		assertEquals(RadixSorting.getDigit(-100, 3), -1);
		assertEquals(RadixSorting.getDigit(-25, 2), -2);		
		assertEquals(RadixSorting.getDigit(97245, 5), 9);
		
		//Tests RadixSorting 
		RadixSorting.sort(A2);
 		assertArrayEquals(A2, A1);
 		
 		RadixSorting.sort(B1);
 		assertArrayEquals(B1, B2);

 		RadixSorting.sort(X);
 		assertArrayEquals(X, new Integer[] {-3, -1, 1, 2});

 		RadixSorting.sort(Y);
 		assertArrayEquals(Y, new Integer[] {-610000, -1000, 400, 210000000});
	}

}
