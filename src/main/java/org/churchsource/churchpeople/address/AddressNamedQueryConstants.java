package org.churchsource.churchpeople.address;

public class AddressNamedQueryConstants {

  public static final String NAME_FIND_ADDRESS_BY_ID = "Address.findAddressById";
  public static final String QUERY_FIND_ADDRESS_BY_ID = "SELECT a FROM Address a WHERE a.id = :id";

}
