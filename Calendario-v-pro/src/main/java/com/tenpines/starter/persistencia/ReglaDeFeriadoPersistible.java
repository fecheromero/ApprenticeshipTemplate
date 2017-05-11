package com.tenpines.starter.persistencia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by fede on 11/05/17.
 */
@Entity
public abstract class ReglaDeFeriadoPersistible {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
