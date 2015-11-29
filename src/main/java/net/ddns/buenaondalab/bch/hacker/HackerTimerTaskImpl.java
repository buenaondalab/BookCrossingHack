package net.ddns.buenaondalab.bch.hacker;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class HackerTimerTaskImpl implements HackerTimerTask {
 
   @Resource
   private TimerService timerService;
   
   @EJB
   private HackerService hackerService;
   
   private Logger logger = LoggerFactory.getLogger(HackerTimerTask.class);
 
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
	   
      this.timerService.createTimer(timeout, interval, "Timer interval set to + " + interval + "ms");
   }
 
   /* (non-Javadoc)
    * @see net.ddns.buenaondalab.bch.hacker.HackerTask#doTask(javax.ejb.Timer)
    */

	@Timeout
	@Override
	public void synchDBData(Timer timer) {
		
//		hackerService.syncAll();
//		hackerService.syncCountries();
//		hackerService.syncRegions();
//		hackerService.syncCities();
//		syncPlaces();
//		syncBooks();
		logger.info((String) timer.getInfo());
	}
}