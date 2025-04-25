package ethAPI.controllers;

import ethAPI.models.FavoriteAddress;
import ethAPI.service.FavoriteAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eth/favorites")
public class FavoriteAddressController {

    private final FavoriteAddressService favoriteAddressService;

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestHeader("Authorization") String token,
                                              @RequestParam String ethAddress) {
        String userId = getUserIdFromToken(token);
        try {
            favoriteAddressService.addFavoriteAddress(userId, ethAddress);
            return ResponseEntity.ok("Added to favorites");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Address already in favorites");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: " + e.getMessage());
        }
    }


    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(@RequestHeader("Authorization") String token,
                                                 @RequestParam String ethAddress) {
        String userId = getUserIdFromToken(token);
        favoriteAddressService.removeFavoriteAddress(userId, ethAddress);
        return ResponseEntity.ok("Address removed from favorites.");
    }

    @GetMapping
    public ResponseEntity<List<String>> getFavorites(@RequestHeader("Authorization") String token) {
        String userId = getUserIdFromToken(token);
        List<String> favorites = favoriteAddressService.getUserFavorites(userId)
                .stream()
                .map(FavoriteAddress::getEthAddress)
                .toList();
        return ResponseEntity.ok(favorites);
    }

    private String getUserIdFromToken(String token) {
        return "dummyUserId";
    }
}
