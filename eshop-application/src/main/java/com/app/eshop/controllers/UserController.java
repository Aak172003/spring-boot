package com.app.eshop.controllers;

import com.app.eshop.models.User;
import com.app.eshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Autowired is also use for Dependency-Injection
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

//    private  UserService userService;

//    Inject Via Constructor
//    public  UserController(UserService userService){
//        this.userService = userService;
//    }

    private final UserService userService;

//    RequestMapping is the same way to define which type of request this is and what is the endpoint of this



    //    @GetMapping("/api/users")

    //    @GetMapping("") -> if we have nothing to mention inside PostMapping then simply write like below
    @GetMapping
    //    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        System.out.println(userService.fetchAllUsers() + " get all user -------------");
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
        // return  ResponseEntity.ok(userService.fetchAllUsers());
        // return  userService.fetchAllUsers();

    }

    @GetMapping("/{id}")
    public ResponseEntity< User> getUser(@PathVariable Long id){
        System.out.println(userService.fetchAllUsers() + " get all user -------------");
//        User user = userService.fetchUser(id);
//        if(user == null){
//            return ResponseEntity.notFound().build();
//        }
//        return  ResponseEntity.ok(user);

//        This single line is replacement of above 5 lines
        return  userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

//    @PostMapping("") -> if we have nothing to mention inside PostMapping then simply write like below
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUserUser(user);
        return ResponseEntity.ok( "User Added Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity< String> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        System.out.println("id ::::::::::::::::::: "+ id);
        boolean updated =  userService.updateUser(id, updatedUser);
        if (updated){
            return  ResponseEntity.ok("User Updated Successfully");
        }
        return  ResponseEntity.notFound().build();
    }
}
