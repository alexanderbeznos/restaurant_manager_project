package entities;

import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "user_settings")
public class UserSettings {

    private Long id;
    private String telephone;
    private String address;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }
}
