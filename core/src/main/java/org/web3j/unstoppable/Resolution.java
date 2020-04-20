/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.web3j.unstoppable;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Resolution {
   private NamingService[] services;
   private Web3j web3;

    public Resolution(Web3j web3) {
        this.web3 = web3;
        this.services = this.buildServices(this.web3, false);
    }

    public Resolution(String provider) {
        this.web3 = Web3j.build(new HttpService(provider));
        this.services = this.buildServices(this.web3, false);
    }

    public Resolution(Web3j web3, Boolean verbose) {
        this.web3 = web3;
        this.services = this.buildServices(this.web3, verbose);
    }

    public Resolution(String provider, Boolean verbose) {
        this.web3 = Web3j.build(new HttpService(provider));
        this.services = this.buildServices(this.web3, verbose);
    }

    public String addr(String domain, String ticker) throws NamingServiceException {
        NamingService service = this.findService(domain);
        return service.addr(domain, ticker);
    }

    public String namehash(String domain) throws NamingServiceException {
        NamingService service = this.findService(domain);
        return service.namehash(domain);
    }
    
    public String ipfsHash(String domain) throws NamingServiceException {
        NamingService service = this.findService(domain);
        return service.ipfsHash(domain);
    }

    public String email(String domain) throws NamingServiceException {
        NamingService service = this.findService(domain);
        return service.email(domain);
    }

    public String owner(String domain) throws NamingServiceException {
        NamingService service = this.findService(domain);
        return service.owner(domain);
    }

    private NamingService findService(String domain) throws NamingServiceException {
        for (NamingService service : this.services) {
            if (service.isSupported(domain)) return service;
        }
        throw new NamingServiceException(NSExceptionCode.UnsupportedDomain, domain);
    }

    private NamingService[] buildServices(Web3j web3, Boolean verbose) {
        NamingService[] services = new NamingService[1];
        services[0] = new CNS(web3, verbose);
        return services;
    }
}
