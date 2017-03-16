package com.test.product;

import static org.junit.Assert.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.example.GetProductTypeByName;
import org.test.example.GetProductTypeByNameResponse;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = MainExample.class)
public class ProductServiceImplTest {

	@Autowired
	private ProducerTemplate template;
	
	@Autowired 
    private CamelContext context; 

	@Test
    public void testGetProductTypeByName() throws Exception {
		
		context.getRouteDefinition("product-webservice").adviceWith(context, new AdviceWithRouteBuilder() {			
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct:cxfIn");
				
			}
		} );
		context.start();

        GetProductTypeByName searchProduct = new GetProductTypeByName();
        searchProduct.setName("TestProductType");

        GetProductTypeByNameResponse productType = (GetProductTypeByNameResponse) template.requestBodyAndHeader("direct:cxfIn", searchProduct, "operationName", "getProductTypeByName");
        assertEquals(productType.getProductType().get(0).getName(), "TestProductType");
    }
}

