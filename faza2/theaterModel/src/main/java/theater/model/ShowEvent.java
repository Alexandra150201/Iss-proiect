package theater.model;

import java.io.Serializable;
import java.util.Objects;

public class ShowEvent extends Entity<Integer> implements Serializable {
    private Integer id;
    private String name;
    private Integer numberOfSeats;

    public ShowEvent(){};
    public ShowEvent(String name, Integer numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }
    public ShowEvent(Integer id,String name, Integer numberOfSeats) {
        this.id=id;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
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

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "ShowEvent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowEvent showEvent = (ShowEvent) o;
        return Objects.equals(name, showEvent.name) && Objects.equals(numberOfSeats, showEvent.numberOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfSeats);
    }
}
