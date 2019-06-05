package com.cafe24.jblog2.exception;

public class PathException extends RuntimeException{
	private static final long serialVersionUID=1L;
	 public PathException() {
		 super("PathException Occurs");
	 }
	 public PathException(String message) {
		 super(message);
	 }
}
