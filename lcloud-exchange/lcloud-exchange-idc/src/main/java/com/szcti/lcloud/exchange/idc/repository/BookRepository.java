package com.szcti.lcloud.exchange.idc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.szcti.lcloud.exchange.idc.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
