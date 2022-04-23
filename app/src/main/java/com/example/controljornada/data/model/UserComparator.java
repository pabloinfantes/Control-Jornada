package com.example.controljornada.data.model;

import java.util.Comparator;

public class UserComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((User)o1).getNumeroHorasMensuales().compareTo(((User)o2).getNumeroHorasMensuales());
    }
}
