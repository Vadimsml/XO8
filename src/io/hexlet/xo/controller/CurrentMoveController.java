package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.InvalidPointException;

import java.awt.*;

public class CurrentMoveController {
    public Figure currentMoveController( final Field field){
        int countFigure = 0;
        for (int x = 0; x<field.getSize();x++){
            for (int y = 0; y<field.getSize();y++){
                try {
                    if (field.getFigure(new Point(x,y))!= null) countFigure ++;
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        if (countFigure == field.getSize() * field.getSize())return null;
        return countFigure%2 == 0 ? Figure.X : Figure.O;
        }
}
