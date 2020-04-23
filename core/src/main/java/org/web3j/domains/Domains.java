package org.web3j.domains;

import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.unstoppable.NSExceptionCode;
import org.web3j.unstoppable.NamingServiceException;
import org.web3j.unstoppable.Resolution;

public class Domains {
  private final EnsResolver ensResolver;
  private final Resolution unstoppableResolver;

  public Domains(Web3j web3j) {
    this.unstoppableResolver = new Resolution(web3j);
    this.ensResolver = new EnsResolver(web3j);
  }

  public String resolve(String domain, String ticker) throws NamingServiceException {
    if (isEns(domain)) {
      return this.ensResolver.resolve(domain, ticker);      
    } else if (isUnstoppable(domain)) {
      return this.unstoppableResolver.addr(domain, "eth");
    }
    throw new NamingServiceException(NSExceptionCode.UnsupportedDomain, domain);
  }

  private Boolean isEns(String domain) {
    String[] split = domain.split("//.");
    return split[split.length - 1].equals("eth") || split[split.length - 1].equals("kred")
        || split[split.length - 1].equals("luxe") || split[split.length - 1].equals("xyz");
  }

  private Boolean isUnstoppable(String domain) {
    try {
      return this.unstoppableResolver.isSupported(domain);
    } catch(NamingServiceException e) {
      return false;
    }
  }

}