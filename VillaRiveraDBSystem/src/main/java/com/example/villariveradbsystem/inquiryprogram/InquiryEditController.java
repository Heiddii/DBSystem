package com.example.villariveradbsystem.inquiryprogram;

import com.example.villariveradbsystem.App;
import com.example.villariveradbsystem.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class InquiryEditController extends App {

    @FXML
    private TextField inputCompanionCount, inputContactNumber, inputCustomerName;

    @FXML
    public ComboBox<String> dropMonth, dropDate, dropYear, dropTime;

    @FXML //Cottage Count Labels
    public Label labelCountFrancis, labelCountFatima, labelCountSimon, labelCountMiguel, labelCountGabriel;
    @FXML //Room Count Labels
    public Label labelCount2PAX, labelCount4PAX, labelCount10PAX;

    @FXML
    public Button  buttonSignout, buttonDatabase;
    @FXML
    public Button buttonSubmit = new Button();
    @FXML
    public Button buttonUpdate = new Button();

    String initFirstName, initLastName, initContactNumber;

    public void setEditParameters(String name, String count, String number, String date, String time, String francis, String simon,
                                  String fatima, String miguel, String gabriel, String PAX2, String PAX4, String PAX10){
        String[] editName = name.split(" ");

        initFirstName = editName[0];
        initLastName = editName[1];
        initContactNumber = number;

        inputCustomerName.setText(name);

        inputCompanionCount.setText(count);

        inputContactNumber.setText(number);

        String[] editDate = date.split("-");

        dropMonth.setValue(editDate[1]);
        dropDate.setValue(editDate[2]);
        dropYear.setValue(editDate[0]);
        dropTime.setValue(time);

        labelCountFrancis.setText(francis);
        labelCountSimon.setText(simon);
        labelCountFatima.setText(fatima);
        labelCountMiguel.setText(miguel);
        labelCountGabriel.setText(gabriel);

        labelCount2PAX.setText(PAX2);
        labelCount4PAX.setText(PAX4);
        labelCount10PAX.setText(PAX10);

        setDropLists();
    }

    public void setDropLists(){
        ObservableList<String> dropList = FXCollections.observableArrayList("01", "02", "03", "04",
                "05", "06", "07", "08", "09", "10", "11", "12");
        ObservableList<String> dateList = FXCollections.observableArrayList(
                "01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18",
                "19","20","21","22","23","24","25","26","27","28","29","30","31");
        ObservableList<String> yearList = FXCollections.observableArrayList("2022","2023","2024");
        ObservableList<String> timeList = FXCollections.observableArrayList("AM", "PM");

        dropMonth.setItems(dropList);
        dropDate.setItems(dateList);
        dropYear.setItems(yearList);
        dropTime.setItems(timeList);
    }

    public void buttonSignoutOnAction(ActionEvent e){
        Stage stage = (Stage) buttonSignout.getScene().getWindow();
        stage.close();
    }

    /**
     * Cottage Functions - Encapsulate when possible (avoid duplication for maintainability
     */
    public void buttonAddFrancisOnAction(ActionEvent e) {
        int count = Integer.parseInt(labelCountFrancis.getText())+1;
        labelCountFrancis.setText(Integer.toString(count));
    }
    public void buttonSubFrancisOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountFrancis.getText())-1;
        labelCountFrancis.setText(Integer.toString(count));
    }
    public void buttonAddSimonOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountSimon.getText())+1;
        labelCountSimon.setText(Integer.toString(count));
    }
    public void buttonSubSimonOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountSimon.getText())-1;
        labelCountSimon.setText(Integer.toString(count));
    }
    public void buttonAddFatimaOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountFatima.getText())+1;
        labelCountFatima.setText(Integer.toString(count));
    }
    public void buttonSubFatimaOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountFatima.getText())-1;
        labelCountFatima.setText(Integer.toString(count));
    }
    public void buttonAddMiguelOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountMiguel.getText())+1;
        labelCountMiguel.setText(Integer.toString(count));
    }
    public void buttonSubMiguelOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountMiguel.getText())-1;
        labelCountMiguel.setText(Integer.toString(count));
    }
    public void buttonAddGabrielOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountGabriel.getText())+1;
        labelCountGabriel.setText(Integer.toString(count));
    }
    public void buttonSubGabrielOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCountGabriel.getText())-1;
        labelCountGabriel.setText(Integer.toString(count));
    }

    /**
     * Room Functions - Encapsulate when possible
     */
    public void buttonAdd2PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount2PAX.getText())+1;
        labelCount2PAX.setText(Integer.toString(count));
    }
    public void buttonSub2PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount2PAX.getText())-1;
        labelCount2PAX.setText(Integer.toString(count));
    }
    public void buttonAdd4PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount4PAX.getText())+1;
        labelCount4PAX.setText(Integer.toString(count));
    }
    public void buttonSub4PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount4PAX.getText())-1;
        labelCount4PAX.setText(Integer.toString(count));
    }
    public void buttonAdd10PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount10PAX.getText())+1;
        labelCount10PAX.setText(Integer.toString(count));
    }
    public void buttonSub10PAXOnAction(ActionEvent e){
        int count = Integer.parseInt(labelCount10PAX.getText())-1;
        labelCount10PAX.setText(Integer.toString(count));
    }

    public void buttonUpdateOnAction(ActionEvent e){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String customerName = inputCustomerName.getText();
        String guestCount= inputCompanionCount.getText();
        String contactNumber = inputContactNumber.getText();

        String month = String.format("%s-%s-%s", dropYear.getValue(), dropMonth.getValue(), dropDate.getValue());
        String time = dropTime.getValue();

        String countFrancis = labelCountFrancis.getText();
        String countFatima = labelCountFatima.getText();
        String countGabriel = labelCountGabriel.getText();
        String countMiguel = labelCountMiguel.getText();
        String countSimon = labelCountSimon.getText();

        String count2PAX = labelCount2PAX.getText();
        String count4PAX = labelCount4PAX.getText();
        String count10PAX = labelCount10PAX.getText();

        try(Statement statement = connectDB.createStatement()){
            String[] name = customerName.split(" ");
            String date = String.format("%s-%s-%s", dropYear.getValue(), dropDate.getValue(), dropYear.getValue());

            String information = String.format("UPDATE villariveradb.inquiries SET firstName='%s', lastName='%s', guestCount='%s', contactNumber='%s', date='%s'," +
                            "time='%s', francis='%s', simon='%s', fatima='%s', miguel='%s', gabriel='%s', 2pax='%s', 4pax='%s', 10pax='%s'" +
                            "WHERE firstName = '%s' AND lastName = '%s' AND contactNumber ='%s'",
                    name[0], name[1], guestCount, contactNumber, date, time, countFrancis, countSimon, countFatima, countMiguel,
                    countGabriel, count2PAX, count4PAX, count10PAX, initFirstName, initLastName, initContactNumber);

            statement.executeUpdate(information);
        }
        catch(Exception f){
            f.printStackTrace();
        }

        Stage stage = (Stage) buttonUpdate.getScene().getWindow();
        stage.close();
    }

    public void buttonDatabaseOnAction(ActionEvent e){
        initDatabase();
    }

    public void clearValues(){
        inputCustomerName.setText("");
        inputCompanionCount.setText("");
        inputContactNumber.setText("");

        dropMonth.setValue("");
        dropDate.setValue(null);
        dropYear.setValue(null);
        dropTime.setValue("");

        labelCountFrancis.setText("0");
        labelCountSimon.setText("0");
        labelCountFatima.setText("0");
        labelCountMiguel.setText("0");
        labelCountGabriel.setText("0");

        labelCount2PAX.setText("0");
        labelCount4PAX.setText("0");
        labelCount10PAX.setText("0");
    }
}