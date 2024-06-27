package lt.chomicenko.final_api.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Data
public class PosterDto {

    private String id;
    private String posterName;
    private String description;
    private String city;
    private String imageUrl;
    private String price;
    private String category;
    private Map<String, List<String>> comment;
    private List<Integer> rating;
    private ZonedDateTime createdAt;
    private List<UserDto> users;

    public PosterDto(String id, String posterName, String description, String city, String imageUrl, String price, String category, Map<String, List<String>> comment, List<Integer> rating, ZonedDateTime createdAt, List<UserDto> users) {
        this.id = id;
        this.posterName = posterName;
        this.description = description;
        this.city = city;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = createdAt;
        this.users = users;
    }

    @Data
    public static class UserDto {
        private String username;

        public UserDto(String username) {
            this.username = username;
        }
    }
}
