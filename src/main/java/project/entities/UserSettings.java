package project.entities;

import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Setter
@Entity
@Table(name = "user_settings")
public class UserSettings {

    private Long id;
    private String phone;
    private String address;

    public UserSettings() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Size(max = 50, message = "Максимум 50 символов")
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Size(max = 50, message = "Максимум 50 символов")
    @Column(name = "address")
    public String getAddress() {
        return address;
    }
}
