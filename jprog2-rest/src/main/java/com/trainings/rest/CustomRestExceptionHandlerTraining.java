package com.trainings.rest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UrlPathHelper;

import com.trainings.rest.exceptions.BadGatewayException;
import com.trainings.rest.exceptions.BadRequestException;
import com.trainings.rest.exceptions.ForbiddenException;
import com.trainings.rest.exceptions.GatewayTimeoutException;
import com.trainings.rest.exceptions.HTTPVersionNotSupportedException;
import com.trainings.rest.exceptions.InsufficientStorageException;
import com.trainings.rest.exceptions.InternalServerErrorException;
import com.trainings.rest.exceptions.InvalidParameterException;
import com.trainings.rest.exceptions.LoopDetectedException;
import com.trainings.rest.exceptions.MethodNotAllowedException;
import com.trainings.rest.exceptions.MovedPermanentlyException;
import com.trainings.rest.exceptions.NetworkAuthenticationRequiredException;
import com.trainings.rest.exceptions.NoContentException;
import com.trainings.rest.exceptions.NotExtendedException;
import com.trainings.rest.exceptions.NotImplementedException;
import com.trainings.rest.exceptions.PayloadTooLargeException;
import com.trainings.rest.exceptions.ProxyAuthenticationRequiredException;
import com.trainings.rest.exceptions.RangeNotSatisfiableException;
import com.trainings.rest.exceptions.RequestTimeoutException;
import com.trainings.rest.exceptions.ResourceAlreadyExistingException;
import com.trainings.rest.exceptions.ResourceNotCreatedException;
import com.trainings.rest.exceptions.ResourceNotFoundException;
import com.trainings.rest.exceptions.ResourceNotModifiedException;
import com.trainings.rest.exceptions.ServiceUnavailableException;
import com.trainings.rest.exceptions.TooManyRequestsException;
import com.trainings.rest.exceptions.URITooLongException;
import com.trainings.rest.exceptions.UnauthorizedException;
import com.trainings.rest.exceptions.UnprocessableEntityException;
import com.trainings.rest.exceptions.UnsupportedMediaTypeException;
import com.trainings.rest.exceptions.VariantAlsoNegotiatesException;

/**
 * 
 * @author Pavel Seda (441048)
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomRestExceptionHandlerTraining extends ResponseEntityExceptionHandler {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(CustomRestExceptionHandlerTraining.class);
	protected static final UrlPathHelper URLHELPER = new UrlPathHelper();

	// 400
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleMethodArgumentNotValid({}, {}, {}, {})", ex, headers,
		// status, request);

		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setErrors(errors).setPath(request.getContextPath()).build();

		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleBindException({}, {}, {}, {})", ex, headers, status,
		// request);

		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setErrors(errors).setPath(request.getContextPath()).build();
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleTypeMismatch({}, {}, {}, {})", ex, headers, status,
		// request);

		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setError(error).setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleMissingServletRequestPart({}, {}, {}, {})", ex, headers,
		// status,
		// request);

		final String error = ex.getRequestPartName() + " part is missing";
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setError(error).setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		// LOGGER.debug("handleMissingServletRequestParameter({}, {}, {}, {})", ex,
		// headers, status,
		// request);

		final String error = ex.getParameterName() + " parameter is missing";
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setError(error).setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 404
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleNoHandlerFoundException({}, {}, {}, {})", ex, headers,
		// status, request);

		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.NOT_FOUND, ex.getLocalizedMessage())
				.setError(error).setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 405
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		// LOGGER.debug("handleHttpRequestMethodNotSupported({}, {}, {}, {})", ex,
		// headers, status,
		// request);

		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage())
				.setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 415
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		// LOGGER.debug("handleHttpMediaTypeNotSupported({}, {}, {}, {})", ex, headers,
		// status,
		// request);

		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				builder.substring(0, builder.length() - 2)).setPath(request.getContextPath()).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// Custom methods for handling own exceptions

	@ExceptionHandler(BadGatewayException.class)
	public ResponseEntity<Object> handleBadGatewayException(final BadGatewayException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleBadGatewayException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				BadGatewayException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(BadGatewayException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleBadRequestException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				BadRequestException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(BadRequestException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// @ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<Object> handleForbiddenException(final ForbiddenException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleForbiddenException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ForbiddenException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(ForbiddenException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(GatewayTimeoutException.class)
	public ResponseEntity<Object> handleGatewayTimeoutException(final GatewayTimeoutException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleGatewayTimeoutException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				GatewayTimeoutException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(GatewayTimeoutException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(HTTPVersionNotSupportedException.class)
	public ResponseEntity<Object> handleHTTPVersionNotSupportedException(final HTTPVersionNotSupportedException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleHTTPVersionNotSupportedException({}, {}, {})", ex,
		// request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				HTTPVersionNotSupportedException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(HTTPVersionNotSupportedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(InsufficientStorageException.class)
	public ResponseEntity<Object> handleInsufficientStorageException(final InsufficientStorageException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleInsufficientStorageException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				InsufficientStorageException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(InsufficientStorageException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> handleInternalServerErrorException(final InternalServerErrorException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleInternalServerErrorException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				InternalServerErrorException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(InternalServerErrorException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<Object> handleInvalidParameterException(final InvalidParameterException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleInvalidParameterException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				InvalidParameterException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(InvalidParameterException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(LoopDetectedException.class)
	public ResponseEntity<Object> handleLoopDetectedException(final LoopDetectedException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleLoopDetectedException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				LoopDetectedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(LoopDetectedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(MethodNotAllowedException.class)
	public ResponseEntity<Object> handleMethodNotAllowedException(final MethodNotAllowedException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleMethodNotAllowedException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				MethodNotAllowedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(MethodNotAllowedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(MovedPermanentlyException.class)
	public ResponseEntity<Object> handleMovedPermanentlyException(final MovedPermanentlyException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleMovedPermanentlyException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				MovedPermanentlyException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(MovedPermanentlyException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(NetworkAuthenticationRequiredException.class)
	public ResponseEntity<Object> handleNetworkAuthenticationRequiredException(
			final NetworkAuthenticationRequiredException ex, final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleNetworkAuthenticationRequiredException({}, {}, {})", ex,
		// request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				NetworkAuthenticationRequiredException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage()).setError(
						NetworkAuthenticationRequiredException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<Object> handleNoContentException(final NoContentException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleNoContentException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				NoContentException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(NoContentException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(NotExtendedException.class)
	public ResponseEntity<Object> handleNotExtendedException(final NotExtendedException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleNotExtendedException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				NotExtendedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(NotExtendedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(NotImplementedException.class)
	public ResponseEntity<Object> handleNotImplementedException(final NotImplementedException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleNotImplementedException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				NotImplementedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(NotImplementedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(PayloadTooLargeException.class)
	public ResponseEntity<Object> handlePayloadTooLargeException(final PayloadTooLargeException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handlePayloadTooLargeException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				PayloadTooLargeException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(PayloadTooLargeException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(ProxyAuthenticationRequiredException.class)
	public ResponseEntity<Object> handleProxyAuthenticationRequiredException(
			final ProxyAuthenticationRequiredException ex, final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleProxyAuthenticationRequiredException({}, {}, {})", ex,
		// request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ProxyAuthenticationRequiredException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(
								ProxyAuthenticationRequiredException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(RangeNotSatisfiableException.class)
	public ResponseEntity<Object> handleRangeNotSatisfiableException(final RangeNotSatisfiableException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleRangeNotSatisfiableException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				RangeNotSatisfiableException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(RangeNotSatisfiableException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(RequestTimeoutException.class)
	public ResponseEntity<Object> handleRequestTimeoutException(final RequestTimeoutException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleRequestTimeoutException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				RequestTimeoutException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(RequestTimeoutException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(ResourceAlreadyExistingException.class)
	public ResponseEntity<Object> handleResourceAlreadyExistingException(final ResourceAlreadyExistingException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleResourceAlreadyExistingException({}, {}, {})", ex,
		// request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ResourceAlreadyExistingException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(ResourceAlreadyExistingException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// resource not created exception
	@ExceptionHandler({ ResourceNotCreatedException.class })
	public ResponseEntity<Object> handleResourceNotCreatedException(final ResourceNotCreatedException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleResourceNotCreatedException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ResourceNotCreatedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(ResourceNotCreatedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// handle resource not found exceptions e.g. ~/{id} which does not exists
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleResourceNotFoundException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ResourceNotFoundException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(ResourceNotFoundException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// resource not created exception
	@ExceptionHandler({ ResourceNotModifiedException.class })
	public ResponseEntity<Object> handleResourceNotModifiedException(final ResourceNotModifiedException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleResourceNotModifiedException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ResourceNotModifiedException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(ResourceNotModifiedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ServiceUnavailableException.class })
	public ResponseEntity<Object> handleServiceUnavailableException(final ServiceUnavailableException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleServiceUnavailableException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				ServiceUnavailableException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(ServiceUnavailableException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ TooManyRequestsException.class })
	public ResponseEntity<Object> handleTooManyRequestsException(final TooManyRequestsException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleTooManyRequestsException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				TooManyRequestsException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(TooManyRequestsException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ UnauthorizedException.class })
	public ResponseEntity<Object> handleUnauthorizedException(final UnauthorizedException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleUnauthorizedException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				UnauthorizedException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(UnauthorizedException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ UnprocessableEntityException.class })
	public ResponseEntity<Object> handleUnprocessableEntityException(final UnprocessableEntityException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleUnprocessableEntityException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				UnprocessableEntityException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(UnprocessableEntityException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ UnsupportedMediaTypeException.class })
	public ResponseEntity<Object> handleUnsupportedMediaTypeException(final UnsupportedMediaTypeException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleUnsupportedMediaTypeException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				UnsupportedMediaTypeException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(UnsupportedMediaTypeException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ URITooLongException.class })
	public ResponseEntity<Object> handleURITooLongException(final URITooLongException ex, final WebRequest request,
			HttpServletRequest req) {
		// LOGGER.debug("handleURITooLongException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				URITooLongException.class.getAnnotation(ResponseStatus.class).value(), ex.getLocalizedMessage())
						.setError(URITooLongException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ VariantAlsoNegotiatesException.class })
	public ResponseEntity<Object> handleVariantAlsoNegotiatesException(final VariantAlsoNegotiatesException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleVariantAlsoNegotiatesException({}, {}, {})", ex, request,
		// req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(
				VariantAlsoNegotiatesException.class.getAnnotation(ResponseStatus.class).value(),
				ex.getLocalizedMessage())
						.setError(VariantAlsoNegotiatesException.class.getAnnotation(ResponseStatus.class).reason())
						.setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// Existing Java Exceptions

	// access denied
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		// LOGGER.debug("handleAccessDeniedException({}, {})", ex, request);

		return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	// handle illegal argument exceptions e.g. given payload is not valid against
	// draft-v4 schema
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(final IllegalArgumentException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleIllegalArgumentException({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage())
				.setError("Illegal Argument").setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleMethodArgumentTypeMismatch({}, {}, {})", ex, request,
		// req);

		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setError(error).setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleConstraintViolation({}, {}, {})", ex, request, req);

		final List<String> errors = new ArrayList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage())
				.setErrors(errors).setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request, HttpServletRequest req) {
		// LOGGER.debug("handleAll({}, {}, {})", ex, request, req);

		final ApiError apiError = new ApiError.ApiErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getLocalizedMessage()).setError("error occurred").setPath(URLHELPER.getRequestUri(req)).build();
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
