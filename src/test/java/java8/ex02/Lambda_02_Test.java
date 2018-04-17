package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test<E> {

    // tag::PersonToAccountMapper[]
    interface PersonToAnyMapper<E> {
        E map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<E> map(List<Person> personList, PersonToAnyMapper mapper) {
        // TODO implémenter la méthode
    	List<E>nouvelleListe = new ArrayList<>();
    	for (int i = 0; i < personList.size(); i++) {
        	nouvelleListe.add((E) mapper.map(personList.get(i)));
		}
        return nouvelleListe;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        PersonToAnyMapper pa = p -> {
            Account	compte = new Account();
            compte.setBalance(100);
            compte.setOwner(p);
            return compte;
            };
        
        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = (List<Account>) map(personList, pa);

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = (List<String>) map(personList, p -> p.getFirstname());


        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
