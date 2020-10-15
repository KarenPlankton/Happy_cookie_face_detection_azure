package codeclan.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="photos")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "imageName", unique = false, nullable = false, length = 100)
    private String imageName;

    public Photo() {
    }
    public Photo(String name) {
        this.imageName=name;
    }

    public String getImageName() {
        return imageName;
    }

    public Long getId() {
        return id;
    }

}
