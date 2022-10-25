package project.controller;

import javafx.scene.layout.AnchorPane;
import project.Manager.ProductManager;
import project.dao.ProductManagerDAO;
import project.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController2 implements Initializable {

    @FXML VBox vBox;
    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane productGridPane;
    @FXML
    GridPane filmGridPane;
    @FXML
    GridPane bookGridPane;
    @FXML
    GridPane musicGridPane;

    //Product
    @FXML TextField idTextField;
    @FXML TextField nameTextField;
    @FXML TextField priceTextField;
    @FXML TextField importPriceTextField;
    @FXML TextField countTextField;
    @FXML ComboBox<String> categoryComboBox;
    @FXML TextArea descriptionTextArea;
    @FXML ComboBox<String> statusComboBox;
    ObservableList<String> observableList = FXCollections.observableArrayList("NHẠC","SÁCH","PHIM");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            categoryComboBox.setItems(observableList);
            statusComboBox.setItems(FXCollections.observableArrayList("Đang bán","Ngừng bán"));
            filmGridPane.setVisible(false);
            bookGridPane.setVisible(false);
            musicGridPane.setVisible(false);
            vBox.setVisible(false);
            descriptionTextArea.setWrapText(true);
        if (ProductController.noScene!=1){
            Product newProduct=ProductController.curentProduct;
            showProduct(newProduct);
            if (newProduct.getCategory()=="SÁCH"){
                bookGridPane.setVisible(true);
                showBook((Book) newProduct);
            }
            else if (newProduct.getCategory()=="PHIM"){
                filmGridPane.setVisible(true);
                showFilm((Film) newProduct);
            }
            else if (newProduct.getCategory()=="NHẠC"){
                musicGridPane.setVisible(true);
                showMusic((Music) newProduct);
            }
            if(ProductController.noScene==0) anchorPane.setDisable(true);
            if(ProductController.noScene==2) {
                vBox.setVisible(true);
                addButton.setVisible(false);
                idTextField.setDisable(true);
            }
        }
        else {
            vBox.setVisible(true);
        }
    }
    //Hiển thị Sản phẩm
    public void showProduct(Product product){
        idTextField.setText(product.getId());
        nameTextField.setText(product.getName());
        categoryComboBox.setValue(product.getCategory() + "");
        statusComboBox.setValue(product.getStatus() + "");
        priceTextField.setText(product.getPrice() + "");
        importPriceTextField.setText(product.getImportPrice() + "");
        countTextField.setText(product.getCount() + "");
        descriptionTextArea.setText(product.getDescription());
    }
    public void showBook(Book book){
        authorTextField.setText(book.getAuthor());
        languageBTextField.setText(book.getLanguage());
        translatorTextField.setText(book.getTranslator());
        numberOfPagesTextField.setText(book.getNumberOfPages()+"");
        publishingCompanyTextField.setText(book.getPublishingCompany());
        publishingYearTextField.setText(book.getPublishingYear()+"");
    }
    public void showFilm(Film film){
        movieGenreTextField.setText(film.getMovieGenre());
        directorTextField.setText(film.getDirector());
        mainActorTextField.setText(film.getMainActor());
        timeFTextField.setText(film.getTime());
        languageFTextField.setText(film.getLanguage());
        premiereTextField.setText(film.getPremiere()+"");
    }
    public void showMusic(Music music){
        musicGenreTextField.setText(music.getMusicGenre());
        singersTextField.setText(music.getSingers());
        producerTextField.setText(music.getProducer());
        timeMTextField.setText(music.getTime());
    }
    //Lấy dữ liệu từ giao diện
    public void setProduct (Product product) throws Exception{
        try {
            String s=idTextField.getText();
            String s1=new String() ;
            for (int i=0;i<s.length();i++)
                if(s.charAt(i)!=' ') s1=s1+s.charAt(i);
            product.setId(s1);
            product.setName(nameTextField.getText());
            product.setPrice(Integer.parseInt(priceTextField.getText()));
            product.setCategory(categoryComboBox.getValue());
            product.setStatus(statusComboBox.getValue());
            product.setImportPrice(Integer.parseInt(importPriceTextField.getText()));
            product.setCount(Integer.parseInt(countTextField.getText()));
            product.setDescription(descriptionTextArea.getText());
        } catch(NumberFormatException e){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText("'Số lượng', 'Giá bán', 'Giá mua' : không là 1 số");
            alert.show();
            throw new NumberFormatException();
        }
    }
    public void setBook(Book book) throws Exception{
        try{
            book.setAuthor(authorTextField.getText());
            book.setLanguage(languageBTextField.getText());
            book.setTranslator(translatorTextField.getText());
            book.setNumberOfPages(Integer.parseInt(numberOfPagesTextField.getText()));
            book.setPublishingCompany(publishingCompanyTextField.getText());
            book.setPublishingYear(Integer.parseInt(publishingYearTextField.getText()));
        } catch(NumberFormatException e){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText("'Số trang','Năm xuất bản' : không là 1 số");
            alert.show();
            throw new NumberFormatException();
        }
    }
    public void setFilm(Film film) throws Exception{
        try{
            film.setDirector(directorTextField.getText());
            film.setMainActor(mainActorTextField.getText());
            film.setMovieGenre(movieGenreTextField.getText());
            film.setTime(timeFTextField.getText());
            film.setPremiere(Integer.parseInt(premiereTextField.getText()));
            film.setLanguage(languageFTextField.getText());
        }catch(NumberFormatException e){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText("'Năm phát hành' : không là 1 số");
            alert.show();
            throw new NumberFormatException();
        }
    }
    public void setMusic(Music music) throws Exception{
        music.setSingers(singersTextField.getText());
        music.setProducer(producerTextField.getText());
        music.setMusicGenre(musicGenreTextField.getText());
        music.setTime(timeMTextField.getText());
    }
    public void choiceBoxChanged(ActionEvent e){
        String s= categoryComboBox.getValue();
        if (s=="SÁCH") {
            filmGridPane.setVisible(false);
            bookGridPane.setVisible(true);
            musicGridPane.setVisible(false);
        }
        else if (s=="PHIM"){
            filmGridPane.setVisible(true);
            bookGridPane.setVisible(false);
            musicGridPane.setVisible(false);
        }
        else if(s=="NHẠC"){
            filmGridPane.setVisible(false);
            bookGridPane.setVisible(false);
            musicGridPane.setVisible(true);
        }
    }
    //NHẠC
    @FXML TextField singersTextField;
    @FXML TextField producerTextField;
    @FXML TextField musicGenreTextField;
    @FXML TextField timeMTextField;
    //SÁCH
    @FXML TextField authorTextField;
    @FXML TextField languageBTextField;
    @FXML TextField translatorTextField;
    @FXML TextField numberOfPagesTextField;
    @FXML TextField publishingCompanyTextField;
    @FXML TextField publishingYearTextField;
    //PHIM
    @FXML TextField directorTextField;
    @FXML TextField mainActorTextField;
    @FXML TextField movieGenreTextField;
    @FXML TextField timeFTextField;
    @FXML TextField premiereTextField;
    @FXML TextField languageFTextField;
    //ADD
    @FXML Button addButton;
    public void add(){
        String s= categoryComboBox.getValue();
        Product newProduct=null;
        try {
            if (s == "SÁCH") {
                newProduct= new Book();
                setProduct(newProduct);
                setBook((Book) newProduct);
            } else if (s == "PHIM") {
                newProduct= new Film();
                setProduct(newProduct);
                setFilm((Film) newProduct);
            } else if (s == "NHẠC") {
                newProduct= new Music();
                setProduct(newProduct);
                setMusic((Music) newProduct);
            } else {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lỗi");
                alert.setContentText("Không thể thêm sản phẩm");
                alert.setHeaderText("Chưa chọn 'Thể loại'");
                alert.show();
                return;
            }
            if (ProductManager.containsID(newProduct.getId())==true){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lỗi");
                alert.setContentText("Không thể thêm sản phẩm");
                alert.setHeaderText("Sản phẩm bị trùng 'ID'");
                alert.show();
            }
            else {
                ProductController.productListController.add(newProduct);
                ProductManager.productList.add(newProduct);
                ProductManagerDAO.insert(newProduct);
                System.out.println(newProduct);
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            }
        } catch(Exception ex){
            System.out.println(ex);
        }

    }

    //CANCEL
    @FXML Button fixButton;
    public void fix(){
        String s= categoryComboBox.getValue();
        Product currentProduct=ProductController.curentProduct;
        try{
            if (s==currentProduct.getCategory()){
                setProduct(currentProduct);
                if (s == "SÁCH") setBook((Book) currentProduct);
                else if (s == "PHIM") setFilm((Film) currentProduct);
                else if (s == "NHẠC") setMusic((Music) currentProduct);
                ProductManagerDAO.update(currentProduct);
            } else {
                int x = ProductController.productListController.indexOf(currentProduct);
                ProductManager.delete(currentProduct);
                ProductManagerDAO.delete(currentProduct);
                Product newProduct = null;
                if (s == "SÁCH") {
                    newProduct= new Book();
                    setProduct(newProduct);
                    setBook((Book) newProduct);
                } else if (s == "PHIM") {
                    newProduct= new Film();
                    setProduct(newProduct);
                    setFilm((Film) newProduct);
                } else if (s == "NHẠC") {
                    newProduct= new Music();
                    setProduct(newProduct);
                    setMusic((Music) newProduct);
                }
                ProductController.productListController.set(x, newProduct);
                ProductManager.add(newProduct);
                ProductManagerDAO.insert(newProduct);
            }
        } catch(Exception ex){
            System.out.println(ex);
        }
        cloneTable.refresh();
        Stage stage = (Stage) fixButton.getScene().getWindow();
        stage.close();
    }
    static TableView<Product>  cloneTable;
    @FXML Button cancelButton;
    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
