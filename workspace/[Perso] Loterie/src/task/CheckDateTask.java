package task;

import java.util.Calendar;

import org.bukkit.scheduler.BukkitRunnable;

import fr.dark.loterie.Loterie;

public class CheckDateTask extends BukkitRunnable {

	Loterie core;

	public CheckDateTask(Loterie core) {
		this.core = core;
	}

	@Override
	public void run() {

		String[] time = core.getConfig().getString("Loterie.Time").split("/");
		int hour = Integer.valueOf(time[0]);
		int minute = Integer.valueOf(time[1]);

		Calendar cal = Calendar.getInstance();
		int currHour = cal.get(Calendar.HOUR_OF_DAY);
		int currMin = cal.get(Calendar.MINUTE);

		if (hour == currHour) {
			if (minute == currMin) {
				Loterie.lotteryDraw("§aTirage de la loterie en cours ...");
			}
		}
	}
}
