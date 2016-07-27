package dao;

import model.CompagnieAerienneVol;

public interface CompagnieAerienneVolDao extends Dao<CompagnieAerienneVol, Integer> {

	CompagnieAerienneVol findById(Integer id);

}
