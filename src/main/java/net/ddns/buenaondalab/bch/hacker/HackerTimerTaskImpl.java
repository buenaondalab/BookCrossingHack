package net.ddns.buenaondalab.bch.hacker;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ddns.buenaondalab.bch.model.Country;

@Stateless
public class HackerTimerTaskImpl implements HackerTimerTask {
 
   @Resource
   private TimerService timerService;
   
   @EJB
   private HackerService hackerService;
   
   private static final Logger LOGGER = LoggerFactory.getLogger(HackerTimerTask.class);
 
   /* (non-Javadoc)
    * @see net.ddns.buenaondalab.bch.hacker.HackerTask#scheduleSingleTask(long)
    */

   @Override
   public void execute(long timeout) {
      this.timerService.createTimer(timeout, "I'm a single action timer");
   }
 
   /* (non-Javadoc)
    * @see net.ddns.buenaondalab.bch.hacker.HackerTask#scheduleIntervalTask(long, long)
    */

   @Override
   public void execute(long timeout, long interval) {
	   
      this.timerService.createTimer(timeout, interval, "Timer interval set to + " + interval/60000D + "minutes.");
   }
 
   /* (non-Javadoc)
    * @see net.ddns.buenaondalab.bch.hacker.HackerTask#doTask(javax.ejb.Timer)
    */

	@Timeout
	@Override
	public void synchDBData(Timer timer) {
		
		LOGGER.info("Data synchronization with Bookcrossing website started!");
		
//		hackerService.syncAll();
		hackerService.syncCountries();
//		hackerService.syncRegions();
		Country italy = hackerService.findById(Country.class, HackerServiceImpl.ITALY_ID);
		hackerService.syncRegions(italy);
//		hackerService.syncCities();
		hackerService.syncCities(italy);
//		syncPlaces();
		hackerService.syncPlaces(italy);
//		syncBooks();
		LOGGER.info("Data synchronization with Bookcrossing website completed!");
	}
}