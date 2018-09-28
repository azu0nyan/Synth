package com.azu.Controls;

import ru.ege.engine.DrawableObject;
import ru.ege.engine.EGEngine;

import java.awt.*;

public class SquareMouseController implements DrawableObject {

    double minX = 0;
    double maxX = 1;
    double minY = 0;
    double maxY = 1;
    //area
    public int left = 0;
    public int top = 0;
    public int sizeX = EGEngine.i().getWidth();
    public int sizeY = EGEngine.i().getHeight();


    ControllableValue controllableValueX;
    ControllableValue controllableValueY;

    public SquareMouseController(ControllableValue controllableValueX) {
        this.controllableValueX = controllableValueX;
    }

    public SquareMouseController(ControllableValue controllableValueX, ControllableValue controllableValueY) {
        this.controllableValueX = controllableValueX;
        this.controllableValueY = controllableValueY;
    }

    public SquareMouseController(ControllableValue controllableValueX, double minX, double maxX, ControllableValue controllableValueY, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.controllableValueX = controllableValueX;
        this.controllableValueY = controllableValueY;
    }

    Point mouse;
    double valueSetX = minX;
    double valueSetY = minY;
    @Override
    public void drawAndUpdate(Graphics2D graphics2D, double v) {
        mouse = EGEngine.i().getMousePosition();

        if (mouse != null) {
            //in area
            if (inArea()) {
                double xOffset = mouse.getX() - left;
                double yOffset = mouse.getY() - top;

                double xFraction = xOffset / sizeX;
                double yFraction = yOffset / sizeY;

                double deltaX = maxX - minX;
                double valueX = minX;
                valueX += deltaX * xFraction;
                double deltaY = maxY - minY;
                double valueY = minY;
                valueY += deltaY * yFraction;

                if (controllableValueX != null) {
                    controllableValueX.set(valueX);
                    valueSetX = valueX;
                }
                if (controllableValueY != null) {
                    controllableValueY.set(valueY);
                    valueSetY = valueY;
                }

            }
        }
        draw(graphics2D);
    }

    boolean inArea() {
        return mouse.getX() > left && mouse.getX() < left + sizeX && mouse.getY() > top && mouse.getY() < left + sizeY;
    }

    int strokeSize = 5;
    Color rect = new Color(34, 189, 35);
    Color background = new Color(0, 0, 0);
    Color lineColor = new Color(212, 35, 46);
    boolean printValues = true;
    int fontSize = 16;
    Color textColor = new Color(84, 102, 226);

    private void draw(Graphics2D g) {
        g.setColor(background);
        g.fillRect(left, top, sizeX, sizeY);
        g.setColor(lineColor);
        g.setStroke(new BasicStroke(strokeSize));
        if (mouse != null && inArea() && controllableValueX != null) {
            int x = mouse.x;
            g.drawLine(x, top,x,top + sizeY);
        }
        if (mouse != null && inArea() && controllableValueY != null) {
            int y = mouse.y;
            g.drawLine(left, y,left + sizeX, y);
        }

        g.setColor(rect);
        g.drawRect(left, top, sizeX, sizeY);
        if(mouse != null && inArea() && printValues){
            g.setColor(textColor);
            g.setFont(new Font("", Font.BOLD, fontSize));
            g.drawString(String.format("%.3f %.3f", valueSetX,valueSetY), mouse.x, mouse.y);
        }
    }
}
