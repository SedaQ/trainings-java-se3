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
import com.trainings.facade.api.dto.relationship.RelationshipDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RelationshipFacade;
import com.trainings.jpa.model.Relationship;
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
@Api(value = "/relationships", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on Relationship Resource", 
          description = "allows operations on Relationship Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/relationships")
public class RelationshipRestController {

	  private RelationshipFacade relationshipFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public RelationshipRestController(RelationshipFacade relationshipFacade, ObjectMapper objectMapper) {
	    this.relationshipFacade = relationshipFacade;
	    this.objectMapper = objectMapper;
	  }

	  /**
	   * Get requested Relationship by id.
	   * 
	   * @param id of Relationship to return.
	   * @return Requested by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get Relationship by Id.", 
	      response = RelationshipDTO.class,
	      nickname = "findRelationshipById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find Relationship by ID", 
	                      description = "allows returning Relationship by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findRelationshipById(@ApiParam(name = "Relationship Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      RelationshipDTO relationshipResource = relationshipFacade.findById(id);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, relationshipResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(relationshipResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	
	  /**
	   * Get all Relationships.
	   * 
	   * @return all Relationships.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All Relationships.",
	      response = RelationshipDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllRelationships",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all Relationships", 
	                      description = "allows returning Relationships."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllRelationships(@QuerydslPredicate(root = Relationship.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      PageResultResource<RelationshipDTO> relationshipResource = relationshipFacade.findAll(predicate, pageable);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, relationshipResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(relationshipResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on

}
