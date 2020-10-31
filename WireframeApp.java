package Tesseract;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* A basic drawing application that creates a rotating 4D Tesseract. It is made up of two classes. 
 *  The first handles  basic window events such as resizing and minimizing and another class handles
 *  the drawing.
 * 
 *  Code was also provided through the help of Professor Bert Wachsmuth at 
 *  Seton Hall University. -Alexis Garcia*/

public class WireframeApp extends JFrame implements Runnable
{
	// Inner class used as drawing canvas
    public class DrawingCanvas extends JPanel
    {
        // Overidden method to handle the actual drawing via a Graphics object
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            // Drawing edges (in blue)
            g.setColor(Color.BLUE);
            for (int edge = 0; edge < wireframe.edges.length; edge++) 
            {
                int vertex1 = wireframe.edges[edge][0];
                int vertex2 = wireframe.edges[edge][1];
                Vector node1 = verticestoDraw.getRowVector(vertex1);
                Vector node2 = verticestoDraw.getRowVector(vertex2);
                g.drawLine(	(int)node1.get(0), (int)node1.get(1), 
                			(int)node2.get(0), (int)node2.get(1));
            }
        }
    }
    // A field of type DrawingCanvas (our inner class)
    DrawingCanvas canvas;   
    Tesseract wireframe;
    // Matrix of vertices (row vectors) to draw (after scaling and shifting):
    Matrix verticestoDraw;
    // Matrix of vertices (row vectors) to be rotated continuously:
    Matrix verticesToRotate;
    // The general 4D Rotational matrix:
    Matrix rotMatrix;
    // A standard Java thread to run independently inside our app:
    Thread thread;

        
    // Constructor; called automatically when an object of this type is created
    public WireframeApp()
    {
        // calls superclass constructor to set the window title
        super("Wireframe Display App");
        
        // Initializing the various matrices
        // - the rotational matrix rotates by some small amount in any direction
        rotMatrix = Matrix.makeRotMatrix4D(.01,.01,.01,0.01,0.01,0.01);
        // - the actual Tesseract definition of vertices and edges
        wireframe = new Tesseract();
        // - the matrix of vertices to be rotated
        verticesToRotate = wireframe.vertices.mult(rotMatrix);
        // - the matrix of vertices to be scaled and shifted
        verticestoDraw = verticesToRotate.mult(100).add(200);

        // initializing the fields of this class
        canvas = new DrawingCanvas();
        
        // The thread to be linked to the run method of this class (defined below)
        // Then we call the thread’s start method, which will automatically call the
        // run method
        thread = new Thread(this);
        thread.start();
        
        // adding the drawing canvas to the layout of the frame/window   
        add(canvas);
        // setting the size of the window to 600 by 600 pixels
        setSize(600, 600);
       
        // defining what should happen if the user closes the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void run() {
        // Creating an infinite loop. Note that this ‘run’ method is not called
        // directly, but the thread’s start method executes it automatically. 
        boolean running = true;
        while (running) {
            // Rotating the matrix of vertices and setting the answer again to 
            // the verticesToRotate field. Thus, each new rotation will be
            // added to the previous rotation, which is exactly what we want.
            verticesToRotate = verticesToRotate.mult(rotMatrix);
            // After rotating the matrix we scale and shift it
            verticestoDraw = verticesToRotate.mult(100).add(200);
            // To get the canvas to redraw the (now rotated) matrix, we call
            // its repaint method, which will cause the paintComponent method
            // to execute automatically.
            canvas.repaint();
            try {
               // We put our thread to sleep for 50 ms to allow the rest of
               // the program to react to any user interaction
            	thread.sleep(50);
            }		
            catch (InterruptedException e) {
            	System.err.println("Error: " + e);
            }
        }
    }
    
    public static void main(String[] args)
    {
        // Creating a new object of type WireframeApp and making it visible 
        WireframeApp app = new WireframeApp();
        app.setVisible(true);
    }
}

