public class Registry {
	public static int levelNum = 1;
	//public static readerLevel[] levels = new readerLevel[4];
	public static readerLevel current;
	public static void registerLevels(){
		register0();
		}
	private static void register0(){
//		levels[0]=new readerLevel("Levels/level1.txt",0);
	}
	public static void setLevel(int num){
		levelNum=num; 
		current = new readerLevel("Levels/level"+num+".txt",num);
		Display.player.loc.setLocation(current.startLocation);
	}
}
