package com.example.productservice.dto;

import com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@SuperBuilder
public class ProductFormChangeDetail extends ProductFormUpload {
    private List<Base64Data> imageList;
}
