import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    //Store model
    private ElectronicStore model;

    //Constructor
    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        //Create the view
        ElectronicStoreView view = new ElectronicStoreView();
        pane.getChildren().add(view);

        //Update the view
        view.update(model);

        //If the stock list is clicked on, update the view
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        //If the cart list is clicked on, update the view
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        //If the add button is pressed
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //Add the item to the cart
                model.addCart(view.getStockList().getSelectionModel().getSelectedItem());
                //Update the view
                view.update(model);
            }
        });

        //If the remove button is pressed
        view.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //Remove the item from the cart
                model.removeCart(view.getCartList().getSelectionModel().getSelectedItem());
                //Update the view
                view.update(model);
            }
        });

        //If the complete sale button is pressed
        view.getSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //Complete the transaction
                model.completeTransaction();
                //Update the view
                view.update(model);
            }
        });

        //If the reset store button is pressed
        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //Reset the store
                model = ElectronicStore.createStore();
                //Update the view
                view.update(model);
            }
        });

        //Show the scene
        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
