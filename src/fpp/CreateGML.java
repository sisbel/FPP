package fpp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CreateGML {

	int mapSize = 0;
	Object[] keySet;
	// Public constructor for creating gml file
	public CreateGML(Map map) throws IOException{
		
		mapSize = map.size();
		keySet = map.keySet().toArray();
		PrintWriter writer = new PrintWriter("TPP.gml", "UTF-8");
		writer.println("graph");
		writer.println("[");
		writer.println("directed 0");
		
		for(int i = 0; i < mapSize; i++) {
			writer.println("node");
			writer.println("[");
			writer.println("id " + keySet[i]);
			writer.println("label \"Node " + map.get(keySet[i]) + "\"");
			writer.println("]");
		}
		
		
		writer.println("]");
		writer.close();
	}
}
