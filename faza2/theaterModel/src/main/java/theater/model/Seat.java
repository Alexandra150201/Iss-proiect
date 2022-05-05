package theater.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

public class Seat extends Entity<Integer> implements Serializable{
    private Integer id;
    private String position;
    private Integer number;
    private Float price;
    private StatusType status;
    private Booking booking;

    public Seat(){};

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", status=" + status +
                ", booking=" + booking +
                '}';
    }

    public Seat(String position, Integer number, Float price, StatusType status, Booking booking) {
        this.position = position;
        this.number = number;
        this.price = price;
        this.status = status;
        this.booking=booking;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }


    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(position, seat.position) && Objects.equals(number, seat.number) && Objects.equals(price, seat.price) && status == seat.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number, price, status);
    }
}
