package ethAPI.controllers;

import ethAPI.service.EtherscanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eth")
public class EtherscanController {

    private final EtherscanService etherscanService;

    public EtherscanController(EtherscanService etherscanService) {
        this.etherscanService = etherscanService;
    }

    @GetMapping("/balance/{address}")
    public ResponseEntity<String> getBalance(@PathVariable String address) {
        return ResponseEntity.ok(etherscanService.getBalance(address));
    }
}

