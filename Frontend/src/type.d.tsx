interface ProductApiGetResponse {
  id: number;
  name: string;
  price: number;
  description: string;
  seller: any;
  quantity:number;
  _links: {
    self: {
      href: string;
    };
    product: {
      href: string;
      templated: boolean;
    };
    imageUrls: {
      href: string;
    };
    attribute_value: {
      href: string;
    };
    attributeSet: {
      href: string;
      templated: boolean;
    };
    category_id: {
      href: string;
    };
  };
}

interface ProductImageApiGet {
  _embedded: {
    productImages: {
      id: number;
      imageUrl: string;
      order: number;
      product: {
        attributeSet: {
          attributes: {
            _require: boolean;
            attributeSet: any[];
            dataType: string;
            default_value: string;
            id: number;
            name: string;
          }[];
          id: number;
          name: string;
          products: any[];
        };
        attribute_value: {
          attribute: {
            _require: boolean;
            attributeSet: any[];
            dataType: string;
            default_value: string;
            id: number;
            name: string;
          };
          id: number;
          product_id: number;
          type: string;
          value: string;
        }[];
        category_id: {
          id: number;
          level: number;
          name: string;
        };
        description: string;
        id: number;
        imageUrls: any[];
        name: string;
        price: number;
        seller: number;
      };
    }[];
  };
  _links: {
    [key: string]: object;
  };
}
