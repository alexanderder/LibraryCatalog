package org.application.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.application.domain.PersonEntity;
import org.application.service.PersonService;

@Named("personBean")
@ViewScoped
public class PersonBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(PersonBean.class.getName());

    private List<PersonEntity> personList;

    private PersonEntity person;

    @Inject
    private PersonService personService;

    public String persist() {

        String message;

        try {

            if (person.getId() != null) {
                person = personService.update(person);
                message = "Entry updated";
            } else {
                person = personService.save(person);
                message = "Entry created";
            }
        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "Your changes could not be saved because an error occurred.";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }

        personList = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return null;
    }

    public String delete() {

        String message;

        try {
            personService.delete(person);
            message = "Entry deleted";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occured", e);
            message = "Error occurred on deleting this entry.";
            // Set validationFailed to keep the dialog open
            FacesContext.getCurrentInstance().validationFailed();
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		
		personList = null;

        return null;
    }

    public PersonEntity getPerson() {
        // Need to check for null, because some strange behaviour of f:viewParam
        // Sometimes it is setting to null
        if (this.person == null) {
            this.person = new PersonEntity();
        }
        return this.person;
    }

    public void setPerson(PersonEntity person) {
        if (person != null) {
            this.person = person;
        }
    }

    public List<PersonEntity> getPersonList() {
        if (personList == null) {
            personList = personService.findAllPersonEntities();
        }
        return personList;
    }

    public void setPersonList(List<PersonEntity> personList) {
        this.personList = personList;
    }

}
