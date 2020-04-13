package org.web3j.unstoppable;

public class UnstoppableException extends RuntimeException {

  public UnstoppableException(UnstoppableExceptionCode code) {
    super(toMessageFromCode(code));
  }

  private static String toMessageFromCode(UnstoppableExceptionCode code) {
    switch(code) {
      case UnregisteredDomain:
        return "Domain is not registered";
      case UnspecifiedResolver:
        return "Domain has no configure resolver";
      case UnspecifiedCurrency:
        return "Such currency is not configured for this domain";
      case NoRecordFound:
        return "No such record was found";
      default:
        return "Naming service is down";
    }
  }
}