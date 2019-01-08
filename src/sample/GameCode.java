package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameCode extends Application
{
    private static List<Button> order = new ArrayList<Button>();
    public static boolean showing = false;
    private static long timeStep;

    @Override
    public void start(Stage primaryStage)
    {

    }

    public static void createOrder()
    {
        showing = true;
        Button btn;
        btn = pickRandomColor();
        order.add(btn);
        timer(btn);
        Main.level++;
    }

    public static Button pickRandomColor()
    {
        int num = (int)(Math.random()*4);
        if(num == 0)
        {
            return Main.green;
        }
        else if(num == 1)
        {
            return Main.blue;
        }
        else if(num == 2)
        {
            return Main.red;
        }
        else
        {
            return Main.yellow;
        }
    }

    public static void checkGreen(String color)
    {
        if(color.equals("green"))
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }

    public static void timer(Button btn)
    {
        timeStep = System.nanoTime() + 100000000L;
        new AnimationTimer()
        {
            public void handle(long now)
            {
                if(now > timeStep)
                {
                    timeStep = now + 1000000000L;
                    showing = !showing;
                }
                if(showing)
                {
                    Main.green.setStyle("-fx-background-color: lightgreen;");
                }
                else
                {
                    Main.green.setStyle("-fx-background-color: green;");
                    this.stop();
                }

            }
        }.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
