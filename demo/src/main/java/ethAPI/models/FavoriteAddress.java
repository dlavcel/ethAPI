package ethAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavoriteAddress {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;
    @Column(unique=true)
    private String ethAddress;
}
