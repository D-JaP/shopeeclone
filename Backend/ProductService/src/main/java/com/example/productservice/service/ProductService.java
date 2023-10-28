package com.example.productservice.service;

import com.example.productservice.dto.ProductFormChangeDetail;
import com.example.productservice.dto.ProductFormUpload;
import com.example.productservice.exception.PersistEntityFailedException;
import com.example.productservice.exception.ProductImageNotFoundException;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductImage;
import com.example.productservice.repository.ProductImageRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final DataUtils dataUtils;
    private final S3Service s3Service;

    /* API adding product to db and s3 */
    public String addProductFromFormUpload(ProductFormUpload form){
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        List<ProductImage> productImages = new ArrayList<>();
        int count =0;
        for (MultipartFile file : form.getImageFile()){

            if(file.getContentType() != null){

                String url = s3Service.uploadFile(file, product.getName());
                ProductImage image = new ProductImage();
                image.setImageUrl(url);
                image.setProduct(product);
//                image.setOrder(count);
                productImages.add(image);
                count++;
            }
        }


        product.setImageUrls(productImages);
        productRepository.save(product);
        log.info("product saved into database, " +product.toString());

        return "add product successfully";
    }

    @Transactional
    public String modifyProduct(long productId, ProductFormUpload form) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException());
        try {
//            join two list
            List<MultipartFile> files = form.getImageFile();
            int count = 5;
            List<ProductImage>  imglist = existingProduct.getImageUrls();

//            new Image handling

            if(files!= null){
                for (MultipartFile file : files){
                    if(file.getContentType() != null){
                        String url = s3Service.uploadFile(file, existingProduct.getName());
                        ProductImage image = new ProductImage();
                        image.setImageUrl(url);
                        image.setProduct(existingProduct);
                        image.setOrder(count);
                        imglist.add(image);
                        count++;
                    }
                }
            }

//            Copy every field except image.
            form.setImageFile(null);
            BeanUtils.copyProperties(form, existingProduct , dataUtils.getNullPropertyNames(form));
            productRepository.save(existingProduct);

        }
        catch (Exception ex){
            log.error(ex.getMessage());
            log.error(ex.getStackTrace().toString());
            throw new PersistEntityFailedException();
        }
        return "Successfully modify product";
    }

    public String deleteProductImage(Long id){
        ProductImage image = productImageRepository.findById(id).orElseThrow(() -> new ProductImageNotFoundException());
        productImageRepository.delete(image);
        return "Image deleted";
    }

    public String deleteProductImage(List<Long> idList){
        productImageRepository.deleteAllById(idList);
        return "Image deleted";
    }
}
