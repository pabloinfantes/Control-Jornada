package com.example.controljornada.data.model;

import java.util.Comparator;

/**
 * Esta clase sirve para poder ordenar las obras
 * @author pablo
 *
 */
public class ObraComparator implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        return ((Obra)o1).getDescription().compareTo(((Obra)o2).getDescription());
    }
}
