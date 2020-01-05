package org.churchsource.churchpeople.address;


import org.churchsource.churchpeople.model.type.AddressType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.churchsource.churchpeople.address.Address.anAddress;
import static org.churchsource.churchpeople.address.AddressMatcher.hasSameStateAsAddress;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddressRepositoryTest {

  @PersistenceContext
  protected EntityManager entityManager;

  @Autowired
  private AddressRepository addressRepository;

  @Test
  public void testSaveAddress_shouldPersistAddress() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();

    Address expectedAddress = anAddress()
            .id(1L)
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();

    Address savedAddress = addressRepository.save(anAddress);

    Address retrievedAddress = entityManager.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
        .setParameter("id", savedAddress.getId()).getSingleResult();

    assertThat(savedAddress, hasSameStateAsAddress(expectedAddress));
    assertThat(retrievedAddress, hasSameStateAsAddress(expectedAddress));
  }

  @Test
  public void testFindAddressByIdThatExists_shouldFindAddress() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();

    entityManager.persist(anAddress);
    entityManager.flush();

    Address retrievedAddress = addressRepository.findAddressById(anAddress.getId());

    assertThat(retrievedAddress, hasSameStateAsAddress(anAddress));
  }

  @Test(expected = NoResultException.class)
  public void testFindAddressByIdThatDoesNotExists_shouldThrowAnException() {
    addressRepository.findAddressById(1L);
  }
}
