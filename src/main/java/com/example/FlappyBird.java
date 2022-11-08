package com.example;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird implements ActionListener {
    
    public static final int WIDTH = 900, HEIGHT = 600, DELAY = 10;
    
    private Bird bird;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<Rectangle> rects;
    private int score, isRenderRectangle;
    private Timer timer;
    private boolean isGameOver;
    private boolean isPaused;
    
    public void start() {
        frame = new JFrame("Flappy Bird");
        timer = new Timer(DELAY, this);
        bird = new Bird();
        rects = new ArrayList<Rectangle>();
        panel = new GamePanel(this, bird, rects);
        frame.add(panel);
        
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        attachCallbacks();
        
        isPaused = true;
        timer.start();
    }
    public static void main(String[] args) {
        new FlappyBird().start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if(!isPaused) {
            bird.physics();
            if(isRenderRectangle % 95 == 0) {
                Rectangle r = new Rectangle(WIDTH, 0, GamePanel.PILLAR_WIDTH, (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5f + (0.2f)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, GamePanel.PILLAR_WIDTH, h2);
                rects.add(r);
                rects.add(r2);
            }
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            isGameOver = false;
            for(Rectangle r : rects) {
                r.x-=3;
                if(r.x + r.width <= 0) {
                    toRemove.add(r);
                }
                if(r.intersects(bird.imgRect)) {
                    isGameOver = true;
                }
                // If bird reaches center of the column, add 1 to score
                if (r.y == 0 && bird.x + bird.imgRect.width / 2 > r.x + r.width / 2 - 2 && 
                    bird.x + bird.imgRect.width / 2 < r.x + r.width / 2 + 2) {
                    score++;
                }
            }
            rects.removeAll(toRemove);
            isRenderRectangle++;

            if(bird.y > HEIGHT || bird.y+bird.sideLength < 0) {
                isGameOver = true;
            }

            if(isGameOver) {
                rects.clear();
                bird.reset();
                score = 0;
                isRenderRectangle = 0;
                isPaused = true;
            }
        }
    }

    private void attachCallbacks() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE) {
                    bird.jump();
                }
                else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    isPaused = !isPaused;
                    if(isGameOver) {
                        isGameOver = !isGameOver;
                    }
                }
            }
        });
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                bird.jump();
            }
        });
    }
    
    public int getScore() {
        return score;
    }
    
    public boolean isPaused() {
        return isPaused;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}