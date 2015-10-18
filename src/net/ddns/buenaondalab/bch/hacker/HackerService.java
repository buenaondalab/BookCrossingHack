package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.Local;

@Local
public interface HackerService {

	void syncAll();

}