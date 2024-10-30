import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.util.Random;



public class ProjectileSimulation extends JPanel implements ActionListener {

    private final Timer timer;

    private static final int PANEL_SIZE = 800;      // This size of the panel

    private static final int MAX_CANNONBALL_SIZE = 50;      // The size of the cannonball

    private double x, y;                    // Current position of the projectile

    private double vx, vy;                  // Velocity components

    private int cannonballSize;             // We show an example of randomizing the size here

    private static Random random = new Random();

    private boolean isLaunched = false;






    // If your ball never falls back down your gravity is too small for each frame

    // Hint: Think about what your initial vx is going to be in magnitude/size and

    // you will want to use some value that can reduce this to zero for the likely number of

    // frames you are going to be showing

    // Set the IMPULSE to a value of for the initial velocity




    private static final double GRAVITY = 0.5; // Gravity constant

    private static final double IMPULSE = 0.03; // Amount of initial thrust/impulse on click



    public ProjectileSimulation() {

        timer = new Timer(20, this); // Timer for animation speed

        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));

        resetPosition();



        addMouseListener(new MouseAdapter() {

            @Override

            public void mousePressed(MouseEvent e) {

                // Reset the position

                resetPosition();

                launchProjectile(e.getX(), e.getY());

            }

        });

       

        timer.start();

    }



    private void resetPosition()

    {

        // TODO: What are the values that are possible for our cannonballSize ?
        // Answer: 

        // Fill in your answer here:

        cannonballSize = random.nextInt(MAX_CANNONBALL_SIZE -10) +10;

        x = 0;      

        y =  PANEL_SIZE-cannonballSize;      

    }



    private void launchProjectile(int mouseX, int mouseY) {




        // to get this to fire with a reasonable velocity

        // i.e. it should move but not shoot off into the 

        // wild yonder immediately. 

        // Think about what the following formulas will set the

        // value of vx and vy to and then how you will use these

        // below each time you update the position.



        vx = (mouseX - x) * IMPULSE;

        vy = (mouseY - y) * IMPULSE;



        isLaunched = true;

    }



    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.RED);

        g.fillOval((int) x, (int) y, cannonballSize, cannonballSize); // Draw the projectile as a red oval

    }



    @Override

    public void actionPerformed(ActionEvent e) {

        if (isLaunched) {




            // based on velocity and gravity (x,y & vy need updating)
            x = this.x+vx;
            y =  this.y+vy;
            vy = this.vy+GRAVITY;


            // Check if the projectile hits the ground

            if (y >= getHeight() - 20) { // Assuming 20 is the projectile diameter

                y = getHeight() - 20;

                vy = 0; // Stop vertical movement

                vx = 0; // Stop horizontal movement

                isLaunched = false;

            }

        }

        repaint();

    }



    public static void main(String[] args) {

        JFrame frame = new JFrame("Projectile Simulation");

        ProjectileSimulation simulation = new ProjectileSimulation();

        frame.add(simulation);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

}
