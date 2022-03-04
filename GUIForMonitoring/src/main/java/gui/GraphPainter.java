package gui;

import java.awt.Canvas;

public class GraphPainter {
	
	public static void paintGraph(Canvas canvas,int[][] array) {
		
	}
	
	public static void paintGraph(Canvas canvas, Integer[] array) {
		int[][] map = new int[array.length][2];
		for(int i = 0 ; i < map.length; i++) {
			map[i][0] = array[i];
			map[i][1] = i;
		}
		paintGraph(canvas,array);
	}
		
}
