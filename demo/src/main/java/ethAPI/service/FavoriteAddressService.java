package ethAPI.service;

import ethAPI.models.FavoriteAddress;
import ethAPI.repos.FavoriteAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteAddressService {

    private final FavoriteAddressRepository favoriteAddressRepository;

    public void addFavoriteAddress(String userId, String ethAddress) {
        boolean alreadyExists = favoriteAddressRepository.existsByEthAddress(ethAddress);
        if (alreadyExists) {
            throw new IllegalArgumentException("Address already in favorites");
        }

        FavoriteAddress favoriteAddress = new FavoriteAddress();
        favoriteAddress.setUserId(userId);
        favoriteAddress.setEthAddress(ethAddress);
        favoriteAddressRepository.save(favoriteAddress);
    }


    public void removeFavoriteAddress(String userId, String ethAddress) {
        FavoriteAddress favoriteAddress = favoriteAddressRepository.findByUserIdAndEthAddress(userId, ethAddress);
        if (favoriteAddress != null) {
            favoriteAddressRepository.delete(favoriteAddress);
        }
    }

    public List<FavoriteAddress> getUserFavorites(String userId) {
        return favoriteAddressRepository.findByUserId(userId);
    }
}
