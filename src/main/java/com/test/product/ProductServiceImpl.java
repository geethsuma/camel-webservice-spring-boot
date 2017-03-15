package com.test.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.example.CreateProductType;
import org.test.example.GetAllProductTypesResponse;
import org.test.example.GetProductTypeByName;
import org.test.example.GetProductTypeByNameResponse;
import org.test.example.ProductAvailabilityEnum;
import org.test.example.ProductType;

public class ProductServiceImpl {

	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	static List<ProductType> productTypes = new ArrayList<ProductType>();
	
	@SuppressWarnings("unused")
	private void initaliseProductTypes() {
        ProductType productType = new ProductType();
        
        productType.setId("123");
        productType.setName("TestProductType");
        productType.setProductAvailabilityStatus(ProductAvailabilityEnum.AVAILABLE_FOR_NEW_BUSINESS);
        
        productTypes.add(productType);
    }
	
	public GetAllProductTypesResponse getAllProductTypes() {

    	GetAllProductTypesResponse response = new GetAllProductTypesResponse();
        response.getProductType().addAll(productTypes);
        return response;
    }

    public GetProductTypeByNameResponse getProductTypeByName(@Body GetProductTypeByName searchProduct) {

        List<ProductType> result = new ArrayList<ProductType>();
        // Search for Product type using name as key
        for(ProductType product : productTypes) {
            if (product.getName().equals(searchProduct.getName())) {
               result.add(product);
               log.info(">> Product Type Matched !");
               break;
            }
        }

        GetProductTypeByNameResponse response = new GetProductTypeByNameResponse();
        response.getProductType().addAll(result);

        return response;

    }

    public CreateProductType createProductType(@Body CreateProductType pdt) {

    	CreateProductType createProductType = new CreateProductType();
        ProductType productType = new ProductType();
        productType.setName(pdt.getProductType().getName());
        productType.setId(pdt.getProductType().getId());
        productType.setProductAvailabilityStatus(pdt.getProductType().getProductAvailabilityStatus());
        productTypes.add(productType);

        log.info(">> ProductType created and added in the list.");
        createProductType.setProductType(productType);
        return createProductType;
    }	
	
}
