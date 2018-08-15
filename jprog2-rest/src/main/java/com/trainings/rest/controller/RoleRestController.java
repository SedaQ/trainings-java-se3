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
import com.trainings.facade.api.dto.role.RoleDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.RoleFacade;
import com.trainings.jpa.model.Role;
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
@Api(value = "/roles", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on Role Resource", 
          description = "allows operations on Role Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/roles")
public class RoleRestController {

	  private RoleFacade roleFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public RoleRestController(RoleFacade roleFacade, ObjectMapper objectMapper) {
	    this.roleFacade = roleFacade;
	    this.objectMapper = objectMapper;
	  }

	  /**
	   * Get requested Role by id.
	   * 
	   * @param id of Role to return.
	   * @return Requested Role by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get Role by Id.", 
	      response = RoleDTO.class,
	      nickname = "findRoleById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find Role by ID", 
	                      description = "allows returning Role by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findUserById(@ApiParam(name = "Role Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      RoleDTO roleResource = roleFacade.findById(id);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, roleResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(roleResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	  /**
	   * Get all Roles.
	   * 
	   * @return all Roles.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All Roles.",
	      response = RoleDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllRoles",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all Roles", 
	                      description = "allows returning Roles."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllRoles(@QuerydslPredicate(root = Role.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields,
	      @RequestHeader HttpHeaders headers) {
	    try {
	      PageResultResource<RoleDTO> roleResource = roleFacade.findAll(predicate, pageable);
	      if(HttpHeadersAcceptAndContentType.isJson(headers)) {
		      Squiggly.init(objectMapper, fields);
		      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, roleResource), HttpStatus.OK);
	      } else {
	    	  return new ResponseEntity<>(roleResource, HttpStatus.OK);
	      }
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on

}
