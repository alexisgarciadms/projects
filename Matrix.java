package Tesseract;

/* This is also used for the WireframeApp.java.                                         
 *  This class encapsulates a matrix and linear algebra methods.
 *  It has one field to contain the values of a matrix, as well
 *  as the methods described below. -Alexis Garcia */

public class Matrix {
	
	//Constant to specify approximity rather than using 0
	public static double EPSILON = 1E-8;
	
	// 2D array to store the values of a matrix
    private double[][] data = null;

    // Constructor to create empty matrix
    public Matrix(int nRows, int nCols) {
    	data = new double[nRows][nCols];
    }
    
    // Constructor to create matrix containing the given data
    public Matrix(double[][] data) {
    	this.data = data;
    }
    
    // Static method to create n-dimensional identity matrix
    public static Matrix makeIdentity(int n) {
    	Matrix identityMatrix = new Matrix(n, n);
    	for(int i = 0; i < n; i++)
    	{
    		identityMatrix.set(i, i, 1);
    	}
    	return identityMatrix;
    }
 
    // Static method to create n-dimensional (square) matrix of zeros 
    public static Matrix makeSquareMatrix(int n) {
    	return new Matrix(n, n);
    }
    
    //For following methods: @param angle the angle (in radians) to rotate
    
    //Makes 2D rotation matrix. When you multiply this matrix by a vector v that has an 
    //angle of beta with the x-axis, the result will be a vector of the same length but 
    //with angle alpha + beta with the x-axis. 
    public static Matrix makeRotMatrix2D(double angle) {
        Matrix matrix = makeSquareMatrix(2);
        matrix.set(0, 0, Math.cos(angle));
        matrix.set(0, 1, -Math.sin(angle));
        matrix.set(1, 0, Math.cos(angle));
        matrix.set(1, 1, Math.sin(angle));
        
        return matrix;
    }
    
    //3D rotational matrix that rotates a vector around the x-axis by the specified angle
    public static Matrix makeRotMatrix3DAroundX(double angle) {
        Matrix matrix = makeSquareMatrix(3);
		matrix.set(0, 0, 1.0);
        matrix.set(1, 1, Math.cos(angle));
        matrix.set(1, 2, -Math.sin(angle));
        matrix.set(2, 1,  Math.sin(angle));
        matrix.set(2, 2, Math.cos(angle));
            
        return matrix;
    }

     //3D rotational matrix that rotates a vector around the y-axis by the specified angle
    public static Matrix makeRotMatrix3DAroundY(double angle) {
        Matrix matrix = makeSquareMatrix(3);
        matrix.set(1, 1, 1.0);
        matrix.set(0, 0, Math.cos(angle));
        matrix.set(0, 2, Math.sin(angle));
        matrix.set(2, 0, -Math.sin(angle));
        matrix.set(2, 2, Math.cos(angle));
        return matrix;
    }

     //3D rotational matrix that rotates a vector around the z-axis by the specified angle
    public static Matrix makeRotMatrix3DAroundZ(double angle) {
        Matrix matrix = makeSquareMatrix(3);
        matrix.set(2, 2, 1.0);
        matrix.set(0, 0, Math.cos(angle));
        matrix.set(0, 1, -Math.sin(angle));
        matrix.set(1, 0,  Math.sin(angle));
        matrix.set(1, 1, Math.cos(angle));
        return matrix;
    }
    
 
    //Creates full 3D Rotation matrix. All angles speified in radians
    public static Matrix makeRotMatrix3D(double alpha, double beta, double gamma)
    {
        Matrix rotX = makeRotMatrix3DAroundX(alpha);
        Matrix rotY = makeRotMatrix3DAroundY(beta);
        Matrix rotZ = makeRotMatrix3DAroundZ(gamma);
        return rotX.mult(rotY.mult(rotZ));
    }

    //Creates a 4D rotational matrix around the xy-plane
    public static Matrix makeRotMatrix4DAroundXY(double angle) {
        Matrix matrix = makeSquareMatrix(4);
        matrix.set(0, 0, 1.0);
        matrix.set(1, 1, 1.0);
        matrix.set(2, 2, Math.cos(angle));
        matrix.set(2, 3, -Math.sin(angle));
        matrix.set(3, 2,  Math.sin(angle));
        matrix.set(3, 3, Math.cos(angle));
		return matrix;
	}

    //Creates a 4D rotational matrix around the xz-plane
    public static Matrix makeRotMatrix4DAroundXZ(double angle) {
        Matrix matrix = makeSquareMatrix(4);
		matrix.set(0, 0, 1.0);
		matrix.set(2, 2, 1.0);
		matrix.set(1, 1, Math.cos(angle));
		matrix.set(1, 3, Math.sin(angle));
		matrix.set(3, 1, -Math.sin(angle));
		matrix.set(3, 3, Math.cos(angle));
		return matrix;
    }
  
    //Creates a 4D rotational matrix around the xw-plane
    public static Matrix makeRotMatrix4DAroundXW(double angle) {
        Matrix matrix = makeSquareMatrix(4);
		matrix.set(0, 0, 1.0);
		matrix.set(3, 3, 1.0);
		matrix.set(1, 1, Math.cos(angle));
		matrix.set(1, 2, -Math.sin(angle));
		matrix.set(2, 1,  Math.sin(angle));
		matrix.set(2, 2, Math.cos(angle));
		return matrix;
	}

    //Creates a 4D rotational matrix around the yz-plane
    public static Matrix makeRotMatrix4DAroundYZ(double angle) {
        Matrix matrix = makeSquareMatrix(4);
		matrix.set(1, 1, 1.0);
		matrix.set(2, 2, 1.0);
		matrix.set(0, 0, Math.cos(angle));
		matrix.set(0, 3, Math.sin(angle));
		matrix.set(3, 0, -Math.sin(angle));
		matrix.set(3, 3, Math.cos(angle));
		return matrix;
        }

    //Creates a 4D rotational matrix around the yw-plane
    public static Matrix makeRotMatrix4DAroundYW(double angle) {
        Matrix matrix = makeSquareMatrix(4);
		matrix.set(1, 1, 1.0);
		matrix.set(3, 3, 1.0);
		matrix.set(0, 0, Math.cos(angle));
		matrix.set(0, 2, -Math.sin(angle));
		matrix.set(2, 0, Math.sin(angle));
		matrix.set(2, 2, Math.cos(angle));
		return matrix;
    }

    //Creates a 4D rotational matrix around the zw-plane
    public static Matrix makeRotMatrix4DAroundZW(double angle) {
        Matrix matrix = makeSquareMatrix(4);
		matrix.set(2, 2, 1.0);
		matrix.set(3, 3, 1.0);
		matrix.set(0, 0, Math.cos(angle));
		matrix.set(0, 1, Math.sin(angle));
		matrix.set(1, 0, -Math.sin(angle));
		matrix.set(1, 1, Math.cos(angle));
        return matrix;
    }
    
    /**
     * Create a full 4D rotational matrix. All angles must be in radians.
     * @param xy angle to rotate around xy-plane
     * @param xz angle to rotate around xz-plane
     * @param xw angle to rotate around xw-plane
     * @param yz angle to rotate around yz-plane
     * @param yw angle to rotate around yw-plane
     * @param zw angle to rotate around zw-plane
     * @return the full 4D rotational matrix
     */
    public static Matrix makeRotMatrix4D(double xy, double xz, double xw,
                                         double yz, double yw, double zw)
    {
        Matrix rotXY = makeRotMatrix4DAroundXY(xy);
        Matrix rotXZ = makeRotMatrix4DAroundXZ(xz);
        Matrix rotXW = makeRotMatrix4DAroundXW(xw);
        Matrix rotYZ = makeRotMatrix4DAroundYZ(yz);
        Matrix rotYW = makeRotMatrix4DAroundYW(yw);
        Matrix rotZW = makeRotMatrix4DAroundZW(zw);
        
        return rotXY.mult(rotXZ.mult(rotXW.mult(rotYZ.mult(rotYW.mult(rotZW)))));
    }
    
    // Static method to create n-dimensional (square) matrix of random 
    // values between 0 and c
    public static Matrix makeRandomSquareMatrix(int n, double c) {   	
    	Matrix randomMatrix = makeSquareMatrix(n);    	
    	for(int i = 0; i < n; i++)
    	{
    		for(int j = 0; j < n; j++)
    		{
    			randomMatrix.set(i, j, c * Math.random());
    		}
    	}
    	return randomMatrix;
    }
    
    //Makes square n by n matrix with random integer entries between 1 and max
    public static Matrix makeRandomSquareMatrix(int n, int max) {
    	Matrix randomMatrix = makeSquareMatrix(n);
    	for (int i = 0; i < n; i++)
    	{
    		for(int j = 0; j < n; j++)
    		{
    			randomMatrix.set(i, j, (int)(max * Math.random()) + 1);
    		}
       	}
    	return randomMatrix;
    }

    // Returns number of rows 
    public int getNRows() {
    	return data.length;
    }
    
    // Returns number of columns
    public int getNCols() {
    	return data[0].length;
    }
    
    //Returns EPSILON value
    public static double getEpsilon() {
    	return EPSILON;
    }
    
    //Set new EPSILON value
    public static void setEpsilon(double n) {
    	EPSILON = n;
    }
  
    // Sets the entry at position (row, col) to c
    public void set(int row, int col, double c) {
    	data[row][col] = c;
    }
    
    //Sets the entire matrix to the input
    public void set(double data[][]) {
        this.data = data;
    }

    // Returns the entry at position (row, col)
    public double get(int row, int col) {
     	return data[row][col];
    }
    
    //Returns the entire data of the matrix 
    public double[][] get(){
        return data;
    }
    
    //Returns the given row of the matrix as a vector
	public Vector getRowVector(int row) {
        double[] data = new double[getNCols()];
        for(int col = 0; col < getNCols(); col++)
            data[col] = get(row, col);
        return new Vector(data);
    }
	
    //Returns the given column of the matrix as a vector
    public Vector getColVector(int col) {
        double[] data = new double[getNRows()];
        for(int row = 0; row < getNRows(); row++)
            data[row] = get(row, col);
        return new Vector(data);
    }
    
    //Returns true if all elements of matrix are less than EPSILON
    public boolean isZeroApprox()
    {
        for (int row=0; row < getNRows(); row++)
            for (int col=0; col < getNCols(); col++)
            {
                if (Math.abs(get(row,col)) > EPSILON)
                    return false;
            } 
        return true;
    }
	
    // Returns product of current matrix and vector v if possible
	public Vector mult(Vector v) {
		if (v.getDim() != getNCols())
			throw new Error("Incompatible for multiplying matrix and vector");
		
    	Vector result = new Vector(getNRows());
    	for (int row = 0; row < getNRows(); row++)
    	{
    		result.set(row, v.dot(this.getRowVector(row)));
    	}
    	return result;
	}
    
    // Returns the negative of the current matrix
    public Matrix negative() {
    	return mult(-1.0);
    }
    
    // Returns the transpose of current matrix
    public Matrix transpose() {
    	Matrix a = new Matrix(getNRows(), getNCols());
    	for (int i = 0; i < getNRows(); i++)
    	{
    		for(int j = 0; j < getNCols(); j++)
    		{
    			a.set(i, j, get(i, j));
    		}
    	}
    	return a;
    }
    
    // Returns a copy of the current matrix
    public Matrix copy() {
    	Matrix a = new Matrix(getNRows(), getNCols());
    	a.data = this.data;
    	for (int i = 0; i < getNRows(); i++)
    	{
    		for (int j = 0; j < getNCols(); j++)
    		{
    			a.set(i, j, get(i, j));
    		}
    	}
    	return a;
    }
    
    //Returns true if the current matrix is equal to the matrix B
    public boolean equals(Matrix B) {
    	//Checks dimmensions of both matrix
    	if ((B.data.length != this.data.length) || (B.data[0].length != this.data[0].length)) {
    		return false;}  		
    	
    	//Checks that the data inside the matrix equals
    	for(int i = 0; i < data.length; i++)
    	{
    		for(int j = 0; j < data[0].length; j++)
    		{
    			if (B.data[i][j] != this.data[i][j]){
    				return false;}
    		}
    	}
    	//Else matrices must be true
    	return true;		
    }
    
    //Returns true if current matrix is the zero matrix
    public boolean isZero() {
    	for(int i = 0; i < getNRows(); i++)
    	{
    		for(int j = 0; j < getNCols(); j++)
    		{
    			if (Math.abs(get(i, j)) != EPSILON) {
    				return false;}
    		}
    	}
    	return true;
    }
    
    //Returns true if the matrix is the zero matrix 
    public boolean isZeroMatrix()
    {
        for (int row = 0; row < getNRows(); row++)
            for (int col = 0; col < getNCols(); col++)
            {
                if (get(row,col) != 0.0)
                    return false;
            } 
        return true;
     }
    
    // Returns true if current matrix is a square matrix
    public boolean isSquare() {
    	return (getNRows() == getNCols());
    }
    
    // Returns true if current matrix is symmetric
    public boolean isSymmetric() {
    	if (!isSquare()) {
    		return false;
    	}
    	return this.equals(this.transpose());
    }
    
    // Returns true if current matrix is skew symmetric
    public boolean isSkewSymmetric() {
    	if (!isSquare()) {
    		return false;
    	}
    	return (this.isSquare()) && (this.transpose().equals(this.negative()));
    }
    
    // Multiplies matrix by scalar c and returns product
    public Matrix mult(double c) {
    	Matrix mult = new Matrix(getNRows(), getNCols());
    	
    	for(int i = 0; i < getNRows(); i++)
    	{
    		for(int j = 0; j < getNCols(); j++)
    		{
    			mult.set(i, j, c * get(i, j));
    		}
    	}
    	return mult;
    }
   
    // Returns product of current matrix and matrix B if possible
    public Matrix mult(Matrix B) {
    	if (getNCols() != B.getNRows()) {
    		throw new Error("Error with dimmensions");}
    	Matrix newMatrix = new Matrix(getNRows(), B.getNCols());
    	for (int i = 0; i < getNRows(); i++)
    	{
    		for (int j = 0; j < getNCols(); j ++)
    		{		
    			newMatrix.set(i, j, this.getRowVector(i).dot(B.getColVector(j)));
    		}
    	}
    	return newMatrix;
    }
    
    // Returns the sum of current matrix and matrix B if possible
    public Matrix add(Matrix B) {
    	if ((getNRows()!= B.getNRows()) || (getNCols() != B.getNCols())) {
    		throw new Error("Error: dimmensions do not equal");
    	}
    	Matrix newMatrix = new Matrix(getNRows(), getNCols());
    	for (int i = 0; i < getNRows(); i++){
    		{
    			for (int j = 0; j < getNCols(); j++)
    			{
    				newMatrix.set(j, j, get(i, j) + B.get(i, j));
    			}
    		}
    	}
    	return newMatrix;
    }
    
    //Adds a constant to every element of the current matrix
    public Matrix add(double c)
    {
        Matrix sum = new Matrix(getNRows(), getNCols());
        for (int row = 0; row < getNRows(); row++)
            for (int col = 0; col < getNCols(); col++)
                sum.set(row, col, get(row, col) + c);
        return sum;
    }
    
    // Returns difference of current matrix and matrix B if possible
    public Matrix subtr(Matrix B) {
    	if ((getNRows() != B.getNRows()) || (getNCols() != B.getNCols())) {
    		new Error("Error: dimmensions do not equal");
    	}
    	
    	return this.add(B.negative());
    }
    
    // Returns String representation of current matrix
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int row = 0; row < getNRows(); row++)
        {
            for (int col = 0; col < getNCols(); col++)
                sb.append(get(row,col) + "\t");
            	sb.append("\n");
        }
        return sb.toString();
    }
    
    //Swaps the two rows given
    private void swapRows(Matrix A, int row1, int row2) {
    	if (row1 == row2) 
    		throw new Error("can't swap a row with itself");
    	
    	for (int col = 0; col < A.getNCols(); col++)
    	{
    		double temp = A.get(row1, col);
    		A.set(row1, col, A.get(row2, col));
    		A.set(row2, col, temp);
    	}
    }
	//Multiplies row by constant c.
    private void scalarMult(Matrix A, int row, double c) {
    	if (c == 0.0) 
    		throw new Error("can't multiply a row with zero");
    	
    	for (int i = 0; i < getNCols(); i++)
    	{
    		A.set(row, i, A.get(row, i) * c);
    	}
    }
    
	//Adds row1 + c*row2 and replaces row1 by the sum
    private void addReplace(Matrix A, int row1, double c, int row2) {
    	if (row1 == row2) 
    		throw new Error("can't add a row with itself");
    	
    	for (int col = 0; col < getNCols(); col++) {
    		A.set(row1, col, A.get(row1, col) + c*A.get(row2, col));
    	}
    }
    
	//Reduce the matrix to row echelon form
    private void reduceColumnBelow(Matrix A, int col) {
    	if (A.get(col, col) != 0)
    	{
    		scalarMult(A, col, 1/A.get(col, col));
    		for (int row = col + 1; row < getNRows(); row++)
    		{
    			addReplace(A, row, -A.get(row, col), col);
    		}
    	}
    }
    
	//To completely reduce the matrix
    private void reduceColumnAbove(Matrix A, int col) {
    	for (int row = col - 1; row >= 0; row--)
    	{
    		addReplace(A, row, -A.get(row, col), col);
    	}
    }
    
	//Checking if main diagonal entry is zero
    private void checkForZero(Matrix A, int col) {
    	if (A.get(col, col) == 0)
    	{
    		for (int row = col; row < getNRows(); row++)
    		{
    			if (A.get(row, col) != 0)
    			{
    				swapRows(A, row, col);
    				return;
    			}
    		}
    		throw new Error("This matrix has no inverse");
    	}
    }
    
    //Reduce the given matrix
    private void reduce(Matrix A) {
    	for (int col = 0; col < getNRows(); col++)
    	{
    		checkForZero(A, col);
    		reduceColumnBelow(A, col);
    	}
    	
    	for (int col = A.getNRows()-1; col >= 0; col--)
    	{
    		reduceColumnAbove(A, col);
    	}
    }
    
    public Matrix inverse() {
    	//setting up n x (2n) matrix and coying the exsiting matrix into the first n
    	//columns and setting diagnal of the 2nd n x n matrix to 1's
    	if (!isSquare()) throw new Error("Not a square matrix; can't find inverse");
    	Matrix expanded = new Matrix(getNRows(), 2 * getNCols());
    	for (int row = 0; row < getNRows(); row++)
    	{
    		for (int col = 0; col < getNCols(); col++)
    		{
    			expanded.set(row, col, get(row, col));
    			if (row == col) {
    				expanded.set(row, col + getNCols(), 1.0);
    			}
    		}
    	}
    	
    	//Reducing the expanded Matrix by applying elementary row operations.
    	reduce(expanded);
    	Matrix inv = new Matrix(getNRows(), getNCols());
    	for (int row = 0; row < getNRows(); row++)
    	{
    		for (int col = 0; col < getNCols(); col++)
    		{
    			inv.set(row, col, expanded.get(row, col + getNCols()));
    		}
    	}
    	return inv;
    }
    
    private void reduceColumnBelowLU(Matrix A, int col, Matrix L) {
    	if (A.get(col,col) != 0.0)
    	{
    		for (int row = col+1; row < getNRows(); row++)
            {
                double c = A.get(row, col)/A.get(col, col);
                addReplace(A, row, -c, col);
                L.set(row, col, c);
            }
        }
        else
            throw new Error("this matrix has no LU factorization");
    }
    
    public Matrix factorLU()
    {
        // create a copy of the current matrix
        Matrix U = copy();
        // create a square matrix with zeros as entries
        Matrix L = makeSquareMatrix(getNRows());
        // Go through all columns of the matrix from left to right
        for (int col = 0; col < U.getNCols(); col++)
        {
            // reduce the col to feature 0’s below the main diagonal
            reduceColumnBelowLU(U, col, L);
        }
        return L.add(U);
    }
    
    //Finding the determinant of a square matrix
    public double det() {
    	if (!this.isSquare()) throw new Error("Not a square matrix; can't find determinant");
    	
    	Matrix A = copy();
    	for (int i = 0; i < getNRows(); i++)
    	{
    		try {checkForZero(A, i);}
    		catch (Error e)
    		{
    			return 0.0;
    		}
    		if (A.get(i, i) != 0.0)
            {
                for (int row = i + 1; row < getNRows(); row++)
                	{addReplace(A, row, -A.get(row, i)/A.get(i, i), i);}
            }
            else
                return 0.0;
    	}
    	double prod = 1.0;
        for (int i = 0; i < getNCols(); i++)
            prod *= A.get(i,i);
        return prod;
    }
    
    // Returns matrix L from matrix returned from factorLU
    public static Matrix getL(Matrix LU) {
        Matrix L = makeIdentity(LU.getNCols());
        for (int row = 0; row < LU.getNRows(); row++)
            for (int col = 0; col < row; col++)
                L.set(row,  col,  LU.get(row, col));
        return L;
    }
    
    // Returns matrix U from matrix returned from factorLU
    public static Matrix getU(Matrix LU) {
        Matrix U = makeIdentity(LU.getNCols());
        for (int row = 0; row < LU.getNRows(); row++)
            for (int col = row; col < LU.getNCols(); col++)
                U.set(row,  col,  LU.get(row, col));
        return U;
    }   
    
    //Finding the Eigenvalues of a 2 x 2 matrix
    public double[] getEigenvalues() {
    	if (!isSquare())
    		throw new Error("non-square matrix does not have eigenvalues");
    	else if (getNRows() != 2)
    		throw new Error("Eigenevaues only implemented for 2 x 2 matrices");
    	
    	//Since we are working with a 2x2, this would work fine
    	double a = get(0, 0);
    	double b = get(0, 1);
    	double c = get(1, 0);
    	double d = get(1, 1); 
    	
    	double sqrt = a*a + 4*b*c - 2*a*d + d*d;
    	if (sqrt < 0)
    		throw new Error("Eigenvalues not real");
    	
    	//Storing the result
    	double [] result = new double[2];
    	result[1] = 0.5 * (a + d + Math.sqrt(sqrt));
    	result[2] = 0.5 * (a + d + Math.sqrt(sqrt));
    	
    	return result;
    }
    
    
	public static void main(String[] args) {
		
		Matrix M = makeRandomSquareMatrix(6, 9.0);
		System.out.println(M);
		System.out.println(M.inverse());
		System.out.println(M.mult(M.inverse()));
		System.out.println(M.mult(M.inverse()).subtr(M.inverse().mult(M)).isZero());
		
	}
     

}
