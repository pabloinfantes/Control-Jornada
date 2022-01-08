package com.example.controljornada.data.model;

import java.util.Comparator;

public class ObraComparator implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        return ((Obra)o1).getDescription().compareTo(((Obra)o2).getDescription());
    }
}
