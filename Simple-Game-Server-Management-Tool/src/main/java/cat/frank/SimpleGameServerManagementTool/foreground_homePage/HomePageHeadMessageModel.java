package cat.frank.SimpleGameServerManagementTool.foreground_homePage;

import jakarta.persistence.*;

@Entity
public class HomePageHeadMessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String message;

    public HomePageHeadMessageModel() {
    }

    public HomePageHeadMessageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HomePageHeadMessageModel{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
