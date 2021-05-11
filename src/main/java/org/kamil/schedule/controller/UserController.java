package org.kamil.schedule.controller;

import org.kamil.schedule.model.Product;
import org.kamil.schedule.model.Role;
import org.kamil.schedule.model.User;
import org.kamil.schedule.model.UserRole;
import org.kamil.schedule.payload.UserDto;
import org.kamil.schedule.repository.RoleRepository;
import org.kamil.schedule.repository.UserRepository;
import org.kamil.schedule.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    EntityManager entityManager;


    @RequestMapping("/signup")
    public String getSigUpPage(){
        return "register";
    }

    @RequestMapping("/user/{userId}/save")
    public String  userForm(ModelMap model, @PathVariable("userId") Long userId){

        if(userId==0){

            model.addAttribute("user",new UserDto());
        }

        else if(userId>0){
            User user=userRepository.getOne(userId);

            model.addAttribute("user",user);
        }

        return "register";
    }

    @PostMapping("/user/save")
    public String  save(ModelMap model, UserDto user){

        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setfName(user.getFName());
        user1.setlName(user.getLName());
        user1.setTelephone(user.getTelephone());
        user1.setUsername(user.getUsername());
        user1.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Long roleId = user.getRoleString().equals("Teacher") ? Long.valueOf(2) : Long.valueOf(3);

        Role role=roleRepository.getOne(roleId);
        UserRole userRole=new UserRole();
        userRole.setRole(role);
        userRole.setUser(user1);
        userRepository.save(user1);
        userRoleRepository.save(userRole);

        return "redirect:/login";
    }
    @RequestMapping("user/list")
    public String getUserPage(){
        return "users";
    }


    @RequestMapping("user/{id}/profile")
    public String getUserProfilePage(ModelMap model, @PathVariable("id") long id){
        User user = userRepository.getOne(id);
        UserRole userRole = userRoleRepository.findAllByUser(user).get(0);
        String baseQuery = "select * from product where user_id="+id;
        Query query=entityManager.createNativeQuery(baseQuery, Product.class);
        List<Product> products = query.getResultList();
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        model.addAttribute("role", userRole.getRole());
        return "blog-details";
    }
}
