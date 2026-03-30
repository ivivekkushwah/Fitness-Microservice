package com.fitness.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


public enum UserRole {
    USER, ADMIN
}