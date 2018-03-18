package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner (final Field field) {
        try {
            for (int i=0; i<3; i++)
            if (check(field, new Point(i, 0), p -> new Point(p.x, p.y+1)))
                return field.getFigure(new Point(i, 0));

            for (int i=0; i<3; i++)
                if (check(field, new Point (0, i), p -> new Point(p.x+1, p.y)))
                    return field.getFigure(new Point(0, i));

            if (check(field, new Point(0, 0),  p -> new Point(p.x+1, p.y+1)))
                return field.getFigure(new Point(1, 1));

            if (check(field, new Point(0, 2), p -> new Point(p.x+1, p.y-1)))
                return field.getFigure(new Point(1, 1));

        } catch (final InvalidPointException e) {e.printStackTrace();}
        return null;
    }


    private boolean check(final Field field,
                          final Point currentPoint,
                          final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);

        try {
            currentFigure = field.getFigure(currentPoint);
            if (currentFigure==null) return false;
            nextFigure =    field.getFigure(nextPoint);
            if (currentFigure!=nextFigure) return false;
        } catch (final InvalidPointException e) {
            return true;
        }

        return check(field, nextPoint, pointGenerator);


//        try {
//            if ( field.getFigure(p1) != null &&
//                 field.getFigure(p1) == field.getFigure(p2) &&
//                 field.getFigure(p1) == field.getFigure(p3) ) return true;
//        } catch (InvalidPointException e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    private interface IPointGenerator {
        public Point next (final Point point);
    }

 }
