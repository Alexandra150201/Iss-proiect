package theater.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import theater.model.Seat;
import theater.model.ShowEvent;
import theater.model.TheaterParticipant;
import theater.service.Service;

import java.util.ArrayList;
import java.util.List;

public class ShowEventsController {
    private Service srv;
    private TheaterParticipant theaterParticipant;
    private  ShowEvent show;
    @FXML
    private Button b1;
    @FXML
    private Button b10;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b13;
    @FXML
    private Button b14;
    @FXML
    private Button b15;
    @FXML
    private Button b16;
    @FXML
    private Button b17;
    @FXML
    private Button b18;
    @FXML
    private Button b19;
    @FXML
    private Button b2;
    @FXML
    private Button b20;
    @FXML
    private Button b21;
    @FXML
    private Button b22;
    @FXML
    private Button b23;
    @FXML
    private Button b24;
    @FXML
    private Button b25;
    @FXML
    private Button b26;
    @FXML
    private Button b27;
    @FXML
    private Button b28;
    @FXML
    private Button b29;
    @FXML
    private Button b3;
    @FXML
    private Button b30;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private Label position;
    @FXML
    private Label price;
    @FXML
    private Label showEvent;
    @FXML
    private Label status;
    private List<Seat> selectedSeats=new ArrayList<>();
    private List<Button> buttons= new ArrayList<>();
    @FXML
    void bookSeatsButton(ActionEvent event) {
        if(name.getText().isEmpty() || phone.getText().isEmpty())
        {showAlert("Must introduce personal data");
            return;}
        if(selectedSeats.isEmpty()){
            showAlert("No selected seats");
            return;
        }
        srv.makeBooking(this.name.getText(),Double.valueOf(this.phone.getText()),selectedSeats,show);
        int i=0;
        for(Seat seat : srv.getSeats(show)){
            if(!seat.getStatus().name().equals("empty"))
                buttons.get(i).setDisable(true);
            i++;
        }
        this.name.setText("");
        this.phone.setText("");
        selectedSeats.clear();

    }

    @FXML
    void seatSelected(ActionEvent event) {
        Button b= (Button)(event.getSource());
        selectedSeats.add(srv.getSeatFromPosition(b.getId().substring(1)));
    }
    public void setService(Service srv) {
        this.srv=srv;
    }

    public void setParticipant(TheaterParticipant theaterParticipant) {
        this.theaterParticipant=theaterParticipant;
        init();

    }

    private void init() {
        ShowEvent showEvent=srv.getShowEvent();
        if(showEvent!=null)
        {this.showEvent.setText(showEvent.getName());
        this.show=showEvent;}
        else{
            showAlert("No available events");
            return;
        }
        setSeats();
    }

    private void setSeats() {
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        buttons.add(b10);
        buttons.add(b11);
        buttons.add(b12);
        buttons.add(b13);
        buttons.add(b14);
        buttons.add(b15);
        buttons.add(b16);
        buttons.add(b17);
        buttons.add(b18);
        buttons.add(b19);
        buttons.add(b20);
        buttons.add(b21);
        buttons.add(b22);
        buttons.add(b23);
        buttons.add(b24);
        buttons.add(b25);
        buttons.add(b26);
        buttons.add(b27);
        buttons.add(b28);
        buttons.add(b29);
        buttons.add(b30);
        Iterable<Seat> seats=srv.getSeats(show);
        int i=0;
        for(Seat seat : seats){
            if(seat.getStatus().name().equals("empty"))
            buttons.get(i).setDisable(false);
            i++;
        }
    }

    @FXML
    void showSeatData(MouseEvent event) {
        Button b= (Button)(event.getSource());
        Seat s= srv.getSeatFromPosition(b.getId().substring(1));
        this.position.setText(s.getPosition());
        this.price.setText(s.getPrice().toString());
        this.status.setText(s.getStatus().name());
    }

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ops");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


}
