package dao;

import model.Firms;

import java.util.List;

public interface FirmsDao {
    List<Firms> findAll();
    Firms getByID (int id);
    Firms getByName (String firmName);
    boolean insert (Firms firms);
    boolean deleteByFirmName (String name);
}
