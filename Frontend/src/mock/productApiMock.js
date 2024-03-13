export const productApiGet = {
    "id" : 135,
    "name" : "fixed product",
    "price" : 1000.0,
    "description" : "fixed product",
    "seller" : null,
    "_links" : {
      "self" : {
        "href" : "http://localhost:8081/api/v1/product/135"
      },
      "product" : {
        "href" : "http://localhost:8081/api/v1/product/135{?projection}",
        "templated" : true
      },
      "imageUrls" : {
        "href" : "http://localhost:8081/api/v1/product/135/imageUrls"
      },
      "attribute_value" : {
        "href" : "http://localhost:8081/api/v1/product/135/attribute_value"
      },
      "attributeSet" : {
        "href" : "http://localhost:8081/api/v1/product/135/attributeSet{?projection}",
        "templated" : true
      },
      "category_id" : {
        "href" : "http://localhost:8081/api/v1/product/135/category_id"
      }
    }
}

export const productImageApiGet = {
    "_embedded" : {
      "productImages" : [ {
        "id" : 181,
        "order" : 0,
        "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008788803.jpg",
        "_embedded" : {
          "product" : {
            "name" : "fixed product",
            "id" : 135,
            "description" : "fixed product",
            "seller" : null,
            "price" : "1000.0",
            "imageUrls" : [ {
              "id" : 181,
              "order" : 0,
              "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008788803.jpg"
            }, {
              "id" : 182,
              "order" : 1,
              "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008789142.jpg"
            } ],
            "attributeSet" : null,
            "category_id" : null,
            "_links" : {
              "imageUrls" : {
                "href" : "http://localhost:8081/api/v1/product/135/imageUrls"
              },
              "category_id" : {
                "href" : "http://localhost:8081/api/v1/product/135/category_id"
              },
              "attributeSet" : {
                "href" : "http://localhost:8081/api/v1/product/135/attributeSet{?projection}",
                "templated" : true
              },
              "attribute_value" : {
                "href" : "http://localhost:8081/api/v1/product/135/attribute_value"
              },
              "self" : {
                "href" : "http://localhost:8081/api/v1/product/135{?projection}",
                "templated" : true
              }
            }
          }
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:8081/api/v1/productImages/181"
          },
          "productImage" : {
            "href" : "http://localhost:8081/api/v1/productImages/181"
          },
          "product" : {
            "href" : "http://localhost:8081/api/v1/productImages/181/product{?projection}",
            "templated" : true
          }
        }
      }, {
        "id" : 182,
        "order" : 1,
        "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008789142.jpg",
        "_embedded" : {
          "product" : {
            "name" : "fixed product",
            "id" : 135,
            "description" : "fixed product",
            "seller" : null,
            "price" : "1000.0",
            "imageUrls" : [ {
              "id" : 181,
              "order" : 0,
              "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008788803.jpg"
            }, {
              "id" : 182,
              "order" : 1,
              "imageUrl" : "https://shopeeclonedata.s3.ap-southeast-2.amazonaws.com/product_image/fixed%20product_1703008789142.jpg"
            } ],
            "attributeSet" : null,
            "category_id" : null,
            "_links" : {
              "imageUrls" : {
                "href" : "http://localhost:8081/api/v1/product/135/imageUrls"
              },
              "category_id" : {
                "href" : "http://localhost:8081/api/v1/product/135/category_id"
              },
              "attributeSet" : {
                "href" : "http://localhost:8081/api/v1/product/135/attributeSet{?projection}",
                "templated" : true
              },
              "attribute_value" : {
                "href" : "http://localhost:8081/api/v1/product/135/attribute_value"
              },
              "self" : {
                "href" : "http://localhost:8081/api/v1/product/135{?projection}",
                "templated" : true
              }
            }
          }
        },
        "_links" : {
          "self" : {
            "href" : "http://localhost:8081/api/v1/productImages/182"
          },
          "productImage" : {
            "href" : "http://localhost:8081/api/v1/productImages/182"
          },
          "product" : {
            "href" : "http://localhost:8081/api/v1/productImages/182/product{?projection}",
            "templated" : true
          }
        }
      } ]
    },
    "_links" : {
      "self" : {
        "href" : "http://localhost:8081/api/v1/product/135/imageUrls"
      }
    }
  }
