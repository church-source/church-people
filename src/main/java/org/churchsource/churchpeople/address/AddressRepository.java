package org.churchsource.churchpeople.address;

import org.churchsource.churchpeople.people.PeopleNamedQueryConstants;
import org.churchsource.churchpeople.people.Person;
import org.churchsource.churchpeople.repository.AbstractRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class AddressRepository extends AbstractRepository<Address> {

  public Address findAddressById(Long id) throws NoResultException {
    return entityManager.createNamedQuery(AddressNamedQueryConstants.NAME_FIND_ADDRESS_BY_ID, Address.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public Address updateAddress(Address address) {
    Address existingAddress = findAddressById(address.getId());
    Address updatedAddress = new Address();
    existingAddress.mergeEntities(address, updatedAddress);
    return update(updatedAddress);
  }
}
