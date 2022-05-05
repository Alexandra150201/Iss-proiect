package theater.model;
import java.io.Serializable;
import java.util.Objects;

public class Booking extends Entity<Integer> implements Serializable {
    private Integer id;
    private String name;
    private Double telephone;
    private ShowEvent showEvent;

    public Booking(){};
    public Booking(String name, Double telephone,ShowEvent showEvent) {
        this.name = name;
        this.telephone = telephone;
        this.showEvent = showEvent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTelephone() {
        return telephone;
    }

    public void setTelephone(Double telephone) {
        this.telephone = telephone;
    }


    public ShowEvent getShowEvent() {
        return showEvent;
    }

    public void setShowEvent(ShowEvent showEvent) {
        this.showEvent = showEvent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(name, booking.name) && Objects.equals(telephone, booking.telephone) && Objects.equals(showEvent, booking.showEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, telephone,showEvent);
    }
}
