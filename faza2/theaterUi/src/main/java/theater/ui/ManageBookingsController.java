package theater.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import theater.model.*;
import theater.service.Service;

public class ManageBookingsController {
    private Service srv;
    private  ShowEvent show;
    @FXML
    private ListView<String> bookingsListView;
    @FXML
    private Label eventLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField seatsField;


    @FXML
    void addEvent(ActionEvent event) {
         if(show!=null){
             showAlert("Event already exists, you must delete first!!");
             return;}
         if(nameField.getText().equals("") && seatsField.getText().equals("")) {
             showAlert("You must introduce data first");
             return;}
         ShowEvent t=srv.addEvent(nameField.getText(),Integer.parseInt(seatsField.getText()));
         if(t!=null)
         {showAlert("Event added successfully!");
         this.show=t;
         this.eventLabel.setText(t.getName());
         }
         else
             showAlert("Couldn't add event!");
    }

    @FXML
    void deleteEvent(ActionEvent event) {
        if(show==null){
            showAlert("Event doesn't exist, you must add first!!");
            return;}
        ShowEvent t=srv.deleteEvent(show.getName(),show.getNumberOfSeats());
        this.eventLabel.setText("");
        this.show=null;
        this.bookingsListView.getItems().clear();
        if(t==null)
            showAlert("Event deleted successfully!");
        else
            showAlert("Couldn't delete event!");
    }

    @FXML
    void emptySeatsReport(ActionEvent event) {
        Iterable<Seat> seats=srv.getSeats(show);
        int n=0;
        for( Seat s : seats)
            if(s.getStatus().equals(StatusType.empty))
                n++;
        showAlert("Empty seats number is: "+String.valueOf(n)+"!");

    }

    @FXML
    void updateEvent(ActionEvent event) {
        if(show==null){
            showAlert("Event doesn't exist, you must add first!!");
            return;}
        if(nameField.getText().equals("")) {
            showAlert("You must introduce name first");
            return;}
        ShowEvent t=srv.updateEvent(nameField.getText(),show.getNumberOfSeats());
        if(t!=null && t.getName().equals(nameField.getText()))
        {showAlert("Event updated successfully!");
            show=t;
        this.eventLabel.setText(show.getName());}

        else
            showAlert("Couldn't update event!");
    }
    public void setService(Service srv) {
        this.srv=srv;
        init();
    }



    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ops");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void init() {
        ShowEvent showEvent=srv.getShowEvent();
        if(showEvent!=null) {
            this.eventLabel.setText(showEvent.getName());
            this.show = showEvent;
            this.bookingsListView.getItems().clear();
            Iterable<Booking> bookings = srv.getBookings(show);
            for (Booking b : bookings)
                this.bookingsListView.getItems().add(b.toString());
        }
    }
}
