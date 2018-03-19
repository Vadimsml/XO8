package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exception.InvalidPointException;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CurrentMoveControllerTest {

    @Test
    public void getCurrentMoveControllerTestX() {
        //final Figure expectedFigure = ;
        final Field field = new Field(3);
        for (int x =0; x<2;x++){
            for (int y =0; y<2;y++){
                try {
                    if ((x+y)%2==0) field.setFigure( new Point(x,y), Figure.X);
                    else field.setFigure( new Point(x,y), Figure.O);
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        CurrentMoveController currentMoveController = new CurrentMoveController();
        assertEquals(Figure.X, currentMoveController.currentMoveController(field));
    }


    @Test
    public void getCurrentMoveControllerTestO() {
        //final Figure expectedFigure = ;
        final Field field = new Field(3);
        try {
            field.setFigure( new Point(0,0), Figure.X);
            field.setFigure( new Point(1,0), Figure.X);
            field.setFigure( new Point(2,0), Figure.O);

        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        CurrentMoveController currentMoveController = new CurrentMoveController();
    assertEquals(Figure.O, currentMoveController.currentMoveController(field));
    }

    @Test
    public void getCurrentMoveControllerTestNull() {
        //final Figure expectedFigure = ;
        final Field field = new Field(3);
        for (int x =0; x<3;x++){
            for (int y =0; y<3;y++){
                try {
                    if ((x+y)%2==0) field.setFigure( new Point(x,y), Figure.X);
                    else field.setFigure( new Point(x,y), Figure.O);
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }
        CurrentMoveController currentMoveController = new CurrentMoveController();
        assertNull( currentMoveController.currentMoveController(field));
    }

}