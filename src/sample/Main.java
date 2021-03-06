package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{

    public static int level = 1;
    private static Button green = new Button();
    private static Button blue = new Button();
    private static Button red = new Button();
    private static Button yellow = new Button();
    private static boolean showing = false;
    private static long timeStep;
    private static List<Button> order = new ArrayList<Button>();
    private static String currentColor = "";


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Simon Says");

        //Button green = new Button();
        green.setStyle("-fx-background-color: green;");
        green.setMinSize(50,50);
        green.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showing = true; currentColor = "green"; timer(green); isCorrect(green);
            }
        });

        //Button blue = new Button();
        blue.setStyle("-fx-background-color: blue;");
        blue.setMinSize(50,50);
        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showing = true; currentColor = "blue"; timer(blue); isCorrect(blue);
            }
        });

        //Button red = new Button();
        red.setStyle("-fx-background-color: red;");
        red.setMinSize(50,50);
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showing = true; currentColor = "red"; timer(red); isCorrect(red);
            }
        });

        //Button yellow = new Button();
        yellow.setStyle("-fx-background-color: yellow;");
        yellow.setMinSize(50,50);
        yellow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showing = true; currentColor = "yellow"; timer(yellow); isCorrect(yellow);
            }
        });

        Button start = new Button("Start Game");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {createOrder();}
        });

        HBox hbox1 = new HBox(green, blue, red, yellow, start);

        Scene scene1 = new Scene(hbox1,400, 400);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void createOrder()
    {
        showing = true;
        Button btn;
        btn = pickRandomColor();
        order.add(btn);
        timer(btn);
        level++;
    }

    public static Button pickRandomColor()
    {
        int num = (int)(Math.random()*4);
        if(num == 0)
        {
            currentColor = "green";
            return green;
        }
        else if(num == 1)
        {
            currentColor = "blue";
            return blue;
        }
        else if(num == 2)
        {
            currentColor = "red";
            return red;
        }
        else
        {
            currentColor = "yellow";
            return yellow;
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
                    btn.setStyle("-fx-background-color: white;");
                }
                else
                {
                    btn.setStyle("-fx-background-color: " + currentColor + ";");
                    this.stop();
                }

            }
        }.start();
    }

    public static boolean isCorrect(Button btn)
    {
        if(order.size() == 0)
        {
            System.out.println("no order");
            return false;
        }
        else {
            if (btn == order.get(order.size() - 1)) {
                System.out.println("true");
                return true;
            }
            System.out.println("false");
            return false;
        }
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
