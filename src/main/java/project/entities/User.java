package project.entities;

import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
public class User {

    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;
    private UserSettings userSettings;
    private Set<Roles> roles;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Column(name = "login", unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    //изменить на 7
    @Size(min = 1, message = "Минимум 7 символов")
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Size(max = 50, message = "Максимум 50 символов")
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Size(max = 50, message = "Максимум 50 символов")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Size(max = 50, message = "Максимум 50 символов")
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_settings_id")
    public UserSettings getUserSettings() {
        return userSettings;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id"))
    public Set<Roles> getRoles() {
        return roles;
    }
}
