package project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import project.Manager.BillManager;
import project.Manager.CustomerManager;
import project.Manager.EmployeeManager;
import project.Manager.ProductManager;
import project.dao.BillManagerDAO;
import project.dao.CustomerManagerDAO;
import project.dao.EmployeeManagerDAO;
import project.dao.ProductManagerDAO;
import project.jdbcutil.JDBCUtil;
import project.model.Administrator;
import project.model.Bill;
import project.model.Order;
import project.model.SoldProduct;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StatisticController implements Initializable {

    Administrator admin = new Administrator();
    @FXML
    private Button Statistic;

    @FXML
    private RadioButton allBtn2;

    @FXML
    private RadioButton allBtn3;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private ToggleGroup choose;

    @FXML
    private ToggleGroup choose2;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private DatePicker dateStart;

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private ComboBox<?> monthBox2;

    @FXML
    private ComboBox<?> monthBox3;

    @FXML
    private RadioButton monthBtn2;

    @FXML
    private RadioButton monthBtn3;

    @FXML
    private TableView<?> tableLuong;

//    @FXML
//    private TableView<?> tableSP;

    @FXML
    void actBtn3(ActionEvent event) {

    }
    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    void actStatistic(ActionEvent event) {
        Comparator<Date> comp = new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        };
        Date start = java.sql.Date.valueOf(dateStart.getValue());;
        Date end = java.sql.Date.valueOf(dateEnd.getValue());
        if(start != null && end != null && end.after(start)){
            lineChart.getData().clear();

            XYChart.Series<String,Integer> series = new XYChart.Series<>();
            series.setName("Doanh thu");
            XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
            series1.setName("Lợi nhuận");
            Map<Date,Integer> mp = new HashMap<>();
            Map<Date,Integer> mp1 = new HashMap<>();
            for(Bill x : admin.billManager.billList){
                if(x.getDate().after(start) && x.getDate().before(end)){
                    if(mp.containsKey(x.getDate())){
                        System.out.println(x.getDate().toString());
                        mp.replace(x.getDate(), mp.get(x.getDate()) + x.getTotalPayout());
                        mp1.replace(x.getDate(),mp1.get(x.getDate()) +   x.getTotalPayout() - x.getOrder().totalImportPrice());
                    }else{
                        System.out.println(x.getDate().toString());
                        mp.put(x.getDate(), x.getTotalPayout());
                        mp1.put(x.getDate(), x.getTotalPayout() - x.getOrder().totalImportPrice());
                    }
                }
            }
            Set<Date> set = mp.keySet();
            ArrayList<Date> arr = new ArrayList<Date>();
            for(Date x : set) arr.add(x);
            arr.sort(comp);
            for(Date x : arr){
                series.getData().add(new XYChart.Data<>(x.toString(), mp.get(x)));
                series1.getData().add(new XYChart.Data<>(x.toString(), mp1.get(x)));
//                series.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
//                    @Override
//                    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
//                        label1.setText(x.toString());
//                        label2.setText("Doanh thu : " + mp.get(x));
//                        label3.setText("Lợi nhuận : " + mp1.get(x));
//                    }
//                });
            }

            lineChart.getData().addAll(series);
            lineChart.getData().addAll(series1);

            for(final XYChart.Data<String , Integer> data : series.getData()){
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                        label1.setText("Ngày : " + data.getXValue());
                        label2.setText("Doanh thu : " + data.getYValue());
                        label3.setText("Lợi nhuận : " );
                        Tooltip.install(data.getNode(),new Tooltip());
                    }
                });
            }
            for(final XYChart.Data<String , Integer> data : series1.getData()){
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        label1.setText("Ngày :" + data.getXValue());
                        label2.setText("Doanh thu : " );
                        label3.setText("Lợi nhuận : " + data.getYValue());
                        Tooltip.install(data.getNode(),new Tooltip());
                    }
                });
            }
        }

    }

    @FXML
    void actallBtn2(ActionEvent event) {

    }

    @FXML
    void actallBtn3(ActionEvent event) {

    }

    @FXML
    void actmontBtn2(ActionEvent event) {

    }

    @FXML
    void actmonthBox2(ActionEvent event) {

    }
    @FXML
    void actmonthBox3(ActionEvent event) {

    }
    @FXML
    private TableColumn<ProductStatistic, String> idColumn;

    @FXML
    private TableColumn<ProductStatistic, String> nameProductColumn;

    @FXML
    private TableColumn<ProductStatistic, Integer> soldCountColumn;

    @FXML
    private TableColumn<ProductStatistic, Integer> profitColumn;
    @FXML
    private TableView<ProductStatistic> tableSP;
    private ObservableList<ProductStatistic> productStatistics;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productStatistics = FXCollections.observableArrayList();
        for(Bill bill : BillManager.billList){
            Order order = bill.getOrder();
            for(SoldProduct sp : order.getList()){
                update(sp);
            }
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistic,String>("id"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistic,String>("nameProduct"));
        soldCountColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistic,Integer>("soldCount"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistic,Integer>("profit"));
        tableSP.setItems(productStatistics);

    }
    public boolean update(SoldProduct sp){
        for(ProductStatistic pr : productStatistics)
            if(pr.getId().compareTo(sp.getIdProduct()) == 0) {
                pr.setSoldCount(sp.getCount() + pr.getSoldCount());
                pr.setProfit(sp.getMoney() + pr.getProfit());
                return true;
            }
        ProductStatistic newPs = new ProductStatistic(sp.getIdProduct(), sp.getNameProduct(),sp.getCount(), sp.getMoney());
        productStatistics.add(newPs);
        return false;
    }

    public class ProductStatistic{

        private String id;
        private String nameProduct;
        private int soldCount;
        private int profit;
        public ProductStatistic() {
        }
        public ProductStatistic(String id, String nameProduct, int soldCount, int profit) {
            this.id = id;
            this.nameProduct = nameProduct;
            this.soldCount = soldCount;
            this.profit = profit;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameProduct() {
            return nameProduct;
        }

        public void setNameProduct(String nameProduct) {
            this.nameProduct = nameProduct;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "ProductStatistic{" +
                    "id='" + id + '\'' +
                    ", nameProduct='" + nameProduct + '\'' +
                    ", soldCount=" + soldCount +
                    ", profit=" + profit +
                    '}';
        }
    }
}
