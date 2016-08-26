package edu.illinois.mitra.lightpaint.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import edu.illinois.mitra.lightpaint.geometry.ImageEdge;

public class WptWriter {

	public static boolean writeWpt(String filename, Set<ImageEdge> edges) {
		FileWriter write = null;
		try {
			write = new FileWriter(filename);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			int idx = 0;
			for (ImageEdge edge : edges) {
				write.write("WAY," + Math.round(edge.getStart().getX()) + ","	+ Math.round(edge.getStart().getY()) + "," + edge.getStart().getColor() + "," + idx + "-A_" + edge.getStart().getSize() + "\n");
				write.write("WAY," + Math.round(edge.getEnd().getX()) + ","	+ Math.round(edge.getEnd().getY()) + "," + edge.getEnd().getColor() + "," + idx + "-B_" + edge.getEnd().getSize() + "\n");
				idx++;
			}
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
