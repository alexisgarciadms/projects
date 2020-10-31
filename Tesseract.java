package Tesseract;

/*Program used with WireframeApp.java. Creates a Tesseract, a 4 dimensional cube.
 * -Alexis Garcia*/

public class Tesseract {
    
	//A tesseract has 16 vertices. Each vertice is an array of 4 integers, such as 16 X 4 matrix. 
	//Each row is a coordinate of a vertice
	protected double[][]vertexData = { 
            {-1,-1,-1,-1},
            {-1,-1,-1, 1},
            {-1,-1, 1,-1},
            {-1,-1, 1, 1},
            {-1, 1,-1,-1},
            {-1, 1,-1, 1},
            {-1, 1, 1,-1},
            {-1, 1, 1, 1},
            { 1,-1,-1,-1},
            { 1,-1,-1, 1},
            { 1,-1, 1,-1},
            { 1,-1, 1, 1},
            { 1, 1,-1,-1},
            { 1, 1,-1, 1},
            { 1, 1, 1,-1},
            { 1, 1, 1, 1}
	};

	Matrix vertices = new Matrix(vertexData);    

	// A tessertact has 32 edges. Each edge is an array of 2 integers, such 32 X 2. 
	protected int[][] edges = {
        { 0, 1},
        { 0, 2},
        { 0, 4},
        { 0, 8},
        { 1, 3},
        { 1, 5},
        { 1, 9},
        { 2, 3},
        { 2, 6},
        { 2,10},
        { 3, 2},
        { 3, 7},
        { 3,11},
        { 4, 5},
        { 4, 6},
        { 4,12},
        { 5, 7},
        { 5,13},
        { 6, 7},
        { 6,14},
        { 7,15},
        { 8, 9},
        { 8,10},
        { 8,12},
        { 9,11},
        { 9,13},
        {10,11},
        {10,14},
        {11,15},
        {12,13},
        {12,14},
        {13,15},
        {14,15}
	};
}
