import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Launcher extends Application {
    private Button btnOpenNewWindow = new Button("There Is Something\nBetter Than Useless WAR\nIf You Just Click\nThis Button");
    private boolean isStarted = false;
    private BorderPane root = new BorderPane();
    private VBox vbox = new VBox();
    private static HBox[] hbox = new HBox[10];
    private static ImageView[][] imageViews = new ImageView[10][11];
    private static int[] clickedPosition = new int[2];

    public static ImageView load(File file) {
        Image image = new Image(file.toURI().toString());
        ImageView iv = new ImageView(image);
        return iv;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Game game = new Game(10, 10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                imageViews[i][j] = load(new File("icons", game.names[i][j] + ".jpg"));//
                int finalI = i;
                int finalJ = j;
                imageViews[i][j].setOnMouseClicked(event ->
                        {
                            System.out.println("x= " + finalI + " y= " + finalJ);
                            clickedPosition[0] = finalI;
                            clickedPosition[1] = finalJ;
                        }
                );
                hbox[j].getChildren().add(imageViews[i][j]);
                if (i == 9 && j == 0) {
                    add(9, 0, "realEmpty", "arrow-up")[1].setOnMouseClicked(event -> move("up", clickedPosition[0], clickedPosition[1]));
                }
                if (i == 9 && j == 1) {
                    ImageView[] im;
                    im = add(9, 1, "arrow-left", "arrow-right");
                    im[0].setOnMouseClicked(event -> move("left", clickedPosition[0], clickedPosition[1]));
                    im[1].setOnMouseClicked(event -> move("right", clickedPosition[0], clickedPosition[1]));
                }
                if (i == 9 && j == 2) {
                    add(9, 2, "realEmpty", "arrow-down")[1].setOnMouseClicked(event -> move("down", clickedPosition[0], clickedPosition[1]));
                }

                if (i == 9 && j == 3) {
                    add(9, 3, "realEmpty", "explode")[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Game.informExplosionEvent(clickedPosition[0], clickedPosition[1]);


                            if ((clickedPosition[0] - 1 >= 0)) {
                                if ((Game.names[clickedPosition[0] - 1][clickedPosition[1]] != "empty")) {
                                    System.out.println(0);
                                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1]);
                                }
                            }
                            if ((clickedPosition[0] + 1 <= 9)) {
                                if ((Game.names[clickedPosition[0] + 1][clickedPosition[1]] != "empty")) {
                                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1]);
                                    System.out.println(1);
                                }
                            }
                            if ((clickedPosition[1] - 1 >= 0)) {
                                if ((Game.names[clickedPosition[0]][clickedPosition[1] - 1] != "empty")) {
                                    Game.informInjuryEvent(clickedPosition[0], clickedPosition[1] - 1);
                                    System.out.println(2);
                                }
                            }
                            if ((clickedPosition[1] + 1 <= 9)) {
                                if (Game.names[clickedPosition[0]][clickedPosition[1] + 1] != "empty") {
                                    Game.informInjuryEvent(clickedPosition[0], clickedPosition[1] + 1);
                                    System.out.println(3);
                                }
                            }
                            if ((clickedPosition[0] - 1 >= 0 && clickedPosition[1] - 1 >= 0)) {
                                if (Game.names[clickedPosition[0] - 1][clickedPosition[1] - 1] != "empty") {
                                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1] - 1);
                                    System.out.println(4);
                                }
                            }
                            if ((clickedPosition[0] - 1 >= 0 && clickedPosition[1] + 1 <= 9)) {
                                if (Game.names[clickedPosition[0] - 1][clickedPosition[1] + 1] != "empty") {
                                    Game.informInjuryEvent(clickedPosition[0] - 1, clickedPosition[1] + 1);
                                    System.out.println(5);
                                }
                            }
                            if ((clickedPosition[0] + 1 <= 9 && clickedPosition[1] + 1 <= 9)) {
                                if (Game.names[clickedPosition[0] + 1][clickedPosition[1] + 1] != "empty") {
                                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1] + 1);
                                    System.out.println(6);
                                }
                            }
                            if ((clickedPosition[0] + 1 <= 9 && clickedPosition[1] + 1 <= 9)) {
                                if (Game.names[clickedPosition[0] + 1][clickedPosition[1] + 1] != "empty") {
                                    Game.informInjuryEvent(clickedPosition[0] + 1, clickedPosition[1] + 1);
                                    System.out.println(7);
                                }
                            }
                        }
                    });
                }
                if (i == 9 && j == 4) {
                    add(9, 4, "realEmpty", "gun")[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(Game.whatIsInThisPlace(clickedPosition[0],clickedPosition[1]).getTeam()==false){
                                for(int i=clickedPosition[0]+1;i<10;i++){
                                    if(Game.names[i][clickedPosition[1]]!="empty"){
                                        Game.names[i][clickedPosition[1]]="empty";
                                    }
                                }
                            }
                            if(Game.whatIsInThisPlace(clickedPosition[0],clickedPosition[1]).getTeam()==true){
                                for(int i=clickedPosition[0]-1;i>=0;i--){
                                    if(Game.names[i][clickedPosition[1]]!="empty"){
                                        Game.names[i][clickedPosition[1]]="empty";
                                    }
                                }
                            }
                        }
                    });
                }
                if (i == 9 && j == 9) {
                    hbox[i].getChildren().add(new ImageView(new Image((new File("icons", "realEmpty.jpg")).toURI().toString())));
                    hbox[i].getChildren().add(btnOpenNewWindow);
                }
            }
            vbox.getChildren().add(hbox[i]);
        }



        root.setCenter(vbox);
        //The taylor thing:
        Stage videoStage = new Stage();
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("Taylor Swift - Mean.mp4").toExternalForm()));

        btnOpenNewWindow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Button backButton = new Button("Back To The Game");
                videoStage.show();
                // Hide this current window (if this is what you want)
                stage.hide();
                if (isStarted == false) {
                    isStarted = true;
                    BorderPane root = new BorderPane();
                    MediaView mediaView = new MediaView(player);
                    root.setCenter(mediaView);
                    root.setBottom(backButton);
                    videoStage.setTitle("This is Taylor swift,Forget About The War");
                    Screen screen = Screen.getPrimary();
                    Rectangle2D bounds = screen.getVisualBounds();
                    Scene vidScene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 50);
                    root.setStyle("-fx-background-color:grey;");
                    videoStage.setScene(vidScene);

                }
                player.play();
                backButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.show();
                        videoStage.hide();
                        player.pause();
                    }
                });


            }
        });


        game.start();
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();
    }

    private ImageView[] add(int i, int j, String name1, String name2) {
        ImageView[] imageViews = new ImageView[2];
        File tempFile = new File("icons", name1 + ".jpg");
        Image tempImage = new Image(tempFile.toURI().toString());
        imageViews[0] = new ImageView(tempImage);
        hbox[j].getChildren().add(imageViews[0]);
        tempFile = new File("icons", name2 + ".jpg");
        tempImage = new Image(tempFile.toURI().toString());
        imageViews[1] = new ImageView(tempImage);
        hbox[j].getChildren().add(imageViews[1]);
        return imageViews;
    }

    public static int[] clickEvent() {
        int[] list = new int[2];
        list[0] = clickedPosition[0];
        list[1] = clickedPosition[1];
        return list;
    }

    public static void updateGraphic() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                imageViews[i][j].setImage(new Image((new File("icons", Game.names[i][j] + ".jpg")).toURI().toString()));
            }
        }

    }


    private void move(String direction, int x, int y) {
        if (direction == "up") {
            if (y >= 1) {
                if (Game.names[x][y + 1] == "empty") {
                    Game.moveInList(x, y, x, y - 1);
                    Game.whatIsInThisPlace(x, y).setY(Game.whatIsInThisPlace(x, y).getY() - 1);
                }
            }
        } else if (direction == "down") {
            if (y <= 8) {
                if (Game.names[x][y +1] == "empty") {
                    Game.moveInList(x, y, x, y + 1);
                    Game.whatIsInThisPlace(x, y).setY(Game.whatIsInThisPlace(x, y).getY() + 1);
                }
            }
        } else if (direction == "left") {
            if (x >= 1) {
                if (Game.names[x - 1][y] == "empty") {
                    Game.moveInList(x, y, x - 1, y);
                    Game.whatIsInThisPlace(x, y).setX(Game.whatIsInThisPlace(x, y).getX() - 1);
                }
            }
        } else if (direction == "right") {
            if (x <= 8) {
                if (Game.names[x + 1][y] == "empty") {
                    Game.moveInList(x, y, x + 1, y);
                    Game.whatIsInThisPlace(x, y).setX(Game.whatIsInThisPlace(x, y).getX() + 1);
                }
            }
        }
    }




    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            hbox[i] = new HBox();
        }
        launch(args);

    }
}