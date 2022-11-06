/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class GamePanel extends JPanel {
    
    private Bird bird;
    private ArrayList<Rectangle> rects;
    private FlappyBird flappyBird;
    private Font scoreFont, textFont;
    public static final Color bg = new Color(0, 158, 158);
    public static final int PILLAR_WIDTH = 75, PIPE_H = 30;
    private Image pillar, background;

    public GamePanel(FlappyBird flappyBird, Bird bird, ArrayList<Rectangle> rects) {
        this.flappyBird = flappyBird;
        this.bird = bird;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        textFont = new Font("Arial", Font.BOLD, 30);
        pillar = ImageHelper.getPillar();
        background = ImageHelper.getBackground();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0,0,FlappyBird.WIDTH,FlappyBird.HEIGHT);
        g.drawImage(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT, null);
        bird.repaint(g);

        for(Rectangle r : rects) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform old = g2d.getTransform();
            g2d.translate(r.x, r.y);
            if(r.y < FlappyBird.HEIGHT/2) {
                g2d.translate(0, r.height);
                g2d.rotate(Math.PI);
                g2d.drawImage(pillar, 0, 0, -GamePanel.PILLAR_WIDTH, r.height, null);
            } else {
                g2d.drawImage(pillar, 0, 0, GamePanel.PILLAR_WIDTH, r.height, null);
            }
            g2d.setTransform(old);
        }

        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + flappyBird.getScore(), 10, 20);

        if(flappyBird.isPaused() && !flappyBird.isGameOver()) {
            drawString(new String[]{"PAUSED!", "PRESS ENTER TO BEGIN OR CONTINUE"}, g);
        }

        if(flappyBird.isGameOver()) {
            drawString(new String[]{"YOU LOSE!", "PRESS ENTER TO START AGAIN"}, g);
        }
    }

    public void drawString(String[] str, Graphics g) {
        g.setFont(textFont);
        g.setColor(Color.DARK_GRAY);
        g.drawString(str[0], FlappyBird.WIDTH/2-100, FlappyBird.HEIGHT/2-100);
        g.drawString(str[1], FlappyBird.WIDTH/2-300, FlappyBird.HEIGHT/2+50);
    }
}