package theater.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

public class TheaterParticipant extends Entity<Integer> implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private AccountType type;


    public TheaterParticipant(){};
    public TheaterParticipant(String username, String password, AccountType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type =type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TheaterParticipant that = (TheaterParticipant) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash( username, password, type);
    }

    @Override
    public String toString() {
        return "TheaterParticipant{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}

