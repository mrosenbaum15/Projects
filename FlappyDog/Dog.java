import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.Image;
import javax.swing.*;
import sun.audio.*;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import java.io.*;
import java.awt.event.*;
/**
 * Dog.java  
 *
 * @author: Matthew Rosenbaum and Casey Borovsky
 * Assignment #: Fourth Quarter Project
 * 
 * Brief Program Description: This class creates the
 * blueprint for th
 * 
 * 
 * 
 * CITATIONS:
 * 
 * 1) Video tutorial on how to code the game: https://www.youtube.com/watch?v=I1qTZaUcFX0
 * This helped us write the methods that enabled us to allow the game to move horizonscore
 * 
 * 2) Obstacle game. We used the instructions in obstacle to add graphics to our game.
 * 
 * 3) Dog picture used in game: http://www.hey.fr/fun/emoji/android/en/android/378-emoji_android_dog_face.png
 * 
 * 4) Pipe image used in game: https://upload.wikimedia.org/wikipedia/commons/9/93/Mario_pipe.png
 * 
 * 5) Background image used in game: https://img.clipartfox.com/b0fbe95eb5afa7ca8da0841476ee5bfb_cartoon-scenery-background-cartoon-clipart-backgrounds_964-507.png
 */
public class Dog implements ActionListener, MouseListener, KeyListener
{
    public static Dog flappyDog;

    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public Renderer renderer;

    public ArrayList<Rectangle> pipe;
    public Rectangle doge;

    public int count;
    public int ySpeed;
    public static int score;
    public static int speed;
    public static int maxScore = 0;
    public static int time;
    public static int difficulty;

    public boolean lose;
    public boolean begin;

    public Random r;

    /**
     * Constructs the Dog class
     */
    public Dog()
    {
        JFrame j = new JFrame();
        Timer timer = new Timer(20, this);
        renderer = new Renderer();
        r = new Random();

        j.add(renderer);
        j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
        j.setTitle("Flappy Dog");
        j.setSize(WIDTH,HEIGHT);
        j.addMouseListener(this);
        j.addKeyListener(this);
        j.setResizable(false);
        j.setVisible(true);

        doge = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2 - 200, 20, 20);
        pipe = new ArrayList<Rectangle>();

        addPipe(true);
        addPipe(true);
        addPipe(true);
        addPipe(true);

        timer.start();
    }

    /**
     * Adds a new pipe to the screen
     */
    public void addPipe(boolean start)
    {
        int gap = 275;
        int width = 100;
        int height = 50 + r.nextInt(200);
        if(start)
        {
            Rectangle r1 = new  Rectangle(WIDTH + width + pipe.size() * 300, HEIGHT - height - 140, width, height);
            pipe.add(r1);
            Rectangle r2 = new Rectangle(WIDTH + width + (pipe.size() - 1) * 300, 0, width, HEIGHT - height - gap);
            pipe.add(r2);
        }
        else
        {
            Rectangle r3 = new Rectangle(pipe.get(pipe.size() - 1).x + 600, HEIGHT - height - 140, width, height);
            pipe.add(r3);
            Rectangle r4 = new Rectangle(pipe.get(pipe.size() - 1).x, 0, width, HEIGHT - height - gap);
            pipe.add(r4);
        }
    }

    /**
     * This is the method that paints a default bottom pipe
     */
    public void paintBottomPipe(Graphics g, Rectangle pipe)
    {
        g.drawImage(getBottomPipe(), pipe.x, pipe.y, pipe.width, pipe.height, null);
    }

    /**
     * This is the method that paints a default bottom pipe
     */
    public void paintTopPipe(Graphics g, Rectangle pipe)
    {
        g.drawImage(getTopPipe(), pipe.x, pipe.y, pipe.width, pipe.height, null);
    }

    //     public void flipImage(OFImage image)
    //     {    public void flipImage(OFImage image)
    //         int height = image.getHeight();
    //         int width = image.getWidth();
    //         
    //         OFImage copy = image;
    //         
    //         for(int x = 0; x < width; x++)
    //         {
    //             for(int y = 0; y < height; y++)
    //             {
    //                 image.setPixel(x, y, copy.getPixel(width - x - 1, y));
    //             }
    //         }
    //     }

    public static void music()
    {
        File soundFile = new File("freeBird.wav");
        Clip clip;
        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();           

            try {
                Thread.sleep(543000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            clip.stop();
        }
        catch(Exception e)
        {
        }
    }

    /**
     * This method allows the dog to jump
     */
    public void jump()
    {
        if(lose)
        {
            doge = new Rectangle(WIDTH / 2 - 50, HEIGHT / 2 - 200, 20, 20);
            pipe.clear();
            ySpeed = 0;
            score = 0;

            addPipe(true);
            addPipe(true);
            addPipe(true);
            addPipe(true);

            lose = false;
        }

        if (!begin)
        {
            begin = true;
        }
        else if (!lose)
        {
            if (ySpeed > 0)
            {
                ySpeed = 0;
            }
            ySpeed -= 10;
        }
    } 

    public void actionPerformed(ActionEvent a)
    {
        count++;
        if (begin)
        {
            for (int i = 0; i < pipe.size(); i++)
            {
                Rectangle p = pipe.get(i);

                p.x -= speed;
            }

            if (count % 2 == 0 && ySpeed < 15)
            {
                ySpeed += 2;
            }

            for(int i = 0; i < pipe.size(); i++)
            {
                Rectangle p = pipe.get(i);
                if(p.x + p.width < 0)
                {
                    pipe.remove(p);
                    if(p.y == 0)
                    {
                        addPipe(false); 
                    }
                }
            }

            doge.y += ySpeed;
            for (Rectangle p : pipe)
            {
                if (p.y == 0 && doge.x + doge.width / 2 > p.x + p.width / 2 - 10 && doge.x + doge.width / 2 < p.x + p.width / 2 + 10)
                {
                    score++;
                }

                if (p.intersects(doge))
                {
                    lose = true;

                    if (doge.x <= p.x)
                    {
                        doge.x = p.x - doge.width;

                    }
                    else
                    {
                        if (p.y != 0)
                        {
                            doge.y = p.y - doge.height;
                        }
                        else if (doge.y < p.height)
                        {
                            doge.y = p.height;
                        }
                    }
                }
            }

            if (doge.y > HEIGHT - 140 || doge.y < 0)
            {
                lose = true;
            }

            if (doge.y + ySpeed >= HEIGHT - 140)
            {
                doge.y = HEIGHT - 140 - doge.height;
                lose = true;
            }

            renderer.repaint();
            if(score > maxScore)
            {
                maxScore = score;
            }
        }
    }

    /**
     * This method will get an image of a dog
     * 
     * @return Image the image of the dog
     */
    public Image getDog()
    {
        return ImageLoader.loadCompatibleImage("dog.png");
    }

    /**
     * This method will get an image of a bottom pipe
     * 
     * @return Image the image of the pipe
     */
    public Image getBottomPipe()
    {
        return ImageLoader.loadCompatibleImage("pipee.png");
    }

    /**
     * This method will get an image of a bottom pipe
     * 
     * @return Image the image of the pipe
     */
    public Image getTopPipe()
    {
        return ImageLoader.loadCompatibleImage("pipeUpsideDown.png");
    }

    /**
     * This method will get an image of the daytime background
     * 
     * @return Image the image of the daytime background
     */
    public Image getBackground()
    {
        return ImageLoader.loadCompatibleImage("bground.png");
    }

    /**
     * This method will get an image of the night background
     * 
     * @return Image the image of the night background
     */
    public Image getNightBackground()
    {
        return ImageLoader.loadCompatibleImage("n2.png");
    }

    /**
     * This method will repaint the entire game
     */
    public void repaint(Graphics g)
    {
        if(time == 0)
        {
            g.drawImage(getBackground(), 0, 0, WIDTH, HEIGHT, null);
        }
        else
        {
            g.drawImage(getNightBackground(), 0, 0, WIDTH, HEIGHT, null);
        }
        g.setColor(Color.green.darker().darker().darker());
        g.fillRect(0, HEIGHT - 140, WIDTH, 150);

        g.drawImage(getDog(), doge.x, doge.y, doge.width + 17, doge.height + 17, null);

        for(int i = 0; i < pipe.size() - 1; i += 2)
        {
            paintBottomPipe(g, pipe.get(i));
        }

        for(int i = 1; i < pipe.size() - 1; i += 2)
        {
            paintTopPipe(g, pipe.get(i));
        }

        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman", 1, 50));

        if (!begin)
        {
            g.drawString("Click or hit space to play!", 25, HEIGHT / 2 - 50);
        }

        if (lose)
        {      
            if(difficulty == 3)
            {
                g.drawString("You lose! Score: " + score / 2, 100, HEIGHT / 2 - 100);
                g.drawString("High score: " + maxScore / 2, 150, HEIGHT / 2 - 50);
            }
            else if(difficulty == 2)
            {
                g.drawString("You lose! Score: " + score, 100, HEIGHT / 2 - 100);
                g.drawString("High score: " + maxScore, 150, HEIGHT / 2 - 50);
            }
            else if(difficulty == 1)
            {
                g.drawString("You lose! Score: " + score, 100, HEIGHT / 2 - 100);
                g.drawString("High score: " + maxScore, 150, HEIGHT / 2 - 50);
            }
            else
            {
                g.drawString("You lose! Score: " + score / 9, 100, HEIGHT / 2 - 100);
                g.drawString("High score: " + maxScore / 9, 150, HEIGHT / 2 - 50);
            }
        }

        if (!lose && begin)
        {
            if(difficulty == 3)
            {
                g.drawString(String.valueOf(score / 2), WIDTH / 2 - 25, 100);
            }
            else if(difficulty == 2)
            {
                g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
            }
            else if(difficulty == 1)
            {
                g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
            }
            else
            {
                g.drawString(String.valueOf(score / 9), WIDTH / 2 - 25, 100);
            }
        }

    }

    public void mouseClicked(MouseEvent e)
    {
        jump();
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jump();
        }
    }

    //     public static void sound()
    //     {
    //         AudioPlayer m = AudioPlayer.player;
    //         AudioStream b;
    //         AudioData data;
    //         ContinuousAudioDataStream loop = null;
    //         try
    //         {
    //             b = new AudioStream(new FileInputStream("point.wav"));
    //             data = b.getData();
    //             loop = new ContinuousAudioDataStream(data);
    //         }
    //         catch(IOException error)
    //         {
    //         }
    //         m.start(loop);
    //     }

    public static void main(String[] args)
    {       
        JOptionPane j = new JOptionPane();
        Object[] options = { "Day", "Night" };
        time = JOptionPane.showOptionDialog(null, "Choose a time of day.\nIf you click the red x, you will get night.", "Choose One",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]);
        Object[] difficulties = {"Painful", "Hard", "Medium", "Easy"};
        difficulty = JOptionPane.showOptionDialog(null, "Choose a difficulty.", "Choose One",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, difficulties, options[0]);
        if(difficulty == 3)
        {
            speed = 8;
        }
        else if(difficulty == 2)
        {
            speed = 10;
        }
        else if(difficulty == 1)
        {
            speed = 15;
        }
        else
        {
            speed = 2;
        }
        flappyDog = new Dog();
        music();
    }

    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }
}