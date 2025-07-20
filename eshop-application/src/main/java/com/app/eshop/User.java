package com.app.eshop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// This below annotation use when we are not define constructors manually
// We can define this both constructor manually, or we can use annotation to add constructor with arguments and constructor with no arguments
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this will generate a unique value fields
    private Long id;
    private String firstName;
    private  String lastName ;


//    We can define this both constructor manually or we can use annotation to add constructor with arguments and constructor with no arguments
//    This is default constructor -> This is mandatory
//    it requires when data get krna ho , so us case me user ka object bnane ke lie ek default constructor chaiye so this is used as a default constructor

//    public User() {
//    }
//
//    public User(Long id, String firstName, String lastName) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
}
