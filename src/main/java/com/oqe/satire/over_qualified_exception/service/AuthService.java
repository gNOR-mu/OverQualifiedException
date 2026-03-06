package com.oqe.satire.over_qualified_exception.service;

import com.oqe.satire.over_qualified_exception.model.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AuthService {

    // Simular base de datos en memoria para los usuarios
    private final Map<String, Customer> memoryDb = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean register(String nickname, String password) {
        if (memoryDb.containsKey(nickname)) {
            return false;
        }

        Customer newCustomer = new Customer();
        newCustomer.setId(idGenerator.getAndIncrement());
        newCustomer.setNickname(nickname);
        newCustomer.setPassHash(passwordEncoder.encode(password)); // Almacenamiento seguro con BCrypt

        memoryDb.put(nickname, newCustomer);
        return true;
    }

    public Optional<Customer> login(String nickname, String password) {
        Customer customer = memoryDb.get(nickname);
        if (customer != null && passwordEncoder.matches(password, customer.getPassHash())) {
            return Optional.of(customer);
        }
        return Optional.empty();
    }
}
