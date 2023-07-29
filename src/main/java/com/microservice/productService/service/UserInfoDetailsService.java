//package com.microservice.productService.service;
//
////import com.microservice.productService.config.UserInfoUserDetails;
//import com.microservice.productService.entity.UserInfo;
//import com.microservice.productService.repository.UserInforRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserInfoDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserInforRepository userInforRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserInfo> userInfo =userInforRepository.findByName(username);
//
//        System.out.println(userInfo);
//
//        return userInfo
//                .map(UserInfoUserDetails::new)
//                .orElseThrow(()->new UsernameNotFoundException("Not found"));
////
//    }
//}
