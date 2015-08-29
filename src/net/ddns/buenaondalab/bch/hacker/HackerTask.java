package net.ddns.buenaondalab.bch.hacker;

import javax.ejb.Local;
import javax.ejb.Timer;

@Local
public interface HackerTask {

	void execute(long timeout);

	void execute(long timeout, long interval);

	void synchDBData(Timer timer);

}