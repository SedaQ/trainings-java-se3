package com.trainings.facade.exceptions;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class FacadeLayerException extends RuntimeException {

	public FacadeLayerException() {
	}

	public FacadeLayerException(String message) {
		super(message);
	}

	public FacadeLayerException(Throwable throwable) {
		super(throwable);
	}

	public FacadeLayerException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
