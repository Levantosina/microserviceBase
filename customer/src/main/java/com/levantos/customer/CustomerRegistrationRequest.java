package com.levantos.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
