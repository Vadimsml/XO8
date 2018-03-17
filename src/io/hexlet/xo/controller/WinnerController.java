package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner (final Field field) {

    }


    private boolean check(final Field field, final Point p1, final Point p2, final Point p3) {
        try {
            if ( field.getFigure(p1) == null) return false;
            else if ( field.getFigure(p1) == field.getFigure(p2) && field.getFigure(p1) == field.getFigure(p3) ) return true;
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
    }
}
