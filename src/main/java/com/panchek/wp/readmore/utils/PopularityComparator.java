package com.panchek.wp.readmore.utils;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.payload.BookReturn;

import java.util.Comparator;

public class PopularityComparator implements Comparator<BookReturn> {
    @Override
    public int compare(BookReturn o1, BookReturn o2) {
        return -Double.compare(o1.getPopularity(),o2.getPopularity());
    }
}
