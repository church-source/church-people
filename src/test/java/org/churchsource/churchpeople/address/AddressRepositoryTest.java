package org.churchsource.churchpeople.address;


import org.churchsource.churchpeople.model.type.AddressType;
import org.hamcrest.core.IsNull;
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
import static org.churchsource.churchpeople.address.helpers.AddressMatcher.hasSameStateAsAddress;

import static org.hamcrest.Matchers.is;
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



    Address savedAddress = addressRepository.save(anAddress);

    Address retrievedAddress = entityManager.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
        .setParameter("id", savedAddress.getId()).getSingleResult();

    Address expectedAddress = anAddress()
            .id(savedAddress.getId())
            .type(AddressType.HOME)
            .unitNumber("1")
            .deleted(false)
            .build();

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

  @Test
  public void testUpdateAddress_shouldMergeAddress() {
    Date birthDate = new Date();
    Address anAddress = anAddress()
            .type(AddressType.HOME)
            .unitNumber("1")
            .streetNumber("22")
            .deleted(false)
            .build();

    entityManager.persist(anAddress);
    entityManager.flush();

    Address newUpdatedAddress = anAddress()
            .id(anAddress.getId())
            .type(AddressType.HOME)
            .unitNumber("9")
            .streetNumber("88")
            .deleted(false)
            .build();

    Address updatedMergedAddress = addressRepository.updateAddress(newUpdatedAddress);

    // check that the address entity was persisted correctly
    assertThat(updatedMergedAddress.getId(), is(IsNull.notNullValue()));

    Address retrievedAddress = entityManager.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
            .setParameter("id", updatedMergedAddress.getId()).getSingleResult();

    assertThat(retrievedAddress, hasSameStateAsAddress(newUpdatedAddress));
  }
}
