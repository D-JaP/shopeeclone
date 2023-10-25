package com.example.productservice.service;

import com.example.productservice.dto.ProductFormUpload;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductImage;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final S3Service s3Service;

    public String addProductFromFormUpload(ProductFormUpload form){
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());

        List<ProductImage> productImages = new ArrayList<>();
        for (MultipartFile file : form.getImageFile()){

            if(file.getContentType() != null){
                String url = s3Service.uploadFile(file, product.getName());
                ProductImage image = new ProductImage();
                image.setImageUrl(url);
                image.setProduct(product);

                productImages.add(image);
            }
        }

        product.setImageUrls(productImages);
        productRepository.save(product);

        return "add product successfully";
    }


}
