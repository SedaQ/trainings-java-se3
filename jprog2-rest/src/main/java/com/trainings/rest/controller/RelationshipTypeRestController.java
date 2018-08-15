package com.trainings.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.querydsl.core.types.Predicate;
import com.trainings.facade.api.PageResultResource;
import com.trainings.facade.api.dto.relationship.RelationshipTypeDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RelationshipTypeFacade;
import com.trainings.jpa.model.RelationshipType;
import com.trainings.rest.exceptions.ResourceNotFoundException;

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
@Api(value = "/relationship-types", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on RelationshipType Resource", 
          description = "allows operations on RelationshipType Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/relationship-types")
public class RelationshipTypeRestController {

	  private RelationshipTypeFacade relationshipTypeFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public RelationshipTypeRestController(RelationshipTypeFacade relationshipTypeFacade, ObjectMapper objectMapper) {
	    this.relationshipTypeFacade = relationshipTypeFacade;
	    this.objectMapper = objectMapper;
	  }

	  /**
	   * Get requested RelationshipType by id.
	   * 
	   * @param id of RelationshipType to return.
	   * @return Requested RelationshipType by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get RelationshipType by Id.", 
	      response = RelationshipTypeDTO.class,
	      nickname = "findRelationshipTypeById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find RelationshipType by ID", 
	                      description = "allows returning RelationshipType by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findRelationshipTypeById(@ApiParam(name = "RelationshipType Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields) {
	    try {
	      RelationshipTypeDTO relationshipTypeResource = relationshipTypeFacade.findById(id);
	      Squiggly.init(objectMapper, fields);
	      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, relationshipTypeResource), HttpStatus.OK);
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	  /**
	   * Get all RelationshipTypes.
	   * 
	   * @return all RelationshipTypes.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All RelationshipTypes.",
	      response = RelationshipTypeDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllRelationshipTypes",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all RelationshipTypes", 
	                      description = "allows returning RelationshipTypes."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllRelationshipTypes(@QuerydslPredicate(root = RelationshipType.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields) {
	    try {
	      PageResultResource<RelationshipTypeDTO> relationshipTypeResource = relationshipTypeFacade.findAll(predicate, pageable);
	      Squiggly.init(objectMapper, fields);
	      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, relationshipTypeResource), HttpStatus.OK);
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on

}
