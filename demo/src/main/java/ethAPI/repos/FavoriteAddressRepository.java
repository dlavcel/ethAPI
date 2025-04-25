package ethAPI.repos;

import ethAPI.models.FavoriteAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteAddressRepository extends JpaRepository<FavoriteAddress, Long> {
    List<FavoriteAddress> findByUserId(String userId);
    FavoriteAddress findByUserIdAndEthAddress(String userId, String ethAddress);
    boolean existsByEthAddress(String ethAddress);
}
