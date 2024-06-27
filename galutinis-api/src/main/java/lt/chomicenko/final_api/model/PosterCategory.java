package lt.chomicenko.final_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class PosterCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String posterCategory;

    public PosterCategory(String posterCategory) {
        this.posterCategory = posterCategory;
    }
}
