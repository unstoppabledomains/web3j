package org.web3j.unstoppable;

import java.math.BigInteger;

import org.web3j.ens.NameHash;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.unstoppable.contracts.generated.Cryptoregistry;
import org.web3j.unstoppable.contracts.generated.Cryptoresolver;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;

public class UnstoppableResolution {
  private final Web3j web3j;
  private final Cryptoregistry registryContract;
  private static final String cryptoRegistry = "0xd1e5b0ff1287aa9f9a268759062e4ab08b9dacbe";
  private final TransactionManager transactionManager;

  public UnstoppableResolution(Web3j web3j) {
    this.web3j = web3j;
    this.transactionManager = new ClientTransactionManager(web3j, null); // don't use empty string
    this.registryContract = this.getRegistryContract();
  }

  String namehash(String domain) {
    return NameHash.nameHash(domain);
  }

  // getAddr(String domain, String currencyTicker)

  boolean isValidDomain(String domain) {
    return (domain.length() > 0 && ( domain.endsWith(".zil") || domain.endsWith(".crypto")));
  }

  private Cryptoregistry getRegistryContract() {
    return Cryptoregistry.load(UnstoppableResolution.cryptoRegistry, this.web3j, this.transactionManager,
        new DefaultGasProvider());
  }

  public Cryptoresolver getResolver(String domain) throws UnstoppableException {
    BigInteger tokenId = this.getTokenId(domain);
    try {
      String resolverAddress = this.registryContract.resolverOf(tokenId).send();
      return Cryptoresolver.load(resolverAddress, this.web3j, this.transactionManager, new DefaultGasProvider());
    } catch(Exception e) {
      throw new UnstoppableException(UnstoppableExceptionCode.UnregisteredDomain);
    }
  }

  private BigInteger getTokenId(String domain) {
    String hash = NameHash.nameHash(domain);
    return new BigInteger(hash, 16);
  }

  private String getOwner(String domain) {
    try {
      BigInteger tokenId = this.getTokenId(domain);
      return this.registryContract.ownerOf(tokenId).send();
    } catch(Exception e) {
      return null;
    }
  }
}