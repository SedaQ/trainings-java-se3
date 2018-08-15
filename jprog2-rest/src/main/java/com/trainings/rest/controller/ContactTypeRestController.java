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
import com.trainings.facade.api.dto.contact.ContactTypeDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.ContactTypeFacade;
import com.trainings.jpa.model.ContactType;
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
@Api(value = "/contact-types", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on ContactType Resource", 
          description = "allows operations on ContactType Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/contact-types")
public class ContactTypeRestController {

	  private ContactTypeFacade contactTypeFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public ContactTypeRestController(ContactTypeFacade contactTypeFacade, ObjectMapper objectMapper) {
	    this.contactTypeFacade = contactTypeFacade;
	    this.objectMapper = objectMapper;
	  }

	  /**
	   * Get requested ContactType by id.
	   * 
	   * @param id of ContactType to return.
	   * @return Requested User by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get ContactType by Id.", 
	      response = ContactTypeDTO.class,
	      nickname = "findContactTypeById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find ContactType by ID", 
	                      description = "allows returning ContactType by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findContactTypeById(@ApiParam(name = "ContactType Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      ContactTypeDTO contactTypeResource = contactTypeFacade.findById(id);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, contactTypeResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(contactTypeResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  
	  //@formatter:on

	  /**
	   * Get all ContactTypes.
	   * 
	   * @return all ContactTypes.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All ContactTypes.",
	      response = ContactTypeDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllContactTypes",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all ContactTypes", 
	                      description = "allows returning ContactTypes."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllContactTypes(@QuerydslPredicate(root = ContactType.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      PageResultResource<ContactTypeDTO> contactTypeResource = contactTypeFacade.findAll(predicate, pageable);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, contactTypeResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(contactTypeResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	  
	  
}
