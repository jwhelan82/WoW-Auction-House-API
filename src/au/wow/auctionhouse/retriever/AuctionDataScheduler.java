package au.wow.auctionhouse.retriever;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import au.wow.auctionhouse.comms.AuctionHouseConstants;

/**
 * Create a Quartz Scheduler to run the auction house retrieval jobs. 
 * 
 * TODO Extend so that any realm can be used. Perhaps read a properties file that will determine which realms to get data from and other settings. 
 * 
 * @author Neil Hoskins
 */
@SuppressWarnings("static-access")
public class AuctionDataScheduler implements AuctionHouseConstants {
	
	Scheduler sched = null;
	
	public AuctionDataScheduler() throws SchedulerException {
		SchedulerFactory sf=new StdSchedulerFactory();
		sched=sf.getScheduler();
		sched.start();
	}
	
	public void stopSchedular() throws SchedulerException  {
		if (sched != null) {
			sched.shutdown();
		}
	}
		
	public void setupRetrievalJob(String realm, String region, String filepath) throws SchedulerException {
		JobDetail jd = new JobDetail(realm + "-" + region + "-" + "auctionRetrieval", sched.DEFAULT_GROUP, AuctionDataRetrievalJob.class);
		JobDataMap map = new JobDataMap();
		map.put("REALM", realm);
		map.put("REGION", region);
		map.put("FILEPATH", filepath);
		jd.setJobDataMap(map);
		SimpleTrigger st=new SimpleTrigger(realm + "-" + region + "-" + "auctionTrigger", 
				sched.DEFAULT_GROUP, new Date(), null, SimpleTrigger.REPEAT_INDEFINITELY, 600L*1000L);
		sched.scheduleJob(jd, st);
	}
	
	public static void main(String args[]) {
		AuctionDataScheduler ads = null;
		try {
			ads = new AuctionDataScheduler();
		} catch (SchedulerException e1) {
			System.err.println("Could not create Quartz Scheduler, exiting");
			e1.printStackTrace();
			System.exit(1);
		}
		try {
			// set up a job for each realm
			// TODO offset the time to start the job by a bit
			for (String realm : REALM_LIST) {
				ads.setupRetrievalJob(realm, DEFAULT_REGION, DEFAULT_FILEPATH);
			}
		} catch(Exception e) {
			e.printStackTrace();
			try {
				ads.stopSchedular();
			} catch (SchedulerException e1) {
				// intentionally ignored
			}
		}		
	}
}
