/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author User
 */
public class Bird {
    public float x, y, vx, vy;
    public Rectangle imgRect;
    public final int sideLength = 35;
    private Image bird;
    public Bird() {
        x = FlappyBird.WIDTH/2;
        y = FlappyBird.HEIGHT/2;
        bird = ImageHelper.getBird();
    }

    public void physics() {
        x+=vx;
        y+=vy;
        vy+=0.8f;
    }

    public void repaint(Graphics g) {
        g.setColor(Color.BLACK);
        int newX = Math.round(x-sideLength);
        int newY = Math.round(y-sideLength);
        g.drawImage(bird, newX , newY, sideLength, sideLength, null);
        imgRect = new Rectangle(newX, newY, sideLength, sideLength);
    }

    public void jump() {
        vy = -10;
    }
    
    public void reset() {
        x = FlappyBird.WIDTH / 2;
        y = FlappyBird.HEIGHT / 2;
        vx = vy = 0;
    }
}