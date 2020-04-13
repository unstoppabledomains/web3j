package org.web3j.unstoppable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.unstoppable.contracts.generated.Cryptoresolver;

import static org.mockito.Mockito.mock;

public class UnstoppableRegistryTest {
  private Web3j web3j;
  private Web3jService Web3jService;
  private UnstoppableResolution unstoppableResolution;

  @BeforeEach
  public void setUp() {
    web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/213fff28936343858ca9c5115eff1419"));
    unstoppableResolution = new UnstoppableResolution(web3j);
  }

  @Test
  public void testResolution() throws Exception {
    Cryptoresolver resolver = unstoppableResolution.getResolver("brad.crypto");
    System.out.println(resolver.toString());
  }
}