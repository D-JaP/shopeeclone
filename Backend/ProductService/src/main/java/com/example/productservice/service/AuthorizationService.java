package com.example.productservice.service;


import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorizationService {
    private final ProductRepository productRepository;

    public boolean isProductOwner(HttpServletRequest request, long product_id){
        try {
            Long user_id = (Long) request.getAttribute("Shopee_User_ID");

            Product product = productRepository.findById(product_id).orElse(null);
            if ( product!=null  && user_id == product.getSeller()){
                System.out.println(user_id);
                return true;
            }

        }
        catch (RuntimeException exception){
            log.error(exception.getMessage());
            return  false;
        }
        log.info("Not product owner");
        return false;
    }

    public boolean hasRole(HttpServletRequest request, String role){
        try{
            List<String> authorities = (List<String>) request.getAttribute("Shopee_User_Authorities");
            if (authorities.contains(role)){
                return true;
            }
            log.info("Does not have role: " + role);
            return false;
        }
        catch (RuntimeException exception){
            log.error(exception.getMessage());
            return  false;
        }
    }

    public boolean hasAnyRole(HttpServletRequest request, String... roles){
        try{
            List<String> authorities = (List<String>) request.getAttribute("Shopee_User_Authorities");
            for (String role : roles){
                if (authorities.contains(role)){
                    return true;
                }
            }
            log.info("Does not have role: " + roles);
            return false;
        }
        catch (RuntimeException exception){
            log.error(exception.getMessage());
            return  false;
        }
    }


}
