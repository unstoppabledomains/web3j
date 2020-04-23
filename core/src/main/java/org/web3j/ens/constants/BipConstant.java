package org.web3j.ens.constants;

public class BipConstant {
  private int constant;
  private String coinSymbol;
  private String coinName;

  public BipConstant(int constant, String symbol, String coinName) {
    this.constant = constant;
    this.coinSymbol = symbol;
    this.coinName = coinName;
  }

  public int getConstant() { return this.constant; }
  public String getSymbol() { return this.coinSymbol; }
  public String getName() { return this.coinName; }

}