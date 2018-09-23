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

    @Override
    public void drawAndUpdate(Graphics2D graphics2D, double v) {
        mouse = EGEngine.i().getMousePosition();
        draw(graphics2D);
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
                }
                if (controllableValueY != null) {
                    controllableValueY.set(valueY);
                }

            }
        }
    }

    boolean inArea() {
        return mouse.getX() > left && mouse.getX() < left + sizeX && mouse.getY() > top && mouse.getY() < left + sizeY;
    }

    int strokeSize = 5;
    Color rect = new Color(34, 189, 35);
    Color background = new Color(0, 0, 0);
    Color lineColor = new Color(212, 35, 46);

    private void draw(Graphics2D graphics2D) {
        graphics2D.setColor(background);
        graphics2D.fillRect(left, top, sizeX, sizeY);
        graphics2D.setColor(lineColor);
        graphics2D.setStroke(new BasicStroke(strokeSize));
        if (mouse != null && inArea() && controllableValueX != null) {
            int x = mouse.x;
            graphics2D.drawLine(x, top,x,top + sizeY);
        }
        if (mouse != null && inArea() && controllableValueY != null) {
            int y = mouse.y;
            graphics2D.drawLine(left, y,left + sizeX, y);
        }

        graphics2D.setColor(rect);
        graphics2D.drawRect(left, top, sizeX, sizeY);

    }
}
