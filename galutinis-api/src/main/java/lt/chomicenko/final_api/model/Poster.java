package lt.chomicenko.final_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posters")
public class Poster {
    @Id
    private String id;
    private String posterName;
    private String description;
    private String city;
    private String imageUrl;
    private String price;
    private String category;

    @ElementCollection
    private Map<String, List<String>> comment = new HashMap<>();

    private List<Integer> rating = new ArrayList<>();

    @ManyToMany(mappedBy = "followedPosters")
    private List<User> users = new ArrayList<>();

    private ZonedDateTime createdAt;

    public Poster(String posterName, String description, String city, String imageUrl, String price, String category, Map<String, List<String>> comment, List<Integer> rating) {
        this.posterName = posterName;
        this.description = description;
        this.city = city;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
        this.comment = comment != null ? comment : new HashMap<>();
        this.rating = rating != null ? rating : new ArrayList<>();
    }

    public Poster(String id, String posterName, String description, String city, String imageUrl, String price, String category) {
        this.id = id;
        this.posterName = posterName;
        this.description = description;
        this.city = city;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
    }

    @PrePersist
    public void onPrePersist() {
        createdAt = ZonedDateTime.now();
    }

    public void addUser(User user) {
        users.add(user);
        user.getFollowedPosters().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getFollowedPosters().remove(this);
    }
}
