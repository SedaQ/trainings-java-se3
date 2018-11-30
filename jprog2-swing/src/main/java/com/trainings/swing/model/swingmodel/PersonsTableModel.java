/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings.swing.model.swingmodel;

import com.trainings.swing.dto.PersonDto;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 *
 * @author Pavel Seda
 */
public class PersonsTableModel extends AbstractTableModel {

    private List<PersonDto> persons = new ArrayList<>();

    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public void addPerson(PersonDto person) {
        persons.add(person);
    }

    public void addPersons(List<PersonDto> personsNew) {
        persons.addAll(personsNew);
    }

    public void removeRows() {
        persons.clear();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonDto person = persons.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getFirstName();
            case 2:
                return person.getLastName();
            case 3:
                return person.getNickname();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

}
