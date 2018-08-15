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
import com.trainings.facade.api.dto.user.UserDTO;
import com.trainings.facade.exceptions.FacadeLayerException;
import com.trainings.facade.iface.UserFacade;
import com.trainings.jpa.model.User;
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
@Api(value = "/users", 
  consumes = "application/json or application/xml", 
  authorizations = {
    @Authorization(value = "sampleoauth", 
      scopes = {
        @AuthorizationScope(
          scope = "HTTP operations on User Resource", 
          description = "allows operations on User Resource."
        )
      }
    )
  }
)
//@formatter:on
@RestController
@RequestMapping(value = "/users")
public class UserRestController {

	  private UserFacade userFacade;
	  private ObjectMapper objectMapper;

	  @Autowired
	  public UserRestController(UserFacade userFacade, ObjectMapper objectMapper) {
	    this.userFacade = userFacade;
	    this.objectMapper = objectMapper;
	  }

	  /**
	   * Get requested User by id.
	   * 
	   * @param id of User to return.
	   * @return Requested User by id.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET", 
	      value = "Get User by Id.", 
	      response = UserDTO.class,
	      nickname = "findUserById",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find User by ID", 
	                      description = "allows returning User by ID."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findUserById(@ApiParam(name = "User Id") @PathVariable Long id,
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields) {
	    try {
	      UserDTO userResource = userFacade.findById(id);
	      Squiggly.init(objectMapper, fields);
	      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, userResource), HttpStatus.OK);
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on

	  public ResponseEntity<Object> findUserAddress(@ApiParam(name = "User Id") @PathVariable Long id,
		      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
		      @RequestParam(value = "fields", required = false) String fields){
	    try {
	      UserDTO userResource = userFacade.findById(id);
	      Squiggly.init(objectMapper, fields);
	      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, userResource), HttpStatus.OK);
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	 
	  
	  /**
	   * Get all Users.
	   * 
	   * @return all Users.
	   */
	  //@formatter:off
	  @ApiOperation(httpMethod = "GET",
	      value = "Get All Users.",
	      response = UserDTO.class,
	      responseContainer = "Page",
	      nickname = "findAllUsers",
	      produces = "application/json or application/xml",
	      authorizations = {
	          @Authorization(value = "sampleoauth", 
	              scopes = {
	                  @AuthorizationScope(
	                      scope = "find all Users", 
	                      description = "allows returning Users."
	                  )
	              }
	          )
	      }
	  )
	  @ApiResponses(value = {
	      @ApiResponse(code = 404, message = "The requested resource was not found.") 
	  })
	  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	  public ResponseEntity<Object> findAllUsers(@QuerydslPredicate(root = User.class) Predicate predicate, Pageable pageable,
	      @RequestParam MultiValueMap<String, String> parameters, 
	      @ApiParam(value = "Fields which should be returned in REST API response", required = false) 
	      @RequestParam(value = "fields", required = false) String fields) {
	    try {
	      PageResultResource<UserDTO> userResource = userFacade.findAll(predicate, pageable);
	      Squiggly.init(objectMapper, fields);
	      return new ResponseEntity<>(SquigglyUtils.stringify(objectMapper, userResource), HttpStatus.OK);
	    } catch (FacadeLayerException ex) {
	      throw new ResourceNotFoundException(ex.getLocalizedMessage());
	    }
	  }
	  //@formatter:on
	  
}
