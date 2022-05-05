package theater.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import theater.model.TheaterParticipant;
import theater.persistence.BookingRepository;
import theater.persistence.SeatRepository;
import theater.persistence.ShowEventRepository;
import theater.persistence.TheaterParticipantRepository;
import theater.service.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LogInController {
    @FXML
    public PasswordField parolaField;
    public TextField numeTextField;
    public CheckBox spectatorCheckBox;
    public CheckBox adminCheckBox;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Service srv;

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ops");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void unselectAdmin(ActionEvent event) throws IOException {
        adminCheckBox.setSelected(false);
    }
    public void unselectSpectator(ActionEvent event) throws IOException {
        spectatorCheckBox.setSelected(false);

    }
    public void logInButton(ActionEvent event) throws IOException {
        String nume = numeTextField.getText();
        if(nume.isEmpty() ){
            showAlert("no username!");
            return;
        }

        String password = parolaField.getText();
        if(password.isEmpty()){
            showAlert("no password!");
            return;
        }
        String type="";
        if(spectatorCheckBox.isSelected())
            type="spectator";
        else if(adminCheckBox.isSelected())
            type="admin";

        if(type.equals("")){
            showAlert("no checkbox selected");
            return;
        }
        TheaterParticipant theaterParticipant = srv.findParticipant(nume,type);
        if(theaterParticipant==null){
            showAlert("No user!");
            return;
        }

        if(!srv.findParticipant(nume,type).getPassword().equals(password)){
            showAlert("Incorrect password!");
            return;
        }

        if(type.equals("spectator")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/showEvents.fxml"));
            root = loader.load();
            ShowEventsController controller = loader.getController();
            controller.setService(this.srv);
            controller.setParticipant(theaterParticipant);}
        else if(type.equals("admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/manageBookings.fxml"));
            root = loader.load();
            ManageBookingsController controller = loader.getController();
            controller.setService(this.srv);
            controller.setParticipant(theaterParticipant);}
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void init() {
        BookingRepository bookingRepository=new BookingRepository();
        SeatRepository seatRepository=new SeatRepository();
        ShowEventRepository showEventRepository=new ShowEventRepository();
        TheaterParticipantRepository theaterParticipantRepository=new TheaterParticipantRepository();
        this.srv = new Service(bookingRepository,seatRepository,showEventRepository,theaterParticipantRepository);
    }

}
