import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane {
    //Listviews, text-fields, buttons, and labels
    private ListView<Product> popularList;
    private ListView<Product> stockList;
    private ListView<String> cartList;
    private TextField salesField, revenueField, perSaleField;
    private Button resetButton, addButton, removeButton, saleButton;
    private Label currentCartLabel;

    //Constructor
    public ElectronicStoreView(){
        //Create the labels, relocate them, and set the font
        Label summaryLabel = new Label("Store Summary:");
        summaryLabel.relocate(50, 15);
        summaryLabel.setStyle("-fx-font: 12 arial;");
        Label popularLabel = new Label("Most Popular Items:");
        popularLabel.relocate(40, 140);
        popularLabel.setStyle("-fx-font: 12 arial;");
        Label stockLabel = new Label("Store stock:");
        stockLabel.relocate(305, 15);
        stockLabel.setStyle("-fx-font: 12 arial;");
        currentCartLabel = new Label();
        currentCartLabel.relocate(585, 15);
        currentCartLabel.setStyle("-fx-font: 12 arial;");
        Label numSalesLabel = new Label("# Sales:");
        numSalesLabel.relocate(32, 45);
        numSalesLabel.setStyle("-fx-font: 12 arial;");
        Label revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(24, 78);
        revenueLabel.setStyle("-fx-font: 12 arial;");
        Label perSaleLabel = new Label("$ / Sale:");
        perSaleLabel.relocate(31, 111);
        perSaleLabel.setStyle("-fx-font: 12 arial;");

        //Create the TextFields, relocate them, resize them, and make them non-editable
        salesField = new TextField();
        salesField.relocate(82, 40);
        salesField.setPrefSize(98,28);
        salesField.setEditable(false);

        revenueField = new TextField();
        revenueField.relocate(82, 73);
        revenueField.setPrefSize(98,28);
        revenueField.setEditable(false);

        perSaleField = new TextField();
        perSaleField.relocate(82, 106);
        perSaleField.setPrefSize(98,28);
        perSaleField.setEditable(false);

        //Create the listviews, relocate them, and resize them
        popularList = new ListView<Product>();
        popularList.relocate(10, 165);
        popularList.setPrefSize(170,170);

        stockList = new ListView<Product>();
        stockList.relocate(190, 40);
        stockList.setPrefSize(295,295);

        cartList = new ListView<String>();
        cartList.relocate(495, 40);
        cartList.setPrefSize(295,295);

        //Create the buttons
        resetButton = new Button("Reset Store");
        resetButton.setStyle("-fx-font: 12 arial;");
        resetButton.relocate(21.25, 342);
        resetButton.setPrefSize(147.5,50);

        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial;");
        addButton.relocate(263.75, 342);
        addButton.setPrefSize(147.5,50);

        removeButton = new Button("Remove from Cart");
        removeButton.setStyle("-fx-font: 12 arial;");
        removeButton.relocate(495, 342);
        removeButton.setPrefSize(147.5,50);

        saleButton = new Button("Complete Sale");
        saleButton.setStyle("-fx-font: 12 arial;");
        saleButton.relocate(642.5, 342);
        saleButton.setPrefSize(147.5,50);

        //Add all the components to the Pane
        getChildren().addAll(summaryLabel, popularLabel, stockLabel, currentCartLabel, numSalesLabel, revenueLabel, perSaleLabel, salesField, revenueField, perSaleField, popularList, stockList, cartList, resetButton, addButton, removeButton, saleButton);

        //Set the size to 800 by 400
        setPrefSize(800, 400);
    }

    //Getter methods
    public ListView<Product> getStockList(){
        return stockList;
    }

    public ListView<String> getCartList(){
        return cartList;
    }

    public Button getResetButton(){
        return resetButton;
    }

    public Button getAddButton(){
        return addButton;
    }

    public Button getRemoveButton(){
        return removeButton;
    }

    public Button getSaleButton(){
        return saleButton;
    }

    //Update method
    public void update (ElectronicStore model){
        //Update the three listviews
        popularList.setItems(FXCollections.observableArrayList(model.mostPopularItems()));
        stockList.setItems(FXCollections.observableArrayList(model.getStock()));
        cartList.setItems(FXCollections.observableArrayList(model.getCart()));

        //Enable/disable add and remove buttons when they are used/not used
        addButton.setDisable(stockList.getSelectionModel().getSelectedIndex() < 0);
        removeButton.setDisable(cartList.getSelectionModel().getSelectedIndex() < 0);

        //Only allow the user to complete the sale when there are items in the cart
        saleButton.setDisable(model.getCart().size() == 0);

        //Update the current cart amount text
        currentCartLabel.setText("Current Cart ($" + String.format("%.2f", model.getCartAmount()) + "):");

        //Update the # of sales and revenue
        salesField.setText("" + model.getNumberSales());
        revenueField.setText(String.format("%.2f", model.getRevenue()));

        //Update the dollars per sale (If the number of sales is greater than zero, display the dollars per sale)
        if (model.getNumberSales() > 0){
            perSaleField.setText(String.format("%.2f", model.getDollarPerSale()));
        }
        //If the number of sales is not greater than zero, display "N/A"
        else {
            perSaleField.setText("N/A");
        }
    }
}
