package com.trainings.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.address.AddressDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.AddressFacade;
import com.trainings.jpa.model.Address;
import com.trainings.rest.exceptions.ResourceNotFoundException;
import com.trainings.rest.utils.HttpHeadersAcceptAndContentType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

/**
 * @author Pavel Šeda
 *
 */
//@formatter:off
@Api(value = "/addresses", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on Address Resource", 
          description = "allows operations on Address Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/addresses")
public class AddressRestController {

	  private AddressFacade addressFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public AddressRestController(AddressFacade addressFacade, ObjectMapper objectMapper) {
	    this.addressFacade = addressFacade;
	    this.objectMapper = objectMapper;
	  }
	  
	  /**
	   * Get requested Address by id.
	   * 
	   * @param id of Address to return.
	   * @return Requested Address by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get Address by Id.", 
	      response = AddressDTO.class,
	      nickname = "findAddressById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find Address by ID", 
	                      description = "allows returning Address by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAddressById(@ApiParam(name = "Address Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      AddressDTO addressResource = addressFacade.findById(id);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, addressResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(addressResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on

	  /**
	   * Get all Address.
	   * 
	   * @return all Address.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All Address.",
	      response = AddressDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllAddress",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all Address", 
	                      description = "allows returning Address."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllAddresses(@QuerydslPredicate(root = Address.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      PageResultResource<AddressDTO> addressResource = addressFacade.findAll(predicate, pageable);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, addressResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(addressResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	  
}
