import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
// import java.awt.event.*;
/**
 * Title:        MazeDepthFirstSearch<p>
 * Description:  Demo program for Java AI Programming<p>
 * Copyright:    Copyright (c) Mark Watson, Released under Open Source Artistic License<p>
 * Company:      Mark Watson Associates<p>
 * @author Mark Watson
 * @version 1.0
 */

public class MazeFirstSearch extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private final int REPETICIONES = 20;
	
	JPanel jPanel1 = new JPanel();
    DepthFirstSearchEngine currentDepthSearchEngine = null;
    DepthFirstSearchEngine currentDepthSearchEngineOptimizado = null;
    BreadthFirstSearchEngine currentBreadthSearchEngine = null;
        
    int [] listTotalBusquedas = new int[REPETICIONES];
    int [] listMaxDepth = new int[REPETICIONES];
    int [] listTotalBusquedasOptimizado = new int[REPETICIONES];
    int [] listMaxDepthOptimizado = new int[REPETICIONES];
    int [] listTotalBusquedasBreadth = new int[REPETICIONES];
    int [] listMaxDepthBreadth = new int[REPETICIONES];


    public MazeFirstSearch() {
        long tInicio = System.currentTimeMillis();
    	try {
          jbInit();
        } catch (Exception e) {
          System.out.println("GUI initilization error: " + e);
        }
    	
    	//currentDepthSearchEngine = new DepthFirstSearchEngine(20, 20, false);
        
        for (int i=0;i<REPETICIONES;i++) {
        	currentDepthSearchEngine = new DepthFirstSearchEngine(20, 20, false);
        	listTotalBusquedas[i] = currentDepthSearchEngine.getTotalBusquedas();
        	listMaxDepth[i] = currentDepthSearchEngine.getMaxDepth();
        }
        
        for (int i=0;i<REPETICIONES;i++) {
        	currentDepthSearchEngineOptimizado = new DepthFirstSearchEngine(20, 20, true);
        	listTotalBusquedasOptimizado[i] = currentDepthSearchEngineOptimizado.getTotalBusquedas();
        	listMaxDepthOptimizado[i] = currentDepthSearchEngineOptimizado.getMaxDepth();
        }
        
        for (int i=0;i<REPETICIONES;i++) {
        	currentBreadthSearchEngine = new BreadthFirstSearchEngine(20, 20);
        	listTotalBusquedasBreadth[i] = currentBreadthSearchEngine.getTotalBusquedas();
        	listMaxDepthBreadth[i] = currentBreadthSearchEngine.getMaxDepth();
        }
        
        int numElementos=0;
        int totalListMaxDepth=0;
        int totalListTotalBusquedas=0;
        for (int i=0;i<REPETICIONES;i++) {
        	if (listMaxDepth[i]!=0) {
        		numElementos=numElementos+1;
        		totalListMaxDepth=totalListMaxDepth+listMaxDepth[i];
        		totalListTotalBusquedas=totalListTotalBusquedas+listTotalBusquedas[i];
        	}
        }
        System.out.println("Busqueda Depth no Optimizada");
        System.out.println("Media MaxDepth: " + totalListMaxDepth/numElementos);
        System.out.println("Media Total Busquedas: " + totalListTotalBusquedas/numElementos);
        
        numElementos=0;
        totalListMaxDepth=0;
        totalListTotalBusquedas=0;
        for (int i=0;i<REPETICIONES;i++) {
        	if (listMaxDepthOptimizado[i]!=0) {
        		numElementos=numElementos+1;
        		totalListMaxDepth=totalListMaxDepth+listMaxDepthOptimizado[i];
        		totalListTotalBusquedas=totalListTotalBusquedas+listTotalBusquedasOptimizado[i];
        	}
        }
        
        System.out.println("Busqueda Depth Optimizada");
        System.out.println("Media MaxDepth: " + totalListMaxDepth/numElementos);
        System.out.println("Media Total Busquedas: " + totalListTotalBusquedas/numElementos);
        
        numElementos=0;
        totalListMaxDepth=0;
        totalListTotalBusquedas=0;
        for (int i=0;i<REPETICIONES;i++) {
        	if (listMaxDepthBreadth[i]!=0) {
        		numElementos=numElementos+1;
        		totalListMaxDepth=totalListMaxDepth+listMaxDepthBreadth[i];
        		totalListTotalBusquedas=totalListTotalBusquedas+listTotalBusquedasBreadth[i];
        	}
        }
        
        System.out.println("Busqueda Breadth");
        System.out.println("Media MaxDepth: " + totalListMaxDepth/numElementos);
        System.out.println("Media Total Busquedas: " + totalListTotalBusquedas/numElementos);
        
        repaint();
        long tFinal = System.currentTimeMillis();
        System.out.println("Tiempo: "+(tFinal-tInicio)+"ms.");
    }

    public void paint(Graphics g_unused) {
        if (currentBreadthSearchEngine == null) return;
        Maze maze = currentBreadthSearchEngine.getMaze();
        
    	/*if (currentDepthSearchEngine == null) return;
        Maze maze = currentDepthSearchEngine.getMaze();*/
        int width = maze.getWidth();
        int height = maze.getHeight();
        System.out.println("Size of current maze: " + width + " by " + height);
        Graphics g = jPanel1.getGraphics();
        BufferedImage image = new BufferedImage(640, 640, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        //g2.fillRect(0, 0, 320, 320);
        g2.fillRect(0, 0, 640, 640);
        g2.setColor(Color.black);
        for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
                short val = maze.getValue(x,y);
                if ( val == Maze.OBSTICLE) {
                    g2.setColor(Color.lightGray);
                    g2.fillRect(6 + x * 29, 3 + y * 29, 29, 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.START_LOC_VALUE || val == 1) {
                    g2.setColor(Color.blue);
                    g2.drawString("S", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else if (val == Maze.GOAL_LOC_VALUE) {
                    g2.setColor(Color.red);
                    g2.drawString("G", 16 + x * 29, 19 + y * 29);
                    g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                } else {
                	g2.setColor(Color.black);
                	g2.drawRect(6 + x * 29, 3 + y * 29, 29, 29);
                }
            }
        }
        // redraw the path in black:
        g2.setColor(Color.black);
        Dimension [] path = currentBreadthSearchEngine.getPath();
        //Dimension [] path = currentDepthSearchEngine.getPath();
        for (int i=1; i< path.length; i++) {
          int x = path[i].width;
          int y = path[i].height;
          short val = maze.getValue(x,y);
          //g2.drawString("0" + val, 25 + x * 28, 19 + y * 29);
          g2.drawString(Integer.toString(val), 25 + x * 28, 19 + y * 29);
        }
        g.drawImage(image, 30, 40, 640, 640, null);
    }

    public static void main(String[] args) {
        new MazeFirstSearch();
    }

    private void jbInit() throws Exception {
        this.setContentPane(jPanel1);
        this.setCursor(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("MazeBreadthFirstSearch");
        this.getContentPane().setLayout(null);
        jPanel1.setBackground(Color.white);
        jPanel1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
        jPanel1.setDoubleBuffered(false);
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(null);
        //this.setSize(370, 420);
        this.setSize(740, 840);
        this.setVisible(true);
    }
}


