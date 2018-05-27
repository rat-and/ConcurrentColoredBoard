package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

/**
 * This is actually the most important class - it creates rectangles, then add them to HasMap object map with
 * the key of Position, bound them in Neighbourhoods (of 4 rectangles), displays them and finally creates threads
 * that either change their color randomly or take average of their neighbour colours.
 * @see SocialRectangle
 * @see Position
 */
public class ActionPanel extends Pane  {
    public int n=5, m=5, k=1000;
    public double p;
    public int height, width;
    public boolean halt = true;
    protected Random random = new Random();

    /**
     * An instance of HashMap which stores SocialRectangles and uses keys of Position class
     */
    HashMap<Position, SocialRectangle> map = new HashMap<Position, SocialRectangle>(1000);

    /**
     * It enables to perform treads asynchronously and thread-safely, sets all threads in queue
     */
    BlockingQueue<SocialRectangle> queue;

    /**
     * Handles adding objects to queue
     */
    ExecutorService threadPool;

    /**
     * Enables performing threads
     */
    Future future;

    public void setColumns(int n) { this.n = n;    }

    public void setRows(int m) {    this.m = m;    }

    public void setDelay(int k) {   this.k = k;    }

    public void setPoblty(double p) {   this.p = p;     }

    public void setCurrnetHeight(double height) { this.height = (int)height; }

    public void setCurrnetWidth(double width) { this.width = (int)width; }

    public void setHalt(boolean halt) { this.halt = halt;   }

    public void shutDownThreads() { this.threadPool.shutdownNow();  }

    public void drawGrid() {

        /**
         * Sets the size of threadPool size of a table + executing thread
         */
        threadPool = Executors.newFixedThreadPool((n*m)+1);

        /**
         * Creates tables of given size with rectangles, add them to HashMap object map and fill them with random colors,
         * at the end adds them to the ActionPane
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                SocialRectangle tmpRec = new SocialRectangle(((int)(double)width / (double)n)*i,((int)(double)height / (double)m)*j,width/n,height/m);
                tmpRec.setPosition(i,j);
                map.put(new Position(i,j), tmpRec);

                tmpRec.setFill(Color.rgb(
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)));

                this.getChildren().addAll(tmpRec);
            }
        }

        /**
         * Iterates through HashMap object map values and for each rectangle bound it with his neighbors (4)
         * It treats table as a torus meaning that edges are connected
         */
        for (SocialRectangle tmpRec : map.values()) {
            int i = tmpRec.getPosition().getRowNo();
            int j = tmpRec.getPosition().getColumnNo();

            if (i != 0 && i != n-1 && j != 0 && j != m-1) {
                tmpRec.setUpNeighbor(map.get(new Position(i-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,j-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if (i == 0  && j != 0 && j != m-1) {
                tmpRec.setUpNeighbor(map.get(new Position(n-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,j-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if( i == n-1 && j != 0 && j != m-1) {
                tmpRec.setUpNeighbor(map.get(new Position(i-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(0,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,j-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if(i != 0 && i != n-1 && j == 0 ) {
                tmpRec.setUpNeighbor(map.get(new Position(i-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,m-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if(i != 0 && i != n-1 && j == m-1 ) {
                tmpRec.setUpNeighbor(map.get(new Position(i-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,j-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,0)));
            } else if(i == 0  && j == 0) {
                tmpRec.setUpNeighbor(map.get(new Position(n-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,m-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if(i == n-1  && j == 0) {
                tmpRec.setUpNeighbor(map.get(new Position(i-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(0,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,m-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,j+1)));
            } else if(i == 0  && j == m-1) {
                tmpRec.setUpNeighbor(map.get(new Position(n-1,j)));
                tmpRec.setDownNeighbor(map.get(new Position(i+1,j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i,j-1)));
                tmpRec.setRightNeighbor(map.get(new Position(i,0)));
            } else if (i == n - 1 && j == m - 1) {
                tmpRec.setUpNeighbor(map.get(new Position(i - 1, j)));
                tmpRec.setDownNeighbor(map.get(new Position(0, j)));
                tmpRec.setLeftNeighbor(map.get(new Position(i, j - 1)));
                tmpRec.setRightNeighbor(map.get(new Position(i, 0)));
            } else {
                System.out.println("Error in defining Torus");
            }

            /**
             * Creates anonymous class of PuttingProcess and thus creates a thread of this rectangle
             */
            threadPool.submit(new PuttingProcess(tmpRec, queue));
        }

        /**
         * Executing Thread
         */
        future = threadPool.submit(new TakingProcess(queue));
    }

    /**
     * Constructs ActionPanel with default Background and a BlockingQueue instance
     */
    public ActionPanel() {
        super();
        this.setBackground(new Background(new BackgroundFill(Color.rgb(250, 250, 250), CornerRadii.EMPTY, Insets.EMPTY)));
        queue = new ArrayBlockingQueue<>(n);
    }

    /**
     * Handles the given rectangle - either changes its color randomly or set it as an
     * average of its neighbors
     */
    private class TakingProcess implements Callable {
        public SocialRectangle rectangle;
        public BlockingQueue<SocialRectangle> queue;
        private int whatToDo;
        private double r=0.0, g=0.0, b=0.0;
        private int red, green, blue;
        private Color colorOfUp, colorOfDown, colorOfLeft, colorOfRight;

        /**
         * Construct object of TakingProcess class and sets given queue to queue
         * @param queue
         */
        public TakingProcess(BlockingQueue<SocialRectangle> queue) {
            this.queue = queue;
        }

        @Override
        public Void call() {
            try {
                while (halt) {
                    rectangle = queue.take();
                    whatToDo = random.nextInt(101);

                    /*when event is within possibility range------------------------------------------*/
                    if (whatToDo <= p) {
//                        System.out.println("Losuje sobie nowy kolor");
                        rectangle.setFill(Color.rgb(
                                random.nextInt(256),
                                random.nextInt(256),
                                random.nextInt(256)));

                    }
                    /*when it's not-------------------------------------------------------------------*/
                    else {
//                        System.out.println("Biore kolor sredni moich sasiadow");

                        colorOfUp = (Color) rectangle.getUpNeighbor().getFill();
                        colorOfDown = (Color) rectangle.getDownNeighbor().getFill();
                        colorOfLeft = (Color) rectangle.getLeftNeighbor().getFill();
                        colorOfRight = (Color) rectangle.getRightNeighbor().getFill();

                        r = colorOfUp.getRed() + colorOfDown.getRed() + colorOfLeft.getRed() + colorOfRight.getRed();
                        g = colorOfUp.getGreen() + colorOfDown.getGreen() + colorOfLeft.getGreen() + colorOfRight.getGreen();
                        b = colorOfUp.getBlue() + colorOfDown.getBlue() + colorOfLeft.getBlue() + colorOfRight.getBlue();

                        red = (int) (r*255/4);
                        green = (int) (g*255/4);
                        blue = (int) (b * 255 / 4);

                        rectangle.setFill(Color.rgb(red, green, blue));

                    }
                    r=0.0;
                    g=0.0;
                    b=0.0;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }

    /**
     * Runs a thread for every single rectangle, sets is sleep time and put it to the queue waiting
     * for TakingProcess to take care of it
     */
    private class PuttingProcess implements Runnable {
        public SocialRectangle rectangle;
        public BlockingQueue<SocialRectangle> queue;

        public PuttingProcess(SocialRectangle rectangle, BlockingQueue<SocialRectangle> queue) {
            this.rectangle = rectangle;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(random.nextInt((int) 1.5 * k) + (int) 0.5 * k);
                    queue.put(rectangle);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


