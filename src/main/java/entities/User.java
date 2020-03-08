package entities;

import lombok.Setter;
import javax.persistence.*;

@Setter
@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;
    private UserSettings userSettings;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
        public Long getId() {
        return id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_settings_id")
    public UserSettings getUserSettings() {
        return userSettings;
    }
}
