package com.rewards.exception;

/**
 * Thrown when customer or data is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}
}