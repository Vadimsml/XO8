package io.hexlet.xo.view;

import io.hexlet.xo.controller.CurrentMoveController;
import io.hexlet.xo.controller.MoveController;
import io.hexlet.xo.controller.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.exception.AlreadyOccupiedException;
import io.hexlet.xo.model.exception.InvalidPointException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final CurrentMoveController currentMoveController = new CurrentMoveController();
    private final WinnerController winnerController = new WinnerController();
    private final MoveController moveController = new MoveController();
    public void show(final Game game) {
        System.out.format("Game name: %s\n" , game.getName());
        final Field field= game.getField();
        for (int x =0; x< field.getSize(); x++){
            if (x!=0)
                printSeparator();
            printLine (field, x);
        }
    }

    private void printLine(final Field field,
                           final  int x) {
//        System.out.print('|');
        for (int y =0; y< field.getSize(); y++){
            if (y!=0)
                System.out.print('|');
            System.out.print(" ");
            final Figure figure;
            try {
                figure = field.getFigure(new Point (y,x));
//                System.out.println(field.getFigure(new Point (x,y)));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null?figure:' ');

        }
        System.out.println();
    }


    private void printSeparator() {
        System.out.println("--------");
    }

    public  boolean move (final Game game) {
        final Field field = game.getField();
        final Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.printf("Winner is: %s", winner);
            return false;
        }
        final Figure currentFigere = currentMoveController.currentMoveController(field);
        if (currentFigere == null) {
//            final Figure winner = winnerController.getWinner(field);

                System.out.println("No winner and no moves left!");
                return false;

        }
        System.out.format("Please enter move point for: %s\n", currentFigere);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field,point,currentFigere);
        } catch (InvalidPointException  | AlreadyOccupiedException e) {
        //    e.printStackTrace();
            System.out.println("Point is invalid");
        }
        return true;
    }

    private Point askPoint (){
        return new Point(askCoordinate("X")-1,askCoordinate("Y")-1);
    }

    private int askCoordinate(final String coordinateName){
        System.out.format("Please input %s", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {

            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("enter number please! olololo\n");
            return askCoordinate(coordinateName);
        }
    }
}
