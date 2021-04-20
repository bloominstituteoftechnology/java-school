package com.lambdaschool.schools.exceptions;

import javax.persistence.EntityExistsException;

public class ResourceFoundException extends EntityExistsException {
  public ResourceFoundException(String message) {
    super("Found an issue with School: " + message);
  }
}
