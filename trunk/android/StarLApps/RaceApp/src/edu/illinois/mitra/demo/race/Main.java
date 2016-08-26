package edu.illinois.mitra.demo.race;

import edu.illinois.mitra.starlSim.main.SimSettings;
import edu.illinois.mitra.starlSim.main.Simulation;

public class Main {

	public static void main(String[] args) {
		SimSettings.Builder settings = new SimSettings.Builder();
		settings.OBSPOINT_FILE("Obstacles.wpt");
		settings.N_IROBOTS(2);
		settings.N_QUADCOPTERS(2);
		settings.GPS_POSITION_NOISE(4);
		settings.TIC_TIME_RATE(0.5);
        settings.WAYPOINT_FILE("four1.wpt");
        settings.INITIAL_POSITIONS_FILE("start.wpt");
        settings.DRAW_TRACE_LENGTH(-1);
		settings.DRAW_WAYPOINTS(false);
		settings.DRAW_WAYPOINT_NAMES(false);
		settings.DRAWER(new RaceDrawer());
		settings.DRAW_TRACE(true);
		settings.DRAW__ROBOT_TYPE(true);
//
		
		
		Simulation sim = new Simulation(RaceApp.class, settings.build());
		sim.start();
	}

}
